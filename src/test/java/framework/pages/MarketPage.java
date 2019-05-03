package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import framework.models.Order;

public class MarketPage extends BasePage {

    public MarketPage(WebDriver driver) {
        super(driver);
        BasePage.navigate(driver, BasePage.LOGIN_PAGE_URL);
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

    @FindBy(className = "product_list")
    WebElement productList;

    @FindBy(xpath = "//a[contains(text(),'Faded Short Sleeve T-shirts')]")
    WebElement product;

    @FindBy(name = "Submit")
    WebElement addToCartButton;

    @FindBy(className = "cheque")
    WebElement checkButton;

    @FindBy(className = "layer_cart_product")
    WebElement addedProductInfo;

    @FindBy(xpath = "//td[@class='cart_description']//p[1]")
    WebElement productName;

    @FindBy(className = "products")
    WebElement productsInCart;

    @FindBy(id = "layer_cart_product_title")
    WebElement addedProductTitle;

    @FindBy(className = "cross")
    WebElement closeProductInfoButton;

    public void openCartPage(WebDriver driver) {
        driver.navigate().to("http://automationpractice.com/index.php?controller=order");
    }

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
        if (order.getColor().equals("Orange"))
            orangeColorCheckBox.click();

        logger.debug("Click to product button");
        product.click();
    }

    public void addToCartButtonClick() {
        logger.debug("Click to cart button");
        addToCartButton.click();
    }

    public void waitAddedProductInfo(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(addedProductInfo));
    }

    public void waitProductDetails(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(addToCartButton));
    }

    public void verifyProductName(String expectedProductName, SoftAssert softAssert) {
        softAssert.assertEquals(productName.getText(), expectedProductName);
    }

    public void addProductToCart(Order order) {
        productList.findElements(By.tagName("li")).stream()
                .filter(element -> element
                        .findElement(By.tagName("a"))
                        .getAttribute("title")
                        .equals(order.getName()))
                .forEach(element -> element
                        .findElement((By.className("button"))).click());
    }

    public void compareProdutsName(Order order, SoftAssert softAssert) {
        softAssert.assertEquals(addedProductTitle.getText(), order.getName(), "Product name doesn't equals: ");
        closeProductInfoButton.click();
    }
}
