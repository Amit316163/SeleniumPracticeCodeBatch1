package com.freecrm.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.freecrm.reports.ExtentReport;

public class Liteners extends ExtentReport implements ITestListener  {
	
	public static ExtentReports extent;
	public static ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
	
		System.out.println("test cases started");
		 test=extent.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("test success");
		test.log(Status.PASS, result.getMethod().getMethodName()+" is Pass");
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
		test.log(Status.FAIL, result.getThrowable());
		
		//add screenshot for failed test.
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		
		String screenshotPath = System.getProperty("user.dir")+
				"/Reports/Screenshots/"+actualDate+".jpeg";
		File dest = new File(screenshotPath);
		
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	System.out.println("Test cases Skipped");
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Automation start ");
		
		 extent=ExtentReport.setupExtentReport();
		
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Automation stop");
		extent.flush();
		
	}

}
