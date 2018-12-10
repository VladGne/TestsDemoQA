package tests;

import framework.models.User;
import framework.pageObjectModels.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static framework.helperClasses.FileReader.readUserDataFrom;

public class LoginPageTests extends TestBase{

    private LoginPage authenticationPage;

    @BeforeMethod(groups = "regression")
    public void openLoginPage(){
        logger.info("Navigate to login page");
        authenticationPage = new LoginPage(driver);
    }

    // Create invalid user
    @DataProvider(name = "repeatedUserEmail")
    @Parameters("validUserFilePath")
    public Object[] getRepeatedUserEmail(String validUserFilePath){

        User[] invalidUserData = new User[1];

        invalidUserData[0] = readUserDataFrom(validUserFilePath);

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
    public Object[] getInvalidUserEmail(String validUserFilePath){

        final String invalidEmail ="invlalidEmail";

        User[] invalidUserData = new User[1];

        invalidUserData[0].setEmail(invalidEmail);

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
