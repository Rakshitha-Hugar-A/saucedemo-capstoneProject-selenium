package com.automation.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.swaglabs.utils.WaitHelper;

public class LoginPage  extends BasePage{
	 private WaitHelper helper;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		helper= new WaitHelper(driver);
	}
	
	    private By usernameField = By.id("user-name");
	    private By passwordField = By.id("password");
	    private By loginButton   = By.id("login-button");
	    private By errorMessage  = By.cssSelector("[data-test='error']");
	    
	    public void enterUsername(String username) {
//	        driver.findElement(usernameField).sendKeys(username);
	    	helper.waitForElementVisible(usernameField, 10).sendKeys(username);
	    }
	    
	    public void enterPassword(String password) {
//	        driver.findElement(passwordField).sendKeys(password);
	        helper.waitForElementVisible(passwordField, 10).sendKeys(password);
	    }
	    
	    public void clickLogin() {
//	    	driver.findElement(loginButton).click();
	    	helper.waitForElementClickable(loginButton, 10);
	    }
	    
	    public String getErrorMessage() {
//	        return driver.findElement(errorMessage).getText();
	        return helper.waitForElementVisible(errorMessage, 10).getText();
	    }

	

}
