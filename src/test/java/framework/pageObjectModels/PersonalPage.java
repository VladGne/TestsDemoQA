package framework.pageObjectModels;

import framework.models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PersonalPage extends BasePage{

    private final String LOGIN_PAGE_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    public PersonalPage(WebDriver driver) {
        super(driver);
        BasePage.navigate(driver, LOGIN_PAGE_URL);
    }

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

    public void navigateToPersonalInfo(WebDriver driver){
        AccountPage accountPage = new AccountPage(driver);
        accountPage.personalInfoButtonClick();

    }

    public User getActualUserData(){
        List<WebElement> daysList = new Select(days).getAllSelectedOptions();
        String[] selectedDay = daysList.get(0).getText().split(" ");

        List<WebElement> monthList = new Select(month).getAllSelectedOptions();
        String[] selectedMonth = monthList.get(0).getText().split(" ");

        List<WebElement> yearList = new Select(year).getAllSelectedOptions();
        String[] selectedYear = yearList.get(0).getText().split(" ");

        User actualUser = new User();

        actualUser.setGender(maleCheckBox.isSelected() ? "Male" : "Female");
        actualUser.setFistName(firstName.getAttribute("value"));
        actualUser.setLastName(lastName.getAttribute("value"));
        actualUser.setEmail(email.getAttribute("value"));
        actualUser.setDayBirth(selectedDay[0]);
        actualUser.setMonthBirth(User.MonthBirth.valueOf(selectedMonth[0]));
        actualUser.setYearBirth(selectedYear[0]);
        actualUser.setNews(newsCheckBox.isSelected());
        actualUser.setOptions(optionCheckBox.isSelected());

        return actualUser;
    }
}
