package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage extends BasePage{
	
	// id
	public static final String LOGIN_BUTTON_LOCATOR = "SubmitLogin"; 
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
	
	public static final String FISTNAME_TEXTBOX_LOCATOR = "firstname";
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

	public static final String ERROR_MESSAGE_LOCATOR = "create_account_error";

	//Alert texts
	public static final String MAX_LAST_NAME_MESSAGE = "lastname is too long. Maximum length: 32";
	public static final String MAX_FIRST_NAME_MESSAGE = "firstname is too long. Maximum length: 32";
	public static final String MAX_PASSWORD_MESSAGE = "passwd is too long. Maximum length: 32";
	public static final String MAX_ALIAS_MESSAGE = "alias is too long. Maximum length: 32";
	public static final String MAX_ADDRESS1_MESSAGE = "address1 is too long. Maximum length: 128";
	public static final String MAX_ADDRESS2_MESSAGE = "address2 is too long. Maximum length: 128";
	public static final String MAX_POSTCODE_MESSAGE = "postcode is too long. Maximum length: 12";
	public static final String MAX_ADDITION_INFO_MESSAGE = "other is too long. Maximum length: 300";
	public static final String MAX_HOME_PHONE_MESSAGE = "phone is too long. Maximum length: 32";
	public static final String MAX_MOBILE_PHONE_MESSAGE = "phone_mobile is too long. Maximum length: 32";

	public static final String INVALID_EMAIL_MESSAGE = "Invalid email address.";
	public static final String INVALID_FIRST_NAME_MESSAGE = "firstname is invalid.";
	public static final String INVALID_LAST_NAME_MESSAGE = "lastname is invalid.";
	public static final String INVALID_PASSWORD_MESSAGE = "passwd is invalid.";
	public static final String INVALID_POSTCODE_MESSAGE = "The Zip/Postal code you've entered is invalid. It must follow this format: 00000";
	public static final String INVALID_STATE_MESSAGE = "This country requires you to choose a State";
	public static final String INVALID_DATE_MESSAGE = "Invalid date of birth";
	public static final String INVALID_COUNTRY_MESSAGE = "Country is invalid";



	//Classes
	public static final String ALERTS_LOCATOR = "alert-danger";
	
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


	public void selectItem(By elementLocator, int index) {
		new Select(driver.findElement(elementLocator)).selectByIndex(index);
	}

}
