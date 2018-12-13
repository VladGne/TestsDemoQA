package tests;

import framework.models.User;
import framework.pageObjectModels.MarketPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class OrdersTests extends TestBase{

    private MarketPage marketPage;

    @BeforeMethod(groups = {"regression", "verifier","updater"})
    public void openLoginPage(Method method){
        logger.info("Navigate to login page");
        marketPage = new MarketPage(driver);
        logger.info(method.getName() + " start");
    }

    @AfterMethod(groups = {"regression", "verifier","updater"})
    public void doLogout(){
        logger.info("Logout");
        marketPage.doLogout();
    }

    @DataProvider(name = "orderedUserData")
    public Object[] getValidUserData(){

        fileReader.processDataFile(parameters.get("orderedUserData"));
        return fileReader.getData();
    }

    @Test(dataProvider = "orderedUserData")
    public void buyProductTest(User users[]){
        User user = users[0];

        logger.info("Login");
        marketPage.doLogin(user.getEmail(),user.getPassword(), driver);

        marketPage.womenButtonClick();

        marketPage.findProduct(user.getOrders());

    }
}
