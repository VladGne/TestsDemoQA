package framework.pageObjectModels;

import framework.models.User;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


@Data
public class AddressRegistrationPage extends BasePage{

    public AddressRegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "submitAddress")
    private WebElement saveButton;

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

    private void inputFirstName(String firstName){
        firstNameBox.clear();
        firstNameBox.sendKeys(firstName);
    }

    private void inputLastName(String lastName){
        lastNameBox.clear();
        lastNameBox.sendKeys(lastName);
    }

    private void inputCompany(String company){
        companyBox.clear();
        companyBox.sendKeys(company);
    }

    private void inputAddress1(String address1){
        address1Box.clear();
        address1Box.sendKeys(address1);
    }

    private void inputAddress2(String address2){
        address2Box.clear();
        address2Box.sendKeys(address2);
    }

    private void inputCity(String city){
        cityBox.clear();
        cityBox.sendKeys(city);
    }

    private void inputPostcode(String postcode){
        postcodeBox.clear();
        postcodeBox.sendKeys(postcode);
    }

    private void inputOther(String additionInfo){
        additionInfoBox.clear();
        additionInfoBox.sendKeys(additionInfo);
    }

    private void inputHomePhone(String phone){
        phoneHomeBox.clear();
        phoneHomeBox.sendKeys(phone);
    }

    private void inputMobilePhone(String phone){
        phoneMobileBox.clear();
        phoneMobileBox.sendKeys(phone);
    }

    private void inputAlias(String alias){
        aliasBox.clear();
        aliasBox.sendKeys(alias);
    }

    public void waitForRegistrationForm(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("company")));
    }

    private void selectCountry(User.Country country){
        new Select(countryBox).selectByValue(String.valueOf(country.getValue()));
    }

    private void selectState(User.State state){
        new Select(stateBox).selectByValue(String.valueOf(state.getValue()));
    }

    public void saveButtonClick(){
        saveButton.click();
    }

    public void fillAddressRegistrationForm(User user){

        logger.debug("Input first name");
        inputFirstName(user.getFistName());

        logger.debug("Input last name");
        inputLastName(user.getLastName());

        logger.debug("Input company");
        inputCompany(user.getCompany());

        logger.debug("Input address1");
        inputAddress1(user.getAddress());

        logger.debug("Input address2");
        inputAddress2(user.getAddress2());

        logger.debug("Input city");
        inputCity(user.getCity());

        //logger.debug("Input country");
        //selectCountry(user.getCountry());

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

        logger.debug("Select state");
        selectState(user.getState());
    }
}
