package POM;

import Models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RegistrationPage extends BasePage {

    // id

    public static final String REGISTRATION_FORM_LOCATOR = "email_create";
    public static final String MALE_BUTTON_LOCATOR = "id_gender1";
    public static final String FEMALE_BUTTON_LOCATOR = "id_gender2";
    public static final String CUSTOMER_FISTNAME_TEXTBOX_LOCATOR = "customer_firstname";
    public static final String CUSTOMER_LASTNAME_TEXTBOX_LOCATOR = "customer_lastname";
    public static final String EMAIL_TEXTBOX_LOCATOR = "email";
    public static final String PASSWORD_TEXTBOX_LOCATOR = "passwd";
    public static final String DAY_LOCATOR = "days";
    public static final String MONTH_LOCATOR = "months";
    public static final String YEAR_LOCATOR = "years";
    public static final String NEWSLETTER_LOCATOR = "newsletter";
    public static final String OFFERS_LOCATOR = "optin";

    public static final String FISTNAME_TEXTBOX_LOCATOR = "firstname";
    public static final String LASTNAME_TEXTBOX_LOCATOR = "lastname";
    public static final String COMPANY_TEXTBOX_LOCATOR = "company";
    public static final String ADDRESS_TEXTBOX_LOCATOR = "address1";
    public static final String ADDRESS2_TEXTBOX_LOCATOR = "address2";
    public static final String CITY_TEXTBOX_LOCATOR = "city";
    public static final String STATE_TEXTBOX_LOCATOR = "id_state";
    public static final String ZIPCODE_TEXTBOX_LOCATOR = "postcode";
    public static final String COUNTRY_TEXTBOX_LOCATOR = "id_country";
    public static final String ADDITIONAL_INFORMATION_TEXTBOX_LOCATOR = "other";
    public static final String HOME_PHONE_TEXTBOX_LOCATOR = "phone";
    public static final String MOBILE_PHONE_TEXTBOX_LOCATOR = "phone_mobile";
    public static final String ALIAS_TEXTBOX_LOCATOR = "alias";
    public static final String REGISTER_BUTTON_LOCATOR = "submitAccount";

    public static final String ERROR_MESSAGE_LOCATOR = "create_account_error";

    //Alert texts
    public static final String MAX_LAST_NAME_MESSAGE = "lastname is too long. Maximum length: 32";
    public static final String MAX_FIRST_NAME_MESSAGE = "firstname is too long. Maximum length: 32";
    public static final String MAX_PASSWORD_MESSAGE = "passwd is too long. Maximum length: 32";
    public static final String MAX_ALIAS_MESSAGE = "alias is too long. Maximum length: 32";
    public static final String MAX_ADDRESS1_MESSAGE = "address1 is too long. Maximum length: 128";
    public static final String MAX_ADDRESS2_MESSAGE = "address2 is too long. Maximum length: 128";
    public static final String MAX_POSTCODE_MESSAGE = "postcode is too long. Maximum length: 12";
    public static final String MAX_ADDITION_INFO_MESSAGE = "other is too long. Maximum length: 300";
    public static final String MAX_HOME_PHONE_MESSAGE = "phone is too long. Maximum length: 32";
    public static final String MAX_MOBILE_PHONE_MESSAGE = "phone_mobile is too long. Maximum length: 32";

    public static final String INVALID_EMAIL_MESSAGE = "Invalid email address.";
    public static final String INVALID_FIRST_NAME_MESSAGE = "firstname is invalid.";
    public static final String INVALID_LAST_NAME_MESSAGE = "lastname is invalid.";
    public static final String INVALID_PASSWORD_MESSAGE = "passwd is invalid.";
    public static final String INVALID_POSTCODE_MESSAGE = "The Zip/Postal code you've entered is invalid. It must follow this format: 00000";
    public static final String INVALID_STATE_MESSAGE = "This country requires you to choose a State";
    public static final String INVALID_DATE_MESSAGE = "Invalid date of birth";
    public static final String INVALID_COUNTRY_MESSAGE = "Country is invalid";

    @FindBy(id = "email_create")
    private WebElement emailTextbox;

    @FindBy(id = "SubmitCreate")
    private WebElement submitButton;

    @FindBy(id = "id_gender1")
    private WebElement maleCheckBox;

    @FindBy(id = "id_gender2")
    private WebElement femaleCheckBox;

    @FindBy(id = "firstname")
    private WebElement firstNameBox;

    @FindBy(id = "lastname")
    private WebElement lastNameBox;

    @FindBy(id = "company")
    private WebElement companyBox;

    @FindBy(id = "address1")
    private WebElement address1Box;

    @FindBy(id = "address2")
    private WebElement address2Box;

    @FindBy(id = "city")
    private WebElement cityBox;

    @FindBy(id = "id_state")
    private WebElement stateBox;

    @FindBy(id = "postcode")
    private WebElement postcodeBox;

    @FindBy(id = "id_country")
    private WebElement countryBox;

    @FindBy(id = "other")
    private WebElement additionInfoBox;

    @FindBy(id = "phone")
    private WebElement phoneHomeBox;

    @FindBy(id = "phone_mobile")
    private WebElement phoneMobileBox;

    @FindBy(id = "alias")
    private WebElement aliasBox;

    @FindBy(id = "submitAccount")
    private WebElement registerButton;

    @FindBy(id = "passwd")
    private WebElement passwordTextbox;

    @FindBy(id= "newsletter")
    private WebElement newsCheckBox;

    @FindBy(id = "optin")
    private WebElement optionCheckBox;

    @FindBy(id = "days")
    private WebElement daysList;

    @FindBy(id = "months")
    private WebElement monthsList;

    @FindBy(id = "years")
    private WebElement yearsList;

    @FindBy(id = "creation_form")
    private WebElement registrationForm;

    @FindBy(className = "page-subheading")
    private WebElement mainHeader;

    @FindBy(xpath = "//div[@class='alert alert-danger']//ol")
    private List<WebElement> alertList;

    public void selectNews(){newsCheckBox.click();}

    public void selectOptions(){optionCheckBox.click();}

    public void inputPassword(String password){ passwordTextbox.sendKeys(password);}

    public void selectMaleGender(){
        maleCheckBox.click();
    }

    public void selectFemaleGender(){
        femaleCheckBox.click();
    }

    public void inputFirstName(String firstName){
        firstNameBox.sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        lastNameBox.sendKeys(lastName);
    }

    public void inputCompany(String company){
        companyBox.sendKeys(company);
    }

    public void inputAddress1(String address1){
        address1Box.sendKeys(address1);
    }

    public void inputAddress2(String address2){
        address2Box.sendKeys(address2);
    }

    public void inputCity(String city){
        cityBox.sendKeys(city);
    }

    public void inputPostcode(String postcode){
        postcodeBox.sendKeys(postcode);
    }

    public void inputOther(String additionInfo){
        additionInfoBox.sendKeys(additionInfo);
    }

    public void inputHomePhone(String phone){
        phoneHomeBox.sendKeys(phone);
    }

    public void inputMobilePhone(String phone){
        phoneMobileBox.sendKeys(phone);
    }

    public void inputAlias(String alias){
        aliasBox.sendKeys(alias);
    }

    public void selectBirthDay(String day){
        new Select(daysList).selectByValue(day);
    }

    public void selectBirthYear(String year){
        new Select(yearsList).selectByValue(year);
    }

    public void selectBirthMonth(String month){
        new Select(monthsList).selectByValue(month);
    }

    public void waitForRegistrationForm(){

        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement header = wait.until((WebDriver d) -> mainHeader) ;

        while (header.getText().equals("") && !wait.equals(0)){
        }

    }

    public void selectCountry(User.Country country){

        new Select(countryBox).selectByValue(String.valueOf(country.getValue()));
    }

    public void selectState(User.State state){

        new Select(stateBox).selectByValue(String.valueOf(state.getValue()));
    }

    public void registerButtonClick(){
        registerButton.click();
    }

    public void submitEmail(String email){
        emailTextbox.sendKeys(email);
        submitButton.click();
    }

    private RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public static RegistrationPage open(WebDriver driver) {
        final String AuthenticationPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.navigate().to(AuthenticationPageURL);
        return new RegistrationPage(driver);
    }

    public void checkInvalidAlerts(){

        if(!alertList.get(0).getText().equals(INVALID_LAST_NAME_MESSAGE))
            softAssertion.fail("Last name alert message error: ");
        if(!alertList.get(1).getText().equals(INVALID_FIRST_NAME_MESSAGE))
            softAssertion.fail("First name alert message error: ");
        if(!alertList.get(2).getText().equals(INVALID_PASSWORD_MESSAGE))
            softAssertion.fail("Password alert message error: ");
        if(!alertList.get(3).getText().equals(INVALID_POSTCODE_MESSAGE))
            softAssertion.fail("Postcode alert message error: ");
        if(!alertList.get(4).getText().equals(INVALID_STATE_MESSAGE))
            softAssertion.fail("State alert message error: ");
        if(!alertList.get(5).getText().equals(INVALID_DATE_MESSAGE))
            softAssertion.fail("Date alert message error: ");
    }

    public void checkUpperLimitsAlerts(){
        if(!alertList.get(0).getText().equals(MAX_LAST_NAME_MESSAGE))
            softAssertion.fail("Last name alert message error: ");
		if(!alertList.get(1).getText().equals(MAX_FIRST_NAME_MESSAGE))
			softAssertion.fail("First name alert message error: ");
		if(!alertList.get(2).getText().equals(MAX_PASSWORD_MESSAGE))
			softAssertion.fail("Password alert message error: ");
		if(!alertList.get(3).getText().equals(MAX_ALIAS_MESSAGE))
			softAssertion.fail("Alias alert message error: ");
		if(!alertList.get(4).getText().equals(MAX_ADDRESS1_MESSAGE))
			softAssertion.fail("Address1 alert message error: ");
		if(!alertList.get(5).getText().equals(MAX_ADDRESS2_MESSAGE))
			softAssertion.fail("Address2 alert message error: ");
		if(!alertList.get(5).getText().equals(MAX_POSTCODE_MESSAGE))
			softAssertion.fail("Postcode alert message error: ");
		if(!alertList.get(5).getText().equals(MAX_ADDITION_INFO_MESSAGE))
			softAssertion.fail("Addition info alert message error: ");
		if(!alertList.get(5).getText().equals(MAX_HOME_PHONE_MESSAGE))
			softAssertion.fail("Home phone info alert message error: ");
		if(!alertList.get(5).getText().equals(MAX_MOBILE_PHONE_MESSAGE))
			softAssertion.fail("Mobile phone info alert message error: ");
    }
}
