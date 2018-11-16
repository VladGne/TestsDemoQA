package com.org.MavenTests;

import Models.User;
import POM.AuthenticationPage;
import POM.BasePage;
import POM.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class LoginPageTests extends TestBase{

    private Logger logger =  Logger.getLogger(LoginPage.class);

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
        authenticationPage.init(driver);

        String mail = user.getEmail();
        logger.info("Input email");
        authenticationPage.openRegistrationForm(mail);

        logger.info("Wait for alert");
        WebElement alertMessage = authenticationPage.waitForAlert();

        logger.info("Check alert message");
        if(!alertMessage.getText().equals(AuthenticationPage.REPEATED_EMAIL_MESSAGE)){
            logger.error("Email validation fail: ");
            softAssertion.fail("Email validation fail: ");
        }

        softAssertion.assertAll();
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
    public void checkEmailValidations(User userData){
        User user =  userData;

        LoginPage loginPage = LoginPage.open(driver);

        // Enter email to get access to registration page
        loginPage.writeText(By.id(LoginPage.EMAIL_CREATION_TEXTBOX_LOCATOR), (user.getEmail()));
        loginPage.click(By.id(LoginPage.SUBMIT_BUTTON_LOCATOR));

        // Wait for registration form
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until((WebDriver d) -> d.findElement(By.className(LoginPage.ALERTS_LOCATOR)));

        logger.error("Alert message loading error: ");
        softAssertion.fail("Alert message loading error: ");

        if(!loginPage.readText(By.className(LoginPage.ALERTS_LOCATOR)).equals(LoginPage.INVALID_EMAIL_MESSAGE)){
            logger.error("Email validation alert message fail:" );
            softAssertion.fail("Email validation alert message fail:");
        }

        logger.error("Email validation fail: ");
        softAssertion.fail("Email validation fail: ");

        softAssertion.assertAll();
    }
}
