package api.utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class DataProviderClass {

   @DataProvider(name = "Usernames")
    public String[] getUserName() throws IOException {
       String path = System.getProperty("user.dir") + File.separator + "DataDriven_Payload.xlsx";
       XLUtility xlUtility = new XLUtility(path);

       String[] username = new String[xlUtility.getRowCount("Sheet1")];
       for (int i=1;i<=xlUtility.getRowCount("Sheet1");i++)
       {
           username[i-1]=xlUtility.getCellData("Sheet1",i,1);
       }

       return username;
    }

    @DataProvider(name="Userdata")
    public String[][] createUser() throws IOException {
       String path = System.getProperty("user.dir")+File.separator+"DataDriven_Payload.xlsx";
       XLUtility xlUtility = new XLUtility(path);
       String[][] userData = new String[xlUtility.getRowCount("Sheet1")][xlUtility.getCellCount("Sheet1",1)];

       for (int i=1;i<=xlUtility.getRowCount("Sheet1");i++)
       {
           for (int j=0; j<xlUtility.getCellCount("Sheet1",i);j++)
           {
                userData[i-1][j]=xlUtility.getCellData("Sheet1",i,j);
           }
       }

       return userData;
    }
}
