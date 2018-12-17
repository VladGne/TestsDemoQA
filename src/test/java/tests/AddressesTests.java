package tests;

import framework.models.User;
import framework.pageObjectModels.AddressRegistrationPage;
import framework.pageObjectModels.AddressesPage;
import org.testng.annotations.*;

import java.lang.reflect.Method;

//import static framework.helperClasses.FileReader.readUserDataFrom;

public class AddressesTests extends TestBase {

    private AddressesPage addressesPage;

    @BeforeMethod(groups = {"regression", "verifier","updater"})
    public void openLoginPage(Method method){
        logger.info("Navigate to login page");
        addressesPage = new AddressesPage(driver);
        logger.info(method.getName() + " start");
    }

    @AfterMethod(groups = {"regression", "verifier","updater"})
    public void doLogout(){
        logger.info("Logout");
        addressesPage.doLogout();
    }

    @DataProvider(name = "validUserData")
    public Object[] getValidUserData(){

        return fileReader.getData(User.class);
    }

    @Test(dataProvider="validUserData", groups = "regression")
    public void checkVisibilityMainElements(User user) {
        //User user = users[0];

        //final String existedEmail = "test1@test.com1";
       // user.setEmail(existedEmail);


        logger.info("Login");
        addressesPage.doLogin(user.getEmail(),user.getPassword(), driver);

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick(driver);

        logger.info("Check elements visibility");

        addressesPage.checkPageElementsVisibility(addressesPage.getClass(), softAssert);

        softAssert.assertAll();
        logger.info("\n --- Addresses test end ---\n");
    }

    @Test(dataProvider="validUserData", groups = {"regression", "verifier"})
    public void checkAddressesUserInformation(User users[]) {
        User user = users[0];

        final String existedEmail = "test1@test.com1";
        user.setEmail(existedEmail);

        logger.info("Login");
        addressesPage.doLogin(user.getEmail(),user.getPassword(), driver);

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick(driver);

        addressesPage.waitAddressPage(driver);

        logger.info("Check address form");

        addressesPage.compareActualUserWith(user, softAssert);

        softAssert.assertAll();
        logger.info("\n --- Addresses test end ---\n");
    }

    @DataProvider(name = "registeredUserData")

    public Object[] getRegisteredUserData(){
        return fileReader.getData(User.class);
    }

    @Test(dataProvider="registeredUserData", groups = {"regression", "updater"})
    public void checkAddressesUpdate(User users[]){
        User currentUserData = users[0];
        User newUsersData = users[1];

        logger.info("Login");
        addressesPage.doLogin(currentUserData.getEmail(),currentUserData.getPassword(), driver);

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick(driver);

        addressesPage.waitAddressPage(driver);

        logger.info("Navigate to address update page");
        addressesPage.updateButtonClick();

        AddressRegistrationPage addressRegistrationPage = new AddressRegistrationPage(driver);

        logger.info("Fill address form");
        addressRegistrationPage.fillAddressRegistrationForm(newUsersData);

        logger.info("Save button click");
        addressRegistrationPage.saveButtonClick();

        addressesPage.waitAddressPage(driver);
    }
}