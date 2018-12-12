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
    private WebElement firstNameBox;

    @FindBy(id = "lastname")
    private WebElement lastNameBox;

    @FindBy(id = "email")
    private WebElement emailBox;

    @FindBy(id = "days")
    private WebElement daysList;

    @FindBy(id = "months")
    private WebElement monthsList;

    @FindBy(id = "years")
    private WebElement yearsList;

    @FindBy(id= "newsletter")
    private WebElement newsCheckBox;

    @FindBy(id = "optin")
    private WebElement optionCheckBox;

    @FindBy(id = "old_passwd")
    private WebElement currentPasswordBox;

    @FindBy(id = "passwd")
    private WebElement newPasswordBox;

    @FindBy(id = "confirmation")
    private WebElement confirmationPasswordBox;

    @FindBy(name = "submitIdentity")
    private WebElement saveButton;

    public void navigateToPersonalInfo(WebDriver driver){
        AccountPage accountPage = new AccountPage(driver);
        accountPage.personalInfoButtonClick();
    }

    public User getActualUserData(){
        List<WebElement> days = new Select(daysList).getAllSelectedOptions();
        String[] selectedDay = days.get(0).getText().split(" ");

        List<WebElement> monthList = new Select(monthsList).getAllSelectedOptions();
        String[] selectedMonth = monthList.get(0).getText().split(" ");

        List<WebElement> yearList = new Select(yearsList).getAllSelectedOptions();
        String[] selectedYear = yearList.get(0).getText().split(" ");

        User actualUser = new User();

        actualUser.setGender(maleCheckBox.isSelected() ? "Male" : "Female");
        actualUser.setFistName(firstNameBox.getAttribute("value"));
        actualUser.setLastName(lastNameBox.getAttribute("value"));
        actualUser.setEmail(emailBox.getAttribute("value"));
        actualUser.setDayBirth(selectedDay[0]);
        actualUser.setMonthBirth(User.MonthBirth.valueOf(selectedMonth[0]));
        actualUser.setYearBirth(selectedYear[0]);
        actualUser.setNews(newsCheckBox.isSelected());
        actualUser.setOptions(optionCheckBox.isSelected());

        return actualUser;
    }

    public void fillUserFormWithDataFrom(User user){

      selectGender(user.getGender());

      inputFirstName(user.getFistName());
      inputLastName(user.getLastName());

      inputEmail(user.getEmail());
      inputNewPassword(user.getPassword());
      confirmNewPassword(user.getPassword());

      selectBirthDay(user.getDayBirth());
      selectBirthMonth(user.getMonthBirth());
      selectBirthYear(user.getYearBirth());

      selectNews(user.isNews());
      selectOptions(user.isOptions());
    }

    private void selectGender(String userGender){
        WebElement gender = userGender.equals("Male")? maleCheckBox : femaleCheckBox;
        gender.click();
    }

    private void inputFirstName(String firstName){
        firstNameBox.clear();
        firstNameBox.sendKeys(firstName);
    }

    private void inputLastName(String lastName){
        lastNameBox.clear();
        lastNameBox.sendKeys(lastName);
    }

    private void inputEmail(String email){
        emailBox.clear();
        emailBox.sendKeys(email);
    }

    public void inputCurrentPassword(String password){
        currentPasswordBox.sendKeys(password);
    }

    private void inputNewPassword(String password){
        newPasswordBox.sendKeys(password);
    }

    private void confirmNewPassword(String password){
        confirmationPasswordBox.sendKeys(password);
    }

    private void selectBirthDay(String day){
        new Select(daysList).selectByValue(day);
    }

    private void selectBirthYear(String year){
        new Select(yearsList).selectByValue(year);
    }

    private void selectBirthMonth(User.MonthBirth month){
        new Select(monthsList).selectByValue(Integer.toString(month.getValue()));
    }

    private void selectNews(Boolean news){
        if(news && !newsCheckBox.isSelected())
            newsCheckBox.click();
        else if(!news && newsCheckBox.isSelected())
            newsCheckBox.click();
    }

    private void selectOptions(Boolean options){
        if(options && !optionCheckBox.isSelected())
            optionCheckBox.click();
        else if(!options && optionCheckBox.isSelected())
            optionCheckBox.click();
    }

    public void saveButtonClick(){
        saveButton.click();
    }
}
