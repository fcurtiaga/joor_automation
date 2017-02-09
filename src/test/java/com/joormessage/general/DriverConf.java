package com.joormessage.general;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.joormessage.objects.LandingPage;



public class DriverConf {
	
	// ATTRIBUTES=============================================================
		private static DriverConf INSTANCE = null;
		private static ThreadLocal<WebDriver> threadedDriver = null;
		private Actions builder;

		// CONSTRUCTORS=============================================================
		public static DriverConf getWDriverInstance() {
			if (INSTANCE == null) {
				INSTANCE = new DriverConf();
			}
			return INSTANCE;
		}

		private DriverConf() {
			threadedDriver = new ThreadLocal<>();
		}

		public WebDriver getDriver() {
			WebDriver d = null;
			try {
				d = threadedDriver.get();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return d;
		}

		/**
		 * @param browser
		 *            receives strings (all in uppercase) to identify the browser
		 *            driver needed.
		 * @param url
		 *            receives the URL desired to navigate to.
		 * @param timeout
		 *            receives an integer specifying the default timeout for each
		 *            script.
		 * 
		 */
		public void setDriverParameters() {

			this.setLocalWebDriverParameters();
			this.setWebDriverParameters();
		}

		// METHODS=============================================================

		/**
		 * @param browser
		 *            Specifies the browser to instantiate.
		 */
		private void setLocalWebDriverParameters() {
			RemoteWebDriver rwd = null;
			switch (CONF.BROWSER) {
			case "IE":
				System.setProperty("webdriver.ie.driver", CONF.IE_DRIVER_PATH);
				rwd = new InternetExplorerDriver();
				break;
			case "FIREFOX":
				rwd = new FirefoxDriver();
				break;
			case "CHROME":

				System.setProperty("webdriver.chrome.driver", CONF.CHROME_DRIVER_PATH);
				rwd = new ChromeDriver();
				break;
			}
			threadedDriver.set(rwd);

		}

		

		/**
		 * Deletes all cookies, maximizes the window and sets the default timeout
		 * for the scripts.
		 * 
		 * @param timeout
		 *            Specifies the default timeout for each script.
		 */
		private void setWebDriverParameters() {
			this.getDriver().manage().deleteAllCookies();
			this.getDriver().manage().window().maximize();
			this.getDriver().manage().timeouts().setScriptTimeout(CONF.WEBDRIVER_TIMEOUT, TimeUnit.SECONDS);
		}

		/**
		 * This method navigates to the URL specified in the constructor.
		 * 
		 * @param url
		 *
		 */
		private void goToURL(String url) {
			this.setDriverParameters();
			this.getDriver().navigate().to(url);
		}

		public LandingPage goToJoor() {
			this.goToURL(CONF.ENVIRONMENT_JOOR_URL);
			CONF.APP_SELECTED = "JOOR";

			return PageFactory.initElements(INSTANCE.getDriver(), LandingPage.class);

		}
		
		/*public void mouseOver(WebElement element) {
		builder = new Actions(getDriver());
		builder.moveToElement(element).perform();
		}
		*/
		public String generateRamdomSubject(String message) {
			 int value = (int)(Math.random() * 1000 + 1);
		     message = message + String.valueOf(value);
		     return message;
			
		}
		public String generateRamdomBody(String message) {
			 int value = (int)(Math.random() * 1000 + 1);
		     message = message + String.valueOf(value);
		     return message;
			
		}
		
		public void closeBrowserAndDriver() {
			this.getDriver().close();
			try {
				Thread.sleep(3000);
				this.getDriver().quit();
			} catch (Exception e) {
			}

		}

}
