package POM;

import Models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class AddressesPage extends BasePage{

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

    public void addressButtonClick(){
        addressButton.click();
    }


    private void checkFullName(String expectedFirstName, String expectedLastName){
       String firstName = fullName.get(0).getText();
       String lastName = fullName.get(1).getText();

//       if(!firstName.equals(expectedFirstName))
//           softAssertion.fail(String.format("First name doesn't match: actual - %s, expected - %s", firstName, expectedFirstName));
//
//       if(!lastName.equals(expectedLastName))
//           softAssertion.fail(String.format("Last name doesn't match: actual - %s, expected - %s", lastName, expectedLastName));
    }

    private void checkCountry(User.Country expectedCountry){

//        if(countryField.getText().equals(expectedCountry.toString()))
//            softAssertion.fail(String.format("Country doesn't match: actual - %s, expected - %s",countryField.getText(), expectedCountry.toString()));
    }

    private void checkCity(String expectedCity){
        String[] currentCity = city.getText().split(",");
//        if (!currentCity[0].equals(expectedCity))
//            softAssertion.fail(String.format("City doesn't match: actual - %s, expected - %s", currentCity[0], expectedCity));
    }

    private void checkState(User.State expectedState){
//        if (!state.getText().equals(expectedState.toString()))
//            softAssertion.fail(String.format("State doesn't match: actual - %s, expected - %s", state.getText(), expectedState.toString()));
    }

    private void checkPostCode(String expectedPostcode){
//        if (postCode.getText().equals(expectedPostcode))
//            softAssertion.fail(String.format("Postcode doesn't match: actual - %s, expected - %s",postCode.getText(), expectedPostcode));
    }

    private void checkCompany(String expectedCompany){
//        if(companyField.getText().equals(expectedCompany))
//            softAssertion.fail(String.format("Company doesn't match: actual - %s, expected - %s", companyField.getText(), expectedCompany));
    }

    private void checkAdderss1(String expectedAddress1){
//        if(address1Field.getText().equals(expectedAddress1))
//           softAssertion.fail(String.format("Address1 doesn't match: actual - %s, expected - %s", address1Field.getText(), expectedAddress1));
    }

    private void checkAddress2(String expectedAddress2){
//        if(address2Field.getText().equals(expectedAddress2))
//            softAssertion.fail(String.format("Address2 doesn't match: actual - %s, expected - %s", address2Field.getText(), expectedAddress2));
    }

    private void checkHomePhone(String expectedPhone){
//        if(homePhoneField.getText().equals(expectedPhone))
//            softAssertion.fail(String.format("Home phone doesn't match: actual - %s, expected - %s", homePhoneField.getText(), expectedPhone));
    }

    private void checkMobilePhone(String expectedPhone){
//        if(mobilePhoneField.getText().equals(expectedPhone))
//            softAssertion.fail(String.format("Mobile phone doesn't match: actual - %s, expected - %s", mobilePhoneField.getText(), expectedPhone));
    }

    public void checkForm(User user){
        logger.debug("Check full name");
        checkFullName(user.getFistName(),user.getLastName());

        logger.debug("Check company");
        checkCompany(user.getCompany());

        logger.debug("Check address1");
        checkAdderss1(user.getAddress());

        logger.debug("Check address2");
        checkAddress2(user.getAddress2());

        logger.debug("Check home phone");
        checkHomePhone(user.getHomePhone());

        logger.debug("Check mobile phone");
        checkMobilePhone(user.getMobilePhone());

        logger.debug("Check country");
        checkCountry(user.getCountry());

        logger.debug("Check city");
        checkCity(user.getCity());

        logger.debug("Check state");
        checkState(user.getState());

        logger.debug("Check Postcode");
        checkPostCode(user.getZipCode());
    }

    private AddressesPage(WebDriver driver) {
        super(driver);
    }

    public static AddressesPage open() {
        //final String LoginPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        //driver.navigate().to(LoginPageURL);
        return new AddressesPage(driver);
    }
}
