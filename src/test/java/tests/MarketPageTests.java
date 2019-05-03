package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.models.Order;
import framework.pages.CartPage;
import framework.pages.MarketPage;
import framework.pages.OrdersHistoryPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Positive tests")
@Feature("Login Tests")
public class MarketPageTests extends TestBase{

    private MarketPage marketPage;

    @BeforeClass(groups = {"regression", "verifier","updater"})
    @Parameters({"email","password"})
    @Step("Open login page and login with params: email - {0}, password - {1}")
    public void openLoginPage(String email, String password){
        logger.info("Navigate to login page");
        marketPage = new MarketPage(driver);
        marketPage.doLogin(email,password, driver);
    }

    @AfterClass(groups = {"regression", "verifier","updater"})
    @Step("Try to logout")
    public void doLogout(){
        marketPage.doLogout();
    }

    @DataProvider(name = "orderedUserData")
    public Object[][] getOrders(){
        return fileReader.getData(Order.class);
    }

    @Test(dataProvider = "orderedUserData")
    @Description("Verify that user can open market and add product to cart")
    @Story("Add product to cart with {0}")
    public void addProductToCartWith(Order order){

        logger.info("Click to WOMEN market button");
        marketPage.womenButtonClick();

        logger.info("Add product ot cart");
        marketPage.addProductToCart(order);

        logger.info("Wait product details");
        marketPage.waitAddedProductInfo(driver);

        logger.info("Verify product details");
        marketPage.compareProdutsName(order,softAssert);

        softAssert.assertAll();
    }

    @Description("Verify that user can complete order in the cart")
    @Story("Complete order int the cart")
    @Test
    public void completeOrderTest(){
        CartPage cartPage = new CartPage(driver);

        logger.info("Navigate to shipping");
        cartPage.openCartPage(driver);
        cartPage.waitCheckoutButton(driver);

        //Navigate to address confirm
        cartPage.checkButtonClick();

        //Navigate to shipping
        cartPage.checkoutAddressButtonClick();

        cartPage.selcetTermsCheckBox();
        cartPage.checkoutCarrierButtonClick();
        cartPage.chequeButtonClick();
        cartPage.submitOrderButtonClick();

        cartPage.successAlertMessageVerify(softAssert);

        softAssert.assertAll();
    }

    @Description("Verify order in the cart equals order model")
    @Story("Open user cart and compare order with {0}")
    @Test(dataProvider = "orderedUserData")
    public void verifyOrderInCartTest(Order order){
        CartPage cartPage = new CartPage(driver);
        cartPage.openCartPage(driver);
        cartPage.verifyOrder(order, softAssert);
        softAssert.assertAll();
    }

    @Test
    public void downloadOrderInfoTest(){
        OrdersHistoryPage historyPage = new OrdersHistoryPage(driver);

        historyPage.verifyFileDownloading(softAssert, driver);

        softAssert.assertAll();
    }
}
