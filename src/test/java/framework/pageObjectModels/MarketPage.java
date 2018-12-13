package framework.pageObjectModels;

import framework.models.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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


    public void findProduct(List<Order> orders) {
        Order order = orders.get(0);

        if (order.getCategory().equals(Order.Category.TOP))
            topsCheckBox.click();
        else if (order.getCategory().equals(Order.Category.DRESS))
            dressesCheckBox.click();

        if (order.getSize().equals(Order.Size.S))
            smallSizeCheckBox.click();
        else if (order.getSize().equals(Order.Size.M))
            mediumSizeCheckBox.click();
        else if (order.getSize().equals(Order.Size.L))
            largeSizeCheckBox.click();

        if(order.getColor().equals("Orange"))
            orangeColorCheckBox.click();

        listButton.click();
    }
}
