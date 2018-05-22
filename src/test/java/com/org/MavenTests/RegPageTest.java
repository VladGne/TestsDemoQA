package com.org.MavenTests;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class RegPageTest {

	public WebDriver driver;
		
		@BeforeSuite
		public void initionalBrowser() {
			System.setProperty("webdriver.gecko.driver", "D:\\_Programs\\geckodriver.exe");
			driver = new FirefoxDriver();	
			driver.get("http://demoqa.com/registration/");			
		}
					
		@AfterSuite
		public void closeBrowser() {
			driver.close();
		}
		
		@BeforeMethod
		public void goToRegisterpage() {			
			driver.navigate().refresh();
		}				
		
		@DataProvider
		/*Contain user data
		 * [0] - Fist Name
		 * [1] - Last Name
		 * [2] - Phone Number
		 * [3] - User Name
		 * [4] - Email		
		 * [5] - Description
		 * [6] - Password
		 * [7] - Confirm password
		 * [8] - Country
		 * [9] - Month
		 * [10] - Day
		 * [11] - Year
		 * [12] - Marital Status
		 * [13] - Hobby	 */
		public Object[][] getUserData(){			
			Object[][] userData = new Object[1][14];
			
			// Correct data set for registration
			userData[0][0]="TestFistName";
			userData[0][1]="TestLastName";			
			userData[0][2]="89609685555";
			userData[0][3]="TestUserName";
			userData[0][4]="test@gmail.com";
			userData[0][5]="TestDescriptionText";
			userData[0][6]="TestPassword123";
			userData[0][7]= userData[0][6];			
			userData[0][8]="Germany";
			userData[0][9]="2";
			userData[0][10]="15";
			userData[0][11]="1990";		
			userData[0][12]="single";
			userData[0][13]="dance";
			
			return userData;
		}
				
		@DataProvider
		/*Contain user contact data
		 * [0] - Month
		 * [1] - Day
		 * [2] - Year
		 * [3] - Phone number
		 * [4] - Email		 * */
		public Object[][] getContactData(){
			Object[][] contactData = new Object[1][5];
			
			// Incorrect data set
			contactData[0][0]="2";
			contactData[0][1]="31";
			contactData[0][2]="1990";
			contactData[0][3]="invalidtelnum";
			contactData[0][4]="invalidmail";
						
			return contactData;
		}
		
		// TestCase 1 - Registration main functional test		
		@Test (priority = 1, dataProvider="getUserData")	
		public void doRegistrationTest(String... userData) {
			
			String elementsId[] = {
					"name_3_firstname","name_3_lastname","phone_9", "username", 
					"email_1", "description", "password_2", "confirm_password_password_2", 
					"dropdown_7", "mm_date_8", "dd_date_8", "yy_date_8"};
			
			try {
				// Set data
				for (int i=0; i<userData.length; i++) {//Textboxes			
					if (i<8){
						driver.findElement(By.id(elementsId[i])).sendKeys(userData[i]);
						continue;
					}							
					
					if(i>=8 && i<12) { //Dropdown lists
						new Select(driver.findElement(By.id(elementsId[i]))).selectByVisibleText(userData[i]);
						continue;
					}						
					
					if(i>=12)	//Radio and check buttons
						driver.findElement(By.cssSelector(String.format("input[value='%s']", userData[i]))).click();				
				}
				
				//driver.findElement(By.cssSelector("input[value='Submit']")).click();			
			}		
			catch(Exception e){
				Assert.fail(e.toString());
			}	
		}
		
		// TestCase 3 - Date, phone, email field validation test
		@Test	(priority = 2, dataProvider="getContactData") 
		public void doCheckValidationTest(String mounth, String day, String year, String phone, String email) {	
		
			SoftAssert softAssert = new SoftAssert();
			
			//Set date
			new Select(driver.findElement(By.id("mm_date_8"))).selectByVisibleText(mounth);
			new Select(driver.findElement(By.id("dd_date_8"))).selectByVisibleText(day);
			new Select(driver.findElement(By.id("yy_date_8"))).selectByVisibleText(year);		
												
			try{				
				softAssert.assertTrue(driver.findElement(By.className("legend_txt")).isDisplayed());
			}
			catch(Exception e) {			
				softAssert.fail("Error: Date Fields Validation Failure!");
			}	
			
			driver.findElement(By.id("phone_9")).sendKeys(phone);	// Set phone number 
			driver.findElement(By.id("breadcrumbs")).click();	// Click on page header to see field validation
			
			try{				
				softAssert.assertTrue(driver.findElement(By.className("legend_txt")).isDisplayed());
			}
			catch(Exception e) {				
				softAssert.fail("Error: Phone Number Field Validation Failure!");
			}		
			
			driver.findElement(By.id("email_1")).sendKeys(email);	// Set email
			driver.findElement(By.id("breadcrumbs")).click();	// Click on page header to see field validation
			
			try{			
				softAssert.assertTrue(driver.findElement(By.className("legend_txt")).isDisplayed());
			}
			catch(Exception e) {			
				softAssert.fail("Error: Email Field Validation Failure!");
			}
			
			softAssert.assertAll();
		}	
}
