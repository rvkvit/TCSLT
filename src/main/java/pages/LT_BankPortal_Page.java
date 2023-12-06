package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import supportlibraries.*;
import com.LT.framework.*;

import businesscomponents.CommonFunctions;



public class LT_BankPortal_Page extends MasterPage{
	private static WebElement element = null;
	CommonFunctions CF = new CommonFunctions(scriptHelper);
	public LT_BankPortal_Page(ScriptHelper scriptHelper)
	{
		super(scriptHelper);	
	}

	/*
	 * Locator details
	 */
	By Nordea_Link = By.linkText("Nordea");

	WebDriverWait wait = new WebDriverWait(driver, 10);

	public LT_BankPortal_Page SelectBankPortal() throws InterruptedException{

		CF.clickOnElement(Nordea_Link, "Nordea", "Link");
		return new LT_BankPortal_Page(scriptHelper);
	}




}
