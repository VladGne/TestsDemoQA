package com.org.MavenTests;

import Models.User;
import POM.AddressesPage;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


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
        User[] validUserData = new User[1];
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert JSON string from file to Object
            validUserData[0] = mapper.readValue(new File("src\\test\\resources\\validUser.json"), User.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String existedEmail = "test1@test.com1";
        validUserData[0].setEmail(existedEmail);

        return validUserData;
    }

    @Test(priority = 0, dataProvider="validUserData", groups = "regression")
    public void checkVisibilityMainElements(User user) {

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
}