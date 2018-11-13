package com.org.MavenTests;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Customizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Models.User;
import POM.BasePage;
import POM.LoginPage;

public class RegPageTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RegPageTest.class); 	
	private WebDriver driver;
				
		@BeforeSuite
		public void initionalBrowser() {
			System.setProperty("webdriver.gecko.driver", "D:\\_Programs\\geckodriver.exe");
			driver = new FirefoxDriver();
			logger.info("Test start!");
		}
					
		@AfterSuite
		public void closeBrowser() {
			//driver.close();
			driver.quit();
			logger.info("Test end!");
		}	
		
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
			final String password = longString;
					
			User[] validUserData = new User[1]; 
			validUserData[0] = new User();
			validUserData[0].setEmail(email);
			validUserData[0].setGender(gender);
			validUserData[0].setFistName(fistName);
			validUserData[0].setLastName(lastName);
			validUserData[0].setAddress(address);
			validUserData[0].setAddress2(address2);
			validUserData[0].setAdditionInformation(additionInformation);
			validUserData[0].setCountry(country);
			validUserData[0].setCity(city);
			validUserData[0].setState(state);
			validUserData[0].setZipCode(zipCode);
			validUserData[0].setCompany(company);
			validUserData[0].setHomePhone(homePhone);
			validUserData[0].setMobilePhone(mobilePhone);
			validUserData[0].setAddressAlias(addressAlias);
			validUserData[0].setPassword(password);
			validUserData[0].setDayBirth(dayBirth);
			validUserData[0].setMonthBirth(monthBirth);
			validUserData[0].setYearBirth(yearBirth);
			return validUserData;
		}
				
		// Test case 1 - User registration with valid data
		@Test (priority = 3, dataProvider="validUserData")
		public void checkUpperLimits(Object userData){
											
			User user = (User) userData;
					
			LoginPage loginPage = LoginPage.open(driver);
					
			try {			
				// Enter email to get access to registration page
				loginPage.writeText(By.id(LoginPage.EMAIL_CREATION_TEXTBOX_LOCATOR), (user.getEmail()));			
				loginPage.click(By.id(LoginPage.SUBMIT_BUTTON_LOCATOR));
			}
					
			catch(Exception e) {
				logger.error("Can't get access to registration page: " + e.toString());
				Assert.fail(e.toString());
			}
								
			try {
				// Wait for registration form
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.id(LoginPage.MALE_BUTTON_LOCATOR)));
			}
			catch (Exception e) {
				logger.error("Registration form loading error: " + e.toString());
				Assert.fail(e.toString());
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
				logger.error("User's data input error:" + e.toString());
				Assert.fail(e.toString());
			}	
					
			//loginPage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
		}					
		
		// Create invalid user
		@DataProvider (name = "repeatedUserEmail")
		public Object[] getRepeatedUserEmail(){
			final String repeatedEmail = "test1@test.com1";			
					
			User[] invalidUserData = new User[1]; 
			invalidUserData[0] = new User();
			invalidUserData[0].setEmail(repeatedEmail);
							
			return invalidUserData;
		}
				
		@Test (priority = 1, dataProvider="repeatedUserEmail")
		public void checkEmailRepeat(Object userData){
			User user = (User) userData;
			
			LoginPage loginPage = LoginPage.open(driver);
					
			try {			
				// Enter email to get access to registration page
				loginPage.writeText(By.id(LoginPage.EMAIL_CREATION_TEXTBOX_LOCATOR), (user.getEmail()));			
				loginPage.click(By.id(LoginPage.SUBMIT_BUTTON_LOCATOR));
				loginPage.readText(By.className(LoginPage.ALERTS_LOCATOR));
			}
					
			catch(Exception e) {
				logger.error("Email validation fail: " + e.toString());
				Assert.fail(e.toString());
			}
		}
		
		// Create invalid user
		@DataProvider (name = "invalidUserEmail")
		public Object[] getInvalidUserEmail(){
			final String invalidEmail = "invlalidMail";			
			
			User[] invalidUserData = new User[1]; 
			invalidUserData[0] = new User();
			invalidUserData[0].setEmail(invalidEmail);
					
			return invalidUserData;
		}
		
		@Test (priority = 2, dataProvider="invalidUserEmail")
		public void checkEmailValidations(Object userData){
			User user = (User) userData;
			
			LoginPage loginPage = LoginPage.open(driver);
			
			try {			
				// Enter email to get access to registration page
				loginPage.writeText(By.id(LoginPage.EMAIL_CREATION_TEXTBOX_LOCATOR), (user.getEmail()));			
				loginPage.click(By.id(LoginPage.SUBMIT_BUTTON_LOCATOR));
				loginPage.readText(By.className(LoginPage.ALERTS_LOCATOR));
				
				try {
					// Wait for registration form
					WebDriverWait wait = new WebDriverWait(driver, 10);
					WebElement element = wait.until((WebDriver d) -> d.findElement(By.className(LoginPage.ALERTS_LOCATOR)));
				}
				catch (Exception e) {
					logger.error("Registration form loading error: " + e.toString());
					Assert.fail(e.toString());
				}
			}
			
			catch(Exception e) {
				logger.error("Email validation fail: " + e.toString());
				Assert.fail(e.toString());
			}
		}
		
		
		// Create invalid user
		@DataProvider (name = "invalidUserData")
		public Object[] getInvalidUserData(){
			final String email = "test2@test.com2";
			final String fistName = "1";
			final String lastName = "2";
			final String company = "3";
			final String address = "4";
			final String address2 = "5"; 							// Additional address information
			final String city = "6";					
			final String zipCode = "1";
			final String homePhone = "1";
			final String mobilePhone = "1";
			final String addressAlias = "1";	
			final String password = "1";
			final String dayBirth = "31";
			final String monthBirth = "2";
			final String yearBirth = "2018";
					
			User[] invalidUserData = new User[1];					
			invalidUserData[0] = new User();
			invalidUserData[0].setEmail(email);
			invalidUserData[0].setFistName(fistName);
			invalidUserData[0].setLastName(lastName);
			invalidUserData[0].setAddress(address);
			invalidUserData[0].setAddress2(address2);
			invalidUserData[0].setCity(city);
			invalidUserData[0].setZipCode(zipCode);
			invalidUserData[0].setCompany(company);
			invalidUserData[0].setHomePhone(homePhone);
			invalidUserData[0].setMobilePhone(mobilePhone);
			invalidUserData[0].setAddressAlias(addressAlias);
			invalidUserData[0].setPassword(password);
			invalidUserData[0].setDayBirth(dayBirth);
			invalidUserData[0].setMonthBirth(monthBirth);
			invalidUserData[0].setYearBirth(yearBirth);
			return invalidUserData;
		}
				
		//Check date and phone number validation
		@Test (priority = 3, dataProvider="invalidUserData")
		public void checkValidations(Object userData){
			User user = (User) userData;
			
			LoginPage loginPage = LoginPage.open(driver);
			
			try {			
				// Enter email to get access to registration page
				loginPage.writeText(By.id(LoginPage.EMAIL_CREATION_TEXTBOX_LOCATOR), (user.getEmail()));			
				loginPage.click(By.id(LoginPage.SUBMIT_BUTTON_LOCATOR));
			}
			
			catch(Exception e) {
				logger.error("Can't get access to registration page: " + e.toString());
				Assert.fail(e.toString());
			}
			
			try {
				// Wait for registration form
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.id(LoginPage.MALE_BUTTON_LOCATOR)));
			}
			catch (Exception e) {
				logger.error("Registration form loading error: " + e.toString());
				Assert.fail(e.toString());
			}
			
			// Enter user data into registration form
						
			try {
							
				loginPage.writeText(By.id(LoginPage.CUSTOMER_FISTNAME_TEXTBOX_LOCATOR), (user.getFistName()));			// Enter fist name						
				loginPage.writeText(By.id(LoginPage.CUSTOMER_LASTNAME_TEXTBOX_LOCATOR), (user.getLastName()));			// Enter last name						
				//loginPage.writeText(By.id(LoginPage.EMAIL_TEXTBOX_LOCATOR), (user.getEmail()));						// email Already in text box							
				loginPage.writeText(By.id(LoginPage.PASSWORD_TEXTBOX_LOCATOR), (user.getPassword()));					// Enter password													
				
				// Enter Date of birth							
				loginPage.selectItem(By.id(LoginPage.DAY_LOCATOR), user.getDayBirth());										
				loginPage.selectItem(By.id(LoginPage.MONTH_LOCATOR), user.getMonthBirth());														
				loginPage.selectItem(By.id(LoginPage.YEAR_LOCATOR), user.getYearBirth());
				
				loginPage.writeText(By.id(LoginPage.ADDRESS_TEXTBOX_LOCATOR), (user.getAddress()));						// Enter address							
				loginPage.writeText(By.id(LoginPage.ADDRESS2_TEXTBOX_LOCATOR), (user.getAddress2()));					// Enter address addition information							
				loginPage.writeText(By.id(LoginPage.CITY_TEXTBOX_LOCATOR), (user.getCity()));							// Enter city													
				
				loginPage.writeText(By.id(LoginPage.ZIPCODE_TEXTBOX_LOCATOR), (user.getZipCode()));						// Enter Post code							
				loginPage.writeText(By.id(LoginPage.HOME_PHONE_TEXTBOX_LOCATOR), (user.getHomePhone()));				// Enter home phone							
				loginPage.writeText(By.id(LoginPage.MOBILE_PHONE_TEXTBOX_LOCATOR), (user.getMobilePhone()));			// Enter mobile phone							
				loginPage.writeText(By.id(LoginPage.ALIAS_TEXTBOX_LOCATOR), (user.getAddressAlias()));					// Enter alias
							
				loginPage.click(By.id(LoginPage.REGISTER_BUTTON_LOCATOR));
				
				loginPage.readText(By.className(LoginPage.ALERTS_LOCATOR));
				}
			
				catch(Exception e){			
					logger.error("User's data input error:" + e.toString());
					Assert.fail(e.toString());
				}					
		}	
		
		// Create valid user
		@DataProvider (name = "validUserData")
		public Object[] getValidUserData(){
			final String email = "test2@test.com2";								//test1@test.com1 - for login
			final String gender = "Male";
			final String fistName = "TestFistName";
			final String lastName = "TestLastName";
			final String company = "TestCompany";
			final String address = "TestAddress";
			final String address2 = "TestAddress2"; 							// Additional address information
			final String city = "Fortuna";
			final String state = User.state.California.toString();; 											// Dropdown List have only values, so it is should be California 
			final String country = "21";										// Dropdown List have only values, so it is should be USA
			final String zipCode = "95540";
			final String additionInformation = "TestAdditionalInformation";
			final String homePhone = "7077259990";
			final String mobilePhone = "9610000000";
			final String addressAlias = "TestAddressAlias";	
			final String password = "TestPassword1";
			final String dayBirth = "11";
			final String monthBirth = User.monthBirth.November.toString();
			final String yearBirth = "1991";
			
			User[] validUserData = new User[1]; 
			validUserData[0] = new User();
			validUserData[0].setEmail(email);
			validUserData[0].setGender(gender);
			validUserData[0].setFistName(fistName);
			validUserData[0].setLastName(lastName);
			validUserData[0].setAddress(address);
			validUserData[0].setAddress2(address2);
			validUserData[0].setAdditionInformation(additionInformation);
			validUserData[0].setCountry(country);
			validUserData[0].setCity(city);
			validUserData[0].setState(state);
			validUserData[0].setZipCode(zipCode);
			validUserData[0].setCompany(company);
			validUserData[0].setHomePhone(homePhone);
			validUserData[0].setMobilePhone(mobilePhone);
			validUserData[0].setAddressAlias(addressAlias);
			validUserData[0].setPassword(password);
			validUserData[0].setDayBirth(dayBirth);
			validUserData[0].setMonthBirth(monthBirth);
			validUserData[0].setYearBirth(yearBirth);
			return validUserData;
		}
		
		// Test case 1 - User registration with valid data
		@Test (priority = 4, dataProvider="validUserData")
		public void checkRegistration(Object userData){
											
			User user = (User) userData;
			
			LoginPage loginPage = LoginPage.open(driver);
			
			try {			
				// Enter email to get access to registration page
				loginPage.writeText(By.id(LoginPage.EMAIL_CREATION_TEXTBOX_LOCATOR), (user.getEmail()));			
				loginPage.click(By.id(LoginPage.SUBMIT_BUTTON_LOCATOR));
			}
			
			catch(Exception e) {
				logger.error("Can't get access to registration page: " + e.toString());
				Assert.fail(e.toString());
			}
						
			try {
				// Wait for registration form
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.id(LoginPage.MALE_BUTTON_LOCATOR)));
			}
			catch (Exception e) {
				logger.error("Registration form loading error: " + e.toString());
				Assert.fail(e.toString());
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
				loginPage.selectItem(By.id(LoginPage.MONTH_LOCATOR), user.getMonthBirth().valueOf(User.monthBirth.November));
				loginPage.selectItem(By.id(LoginPage.YEAR_LOCATOR), user.getYearBirth());
				
				// Optional check-boxes
				loginPage.click(By.id(LoginPage.NEWSLETTER_LOCATOR));
				loginPage.click(By.id(LoginPage.OFFERS_LOCATOR));
				
				loginPage.writeText(By.id(LoginPage.COMPANY_TEXTBOX_LOCATOR), (user.getCompany()));						// Enter company
				loginPage.writeText(By.id(LoginPage.ADDRESS_TEXTBOX_LOCATOR), (user.getAddress()));						// Enter address
				loginPage.writeText(By.id(LoginPage.ADDRESS2_TEXTBOX_LOCATOR), (user.getAddress2()));					// Enter address addition information
				loginPage.writeText(By.id(LoginPage.CITY_TEXTBOX_LOCATOR), (user.getCity()));							// Enter city
								
				loginPage.selectItem(By.id(LoginPage.COUNTRY_TEXTBOX_LOCATOR), user.getCountry());						// Choice Country
				
				if ("United states".equals(loginPage.readText(By.id(LoginPage.COUNTRY_TEXTBOX_LOCATOR))))
					loginPage.selectItem(By.id(LoginPage.STATE_TEXTBOX_LOCATOR), user.getState());							// Choice State
				
				loginPage.writeText(By.id(LoginPage.ZIPCODE_TEXTBOX_LOCATOR), (user.getZipCode()));									// Enter Post code
				loginPage.writeText(By.id(LoginPage.ADDITIONAL_INFORMATION_TEXTBOX_LOCATOR), (user.getAdditionInformation()));		// Enter addition information
				loginPage.writeText(By.id(LoginPage.HOME_PHONE_TEXTBOX_LOCATOR), (user.getHomePhone()));							// Enter home phone
				loginPage.writeText(By.id(LoginPage.MOBILE_PHONE_TEXTBOX_LOCATOR), (user.getMobilePhone()));						// Enter mobile phone
				loginPage.writeText(By.id(LoginPage.ALIAS_TEXTBOX_LOCATOR), (user.getAddressAlias()));								// Enter alias
				
				//loginPage.click(By.id(LoginPage.REGISTER_BUTTON_LOCATOR));
			}
			catch(Exception e){			
				logger.error("User's data input error:" + e.toString());
				Assert.fail(e.toString());
			}	
			
			//loginPage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
		}			
		
}
