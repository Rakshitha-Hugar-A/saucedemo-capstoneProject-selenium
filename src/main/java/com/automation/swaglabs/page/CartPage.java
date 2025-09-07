package com.automation.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.swaglabs.utils.WaitHelper;

public class CartPage {
	private WebDriver driver;
	private WaitHelper helper;
	
	 // Locators
    private By cartTitle = By.className("title"); 
    private By cartItems = By.className("cart_item");
    private By checkoutButton = By.id("checkout");
    private By removeButton = By.cssSelector(".cart_button"); 
    private By continueShoppingButton = By.id("continue-shopping");

	
	public CartPage(WebDriver driver) {
	    this.driver = driver;
	    this.helper = new WaitHelper(driver);
	}
	
	 public String getCartTitle() {
//	        return driver.findElement(cartTitle).getText();
		    return helper.waitForElementVisible(cartTitle, 10).getText();
	    }
	 
	 public int getCartItemCount() {
	        return driver.findElements(cartItems).size();
	        
	    }
	 
	 public void removeFirstProduct() {
//		    driver.findElements(removeButton).get(0).click();
		 helper.waitForAllElementsVisible(removeButton, 10).get(0).click();
		 helper.delay(2000);
		}
	 
	 public void clickCheckout() {
//	        driver.findElement(checkoutButton).click();
		 helper.clickWhenClickable(checkoutButton, 10);
		 helper.delay(2000);
	    }
	 public void clickContinueShopping() {
//		    driver.findElement(continueShoppingButton).click();
		 helper.waitForElementClickable(continueShoppingButton, 10);
		 helper.delay(2000);
		}
	
	

}
