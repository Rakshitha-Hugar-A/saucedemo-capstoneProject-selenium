package com.automation.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutPageTest extends BaseTest {

	@BeforeMethod
	public void pageSetUp() {
		super.setUp();
		loginAsStandardUser();
		productPage.addFirstProductToCart();
		productPage.clickCartIcon();
		cartPage.clickCheckout();

	}

	@Test(priority =1)
	public void testCheckOutWithValidInfo() {
		checkoutPage.enterCheckOutInfo("Rakshitha", "Hugar", "12345");
		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isOrderSummaryDisplayed(), "Order Summary is not displayed!");
	}

	@Test(priority =2)
	public void testCheckoutWithMissingFields() {
		checkoutPage.enterCheckOutInfo("", "Rita", "");
		checkoutPage.clickContinue();
		String error = checkoutPage.getErrorMessage();
		Assert.assertTrue(error.contains("Error"), " Error Message is not displayed for missing field");
	}

	@Test(priority =3)
	public void testOrderSummaryDetals() {
		checkoutPage.enterCheckOutInfo("Shiv", "Ram", "1235");
		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isOrderSummaryDisplayed(), "Order summary page is not visible!");

	}

	@Test(priority =4)
	public void testOrderCompletionAndVerifyConfirmation() {
		checkoutPage.enterCheckOutInfo("Rohith", "sharma", "4567");
		checkoutPage.clickContinue();
		checkoutPage.clickFinish();
		String confirmation = checkoutPage.getConfirmationMessage();
		Assert.assertEquals(confirmation, "Thank you for your order!", "confirmation message mismatched.");
	}
	
	@Test(priority =5)
	public void testCancelButton() {
		checkoutPage.enterCheckOutInfo("Richa", "Verma", "4567");
		checkoutPage.clickCancel();
		 String currentUrl = driver.getCurrentUrl();
		 Assert.assertTrue(currentUrl.contains("cart.html"),"Clicking cancel button should navigate back to cart ");
		
	}
	
	@Test (priority =6)
	public void testBackToHomeButton() {
		checkoutPage.enterCheckOutInfo("Divya", "Singh", "4567");
		checkoutPage.clickContinue();
		checkoutPage.clickFinish();
		checkoutPage.backtohome();
		String currentUrl = driver.getCurrentUrl();
		 Assert.assertTrue(currentUrl.contains("inventory.html"),"Did not navigate back to Products page!.");
	}

}
