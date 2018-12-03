package com.org.MavenTests;

import POM.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.LinkedList;
import java.util.List;

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

    private List<WebElement> getAllElements(WebDriver driver){
        List<WebElement> allElementsList = new LinkedList<>(driver.findElements(By.cssSelector("*")));
        return allElementsList;
    }

    private void checkVisibility(List<WebElement> webElementsList){

        List<WebElement> hiddenElements = new LinkedList<>();

        for (WebElement element: webElementsList)
            if (!element.isDisplayed())
                hiddenElements.add(element);

        if (hiddenElements.size()>0){
            logger.error("Number of hidden elements: " + hiddenElements.size());
            for (WebElement element: webElementsList)
                logger.error("Hidden element: " + element.getText());
            Assert.fail();
        }

        else {
            logger.info("Number of hidden elements: 0");
        }
    }

    public void checkPageElementsVisibility(WebDriver driver){
        List<WebElement> pageElements = getAllElements(driver);
        checkVisibility(pageElements);
    }
}
