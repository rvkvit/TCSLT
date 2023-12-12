package pages;

import java.time.Duration;
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



public class LT_CompensationAndDamageMatters_Page extends MasterPage{
	private static WebElement element = null;
	CommonFunctions CF = new CommonFunctions(scriptHelper);
	public LT_CompensationAndDamageMatters_Page(ScriptHelper scriptHelper)
	{
		super(scriptHelper);	
	}

	/*
	 * Locator details
	 */
	By ApplyForCompensation_Button = By.id("claimOpenBtn");



	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));

	public LT_CompensationAndDamageMatters_Page ClickOnApplyForCompensation() throws InterruptedException{
		CF.clickOnElement(ApplyForCompensation_Button, "Apply for Compensation", "Button");

		return new LT_CompensationAndDamageMatters_Page(scriptHelper);
	}

}
