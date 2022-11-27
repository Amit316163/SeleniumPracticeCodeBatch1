package com.freecrm.testpages;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.freecrm.baseclass.BaseClass;
import com.freecrm.pages.LoginPage;

public class LoginPageTest extends BaseClass{
	LoginPage loginPage;
	
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeTest
	public void setUp(){
		launchBrowser();
		loginPage = new LoginPage();	
	}
	
	@Test(priority=1,enabled = false)
	public void loginPageTitleTest(){
		String title=loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM for Any Business: Online Customer Relationship Software");
	}
	
	
	@Test(priority=2)
	public void loginTest() {
		loginPage.login(prop.getvalue("username"), prop.getvalue("password"));
		String userName=loginPage.afterLogin();
		System.out.println(userName);
		System.out.println(userName.length());
		int i=userName.indexOf(':');
		System.out.println("ankita "+userName.substring(i+2));
		System.out.println("Sy "+userName.substring(8, 19));
		
		String userData=userName.substring(i+2);
		Assert.assertEquals(userData, prop.getvalue("userNameData"),"username validation after login");
		

		
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
		
	}
	
}
