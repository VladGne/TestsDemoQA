package com.org.MavenTests;


import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Models.User;
import POM.BasePage;
import POM.LoginPage;
import org.testng.log4testng.Logger;

public class RegPageTest extends TestBase{

	private Logger logger =  Logger.getLogger(RegPageTest.class);

		// Create invalid user
		@DataProvider (name = "upperUserData")
		public Object[] getUpperUserData(){
			String longString ="1";
			for (int i=0; i<10000; i++)
				longString += "1";
			
			final String email = "Mustbeverylargetestusermailforthistextbox@gamil.com";								//test1@test.com1 - for login
			final String fistName = "Mustbeverylargetestfistnameforthistextbox";
			final String lastName = "Mustbeverylargetestlastnameforthistextbox";
			final String company = "Mustbeverylargetestlastnameforthistextbox";
			final String address = longString;
			final String address2 = longString; 							// Additional address information
			final String city = "Mustbeverylargetestlastnameforthistextbox";
			final String zipCode = "88888888888888888888888888888888888";
			final String additionInformation = longString;
			final String homePhone = "88888888888888888888888888888888888";
			final String mobilePhone = "88888888888888888888888888888888888";
			final String addressAlias = longString;	
			final String password = "MustBeVeryLargeTestPasswordForThisTextbox1";;
					
			User[] validUserData = new User[1]; 
			validUserData[0] = new User(longString);
			validUserData[0].setEmail(email);
			validUserData[0].setFistName(fistName);
			validUserData[0].setLastName(lastName);
			validUserData[0].setAddress(address);
			validUserData[0].setAddress2(address2);
			validUserData[0].setAdditionInformation(additionInformation);
			validUserData[0].setCity(city);
			validUserData[0].setZipCode(zipCode);
			validUserData[0].setCompany(company);
			validUserData[0].setHomePhone(homePhone);
			validUserData[0].setMobilePhone(mobilePhone);
			validUserData[0].setAddressAlias(addressAlias);
			validUserData[0].setPassword(password);
			return validUserData;
		}
				
		// Test case 1 - User registration with valid data
		@Test (priority = 3, dataProvider="upperUserData")
		public void checkUpperLimits(User userData){
											
			User user = userData;
					
			LoginPage loginPage = LoginPage.open(driver);
					
			try {			
				// Enter email to get access to registration page
				loginPage.writeText(By.id(LoginPage.EMAIL_CREATION_TEXTBOX_LOCATOR), (user.getEmail()));			
				loginPage.click(By.id(LoginPage.SUBMIT_BUTTON_LOCATOR));
			}
					
			catch(Exception e) {
				logger.error("Can't get access to registration page: " + e.toString());
				softAssertion.fail("Can't get access to registration page: " + e.toString());
			}
								
			try {
				// Wait for registration form
				WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.id(LoginPage.MALE_BUTTON_LOCATOR)));
			}
			catch (Exception e) {
				logger.error("Registration form loading error: " + e.toString());
				softAssertion.fail("Registration form loading error: " + e.toString());
			}

			// Enter user data into registration form
			try {
				loginPage.writeText(By.id(LoginPage.CUSTOMER_FISTNAME_TEXTBOX_LOCATOR), (user.getFistName()));			// Enter fist name
				loginPage.writeText(By.id(LoginPage.CUSTOMER_LASTNAME_TEXTBOX_LOCATOR), (user.getLastName()));			// Enter last name
				//loginPage.writeText(By.id(LoginPage.EMAIL_TEXTBOX_LOCATOR), (user.getEmail()));						// email Already in text box
				loginPage.writeText(By.id(LoginPage.PASSWORD_TEXTBOX_LOCATOR), (user.getPassword()));					// Enter password
						
				loginPage.writeText(By.id(LoginPage.COMPANY_TEXTBOX_LOCATOR), (user.getCompany()));						// Enter company
				loginPage.writeText(By.id(LoginPage.ADDRESS_TEXTBOX_LOCATOR), (user.getAddress()));						// Enter address
				loginPage.writeText(By.id(LoginPage.ADDRESS2_TEXTBOX_LOCATOR), (user.getAddress2()));					// Enter address addition information
				loginPage.writeText(By.id(LoginPage.CITY_TEXTBOX_LOCATOR), (user.getCity()));							// Enter city
						
				loginPage.writeText(By.id(LoginPage.ZIPCODE_TEXTBOX_LOCATOR), (user.getZipCode()));									// Enter Post code
				loginPage.writeText(By.id(LoginPage.ADDITIONAL_INFORMATION_TEXTBOX_LOCATOR), (user.getAdditionInformation()));		// Enter addition information
				loginPage.writeText(By.id(LoginPage.HOME_PHONE_TEXTBOX_LOCATOR), (user.getHomePhone()));							// Enter home phone
				loginPage.writeText(By.id(LoginPage.MOBILE_PHONE_TEXTBOX_LOCATOR), (user.getMobilePhone()));						// Enter mobile phone
				loginPage.writeText(By.id(LoginPage.ALIAS_TEXTBOX_LOCATOR), (user.getAddressAlias()));								// Enter alias
						
				loginPage.click(By.id(LoginPage.REGISTER_BUTTON_LOCATOR));
				
				loginPage.readText(By.className(LoginPage.ALERTS_LOCATOR));
			}
			catch(Exception e){			
				logger.error("User's data input error: " + e.toString());
				softAssertion.fail("User's data input error: " + e.toString());
			}

			List<WebElement> alertMessages = driver.findElements(By.xpath("//div[@class='alert alert-danger']//ol"));

			String fn = alertMessages.get(1).getText();
			if(alertMessages.get(0).getText().equals(LoginPage.MAX_LAST_NAME_MESSAGE))
				softAssertion.fail("Last name alert message error: ");
			if(alertMessages.get(1).getText().equals(LoginPage.MAX_FIRST_NAME_MESSAGE))
				softAssertion.fail("First name alert message error: ");
			if(alertMessages.get(2).getText().equals(LoginPage.MAX_PASSWORD_MESSAGE))
				softAssertion.fail("Password alert message error: ");
			if(alertMessages.get(3).getText().equals(LoginPage.MAX_ALIAS_MESSAGE))
				softAssertion.fail("Alias alert message error: ");
			if(alertMessages.get(4).getText().equals(LoginPage.MAX_ADDRESS1_MESSAGE))
				softAssertion.fail("Address1 alert message error: ");
			if(alertMessages.get(5).getText().equals(LoginPage.MAX_ADDRESS2_MESSAGE))
				softAssertion.fail("Address2 alert message error: ");
			if(alertMessages.get(5).getText().equals(LoginPage.MAX_POSTCODE_MESSAGE))
				softAssertion.fail("Postcode alert message error: ");
			if(alertMessages.get(5).getText().equals(LoginPage.MAX_ADDITION_INFO_MESSAGE))
				softAssertion.fail("Addition info alert message error: ");
			if(alertMessages.get(5).getText().equals(LoginPage.MAX_HOME_PHONE_MESSAGE))
				softAssertion.fail("Home phone info alert message error: ");
			if(alertMessages.get(5).getText().equals(LoginPage.MAX_MOBILE_PHONE_MESSAGE))
				softAssertion.fail("Mobile phone info alert message error: ");

			softAssertion.assertAll();
			//loginPage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
		}					
		

		

		
		// Create invalid user
		@DataProvider (name = "invalidUserData")
		public Object[] getInvalidUserData(){
			final String email = "test2@test.com2";
			final String invalidValue = "1";

			User[] invalidUserData = new User[1];					
			invalidUserData[0] = new User(invalidValue);
			invalidUserData[0].setEmail(email);
			return invalidUserData;
		}
				
		//Check date and phone number validation
		@Test (priority = 3, dataProvider="invalidUserData")
		public void checkValidations(User userData){
			User user = userData;
			
			LoginPage loginPage = LoginPage.open(driver);
			
			try {			
				// Enter email to get access to registration page
				loginPage.writeText(By.id(LoginPage.EMAIL_CREATION_TEXTBOX_LOCATOR), (user.getEmail()));			
				loginPage.click(By.id(LoginPage.SUBMIT_BUTTON_LOCATOR));
			}
			
			catch(Exception e) {
				logger.error("Can't get access to registration page: " + e.toString());
				softAssertion.fail("Can't get access to registration page: " + e.toString());
			}
			
			try {
				// Wait for registration form
				WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.id(LoginPage.MALE_BUTTON_LOCATOR)));
			}
			catch (Exception e) {
				logger.error("Registration form loading error: " + e.toString());
				softAssertion.fail("Registration form loading error: " + e.toString());
			}
			
			// Enter user data into registration form
						
			try{
							
				loginPage.writeText(By.id(LoginPage.CUSTOMER_FISTNAME_TEXTBOX_LOCATOR), (user.getFistName()));			// Enter fist name
				loginPage.writeText(By.id(LoginPage.CUSTOMER_LASTNAME_TEXTBOX_LOCATOR), (user.getLastName()));			// Enter last name
				//loginPage.writeText(By.id(LoginPage.EMAIL_TEXTBOX_LOCATOR), (user.getEmail()));						// email Already in text box
				loginPage.writeText(By.id(LoginPage.PASSWORD_TEXTBOX_LOCATOR), (user.getPassword()));					// Enter password

				// Enter Date of birth
				loginPage.selectItem(By.id(LoginPage.DAY_LOCATOR), user.getDayBirth());
				loginPage.selectItem(By.id(LoginPage.MONTH_LOCATOR), user.getMonthBirth().getValue());
				loginPage.selectItem(By.id(LoginPage.YEAR_LOCATOR), user.getYearBirth());

				loginPage.writeText(By.id(LoginPage.ADDRESS_TEXTBOX_LOCATOR), (user.getAddress()));						// Enter address
				loginPage.writeText(By.id(LoginPage.ADDRESS2_TEXTBOX_LOCATOR), (user.getAddress2()));					// Enter address addition information
				loginPage.writeText(By.id(LoginPage.CITY_TEXTBOX_LOCATOR), (user.getCity()));							// Enter city

				loginPage.writeText(By.id(LoginPage.ZIPCODE_TEXTBOX_LOCATOR), (user.getZipCode()));						// Enter Post code
				loginPage.writeText(By.id(LoginPage.HOME_PHONE_TEXTBOX_LOCATOR), (user.getHomePhone()));				// Enter home phone
				loginPage.writeText(By.id(LoginPage.MOBILE_PHONE_TEXTBOX_LOCATOR), (user.getMobilePhone()));			// Enter mobile phone
				loginPage.writeText(By.id(LoginPage.ALIAS_TEXTBOX_LOCATOR), (user.getAddressAlias()));					// Enter alias

				loginPage.click(By.id(LoginPage.REGISTER_BUTTON_LOCATOR));
				}
			
				catch(Exception e){
					logger.error("User's data input error: " + e.toString());
					softAssertion.fail("User's data input error: " + e.toString());
				}

				try {
					// Wait for registration form
					WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
					WebElement element = wait.until((WebDriver d) -> d.findElement(By.className(LoginPage.ALERTS_LOCATOR)));
				}
				catch (Exception e) {
					logger.error("Alert message loading error: " + e.toString());
					softAssertion.fail("Alert message loading error: " + e.toString());
				}

				List<WebElement> alertMessages = driver.findElements(By.xpath("//div[@class='alert alert-danger']//ol"));
				if(alertMessages.get(0).getText().equals(LoginPage.INVALID_LAST_NAME_MESSAGE))
					softAssertion.fail("Last name alert message error: ");
				if(alertMessages.get(1).getText().equals(LoginPage.INVALID_FIRST_NAME_MESSAGE))
					softAssertion.fail("First name alert message error: ");
				if(alertMessages.get(2).getText().equals(LoginPage.INVALID_PASSWORD_MESSAGE))
				softAssertion.fail("Password alert message error: ");
				if(alertMessages.get(3).getText().equals(LoginPage.INVALID_POSTCODE_MESSAGE))
					softAssertion.fail("Postcode alert message error: ");
				if(alertMessages.get(4).getText().equals(LoginPage.INVALID_STATE_MESSAGE))
					softAssertion.fail("State alert message error: ");
				if(alertMessages.get(5).getText().equals(LoginPage.INVALID_DATE_MESSAGE))
					softAssertion.fail("Date alert message error: ");

				softAssertion.assertAll();
		}	
		
		// Create valid user
		@DataProvider (name = "validUserData")
		public Object[] getValidUserData(){
			User[] validUserData = new User[1];
			validUserData[0] = new User();
			return validUserData;
		}
		
		// Test case 1 - User registration with valid data
		@Test (priority = 4, dataProvider="validUserData")
		public void checkRegistration(User userData){
											
			User user = userData;
			
			LoginPage loginPage = LoginPage.open(driver);
			
			try {			
				// Enter email to get access to registration page
				loginPage.writeText(By.id(LoginPage.EMAIL_CREATION_TEXTBOX_LOCATOR), (user.getEmail()));			
				loginPage.click(By.id(LoginPage.SUBMIT_BUTTON_LOCATOR));
			}
			
			catch(Exception e) {
				logger.error("Can't get access to registration page: " + e.toString());
				softAssertion.fail("Can't get access to registration page: " + e.toString());
			}
						
			try {
				// Wait for registration form
				WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.id(LoginPage.MALE_BUTTON_LOCATOR)));
			}
			catch (Exception e) {
				logger.error("Registration form loading error: " + e.toString());
				softAssertion.fail("Registration form loading error: " + e.toString());
			}
			
						
			// Enter user data into registration form
			try {
				loginPage.click(By.id(LoginPage.MALE_BUTTON_LOCATOR));													// Choice gender
				loginPage.writeText(By.id(LoginPage.CUSTOMER_FISTNAME_TEXTBOX_LOCATOR), (user.getFistName()));			// Enter fist name
				loginPage.writeText(By.id(LoginPage.CUSTOMER_LASTNAME_TEXTBOX_LOCATOR), (user.getLastName()));			// Enter last name
				//loginPage.writeText(By.id(LoginPage.EMAIL_TEXTBOX_LOCATOR), (user.getEmail()));						// email Already in text box
				loginPage.writeText(By.id(LoginPage.PASSWORD_TEXTBOX_LOCATOR), (user.getPassword()));					// Enter password
				
				// Enter Date of birth
				loginPage.selectItem(By.id(LoginPage.DAY_LOCATOR), user.getDayBirth());			
				loginPage.selectItem(By.id(LoginPage.MONTH_LOCATOR), user.getMonthBirth().getValue());
				loginPage.selectItem(By.id(LoginPage.YEAR_LOCATOR), user.getYearBirth());
				
				// Optional check-boxes
				loginPage.click(By.id(LoginPage.NEWSLETTER_LOCATOR));
				loginPage.click(By.id(LoginPage.OFFERS_LOCATOR));
				
				loginPage.writeText(By.id(LoginPage.COMPANY_TEXTBOX_LOCATOR), (user.getCompany()));						// Enter company
				loginPage.writeText(By.id(LoginPage.ADDRESS_TEXTBOX_LOCATOR), (user.getAddress()));						// Enter address
				loginPage.writeText(By.id(LoginPage.ADDRESS2_TEXTBOX_LOCATOR), (user.getAddress2()));					// Enter address addition information
				loginPage.writeText(By.id(LoginPage.CITY_TEXTBOX_LOCATOR), (user.getCity()));							// Enter city
								
				loginPage.selectItem(By.id(LoginPage.COUNTRY_TEXTBOX_LOCATOR), user.getCountry().getValue());						// Choice Country



				if ("United states".equals(loginPage.readText(By.id(LoginPage.COUNTRY_TEXTBOX_LOCATOR))))
					//loginPage.selectItem(By.id(LoginPage.STATE_TEXTBOX_LOCATOR), user.getState().getValue());							// Choice State
					loginPage.selectItem(By.id(LoginPage.STATE_TEXTBOX_LOCATOR), user.getState().getValue());

				loginPage.writeText(By.id(LoginPage.ZIPCODE_TEXTBOX_LOCATOR), (user.getZipCode()));									// Enter Post code
				loginPage.writeText(By.id(LoginPage.ADDITIONAL_INFORMATION_TEXTBOX_LOCATOR), (user.getAdditionInformation()));		// Enter addition information
				loginPage.writeText(By.id(LoginPage.HOME_PHONE_TEXTBOX_LOCATOR), (user.getHomePhone()));							// Enter home phone
				loginPage.writeText(By.id(LoginPage.MOBILE_PHONE_TEXTBOX_LOCATOR), (user.getMobilePhone()));						// Enter mobile phone
				loginPage.writeText(By.id(LoginPage.ALIAS_TEXTBOX_LOCATOR), (user.getAddressAlias()));								// Enter alias
				
				//loginPage.click(By.id(LoginPage.REGISTER_BUTTON_LOCATOR));
			}
			catch(Exception e){			
				logger.error("User's data input error: " + e.toString());
				softAssertion.fail("User's data input error: " + e.toString());
			}	

			softAssertion.assertAll();
			//loginPage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
		}			
		
}
