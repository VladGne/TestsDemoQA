package framework.pages;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.asserts.SoftAssert;

import lombok.SneakyThrows;

public class OrdersHistoryPage extends BasePage{

    final String FILEPATH = String.format("C:\\Users\\%s\\Downloads\\",System.getProperty("user.name"));

    @FindBy (className="link-button")
    WebElement downloadButton;

    public OrdersHistoryPage(WebDriver driver) {
        final String orderHistoryUrl = "http://automationpractice.com/index.php?controller=history";
        PageFactory.initElements(driver, this);
        driver.navigate().to(orderHistoryUrl);
    }

    @SneakyThrows
    public void verifyFileDownloading(SoftAssert softAssert, WebDriver driver){

        String link = downloadButton.getAttribute("href");
        driver.get(link);
        waitForFileDownload(driver);
    }

    public boolean getAllFiles(){
        File[] files = new File(FILEPATH).listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().contains(".pdf")) {
                return true;
            }
        }
        return false;
    }

    private void waitForFileDownload(WebDriver driver){
        int totalTimeoutInMillis = 10000;

        FluentWait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(totalTimeoutInMillis, TimeUnit.MILLISECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS);

        wait.until((WebDriver wd) -> getAllFiles());
    }
}