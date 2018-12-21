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
import java.util.Map;

@Listeners(framework.helperClasses.Listener.class)

public class TestBase {

    static Map<String, Object> context = new HashMap<>();
    private static ThreadLocal<WebDriver> storage = new ThreadLocal<>();

    SoftAssert softAssert = new SoftAssert();
    protected Logger logger = LogManager.getLogger(this);
    HashMap<String,String> parameters;
    WebDriver driver;
    FileReader fileReader;
    int maxFieldLength = 1000;

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