package com.org.MavenTests;

import POM.RegistrationPage;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.*;
import Models.User;

import java.io.File;
import java.io.IOException;

public class RegPageTest extends TestBase{

	private RegistrationPage registrationPage;

		@BeforeMethod(groups = "regression")
		public void openLoginPage(){
			logger.info("Navigate to login page");
			registrationPage = new RegistrationPage(driver);
		}

		// Create invalid user
		@DataProvider (name = "upperUserData")
		public Object[] getUpperUserData(){
			User[] upperUserData = new User[1];

			ObjectMapper mapper = new ObjectMapper();
			try {
				// Convert JSON string from file to Object
				upperUserData[0] = mapper.readValue(new File("src\\test\\resources\\upperUser.json"), User.class);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return upperUserData;
		}
				
		// Test case 1 - User registration with valid data
		@Test (priority = 3, dataProvider="upperUserData", groups = "regression")
		public void checkUpperLimits(User user){
			logger.info("\n --- Validation test start ---\n");

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail(),driver);

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm(driver);

			logger.info("Fill registration form");
			registrationPage.fillRegistrationForm(user);

			logger.info("Click register button");
			registrationPage.registerButtonClick();

			logger.info("Check alert");
			registrationPage.checkUpperLimitsAlerts(softAssert);

			softAssert.assertAll();

		}
		
		// Create invalid user
		@DataProvider (name = "invalidUserData")
		public Object[] getInvalidUserData(){

			User[] invalidUserData = new User[1];

			ObjectMapper mapper = new ObjectMapper();
			try {
				// Convert JSON string from file to Object
				invalidUserData[0] = mapper.readValue(new File("src\\test\\resources\\invalidUser.json"), User.class);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return invalidUserData;
		}
				
		//Check date and phone number validation
		@Test (priority = 2, dataProvider="invalidUserData", groups = "regression")
		public void checkValidations(User user){
			logger.info("\n --- Validation test start ---\n");

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail(),driver);

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm(driver);

			logger.info("Fill registration form");
			registrationPage.fillRegistrationForm(user);

			logger.info("Click register button");
			registrationPage.registerButtonClick();

			logger.info("Check alert");
			registrationPage.checkInvalidAlerts(softAssert);

			softAssert.assertAll();
		}	
		
		// Create valid user
		@DataProvider (name = "validUserData")
		public Object[] getValidUserData(){
			User[] validUserData = new User[1];
			ObjectMapper mapper = new ObjectMapper();
			try {
				// Convert JSON string from file to Object
				validUserData[0] = mapper.readValue(new File("src\\test\\resources\\validUser.json"), User.class);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return validUserData;
		}
		
		// Test case 1 - User registration with valid data
		@Test (priority = 1, dataProvider="validUserData", groups = "regression")
		public void checkRegistration(User user){
			logger.info("\n --- Registration test start ---\n");

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail(), driver);

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm(driver);

			logger.info("Fill registration form");
			registrationPage.fillRegistrationForm(user);

			//logger.info("Click register button");
			//registrationPage.registerButtonClick();

			logger.info("Check user profile button");
			registrationPage.checkUserButton();
		}

		@Test (priority = 0, dataProvider="validUserData", groups = "regression")
		public void checkVisibility(User user){
			logger.info("\n --- Visibility test start ---\n");

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail(), driver);

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm(driver);

			registrationPage.checkPageElementsVisibility(registrationPage.getClass(),softAssert);
			softAssert.assertAll();
		}
}
