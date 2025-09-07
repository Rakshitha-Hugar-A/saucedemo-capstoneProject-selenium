package com.automation.swaglabs.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.swaglabs.page.CartPage;
import com.automation.swaglabs.page.CheckoutPage;
import com.automation.swaglabs.page.LoginPage;
import com.automation.swaglabs.page.ProductPage;
import com.automation.swaglabs.utils.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {
	protected WebDriver driver;
	protected LoginPage loginPage;
	protected ProductPage productPage;
	protected CartPage cartPage;
	protected CheckoutPage checkoutPage;
	protected ExtentReports extent;
	protected ExtentTest test;


	@BeforeMethod
	public void setUp() {
		driver = DriverFactory.getDriver();
		driver.get("https://www.saucedemo.com/");

	}

	protected void loginAsStandardUser() {
		loginPage = new LoginPage(driver);
		loginPage.enterUsername("standard_user");
		loginPage.enterPassword("secret_sauce");
		loginPage.clickLogin();
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
