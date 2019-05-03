package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.models.User;
import framework.pages.PersonalPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;

public class PersonalInfoTests extends TestBase {

    private PersonalPage personalPage;

    @BeforeMethod(groups = {"regression", "verifier", "updater"})
    public void openLoginPage() {
        logger.info("Navigate to login page");
        personalPage = new PersonalPage(driver);
    }

    @AfterMethod(groups = {"regression", "verifier", "updater"})
    public void doLogout() {
        personalPage.doLogout();
    }

    // Create valid user
    @DataProvider(name = "validUserData")
    public Object[][] getValidUserData() {

        return fileReader.getData(User.class);
    }

    @Description("Verify interactivity main elements of login page")
    @Story("All main element are enabled")
    @Test(dataProvider = "validUserData", groups = "regression")
    public void checkVisibility(User user) {
        logger.info("Check personal info page elements visibility test start\n");

        logger.info("Login");
        personalPage.doLogin(user.getEmail(), user.getPassword(), driver);

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo(driver);

        logger.info("Check elements visibility");
        personalPage.checkPageElementsVisibility(personalPage.getClass(), softAssert);
        softAssert.assertAll();

        logger.info(" --- Check personal info page elements visibility test end ---\n");
    }

    @Test(dataProvider = "validUserData", groups = {"regression", "verifier"})
    @Description("User personal info on page should be equals to user model")
    @Story("Verify user personal info")
    public void checkPersonalInfo(User user) {
        logger.info("\n --- Check personal info test start ---\n");

        String email = context.get("Email").toString();
        user.setEmail(email);

        logger.info("Login");
        personalPage.doLogin(user.getEmail(), user.getPassword(), driver);

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo(driver);

        logger.info("Validate personal page");
        personalPage.compareActualUserWith(user, softAssert);

        softAssert.assertAll();
    }

    @DataProvider(name = "registeredUserData")
    public Object[][] getRegisteredUserData() {

        return fileReader.getData(User.class);
    }

    @Test(dataProvider = "registeredUserData", groups = {"regression", "updater"})
    @Description("User personal info on page should be updated and saved successfully")
    @Story("Edit user personal info")
    public void updatePersonalInfo(User user) {

        String email = context.get("Email").toString();
        user.setEmail(email);

        logger.info("Login");
        personalPage.doLogin(user.getEmail(), user.getPassword(), driver);

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo(driver);

        personalPage.inputCurrentPassword(user.getPassword());

        personalPage.fillUserFormWithDataFrom(user);

        personalPage.saveButtonClick();
        personalPage.waitSuccessfulMessage(driver);

    }
}
