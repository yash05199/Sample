package functionalities;

 

import utilities.ExtentReport;
import utilities.SendToExcel;
import utilities.Wait;

 

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

 

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

 

public class Level {
    public static WebDriver driver;
    public static Actions action;

 

    // Creating instance of Wait class
    public static Wait wait = new Wait();

 

    // Creating an object of class extent report
    ExtentReport ex;

 

    // List of String and Integer to store Level and Count
    List<String> level = new ArrayList<String>();
    List<Integer> count = new ArrayList<Integer>();

 

    // Creating instance of SendToExcel class
    SendToExcel sendExcel = new SendToExcel();

 

    // Creating instance of System Properties
    Properties config = new Properties();

 

    String path;

 

    public Level(WebDriver d, ExtentReport extent) throws Exception {
        // initializing the driver to global driver in this class
        driver = d;

 

        // creating a file reader object to read file"projec.properties" file
        FileReader reader = new FileReader("project.properties");
        config.load(reader);

 

        ex = extent;
    }

 

    // Method to get Learning levels and its total count
    @Test
    public void getLearningLevels() throws Exception {
        
        System.out.println("Inside Level");
        
        // Implicit wait for 15 seconds
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        JavascriptExecutor js = (JavascriptExecutor) driver;

 

        WebElement ll = driver.findElement(By.xpath(config.getProperty("levelLabel")));
        ll.click();
        
        // Scroll down window
        js.executeScript("window.scrollBy(0, 2000)");
        
        // Logging the action
        ex.actionLog("Clicked on Level label to display collapsable");

 

        //wait.waitImplicit(driver);

 

        // To find whether the button is expanded
        String levelValue = driver.findElement(By.xpath(config.getProperty("levelButtonExpand")))
                .getAttribute("aria-expanded");

 

        // If Button Expanded value is true
        if (levelValue.contains("true")) {

 

            // Creating a screenshot driver and storing in scrFile.
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

            // Now we take a screenshot
            path = System.getProperty("user.dir") + "\\screenshot\\LanguageLevelDropDown.png";
            FileUtils.copyFile(scrFile, new File(path));

 

            // Logging the action
            ex.screenShotLog("Snapshot: Level DropDown Page", path);

 

            // Created List of WebElements named allLevel
            List<WebElement> allLevel = driver.findElements(By.xpath(config.getProperty("levelOptions")));

 

            // Iterating values of allLevel to split Level and Count
            Iterator<WebElement> itrt = allLevel.iterator();
            while (itrt.hasNext()) {

 

                // The value come during iteration is stored in text variable
                String text = itrt.next().getText();
                String[] arrSplit = text.split("\n");
                for (int i = 0; i < arrSplit.length; i++) {
                    if (i % 2 == 0) {
                        level.add(arrSplit[i]);
                    } else {
                        int valueInt = Integer.parseInt(
                                arrSplit[i].replace("(", "").replace(")", "").replace(",", "").trim().toString());
                        count.add(valueInt);
                    }
                }
            }
            //displaying values on console
            String msg = "Levels: " + level+ "\nNo of Courses: " + count;
            System.out.println(msg);
            ex.actionLog("Console output: "+msg);

 

        } else {
            //displaying values on console
            String msg ="Level not Expanded";
            System.out.println(msg);
            ex.actionLog("Console output: "+msg);
            //System.exit(0);
        }
    }

 

    // Method to send lists to excel file
    public void sendListsToExcel() throws Exception {
        // Sending lists level and count as parameter using sendExcel object
        sendExcel.levelResultToExcel(level, count);
        ex.actionLog("Sending to Excel file");
    }

 

}