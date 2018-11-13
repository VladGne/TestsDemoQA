package com.org.MavenTests;

import static org.testng.Assert.expectThrows;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Models.User;
import POM.BasePage;
import POM.LoginPage;
import POM.ProfilePage;

public class UserProfileTests {
	
	private static final Logger logger = LoggerFactory.getLogger(RegPageTest.class); 	
	public WebDriver driver;
				
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
		
		@DataProvider (name = "validUserData")
		public Object[] getValidUserData(){
			final String email = "test1@test.com1";								//test1@test.com1 - for login
			final String gender = "Male";
			final String fistName = "TestFistName";
			final String lastName = "TestLastName";
			final String company = "TestCompany";
			final String address = "TestAddress";
			final String address2 = "TestAddress2"; 							// Additional address information
			final String city = "Fortuna";
			final String state = User.state.California.toString(); 			    // Dropdown List have only values, so it is should be California 
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
		
		@Test (priority = 1, dataProvider="validUserData")
		public void checkAddresses(Object userData) throws Exception{
			User user = (User) userData;
			
			ProfilePage profilePage = ProfilePage.open(driver); // open login page
			
			try {			
				// Login
				profilePage.writeText(By.id(LoginPage.EMAIL_TEXTBOX_LOCATOR), (user.getEmail()));			
				profilePage.writeText(By.id(LoginPage.PASSWORD_TEXTBOX_LOCATOR), (user.getPassword()));	
				profilePage.click(By.id(LoginPage.LOGIN_BUTTON_LOCATOR));
			}
			
			catch(Exception e) {
				Assert.fail(e.toString());
				logger.error("Can't login: " + e.toString());
			}
			
			try {
				// Wait for profile form
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.className(ProfilePage.ADDRESSES_BUTTON_LOCATOR)));
				
				profilePage.click(By.className(ProfilePage.ADDRESSES_BUTTON_LOCATOR));
			}
			catch(Exception e) {
				Assert.fail(e.toString());
				logger.error("Profile form load error: " + e.toString());
			}
					
			if(!profilePage.readText(By.className(ProfilePage.NAME_LOCATOR)).equals(user.getFistName())) {
				logger.error("names do not match");
				Assert.fail("names do not match");
				throw new Exception("names do not match");				
			}
			
			// TODO: Check last name
			//WebElement elem = driver.findElement(By.xpath("//li[@class='address_name']/li"));
			//driver.findElements(By.className("address_name"));
			//ArrayList<WebElement> elements =  new ArrayList<WebElement>();
			
			
			
			if(!profilePage.readText(By.className(ProfilePage.COMPANY_LOCATOR)).equals(user.getCompany())) {
				logger.error("company do not match");
				Assert.fail("company do not match");
				throw new Exception("company do not match");				
			}
			
			if(!profilePage.readText(By.className(ProfilePage.ADDRESS1_LOCATOR)).equals(user.getAddress())) {
				logger.error("address1 do not match");
				Assert.fail("address1 do not match");
				throw new Exception("address1 do not match");				
			}
			
			if(!profilePage.readText(By.className(ProfilePage.ADDRESS2_LOCATOR)).equals(user.getAddress2())) {
				logger.error("address2 do not match");
				Assert.fail("address2 do not match");
				throw new Exception("address2 do not match");				
			}
			
			if(!profilePage.readText(By.className(ProfilePage.HOME_PHONE_LOCATOR)).equals(user.getHomePhone())) {
				logger.error("home phone do not match");
				Assert.fail("home phone do not match");
				throw new Exception("home phone do not match");				
			}
			
			if(!profilePage.readText(By.className(ProfilePage.MOBILE_PHONE_LOCATOR)).equals(user.getMobilePhone())) {
				logger.error("mobile phone do not match");
				Assert.fail("mobile phone do not match");
				throw new Exception("mobile phone do not match");				
			}
			
			profilePage.click(By.className(BasePage.LOGOUT_BUTTON_LOCATOR));
			
			try {
				// Wait for profile form
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.id(LoginPage.EMAIL_TEXTBOX_LOCATOR)));
			}
			catch(Exception e) {
				Assert.fail(e.toString());
				logger.error("Lpgin form load error: " + e.toString());
			}
			
		}
		
		@Test (priority = 2, dataProvider="validUserData")
		public void checkPrsonalInfo(Object userData) throws Exception{
			User user = (User) userData;
					
			ProfilePage profilePage = ProfilePage.open(driver); // open login page
			
			try {			
				// Login
				profilePage.writeText(By.id(LoginPage.EMAIL_TEXTBOX_LOCATOR), (user.getEmail()));			
				profilePage.writeText(By.id(LoginPage.PASSWORD_TEXTBOX_LOCATOR), (user.getPassword()));	
				profilePage.click(By.id(LoginPage.LOGIN_BUTTON_LOCATOR));
			}
			
			catch(Exception e) {
				Assert.fail(e.toString());
				logger.error("Can't login: " + e.toString());
			}
			
			try {
				// Wait for profile form
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.className(ProfilePage.ADDRESSES_BUTTON_LOCATOR)));
				
				profilePage.click(By.className(ProfilePage.USER_BUTTON_LOCATOR));
			}
			catch(Exception e) {
				Assert.fail(e.toString());
				logger.error("Login form load error: " + e.toString());
			}
			
			if(!profilePage.checkSelection(By.id(ProfilePage.MALE_BUTTON_LOCATOR))) {
				logger.error("gender do not match");
				Assert.fail("gender do not match");
				throw new Exception("gender do not match");				
			}
			
			//String txt = profilePage.readText(By.id(ProfilePage.FISTNAME_TEXTBOX_LOCATOR));
			if(!profilePage.readText(By.id(ProfilePage.FISTNAME_TEXTBOX_LOCATOR)).equals(user.getFistName())) { // Doesn't read name
				logger.error("fist name do not match");
				Assert.fail("fist name do not match");
				throw new Exception("fist name do not match");				
			}
			
			if(!profilePage.readText(By.id(ProfilePage.LASTNAME_TEXTBOX_LOCATOR)).equals(user.getLastName())) {
				logger.error("last name do not match");
				Assert.fail("last name do not match");
				throw new Exception("last name do not match");				
			}
			
			if(!profilePage.readText(By.id(ProfilePage.EMAIL_TEXTBOX_LOCATOR)).equals(user.getEmail())) {
				logger.error("email do not match");
				Assert.fail("email do not match");
				throw new Exception("email do not match");				
			}
			
			if(!profilePage.readText(By.id(ProfilePage.DAY_LOCATOR)).equals(user.getDayBirth())) {
				logger.error("day of birth do not match");
				Assert.fail("day of birth do not match");
				throw new Exception("day of birth  do not match");				
			}
			
			//TODO: Check month
			
			if(!profilePage.readText(By.id(ProfilePage.YEAR_LOCATOR)).equals(user.getYearBirth())) {
				logger.error("Year of birth do not match");
				Assert.fail("Year of birth do not match");
				throw new Exception("Year of birth  do not match");				
			}
			
			//profilePage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
		}
}
