package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage{
	
	// Classes
	public static final String ADDRESSES_BUTTON_LOCATOR = "icon-building"; 
	public static final String USER_BUTTON_LOCATOR = "//span[contains(text(),'My personal information')]";
	public static final String NAME_LOCATOR = "address_name"; 
	public static final String COMPANY_LOCATOR = "address_company"; 
	public static final String ADDRESS1_LOCATOR = "address_address1";
	public static final String ADDRESS2_LOCATOR = "address_address2"; 
	public static final String HOME_PHONE_LOCATOR = "address_phone"; 
	public static final String MOBILE_PHONE_LOCATOR = "address_phone_mobile"; 
	
	//id
	public static final String MALE_BUTTON_LOCATOR = "id_gender1";
	public static final String FEMALE_BUTTON_LOCATOR = "id_gender2";
	public static final String EMAIL_TEXTBOX_LOCATOR = "email";
	public static final String FISTNAME_TEXTBOX_LOCATOR = "firstname";
	public static final String LASTNAME_TEXTBOX_LOCATOR = "lastname";
	public static final String DAY_LOCATOR = "days";
	public static final String MONTH_LOCATOR = "daysList";
	public static final String YEAR_LOCATOR = "years";
	public static final String NEWSLETTER_LOCATOR = "newsletter";
	public static final String OFFERS_LOCATOR = "optin";
	
	public ProfilePage(WebDriver driver) {
		super(driver);
	}
	

	
	public boolean checkSelection(By elementLocator) {
		return driver.findElement(elementLocator).isSelected();
	}
}
