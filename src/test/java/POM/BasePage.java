package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.WatchEvent;

public class BasePage {

	//public static final String DRIVER_PATH = "E:\\_Programs\\GekoDriver\\geckodriver.exe";
	public static final String DRIVER_PATH = "E:\\_Programs\\ChromeDriver\\chromedriver.exe";

	//Classes
	public static final String LOGOUT_BUTTON_LOCATOR = "logout"; 
	public static final String LOGIN_BUTTON_LOCATOR = "login";
	public static final String ALERTS_LOCATOR = "alert-danger";

	public static int waiterTime = 10;
	public WebDriver driver;


	@FindBy(className = "alert-danger")
	protected WebElement alertMessage;

	public boolean checkAlertMessage(String expectedMessage){

		return alertMessage.getText().equals(expectedMessage);
	}

	public WebElement waitForAlertMessage(){

		WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
		WebElement message;
		while (alertMessage.getText().equals("") && !wait.equals(0)){
		}

		message = wait.until((WebDriver d) -> alertMessage) ;

		return message;
	}
	
	//Constructor
	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
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

	public String getValue(By elementLocator) {
		return driver.findElement(elementLocator).getAttribute("value");
	}
}


