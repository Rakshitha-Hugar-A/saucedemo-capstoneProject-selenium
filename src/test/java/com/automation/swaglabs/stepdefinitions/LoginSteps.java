package com.automation.swaglabs.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.swaglabs.page.LoginPage;
import com.automation.swaglabs.utils.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps{
	 WebDriver driver = DriverFactory.getDriver();
	private LoginPage loginPage;
	
	@Given("I am on the login page")
	public void i_am_on_the_login_page() { 
		driver.get("https://www.saucedemo.com/");
		 loginPage = new LoginPage(driver);
	}
	
	@When("I enter username {string} and password {string}")
	public void i_enter_username_and_password(String username , String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}
	
	@When("I click the login button")
	public void clck_the_login_button() {
		loginPage.clickLogin();
		
	}
	
	@Then("I should be redirected to the inventory page")
	public void i_should_be_redirected_to_the_inventory_page() {
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
	}
	
	@Then("I should see an error message {string}")
	public void i_should_see_an_error_message(String expectedMessage) {
		Assert.assertTrue(loginPage.getErrorMessage().contains(expectedMessage));
	}
	
	
}
