package com.org.MavenTests;


import POM.RegistrationPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Models.User;
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
		public void checkUpperLimits(User user){
			logger.info("\n --- Validation test start ---\n");

			logger.info("Navigate to login page");
			RegistrationPage registrationPage = RegistrationPage.open(driver);

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail());

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm();

			logger.info("Select gender");
			registrationPage.selectMaleGender();

			logger.info("Input first name");
			registrationPage.inputFirstName(user.getFistName());

			logger.info("Input last name");
			registrationPage.inputLastName(user.getLastName());

			logger.info("Input password");
			registrationPage.inputPassword(user.getPassword());

			logger.info("Input company");
			registrationPage.inputCompany(user.getCompany());

			logger.info("Input address1");
			registrationPage.inputAddress1(user.getAddress());

			logger.info("Input address2");
			registrationPage.inputAddress2(user.getAddress2());

			logger.info("Input city");
			registrationPage.inputCity(user.getCity());

			logger.info("Input country");
			registrationPage.selectCountry(user.getCountry());

			logger.info("Input state");
			registrationPage.selectState(user.getState());

			logger.info("Input post code");
			registrationPage.inputPostcode(user.getZipCode());

			logger.info("Input addition information");
			registrationPage.inputOther(user.getAdditionInformation());

			logger.info("Input home phone");
			registrationPage.inputHomePhone(user.getHomePhone());

			logger.info("Input mobile phone");
			registrationPage.inputMobilePhone(user.getMobilePhone());

			logger.info("Input alias");
			registrationPage.inputAlias(user.getAddressAlias());

			logger.info("Select news");
			registrationPage.selectNews();

			logger.info("Select options");
			registrationPage.selectOptions();

			logger.info("Select day of birth");
			registrationPage.selectBirthDay(user.getDayBirth());

			logger.info("Select month of birth");
			registrationPage.selectBirthMonth(user.getMonthBirth().toString());

			logger.info("Select year of birth");
			registrationPage.selectBirthYear(user.getYearBirth());

			logger.info("Click register button");
			registrationPage.registerButtonClick();

			logger.info("Check alert");
			registrationPage.checkUpperLimitsAlerts();

			softAssertion.assertAll();
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
		public void checkValidations(User user){
			logger.info("\n --- Validation test start ---\n");

			logger.info("Navigate to login page");
			RegistrationPage registrationPage = RegistrationPage.open(driver);

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail());

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm();

			logger.info("Select gender");
			registrationPage.selectMaleGender();

			logger.info("Input first name");
			registrationPage.inputFirstName(user.getFistName());

			logger.info("Input last name");
			registrationPage.inputLastName(user.getLastName());

			logger.info("Input password");
			registrationPage.inputPassword(user.getPassword());

			logger.info("Input company");
			registrationPage.inputCompany(user.getCompany());

			logger.info("Input address1");
			registrationPage.inputAddress1(user.getAddress());

			logger.info("Input address2");
			registrationPage.inputAddress2(user.getAddress2());

			logger.info("Input city");
			registrationPage.inputCity(user.getCity());

			logger.info("Input country");
			registrationPage.selectCountry(user.getCountry());

			logger.info("Input state");
			registrationPage.selectState(user.getState());

			logger.info("Input post code");
			registrationPage.inputPostcode(user.getZipCode());

			logger.info("Input addition information");
			registrationPage.inputOther(user.getAdditionInformation());

			logger.info("Input home phone");
			registrationPage.inputHomePhone(user.getHomePhone());

			logger.info("Input mobile phone");
			registrationPage.inputMobilePhone(user.getMobilePhone());

			logger.info("Input alias");
			registrationPage.inputAlias(user.getAddressAlias());

			logger.info("Select news");
			registrationPage.selectNews();

			logger.info("Select options");
			registrationPage.selectOptions();

			logger.info("Select day of birth");
			registrationPage.selectBirthDay(user.getDayBirth());

			logger.info("Select month of birth");
			registrationPage.selectBirthMonth(user.getMonthBirth().toString());

			logger.info("Select year of birth");
			registrationPage.selectBirthYear(user.getYearBirth());

			logger.info("Click register button");
			registrationPage.registerButtonClick();

			logger.info("Check alert");
			registrationPage.checkInvalidAlerts();

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
		public void checkRegistration(User user){
			logger.info("\n --- Registration test start ---\n");

			logger.info("Navigate to login page");
			RegistrationPage registrationPage = RegistrationPage.open(driver);

			logger.info("Input email");
			registrationPage.submitEmail(user.getEmail());

			logger.info("Wait for registration form");
			registrationPage.waitForRegistrationForm();

			logger.info("Select gender");
			registrationPage.selectMaleGender();

			logger.info("Input first name");
			registrationPage.inputFirstName(user.getFistName());

			logger.info("Input last name");
			registrationPage.inputLastName(user.getLastName());

			logger.info("Input password");
			registrationPage.inputPassword(user.getPassword());

			logger.info("Input company");
			registrationPage.inputCompany(user.getCompany());

			logger.info("Input address1");
			registrationPage.inputAddress1(user.getAddress());

			logger.info("Input address2");
			registrationPage.inputAddress2(user.getAddress2());

			logger.info("Input city");
			registrationPage.inputCity(user.getCity());

			logger.info("Input country");
			registrationPage.selectCountry(user.getCountry());

			logger.info("Input state");
			registrationPage.selectState(user.getState());

			logger.info("Input post code");
			registrationPage.inputPostcode(user.getZipCode());

			logger.info("Input addition information");
			registrationPage.inputOther(user.getAdditionInformation());

			logger.info("Input home phone");
			registrationPage.inputHomePhone(user.getHomePhone());

			logger.info("Input mobile phone");
			registrationPage.inputMobilePhone(user.getMobilePhone());

			logger.info("Input alias");
			registrationPage.inputAlias(user.getAddressAlias());

			logger.info("Select news");
			registrationPage.selectNews();

			logger.info("Select options");
			registrationPage.selectOptions();

			logger.info("Select day of birth");
			registrationPage.selectBirthDay(user.getDayBirth());

			logger.info("Select month of birth");
			registrationPage.selectBirthMonth(user.getMonthBirth().toString());

			logger.info("Select year of birth");
			registrationPage.selectBirthYear(user.getYearBirth());

			logger.info("Click register button");
			//registrationPage.registerButtonClick();

			softAssertion.assertAll();
		}
}
