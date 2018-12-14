package tests;

import framework.models.User;
import framework.pageObjectModels.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import static framework.helperClasses.FileReader.readUserDataFrom;

public class LoginPageTests extends TestBase{

    private LoginPage authenticationPage;

    @BeforeMethod(groups = "regression")
    public void openLoginPage(){
        logger.info("Navigate to login page");
        authenticationPage = new LoginPage(driver);
    }

    // Create invalid user
    @DataProvider(name = "repeatedUserEmail")
    public Object[] getRepeatedUserEmail( ){

        return fileReader.getData(User.class);
    }

    @Test(dataProvider="repeatedUserEmail", groups = "regression")
    public void checkEmailRepeat(User user){
        logger.info("\n --- Check Repeated Email Test Start ---\n");

        final String existedEmail = "test1@test.com1";
        user.setEmail(existedEmail);

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

        return fileReader.getData(User.class);
    }

    @Test (dataProvider="invalidUserEmail", groups = "regression")
    public void checkEmailValidations(User user){

        final String invalidEmail = "invlalidEmail";
        user.setEmail(invalidEmail);

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

    @Test ()
    public void checkVisibility(){
        logger.info("\n --- Visibility test start ---\n");
        authenticationPage.checkPageElementsVisibility(authenticationPage.getClass(),softAssert);
        softAssert.assertAll();
    }
}
