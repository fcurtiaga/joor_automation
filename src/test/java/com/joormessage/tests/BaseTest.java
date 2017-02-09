package com.joormessage.tests;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.joormessage.general.CONF;
import com.joormessage.general.DriverConf;
import com.joormessage.general.EXEC;

public class BaseTest {
	// ====================ATTRIBUTES====================
	protected String methodName;
	protected String testName;
	protected SoftAssert softAssert;

	// ====================METHODS====================
	@BeforeSuite(alwaysRun = true)
	protected void beforeSuite() {
		new CONF();
	}

	/**
	 * This method initializes all the variables for instantiating the
	 * environment before each script runs.
	 * 
	 * @param method
	 */
	@BeforeMethod(alwaysRun = true)
	protected void before(Method method, ITestResult testResult) {
		this.softAssert = new SoftAssert();
		this.getTestNames(method);

		EXEC.messageStartTestExecutionInformation(this.methodName, this.testName);
		testResult.setStatus(-1);
	}

	
	@AfterMethod(alwaysRun = true)
	protected void after(ITestResult testResult) throws IOException {
		String testExecutionResult = "";

		switch (testResult.getStatus()) {
		case ITestResult.FAILURE:
			testExecutionResult = "FAILED";
			break;

		case ITestResult.SUCCESS:
			testExecutionResult = "PASSED";
			break;

		case ITestResult.SKIP:
			testExecutionResult = "SKIPPED";
			break;

		default:
			break;
		}

		EXEC.messageTestResult(testExecutionResult);
		EXEC.messageFinishTestExecutionInformation(this.methodName, this.testName);
		DriverConf.getWDriverInstance().closeBrowserAndDriver();
	}

	protected void skipTest(String pMessage) {
		EXEC.log(pMessage);
		throw new SkipException(pMessage);
	}

	protected void failTest(String pMessage) {
		Assert.fail(pMessage);
	}

	private void getTestNames(Method method) {
		this.methodName = method.getName();

		Annotation[] testAnnotations = method.getAnnotations();

		this.testName = testAnnotations[0].toString();
		int p = this.testName.indexOf("testName=");
		this.testName = this.testName.substring(p + 9, testName.length() - 1);

	}
	
	
	

}
