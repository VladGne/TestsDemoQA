package framework.pages;

import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{

    public static final String REPEATED_EMAIL_MESSAGE = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    public static final String INVALID_EMAIL_MESSAGE = "Invalid email address.";

    @FindBy(id = "email_create")
    private WebElement emailCreateTextbox;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(className = "alert-danger")
    private WebElement alertMessage;

    @FindBy(id = "email")
    private WebElement emailTextbox;

    @FindBy(id = "passwd")
    private WebElement passwordTextbox;

    @FindBy(id = "SubmitLogin")
    private WebElement loginButton;

    @FindBy(id = "SubmitCreate")
    private WebElement submitButton;

    @Step("Try to login by click on submit button")
    public void submitButtonClick(){
        submitButton.click();
    }

    @Step("Go to login page by click to login button")
    public void loginButtonClick(){
        loginButton.click();
    }

    @Step("Input {0} to email field")
    public void inputEmail(String email){
        emailTextbox.sendKeys(email);
    }

    @Step("Input {0} to password field")
    public void inputPassword(String password){
        passwordTextbox.sendKeys(password);
    }

    @Step("Input new email to registration email field")
    public void inputNewEmail(final String email){emailCreateTextbox.sendKeys(email);}

    @Step("Try to create account by click to create account button")
    public void createAccountButtonClick(){createAccountButton.click();}

    public LoginPage(WebDriver driver) {
        super(driver);
        BasePage.navigate(driver, BasePage.LOGIN_PAGE_URL);
    }

    @Step("Get alerts text")
    public String getEmailAlertMessage(){
       return alertMessage.getText();
    }

    @Step("Wait alert with method: {method} step...")
    public void waitForAlertMessage(WebDriver driver){
        Pattern pattern = Pattern.compile("1.*");
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement message = wait.until(ExpectedConditions.visibilityOf(alertMessage));
    }
}