package com.org.MavenTests;

import POM.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class TestBase {

    protected Logger logger = LogManager.getLogger(this);

    //protected WebDriver driver;
    protected SoftAssert softAssertion= new SoftAssert();

    public List<WebElement> getMainPageElements(Class classObject){

        List<WebElement> allPageElementList = new LinkedList<>();

        for (Field field : classObject.getDeclaredFields()) {
            if (field.getType() == WebElement.class){
                try {
                    allPageElementList.add((WebElement) field.get(classObject));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return allPageElementList;
    }


    public void checkElementsVisibility(Class classObject){

        SoftAssert softAssert = new SoftAssert();
        WebElement element;

        for (Field field : classObject.getDeclaredFields()) {
            if (field.getType() == WebElement.class){
                try {
                    element = (WebElement) field.get(classObject);
                    if (!element.isDisplayed())
                        softAssert.fail("Element isn't displayed - " + element.getAttribute("name"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        softAssert.assertAll();
    }
}
