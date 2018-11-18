package com.org.MavenTests;

import POM.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

public class TestBase {

    private static Logger logger = Logger.getLogger(TestBase.class);;//LoggerFactory.getLogger(RegPageTest.class);
    public WebDriver driver;
    protected SoftAssert softAssertion= new SoftAssert();

    @BeforeSuite
    public void initionalBrowser() {
        //System.setProperty("webdriver.gecko.driver", BasePage.DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver",BasePage.DRIVER_PATH);
        driver = new ChromeDriver();
        logger.info("Test start!");
    }

    @AfterSuite
    public void closeBrowser() {
        //driver.close();
        //driver.quit();
        logger.info("Test end!");
    }
}
