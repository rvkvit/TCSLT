package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import supportlibraries.*;
import com.LT.framework.*;

import businesscomponents.CommonFunctions;



public class LT_Home_Page extends MasterPage{
	private static WebElement element = null;
	CommonFunctions CF = new CommonFunctions(scriptHelper);
	public LT_Home_Page(ScriptHelper scriptHelper)
	{
		super(scriptHelper);	
	}

	/*
	 * Locator details
	 */
	By shadowHost = By.cssSelector("body > app-root > ss-app-base > ss-nav-bar > duet-header");
	By CompensationAndDamageMatters_Link = By.cssSelector("#generic\\.claims_tab");
	By SignOutSession_Link = By.cssSelector("#session");
	By FrontPage_Link = By.cssSelector("#generic\\.home_tab");

	

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));;

	public LT_Home_Page VerifyHomePage() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(shadowHost)));
	    CF.verifyShadowElement(shadowHost, FrontPage_Link, "Front Page", "Tab");
	    CF.verifyShadowElement(shadowHost, CompensationAndDamageMatters_Link, "Compensation and Damage Matters", "Tab");
	    CF.verifyShadowElement(shadowHost, SignOutSession_Link, "SignOut", "Link");
		
		return new LT_Home_Page(scriptHelper);
	}
	
	public LT_Home_Page clickOnSpecificTabAvailableOnHomePage(String strTabName) {
		switch (strTabName) {
			case "Compensation and Damage Matters":
				 CF.clickOnShadowElement(shadowHost, CompensationAndDamageMatters_Link, "Compensation and Damage Matters", "Tab");
				break;
			case "":
				break;
		}
		return new LT_Home_Page(scriptHelper);
	}



}
