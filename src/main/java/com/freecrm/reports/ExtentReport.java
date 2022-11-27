package com.freecrm.reports;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.freecrm.baseclass.BaseClass;


public class ExtentReport extends BaseClass {
	
	public static ExtentReports setupExtentReport() {
		
	
	
	SimpleDateFormat simple=new SimpleDateFormat("yyMMddHHmmss");
	Date date=new Date();
	String actualDate=simple.format(date);
	
	String reportPath=System.getProperty("user.dir")+"/Reports/ExtentReport_"+actualDate+".html";
	
	ExtentSparkReporter sparkReport=new ExtentSparkReporter(reportPath);
	
	ExtentReports extent=new ExtentReports();
	extent.attachReporter(sparkReport);
	
	sparkReport.config().setDocumentTitle("FreeCRM");
	sparkReport.config().setTheme(Theme.DARK);
	sparkReport.config().setReportName("CyberSuccess:-FreeCRM report");
	
	return extent;
	
	
	}

}
