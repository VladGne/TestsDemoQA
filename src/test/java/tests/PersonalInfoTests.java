package tests;

import framework.models.User;
import framework.pageObjectModels.PersonalPage;
import org.testng.annotations.*;

//import static framework.helperClasses.FileReader.readUserDataFrom;

public class PersonalInfoTests extends TestBase{

    private PersonalPage personalPage;

    @BeforeMethod(groups = {"regression", "verifier","updater"})
    public void openLoginPage(){
        logger.info("Navigate to login page");
        personalPage = new PersonalPage(driver);
    }

    @AfterMethod(groups = {"regression", "verifier","updater"})
    public void doLogout(){
        logger.info("Logout");
        personalPage.doLogout();
    }

    // Create valid user
    @DataProvider(name = "validUserData")
    public Object[] getValidUserData(){

        return fileReader.getData(User.class);
    }

    @Test(dataProvider="validUserData", groups = "regression")
    public void checkVisibility(User user){
        logger.info("Check personal info page elements visibility test start\n");

        logger.info("Login");
        personalPage.doLogin(user.getEmail(),user.getPassword(), driver);

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo(driver);

        logger.info("Check elements visibility");
        personalPage.checkPageElementsVisibility(personalPage.getClass(), softAssert);
        softAssert.assertAll();

        logger.info("\n --- Check personal info page elements visibility test end ---\n");
    }

    @Test(dataProvider="validUserData", groups = {"regression", "verifier"})
    public void checkPersonalInfo(User user){

        logger.info("\n --- Check personal info test start ---\n");

        final String existedEmail = "test1@test.com1";
        user.setEmail(existedEmail);

        logger.info("Login");
        personalPage.doLogin(user.getEmail(),user.getPassword(), driver);

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo(driver);

        logger.info("Validate personal page");
        personalPage.compareActualUserWith(user, softAssert);

        softAssert.assertAll();
    }

    @DataProvider(name = "registeredUserData")
    public Object[] getRegisteredUserData(){

        return fileReader.getData(User.class);
    }

    @Test(dataProvider="registeredUserData", groups = {"regression","updater"})
    public void updatePersonalInfo(User user){

        String email = context.getAttribute("Email").toString();

        logger.info("Login");
        personalPage.doLogin(email, user.getPassword(), driver);

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo(driver);

        personalPage.inputCurrentPassword(user.getPassword());

        personalPage.fillUserFormWithDataFrom(user);

        personalPage.saveButtonClick();
        personalPage.waitSuccessfulMessage(driver);

    }
}
