package tests;

 

import utilities.*;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import functionalities.Business;
import functionalities.Search;

 

public class Courses {
    // Declaration of needed variables
    public static WebDriver driver;
    public static Wait wait = new Wait();

 

    // Creating an object of class extent report
    ExtentReport ex = new ExtentReport();

 

    // Create properties reference to read from properties file
    Properties p = new Properties();

 

    // Constructor to initialize reader object with our properties file and load it
   
    public Courses() throws Exception {
        FileReader reader = new FileReader("project.properties");
        p.load(reader);
    }

 

    @BeforeSuite
    public void setDriver() throws Exception {
        // driver initialization
        MainDriver obj = new MainDriver();
        driver = obj.mainDriver(ex);

 

        // Logging the action
        ex.actionLog("Browser Launched");

 

        // Letting the program to go to sleep for 2 seconds so that the page can load
        // and we can take screenshot.
        wait.waitImplicit(driver);

 

        // Creating a screenshot driver and storing in scrFile.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "\\screenshot\\HomePage.png";

 

        // Now we take a screenshot
        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: Home Page", path);
    }

 

    //@Test(priority = 1)
    public void searchWeb() throws Exception {
        // Creating the object of Search class to execute the search procedure
        Search search = new Search(driver, ex);

 

        // Giving the input to the search bar
        search.inputToSearchBar();

 

        // selecting the filters
        search.selectFilter();

 

        // retrieving values and inserting them in excel
        // (name, total learning hours and rating)
        search.retrieveValues();

 

    }

 

   /* @Test(priority = 2)
    public void retrieveLanguage() throws Exception {
        Language lan = new Language(driver, ex);

 

        // RETRIEVE ALL LANGUAGES
        lan.getLanguage();

 

        // COUNT THE NUMBER OF LANGUAGES
        lan.count();

 

        // SAVING TO EXCEL FILE
        lan.saveExcel();
    }*/

 

   /* @Test(priority = 3)
    // Method to retrive level and it's count
    public void retrieveLevel() throws Exception {

 

        // Constructor for Level class
        Level l = new Level(driver, ex);

 

        // Calling method for Retrieve Levels and their Count using constructor
        l.getLearningLevels();

 

        // Calling method to store Lists of Level and Count into Excel file using
        // constructor
        l.sendListsToExcel();
    }*/

 

    //@Test(priority = 4)
    // To Retrieve Udemy Demo Form
  @Test(priority = 4)
    public void business() throws Exception {
        
        Business b = new Business(driver, ex);
        
        // Method to click on Udemy Logo
        b.clickLogo();
        
        // Method to Provide FormValues to Udemy Demo Form
        b.setFormValues();
        
        // Method to print messageOn Console and Excel
        b.printMessage();
    }

 

    @AfterSuite
    public void closeTheBrowser() {
        // Close the browser
        driver.quit();

 

        // Logging the action
        ex.actionLog("Browser closed");

 

        // writing everything to document
        ex.completeReport();
    }
}