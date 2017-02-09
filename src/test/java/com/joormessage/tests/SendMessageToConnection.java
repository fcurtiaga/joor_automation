package com.joormessage.tests;

import org.testng.annotations.Test;

import com.joormessage.general.CONF;
import com.joormessage.general.DriverConf;
import com.joormessage.general.EXEC;
import com.joormessage.objects.LandingPage;
import com.joormessage.objects.MessagesPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class SendMessageToConnection extends BaseTest {

	@Test(testName = "TEST-002: Verify that the user will be able to send an email to Specific Connection (Random) with out attachments")
	public void sendMessageToAllConnectins() {

		EXEC.log("Navigating to JOORAccess website.");
		LandingPage landingPage = DriverConf.getWDriverInstance().goToJoor();

		EXEC.log("Log into JOOR system");
		MessagesPage message = landingPage.login(CONF.JOOR_USERNAME1, CONF.JOOR_PASSWORD1);

		EXEC.log("Clicking on Send a Message button");

		message.goToSendAMessage();

		EXEC.log("Composing the email to be sent to specific connection");

		message.composeTheEmailToSpecificConnection();

		EXEC.log("Click on Send button");
		message.clickOnSendButton();
		
		EXEC.log("Go to Sent view");

		message.goToSentTab();
		
		EXEC.log("Verifying if the email was correctly sent out");
        boolean found = message.checkEmailSent();
		
        Assert.assertTrue(found, "The email was not sent correctly");;
        
	}
}
