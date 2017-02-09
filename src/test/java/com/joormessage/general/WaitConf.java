package com.joormessage.general;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class WaitConf {

	
	public static void waitForElement(WebElement webElement, String waitFor) {
		int elementTimeout = CONF.WEBDRIVER_TIMEOUT;

		WebDriverWait wbWait = new WebDriverWait(DriverConf.getWDriverInstance().getDriver(), elementTimeout);

		switch (waitFor.toUpperCase()) {
		case "CLICKABLE":
			webElement = wbWait.until(ExpectedConditions.elementToBeClickable(webElement));
			break;
		case "SELECTED":
			waitForElement(null, "LOADING_NOT_DISPLAYED");
			wbWait.until(ExpectedConditions.elementToBeSelected(webElement));
			break;
		case "VISIBLE":
			waitForElement(null, "LOADING_NOT_DISPLAYED");
			webElement = wbWait.until(ExpectedConditions.visibilityOf(webElement));
			break;

		case "PAUSE":
			DriverConf.getWDriverInstance().getDriver().manage().timeouts().implicitlyWait(elementTimeout,
					TimeUnit.SECONDS);
		    break;	
		case "SLEEP":
			try {
				Thread.sleep(elementTimeout * 150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			break;
		}	
   	
	}

}	

