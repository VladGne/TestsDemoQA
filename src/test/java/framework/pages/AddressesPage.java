package framework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import framework.models.User;

public class AddressesPage extends BasePage{

    // Web Elements on Addresses Page
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

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[9]/a[1]/span[1]")
    private WebElement updateButton;

    // Page constructor
    public AddressesPage(WebDriver driver) {
        super(driver);
        logger.info( "Navigate to url: " +  BasePage.LOGIN_PAGE_URL );
        BasePage.navigate(driver, BasePage.LOGIN_PAGE_URL);
    }

    // Page waiter
    public void waitAddressPage(WebDriver driver){
        logger.info( "Wait for address page load " + BasePage.waiterTime + " seconds" );
        new WebDriverWait(driver, BasePage.waiterTime)
                .until( ExpectedConditions.presenceOfElementLocated( By.xpath("//span[@class='address_name']")));
    }

    public void addressButtonClick(WebDriver driver){
        AccountPage accountPage = new AccountPage(driver);
        accountPage.addressButtonClick();
    }

    public void updateButtonClick(){
        updateButton.click();
    }

    public User getActualUserData(){
        User actualUser = new User();

        logger.debug( "Get user first name" );
        actualUser.setFirstName(fullName.get(0).getText());

        logger.debug( "Get user last name" );
        actualUser.setLastName(fullName.get(1).getText());

        logger.debug( "Get user country" );
        String country = countryField.getText().replaceAll("\\s","");
        actualUser.setCountry(User.Country.valueOf(country));

        logger.debug( "Get user city" );
        String[] currentCity = city.getText().split(",");
        actualUser.setCity(currentCity[0]);

        logger.debug( "Get user state" );
        actualUser.setState(User.State.valueOf(state.getText()));

        logger.debug( "Get user zip code" );
        actualUser.setZipCode(postCode.getText());

        logger.debug( "Get user company" );
        actualUser.setCompany(companyField.getText());

        logger.debug( "Get user address" );
        actualUser.setAddress(address1Field.getText());

        logger.debug( "Get user address2" );
        actualUser.setAddress2(address2Field.getText());

        logger.debug( "Get user home phone" );
        actualUser.setHomePhone(homePhoneField.getText());

        logger.debug( "Get user mobile phone" );
        actualUser.setMobilePhone(mobilePhoneField.getText());

        return actualUser;
    }

    public void compareActualUserWith(User user, SoftAssert softAssert){

        logger.debug( "Get actual user data from the page" );
        User actualUser = getActualUserData();

        logger.debug( "Verify user data" );
        softAssert.assertEquals( actualUser.getFirstName(),      user.getFirstName(),     "Users first names doesn't match: " );
        softAssert.assertEquals( actualUser.getLastName(),      user.getLastName(),     "Users last names doesn't match " );
        softAssert.assertEquals( actualUser.getCountry(),       user.getCountry(),      "Users countries doesn't match " );
        softAssert.assertEquals( actualUser.getCity(),          user.getCity(),         "Users cities doesn't match " );
        softAssert.assertEquals( actualUser.getState(),         user.getState(),        "Users states doesn't match " );
        softAssert.assertEquals( actualUser.getZipCode(),       user.getZipCode(),      "Users post codes doesn't match " );
        softAssert.assertEquals( actualUser.getCompany(),       user.getCompany(),      "Users companies doesn't match " );
        softAssert.assertEquals( actualUser.getAddress(),       user.getAddress(),      "Users addresses doesn't match " );
        softAssert.assertEquals( actualUser.getAddress2(),      user.getAddress2(),     "Users addressess2 doesn't match " );
        softAssert.assertEquals( actualUser.getHomePhone(),     user.getHomePhone(),    "Users home phones doesn't match " );
        softAssert.assertEquals( actualUser.getMobilePhone(),   user.getMobilePhone(),  "Users mobile phones doesn't match " );
    }
}