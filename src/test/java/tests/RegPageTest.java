package tests;

import framework.models.User;
import framework.pageObjectModels.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import net.bytebuddy.utility.RandomString;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegPageTest extends TestBase {

    private RegistrationPage registrationPage;

    @Step("Open login page")
    @BeforeMethod(groups = {"regression", "contentGenerator"})
    public void openLoginPage() {
        logger.info("Navigate to login page");
        registrationPage = new RegistrationPage(driver);
    }

    @AfterMethod(groups = {"regression", "verifier", "updater"})
    public void doLogout() {
        logger.info("Logout");
        registrationPage.doLogout();
    }

    // Create invalid user
    @DataProvider(name = "upperUserData")
    //@Parameters("upperUserFilePath")
    public Object[][] getUpperUserData() {

        return fileReader.getData(User.class);
    }

    // Test case 1 - User registration with valid data
    @Test(dataProvider = "upperUserData", groups = "regression")
    @Description("Input upper-limits values to all fields in register page, collect and compare all alerts")
    @Story("Check all register fields validation for upper-limits values")
    public void checkUpperLimits(User user) {

        String invalidValue = new RandomString(maxFieldLength).nextString();

        user.setAdditionInformation(invalidValue);
        user.setAddress(invalidValue);
        user.setAddress2(invalidValue);
        user.setAddressAlias(invalidValue);

        logger.info("\n --- Validation test start ---\n");

        logger.info("Input email");
        registrationPage.submitEmail(user.getEmail(), driver);

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
    @DataProvider(name = "invalidUserData")
    public Object[][] getInvalidUserData() {
        return fileReader.getData(User.class);
    }

    //Check date and phone number validation
    @Test(dataProvider = "invalidUserData", groups = "regression")
    @Description("Input invalid values to all fields in register page, collect and compare all alerts")
    @Story("Check all register fields validation for invalid values")
    public void checkValidations(User user) {
        logger.info("\n --- Validation test start ---\n");

        logger.info("Input email");
        registrationPage.submitEmail(user.getEmail(), driver);

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
    @DataProvider(name = "validUserData")
    public Object[][] getValidUserData() {
        return fileReader.getData(User.class);
    }

    // Test case 1 - User registration with valid data
    @Description("Verify that we can open register page, fill fields and register ")
    @Story("Register new user")
    @Test(dataProvider = "validUserData", groups = "regression")
    public void checkRegistration(User user) {
        logger.info("\n --- Registration test start ---\n");

        String generatedEmail = new RandomString().nextString() + "@testmail.test";

        user.setEmail(generatedEmail);
        //context.setAttribute("Email", generatedEmail);
        context.put("Email", generatedEmail);

        logger.info("Input email");
        registrationPage.submitEmail(user.getEmail(), driver);

        logger.info("Wait for registration form");
        registrationPage.waitForRegistrationForm(driver);

        logger.info("Fill registration form");
        registrationPage.fillRegistrationForm(user);

        logger.info("Click register button");
        registrationPage.registerButtonClick();

        logger.info("Check user profile button");
        registrationPage.checkUserButton();
    }

    @Description("Verify interactivity main elements of login page")
    @Story("All main element are enabled")
    @Test(dataProvider = "validUserData", groups = {"regression", "contentGenerator"})
    public void checkVisibility(User user) {
        String generatedEmail = new RandomString().nextString() + "@testmail.test";
        user.setEmail(generatedEmail);

        logger.info("\n --- Visibility test start ---\n");

        logger.info("Input email");
        registrationPage.submitEmail(user.getEmail(), driver);

        logger.info("Wait for registration form");
        registrationPage.waitForRegistrationForm(driver);

        registrationPage.checkPageElementsVisibility(registrationPage.getClass(), softAssert);
        softAssert.assertAll();
    }
}