package functionalities;

 

import utilities.ExtentReport;
import utilities.InputFromExcel;
import utilities.SendToExcel;
import utilities.Wait;

 

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

 

@Test
public class Search {
    // Declaration of needed variables
    Properties p = new Properties();
    public static WebDriver driver;
    public static Wait wait = new Wait();
    String languageDropDown, englishCheckBox, levelDropDown, beginnerCheckBox, path;
    // String screenshotpath = p.getProperty("screenShotPath");

 

    // Creating an object of class extent report
    ExtentReport ex;

 

    // Constructor to initialize reader object with our properties file and load it
    public Search(WebDriver d, ExtentReport extent) throws Exception {
        // initializing the driver to global driver in this class
        driver = d;

 

        // creating a filereader object to read file"projec.properties" file
        FileReader reader = new FileReader("project.properties");
        p.load(reader);

 

        ex = extent;
    }

 

    public void inputToSearchBar() throws IOException, InterruptedException {
        String searchName, searchButtonCssSelector;

 

        JavascriptExecutor js = (JavascriptExecutor) driver;

 

        // retrieving the search bar's name and search button's css selector path from
        // "project.properties"
        searchName = p.getProperty("searchName");
        searchButtonCssSelector = p.getProperty("searchButtonCssSelector");

 

        String course = InputFromExcel.searchInput();

 

        // Sending the value to search bar
        WebElement search = driver.findElement(By.name(searchName));
        js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", search);
        search.sendKeys(course);

 

        // Logging the action
        ex.actionLog("Entered \"web development\" in search bar");

 

        // Creating a screenshot driver and storing in scrFile.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

        path = System.getProperty("user.dir") + "\\screenshot\\InputSearch.png";

 

        // Now we take a screenshot
        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: Search Bar input", path);

 

        wait.waitImplicit(driver);

 

        // Clicking on the search button for the results
        driver.findElement(By.cssSelector(searchButtonCssSelector)).click();

 

        wait.waitImplicit(driver);

 

        // Logging the action
        ex.actionLog("Clicked on search button");

 

        // Scroll down the web page
        js.executeScript("window.scrollBy(0,2400)");

 

        // Creating a screenshot driver and storing in scrFile.
        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

        path = System.getProperty("user.dir") + "\\screenshot\\InputSearch.png";

 

        // Now we take a screenshot
        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: Output of search", path);

 

    }

 

    public void selectFilter() throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

 

        languageDropDown = p.getProperty("languageDropDownXpath");
        englishCheckBox = p.getProperty("englishCheckBoxXpath");

 

        // Click on the language dropdown list by using it's xpath
        WebElement language = driver.findElement(By.xpath(languageDropDown));
        js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", language);
        language.click();

 

        wait.waitImplicit(driver);

 

        // Select language as "English" using xpath locator
        WebElement english = driver.findElement(By.xpath(englishCheckBox));
        js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", english);
        english.click();

 

        wait.waitImplicit(driver);

 

        // Logging the action
        ex.actionLog("English language selected from language drop down");

 

        // Creating a screenshot driver and storing in scrFile.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

        path = System.getProperty("user.dir") + "\\screenshot\\EnglishSelected.png";

 

        // Now we take a screenshot
        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: English Language selected", path);

 

        wait.waitImplicit(driver);

 

        levelDropDown = p.getProperty("levelDropDownXpath");
        beginnerCheckBox = p.getProperty("beginnerCheckBoxXpath");

 

        // Click on the level dropdown list by using it's xpath
        WebElement level = driver.findElement(By.xpath(levelDropDown));
        js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", level);
        level.click();

 

        wait.waitImplicit(driver);

 

        // Select level as "Beginner" using xpath locator
        WebElement beginner = driver.findElement(By.xpath(beginnerCheckBox));
        js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", beginner);
        beginner.click();

 

        wait.waitImplicit(driver);

 

        js.executeScript("arguments[0].scrollIntoView();", level);

 

        // Logging the action
        ex.actionLog("Beginner Level Selected from level drop down");

 

        // Creating a screenshot driver and storing in scrFile.
        scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

 

        path = System.getProperty("user.dir") + "\\screenshot\\BeginnerSelected.png";

 

        // Now we take a screenshot
        FileUtils.copyFile(scrFile, new File(path));

 

        // Logging the screenshot
        ex.screenShotLog("Snapshot: Beginner Level Selected", path);

 

        wait.waitImplicit(driver);

 

    }

 

    public void retrieveValues() throws Exception {
        
        //retrieveing the course details
        String n[] = new String[3];
        String lh[] = new String[3];
        String r[] = new String[3];

 

        SendToExcel s = new SendToExcel();

 

        // Logging the action
        ex.actionLog("Retrieveing two course details");

 

        n[0] = "Course Name";
        lh[0] = "Total Learning Hours";
        r[0] = "Rating";

 

        //retrieveing 1st courses details
        WebElement name = driver.findElement(By.xpath(p.getProperty("courseName1")));
        n[1] = name.getText();

 

        WebElement lhw = driver.findElement(By.xpath(p.getProperty("learningHrs1")));
        lh[1] = lhw.getText();

 

        WebElement rw = driver.findElement(By.xpath(p.getProperty("rating1")));
        r[1] = rw.getText();

 

        //displaying values on console
        String c1 = "Name : " + n[1] + "\nHours : " + lh[1] + "\nRating : " + r[1] + "\n";
        System.out.println(c1);

 

        // Logging the action
        ex.actionLog("Console Output: " + c1);

 

        name = driver.findElement(By.xpath(p.getProperty("courseName2")));
        n[2] = name.getText();

 

        lhw = driver.findElement(By.xpath(p.getProperty("learningHrs2")));
        lh[2] = lhw.getText();

 

        rw = driver.findElement(By.xpath(p.getProperty("rating2")));
        r[2] = rw.getText();

 

        //displaying values on console
        String c2 = "Name : " + n[2] + "\nHours : " + lh[2] + "\nRating : " + r[2] + "\n";
        System.out.println(c2);

 

        // Logging the action
        ex.actionLog("Console Output: " + c2);

 

        // Logging the action
        ex.actionLog("Values retrieved and saved in excel");

 

        //sending value to excel
        s.searchResultToExcel(n, lh, r);
    }
}