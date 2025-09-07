package com.automation.swaglabs.stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automation.swaglabs.utils.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {

	private static ExtentReports extent;
	private static ExtentTest test;
	private WebDriver driver;

	@BeforeAll
	public static void startReport() {
		ExtentSparkReporter spark = new ExtentSparkReporter("reports/extent-report.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	@Before
	public void setup(Scenario scenario) {
		 driver = DriverFactory.getDriver();
	        driver.get("https://www.saucedemo.com/");
	        test = extent.createTest(scenario.getName());
	}

	@After
	public void teardownDriver(Scenario scenario) {
		if (scenario.isFailed()) {
			// Screenshot for cucumber report
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Failure Screenshot");

			// Save screenshot to file for extent report
			try {
				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File dest = new File("reports/screenshots/" + scenario.getName() + ".png");
				dest.getParentFile().mkdirs();
				Files.copy(src.toPath(), dest.toPath());
				test.fail("Failed at: " + scenario.getName()).addScreenCaptureFromPath(dest.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			test.pass("Scenario passed");
		}
		DriverFactory.quitDriver();
	}

	@AfterAll
	public static void endReport() {
		extent.flush();
	}

}
