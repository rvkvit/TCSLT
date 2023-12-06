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



public class LT_NordeaBankPortalSignIn_Page extends MasterPage{
	private static WebElement element = null;
	CommonFunctions CF = new CommonFunctions(scriptHelper);
	public LT_NordeaBankPortalSignIn_Page(ScriptHelper scriptHelper)
	{
		super(scriptHelper);	
	}

	/*
	 * Locator details
	 */
	By NordeaUserID_TextBox = By.id("demo-mock-user-id");
	By OK_Button = By.id("auth-button");

	WebDriverWait wait = new WebDriverWait(driver, 10);

	public LT_NordeaBankPortalSignIn_Page LoginToNordeaPortal() throws InterruptedException{
		
		String strUserID = dataTable.getData("General_Data", "UserID");
		
		CF.enterValuesInTextBox(NordeaUserID_TextBox, "User Id", strUserID);
		Assert.assertEquals(CF.getAttribute(OK_Button, "aria-disabled"), "false");
		CF.clickOnElement(OK_Button, "OK", "Button");
		
		return new LT_NordeaBankPortalSignIn_Page(scriptHelper);
	}




}
