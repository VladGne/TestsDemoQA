package framework.helperClasses;

import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class Listener implements ITestListener {

    WebDriver driver = null;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
    }

    @Override
    @SneakyThrows
    public void onTestFailure(ITestResult iTestResult) {

        //System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
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
        File DestFile = new File(String.format("F://%s.png", flieName));

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
       // System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        //System.out.println("I am in onStart method " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        //System.out.println("I am in onFinish method " + iTestContext.getName());
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
