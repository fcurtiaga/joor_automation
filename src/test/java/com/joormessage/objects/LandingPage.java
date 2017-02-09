package com.joormessage.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.joormessage.general.WaitConf;

public class LandingPage {
	
//====================	
//=====ATTRIBUTES=====
//====================
 
@FindBy(className = "login-button")	
private WebElement expandLogin;

@FindBy(id = "login-name")
private WebElement userNameField;

@FindBy(name = "data[User][password]")
private WebElement passwordField;

@FindBy(xpath = "//input[@value='Sign In']")
private WebElement signinButton;

protected WebDriver driver;



// =====CONSTRUCTORS=====
public LandingPage(WebDriver driver) {
	this.driver = driver;
}

// =====METHODS=====
	public MessagesPage login(String username, String password) {
		WaitConf.waitForElement(this.expandLogin, "CLICKABLE");
		this.expandLogin.click();

		WaitConf.waitForElement(this.userNameField, "VISIBLE");
		this.userNameField.sendKeys(username);
		WaitConf.waitForElement(this.passwordField, "VISIBLE");
		this.passwordField.sendKeys(password);

		WaitConf.waitForElement(this.signinButton, "CLICKABLE");
		this.signinButton.click();

		MessagesPage message = PageFactory.initElements(this.driver, MessagesPage.class);

		return message;

	}

}
