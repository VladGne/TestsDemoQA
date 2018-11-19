package POM;

import Models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddressesPage extends BasePage{


    public static final String COMPANY_LOCATOR = "address_company";
    public static final String ADDRESS1_LOCATOR = "address_address1";
    public static final String ADDRESS2_LOCATOR = "address_address2";
    public static final String HOME_PHONE_LOCATOR = "address_phone";
    public static final String MOBILE_PHONE_LOCATOR = "address_phone_mobile";



    @FindBy(xpath = "//span[@class='address_name']")
    private List<WebElement> fullName;

    @FindBy(className = "building")
    private WebElement addressButton;

    @FindBy(className = "company")
    private WebElement companyField;

    @FindBy(className = "address1")
    private WebElement address1Field;

    @FindBy(className = "address2")
    private WebElement address2Field;

    @FindBy(className = "phone")
    private WebElement homePhoneField;

    @FindBy(className = "phone_mobile")
    private WebElement mobilePhoneField;

    @FindBy(id = "email")
    private WebElement emailTextbox;

    @FindBy(id = "passwd")
    private WebElement passwordTextbox;

    @FindBy(id = "SubmitLogin")
    private WebElement loginButton;

    public void addressButtonClick(){
        addressButton.click();
    }

    public void doLogin(String email, String password){
        emailTextbox.sendKeys(email);
        passwordTextbox.sendKeys(password);
        loginButton.click();
    }

    public boolean checkFullName(String expectedFirstName, String expectedSecondName){
       String firstName = fullName.get(0).getText();
       String lastName = fullName.get(1).getText();

       if (! firstName.equals(expectedFirstName))
           return false;
       if (lastName.equals(expectedSecondName))
           return false;

        return true;
    }

    public boolean checkCompany(String expectedCompany){
        return companyField.getText().equals(expectedCompany);
    }

    public boolean checkAdderss1(String expectedAddress1){
        return address1Field.getText().equals(expectedAddress1);
    }

    public boolean checkAddress2(String expectedAddress2){
        return address2Field.getText().equals(expectedAddress2);
    }

    public boolean checkHomePhone(String expectedPhone){
        return homePhoneField.getText().equals(expectedPhone);
    }

    public boolean checkMobilePhone(String expectedPhone){
        return mobilePhoneField.getText().equals(expectedPhone);
    }


    public AddressesPage(WebDriver driver) {
        super(driver);
    }
}
