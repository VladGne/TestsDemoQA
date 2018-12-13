package tests;

import framework.helperClasses.FileReader;
import framework.pageObjectModels.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class TestBase {

    SoftAssert softAssert = new SoftAssert();
    protected Logger logger = LogManager.getLogger(this);
    HashMap<String,String> parameters;
    WebDriver driver;
    FileReader fileReader;

    @BeforeSuite(groups = {"regression", "contentGenerator", "verifier","updater" })
    public void initializeBrowser() {
        logger.info("Browser Initialization");
        //System.setProperty("webdriver.gecko.driver", BasePage.DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver",BasePage.DRIVER_PATH);
    }

    @AfterSuite(groups = {"regression", "verifier","updater", "contentGenerator"})
    public void disableDriver() {
        driver.quit();
    }

    @BeforeClass(groups = {"regression", "contentGenerator", "verifier","updater"})
    public void openBasePage(ITestContext testContext){
        logger.info("Open main page");
        driver = new ChromeDriver();
        parameters = new HashMap<String, String>(testContext.getCurrentXmlTest().getAllParameters());
        fileReader = new FileReader();
       // BasePage basePage = new BasePage(driver);
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}