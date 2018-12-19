package framework.pageObjectModels;

import framework.models.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class CartPage extends BasePage{

    @FindBy(className = "cart_quantity_input")
    WebElement quantityTextBox;

    @FindBy(xpath = "//td[@class='cart_description']//small[2]")
    WebElement productDescription;

    @FindBy(xpath = "//a[@title='Proceed to checkout']//span")
    WebElement checkoutButton;

    @FindBy(id = "cgv")
    WebElement termsCheckBox;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    WebElement successAlertMessage;

    @FindBy ( className="icon-chevron-right")
    WebElement submitOrderButton;


    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openCartPage(WebDriver driver){
        driver.navigate().to("http://automationpractice.com/index.php?controller=order");
    }

    public void verifyOrder(Order order, SoftAssert softAssert){

        logger.debug("Get product quantity");
        int quantity = Integer.valueOf(quantityTextBox.getAttribute("value"));

        softAssert.assertEquals(quantity, order.getQuantity());

        logger.debug("Get product parameters");
        String productParams = productDescription.getText();
        productParams = productParams.replaceAll(", Size :", "");
        productParams = productParams.replaceAll("Color : ", "");
        String params[] = productParams.split(" ");

        softAssert.assertEquals(params[0], order.getColor());
        softAssert.assertEquals(params[1], order.getSize());
    }

    public void waitCheckoutButton(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(checkoutButton));
    }

    public void selcetTermsCheckBox(){
        if (!termsCheckBox.isSelected())
            termsCheckBox.click();
    }

    public void checkButtonClick(){
        checkoutButton.click();
    }

    public void submitOrderButtonClick(){
        submitOrderButton.click();
    }

    public void successAlertMessageVerify(SoftAssert softAssert){
        softAssert.assertTrue(successAlertMessage.isDisplayed());
    }
}
