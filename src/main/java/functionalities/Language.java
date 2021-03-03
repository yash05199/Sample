package functionalities;

 

import utilities.ExtentReport;
import utilities.SendToExcel;
import utilities.Wait;

 

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentTest;

 

public class Language {
    public static WebDriver driver;
    public static Actions action;
    public static String[] list;
    public static String num;
    public static WebElement categories, taOption, langOption;
    public static List<WebElement> elements;

 

    // Creating instance of Wait class
    public static Wait wait = new Wait();
    ExtentTest extentTest;

 

    // List of String and Integer to store Level and Count
    List<String> level = new ArrayList<String>();
    List<Integer> count = new ArrayList<Integer>();

 

    // Creating instance of System Properties
    Properties config = new Properties();

 

    ExtentReport ex;

 

    String path;

 

    public Language(WebDriver d, ExtentReport extend) throws Exception {
        // initializing the driver to global driver in this class
        driver = d;

 

        // creating a filereader object to read file"projec.properties" file
        FileReader reader = new FileReader("project.properties");
        config.load(reader);

 

        ex = extend;

 

    }

 

    // Method to Navigate to Language Course Page from dropdown
    @Test
    public void getLanguage() throws Exception {

 

        //clicking on udemy logo to go on homepage
        driver.findElement(By.cssSelector(config.getProperty("homepage"))).click();

 

        // Logging the action
        ex.actionLog("Back to Home Page");

 

        // Creating a screenshot driver and storing in scrFile.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        path = System.getProperty("user.dir") + "\\screenshot\\HomePage2.png";

 

        // Now we take a screenshot
        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: Home Page after search result", path);

 

        action = new Actions(driver);

 

        JavascriptExecutor js = (JavascriptExecutor) driver;

 

        //categories = driver.findElement(By.cssSelector(config.getProperty("categories")));
        categories = driver.findElement(By.xpath("//span[contains(.,'Categories')]"));

 

        // To highlight selected text
        js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", categories);

 

        categories.click();

 

        // Logging the action
        ex.actionLog("Clicked on Categories");

 

        // Creating a screenshot driver and storing in scrFile.
        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

        path = System.getProperty("user.dir") + "\\screenshot\\Categories.png";

 

        // Now we take a screenshot
        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: After clicking on Categories", path);

 

        wait.waitImplicit(driver);

 

        taOption = driver.findElement(By.xpath(config.getProperty("firstSelect")));

 

        // To highlight selected text
        js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", taOption);

 

        action.moveToElement(taOption).perform();

 

        // Logging the action
        ex.actionLog("Hovering on Teaching & Acedmics");

 

        // Creating a screenshot driver and storing in scrFile.
        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

        path = System.getProperty("user.dir") + "\\screenshot\\TeachingAcademicsDropDown.png";

 

        // Now we take a screenshot
        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: Teaching & Academics", path);

 

        wait.waitImplicit(driver);

 

        langOption = driver.findElement(By.xpath(config.getProperty("secondSelect")));

 

        js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", langOption);

 

        action.moveToElement(langOption).perform();

 

        wait.waitImplicit(driver);

 

        // Logging the action
        ex.actionLog("Hovering on Language");

 

        // Creating a screenshot driver and storing in scrFile.
        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

        path = System.getProperty("user.dir") + "\\screenshot\\LanguagesDropDown.png";

 

        // Now we take a screenshot
        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: Languages", path);

 

        // Logging the action
        ex.actionLog("Clicked on Language option");

 

        wait.waitImplicit(driver);
    }

 

    @Test
    public void count() throws IOException {
        elements = driver.findElements(By.xpath(config.getProperty("languageList")));

 

        num = elements.size() + "";
        
        String str = "Number of Languages Available: " + num;

 

        // Logging the action
        ex.actionLog("Console output: " + str);

 

        //displaying values on console
        System.out.println(str);

 

        list = new String[elements.size()];

 

        // Creating a screenshot driver and storing in scrFile.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

        // Now we take a screenshot
        path = System.getProperty("user.dir") + "\\screenshot\\NumberofLanguagesAvailable.png";

 

        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: Number of language available", path);

 

        for (int i = 0; i < elements.size(); i++) {

 

            System.out.println(elements.get(i).getText());

 

            list[i] = elements.get(i).getText();
        }
        
        // Logging the action
        ex.actionLog("Console Output: " + list);
    }

 

    public void saveExcel() throws Exception {
        //sending the values to excel
        SendToExcel excel = new SendToExcel();
        excel.languageToExcel(list, num);
        
        //langOption.click();
        
        //logging the action
        ex.actionLog("Saving to Excel file");
    }

 

}