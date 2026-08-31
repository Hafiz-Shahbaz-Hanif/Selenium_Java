package com.hafiz.automation.listeners;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.hafiz.automation.driver.DriverFactory;

/**
 * TestNG listener that mirrors every test into an ExtentReports node and, on
 * failure, captures a screenshot and attaches it to the report.
 */
public class TestListener implements ITestListener {

    private final ExtentReports extent = ExtentManager.getInstance();
    private final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
                result.getTestClass().getRealClass().getSimpleName() + " :: " + result.getName());
        currentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest.get().log(Status.PASS, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = currentTest.get();
        test.log(Status.FAIL, result.getThrowable());
        String screenshot = capture(result.getName());
        if (screenshot != null) {
            test.addScreenCaptureFromPath(screenshot);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest.get().log(Status.SKIP, "Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String capture(String name) {
        try {
            WebDriver driver = DriverFactory.get();
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dir = Paths.get("target", "screenshots");
            Files.createDirectories(dir);
            Path dest = dir.resolve(name + "-" + System.currentTimeMillis() + ".png");
            Files.copy(src.toPath(), dest);
            return dest.toAbsolutePath().toString();
        } catch (Exception e) {
            return null;
        }
    }
}
