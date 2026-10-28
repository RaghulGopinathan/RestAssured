package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DD_DataProvider {

    XLUtility xlUtility = new XLUtility("C:\\Users\\raghul.g01\\OneDrive - Infosys Limited\\HUB Tickets\\Personal\\RestAssured\\GoRest_framework\\DataDriven_Payload.xlsx");
    String sheetName = "Sheet1";
    int rowCount;
    int columnCount;

    @DataProvider(name="userData")
    public String[][] userData() throws IOException {

         rowCount = xlUtility.getRowCount(sheetName);
         columnCount = xlUtility.getCellCount(sheetName,1);

        String[][] userData = new String[rowCount][columnCount];

        for (int i=1; i<=rowCount;i++)
        {
            for (int j=0;j<columnCount;j++)
            {
                userData[i-1][j] = xlUtility.getCellData(sheetName,i,j);
            }
        }

        return userData;
    }
}
