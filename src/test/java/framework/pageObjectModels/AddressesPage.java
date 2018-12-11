package framework.pageObjectModels;

import framework.models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddressesPage extends BasePage{

    final String LoginPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

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

    @FindBy(className = "icon-chevron-right right")
    private WebElement updateButton;

    public void addressButtonClick(WebDriver driver){
        AccountPage accountPage = new AccountPage(driver);
        accountPage.addressButtonClick();
    }

    public User getActualUserData(){
        User actualUser = new User();

        actualUser.setFistName(fullName.get(0).getText());
        actualUser.setLastName(fullName.get(1).getText());
        String country = countryField.getText().replaceAll("\\s","");
        actualUser.setCountry(User.Country.valueOf(country));
        String[] currentCity = city.getText().split(",");
        actualUser.setCity(currentCity[0]);
        actualUser.setState(User.State.valueOf(state.getText()));
        actualUser.setZipCode(postCode.getText());
        actualUser.setCompany(companyField.getText());
        actualUser.setAddress(address1Field.getText());
        actualUser.setAddress2(address2Field.getText());
        actualUser.setHomePhone(homePhoneField.getText());
        actualUser.setMobilePhone(mobilePhoneField.getText());

        return actualUser;
    }

    public AddressesPage(WebDriver driver) {
        super(driver);
        BasePage.navigate(driver, LoginPageURL);
    }

    public void updateButtonClick(){
        updateButton.click();
    }

    public void fillAddressesDataWith(User user){
        //RegistrationPage.inputFirstName(newUsersData.getFistName());
    }
}