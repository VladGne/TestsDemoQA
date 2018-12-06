package POM;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationPage extends BasePage{

    public static final String REPEATED_EMAIL_MESSAGE = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    public static final String INVALID_EMAIL_MESSAGE = "Invalid email address.";

    @FindBy(id = "email_create")
    private WebElement emailCreateTextbox;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;

    public void inputNewEmail(final String email){emailCreateTextbox.sendKeys(email);}

    public void createAccountButtonClick(){createAccountButton.click();}

    public void checkRepeatedEmailAlertMessage(){
        if(!alertMessage.getText().equals(REPEATED_EMAIL_MESSAGE));
            //softAssertion.fail("Alert message validation fail: ");
    }

    public String getEmailAlertMessage(){
        return alertMessage.getText();
    }
    public boolean checkInvalidEmailAlertMessage(){

        return alertMessage.getText().equals(INVALID_EMAIL_MESSAGE);
    }

    private AuthenticationPage(WebDriver driver) {
        super(driver);
    }

   static public AuthenticationPage open() {
        final String AuthenticationPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.navigate().to(AuthenticationPageURL);
        return new AuthenticationPage(driver);
    }
}

