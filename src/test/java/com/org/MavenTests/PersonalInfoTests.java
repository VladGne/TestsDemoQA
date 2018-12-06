package com.org.MavenTests;

import Models.User;
import POM.PersonalPage;

import org.testng.annotations.*;

public class PersonalInfoTests extends TestBase{


    private PersonalPage personalPage;

    // Create valid user
    @DataProvider(name = "validUserData")
    public Object[] getValidUserData(){
        User[] validUserData = new User[1];
        validUserData[0] = new User();
        validUserData[0].setEmail("test1@test.com1");
        return validUserData;
    }

    @BeforeTest
    public void initialBrowser(){
        logger.info("Navigate to login page");
//        personalPage = PersonalPage.open(driver);
    }

    @AfterTest
    public void closeBrowser(){
        logger.info("Close browser");
        personalPage.closeBrowser();
    }

    @Test(priority = 0, dataProvider="validUserData")
    public void checkVisibility(User user){

        logger.info("Check personal info page elements visibility test start\n");

        logger.info("Login");
        personalPage.doLogin(user.getEmail(),user.getPassword());

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo();

        logger.info("Check elements visibility");
       // personalPage.checkElementsVisibility(this.getClass());

        logger.info("\n --- Check personal info page elements visibility test end ---\n");
    }

    @Test(priority = 1, dataProvider="validUserData")
    public void checkPersonalInfo(User user){

        logger.info("\n --- Check personal info test start ---\n");

       //logger.info("Login");
       //personalPage.doLogin(user.getEmail(),user.getPassword());

       //logger.info("Navigate to personal page");
       //personalPage.navigateToPersonalInfo();

        logger.info("Check personal information form");
        personalPage.checkPersonalInfoForm(user, logger);

        //profilePage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
    }


}
