package com.org.MavenTests;

import Models.User;
import POM.PersonalPage;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;

public class PersonalInfoTests extends TestBase{

    private PersonalPage personalPage;

    @BeforeMethod(groups = "regression")
    public void openLoginPage(){
        logger.info("Navigate to login page");
        personalPage = new PersonalPage(driver);
    }

    @AfterMethod(groups = "regression")
    public void doLogout(){
        logger.info("Logout");
        personalPage.doLogout();
    }

    // Create valid user
    @DataProvider(name = "validUserData")
    public Object[] getValidUserData(){

        User[] validUserData = new User[1];
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert JSON string from file to Object
            validUserData[0] = mapper.readValue(new File("src\\test\\resources\\validUser.json"), User.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String existedEmail = "test1@test.com1";
        validUserData[0].setEmail(existedEmail);
        return validUserData;
    }

    @Test(priority = 0, dataProvider="validUserData", groups = "regression")
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

    @Test(priority = 1, dataProvider="validUserData", groups = "regression")
    public void checkPersonalInfo(User user){

        logger.info("\n --- Check personal info test start ---\n");

        logger.info("Login");
        personalPage.doLogin(user.getEmail(),user.getPassword(), driver);

        logger.info("Navigate to personal page");
        personalPage.navigateToPersonalInfo(driver);

        logger.info("Get actual user data");
        User actualUser = personalPage.getActualUserData();

        logger.info("Check personal information");
        //personalPage.checkPersonalInfoForm(user);

        softAssert.assertEquals(actualUser.getFistName(), user.getFistName(), "Users first names doesn't match ");
        softAssert.assertEquals(actualUser.getLastName(), user.getLastName(), "Users last names doesn't match ");
        softAssert.assertEquals(actualUser.getEmail(), user.getEmail(), "Users emails doesn't match ");
        softAssert.assertEquals(actualUser.getGender(), user.getGender(), "Users genders doesn't match ");
        softAssert.assertEquals(actualUser.getDayBirth(), user.getDayBirth(), "Users birth days doesn't match ");
        softAssert.assertEquals(actualUser.getMonthBirth(), user.getMonthBirth(), "Users birth months doesn't match ");
        softAssert.assertEquals(actualUser.getYearBirth(), user.getYearBirth(), "Users birth years doesn't match ");
        softAssert.assertEquals(actualUser.isNews(), user.isNews(), "Users news doesn't match ");
        softAssert.assertEquals(actualUser.isOptions(), user.isOptions(), "Users options doesn't match ");

        softAssert.assertAll();
    }
}
