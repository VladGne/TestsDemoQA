package POM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class BasePage{

	static Properties prop = new Properties();
	public static int waiterTime = 10;
	public static final String DRIVER_PATH = "src\\test\\resources\\chromedriver.exe";
	protected Logger logger = LogManager.getLogger(this);

	// Read config.properties file
	static {
		InputStream input = null;
		try {
			input =new FileInputStream("src/test/resources/config.properties");
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

	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		final String BasePageURL = prop.getProperty("durl");
		driver.navigate().to(BasePageURL);
	}

	@FindBy(className = "logout")
	private WebElement logoutButton;

	public void doLogin(String email, String password, WebDriver driver){
		LoginPage loginPage = new LoginPage(driver);
		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
		loginPage.loginButtonClick();
	}

	public void doLogout(){
		logoutButton.click();
	}

	static void navigate(WebDriver driver, String Url){
		driver.navigate().to(Url);
		//return new BasePage(driver);
	}

	public void checkPageElementsVisibility(Class classObject, SoftAssert softAssert){

		//ToDo : why isDisplayed work not correct?

		for (Field field : classObject.getDeclaredFields()) {
			if (field.getType() == WebElement.class){
				try {
					field.setAccessible(true);
					WebElement element = (WebElement) field.get(this);
					if (!element.isEnabled())
						softAssert.fail("Element isn't enabled - " + element.getAttribute("name"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

static class AccountPage{

	@FindBy(className = "icon-building")
	private WebElement addressButton;

	@FindBy(className = "icon-user")
	private WebElement personalInfoButton;

	public AccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	void addressButtonClick(){
		addressButton.click();
	}

	void personalInfoButtonClick(){
		personalInfoButton.click();
	}
}

}




