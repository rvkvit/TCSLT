package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import supportlibraries.*;
import com.LT.framework.*;

import businesscomponents.CommonFunctions;



public class LT_SSN_Page extends MasterPage{
	private static WebElement element = null;
	CommonFunctions CF = new CommonFunctions(scriptHelper);
	public LT_SSN_Page(ScriptHelper scriptHelper)
	{
		super(scriptHelper);	
	}

	/*
	 * Locator details
	 */
	By SSN_TextBox = By.id("ssn");
	By Continue_Button = By.id("continue");

	WebDriverWait wait = new WebDriverWait(driver, 10);

	public LT_SSN_Page EnterSSNonLT() throws InterruptedException{
		
		String strSSN = dataTable.getData("General_Data", "SSN");
		
		CF.enterValuesInTextBox(SSN_TextBox, "SSN", strSSN);
		CF.clickOnElement(Continue_Button, "Continue", "Button");
		
		return new LT_SSN_Page(scriptHelper);
	}




}
