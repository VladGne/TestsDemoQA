package tests;

import framework.helperClasses.FileReader;
import framework.pageObjectModels.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

@Listeners(framework.helperClasses.Listener.class)

public class TestBase {

    //ITestContext context;
    SoftAssert softAssert = new SoftAssert();
    protected Logger logger = LogManager.getLogger(this);
    HashMap<String,String> parameters;
    WebDriver driver;
    FileReader fileReader;
    int maxFieldLength = 1000;

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
        fileReader.processDataFile( parameters.get( "Data" ) );
       // BasePage basePage = new BasePage(driver);
        testContext.setAttribute("WebDriver", driver);
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }

//    @BeforeMethod
//    @Parameters({"email","password","login"})
//    public void doLogin(Boolean login, String email, String password){
//
//        if (login){
//
//        }
//    }
}