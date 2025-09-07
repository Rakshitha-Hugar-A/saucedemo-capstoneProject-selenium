package com.automation.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.swaglabs.page.LoginPage;

public class LoginPageTest extends BaseTest{
	
	private LoginPage loginPage;
	
	@BeforeMethod
    public void pageSetup() {
		
         loginPage = new LoginPage(driver); 
    }

	   @Test(priority =1)
	   public void testValidLogin() {
//		   LoginPage loginPage = new LoginPage(driver);
		   loginPage.enterUsername("standard_user");
		   loginPage.enterPassword("secret_sauce");
		   loginPage.clickLogin();
		   String currentUrl =driver.getCurrentUrl();
		   Assert.assertTrue(currentUrl.contains("inventory.html"), "Login Failed user has not redirected to the inventory page.");
		   
	   }
	   
	   @Test(priority=2)
	   public void testInvalidLogin() {
		   loginPage.enterUsername("wrong_user");
	        loginPage.enterPassword("wrong_pass");
	        loginPage.clickLogin();

	        String errorText = loginPage.getErrorMessage();
	        Assert.assertTrue(errorText.contains("Username and password do not match"),
	                "Error message not displayed correctly!");
		   
	   }
	   
}
