package com.org.MavenTests;

import POM.BasePage;
import POM.RegistrationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import Models.User;

public class RegPageTest extends TestBase{

	private Logger logger = LogManager.getLogger(RegPageTest.class);

		// Create invalid user
		@DataProvider (name = "upperUserData")
		public Object[] getUpperUserData(){
			StringBuilder longString = new StringBuilder("1");
			for (int i=0; i<10000; i++)
				longString.append("1");
			
			final String email = "Mustbeverylargetestusermailforthistextbox@gamil.com";								//test1@test.com1 - for login
			final String fistName = "Mustbeverylargetestfistnameforthistextbox";
			final String lastName = "Mustbeverylargetestlastnameforthistextbox";
			final String company = "Mustbeverylargetestlastnameforthistextbox";
			final String address = longString.toString();
			final String address2 = longString.toString(); 							// Additional address information
			final String city = "Mustbeverylargetestlastnameforthistextbox";
			final String zipCode = "88888888888888888888888888888888888";
			final String additionInformation = longString.toString();
			final String homePhone = "88888888888888888888888888888888888";
			final String mobilePhone = "88888888888888888888888888888888888";
			final String addressAlias = longString.toString();
			final String password = "MustBeVeryLargeTestPasswordForThisTextbox1";;
					
			User[] validUserData = new User[1]; 
			validUserData[0] = new User(longString.toString());
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
		public void checkUpperLimits(User user){
			logger.info("\n --- Validation test start ---\n");

			logger.info("Navigate to login page");
			RegistrationPage registrationPage = RegistrationPage.open();

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail());

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm();

			logger.info("Fill registration form");
			registrationPage.fillRegistrationForm(user);

			logger.info("Click register button");
			registrationPage.registerButtonClick();

			logger.info("Check alert");
			registrationPage.checkUpperLimitsAlerts();

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
		@Test (priority = 2, dataProvider="invalidUserData")
		public void checkValidations(User user){
			logger.info("\n --- Validation test start ---\n");

			logger.info("Navigate to login page");
			RegistrationPage registrationPage = RegistrationPage.open();

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail());

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm();

			logger.info("Fill registration form");
			registrationPage.fillRegistrationForm(user);

			logger.info("Click register button");
			registrationPage.registerButtonClick();

			logger.info("Check alert");
			registrationPage.checkInvalidAlerts();
		}	
		
		// Create valid user
		@DataProvider (name = "validUserData")
		public Object[] getValidUserData(){
			User[] validUserData = new User[1];
			validUserData[0] = new User();
			return validUserData;
		}
		
		// Test case 1 - User registration with valid data
		@Test (priority = 1, dataProvider="validUserData")
		public void checkRegistration(User user){
			logger.info("\n --- Registration test start ---\n");

			logger.info("Navigate to login page");
			RegistrationPage registrationPage = RegistrationPage.open();

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail());

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm();

			logger.info("Fill registration form");
			registrationPage.fillRegistrationForm(user);

			logger.info("Click register button");
			//registrationPage.registerButtonClick();

			logger.info("Check user profile button");
			registrationPage.checkUserButton();
		}

		@Test (priority = 0, dataProvider="validUserData")
		public void checkVisibility(User user){
			logger.info("\n --- Visibility test start ---\n");

			logger.info("Navigate to login page");
			RegistrationPage registrationPage = RegistrationPage.open();

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail());

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm();

			//registrationPage.checkElementsVisibility(this.getClass());
		}
}
