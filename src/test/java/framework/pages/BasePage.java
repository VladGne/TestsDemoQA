package framework.pages;

import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import lombok.SneakyThrows;

import static framework.helpers.FileReader.readProperties;

public class BasePage {

    static final String LOGIN_PAGE_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    private static Properties prop = readProperties();
    static int waiterTime = 10;
    public static final String DRIVER_PATH = "src\\test\\resources\\chromedriver.exe";
    Logger logger = LogManager.getLogger(this);

    @FindBy(className = "logout")
    private WebElement logoutButton;

    @FindBy(className = "alert-danger")
    private WebElement alertList;

    @FindBy(className = "//ul[@class='footer_links clearfix']//li[1]")
    private WebElement backToAccountButton;

    @FindBy(className = "header_user_info")
    private WebElement userProfileButton;

    @FindBy(linkText = "T-SHIRTS")
    private WebElement tShirtButton;

    @FindBy(className = "sf-with-ul")
    WebElement womenButton;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        final String BasePageURL = prop.getProperty("durl");
        //driver.navigate().to(BasePageURL);
    }

    public BasePage() {
    }

    public void backToAccountButtonClick() {
        backToAccountButton.click();
    }

    public void userProfileButtonClick() {
        userProfileButton.click();
    }

    public void womenButtonClick() {
        womenButton.click();
    }

    public void waitSuccessfulMessage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identity")));
    }

    public void waitEmailBox(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
    }

    public void doLogin(String email, String password, WebDriver driver) {
        logger.info("Login");
        waitEmailBox(driver);
        LoginPage loginPage = new LoginPage(driver);
        logger.info("Enter to Email Field - " + email );
        loginPage.inputEmail(email);
        logger.info("Enter to Password Field" + password );
        loginPage.inputPassword(password);
        logger.info("Click Login button");
        loginPage.loginButtonClick();
    }

    public void doLogout() {
        logger.info("Logout");
        logoutButton.click();
    }

    static void navigate(WebDriver driver, String url) {
        driver.navigate().to(url);
    }

    public void tShirtButtonClick() {
        tShirtButton.click();
    }

    @SneakyThrows
    public void checkPageElementsVisibility(Class className, SoftAssert softAssert) {
        for (Field field : className.getDeclaredFields()) {
            if (field.getType() == WebElement.class) {
                field.setAccessible(true);
                WebElement element = (WebElement) field.get(this);
                logger.debug("Check interactivity of " + field.getName());
                if (!element.isEnabled())
                    softAssert.fail("Element isn't enabled - " + field.getName());
            }
        }
    }

    public String[] getAlertList() {
        return alertList.findElement(By.tagName("ol")).getText().split("\n");
    }

    static class AccountPage {

        @FindBy(className = "icon-building")
        private WebElement addressButton;

        @FindBy(className = "icon-user")
        private WebElement personalInfoButton;

        AccountPage(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }

        void addressButtonClick() {
            addressButton.click();
        }

        void personalInfoButtonClick() {
            personalInfoButton.click();
        }
    }
}