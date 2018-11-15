package com.org.MavenTests;

import static POM.ProfilePage.DAY_LOCATOR;
import static POM.ProfilePage.MONTH_LOCATOR;
import static POM.ProfilePage.YEAR_LOCATOR;
import static org.testng.Assert.expectThrows;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

public class UserProfileTests {
	
	private static final Logger logger = Logger.getLogger(RegPageTest.class); //LoggerFactory.getLogger(RegPageTest.class);
	public WebDriver driver;

	String fistName;
	String lastName;
	WebElement chosenMonth;
	WebElement chosenDay;
	WebElement chosenYear;
	SoftAssert softAssertion= new SoftAssert();
				
		@BeforeSuite
		public void initionalBrowser() {
			System.setProperty("webdriver.gecko.driver", BasePage.DRIVER_PATH);
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
			User[] validUserData = new User[1]; 
			validUserData[0] = new User();
			validUserData[0].setEmail(email);
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
				softAssertion.fail("Login error: " + e.toString());
				logger.error("Can't login: " + e.toString());
			}

			try {
				// Wait for profile form
				WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.className(ProfilePage.ADDRESSES_BUTTON_LOCATOR)));
				
				profilePage.click(By.className(ProfilePage.ADDRESSES_BUTTON_LOCATOR));
			}
			catch(Exception e) {
				softAssertion.fail("Load form error: " + e.toString());
				logger.error("Profile form load error: " + e.toString());
			}

			// Check names
			try{
				List<WebElement> names = driver.findElements(By.xpath("//span[@class='address_name']"));
				fistName = names.get(0).getText();
				lastName = names.get(1).getText();
			}
			catch(Exception e) {
				softAssertion.fail("Names list load error: " + e.toString());
				logger.error("Names list load error: " + e.toString());
			}

			if(!fistName.equals(user.getFistName())) {
				logger.error("first name do not match");
				softAssertion.fail("first name do not match");
			}

			if(!lastName.equals(user.getLastName())) {
				logger.error("last name do not match");
				softAssertion.fail("last name do not match");
			}
			
			if(!profilePage.readText(By.className(ProfilePage.COMPANY_LOCATOR)).equals(user.getCompany())) {
				logger.error("company do not match");
				softAssertion.fail("company do not match");
			}
			
			if(!profilePage.readText(By.className(ProfilePage.ADDRESS1_LOCATOR)).equals(user.getAddress())) {
				logger.error("address1 do not match");
				softAssertion.fail("address1 do not match");
			}
			
			if(!profilePage.readText(By.className(ProfilePage.ADDRESS2_LOCATOR)).equals(user.getAddress2())) {
				logger.error("address2 do not match");
				softAssertion.fail("address2 do not match");
			}
			
			if(!profilePage.readText(By.className(ProfilePage.HOME_PHONE_LOCATOR)).equals(user.getHomePhone())) {
				logger.error("home phone do not match");
				softAssertion.fail("home phone do not match");
			}
			
			if(!profilePage.readText(By.className(ProfilePage.MOBILE_PHONE_LOCATOR)).equals(user.getMobilePhone())) {
				logger.error("mobile phone do not match");
				softAssertion.fail("mobile phone do not match");
			}
			
			profilePage.click(By.className(BasePage.LOGOUT_BUTTON_LOCATOR));
			
			try {
				// Wait for profile form
				WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.id(LoginPage.EMAIL_TEXTBOX_LOCATOR)));
			}
			catch(Exception e) {
				softAssertion.fail("Login form load error: " + e.toString());
				logger.error("Login form load error: " + e.toString());
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
				softAssertion.fail("Can't login: " + e.toString());
				logger.error("Can't login: " + e.toString());
			}
			
			try {
				// Wait for profile form
				WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
				WebElement element = wait.until((WebDriver d) -> d.findElement(By.className(ProfilePage.ADDRESSES_BUTTON_LOCATOR)));
				
				profilePage.click(By.xpath(ProfilePage.USER_BUTTON_LOCATOR));
			}
			catch(Exception e) {
				softAssertion.fail("Login form load error: " + e.toString());
				logger.error("Login form load error: " + e.toString());
			}
			
			if(!profilePage.checkSelection(By.id(ProfilePage.MALE_BUTTON_LOCATOR))) {
				logger.error("gender do not match");
				softAssertion.fail("gender do not match" );
			}
			
			//String txt = profilePage.readText(By.id(ProfilePage.FISTNAME_TEXTBOX_LOCATOR));
			if(!profilePage.getValue(By.id(ProfilePage.FISTNAME_TEXTBOX_LOCATOR)).equals(user.getFistName())) { // Doesn't read name
				logger.error("fist name do not match");
				softAssertion.fail("fist name do not match" );
			}
			
			if(!profilePage.getValue(By.id(ProfilePage.LASTNAME_TEXTBOX_LOCATOR)).equals(user.getLastName())) {
				logger.error("last name do not match");
				softAssertion.fail("last name do not match");
			}
			
			if(!profilePage.getValue(By.id(ProfilePage.EMAIL_TEXTBOX_LOCATOR)).equals(user.getEmail())) {
				logger.error("email do not match");
				softAssertion.fail("email do not match");
			}

			//new Select(driver.findElement(By.id(DAY_LOCATOR))).getAllSelectedOptions();

			//Check day
			try{
				List<WebElement> days = new Select(driver.findElement(By.id(DAY_LOCATOR))).getAllSelectedOptions();
				for (WebElement day : days)
					if (day.isSelected())
						chosenDay = day;
			}
			catch(Exception e) {
				softAssertion.fail("Days list load error: " + e.toString());
				logger.error("Days list load error: " + e.toString());
			}

			String[] day = chosenDay.getText().split(" ");
			if(!day[0].equals(user.getDayBirth())) {
				logger.error("day of birth do not match");
				softAssertion.fail("day of birth do not match" );
			}

			//Check month
			try{
				List<WebElement> months = new Select(driver.findElement(By.id(MONTH_LOCATOR))).getAllSelectedOptions();
				for (WebElement month : months)
					if (month.isSelected())
						chosenMonth = month;
			}
			catch(Exception e) {
				softAssertion.fail("Month list load error: " + e.toString());
				logger.error("Month list load error: " + e.toString());
			}

			String[] month = chosenMonth.getText().split(" ");
			if(!month[0].equals(user.getMonthBirth().toString())) {
				logger.error("Year of birth do not match");
				softAssertion.fail("Year of birth do not match");
			}

			//Check year
			try{
				List<WebElement> years = new Select(driver.findElement(By.id(YEAR_LOCATOR))).getAllSelectedOptions();
				for (WebElement year : years)
					if (year.isSelected())
						chosenYear = year;
			}
			catch(Exception e) {
				softAssertion.fail("Month list load error: " + e.toString());
				logger.error("Month list load error: " + e.toString());
			}

			String[] year = chosenYear.getText().split(" ");
			if(!year.equals(user.getYearBirth())) {
				logger.error("Year of birth do not match");
				softAssertion.fail("Year of birth do not match");
			}


			if (!driver.findElement(By.id(LoginPage.NEWSLETTER_LOCATOR)).isSelected()){
				logger.error("Newsletter checkbox is'nt selected");
				softAssertion.fail("Newsletter checkbox is'nt selected");
			}

			if (!driver.findElement(By.id(LoginPage.OFFERS_LOCATOR)).isSelected()){
				logger.error("Newsletter checkbox is'nt selected");
				softAssertion.fail("Newsletter checkbox is'nt selected");
			}

			softAssertion.assertAll();
			//profilePage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
		}
}
