package com.freecrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.baseclass.BaseClass;

public class HomePage extends BaseClass{
	
	@FindBy(xpath="//td[text()='CRMPRO']")
	WebElement crmpro;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateCRMPRO() {
		return crmpro.isDisplayed();
	}

}
