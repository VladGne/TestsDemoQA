package com.org.MavenTests;

import Models.User;
import POM.PersonalPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

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

    @Test(priority = 1, dataProvider="validUserData")
    public void checkPersonalInfo(User user){

        logger.info("\n --- Check personal info test start ---\n");

        logger.info("Navigate to login page");
        PersonalPage personalPage = PersonalPage.open(driver);

        logger.info("Login");
        personalPage.doLogin(user.getEmail(),user.getPassword());

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo();

        logger.info("Check gender");
        if(!personalPage.checkMaleGender()){
            logger.error("Gender validation error");
            softAssertion.fail("Gender validation error");
        }

        logger.info("Check first name");
        if(!personalPage.checkFirstName(user.getFistName())){
            logger.error("First name validation error");
            softAssertion.fail("First name validation error");
        }

        logger.info("Check last name");
        if(!personalPage.checkLastName(user.getLastName())){
            logger.error("Last name validation error");
            softAssertion.fail("Last name validation error");
        }

        logger.info("Check email");
        if(!personalPage.checkEmail(user.getEmail())){
            logger.error("Email validation error");
            softAssertion.fail("Email validation error");
        }

        logger.info("Check birth day");
        if(!personalPage.checkBithDay(user.getDayBirth())){
            logger.error("Birth day validation error");
            softAssertion.fail("Birth day validation error");
        }

        logger.info("Check birth month");
        if(!personalPage.checkBithMonth(user.getMonthBirth())){
            logger.error("Birth month validation error");
            softAssertion.fail("Birth month validation error");
        }

        logger.info("Check birth year");
        if(!personalPage.checkBithYear(user.getYearBirth())){
            logger.error("Birth year validation error");
            softAssertion.fail("Birth year validation error");
        }

        logger.info("Check news");
        if(!personalPage.checkNews()){
            logger.error("News validation error");
            softAssertion.fail("News validation error");
        }

        logger.info("Check options");
        if(!personalPage.checkOptions()){
            logger.error("Option validation error");
            softAssertion.fail("Option validation error");
        }

        softAssertion.assertAll();
        //profilePage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
    }
}
