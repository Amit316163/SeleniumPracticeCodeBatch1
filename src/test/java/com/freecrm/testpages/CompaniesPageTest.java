package com.freecrm.testpages;

import java.util.HashMap;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.freecrm.baseclass.BaseClass;
import com.freecrm.pages.CompaniesPage;
import com.freecrm.pages.HomePage;
import com.freecrm.pages.LoginPage;
import com.freecrm.utilities.ExcelHandling;

public class CompaniesPageTest extends BaseClass{
	LoginPage lp;
	HomePage hp;
	CompaniesPage cp;
	ExcelHandling excel=new ExcelHandling("sheet2");


	public CompaniesPageTest(){
		super();
	}

	@DataProvider(name="Data1")
	public Object[][] companyData1() {
		return new Object[][] {{"TCS1","IT Service1","2323232323","Active"},
		};
	}
	
	@DataProvider(name="testdata1")
	public Object[][] companyDetailsFromExcel() {

		Object[][] obj = new Object[excel.getRowCount()][1];

		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData = excel.getTestDataInMap(i);
			obj[i-1][0] = testData;
		}
		return obj;


	}


	@BeforeTest
	public void setUp(){
		launchBrowser();
		lp=new LoginPage();
		hp=new HomePage();
		lp.login(prop.getvalue("username"), prop.getvalue("password"));
		cp=new CompaniesPage();

	}

	@Test(dataProvider = "testdata1")
	public void createANewComapny(Object object) {
		HashMap<String, String> testData=(HashMap<String, String>)object;

//		System.out.println(testData.get("CompanyName"));
//		System.out.println(testData.get("Industry"));
//		System.out.println(testData.get("Number"));
//		System.out.println(testData.get("Status"));
		cp.navigateToNewCompany();
		cp.createNewCompany(testData.get("CompanyName"),testData.get("Industry"),testData.get("Number"),testData.get("Status"));
	}






	@AfterTest
	public void tearDown(){
		driver.quit();

	}



}
