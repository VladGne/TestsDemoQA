package framework.pageObjectModels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Field;
import java.util.Properties;

import static framework.helperClasses.FileReader.readProperties;

public class BasePage{

	private static Properties prop = readProperties();
	static int waiterTime = 10;
	public static final String DRIVER_PATH = "src\\test\\resources\\chromedriver.exe";
	Logger logger = LogManager.getLogger(this);

	@FindBy(className = "logout")
	private WebElement logoutButton;

	@FindBy(xpath = "alert-danger") //"//div[@class='alert alert-danger']//ol"
	private WebElement alertList;

	BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		final String BasePageURL = prop.getProperty("durl");
		driver.navigate().to(BasePageURL);
	}

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

	public String[] getAlertList(){
		return alertList.getText().split("\n");
	}

	static class AccountPage{

		@FindBy(className = "icon-building")
		private WebElement addressButton;

		@FindBy(className = "icon-user")
		private WebElement personalInfoButton;

		AccountPage(WebDriver driver) {
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