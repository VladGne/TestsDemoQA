package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
	public WebDriver driver;
	
	//Constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public static BasePage open(WebDriver driver) {
		final String BasePageURL = "http://automationpractice.com/index.php";
		driver.navigate().to(BasePageURL);
		return new BasePage(driver);
	}
	
	//Click Method
	public void click(By elementLocator) {
		driver.findElement(elementLocator).click();
	}
	
	//Write Text
	public void writeText(By elementLocator, String text) {
		driver.findElement(elementLocator).sendKeys(text);
	}
	
	//Read Text
	public String readText(By elementLocator) {
		return driver.findElement(elementLocator).getText();
	}
}


