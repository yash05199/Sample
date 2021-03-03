package utilities;

 

import java.io.FileReader;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;

 

public class ApplicationDriver
{
        // Create properties reference to read from properties file
        Properties p = new Properties();
        // Constructor to initialize reader object with our properties file and load it
        public ApplicationDriver()throws Exception
        {
            FileReader reader = new FileReader("project.properties");        
            p.load(reader);
        }
        // Method to invoke Chrome driver
        public WebDriver ChromeDriver()
        {
            WebDriver driver;

 

            //Set driver properties (driverName, driverLocation)
            System.setProperty("webdriver.chrome.driver","C:\\Users\\YASH\\eclipse-workspace\\Hackathon\\Hackathon(IC)\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            
            //Get the value of baseURL by driver to open the website/web application
            driver.get(p.getProperty("baseUrl"));
            
            //Maximize the opened window
            driver.manage().window().maximize();
            return driver;
        }    
        // Method to invoke Firefox driver
        public WebDriver FirefoxDriver()
        {
            WebDriver driver;
            
            //Set driver properties (driverName, driverLocation)
            System.setProperty("webdriver.gecko.driver","C:\\Users\\YASH\\eclipse-workspace\\Hackathon\\Hackathon(IC)\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
            
            //Get the value of baseURL by driver to open the website/web application
            driver.get(p.getProperty("baseUrl"));
            
            //Maximize the opened window
            driver.manage().window().maximize();
            return driver;
        }
        public WebDriver InternetExplorer() {
            WebDriver driver;
            
            //Set driver properties (driverName, driverLocation)
            System.setProperty("webdriver.ie.driver","C:\\Users\\YASH\\eclipse-workspace\\Hackathon\\Hackathon(IC)\\drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            
            //Get the value of baseURL by driver to open the website/web application
            driver.get(p.getProperty("baseUrl"));
            
            //Maximize the opened window
            driver.manage().window().maximize();
            return driver;
        }
}