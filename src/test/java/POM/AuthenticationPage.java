package POM;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class AuthenticationPage extends BasePage{

    public static final String REPEATED_EMAIL_MESSAGE = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    public static final String INVALID_EMAIL_MESSAGE = "Invalid email address.";

    @FindBy(id = "email_create")
    private WebElement emailCreateTextbox;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(className = "alert-danger")
    protected WebElement alertMessage;

    public void inputNewEmail(final String email){emailCreateTextbox.sendKeys(email);}

    public void createAccountButtonClick(){createAccountButton.click();}

    private AuthenticationPage(WebDriver driver) {
        super(driver);
    }

   static public AuthenticationPage open() {
        final String AuthenticationPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.navigate().to(AuthenticationPageURL);
        return new AuthenticationPage(driver);
    }

    public boolean checkAlertMessage(String expectedMessage){

        return alertMessage.getText().equals(expectedMessage);
    }

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

    public void waitForAlertMessage(){
        Pattern pattern = Pattern.compile("1.*");
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement message = wait.until(ExpectedConditions.visibilityOf(alertMessage));
    }
}

