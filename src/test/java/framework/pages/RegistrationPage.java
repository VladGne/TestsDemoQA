package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import framework.models.User;
import io.qameta.allure.Step;

public class RegistrationPage extends BasePage {

    //Alerts text
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

    public void inputFirstName(String firstName){
        firstNameBox.sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        lastNameBox.sendKeys(lastName);
    }

    public void inputCompany(String company){
        companyBox.sendKeys(company);
    }

    public  void inputAddress1(String address1){
        address1Box.sendKeys(address1);
    }

    public void inputAddress2(String address2){
        address2Box.sendKeys(address2);
    }

    public void inputCity(String city){
        cityBox.sendKeys(city);
    }

    public  void inputPostcode(String postcode){
        postcodeBox.sendKeys(postcode);
    }

    public void inputOther(String additionInfo){
        additionInfoBox.sendKeys(additionInfo);
    }

    public void inputHomePhone(String phone){
        phoneHomeBox.sendKeys(phone);
    }

    public  void inputMobilePhone(String phone){
        phoneMobileBox.sendKeys(phone);
    }

    public void inputAlias(String alias){
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
        logger.info("Wait for registration form for " +  BasePage.waiterTime + " seconds");
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_gender1")));
        element = wait.until(ExpectedConditions.visibilityOf(postcodeBox));
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

    public void submitEmail(String email, WebDriver driver){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputNewEmail(email);
        loginPage.submitButtonClick();
    }

    public RegistrationPage(WebDriver driver) {
       super(driver);
       BasePage.navigate(driver, BasePage.LOGIN_PAGE_URL);
    }

    public void checkUserButton(){
        if(!userProfileButton.isDisplayed())
            Assert.fail("User profile button doesn't exist");
    }

    public void checkInvalidAlerts(SoftAssert softAssert){

        String[] actualAlertMessages = getAlertList();
        String[] expectedAlertMessages = {INVALID_LAST_NAME_MESSAGE,INVALID_FIRST_NAME_MESSAGE,
                INVALID_PASSWORD_MESSAGE, INVALID_POSTCODE_MESSAGE,INVALID_DATE_MESSAGE};
        for (int i = 0; i < actualAlertMessages.length; i++){
            softAssert.assertEquals(actualAlertMessages[i], expectedAlertMessages[i],
                    String.format("Invalid alert message:  expected - %s, actual - %s",
                            expectedAlertMessages[i], actualAlertMessages[i]));
        }
    }


    public void checkUpperLimitsAlerts(SoftAssert softAssert){

        String[] actualAlertMessages = getAlertList();
        String[] expectedAlertMessages = {MAX_LAST_NAME_MESSAGE, MAX_FIRST_NAME_MESSAGE, MAX_PASSWORD_MESSAGE,
                MAX_ALIAS_MESSAGE, MAX_ADDRESS1_MESSAGE, MAX_ADDRESS2_MESSAGE,MAX_POSTCODE_MESSAGE, MAX_ADDITION_INFO_MESSAGE,
                MAX_HOME_PHONE_MESSAGE, MAX_MOBILE_PHONE_MESSAGE, INVALID_POSTCODE_MESSAGE, INVALID_DATE_MESSAGE};
        for (int i = 0; i < actualAlertMessages.length; i++){
            softAssert.assertEquals(actualAlertMessages[i], expectedAlertMessages[i],
                    String.format("Invalid alert message:  expected - %s, actual - %s",expectedAlertMessages[i], actualAlertMessages[i]));
        }
    }

    @Step("Fill registration page form with {0}")
    public void fillRegistrationForm(User user){

        logger.debug("Select gender - Male");
        selectMaleGender();

        logger.debug("Input first name - " + user.getFirstName());
        inputFirstName(user.getFirstName());

        logger.debug("Input last name - " + user.getLastName());
        inputLastName(user.getLastName());

        logger.debug("Input password - " + user.getPassword());
        inputPassword(user.getPassword());

        logger.debug("Input company - " + user.getCompany());
        inputCompany(user.getCompany());

        logger.debug("Input address1 - " + user.getAddress());
        inputAddress1(user.getAddress());

        logger.debug("Input address2 - " + user.getAddress2());
        inputAddress2(user.getAddress2());

        logger.debug("Input city - " + user.getCity());
        inputCity(user.getCity());

        logger.debug("Input post code - " + user.getZipCode());
        inputPostcode(user.getZipCode());

        logger.debug("Input country - " + user.getCountry());
        selectCountry(user.getCountry());

        logger.debug("Input addition information - " + user.getAdditionInformation());
        inputOther(user.getAdditionInformation());

        logger.debug("Input home phone - " + user.getHomePhone());
        inputHomePhone(user.getHomePhone());

        logger.debug("Input mobile phone - " + user.getMobilePhone());
        inputMobilePhone(user.getMobilePhone());

        logger.debug("Input alias - " + user.getAddressAlias());
        inputAlias(user.getAddressAlias());

        logger.debug("Select news");
        selectNews();

        logger.debug("Select options");
        selectOptions();

        logger.debug("Select day of birth - " + user.getDayBirth());
        selectBirthDay(user.getDayBirth());

        logger.debug("Select month of birth - " + user.getMonthBirth());
        selectBirthMonth(user.getMonthBirth());

        logger.debug("Select year of birth - " + user.getYearBirth());
        selectBirthYear(user.getYearBirth());

        logger.debug("Input state - " + user.getState());
        selectState(user.getState());
    }
}
