package com.org.MavenTests;

import Models.User;
import POM.AddressesPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.List;

public class AddressesTests extends TestBase {

    private AddressesPage addressesPage;

    @DataProvider(name = "validUserData")
    public Object[] getValidUserData(){
        final String email = "test1@test.com1";								//test1@test.com1 - for login
        User[] validUserData = new User[1];
        validUserData[0] = new User();
        validUserData[0].setEmail(email);
        return validUserData;
    }

    @BeforeMethod
    public void openLoginPage(Method method){
        logger.info("Navigate to login page");
        addressesPage = AddressesPage.open();
        logger.info(method.getName() + " start");
    }

    @AfterMethod
    public void doLogout(){
        logger.info("Logout");
        addressesPage.doLogout();
    }
    @Test(priority = 0, dataProvider="validUserData")
    public void checkVisibilityMainElements(User user) {

        logger.info("Login");
        addressesPage.doLogin(user.getEmail(),user.getPassword());

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick();

        logger.info("Check elements visibility");
        //addressesPage.checkElementsVisibility(this.getClass());
        List<WebElement> elements = addressesPage.getMainElements(this.getClass());

        Assert.assertEquals(elements, addressesPage.getVisibleElementsFromList(elements));

        logger.info("\n --- Addresses test end ---\n");
    }

    @Test(priority = 1, dataProvider="validUserData")
    public void checkAddresses(User user) {
        SoftAssert softAssert = new SoftAssert();

        logger.info("Login");
        addressesPage.doLogin(user.getEmail(),user.getPassword());

        logger.info("Navigate to addresses page");
        addressesPage.addressButtonClick();

        logger.info("Check address form");
        //addressesPage.checkForm(user);
        User actualUser = addressesPage.getActualUserData();
        softAssert.assertEquals(actualUser.getFistName(), user.getFistName());
        softAssert.assertEquals(actualUser.getLastName(), user.getLastName());
        softAssert.assertEquals(actualUser.getCountry(), user.getCountry());
        softAssert.assertEquals(actualUser.getCity(), user.getCity());
        softAssert.assertEquals(actualUser.getState(), user.getState());
        softAssert.assertEquals(actualUser.getZipCode(), user.getZipCode());
        softAssert.assertEquals(actualUser.getCompany(), user.getCompany());
        softAssert.assertEquals(actualUser.getAddress(), user.getAddress());
        softAssert.assertEquals(actualUser.getAddress2(), user.getAddress2());
        softAssert.assertEquals(actualUser.getHomePhone(), user.getHomePhone());
        softAssert.assertEquals(actualUser.getMobilePhone(), user.getMobilePhone());

        softAssert.assertAll();
        logger.info("\n --- Addresses test end ---\n");
    }
}