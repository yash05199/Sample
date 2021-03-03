package utilities;

 

import org.testng.annotations.Test;

 

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

 

@Test
public class ExtentReport {

 

    String extentReportFile = System.getProperty("user.dir") + "\\Extented_Report\\ReportFile.html";
    String extentReportImage;

 

    // Create object of extent report and specify the report file path.
    ExtentReports extent = new ExtentReports(extentReportFile, false);

 

    // Start the test using the ExtentTest class object.
    ExtentTest extentTest = extent.startTest("Identifying Courses", "Hackathon");

 

    public void actionLog(String action) {
        //Logging the action
        extentTest.log(LogStatus.INFO, action);
    }

 

    public void screenShotLog(String action, String path) {
        //Logging the screenshot
        extentTest.log(LogStatus.INFO, action + extentTest.addScreenCapture(path));
    }

 

    public void completeReport() {

 

        // close report
        extent.endTest(extentTest);

 

        // writing everything to document
        extent.flush();
    }
}