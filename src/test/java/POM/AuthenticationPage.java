package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.awt.*;

public class AuthenticationPage extends BasePage{

    private static final String REPEATED_EMAIL_MESSAGE = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    private static final String INVALID_EMAIL_MESSAGE = "Invalid email address.";

    @FindBy(id = "email_create")
    private WebElement emailCreateTextbox;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;

    public void inputNewEmail(final String email){
        emailCreateTextbox.sendKeys(email);
        createAccountButton.click();
    }

    public boolean checkRepeatedEmailAlertMessage(){

        return alertMessage.getText().equals(REPEATED_EMAIL_MESSAGE);
    }

    public boolean checkInvalidEmailAlertMessage(){

        return alertMessage.getText().equals(INVALID_EMAIL_MESSAGE);
    }

    private AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    public static AuthenticationPage open(WebDriver driver) {
        final String AuthenticationPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.navigate().to(AuthenticationPageURL);
        return new AuthenticationPage(driver);
    }
}

