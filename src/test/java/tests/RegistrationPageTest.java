package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.models.User;
import framework.pages.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import net.bytebuddy.utility.RandomString;

public class RegistrationPageTest extends TestBase {

    private RegistrationPage registrationPage;

    @Step("Open login page")
    @BeforeMethod(groups = {"regression", "contentGenerator"})
    public void openLoginPage() {
        logger.info("Navigate to login page");
        registrationPage = new RegistrationPage(driver);
    }

    // Create invalid user
    @DataProvider(name = "upperUserData")
    public Object[][] getUpperUserData() {

        return fileReader.getData(User.class);
    }

    // Test case 1 - User registration with valid data
    @Test(dataProvider = "upperUserData", groups = "verifier")
    @Description("Input upper-limits values to all fields in register page, collect and compare all alerts")
    @Story("Check all register fields validation for upper-limits values")
    public void checkUpperLimits(User user) {

        String invalidValue = new RandomString(maxFieldLength).nextString();

        user.setAdditionInformation(invalidValue);
        user.setAddress(invalidValue);
        user.setAddress2(invalidValue);
        user.setAddressAlias(invalidValue);

        logger.info(" --- Validation test start ---\n");

        logger.info("Input email - " + user.getEmail());
        registrationPage.submitEmail(user.getEmail(), driver);

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
    @DataProvider(name = "invalidUserData")
    public Object[][] getInvalidUserData() {
        return fileReader.getData(User.class);
    }

    //Check date and phone number validation
    @Test(dataProvider = "invalidUserData", groups = "verifier")
    @Description("Input invalid values to all fields in register page, collect and compare all alerts")
    @Story("Check all register fields validation for invalid values")
    public void checkValidations(User user) {
        logger.info(" --- Validation test start ---\n");

        logger.info("Input email - " + user.getEmail());
        registrationPage.submitEmail(user.getEmail(), driver);

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
    @DataProvider(name = "validUserData")
    public Object[][] getValidUserData() {
        return fileReader.getData(User.class);
    }

    // Test case 1 - User registration with valid data
    @Description("Verify that we can open register page, fill fields and register ")
    @Story("Register new user")
    @Test(dataProvider = "validUserData", groups = "regression")
    public void checkRegistration(User user) {
        logger.info(" --- Registration test start ---\n");

        String generatedEmail = new RandomString().nextString() + "@testmail.test";

        user.setEmail(generatedEmail);
        //context.setAttribute("Email", generatedEmail);
        context.put("Email", generatedEmail);

        logger.info("Input email - " + user.getEmail());
        registrationPage.submitEmail(user.getEmail(), driver);

        registrationPage.waitForRegistrationForm(driver);

        logger.info("Fill registration form");
        registrationPage.fillRegistrationForm(user);

        logger.info("Click register button");
        registrationPage.registerButtonClick();

        logger.info("Check user profile button");
        registrationPage.checkUserButton();

        registrationPage.doLogout();
    }

    @Description("Verify interactivity main elements of login page")
    @Story("All main element are enabled")
    @Test(dataProvider = "validUserData", groups = {"regression", "contentGenerator"})
    public void checkVisibility(User user) {
        String generatedEmail = new RandomString().nextString() + "@testmail.test";
        user.setEmail(generatedEmail);

        logger.info(" --- Visibility test start ---\n");

        logger.info("Input email - " + user.getEmail() );
        registrationPage.submitEmail(user.getEmail(), driver);

        registrationPage.waitForRegistrationForm(driver);

        registrationPage.checkPageElementsVisibility(registrationPage.getClass(), softAssert);
        softAssert.assertAll();
    }
}