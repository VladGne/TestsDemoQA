package com.org.MavenTests;

import Models.User;
import POM.AuthenticationPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class LoginPageTests extends TestBase{

    private Logger logger =  Logger.getLogger(AuthenticationPage.class);

    // Create invalid user
    @DataProvider(name = "repeatedUserEmail")
    public Object[] getRepeatedUserEmail(){
        final String repeatedEmail = "test1@test.com1";

        User[] invalidUserData = new User[1];
        invalidUserData[0] = new User();
        invalidUserData[0].setEmail(repeatedEmail);

        return invalidUserData;
    }

    @Test(priority = 1, dataProvider="repeatedUserEmail")
    public void checkEmailRepeat(User user){
        logger.info("\n --- Check Repeated Email Test Start ---\n");

        logger.info("Navigate to Authentication Page");
        AuthenticationPage authenticationPage = AuthenticationPage.open(driver);

        logger.info("Input email");
        authenticationPage.inputNewEmail(user.getEmail());

        logger.info("Click submit button");
        authenticationPage.createAccountButtonClick();

        logger.info("Wait for alert");
        WebElement alertMessage = authenticationPage.waitForAlertMessage();

        logger.info("Check alert message");
        if(!authenticationPage.checkRepeatedEmailAlertMessage()){
            logger.error("Alert message validation fail: ");
            softAssertion.fail("Alert message validation fail: ");
        }

        softAssertion.assertAll();
        logger.info("\n --- Check Repeated Email Test End ---\n");
    }

    // Create invalid user
    @DataProvider (name = "invalidUserEmail")
    public Object[] getInvalidUserEmail(){
        final String invalidEmail = "invlalidMail";

        User[] invalidUserData = new User[1];
        invalidUserData[0] = new User(invalidEmail);

        return invalidUserData;
    }

    @Test (priority = 2, dataProvider="invalidUserEmail")
    public void checkEmailValidations(User user){

        logger.info("\n --- Check Invalid Email Test Start ---\n");

        logger.info("Navigate to Authentication Page");
        AuthenticationPage authenticationPage = AuthenticationPage.open(driver);

        logger.info("Input email");
        authenticationPage.inputNewEmail(user.getEmail());

        logger.info("Wait for alert");
        WebElement alertMessage = authenticationPage.waitForAlertMessage();

        logger.info("Check alert message");
        if(!authenticationPage.checkInvalidEmailAlertMessage()){
            logger.error("Alert message validation fail: ");
            softAssertion.fail("Alert message validation fail: ");
        }

        softAssertion.assertAll();
        logger.info("\n --- Check Invalid Email Test End ---\n");
    }
}
