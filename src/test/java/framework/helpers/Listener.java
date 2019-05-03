package framework.helpers;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import lombok.SneakyThrows;

public class Listener implements ITestListener {

    WebDriver driver = null;
    private Logger logger = LogManager.getLogger(this);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info( getTestMethodName(iTestResult) + " Test start" );
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info( getTestMethodName(iTestResult) + " Test succeed" );
    }

    @Override
    @SneakyThrows
    public void onTestFailure(ITestResult iTestResult) {
        logger.info( getTestMethodName(iTestResult) + " Test failed" );
        ITestContext context = iTestResult.getTestContext();
        driver = (WebDriver) context.getAttribute("WebDriver");

        Object testClass = iTestResult.getInstance();
        if (driver instanceof WebDriver){
            saveScreenshotPNG(driver);
        }

        saveTestLog(getTestMethodName(iTestResult));

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        String flieName = String.format("testFail on %s - %s", iTestResult.getTestClass().getName(), iTestResult.getMethod().getMethodName());

        //Move image file to new destination
        File DestFile = new File(String.format("D://%s.png", flieName));

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info( getTestMethodName(iTestResult) + " Test skipped" );
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.info( getTestMethodName(iTestResult) + " Test failed but it is in defined success ratio" );
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.info( iTestContext.getName() + " method start" );
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info( iTestContext.getName() + " method end" );
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTestLog(String message){
        return message;
    }
}
