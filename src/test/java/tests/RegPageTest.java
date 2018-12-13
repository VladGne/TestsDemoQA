package tests;

import framework.models.User;
import framework.pageObjectModels.RegistrationPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import static framework.helperClasses.FileReader.readUserDataFrom;

public class RegPageTest extends TestBase{

	private RegistrationPage registrationPage;

		@BeforeMethod( groups = {"regression", "contentGenerator"})
		public void openLoginPage(){
			logger.info("Navigate to login page");
			registrationPage = new RegistrationPage(driver);
		}

		// Create invalid user
		@DataProvider (name = "upperUserData")
		//@Parameters("upperUserFilePath")
		public Object[] getUpperUserData(){

			fileReader.processDataFile(parameters.get("upperUserData"));
			return fileReader.getData();
		}
				
		// Test case 1 - User registration with valid data
		@Test (dataProvider="upperUserData", groups = "regression")
		public void checkUpperLimits(User users[]){
			User user = users[0];
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

			fileReader.processDataFile(parameters.get("invalidUserData"));
			return fileReader.getData();
		}
				
		//Check date and phone number validation
		@Test (dataProvider="invalidUserData", groups = "regression")
		public void checkValidations(User users[]){
			User user = users[0];
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
			fileReader.processDataFile(parameters.get("validUserData"));
			return fileReader.getData();
		}
		
		// Test case 1 - User registration with valid data
		@Test (dataProvider="validUserData", groups = "regression")
		public void checkRegistration(User users[]){
			User user = users[0];
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

		@Test (dataProvider="validUserData", groups = {"regression", "contentGenerator"})
		public void checkVisibility(User users[]){
			User user = users[0];
			final String existedEmail = "test3@test.com3";
			user.setEmail(existedEmail);
			logger.info("\n --- Visibility test start ---\n");

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail(), driver);

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm(driver);

			registrationPage.checkPageElementsVisibility(registrationPage.getClass(),softAssert);
			softAssert.assertAll();
		}
}