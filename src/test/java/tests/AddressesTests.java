package tests;

import framework.models.User;
import framework.pageObjectModels.AddressRegistrationPage;
import framework.pageObjectModels.AddressesPage;
import framework.pageObjectModels.RegistrationPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

//import static framework.helperClasses.FileReader.readUserDataFrom;

public class AddressesTests extends TestBase {

    private AddressesPage addressesPage;

    @BeforeMethod(groups = "regression")
    public void openLoginPage(Method method){
        logger.info("Navigate to login page");
        addressesPage = new AddressesPage(driver);
        logger.info(method.getName() + " start");
    }

    @AfterMethod(groups = "regression")
    public void doLogout(){
        logger.info("Logout");
        addressesPage.doLogout();
    }

    @DataProvider(name = "validUserData")
    public Object[] getValidUserData(){

        fileReader.processDataFile(parameters.get("validUserData"));
        return fileReader.getData();
    }

    @Test(priority = 1, dataProvider="validUserData", groups = "regression")
    public void checkVisibilityMainElements(User user) {

        final String existedEmail = "test1@test.com1";
        user.setEmail(existedEmail);


        logger.info("Login");
        addressesPage.doLogin(user.getEmail(),user.getPassword(), driver);

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick(driver);

        logger.info("Check elements visibility");

        addressesPage.checkPageElementsVisibility(addressesPage.getClass(), softAssert);

        softAssert.assertAll();
        logger.info("\n --- Addresses test end ---\n");
    }

    @Test(priority = 1, dataProvider="validUserData", groups = "regression")
    public void checkAddressesUserInformation(User user) {

        logger.info("Login");
        addressesPage.doLogin(user.getEmail(),user.getPassword(), driver);

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick(driver);

        addressesPage.waitAddressPage(driver);

        logger.info("Check address form");

        User actualUser = addressesPage.getActualUserData();
        softAssert.assertEquals(actualUser.getFistName(), user.getFistName(), "Users first names doesn't match: ");
        softAssert.assertEquals(actualUser.getLastName(), user.getLastName(), "Users last names doesn't match ");
        softAssert.assertEquals(actualUser.getCountry(), user.getCountry(), "Users countries doesn't match ");
        softAssert.assertEquals(actualUser.getCity(), user.getCity(), "Users cities doesn't match ");
        softAssert.assertEquals(actualUser.getState(), user.getState(), "Users states doesn't match ");
        softAssert.assertEquals(actualUser.getZipCode(), user.getZipCode(), "Users post codes doesn't match ");
        softAssert.assertEquals(actualUser.getCompany(), user.getCompany(), "Users companies doesn't match ");
        softAssert.assertEquals(actualUser.getAddress(), user.getAddress(), "Users addresses doesn't match ");
        softAssert.assertEquals(actualUser.getAddress2(), user.getAddress2(), "Users addressess2 doesn't match ");
        softAssert.assertEquals(actualUser.getHomePhone(), user.getHomePhone(), "Users home phones doesn't match ");
        softAssert.assertEquals(actualUser.getMobilePhone(), user.getMobilePhone(), "Users mobile phones doesn't match ");

        softAssert.assertAll();
        logger.info("\n --- Addresses test end ---\n");
    }

    @DataProvider(name = "registeredUserData")
    public Object[] getRegisteredUserData(){ ;
        fileReader.processDataFile( parameters.get( "registeredUserData" ) );

        return fileReader.getData();
    }

    @Test(priority = 0, dataProvider="registeredUserData", groups = "regression")
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

        logger.info("Check address form");
        User actualUser = addressesPage.getActualUserData();
        softAssert.assertEquals(actualUser.getFistName(), newUsersData.getFistName(), "Users first names doesn't match: ");
        softAssert.assertEquals(actualUser.getLastName(), newUsersData.getLastName(), "Users last names doesn't match ");
        softAssert.assertEquals(actualUser.getCountry(), newUsersData.getCountry(), "Users countries doesn't match ");
        softAssert.assertEquals(actualUser.getCity(), newUsersData.getCity(), "Users cities doesn't match ");
        softAssert.assertEquals(actualUser.getState(), newUsersData.getState(), "Users states doesn't match ");
        softAssert.assertEquals(actualUser.getZipCode(), newUsersData.getZipCode(), "Users post codes doesn't match ");
        softAssert.assertEquals(actualUser.getCompany(), newUsersData.getCompany(), "Users companies doesn't match ");
        softAssert.assertEquals(actualUser.getAddress(), newUsersData.getAddress(), "Users addresses doesn't match ");
        softAssert.assertEquals(actualUser.getAddress2(), newUsersData.getAddress2(), "Users addressess2 doesn't match ");
        softAssert.assertEquals(actualUser.getHomePhone(), newUsersData.getHomePhone(), "Users home phones doesn't match ");
        softAssert.assertEquals(actualUser.getMobilePhone(), newUsersData.getMobilePhone(), "Users mobile phones doesn't match ");

        softAssert.assertAll();
    }
}