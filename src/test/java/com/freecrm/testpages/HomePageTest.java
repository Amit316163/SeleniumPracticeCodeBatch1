package com.freecrm.testpages;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.freecrm.baseclass.BaseClass;
import com.freecrm.pages.HomePage;
import com.freecrm.pages.LoginPage;

public class HomePageTest  extends BaseClass{
	LoginPage lp;
	HomePage hp;
	
	public HomePageTest() {
		super();
	}
	@BeforeTest
	public void setUp(){
		launchBrowser();
		lp=new LoginPage();
		hp=new HomePage();
		lp.login(prop.getvalue("username"), prop.getvalue("password"));
		
	}
	
	@Test
	public void validateHomePageCRMPRO() {
	
	boolean data=	hp.validateCRMPRO();
	Assert.assertTrue(data);
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
		
	}

}
