package com.automation.swaglabs.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	private WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }
    public void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        delay(4000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
       
    }

    public void waitForElementClickable(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        delay(4000);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

	public List<WebElement> waitForAllElementsVisible(By  locator, int timeout) {
		// TODO Auto-generated method stub
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 delay(4000);
	        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public void clickWhenClickable(By locator, int timeOut) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	    delay(4000);
	    element.click();
	}


}
