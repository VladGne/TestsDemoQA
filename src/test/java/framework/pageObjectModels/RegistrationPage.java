package framework.pageObjectModels;

import framework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class RegistrationPage extends BasePage {

    final String AuthenticationPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

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

    @FindBy(className = "header_user_info")
    private WebElement userProfileButton;

    @FindBy(id = "id_gender1")
    private WebElement maleCheckBox;

    @FindBy(id = "id_gender2")
    private WebElement femaleCheckBox;

    @FindBy(id = "customer_firstname")
    private WebElement firstNameBox;

    @FindBy(id = "customer_lastname")
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

    @FindBy(className = "page-subheading")
    private WebElement mainHeader;

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

    public void waitForRegistrationForm(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_gender1")));
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

    public void submitEmail(String email, WebDriver driver){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputNewEmail(email);
        loginPage.submitButtonClick();
    }

    public RegistrationPage(WebDriver driver) {
       super(driver);
       BasePage.navigate(driver, AuthenticationPageURL);
    }

    public void checkUserButton(){
        if(!userProfileButton.isDisplayed())
            Assert.fail("User profile button doesn't exist");
    }

    public void checkInvalidAlerts(SoftAssert softAssert){

        String[] alertMessage = getAlertList();

        if(!alertMessage[0].equals(INVALID_LAST_NAME_MESSAGE))
            softAssert.fail(String.format("Last name alert message error: expected - %s, actual - %s", INVALID_LAST_NAME_MESSAGE, alertMessage[0]));
        if(!alertMessage[1].equals(INVALID_FIRST_NAME_MESSAGE))
            softAssert.fail(String.format("First name alert message error: expected - %s, actual - %s", INVALID_FIRST_NAME_MESSAGE, alertMessage[1]));
        if(!alertMessage[2].equals(INVALID_PASSWORD_MESSAGE))
            softAssert.fail(String.format("Password alert message error: expected - %s, actual - %s", INVALID_PASSWORD_MESSAGE, alertMessage[2]));
        if(!alertMessage[3].equals(INVALID_POSTCODE_MESSAGE))
            softAssert.fail(String.format("Postcode alert message error: expected - %s, actual - %s",INVALID_POSTCODE_MESSAGE,  alertMessage[3]));
//        if(!alertMessage[4].equals(INVALID_STATE_MESSAGE))
//            softAssertion.fail("State alert message error: ");
        if(!alertMessage[4].equals(INVALID_DATE_MESSAGE))
            softAssert.fail(String.format("Date alert message error: expected - %s, actual - %s", INVALID_DATE_MESSAGE, alertMessage[4]));
    }

    public void checkUpperLimitsAlerts(SoftAssert softAssert){

        String[] alertMessage = getAlertList();

        if(!alertMessage[0].equals(MAX_LAST_NAME_MESSAGE))
            softAssert.fail(String.format("Last name alert message error:  expected - %s, actual - %s", MAX_LAST_NAME_MESSAGE, alertMessage[0]));
		if(!alertMessage[1].equals(MAX_FIRST_NAME_MESSAGE))
            softAssert.fail(String.format("First name alert message error: expected - %s, actual - %s", MAX_FIRST_NAME_MESSAGE, alertMessage[1]));
		if(!alertMessage[2].equals(MAX_PASSWORD_MESSAGE))
            softAssert.fail(String.format("Password alert message error: expected - %s, actual - %s", MAX_PASSWORD_MESSAGE, alertMessage[2]));
		if(!alertMessage[3].equals(MAX_ALIAS_MESSAGE))
            softAssert.fail(String.format("Alias alert message error: expected - %s, actual - %s", MAX_ALIAS_MESSAGE, alertMessage[3]));
		if(!alertMessage[4].equals(MAX_ADDRESS1_MESSAGE))
            softAssert.fail(String.format("Address1 alert message error: expected - %s, actual - %s", MAX_ADDRESS1_MESSAGE, alertMessage[4]));
		if(!alertMessage[5].equals(MAX_ADDRESS2_MESSAGE))
            softAssert.fail(String.format("Address2 alert message error: expected - %s, actual - %s", MAX_ADDRESS2_MESSAGE, alertMessage[5]));
		if(!alertMessage[6].equals(MAX_POSTCODE_MESSAGE))
            softAssert.fail(String.format("Postcode alert message error: expected - %s, actual - %s", MAX_POSTCODE_MESSAGE, alertMessage[6]));
		if(!alertMessage[7].equals(MAX_ADDITION_INFO_MESSAGE))
            softAssert.fail(String.format("Addition info alert message error: expected - %s, actual - %s",MAX_ADDITION_INFO_MESSAGE, alertMessage[7]));
		if(!alertMessage[8].equals(MAX_HOME_PHONE_MESSAGE))
            softAssert.fail(String.format("Home phone info alert message error: expected - %s, actual - %s", MAX_HOME_PHONE_MESSAGE, alertMessage[8]));
		if(!alertMessage[9].equals(MAX_MOBILE_PHONE_MESSAGE))
            softAssert.fail(String.format("Mobile phone info alert message error: expected - %s, actual - %s", MAX_MOBILE_PHONE_MESSAGE, alertMessage[9]));
    }

    public void fillRegistrationForm(User user){
        logger.debug("Select gender");
        selectMaleGender();

        logger.debug("Input first name");
        inputFirstName(user.getFistName());

        logger.debug("Input last name");
        inputLastName(user.getLastName());

        logger.debug("Input password");
        inputPassword(user.getPassword());

        logger.debug("Input company");
        inputCompany(user.getCompany());

        logger.debug("Input address1");
        inputAddress1(user.getAddress());

        logger.debug("Input address2");
        inputAddress2(user.getAddress2());

        logger.debug("Input city");
        inputCity(user.getCity());

        logger.debug("Input country");
        selectCountry(user.getCountry());

        logger.debug("Input post code");
        inputPostcode(user.getZipCode());

        logger.debug("Input addition information");
        inputOther(user.getAdditionInformation());

        logger.debug("Input home phone");
        inputHomePhone(user.getHomePhone());

        logger.debug("Input mobile phone");
        inputMobilePhone(user.getMobilePhone());

        logger.debug("Input alias");
        inputAlias(user.getAddressAlias());

        logger.debug("Select news");
        selectNews();

        logger.debug("Select options");
        selectOptions();

        logger.debug("Select day of birth");
        selectBirthDay(user.getDayBirth());

        logger.debug("Select month of birth");
        selectBirthMonth(user.getMonthBirth());

        logger.debug("Select year of birth");
        selectBirthYear(user.getYearBirth());

        logger.debug("Input state");
        selectState(user.getState());
    }
}
