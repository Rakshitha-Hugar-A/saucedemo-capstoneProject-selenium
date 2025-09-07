package com.automation.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.automation.swaglabs.utils.WaitHelper;

public class ProductPage {
	private WebDriver driver;
	private WaitHelper helper;

	// Locators
	private By productTitle = By.className("title");
	private By productList = By.className("inventory_item");
	private By addToCartButton = By.cssSelector(".btn_inventory");
	private By cartBadge = By.className("shopping_cart_badge");
	private By sortDropdown = By.className("product_sort_container");
	private By productPrice = By.className("inventory_item_price");
	private By cartIcon = By.className("shopping_cart_link");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		this.helper = new WaitHelper(driver);
	}

	public String getPageTitle() {
//	        return driver.findElement(productTitle).getText();
		return helper.waitForElementVisible(productTitle, 10).getText();
	}

	public int getProductCount() {
//	        return driver.findElements(productList).size();
		return helper.waitForAllElementsVisible(productList, 10).size();
	}

	public void addFirstProductToCart() {
		helper.waitForAllElementsVisible(addToCartButton, 10).get(0).click();
	}

	// Get cart count as String (returns "0" if empty)
	public String getCartCount() {
		try {
			return driver.findElement(cartBadge).getText();
		} catch (Exception e) {
			return "0";
		}
	}

	// Select sort option from dropdown
	public void selectSortOption(String option) {
		WebElement dropdown = helper.waitForElementVisible(sortDropdown, 10);
		Select sort = new Select(dropdown);
		sort.selectByVisibleText(option);
	}

	// Verify products are sorted by price (ascending)
	public boolean verifySorting(String order) {
		List<Double> prices = driver.findElements(productPrice).stream().map(WebElement::getText) // "$9.99"
				.map(s -> s.replace("$", "")) // "9.99"
				.map(Double::parseDouble) // 9.99
				.collect(Collectors.toList());

		if (order.equalsIgnoreCase("ascending")) {
			for (int i = 0; i < prices.size() - 1; i++) {
				helper.delay(2000);
				if (prices.get(i) > prices.get(i + 1))
					return false;
			}
		} else if (order.equalsIgnoreCase("descending")) {
			for (int i = 0; i < prices.size() - 1; i++) {
				helper.delay(2000);
				if (prices.get(i) < prices.get(i + 1))
					return false;
			}
		}
		return true;
	}

	public void clickCartIcon() {
		// TODO Auto-generated method stub
//			driver.findElement(cartIcon).click();
		helper.waitForElementClickable(cartIcon, 10);

	}
}
