package framework.pageObjectModels;

import framework.models.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class MarketPage extends BasePage {

    public MarketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "layered_category_4")
    WebElement topsCheckBox;

    @FindBy(id = "layered_category_8")
    WebElement dressesCheckBox;

    @FindBy(id = "layered_id_attribute_group_1")
    WebElement smallSizeCheckBox;

    @FindBy(id = "layered_id_attribute_group_2")
    WebElement mediumSizeCheckBox;

    @FindBy(id = "layered_id_attribute_group_3")
    WebElement largeSizeCheckBox;

    @FindBy(id = "layered_id_attribute_group_13")
    WebElement orangeColorCheckBox;

    @FindBy(id = "list")
    WebElement listButton;

    @FindBy(id = "product_list")
    WebElement productList;

    @FindBy(xpath = "//a[contains(text(),'Faded Short Sleeve T-shirts')]")
    WebElement product;

    @FindBy(name = "Submit")
    WebElement addToCartButton;

    @FindBy(xpath = "//a[@title='Proceed to checkout']//span")
    WebElement checkoutButton;

    @FindBy(xpath = "//td[@class='cart_description']//small[2]")
    WebElement productDescription;

    @FindBy(className = "cart_quantity_input")
    WebElement quantityTextBox;

    @FindBy(id = "cgv")
    WebElement termsCheckBox;

    @FindBy(className ="cheque")
    WebElement checkButton;

    @FindBy ( className="icon-chevron-right")
    WebElement submitOrderButton;

    public void findProduct(Order order) {

        logger.debug("Click to category checkbox");
        if (order.getCategory().equals(Order.Category.TOP))
            topsCheckBox.click();
        else if (order.getCategory().equals(Order.Category.DRESS))
            dressesCheckBox.click();

        logger.debug("Click to size checkbox");
        if (order.getSize().equals(Order.Size.S))
            smallSizeCheckBox.click();
        else if (order.getSize().equals(Order.Size.M))
            mediumSizeCheckBox.click();
        else if (order.getSize().equals(Order.Size.L))
            largeSizeCheckBox.click();

        logger.debug("Click to color checkbox");
        if(order.getColor().equals("Orange"))
            orangeColorCheckBox.click();

        logger.debug("Click to product button");
        product.click();
    }

    public void addToCartButtonClick(){
        logger.debug("Click to cart button");
        addToCartButton.click();
    }

    public void selcetTermsCheckBox(){
        if (!termsCheckBox.isSelected())
            termsCheckBox.click();
    }

    public void waitProductDetails(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(addToCartButton));
    }

    public void waitCheckoutButton(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(checkoutButton));
    }

    public void verifyOrder(Order order, SoftAssert softAssert){
        logger.debug("Click to cart button");

        checkoutButton.click();

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

    public void openCartPage(WebDriver driver){
        driver.navigate().to("http://automationpractice.com/index.php?controller=order");
    }

    public void compliteOrder(){
        checkoutButton.click();
    }

    public void checkButtonClick(){
        checkoutButton.click();
    }

    public void submitOrderButtonClick(){
        submitOrderButton.click();
    }

}
