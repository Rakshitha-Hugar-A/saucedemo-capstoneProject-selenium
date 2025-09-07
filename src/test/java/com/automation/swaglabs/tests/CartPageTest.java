package com.automation.swaglabs.tests;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CartPageTest  extends BaseTest{
    
    @BeforeMethod
    public void pageSetup() {
        super.setUp();
        // Login first
        loginAsStandardUser();
    }
    @Test(priority=1)
    public void testCartPageTitle() {
    	productPage.clickCartIcon(); 
        String title = cartPage.getCartTitle();
        Assert.assertEquals(title, "Your Cart", "Cart page title mismatch!");
    }
    
    @Test(priority =2)
    public void testCartItemCount() {
    	productPage.addFirstProductToCart();
        productPage.clickCartIcon();
        int count = cartPage.getCartItemCount();
        Assert.assertEquals(count, 1, "Cart item count mismatch!");
    }
    
    @Test(priority = 3)
    public void testRemoveItemFromCart() {
    	productPage.addFirstProductToCart();
        productPage.clickCartIcon();
        // Remove the first product
        cartPage.removeFirstProduct();

        // Now verify cart is empty
        int count = cartPage.getCartItemCount();
        Assert.assertEquals(count, 0, "Item was not removed from the cart!");
    }
    
    @Test(priority = 5)
    public void testProceedToCheckout() {
//    	productPage.addFirstProductToCart();
        productPage.clickCartIcon();
        cartPage.clickCheckout();
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("checkout-step-one.html"), 
                "Checkout page not displayed!");
    }
    
    @Test(priority = 4)
    public void testContinueShopping() {
    	productPage.addFirstProductToCart();
        productPage.clickCartIcon();
        cartPage.clickContinueShopping();
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("inventory.html"), 
                "Did not navigate back to product page!");
    }
}
