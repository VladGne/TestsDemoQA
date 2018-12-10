package tests;

import framework.pageObjectModels.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class TestBase {

    SoftAssert softAssert = new SoftAssert();
    protected Logger logger = LogManager.getLogger(this);

    WebDriver driver;

    @BeforeSuite(groups = "regression")
    public void initializeBrowser(ITestContext testContext) {
        logger.info("Browser Initialization");
        //System.setProperty("webdriver.gecko.driver", BasePage.DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver",BasePage.DRIVER_PATH);

        HashMap<String,String> parameters = new HashMap<String, String>(testContext.getCurrentXmlTest().getAllParameters());
    }

    @AfterSuite(groups = "regression")
    public void closeBrowser() {
        //driver.quit();
    }

    @BeforeClass(groups = "regression")
    public void openBasePage(){
        logger.info("Open main page");
        driver = new ChromeDriver();
       // BasePage basePage = new BasePage(driver);
    }
}
