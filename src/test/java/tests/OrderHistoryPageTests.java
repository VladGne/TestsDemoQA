package tests;

import framework.pageObjectModels.OrdersHistoryPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class OrderHistoryPageTests extends TestBase{

    private OrdersHistoryPage historyPage;

    @BeforeMethod(groups = {"regression", "verifier","updater"})
    @Parameters({"email","password"})
    public void openLoginPage(Method method, String email, String password){
        logger.info("Navigate to login page");
        historyPage = new OrdersHistoryPage(driver);
        logger.info(method.getName() + " start");
        logger.info("Login");
        historyPage.doLogin(email,password, driver);
    }

    @AfterMethod(groups = {"regression", "verifier","updater"})
    public void doLogout(){
        logger.info("Logout");
        historyPage.doLogout();
    }

    @Test(dataProvider = "orderedUserData")
    public void downloadOrderInfoTest(){

        historyPage.verifyFileDownloading(softAssert);

        softAssert.assertAll();
    }
}
