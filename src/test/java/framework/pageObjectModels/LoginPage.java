package framework.pageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class LoginPage extends BasePage{

    public static final String REPEATED_EMAIL_MESSAGE = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    public static final String INVALID_EMAIL_MESSAGE = "Invalid email address.";
    private final String AuthenticationPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

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

    public void submitButtonClick(){
        submitButton.click();
    }

    public void loginButtonClick(){
        loginButton.click();
    }

    public void inputEmail(String email){
        emailTextbox.sendKeys(email);
    }

    public void inputPassword(String password){
        passwordTextbox.sendKeys(password);
    }

    public void inputNewEmail(final String email){emailCreateTextbox.sendKeys(email);}

    public void createAccountButtonClick(){createAccountButton.click();}

    public LoginPage(WebDriver driver) {
        super(driver);
        BasePage.navigate(driver, AuthenticationPageURL);
    }

    public String getEmailAlertMessage(){
       return alertMessage.getText();
    }

    public void waitForAlertMessage(WebDriver driver){
        Pattern pattern = Pattern.compile("1.*");
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement message = wait.until(ExpectedConditions.visibilityOf(alertMessage));
    }
}