package tests;

import framework.models.Order;
import framework.pageObjectModels.MarketPage;
import framework.pageObjectModels.OrdersHistoryPage;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class OrdersTests extends TestBase{

    private MarketPage marketPage;

    @BeforeMethod(groups = {"regression", "verifier","updater"})
    @Parameters({"email","password"})
    public void openLoginPage(Method method, String email, String password){
        logger.info("Navigate to login page");
        marketPage = new MarketPage(driver);
        logger.info(method.getName() + " start");
        logger.info("Login");
        marketPage.doLogin(email,password, driver);
    }

    @AfterMethod(groups = {"regression", "verifier","updater"})
    public void doLogout(){
        logger.info("Logout");
        marketPage.doLogout();
    }

    @DataProvider(name = "orderedUserData")
    public Object[] getOrders(){
        return fileReader.getData(Order.class);
    }

    @Test(dataProvider = "orderedUserData")
    public void addProductToCartWith(Order order){

        marketPage.womenButtonClick();

        marketPage.findProduct(order);
        marketPage.waitProductDetails(driver);
        marketPage.addToCartButtonClick();
        marketPage.waitCheckoutButton(driver);
        marketPage.checkButtonClick();
        marketPage.verifyProductName(order.getName(), softAssert);

        softAssert.assertAll();
    }

    @Test(dataProvider = "orderedUserData")
    public void verifyOrderTest(Order order){

        marketPage.openCartPage(driver);
        marketPage.verifyOrder(order, softAssert);
        softAssert.assertAll();
    }

    @Test(dataProvider = "orderedUserData")
    public void completeOrderTest(){

        logger.info("Navigate to addresses");
        marketPage.openCartPage(driver);
        marketPage.waitCheckoutButton(driver);

        logger.info("Navigate to shipping");
        marketPage.openCartPage(driver);
        marketPage.waitCheckoutButton(driver);

        marketPage.selcetTermsCheckBox();
        marketPage.checkButtonClick();
        marketPage.submitOrderButtonClick();

        marketPage.successAlertMessageVerify(softAssert);

        softAssert.assertAll();
    }

    @Test(dataProvider = "orderedUserData")
    public void downloadOrderInfoTest(){

        OrdersHistoryPage historyPage = new OrdersHistoryPage(driver);

        historyPage.verifyFileDownloading(softAssert);

        softAssert.assertAll();
    }
}
