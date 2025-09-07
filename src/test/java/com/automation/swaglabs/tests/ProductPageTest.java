package com.automation.swaglabs.tests;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ProductPageTest extends BaseTest {
    
    @BeforeMethod
    public void setUp() {
    	super.setUp();
    	loginAsStandardUser();
    }

    @Test
    public void testProductPageTitle() {
        String title = productPage.getPageTitle();
        Assert.assertEquals(title, "Products", "Product page title mismatch!");
    }
    
    @Test
    public void testAddProductToCart() {
        productPage.addFirstProductToCart();
        String cartCount = productPage.getCartCount();
        Assert.assertEquals(cartCount, "1", "Cart count mismatch after adding product!");
    }
    
    @Test
    public void testCartIconNavigation() {
        productPage.addFirstProductToCart();
        productPage.clickCartIcon();
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("cart.html"), "Cart page not displayed!");
    }
    
    @Test
    public void testSortProductsAscending() {
        productPage.selectSortOption("Price (low to high)");
        Assert.assertTrue(productPage.verifySorting("ascending"), "Products not sorted ascending!");
    }
    
    @Test
    public void testSortProductsDescending() {
        productPage.selectSortOption("Price (high to low)");
        Assert.assertTrue(productPage.verifySorting("descending"), "Products not sorted descending!");
    }
}
