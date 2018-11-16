package POM;

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

    private boolean checkFullName(List<WebElement> fullName, String expectedFirstName, String expectedSecondName){
       String firstName = fullName.get(0).getText();
       String lastName = fullName.get(1).getText();

       if (! firstName.equals(expectedFirstName))
           return false;
       if (lastName.equals(expectedSecondName))
           return false;

        return true;
    }

    private boolean checkField(){

        return true;
    }


    public AddressesPage(WebDriver driver) {
        super(driver);
    }
}
