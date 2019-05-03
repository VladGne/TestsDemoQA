package tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import framework.helpers.FileReader;
import framework.pages.BasePage;

@Listeners(framework.helpers.Listener.class)

public class TestBase {

    static         Map<String, Object>    context = new HashMap<>();
    private static ThreadLocal<WebDriver> storage = new ThreadLocal<>();

    SoftAssert              softAssert = new SoftAssert();
    HashMap<String, String> parameters;
    WebDriver               driver;
    FileReader              fileReader;
    final     int           maxFieldLength = 1000;
    protected Logger        logger         = LogManager.getLogger( this );

    public WebDriver getDriver() {
        WebDriver current = storage.get();

        if ( null == current ) {
            logger.debug("Driver for the current thread has not been initialized yet. Creating new Driver." );
            System.setProperty("webdriver.chrome.driver",BasePage.DRIVER_PATH);
            current = new ChromeDriver();
            storage.set( current );
        }
        return current;
    }

    @BeforeSuite
    public void initializeBrowser() {
        logger.info("Browser Initialization");
        driver = getDriver();
    }

    @AfterSuite(groups = {"regression", "verifier","updater", "contentGenerator"})
    public void disableDriver() {
        driver.quit();
    }

    @BeforeClass(groups = {"regression", "contentGenerator", "verifier","updater"})
    public void openBasePage(ITestContext testContext){
        logger.info("Open main page");
        parameters = new HashMap<String, String>(testContext.getCurrentXmlTest().getAllParameters());
        fileReader = new FileReader();
        fileReader.processDataFile( parameters.get( "Data" ) );
    }

    @BeforeMethod
    public void beforeMethod(ITestContext testContext){
        this.driver = getDriver();
        testContext.setAttribute("WebDriver", driver);
    }
}