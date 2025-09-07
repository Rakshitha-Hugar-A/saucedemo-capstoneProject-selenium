package com.automation.swaglabs.stepdefinitions;

import org.testng.Assert;

import com.automation.swaglabs.page.LoginPage;
import com.automation.swaglabs.page.ProductPage;
import com.automation.swaglabs.utils.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductPageSteps {
	   private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	   private ProductPage productPage = new ProductPage(DriverFactory.getDriver());
	    
	    // Background: login with valid credentials
	    @Given("I am logged in with valid credentials")
	    public void i_am_logged_in_with_valid_credentials() {
	        loginPage.enterUsername("standard_user");
	        loginPage.enterPassword("secret_sauce");
	        loginPage.clickLogin();
	    }
	    
	    // Verify page title
	    @Then("I should see {string} as the page title")
	    public void i_should_see_as_the_page_title(String expectedTitle) {
	        Assert.assertEquals(expectedTitle, productPage.getPageTitle());
	    }
	    
	 // Add product to cart
	    @When("I add a product to the cart")
	    public void i_add_a_product_to_the_cart() {
	        productPage.addFirstProductToCart();
	    }
	    
	    // Verify cart count
	    @Then("the cart count should be {string}")
	    public void the_cart_count_should_be(String expectedCount) {
	        Assert.assertEquals(expectedCount, productPage.getCartCount());
	    }
	    
	    // Verify sorting by Price (low to high)
	    @When("I sort products by {string}")
	    public void i_sort_products_by(String option) {
	        productPage.selectSortOption(option);
	    }

	    @Then("products should be sorted in ascending order of price")
	    public void products_should_be_sorted_in_ascending_order_of_price() {
	        Assert.assertTrue(productPage.verifySorting("ascending"), 
	                "Products are not sorted in ascending order");
	    }
	    
	    @Then("products should be sorted in descending order of price")
	    public void products_should_be_sorted_in_descending_order_of_price() {
	        Assert.assertTrue(productPage.verifySorting("descending"), 
	                "Products are not sorted in descending order");
	    }

}
