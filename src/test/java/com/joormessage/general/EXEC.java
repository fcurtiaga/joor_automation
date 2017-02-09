package com.joormessage.general;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.testng.Reporter;


public final class EXEC {

	public static void messageTestResult(String testExecutionResult) {
		String message = "---TEST CASE " + testExecutionResult + "---";
		message = message
				+ "\n______________________________________________________________________________________________________________________";

		log(message);

	}
	
	public static void log(String pMessage) {

		if (CONF.FLAG_REPORTER_LOGS) {
			Reporter.log(pMessage, CONF.FLAG_REPORTER_STANDARD_OUTPUT);
		}

	}


	public static void messageScreenshotFlagOn(String testExecutionResult, String pFileAbsolutePath) {

		String message = "\n" + testExecutionResult + " SCREENSHOT IN: " + pFileAbsolutePath;
		log(message);
	}

	public static void messageStartTestExecutionInformation(String methodName, String testLinkName) {
		Calendar date = new GregorianCalendar();
		int hour = date.get(Calendar.HOUR_OF_DAY);
		int min = date.get(Calendar.MINUTE);
		int sec = date.get(Calendar.SECOND);

		String message = "=======================================================================================================================";
		message += "\nStarting Execution for: " + methodName + " || " + testLinkName;
		message += "\nBrowser: " + CONF.BROWSER;

		message += "\nTime Start Test: " + hour + ":" + min + ":" + sec + "\n";

		log(message);
	}

	public static void messageFinishTestExecutionInformation(String methodName, String testLinkName) {
		Calendar date = new GregorianCalendar();
		int hour = date.get(Calendar.HOUR_OF_DAY);
		int min = date.get(Calendar.MINUTE);
		int sec = date.get(Calendar.SECOND);

		String message = "______________________________________________________________________________________________________________________";

		message += "\nFinishing Execution for: " + methodName + " || " + testLinkName;
		message += "\nApplication: " + CONF.APP_SELECTED;
		message += "\nBrowser: " + CONF.BROWSER;

		message += "\nTime Start Test: " + hour + ":" + min + ":" + sec + "\n";
		message += "=======================================================================================================================";
		log(message);
	}

}
