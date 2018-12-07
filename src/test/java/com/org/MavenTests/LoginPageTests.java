package com.org.MavenTests;

import Models.User;
import POM.LoginPage;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class LoginPageTests extends TestBase{

    private LoginPage authenticationPage;

    @BeforeMethod(groups = "regression")
    public void openLoginPage(){
        logger.info("Navigate to login page");
        authenticationPage = new LoginPage(driver);
    }

    // Create invalid user
    @DataProvider(name = "repeatedUserEmail")
    public Object[] getRepeatedUserEmail(){

        User[] invalidUserData = new User[1];
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert JSON string from file to Object
            invalidUserData[0] = mapper.readValue(new File("src\\test\\resources\\validUser.json"), User.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String existedEmail = "test1@test.com1";
        invalidUserData[0].setEmail(existedEmail);

        return invalidUserData;
    }

    @Test(priority = 1, dataProvider="repeatedUserEmail", groups = "regression")
    public void checkEmailRepeat(User user){
        logger.info("\n --- Check Repeated Email Test Start ---\n");

        logger.info("Input email");
        authenticationPage.inputNewEmail(user.getEmail());

        logger.info("Click submit button");
        authenticationPage.createAccountButtonClick();

        logger.info("Wait for alert");
        authenticationPage.waitForAlertMessage(driver);

        logger.info("Check alert message");

        Assert.assertEquals(authenticationPage.getEmailAlertMessage(), LoginPage.REPEATED_EMAIL_MESSAGE);

        logger.info("\n --- Check Repeated Email Test End ---\n");
    }

    // Create invalid user
    @DataProvider (name = "invalidUserEmail")
    public Object[] getInvalidUserEmail(){

        User[] invalidUserData = new User[1];
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert JSON string from file to Object
            invalidUserData[0] = mapper.readValue(new File("src\\test\\resources\\invalidEmailUser.json"), User.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return invalidUserData;
    }

    @Test (priority = 2, dataProvider="invalidUserEmail", groups = "regression")
    public void checkEmailValidations(User user){

        logger.info("\n --- Check Invalid Email Test Start ---\n");

        logger.info("Input email");
        authenticationPage.inputNewEmail(user.getEmail());

        logger.info("Click submit button");
        authenticationPage.createAccountButtonClick();

        logger.info("Wait for alert");
        authenticationPage.waitForAlertMessage(driver);

        logger.info("Check alert message");

        Assert.assertEquals(authenticationPage.getEmailAlertMessage(), LoginPage.INVALID_EMAIL_MESSAGE);

        logger.info("\n --- Check Invalid Email Test End ---\n");
    }
}
