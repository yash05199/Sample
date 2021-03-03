package utilities;

 

import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

 

public class MainDriver 
{
    @Test
    public WebDriver mainDriver(ExtentReport extent) throws Exception {
         WebDriver driver = null;
         
        // Creating an object of class extent report
        ExtentReport ex = extent;
        
        // Asking user for his choice of web driver
        System.out.println("Enter the name of the browser from available choices:\n1: Chrome\n2: Firefox\n3: IE");
        Scanner sc = new Scanner(System.in);
        
        ex.actionLog("Taking the choice of browser from the user");
        String choice = sc.next();
        
        // Closing scanner object to prevent leak 
        sc.close();
        
        // Creating object of ApplicationDriver class to invoke driver depending upon user choice and available choices
        ApplicationDriver obj = new ApplicationDriver();
        if(choice.equalsIgnoreCase("chrome"))
        {
            // If user enters chrome as the driver, calling the ChromeDriver class 
            // and storing the returned driver to static variable
            driver = obj.ChromeDriver();
            ex.actionLog("User has selected chrome");
            return driver;
        }
        else if(choice.equalsIgnoreCase("firefox"))
        {
            // If user enters firefox as the driver, calling the ChromeDriver class 
            // and storing the returned driver to static variable
            driver = obj.FirefoxDriver();
            ex.actionLog("User has selected firefox");
            return driver;
        }
        else if(choice.equalsIgnoreCase("ie"))
        {
            // If user enters firefox as the driver, calling the ChromeDriver class 
            // and storing the returned driver to static variable
            driver = obj.InternetExplorer();
            ex.actionLog("User has selected internet explorer");
            return driver;
        }
    return driver;
    }
}