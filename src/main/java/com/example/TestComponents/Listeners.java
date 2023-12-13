package com.example.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.example.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        ITestListener.super.onStart(context);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // TODO Auto-generated method stub
        ITestListener.super.onTestFailure(result);
        extentTest.get().fail(result.getThrowable());
        String filePath = null;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stu
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestSuccess(result);
        extentTest.get().log(Status.PASS, "Passed");
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        ITestListener.super.onFinish(context);
        extent.flush();
    }

}
