package businesscomponents;

import java.text.DateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

import org.apache.bcel.generic.RETURN;
import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.LT.framework.FrameworkException;
import com.LT.framework.Status;
import com.LT.framework.selenium.WebDriverUtil;

import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;
import org.openqa.selenium.JavascriptExecutor;


/**
 * Verification Components class
 * @author LT TCS
 */
public class CommonFunctions extends ReusableLibrary
{
	private WebDriverUtil driverUtil;

	/**
	 * Constructor to initialize the component library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public CommonFunctions(ScriptHelper scriptHelper)
	{
		super(scriptHelper);

		driverUtil = new WebDriverUtil(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(2));	


	public void clickOnElement(By identifer, String strElementName, String strElementType) {

		By strA = identifer;
		try{
			// wait condition check
			wait1.until(ExpectedConditions.visibilityOfElementLocated(strA));
			WebElement webElement= driver.findElement(strA);
			if(webElement.isDisplayed()){
				webElement.click();
				report.updateTestLog("Click on "+ strElementName ,  strElementName +" "+ strElementType+" is clicked successfully", Status.DONE);
			}
		}
		catch(Exception e){
			report.updateTestLog("Verify element:", strElementName +": is not displayed", Status.FAIL);
		}

	}

	public void verifyElement(By identifer, String strElementName, String strElementType) {

		By strA = identifer;
		try{
			// wait condition check
			wait1.until(ExpectedConditions.visibilityOfElementLocated(strA));
			WebElement webElement= driver.findElement(strA);
			if(webElement.isDisplayed()){
				report.updateTestLog("Click on "+ strElementName ,  strElementName +" "+ strElementType+" is clicked successfully", Status.DONE);
			}
		}
		catch(Exception e){
			report.updateTestLog("Verify element:", strElementName +": is not displayed", Status.FAIL);
		}

	}




	public void enterValuesInTextBox(By identifer,String strTextBoxName, String strValue) {
		//Thread.sleep(2000);
		By strA = identifer;
		try
		{
			wait1.until(ExpectedConditions.visibilityOfElementLocated(strA));
			WebElement webElement= driver.findElement(strA);
			//wait.until(ExpectedConditions.stalenessOf(webElement));
			if(webElement.isDisplayed()){
				webElement.clear();
				webElement.click();
				webElement.sendKeys(strValue);
				report.updateTestLog("Enter values in "+strTextBoxName, strValue +" is entered successfully", Status.DONE);
			}
		}
		catch(Exception e){
			report.updateTestLog("Verify element:", strTextBoxName +": is not displayed", Status.FAIL);

		}


	}


	public int getCount(By identifer){
		By strA = identifer;
		int C = 0;
		try{
			String strCount;

			strCount = driver.findElement(strA).getText();
			C = Integer.parseInt(strCount);

		}catch(Exception e){

		}

		return C;

	}

	public String getWindowHandle(){

		String winHandleBefore = driver.getWindowHandle();
		return winHandleBefore;

	}
	public void browseAndUploadFile(By identifer,String strLocation){

		By strA = identifer;

		try{
			WebElement webElement= driver.findElement(strA);
			if(webElement.isDisplayed()){
				webElement.sendKeys(strLocation);
				report.updateTestLog("Browse file and uploadit", "File is browsed from location: "+strLocation +" and uploaded successfully", Status.DONE);
			}
		}
		catch(Exception e){
			report.updateTestLog("Verify element:", "Browse element is not displayed", Status.FAIL);
		}

	}
	public void enterValues(By identifer,String strName, String strValue){
		By strA = identifer;
		try{

			WebElement webElement= driver.findElement(strA);
			if(webElement.isDisplayed()){
				webElement.clear();
				webElement.sendKeys(strValue);
				report.updateTestLog("Enter values in "+strName, strValue +" is entered successfully", Status.DONE);
			}
		}
		catch(Exception e){
			report.updateTestLog("Verify element:", strName +": is not displayed", Status.FAIL);
		}
	}

	public void selectValueFromDropDown(By identifer,String strDropDownName, String strValue) {
		By strA = identifer;

		try{

			WebElement webElement= driver.findElement(strA);
			if(webElement.isDisplayed()){

				Select dropDownOption = new Select(webElement); 
				dropDownOption.selectByVisibleText(strValue);
				report.updateTestLog("Select values from "+strDropDownName, strValue +" is selected successfully", Status.DONE);
			}
		}
		catch(Exception e){

		}

	}


	public void performTab(By identifer, int intCount) {
		By strA = identifer;
		WebElement webElement= driver.findElement(strA);
		if(webElement.isDisplayed()){
			for(int i=1; i<= intCount; i++){
				webElement.sendKeys(Keys.TAB);
			}
		}

	}

	public String getValue(By identifer, int beginIndex, int endIndex) {
		By strA = identifer;
		String value = null;
		String getValue;
		WebElement webElement= driver.findElement(strA);
		if(webElement.isDisplayed()){
			getValue = webElement.getText();
			value = getValue.substring(beginIndex, endIndex); 

		}
		return value;
	}

	public String getText(By identifer) {
		By strA = identifer;
		String getValue = null;
		wait1.until(ExpectedConditions.visibilityOfElementLocated(strA));
		WebElement webElement= driver.findElement(strA);
		if(webElement.isDisplayed()){
			getValue = webElement.getText();
		}
		return getValue;
	}

	public void takeScreenshot() {
		report.updateTestLog("Take Screenshot", "Screenshot taken", Status.SCREENSHOT);

	}

	public String getAttribute(By identifer, String strAttrType) {
		By strA = identifer;
		String getValue = null;
		WebElement webElement= driver.findElement(strA);
		if(webElement.isDisplayed()){
			getValue = webElement.getAttribute(strAttrType);
		}
		return getValue;
	}

	/*
	 * Shadow Elements
	 */

	public void verifyShadowElement(By ShadowHost, By ShadowRoot,  String strElementName, String strElementType) {

		try{
			wait1.until(ExpectedConditions.visibilityOfElementLocated(ShadowHost));
			WebElement shadowHost = driver.findElement(ShadowHost);
			SearchContext shadowRoot = shadowHost.getShadowRoot();
			WebElement shadowContent = shadowRoot.findElement(ShadowRoot);

			if(shadowContent.isDisplayed()){
				report.updateTestLog("Click on "+ strElementName ,  strElementName +" "+ strElementType+" is displayed successfully", Status.DONE);
			}
		}
		catch(Exception e){
			report.updateTestLog("Verify element:", strElementName +": is not displayed", Status.FAIL);
		}

	}

	public void clickOnShadowElement(By ShadowHost, By ShadowRoot,  String strElementName, String strElementType) {

		try{
			wait1.until(ExpectedConditions.visibilityOfElementLocated(ShadowHost));
			WebElement shadowHost = driver.findElement(ShadowHost);
			SearchContext shadowRoot = shadowHost.getShadowRoot();
			WebElement shadowContent = shadowRoot.findElement(ShadowRoot);

			if(shadowContent.isDisplayed()){
				shadowContent.click();
				report.updateTestLog("Click on "+ strElementName ,  strElementName +" "+ strElementType+" is clicked successfully", Status.DONE);
			}
		}
		catch(Exception e){
			report.updateTestLog("Verify element:", strElementName +": is not displayed", Status.FAIL);
		}

	}
	
	public void clickOnShadowElementWithScrollView(By ShadowHost, By ShadowRoot,  String strElementName, String strElementType) {

		try{
			wait1.until(ExpectedConditions.visibilityOfElementLocated(ShadowHost));
			WebElement shadowHost = driver.findElement(ShadowHost);
			SearchContext shadowRoot = shadowHost.getShadowRoot();
			WebElement shadowContent = shadowRoot.findElement(ShadowRoot);

			if(shadowContent.isDisplayed()){
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shadowContent);
				shadowContent.click();
				report.updateTestLog("Click on "+ strElementName ,  strElementName +" "+ strElementType+" is clicked successfully", Status.DONE);
			}
		}
		catch(Exception e){
			report.updateTestLog("Verify element:", strElementName +": is not displayed", Status.FAIL);
		}

	}

	public void clickOnNestedShadowElement(By ShadowHost, By ShadowRoot1,  By ShadowRoot2, String strElementName, String strElementType) {

		try{
			
			wait1.until(ExpectedConditions.visibilityOfElementLocated(ShadowHost));
			WebElement shadowHost = driver.findElement(ShadowHost);
			SearchContext shadowRoot = shadowHost.getShadowRoot();
			WebElement shadowContent = shadowRoot.findElement(ShadowRoot1);
			SearchContext shadowRootTwo = shadowContent.getShadowRoot ();
			WebElement shadowContent1 = shadowRootTwo.findElement(ShadowRoot2);

			if(shadowContent1.isDisplayed()){
				shadowContent1.click();
				report.updateTestLog("Click on "+ strElementName ,  strElementName +" "+ strElementType+" is clicked successfully", Status.DONE);
			}
		}
		catch(Exception e){
			report.updateTestLog("Verify element:", strElementName +": is not displayed", Status.FAIL);
		}

	}

	/*
	 * incase, we need to click on a sub-menu that is visible only when users do mouse-hover on the main-menu, then we can do it using this function. Just pass web element position to this function.
		e.g MouseOver(driver.findElement(By.name(�Main-Menu�)))
	 */
	public void MouseOver(WebElement we){
		Actions actObj=new Actions(driver);
		actObj.moveToElement(we).build().perform();
	}

	public static void scrollToElementAndClick(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	/*
	 * Scenario � While reporting your script status, you need to pass time & date that when your 
	 * test-script has finished. Another scenario: If you need unique username every time, you can 
	 * append the timestamp with any constant string  (e.g XYZ_May28201513_15_10 PM), so here you are:
	 */

	public static String fn_GetTimeStamp(){
		DateFormat DF=DateFormat.getDateTimeInstance();
		Date dte=new Date();
		String DateValue=DF.format(dte);
		DateValue=DateValue.replaceAll(":", "_");
		DateValue=DateValue.replaceAll(",", "");
		return DateValue;
	}




}