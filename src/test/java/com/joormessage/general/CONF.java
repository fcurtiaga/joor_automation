package com.joormessage.general;

import java.util.Properties;

import com.joormessage.propertyParser.PropertyAccess;


/**
 * Class to set the static variables that will be used across
 * the tests execution.
 * @author fcurtiaga
 *
 */
public final class CONF {

	/*
	 * ======================================================================
	 * ========================== FILE PATHS ================================
	 * ======================================================================
	 */
	private static final Properties prop = PropertyAccess.getProperties();
	public static final String PROPERTY_FILE_PATH = "/Joor.properties";
	public static String APP_SELECTED;

	private static final String ROOT_PATH = ".";
	public static final String IE_DRIVER_PATH = ROOT_PATH + "\\BROWSER_DRIVERS\\IEDriverServer.exe";
	public static final String CHROME_DRIVER_PATH = ROOT_PATH + "\\BROWSER_DRIVERS\\chromedriver.exe";
	public static final String REMOTEDRIVERS_PATH = ROOT_PATH;
	public static final String FAIL_SCREENSHOT_PATH = ROOT_PATH + "\\FAIL_TESTS_SCREENSHOTS\\";
	public static final String PASS_SCREENSHOT_PATH = ROOT_PATH + "\\PASS_TESTS_SCREENSHOTS\\";
	public static final String SKIP_SCREENSHOT_PATH = ROOT_PATH + "\\SKIP_TESTS_SCREENSHOTS\\";

	/*
	 * ======================================================================
	 * ================== ENVIRONMENT CONSTANTS =============================
	 * ======================================================================
	 */

	public final static String BROWSER = prop.getProperty("env.browser");
	public final static String ENVIRONMENT_JOOR_URL = prop.getProperty("env.joor.url");
	public final static int WEBDRIVER_TIMEOUT = Integer.parseInt(prop.getProperty("webdriver.timeout"));

	/*
	 * ===================================================================
	 * ============================== USERS ==============================
	 * ===================================================================
	 */
	public static final String JOOR_USERNAME1 = prop.getProperty("user.joor.username");
	public static final String JOOR_PASSWORD1 = prop.getProperty("user.joor.password");

	// ======================================================================
	public static final boolean FLAG_TAKE_FAIL_SCREENSHOT = Boolean
			.parseBoolean(prop.getProperty("framework.flag.screenshot.fail"));

	public static final boolean FLAG_TAKE_PASS_SCREENSHOT = Boolean
			.parseBoolean(prop.getProperty("framework.flag.screenshot.pass"));

	public static final boolean FLAG_TAKE_SKIP_SCREENSHOT = Boolean
			.parseBoolean(prop.getProperty("framework.flag.screenshot.skip"));

	public static final boolean FLAG_REPORTER_LOGS = Boolean
			.parseBoolean(prop.getProperty("framework.flag.reporter.log"));

	public static final boolean FLAG_REPORTER_STANDARD_OUTPUT = Boolean
			.parseBoolean(prop.getProperty("framework.flag.reporter.standard.output"));

	public static final int FLAG_WEBELEMENT_DEFAULT_TIMEOUT = Integer
			.parseInt(prop.getProperty("framework.flag.webelement.timeout"));


	public CONF() {

	}
	
	
}
