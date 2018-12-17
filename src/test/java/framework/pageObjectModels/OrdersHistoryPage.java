package framework.pageObjectModels;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class OrdersHistoryPage {

    public OrdersHistoryPage(WebDriver driver) {
        final String orderHistoryUrl = "http://automationpractice.com/index.php?controller=history";
        PageFactory.initElements(driver, this);
        driver.navigate().to(orderHistoryUrl);
    }

    @FindBy (className="icon-file-text")
    WebElement downloadButton;

    public void downloadButtonClick(){
        downloadButton.click();
    }

    @SneakyThrows
    public void verifyFileDownloading(WebDriver driver, SoftAssert softAssert){

        long currentFilesNumber = fileNumersInDownloads();

        downloadButtonClick();

        Thread.sleep(5000);

        softAssert.assertTrue(currentFilesNumber + 1 == fileNumersInDownloads());
    }

    @SneakyThrows
    private long fileNumersInDownloads(){
        String filePath = String.format("C:\\Users\\%s\\Downloads\\",System.getProperty("user.name"));
        try (Stream<Path> files = Files.list(Paths.get(filePath))) {
            return files.count();
        }
    }
}
