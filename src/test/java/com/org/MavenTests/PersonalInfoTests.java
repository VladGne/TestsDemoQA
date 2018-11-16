package com.org.MavenTests;

import Models.User;
import POM.BasePage;
import POM.LoginPage;
import POM.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.List;

import static POM.ProfilePage.DAY_LOCATOR;
import static POM.ProfilePage.MONTH_LOCATOR;
import static POM.ProfilePage.YEAR_LOCATOR;

public class PersonalInfoTests extends TestBase{

    WebElement chosenMonth;
    WebElement chosenDay;
    WebElement chosenYear;
    private Logger logger =  Logger.getLogger(PersonalInfoTests.class);

    @Test(priority = 1, dataProvider="validUserData")
    public void checkPrsonalInfo(Object userData) throws Exception{
        User user = (User) userData;

        ProfilePage profilePage = ProfilePage.open(driver); // open login page

        // Login
        profilePage.writeText(By.id(LoginPage.EMAIL_TEXTBOX_LOCATOR), (user.getEmail()));
        profilePage.writeText(By.id(LoginPage.PASSWORD_TEXTBOX_LOCATOR), (user.getPassword()));
        profilePage.click(By.id(LoginPage.LOGIN_BUTTON_LOCATOR));

        softAssertion.fail("Can't login: ");
        logger.error("Can't login: " );

        // Wait for profile form
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until((WebDriver d) -> d.findElement(By.className(ProfilePage.ADDRESSES_BUTTON_LOCATOR)));

        profilePage.click(By.xpath(ProfilePage.USER_BUTTON_LOCATOR));

        softAssertion.fail("Login form load error: " );
        logger.error("Login form load error: ");


        if(!profilePage.checkSelection(By.id(ProfilePage.MALE_BUTTON_LOCATOR))) {
            logger.error("gender do not match");
            softAssertion.fail("gender do not match" );
        }

        //String txt = profilePage.readText(By.id(ProfilePage.FISTNAME_TEXTBOX_LOCATOR));
        if(!profilePage.getValue(By.id(ProfilePage.FISTNAME_TEXTBOX_LOCATOR)).equals(user.getFistName())) { // Doesn't read name
            logger.error("fist name do not match");
            softAssertion.fail("fist name do not match" );
        }

        if(!profilePage.getValue(By.id(ProfilePage.LASTNAME_TEXTBOX_LOCATOR)).equals(user.getLastName())) {
            logger.error("last name do not match");
            softAssertion.fail("last name do not match");
        }

        if(!profilePage.getValue(By.id(ProfilePage.EMAIL_TEXTBOX_LOCATOR)).equals(user.getEmail())) {
            logger.error("email do not match");
            softAssertion.fail("email do not match");
        }

        //new Select(driver.findElement(By.id(DAY_LOCATOR))).getAllSelectedOptions();

        //Check day

        List<WebElement> days = new Select(driver.findElement(By.id(DAY_LOCATOR))).getAllSelectedOptions();
        for (WebElement day : days)
            if (day.isSelected())
                chosenDay = day;

        softAssertion.fail("Days list load error: ");
        logger.error("Days list load error: ");


        String[] day = chosenDay.getText().split(" ");
        if(!day[0].equals(user.getDayBirth())) {
            logger.error("day of birth do not match");
            softAssertion.fail("day of birth do not match" );
        }

        //Check month

        List<WebElement> months = new Select(driver.findElement(By.id(MONTH_LOCATOR))).getAllSelectedOptions();
        for (WebElement month : months)
            if (month.isSelected())
                chosenMonth = month;

        softAssertion.fail("Month list load error: ");
        logger.error("Month list load error: ");


        String[] month = chosenMonth.getText().split(" ");
        if(!month[0].equals(user.getMonthBirth().toString())) {
            logger.error("Year of birth do not match");
            softAssertion.fail("Year of birth do not match");
        }

        //Check year

        List<WebElement> years = new Select(driver.findElement(By.id(YEAR_LOCATOR))).getAllSelectedOptions();
        for (WebElement year : years)
            if (year.isSelected())
                chosenYear = year;

        softAssertion.fail("Month list load error: ");
        logger.error("Month list load error: ");


        String[] year = chosenYear.getText().split(" ");
        if(!year.equals(user.getYearBirth())) {
            logger.error("Year of birth do not match");
            softAssertion.fail("Year of birth do not match");
        }

        if (!driver.findElement(By.id(LoginPage.NEWSLETTER_LOCATOR)).isSelected()){
            logger.error("Newsletter checkbox is'nt selected");
            softAssertion.fail("Newsletter checkbox is'nt selected");
        }

        if (!driver.findElement(By.id(LoginPage.OFFERS_LOCATOR)).isSelected()){
            logger.error("Newsletter checkbox is'nt selected");
            softAssertion.fail("Newsletter checkbox is'nt selected");
        }

        softAssertion.assertAll();
        //profilePage.click(By.className(BasePage.LOGIN_BUTTON_LOCATOR));
    }
}
