package com.automation.swaglabs.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
			spark.config().setReportName("SauceDemo Automation Report");
			spark.config().setDocumentTitle("Test Report");
			extent.attachReporter(spark);
		}
		return extent;
	}
	

}
