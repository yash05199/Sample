package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Wait {
	@Test
	// Method for Wait until Pageload
	public void waitImplicit(WebDriver driver) {

		// Method to return boolean value as page is loaded completely by using
		// JavascriptExecutor
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// Explicit Wait with 30 seconds timeout
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// Wait until expected condition is true
		wait.until(pageLoadCondition);
	}
}