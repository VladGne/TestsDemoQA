package POM;

import Models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class PersonalPage extends BasePage{

    private PersonalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement emailTextbox;

    @FindBy(id = "passwd")
    private WebElement passwordTextbox;

    @FindBy(id = "SubmitLogin")
    private WebElement loginButton;

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

    public void doLogin(String email, String password){
        emailTextbox.sendKeys(email);
        passwordTextbox.sendKeys(password);
        loginButton.click();
    }

    public void navigateToPersonalInfo(){
        personalInfoButton.click();
    }

    public void waitForPersonalInfoForm(){

        WebDriverWait wait = new WebDriverWait(driver, BasePage.waiterTime);
        WebElement form = wait.until((WebDriver d) -> personalInfoForm) ;
    }

    public boolean checkMaleGender(){
            return maleCheckBox.isSelected();
    }

    public boolean checkFemaleGender(){
        return femaleCheckBox.isSelected();
    }

    public boolean checkFirstName(String expectedFirstName){

        return firstName.getAttribute("value").equals(expectedFirstName);
    }

    public boolean checkLastName(String expectedLastName){

        return lastName.getAttribute("value").equals(expectedLastName);
    }

    public boolean checkEmail (String expectedEmail){

        return email.getAttribute("value").equals(expectedEmail);
    }

    public boolean checkBithDay(String expectedDay){

        List<WebElement> daysList = new Select(days).getAllSelectedOptions();
        String[] selectedDay = daysList.get(0).getText().split(" ");
        return selectedDay[0].equals(expectedDay);
    }

    public boolean checkBithMonth(User.MonthBirth expectedMonth){

        List<WebElement> monthList = new Select(month).getAllSelectedOptions();
        String[] selectedMonth = monthList.get(0).getText().split(" ");
        return selectedMonth[0].equals(expectedMonth.toString());
    }

    public boolean checkBithYear(String expectedYear){

        List<WebElement> yearList = new Select(year).getAllSelectedOptions();
        String[] selectedYear = yearList.get(0).getText().split(" ");
        return selectedYear[0].equals(expectedYear);
    }

    public boolean checkNews(){
        return newsCheckBox.isSelected();
    }

    public boolean checkOptions(){
        return optionCheckBox.isSelected();
    }

    public static PersonalPage open(WebDriver driver) {
        final String LoginPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        driver.navigate().to(LoginPageURL);
        return new PersonalPage(driver);
    }
}
