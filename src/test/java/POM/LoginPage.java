package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage extends BasePage{
	
	// id
	public static final String SUBMIT_BUTTON_LOCATOR = "SubmitCreate"; 
	public static final String EMAIL_CREATION_TEXTBOX_LOCATOR = "email_create";
	public static final String REGISTRATION_FORM_LOCATOR = "email_create";
	public static final String MALE_BUTTON_LOCATOR = "id_gender1";
	public static final String FEMALE_BUTTON_LOCATOR = "id_gender2";
	public static final String CUSTOMER_FISTNAME_TEXTBOX_LOCATOR = "customer_firstname";
	public static final String CUSTOMER_LASTNAME_TEXTBOX_LOCATOR = "customer_lastname";
	public static final String EMAIL_TEXTBOX_LOCATOR = "email";
	public static final String PASSWORD_TEXTBOX_LOCATOR = "passwd";
	public static final String DAY_LOCATOR = "days";
	public static final String MONTH_LOCATOR = "months";
	public static final String YEAR_LOCATOR = "years";
	public static final String NEWSLETTER_LOCATOR = "newsletter";
	public static final String OFFERS_LOCATOR = "optin";
	
	public static final String FISTNAME_TEXTBOX_LOCATOR = "fistname";
	public static final String LASTNAME_TEXTBOX_LOCATOR = "lastname";
	public static final String COMPANY_TEXTBOX_LOCATOR = "company";
	public static final String ADDRESS_TEXTBOX_LOCATOR = "address1";
	public static final String ADDRESS2_TEXTBOX_LOCATOR = "address2";
	public static final String CITY_TEXTBOX_LOCATOR = "city";
	public static final String STATE_TEXTBOX_LOCATOR = "id_state";
	public static final String ZIPCODE_TEXTBOX_LOCATOR = "postcode";
	public static final String COUNTRY_TEXTBOX_LOCATOR = "id_country";
	public static final String ADDITIONAL_INFORMATION_TEXTBOX_LOCATOR = "other";
	public static final String HOME_PHONE_TEXTBOX_LOCATOR = "phone";
	public static final String MOBILE_PHONE_TEXTBOX_LOCATOR = "phone_mobile";
	public static final String ALIAS_TEXTBOX_LOCATOR = "alias";
	public static final String REGISTER_BUTTON_LOCATOR = "submitAccount";
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public static LoginPage open(WebDriver driver) {
		final String LoginPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
		driver.navigate().to(LoginPageURL);
		return new LoginPage(driver);
	}
	
	//Select item in dropdown list
	public void selectItem(By elementLocator, String text) {
		new Select(driver.findElement(elementLocator)).selectByValue(text);
	}

}
