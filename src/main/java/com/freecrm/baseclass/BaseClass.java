package com.freecrm.baseclass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.freecrm.utilities.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

//properties handling
//launch the browser
//maximize,cookies,pageloadtime

public class BaseClass {
	
public static	WebDriver driver;
	public Configuration prop;

	

	
	public BaseClass() {
		 prop=new Configuration();
	}
	
	public  void launchBrowser( ) {
		switch (prop.getvalue("browserType")) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}
		driver.get(prop.getvalue("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

}

