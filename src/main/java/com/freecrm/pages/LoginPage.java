package com.freecrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.baseclass.BaseClass;
import com.freecrm.utilities.Reusability;

public class LoginPage extends BaseClass{
	
	Reusability res=new Reusability(driver);
	
	@FindBy(xpath="//input[@name='username']")
	WebElement username;
	
	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath="//input[@name='password']")
	WebElement password;

	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//frame[@name='mainpanel']")
	WebElement mainPanelFrame;
	
	@FindBy(xpath = "(//td[@class='headertable'])[1]//td[1]")
	WebElement adminName;
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}

	public String validateLoginPageTitle(){
		return driver.getTitle();
	}

	public void login(String usern,String pass) {
		username.sendKeys(usern);
		password.sendKeys(pass);
		loginBtn.click();
		res.switchToFrame(mainPanelFrame);
		
	
		
		
	}
	public String afterLogin() {
	return	adminName.getText();
	}
	
}

