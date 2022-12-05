package com.freecrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.baseclass.BaseClass;
import com.freecrm.utilities.Reusability;

public class CompaniesPage extends BaseClass {
	
	Reusability res=new Reusability(driver);
	
	@FindBy(xpath = "//a[text()='Companies']")
	WebElement companies;

	@FindBy(xpath = "//a[text()='New Company']")
	WebElement newCompany;
	
	@FindBy(id = "company_name")
	WebElement txtCompanyName;
	
	@FindBy(name = "industry")
	WebElement txtIndustryName;
	
	@FindBy(name = "phone")
	WebElement txtPhoneNum;
	
	@FindBy(name = "status")
	WebElement dropdownStatus;
	
	@FindBy(xpath = "(//td[@class='tabs_header'])[1]")
	WebElement companyName;
	
	@FindBy(xpath = "(//form[@id='companyForm' ]//input[@value='Save'])[1]")
	WebElement btnSave;
	
	

	// Initializing the Page Objects:
	public CompaniesPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToNewCompany(){
		
		res.elementHighlighter(companies);
		res.hoverTheMouse(companies);
		res.clickAnElement(newCompany);
	}
	
	public void createNewCompany(String val1,String val2,String val3,String val4) {
		
		res.doSendKeys(txtCompanyName, val1);
		res.doSendKeys(txtIndustryName, val2);
		res.doSendKeys(txtPhoneNum, val3);
		res.selectByVisbleText(dropdownStatus, val4);
		
		res.elementHighlighter(btnSave);
		res.clickAnElement(btnSave);
	
		
	}
	public String textValidation() throws InterruptedException {
		Thread.sleep(2000);
		return companyName.getText();
	}
	
	

}
