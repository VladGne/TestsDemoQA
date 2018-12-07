package POM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class BasePage{

	static Properties prop = new Properties();

	// Read config.properties file
	static {
		InputStream input = null;
		try {
			input =new FileInputStream("src/main/resources/config.properties");
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//public static final String DRIVER_PATH = "E:\\_Programs\\GekoDriver\\geckodriver.exe";
	public static final String DRIVER_PATH = "F:\\Programs\\ChromeDriver\\chromedriver.exe";
	protected Logger logger = LogManager.getLogger(this);

	public static int waiterTime = 10;

	static protected WebDriver driver;

	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public static BasePage open(WebDriver driver) {
		BasePage.driver = driver;
		final String BasePageURL = prop.getProperty("durl"); //"http://automationpractice.com/index.php";	//
		BasePage.driver.navigate().to(BasePageURL);
		return new BasePage(BasePage.driver);
	}

	@FindBy(className = "logout")
	private WebElement logoutButton;

	@FindBy(id = "email")
	private WebElement emailTextbox;

	@FindBy(id = "passwd")
	private WebElement passwordTextbox;

	@FindBy(id = "SubmitLogin")
	private WebElement loginButton;

	public void click(By elementLocator) {
		driver.findElement(elementLocator).click();
	}

	public void writeText(By elementLocator, String text) {
		driver.findElement(elementLocator).sendKeys(text);
	}

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

	public List<WebElement> getMainElements(Class classObject){

		List<WebElement> elements = new LinkedList<>();

		for (Field field : classObject.getDeclaredFields()) {
			if (field.getType() == WebElement.class){
				try {
					elements.add( (WebElement) field.get(classObject) );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return elements;
	}

	public List<WebElement> getVisibleElementsFromList(List<WebElement> elements){

		List<WebElement> visibleElements = new LinkedList<>();

		for (WebElement element: elements)
			if( element.isDisplayed())
				visibleElements.add(element);

		return visibleElements;
	}

	public void checkElementsVisibility(Class classObject){

		SoftAssert softAssert = new SoftAssert();
		WebElement element;

		for (Field field : classObject.getDeclaredFields()) {
			if (field.getType() == WebElement.class){
				try {
					element = (WebElement) field.get(classObject);
					if (!element.isDisplayed())
						softAssert.fail("Element isn't displayed - " + element.getAttribute("name"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		softAssert.assertAll();
	}

	public void doLogout(){
		logoutButton.click();
	}
}



