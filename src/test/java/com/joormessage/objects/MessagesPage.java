package com.joormessage.objects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.joormessage.general.CONF;
import com.joormessage.general.DriverConf;
import com.joormessage.general.WaitConf;

public class MessagesPage {


	//====================	
	//=====ATTRIBUTES=====
	//====================
	 
	/*@FindBy(linkText = "Send a Message")	
	private WebElement sendMessageLink;*/
	
	@FindBy(xpath = "//div[@id='nav-container']/ul/li[4]/div/a/span")
	private WebElement messageNav;
	
	@FindBy(xpath = "//a[contains(text(),'Compose Mail')]")
	private WebElement composeEmail;
	
	@FindBy(id = "MessageSendToAllConnections")
	private WebElement sendToAllRadio;
	
	@FindBy(id = "MessageSubject")
	private WebElement subjectText;
	
	@FindBy(id = "MessageSendToSelectConnections")
	private WebElement sendtoSelectConnectionRadio;
	
	@FindBy(name = "recipient")
	private WebElement recipientText;


	@FindBy(css = ".ui-autocomplete.ui-menu.ui-widget.ui-widget-content.ui-corner-all")
	private List<WebElement> menuItemRecipients;
	
	@FindBy(id = "MessageBody")
	private WebElement messageText;
	
	@FindBy(xpath = "//*[@id='MessageSendForm']/a")
	private WebElement sendButton;
	
	@FindBy(xpath = "//*[@id='sub-nav-messages']//*[text()='Sent Messages']")
	private WebElement sentLink;
	
	@FindBy(xpath = "//*[contains(@id,'j-message-')]//td[3]//p//*")
	private List<WebElement> emailSent; 
	
	
	private String subjectMessage = "";
	private String bodyMessage = "";
	private static String subjectAndBody = "";

	
	
	
	protected WebDriver driver = null;
	protected Actions builder = null;


	
	public MessagesPage(WebDriver driver){
		this.driver = driver;
	}

	
	public void goToSendAMessage() {

			
		WaitConf.waitForElement(this.messageNav, "VISIBLE");
		this.messageNav.click();
		
		
		
		WaitConf.waitForElement(this.composeEmail, "CLICKABLE");
		this.composeEmail.click();
		
	}
	
	public void composeTheEmailToAllConnections() {
		
		WaitConf.waitForElement(this.sendToAllRadio, "CLICKABLE");
		this.sendToAllRadio.click();
		
		WaitConf.waitForElement(this.subjectText, "VISIBLE");
		
	    subjectMessage = DriverConf.getWDriverInstance().generateRamdomSubject("Email Subject");
		
		this.subjectText.sendKeys(subjectMessage);
		
		WaitConf.waitForElement(this.messageText, "VISIBILE");
		
		bodyMessage = DriverConf.getWDriverInstance().generateRamdomBody("Test: Send an email to All Connections");

		this.messageText.sendKeys(bodyMessage);
		
		
	} 
	
   public void composeTheEmailToSpecificConnection() {
		
	    subjectMessage = "";
	    bodyMessage = "";
	   
		WaitConf.waitForElement(this.sendtoSelectConnectionRadio, "CLICKABLE");
		this.sendtoSelectConnectionRadio.click();
		
		WaitConf.waitForElement(this.recipientText, "VISIBLE");
		
		this.recipientText.sendKeys("JOOR");
		
		
		WaitConf.waitForElement(this.menuItemRecipients.get(0), "VISIBLE");

		
		for (WebElement element : this.menuItemRecipients) {
			element.click();
		}
		
		
		
		WaitConf.waitForElement(this.subjectText, "VISIBLE");
		
	    subjectMessage = DriverConf.getWDriverInstance().generateRamdomSubject("Email Subject");
		
		this.subjectText.sendKeys(subjectMessage);
		
		WaitConf.waitForElement(this.messageText, "VISIBILE");
		
		bodyMessage = DriverConf.getWDriverInstance().generateRamdomBody("Test: Send an email to Specific Connections");

		this.messageText.sendKeys(bodyMessage);
		
		
	} 
	
	public void clickOnSendButton() {
		
		WaitConf.waitForElement(this.sendButton, "CLICKABLE");
		this.sendButton.click();
	}
	
	public void goToSentTab() {
		
		WaitConf.waitForElement(this.sentLink, "SLEEP");
		this.sentLink.click();

	}
	
	public boolean checkEmailSent() {

		
		subjectAndBody = subjectMessage + " - " + bodyMessage ; 

		WaitConf.waitForElement(this.emailSent.get(0), "VISIBLE");


		for (WebElement element : this.emailSent) {
			if (element.getText().equals(subjectAndBody)) {
			 return true;			 
			}
		}

		return false;
	}

}
