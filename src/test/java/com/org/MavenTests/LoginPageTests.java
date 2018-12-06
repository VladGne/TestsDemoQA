package com.org.MavenTests;

import Models.User;
import POM.AuthenticationPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTests extends TestBase{

    private AuthenticationPage authenticationPage;

    // Create invalid user
    @DataProvider(name = "repeatedUserEmail")
    public Object[] getRepeatedUserEmail(){
        final String repeatedEmail = "test1@test.com1";

        User[] invalidUserData = new User[1];
        invalidUserData[0] = new User();
        invalidUserData[0].setEmail(repeatedEmail);

        return invalidUserData;
    }

    @BeforeMethod
    public void openLoginPage(){
        logger.info("Navigate to login page");
        authenticationPage = AuthenticationPage.open();
    }

    @Test(priority = 1, dataProvider="repeatedUserEmail")
    public void checkEmailRepeat(User user){
        logger.info("\n --- Check Repeated Email Test Start ---\n");

        logger.info("Input email");
        authenticationPage.inputNewEmail(user.getEmail());

        logger.info("Click submit button");
        authenticationPage.createAccountButtonClick();

        logger.info("Wait for alert");
        WebElement alertMessage = authenticationPage.waitForAlertMessage();

        logger.info("Check alert message");
        //authenticationPage.checkRepeatedEmailAlertMessage();

        Assert.assertEquals(authenticationPage.getEmailAlertMessage(), AuthenticationPage.REPEATED_EMAIL_MESSAGE);

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

        logger.info("Input email");
        authenticationPage.inputNewEmail(user.getEmail());

        logger.info("Click submit button");
        authenticationPage.createAccountButtonClick();

        logger.info("Wait for alert");
        WebElement alertMessage = authenticationPage.waitForAlertMessage();

        logger.info("Check alert message");

        Assert.assertEquals(authenticationPage.getEmailAlertMessage(), AuthenticationPage.INVALID_EMAIL_MESSAGE);

        logger.info("\n --- Check Invalid Email Test End ---\n");
    }
}
