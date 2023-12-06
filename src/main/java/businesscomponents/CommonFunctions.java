package businesscomponents;

import java.text.DateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

import org.apache.bcel.generic.RETURN;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	WebDriverWait wait = new WebDriverWait(driver, 10);
	WebDriverWait wait1 = new WebDriverWait(driver, 20);

	//declaring fluent wait with time out of 30 seconds and frequency is set to 5 seconds  		
	Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver)							
			.withTimeout(60, TimeUnit.SECONDS) 			
			.pollingEvery(5, TimeUnit.SECONDS) 			
			.ignoring(NoSuchElementException.class);	

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

	public void dependableClick( By identifer , String strElementName, String strElementType)
	{
		WebDriver d = null;
		final int MAXIMUM_WAIT_TIME = 10;
		final int MAX_STALE_ELEMENT_RETRIES = 5;
		By strA = identifer;

		WebDriverWait wait = new WebDriverWait(d, MAXIMUM_WAIT_TIME);
		int retries = 0;
		while (true)
		{
			try
			{
				wait.until(ExpectedConditions.elementToBeClickable(strA)).click();

				return;
			}
			catch (StaleElementReferenceException e)
			{
				if (retries < MAX_STALE_ELEMENT_RETRIES)
				{
					retries++;
					continue;
				}
				else
				{
					throw e;
				}
			}
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
		//Thread.sleep(2000);
		By strA = identifer;

		try{
			/*try{
				wait1.until(ExpectedConditions.stalenessOf(driver.findElement(strA)));
			}
			catch(Exception e){

			}*/

			//wait.until(ExpectedConditions.presenceOfElementLocated(strA));
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

		//Thread.sleep(2000);
		By strA = identifer;

		try{
			/*try{
				wait1.until(ExpectedConditions.stalenessOf(driver.findElement(strA)));
			}
			catch(Exception e){

			}
			 */
			//wait.until(ExpectedConditions.visibilityOfElementLocated(strA));
			WebElement webElement= driver.findElement(strA);
			// 							_FOpt1:_FOr1:0:_FOSritemNode_my_information_expenses:0:MAnt2:1:AP1:ExpenseTypeId

			if(webElement.isDisplayed()){

				Select dropDownOption = new Select(webElement); 
				dropDownOption.selectByVisibleText(strValue);
				report.updateTestLog("Select values from "+strDropDownName, strValue +" is selected successfully", Status.DONE);
			}
		}
		catch(Exception e){
			// return true;
			//report.updateTestLog("Verify element:", strDropDownName +": is not displayed", Status.FAIL);
		}
		/*Select paymentMethod = new Select(drpDwn_PaymentMethod);
		paymentMethod.selectByVisibleText(strPaymentMethod);*/
	}


	public void performTab(By identifer, int intCount) {
		//Thread.sleep(2000);
		By strA = identifer;

		/*try{
			wait1.until(ExpectedConditions.stalenessOf(driver.findElement(strA)));
		}
		catch(Exception e){

		}*/
		//wait.until(ExpectedConditions.presenceOfElementLocated(strA));
		WebElement webElement= driver.findElement(strA);
		if(webElement.isDisplayed()){
			for(int i=1; i<= intCount; i++){
				webElement.sendKeys(Keys.TAB);
			}
		}

	}

	public String getValue(By identifer, int beginIndex, int endIndex) {


		//Thread.sleep(2000);
		By strA = identifer;
		String value = null;
		String getValue;
		WebElement webElement= driver.findElement(strA);
		if(webElement.isDisplayed()){
			getValue = webElement.getText();
			value = getValue.substring(beginIndex, endIndex); // 0// 18 // retrun : 

		}
		return value;
	}

	public String getText(By identifer) {
		//Thread.sleep(2000);

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
		//Thread.sleep(2000);
		report.updateTestLog("Take Screenshot", "Screenshot taken", Status.SCREENSHOT);

	}

	public String getAttribute(By identifer, String strAttrType) {
		//Thread.sleep(2000);

		By strA = identifer;
		String getValue = null;
		WebElement webElement= driver.findElement(strA);
		if(webElement.isDisplayed()){
			getValue = webElement.getAttribute(strAttrType);
		}
		return getValue;
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
        // Scroll to the element using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // Click on the element
        element.click();
    }

	/*
	 * Scenario � While reporting your script status, you need to pass time & date that when your 
	 * test-script has finished. Another scenario: If you need unique username every time, you can 
	 * append the timestamp with any constant string  (e.g SumitMay28201513_15_10 PM), so here you are:
	 */

	public static String fn_GetTimeStamp(){
		DateFormat DF=DateFormat.getDateTimeInstance();
		Date dte=new Date();
		String DateValue=DF.format(dte);
		DateValue=DateValue.replaceAll(":", "_");
		DateValue=DateValue.replaceAll(",", "");
		return DateValue;
	}

	/**
	 * @param driver
	 *            WebDriver reference
	 * @param timeOutInSeconds
	 *            integer type waiting time in second after that time out of
	 *            waiting
	 * @param FindElementBy
	 *            String type expression of finding the element using :
	 *            xpath/ID/CSS path
	 * @return boolean Status of WebDriver element i.e. found or not
	 * @param Expr
	 *            String type expression of xpath/ID/CSS path
	 * @throws TimeoutException
	 *             if time out exception occurred
	 */
	public boolean commonWaitToFindElement(WebDriver driver,
			int timeOutInSeconds, String findElementBy, By Expr)
					throws TimeoutException {
		boolean result = false;
		WebElement element = null;
		try {
			// Element is Click able - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

			// TODO - Use Switch case, move to Java 1.7
			if (findElementBy.equals("xpath")) {
				element = wait.until(ExpectedConditions.elementToBeClickable(Expr));
			} else if (findElementBy.equals("id")) {
				element = wait.until(ExpectedConditions.elementToBeClickable(Expr));
			} else if (findElementBy.equals("cssSelector")) {
				element = wait.until(ExpectedConditions.elementToBeClickable(Expr));
			} else if (findElementBy.equals("className")) {
				element = wait.until(ExpectedConditions.elementToBeClickable(Expr));
			} else if (findElementBy.equals("linkText")) {
				element = wait.until(ExpectedConditions.elementToBeClickable(Expr));
			}

			if (element != null) {
				result = true;
			} else {
				result = false;
			}
		} catch (TimeoutException e) {
			report.updateTestLog("Verify timeout reason for the element",  "TimeoutException in commonWaitToFindElement for :"
					+ e.getMessage(), Status.FAIL);

		}
		return result;
	}

}