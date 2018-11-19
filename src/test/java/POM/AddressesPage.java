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

    @FindBy(xpath = "//ul[@class='last_item item box']//li[5]")
    private List<WebElement> addressesField;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[5]/span[1]")
    private WebElement city;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[5]/span[2]")
    private WebElement state;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[5]/span[3]")
    private WebElement postCode;

    @FindBy(xpath = "//ul[@class='last_item item box']//li[6]")
    private WebElement countryField;

    @FindBy(className = "icon-building")
    private WebElement addressButton;

    @FindBy(className = "address_company")
    private WebElement companyField;

    @FindBy(className = "address_address1")
    private WebElement address1Field;

    @FindBy(className = "address_address2")
    private WebElement address2Field;

    @FindBy(className = "address_phone")
    private WebElement homePhoneField;

    @FindBy(className = "address_phone_mobile")
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

    public void checkFullName(String expectedFirstName, String expectedSecondName){
       String firstName = fullName.get(0).getText();
       String lastName = fullName.get(1).getText();

       if(!firstName.equals(expectedFirstName))
           softAssertion.fail("First name doesn't match");

       if(!lastName.equals(expectedSecondName))
           softAssertion.fail("Last name doesn't match");
    }

    public void checkCountry(User.Country expectedCountry){

        if(countryField.getText().equals(expectedCountry.toString()))
            softAssertion.fail("Country doesn't match");
    }

    public void checkCity(String expectedCity){
        String[] currentCity = city.getText().split(",");
        if (!currentCity[0].equals(expectedCity))
            softAssertion.fail("City doesn't match");
    }

    public void checkState(User.State expectedState){
        if (!state.getText().equals(expectedState.toString()))
            softAssertion.fail("State doesn't match");
    }

    public void checkPostCode(String expectedPostcode){
        if (postCode.getText().equals(expectedPostcode))
            softAssertion.fail("Postcode doesn't match");
    }

    public void checkCompany(String expectedCompany){
        if(companyField.getText().equals(expectedCompany))
            softAssertion.fail("Company doesn't match");
    }

    public void checkAdderss1(String expectedAddress1){
        if(address1Field.getText().equals(expectedAddress1))
           softAssertion.fail("Address1 doesn't match");
    }

    public void checkAddress2(String expectedAddress2){
        if(address2Field.getText().equals(expectedAddress2))
            softAssertion.fail("Address2 doesn't match");
    }

    public void checkHomePhone(String expectedPhone){
        if(homePhoneField.getText().equals(expectedPhone))
            softAssertion.fail("Home phone doesn't match");
    }

    public void checkMobilePhone(String expectedPhone){
        if(mobilePhoneField.getText().equals(expectedPhone))
            softAssertion.fail("Mobile phone doesn't match");
    }


    public AddressesPage(WebDriver driver) {
        super(driver);
    }

    public static AddressesPage open(WebDriver driver) {
        final String LoginPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.navigate().to(LoginPageURL);
        return new AddressesPage(driver);
    }
}
