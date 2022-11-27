package com.freecrm.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class Reusability {
	
	//click//send//wait//frame//dropdown/windowHandling//JavaScriptExecutor//Alerts/Screenshot/
	
	WebDriver driver;
	SimpleDateFormat simple=new SimpleDateFormat("yyMMddHHmmss");
	String dateFormat=simple.format(new Date());

	public Reusability(WebDriver driver) {
		this.driver=driver;

	}
	
	//click on webelement
	public void clickAnElement(WebElement element) {
		element.click();

	}
	
	public void selectRadioButtonValue(List<WebElement> element, String valueToBeSelected) {
		for (WebElement ref : element) {
			if(ref.getText().equalsIgnoreCase(valueToBeSelected)) {
				ref.click();
				break;
			}
			
		}
	}
	public void selectCheckBoxes(List<WebElement> element, String checks) {
		String[] checksArray = checks.split(",");
		for (String str : checksArray) {   
			for (WebElement ele : element) {
				if(ele.getText().equalsIgnoreCase(str)) {
					ele.click();
					break;
				}
			}
		}
		
	}
	public void doSendKeys(WebElement element,String val) {
		element.clear();
		element.sendKeys(val);

	}
	public void switchToFrame(WebElement frameNameOrId) {
		driver.switchTo().frame(frameNameOrId);
	}

	public void switchToAlert(WebElement frameNameOrId) {
		Alert alt=driver.switchTo().alert();
		alt.accept();
	}

	public void switchToChildWindow() {
		Set<String> winHandles=driver.getWindowHandles();
		Iterator<String> it=winHandles.iterator();
		String parentWin=it.next();
		String newWin=it.next();
		driver.switchTo().window(newWin);

	}

	public void switchToNextWindow( String firstWindow, String secondWindow)
	{
		Set<String> windowHandles = driver.getWindowHandles();
		for(String windows : windowHandles)
		{
			if(!(windows.equals(firstWindow) && windows.equals(secondWindow)))
			{
				driver.switchTo().window(windows);
			}}}

	public void selectByVisbleText(WebElement element,String val) {
		Select sel=new Select(element);
		sel.selectByVisibleText(val);
	}

	public void selectByValue(WebElement element,String val) {
		Select sel=new Select(element);
		sel.selectByValue(val);
	}

	public void multipleDropdownHandles(List<WebElement> dropdownValues,String value) {

		for(WebElement dropdownValue:dropdownValues) {
			if(dropdownValue.getText().equalsIgnoreCase(value)) {
				dropdownValue.click();
			}
		}

	}

	public void hoverTheMouse(WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void pageScrollUsingActions(WebElement element) {
		Actions act=new Actions(driver);
		act.scrollToElement(element);
	}

	//Perform a click operation using JS
	public void clickAnElementUsingJS(WebElement element ) {

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}

	//Send a data to textbox using JS
	public void sendDataUsingJS(WebElement element,String val ) {

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+val+"'", element);
	}

	//Highlight an element using JS
	public  void elementHighlighter(WebElement element ) {

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='4px solid red'",element);
	}

	//scroll the page till the element is visiable using JS
	public void pageScrollTillWebElementUsingJS(WebElement element ) {

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",element);
	}

	//Scroll the page directly giving the height
	public void pageScrollByHeight() {

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,5000)");
	}

	public void screenshotForElement(WebElement element) {
		
File screenshotFile=element.getScreenshotAs(OutputType.FILE);
		
		
		try {
			FileUtils.copyFile(screenshotFile, new File("./CaptureScreenshot/Screenshot_"+dateFormat+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		}

	public void screenshotForPage() {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File screenshotFile=ts.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(screenshotFile, new File("./CaptureScreenshot/Screenshot_"+dateFormat+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	public WebElement waitForElementPresence(WebElement locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOf(locator));
	}
	public void waitForElementToClickable(WebElement element,int timeout,int pollingTimeOut) {
		
		Wait<WebDriver>wait =new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTimeOut))
				.ignoring(NoSuchElementException.class);
		
		wait.until(new Function<WebDriver,WebElement>() {
			
			public WebElement apply(WebDriver driver) {
				return element;
			}
		});
	}

	//Calendar code should be modified based calendar behaviour
	public void calendarHandling(WebElement pickADateElement,WebElement monthElement,
			WebElement rightClickElement,List<WebElement> dateElements,
			String enteredYear,String enteredMonth,String enteredDate) {
		WebElement calendarOption=pickADateElement;
		calendarOption.click();

		WebElement year=monthElement;
		WebElement month=monthElement;
		WebElement rightNavigationArrow=rightClickElement;

		while(!year.getText().contains(enteredYear)) {

			rightNavigationArrow.click();

		}

		while(!month.getText().contains(enteredMonth)) {
			rightNavigationArrow.click();

		}

		List<WebElement> multipleDates1=dateElements;

		for(WebElement date:multipleDates1) {

			if(date.getText().contains(enteredDate)) {
				date.click();
				break;
			}


		}

	}






}
