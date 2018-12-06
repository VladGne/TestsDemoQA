package com.org.MavenTests;

import POM.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class TestBase {

    protected Logger logger = LogManager.getLogger(this);
    private WebDriver driver;

    @BeforeSuite
    public void initionalBrowser() {
        logger.info("Browser Initialization");
        //System.setProperty("webdriver.gecko.driver", BasePage.DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver",BasePage.DRIVER_PATH);
        driver = new ChromeDriver();
    }

    @AfterSuite
    public void closeBrowser() {
        //driver.quit();
    }

    @BeforeTest
    public void openBasePage(){
        logger.info("Open main page");
        BasePage.open(driver);
    }

    protected SoftAssert softAssertion= new SoftAssert();
}
