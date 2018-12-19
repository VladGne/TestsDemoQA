package tests;

import framework.models.Order;
import framework.pageObjectModels.CartPage;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class CartPageTests extends TestBase{
    private CartPage cartPage;

    @BeforeMethod(groups = {"regression", "verifier","updater"})
    @Parameters({"email","password"})
    public void openLoginPage(Method method, String email, String password){
        logger.info("Navigate to login page");
        cartPage = new CartPage(driver);
        logger.info(method.getName() + " start");
        logger.info("Login");
        cartPage.doLogin(email,password, driver);
    }

    @AfterMethod(groups = {"regression", "verifier","updater"})
    public void doLogout(){
        logger.info("Logout");
        cartPage.doLogout();
    }

    @DataProvider(name = "orderedUserData")
    public Object[] getOrders(){
        return fileReader.getData(Order.class);
    }

    @Test(dataProvider = "orderedUserData")
    public void verifyOrderInCartTest(Order order){

        cartPage.openCartPage(driver);
        cartPage.verifyOrder(order, softAssert);
        softAssert.assertAll();
    }

    @Test(dataProvider = "orderedUserData")
    public void completeOrderTest(){
        logger.info("Navigate to addresses");
        cartPage.openCartPage(driver);
        cartPage.waitCheckoutButton(driver);

        logger.info("Navigate to shipping");
        cartPage.openCartPage(driver);
        cartPage.waitCheckoutButton(driver);

        cartPage.selcetTermsCheckBox();
        cartPage.checkButtonClick();
        cartPage.submitOrderButtonClick();

        cartPage.successAlertMessageVerify(softAssert);

        softAssert.assertAll();
    }
}

