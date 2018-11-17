package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.awt.*;

public class AuthenticationPage extends BasePage{

    public static final String REPEATED_EMAIL_MESSAGE = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    public static final String INVALID_EMAIL_MESSAGE = "Invalid email address.";

    @FindBy(id = "email_create")
    private WebElement emailCreateTextbox;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;

    public void openRegistrationForm(final String email){
        emailCreateTextbox.sendKeys(email);
        createAccountButton.click();
    }

    @FindBy(id = "email")
    private WebElement emailTextbox;

    @FindBy(id = "passwd")
    private WebElement passwordTextbox;

    @FindBy(id = "SubmitLogin")
    private WebElement loginButton;


    public void doLogin(String email, String password){
        emailTextbox.sendKeys(email);
        passwordTextbox.sendKeys(password);
        loginButton.click();
    }

    public void init(final WebDriver driver){PageFactory.initElements(driver, this);}

    private AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    public static AuthenticationPage open(WebDriver driver) {
        final String AuthenticationPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.navigate().to(AuthenticationPageURL);
        return new AuthenticationPage(driver);
    }
}

