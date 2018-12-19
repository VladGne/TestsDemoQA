package framework.pageObjectModels;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class OrdersHistoryPage extends BasePage{

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
    public void verifyFileDownloading(SoftAssert softAssert){

        long currentFilesNumber = fileNumberInDownloads();

        downloadButtonClick();

        Thread.sleep(5000);

        softAssert.assertTrue(currentFilesNumber + 1 == fileNumberInDownloads());
    }

    @SneakyThrows
    private long fileNumberInDownloads(){
        String filePath = String.format("C:\\Users\\%s\\Downloads\\",System.getProperty("user.name"));
        try (Stream<Path> files = Files.list(Paths.get(filePath))) {
            return files.count();
        }
    }







    private void waitForFileDownload(String expectedFileName, WebDriver driver){
        int totalTimeoutInMillis = 10000;

        FluentWait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(totalTimeoutInMillis, TimeUnit.MILLISECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS);
        File fileToCheck = getDownloadsDirectory()
                .resolve(expectedFileName)
                .toFile();

        wait.until((WebDriver wd) -> fileToCheck.exists());

    }

    private Path downloadsDirectory;
    private synchronized Path getDownloadsDirectory(){

        if(downloadsDirectory == null){

            try {
                downloadsDirectory = Files.createTempDirectory("selleniumdownloads_");
            } catch (IOException ex) {
                throw new RuntimeException("Failed to create temporary downloads directory");
            }
        }
        return downloadsDirectory;
    }
}