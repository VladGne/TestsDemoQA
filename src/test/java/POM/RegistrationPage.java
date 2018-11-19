package POM;

import Models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import java.util.List;


public class RegistrationPage extends BasePage {

    //Alert texts
    private static final String MAX_LAST_NAME_MESSAGE = "lastname is too long. Maximum length: 32";
    private static final String MAX_FIRST_NAME_MESSAGE = "firstname is too long. Maximum length: 32";
    private static final String MAX_PASSWORD_MESSAGE = "passwd is too long. Maximum length: 32";
    private static final String MAX_ALIAS_MESSAGE = "alias is too long. Maximum length: 32";
    private static final String MAX_ADDRESS1_MESSAGE = "address1 is too long. Maximum length: 128";
    private static final String MAX_ADDRESS2_MESSAGE = "address2 is too long. Maximum length: 128";
    private static final String MAX_POSTCODE_MESSAGE = "postcode is too long. Maximum length: 12";
    private static final String MAX_ADDITION_INFO_MESSAGE = "other is too long. Maximum length: 300";
    private static final String MAX_HOME_PHONE_MESSAGE = "phone is too long. Maximum length: 32";
    private static final String MAX_MOBILE_PHONE_MESSAGE = "phone_mobile is too long. Maximum length: 32";

    public static final String INVALID_EMAIL_MESSAGE = "Invalid email address.";
    private static final String INVALID_FIRST_NAME_MESSAGE = "firstname is invalid.";
    private static final String INVALID_LAST_NAME_MESSAGE = "lastname is invalid.";
    private static final String INVALID_PASSWORD_MESSAGE = "passwd is invalid.";
    private static final String INVALID_POSTCODE_MESSAGE = "The Zip/Postal code you've entered is invalid. It must follow this format: 00000";
    private static final String INVALID_STATE_MESSAGE = "This country requires you to choose a State";
    private static final String INVALID_DATE_MESSAGE = "Invalid date of birth";
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

    @FindBy(xpath = "//div[@class='alert alert-danger']//ol") ////div[@class='alert alert-danger']//ol
    private WebElement alertList;

    private void selectNews(){newsCheckBox.click();}

    private void selectOptions(){optionCheckBox.click();}

    private void inputPassword(String password){ passwordTextbox.sendKeys(password);}

    private void selectMaleGender(){
        maleCheckBox.click();
    }

    public void selectFemaleGender(){
        femaleCheckBox.click();
    }

    private void inputFirstName(String firstName){
        firstNameBox.sendKeys(firstName);
    }

    private void inputLastName(String lastName){
        lastNameBox.sendKeys(lastName);
    }

    private void inputCompany(String company){
        companyBox.sendKeys(company);
    }

    private void inputAddress1(String address1){
        address1Box.sendKeys(address1);
    }

    private void inputAddress2(String address2){
        address2Box.sendKeys(address2);
    }

    private void inputCity(String city){
        cityBox.sendKeys(city);
    }

    private void inputPostcode(String postcode){
        postcodeBox.sendKeys(postcode);
    }

    private void inputOther(String additionInfo){
        additionInfoBox.sendKeys(additionInfo);
    }

    private void inputHomePhone(String phone){
        phoneHomeBox.sendKeys(phone);
    }

    private void inputMobilePhone(String phone){
        phoneMobileBox.sendKeys(phone);
    }

    private void inputAlias(String alias){
        aliasBox.sendKeys(alias);
    }

    private void selectBirthDay(String day){
        new Select(daysList).selectByValue(day);
    }

    private void selectBirthYear(String year){
        new Select(yearsList).selectByValue(year);
    }

    private void selectBirthMonth(User.MonthBirth month){
        new Select(monthsList).selectByValue(Integer.toString(month.getValue()));
    }

    public void waitForRegistrationForm(){

        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement header = wait.until((WebDriver d) -> maleCheckBox);

    }

    private void selectCountry(User.Country country){
        new Select(countryBox).selectByValue(String.valueOf(country.getValue()));
    }

    private void selectState(User.State state){
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

        String[] alertMessage = alertList.getText().split("\n");

        if(!alertMessage[0].equals(INVALID_LAST_NAME_MESSAGE))
            softAssertion.fail("Last name alert message error: ");
        if(!alertMessage[1].equals(INVALID_FIRST_NAME_MESSAGE))
            softAssertion.fail("First name alert message error: ");
        if(!alertMessage[2].equals(INVALID_PASSWORD_MESSAGE))
            softAssertion.fail("Password alert message error: ");
        if(!alertMessage[3].equals(INVALID_POSTCODE_MESSAGE))
            softAssertion.fail("Postcode alert message error: ");
//        if(!alertMessage[4].equals(INVALID_STATE_MESSAGE))
//            softAssertion.fail("State alert message error: ");
        if(!alertMessage[4].equals(INVALID_DATE_MESSAGE))
            softAssertion.fail("Date alert message error: ");
    }

    public void checkUpperLimitsAlerts(){

        String[] alertMessage = alertList.getText().split("\n");

        if(!alertMessage[0].equals(MAX_LAST_NAME_MESSAGE))
            softAssertion.fail("Last name alert message error: ");
		if(!alertMessage[1].equals(MAX_FIRST_NAME_MESSAGE))
			softAssertion.fail("First name alert message error: ");
		if(!alertMessage[2].equals(MAX_PASSWORD_MESSAGE))
			softAssertion.fail("Password alert message error: ");
		if(!alertMessage[3].equals(MAX_ALIAS_MESSAGE))
			softAssertion.fail("Alias alert message error: ");
		if(!alertMessage[4].equals(MAX_ADDRESS1_MESSAGE))
			softAssertion.fail("Address1 alert message error: ");
		if(!alertMessage[5].equals(MAX_ADDRESS2_MESSAGE))
			softAssertion.fail("Address2 alert message error: ");
		if(!alertMessage[6].equals(MAX_POSTCODE_MESSAGE))
			softAssertion.fail("Postcode alert message error: ");
		if(!alertMessage[7].equals(MAX_ADDITION_INFO_MESSAGE))
			softAssertion.fail("Addition info alert message error: ");
		if(!alertMessage[8].equals(MAX_HOME_PHONE_MESSAGE))
			softAssertion.fail("Home phone info alert message error: ");
		if(!alertMessage[9].equals(MAX_MOBILE_PHONE_MESSAGE))
			softAssertion.fail("Mobile phone info alert message error: ");
    }

    public void fillRegistrationForm(User user, Logger logger){
        logger.info("Select gender");
        selectMaleGender();

        logger.info("Input first name");
        inputFirstName(user.getFistName());

        logger.info("Input last name");
        inputLastName(user.getLastName());

        logger.info("Input password");
        inputPassword(user.getPassword());

        logger.info("Input company");
        inputCompany(user.getCompany());

        logger.info("Input address1");
        inputAddress1(user.getAddress());

        logger.info("Input address2");
        inputAddress2(user.getAddress2());

        logger.info("Input city");
        inputCity(user.getCity());

        logger.info("Input country");
        selectCountry(user.getCountry());

        logger.info("Input state");
        selectState(user.getState());

        logger.info("Input post code");
        inputPostcode(user.getZipCode());

        logger.info("Input addition information");
        inputOther(user.getAdditionInformation());

        logger.info("Input home phone");
        inputHomePhone(user.getHomePhone());

        logger.info("Input mobile phone");
        inputMobilePhone(user.getMobilePhone());

        logger.info("Input alias");
        inputAlias(user.getAddressAlias());

        logger.info("Select news");
        selectNews();

        logger.info("Select options");
        selectOptions();

        logger.info("Select day of birth");
        selectBirthDay(user.getDayBirth());

        logger.info("Select month of birth");
        selectBirthMonth(user.getMonthBirth());

        logger.info("Select year of birth");
        selectBirthYear(user.getYearBirth());
    }
}
