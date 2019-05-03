package tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.models.User;
import framework.pages.AddressRegistrationPage;
import framework.pages.AddressesPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Positive tests")
@Feature("Login Tests")
public class AddressesTests extends TestBase {

    private AddressesPage addressesPage;

    @Step("Open login page")
    @BeforeMethod(groups = {"regression", "verifier","updater"})
    public void openLoginPage(Method method){
        logger.info("Navigate to login page");
        addressesPage = new AddressesPage(driver);
        logger.info(method.getName() + " start");
    }

    @Step("Try to logout")
    @AfterMethod(groups = {"regression", "verifier","updater"})
    public void doLogout(){
        addressesPage.doLogout();
    }

    @DataProvider(name = "validUserData")
    public Object[][] getValidUserData(){
        return fileReader.getData(User.class);
    }

    @Description("Verify interactivity main elements of login page")
    @Story("All main element are enabled")
    @Test(dataProvider="validUserData", groups = "regression")
    public void checkVisibilityMainElements(User user) {

        logger.info("Login");
        addressesPage.doLogin(user.getEmail(),user.getPassword(), driver);

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick(driver);

        logger.info("Check elements visibility");

        addressesPage.checkPageElementsVisibility(addressesPage.getClass(), softAssert);

        softAssert.assertAll();
        logger.info(" --- Addresses test end ---\n");
    }

    @Description("Verify that users addresses info shows correctly")
    @Story("User data on the page equals to user model")
    @Test(dataProvider="validUserData", groups = {"regression", "verifier"})
    public void checkAddressesUserInformation(User user) {

        String email = context.get("Email").toString();
        user.setEmail(email);

        logger.info("Login");
        addressesPage.doLogin(user.getEmail(),user.getPassword(), driver);

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick(driver);

        addressesPage.waitAddressPage(driver);

        logger.info("Check address form");

        addressesPage.compareActualUserWith(user, softAssert);

        softAssert.assertAll();
        logger.info(" --- Addresses test end ---\n");
    }

    @DataProvider(name = "registeredUserData")

    public Object[][] getRegisteredUserData(){
        return fileReader.getData(User.class);
    }

    @Description("Verify that users addresses info could be change and save successful")
    @Story("User data on the page equals to user model")
    @Test(dataProvider="registeredUserData", groups = {"regression", "updater"})
    public void checkAddressesUpdate(User user){
        String email = context.get("Email").toString();

        logger.info("Login");
        addressesPage.doLogin(email,user.getPassword(), driver);

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick(driver);

        addressesPage.waitAddressPage(driver);

        logger.info("Navigate to address update page");
        addressesPage.updateButtonClick();

        AddressRegistrationPage addressRegistrationPage = new AddressRegistrationPage(driver);

        logger.info("Fill address form");
        addressRegistrationPage.fillAddressRegistrationForm(user);

        logger.info("Save button click");
        addressRegistrationPage.saveButtonClick();

        addressesPage.waitAddressPage(driver);
    }
}