package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;

    // FIX: Replaced "public ExtentTest test;" with ThreadLocal to safely isolate threads
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    String repName;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter("./reports/" + repName);
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
        sparkReporter.config().setReportName("API Automation Test Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "Rest Assured API Automation");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // FIX: Create the test node immediately when the test thread starts running
        ExtentTest testNode = extent.createTest(result.getName());
        testNode.assignCategory(result.getMethod().getGroups());
        extentTest.set(testNode); // Bind it safely to the current thread
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // FIX: Retrieve the thread's isolated test instance using .get()
        extentTest.get().log(Status.PASS, "Test Passed");
        extentTest.remove(); // Clean up thread
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed");
        if (result.getThrowable() != null) {
            extentTest.get().log(Status.FAIL, result.getThrowable());
        }
        extentTest.remove(); // Clean up thread
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            extentTest.get().log(Status.SKIP, result.getThrowable());
        }
        extentTest.remove(); // Clean up thread
    }

    @Override
    public void onFinish(ITestContext testContext) {
        if (extent != null) {
            extent.flush();
        }
    }
}
