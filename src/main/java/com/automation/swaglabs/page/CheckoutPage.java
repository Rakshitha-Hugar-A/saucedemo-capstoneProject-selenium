package com.automation.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.swaglabs.utils.WaitHelper;

public class CheckoutPage {
	
//	private WebDriver driver;
	private WaitHelper helper;
	//locators
	private By firstNameField = By.id("first-name");
	private By lastNameField = By.id("last-name");
	private By zipCode = By.id("postal-code");
	private By continueButton = By.id("continue");
	private By errorMessage = By.cssSelector("[data-test ='error']");
	private By finishButton = By.id("finish");
	private By summaryContainer = By.id("checkout_summary_container");
    private By confirmationMessage = By.className("complete-header");
    private By cancelbtn = By.id("cancel");
    private By backToHomebtn = By.id("back-to-products");
    
    public CheckoutPage(WebDriver driver) {
		helper = new WaitHelper(driver);
	}
 
    public void enterCheckOutInfo(String firstName , String lastName, String zip) {
    	helper.waitForElementVisible(firstNameField,10).sendKeys(firstName);
    	helper.waitForElementVisible(lastNameField,10).sendKeys(lastName);
    	helper.waitForElementVisible(zipCode,10).sendKeys(zip);   	
    }    
    public void clickContinue() {
    	helper.clickWhenClickable(continueButton, 10);
    }
   
    public boolean isOrderSummaryDisplayed() {
        return helper.waitForElementVisible(summaryContainer, 10).isDisplayed();
    }  
    public void clickFinish() {
    	helper.clickWhenClickable(finishButton, 10);
    }
 
    public String getConfirmationMessage() {
    	return helper.waitForElementVisible(confirmationMessage, 10).getText();
    }

	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return helper.waitForElementVisible(errorMessage, 10).getText();
	}

	public void clickCancel() {
		helper.clickWhenClickable(cancelbtn, 10);	
		helper.delay(2000); 
	}
	public void backtohome() {
		helper.clickWhenClickable(backToHomebtn, 10);
		helper.delay(2000); 
	}
	

	
	
	
	   
	

}
