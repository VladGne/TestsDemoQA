package com.org.MavenTests;

import Models.User;
import POM.PersonalPage;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PersonalInfoTests extends TestBase{

    private Logger logger =  Logger.getLogger(PersonalInfoTests.class);

    // Create valid user
    @DataProvider(name = "validUserData")
    public Object[] getValidUserData(){
        User[] validUserData = new User[1];
        validUserData[0] = new User();
        validUserData[0].setEmail("test1@test.com1");
        return validUserData;
    }

    @Test(priority = 0, dataProvider="validUserData")
    public void checkVisibility(User user){

        logger.info("\n --- Check personal info page elements visibility test start ---\n");

        logger.info("Navigate to login page");
        PersonalPage personalPage = PersonalPage.open(driver);

        logger.info("Login");
        personalPage.doLogin(user.getEmail(),user.getPassword());

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo();

        checkPageElementsVisibility(driver);

        logger.info("\n --- Check personal info page elements visibility test end ---\n");
    }

    @Test(priority = 1, dataProvider="validUserData")
    public void checkPersonalInfo(User user){

        logger.info("\n --- Check personal info test start ---\n");

        logger.info("Navigate to login page");
        PersonalPage personalPage = PersonalPage.open(driver);

        logger.info("Login");
        personalPage.doLogin(user.getEmail(),user.getPassword());

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo();

        logger.info("Check personal information form");
        personalPage.checkPersonalInfoForm(user, logger);

        softAssertion.assertAll();
        //profilePage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
    }
}
