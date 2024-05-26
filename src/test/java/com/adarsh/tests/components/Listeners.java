package com.adarsh.tests.components;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.adarsh.resources.ExtentReportNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {
    ExtentReports extent = ExtentReportNG.getExtentReport();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
        addScreenshotToReport(result, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, result.getThrowable());
        addScreenshotToReport(result, "Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, result.getThrowable());
        addScreenshotToReport(result, "Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public void addScreenshotToReport(ITestResult result, String status) {
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            String filePath = null;
            try {
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                filePath = captureScreenshot(driver, status + "_" + result.getMethod().getMethodName() + "_" + timestamp);
                extentTest.get().addScreenCaptureFromPath(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            extentTest.get().log(Status.FAIL, "Driver is not initialized");
        }
    }
}
