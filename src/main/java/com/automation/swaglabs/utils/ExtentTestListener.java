package com.automation.swaglabs.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

	public class ExtentTestListener implements ITestListener {
	    private static ExtentReports extent = ExtentManager.getInstance();
	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	    @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
	        test.set(extentTest);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.get().log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        test.get().log(Status.FAIL, result.getThrowable());

	        // Screenshot
	        try {
	            File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
	            File dest = new File("reports/screenshots/" + result.getMethod().getMethodName() + ".png");
	            dest.getParentFile().mkdirs();
	            Files.copy(src.toPath(), dest.toPath());
	            test.get().addScreenCaptureFromPath(dest.getAbsolutePath());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }
	}

