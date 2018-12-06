package POM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class BasePage{

	//public static final String DRIVER_PATH = "E:\\_Programs\\GekoDriver\\geckodriver.exe";
	public static final String DRIVER_PATH = "F:\\Programs\\ChromeDriver\\chromedriver.exe";
	protected Logger logger = LogManager.getLogger(this);


	public static int waiterTime = 10;
	static protected WebDriver driver;


	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public static BasePage open() {
		final String BasePageURL = "http://automationpractice.com/index.php";
		driver.navigate().to(BasePageURL);
		return new BasePage(driver);
	}

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

	@FindBy(id = "email")
	private WebElement emailTextbox;

	@FindBy(id = "passwd")
	private WebElement passwordTextbox;

	@FindBy(id = "SubmitLogin")
	private WebElement loginButton;
	
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

	public void doLogin(String email, String password){
		emailTextbox.sendKeys(email);
		passwordTextbox.sendKeys(password);
		loginButton.click();
	}

	@BeforeSuite
	public void initionalBrowser() {
		logger.info("Browser Initialization");
		//System.setProperty("webdriver.gecko.driver", BasePage.DRIVER_PATH);
		System.setProperty("webdriver.chrome.driver",BasePage.DRIVER_PATH);
		driver = new ChromeDriver();
	}

	@AfterSuite
	public void closeBrowser() {
		//driver.close();
		driver.quit();
	}

	@BeforeTest
	public void openBasePage(){
		logger.info("Open main page");
		BasePage.open();
	}
}


