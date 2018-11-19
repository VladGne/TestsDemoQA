package POM;

import Models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import java.util.List;


public class PersonalPage extends BasePage{

    private PersonalPage(WebDriver driver) {
        super(driver);
    }



    @FindBy(className = "icon-user")
    private WebElement personalInfoButton;

    @FindBy(className = "std")
    private WebElement personalInfoForm;

    @FindBy(id = "id_gender1")
    private WebElement maleCheckBox;

    @FindBy(id = "id_gender1")
    private WebElement femaleCheckBox;

    @FindBy(id = "firstname")
    private WebElement firstName;

    @FindBy(id = "lastname")
    private WebElement lastName;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "days")
    private WebElement days;

    @FindBy(id = "months")
    private WebElement month;

    @FindBy(id = "years")
    private WebElement year;

    @FindBy(id= "newsletter")
    private WebElement newsCheckBox;

    @FindBy(id = "optin")
    private WebElement optionCheckBox;



    public void navigateToPersonalInfo(){
        personalInfoButton.click();
    }

    public void waitForPersonalInfoForm(){

        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement form = wait.until((WebDriver d) -> personalInfoForm) ;
    }

    private boolean checkMaleGender(){
            return maleCheckBox.isSelected();
    }

    public boolean checkFemaleGender(){
        return femaleCheckBox.isSelected();
    }

    private void checkFirstName(String expectedFirstName){
        String actualName = firstName.getAttribute("value");
        if(!actualName.equals(expectedFirstName))
            softAssertion.fail(String.format("First name validation error: actual - %s, expected - %s",actualName, expectedFirstName ));
    }

    private void checkLastName(String expectedLastName){
        String actualName = lastName.getAttribute("value");
        if(!actualName.equals(expectedLastName))
            softAssertion.fail(String.format("Last name validation error: actual - %s, expected - %s",actualName, expectedLastName ));
    }

    private void checkEmail (String expectedEmail){
        String actualEmail = email.getAttribute("value");
        if(!actualEmail.equals(expectedEmail))
            softAssertion.fail(String.format("Email validation error: actual - %s, expected - %s", actualEmail, expectedEmail ));
    }

    private void checkBithDay(String expectedDay){

        List<WebElement> daysList = new Select(days).getAllSelectedOptions();
        String[] selectedDay = daysList.get(0).getText().split(" ");
        if(selectedDay[0].equals(expectedDay))
            softAssertion.fail(String.format("Birth day validation error: actual - %s, expected - %s", selectedDay[0], expectedDay ));
    }

    private void checkBithMonth(User.MonthBirth expectedMonth){

        List<WebElement> monthList = new Select(month).getAllSelectedOptions();
        String[] selectedMonth = monthList.get(0).getText().split(" ");
        if(selectedMonth[0].equals(expectedMonth.toString()))
            softAssertion.fail(String.format("Birth month validation error: actual - %s, expected - %s", selectedMonth[0], expectedMonth.toString() ));
    }

    private void checkBithYear(String expectedYear){

        List<WebElement> yearList = new Select(year).getAllSelectedOptions();
        String[] selectedYear = yearList.get(0).getText().split(" ");
        if(selectedYear[0].equals(expectedYear))
            softAssertion.fail(String.format("Birth year validation error: actual - %s, expected - %s", selectedYear[0], expectedYear ));
    }

    private boolean checkNews(){
        return newsCheckBox.isSelected();
    }

    private boolean checkOptions(){
        return optionCheckBox.isSelected();
    }

    public static PersonalPage open(WebDriver driver) {
        final String LoginPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.navigate().to(LoginPageURL);
        return new PersonalPage(driver);
    }

    public void checkPersonalInfoForm(User user, Logger logger){
        logger.info("Check gender");
        if(!checkMaleGender()){
            softAssertion.fail("Gender validation error: male gender isn't selected");
        }

        logger.info("Check first name");
        checkFirstName(user.getFistName());

        logger.info("Check last name");
        checkLastName(user.getLastName());

        logger.info("Check email");
        checkEmail(user.getEmail());

        logger.info("Check birth day");
        checkBithDay(user.getDayBirth());

        logger.info("Check birth month");
        checkBithMonth(user.getMonthBirth());

        logger.info("Check birth year");
        checkBithYear(user.getYearBirth());

        logger.info("Check news");
        if(!checkNews()){
            softAssertion.fail("News validation error: news checkbox isn't selected");
        }

        logger.info("Check options");
        if(!checkOptions()){
            softAssertion.fail("Option validation error: options checkbox isn't selected");
        }
    }
}
