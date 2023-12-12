package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import supportlibraries.*;
import com.LT.framework.*;

import businesscomponents.CommonFunctions;



public class LT_FilingAClaim_Page extends MasterPage{
	private static WebElement element = null;
	CommonFunctions CF = new CommonFunctions(scriptHelper);
	public LT_FilingAClaim_Page(ScriptHelper scriptHelper)
	{
		super(scriptHelper);	
	}
	String strDamageType;
	String strTimeOfTheInjury;
	String strVehicleName;
	/*
	 * Locator details
	 */
	By ShadowHost = By.cssSelector("#content");
	By ShadowRootForApplyCompensation = By.cssSelector("div > div > div.duet-hero-text > duet-button");
	By ApplyForCompensation_Button = By.cssSelector("#claimOpenBtn");
	
	By Vechile_DamageType_RadioButton = By.cssSelector("#vehicle");
	
	By TimeOfTheInjury_Title = By.xpath(".//*[contains(text(),'The time of the injury')]");
	By TimeOfTheInjury_RadioButton = By.cssSelector("#damage-time > duet-fieldset > div > duet-choice:nth-child(1)");
	// #DuetInput-bb9134d6-f4b2-49e1-b6cb-f7d2723b51a7
	By Date_DamageWasDone_TextBox = By.xpath("(.//*[@id='damage-occurred-date']//input)[1]");
	By Time_DamageWasDone_TextBox = By.xpath(".//input[@id='damage-occurred-time']");
	
	By SelectVehicleType_RadioButton = By.xpath(".//*[contains(text(),'ABC-123')]");
	By ShadowHost_Next = By.cssSelector("#main-layout > div > duet-layout > div > app-choose-policy-type > div > form > duet-card > duet-button");
	// #main-layout > div > duet-layout > div > app-choose-policy-type > div > form > duet-card > duet-button
	//#main-layout > div > duet-layout > div > ss-vehicle-list > duet-card > div > div > duet-grid > duet-grid-item:nth-child(1) > duet-button
	By ShadowHost_Next_TimeOfInjury = By.cssSelector("#main-layout > div > duet-layout > div > lossdate > duet-card > div > div > duet-button:nth-child(6)");
	By Shadow_Next_VehicleDamage = By.cssSelector("#main-layout > div > duet-layout > div > ss-vehicle-list > duet-card > div > div > duet-grid > duet-grid-item:nth-child(1) > duet-button");
	// #main-layout > div > duet-layout > div > lossdate > duet-card > div > div > duet-button:nth-child(6)
	By Next_Button = By.cssSelector("div > button");
	
	
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));

	public LT_FilingAClaim_Page ClickOnApplyForCompensation() throws InterruptedException{
		Thread.sleep(5000);
		CF.clickOnNestedShadowElement(ShadowHost, ShadowRootForApplyCompensation, ApplyForCompensation_Button, "Apply for Compensation", "Button");
		
		return new LT_FilingAClaim_Page(scriptHelper);
	}
	
	public LT_FilingAClaim_Page SelectDamageTypeAndMoveNext() {
		
		CF.clickOnElement(Vechile_DamageType_RadioButton, "Damage Type", "Radio Button");
		
		//document.querySelector("#main-layout > div > duet-layout > div > app-choose-policy-type > div > form > duet-card > duet-button").shadowRoot.querySelector("div > button")
		
		CF.clickOnShadowElementWithScrollView(ShadowHost_Next, Next_Button, "Next", "Button");
		return new LT_FilingAClaim_Page(scriptHelper);
	}
	
	public LT_FilingAClaim_Page SelectTimeOfTheInjuryAndMoveNext(String timeOfTheInjury) {
		strTimeOfTheInjury = timeOfTheInjury;
		String strDate = dataTable.getData("General_Data", "Date");
		String strTime = dataTable.getData("General_Data", "Time");
		// #damage-time > duet-fieldset > div > duet-choice:nth-child(1) > div.duet-choice-card-flextainer.sc-duet-choice > div > label > div.duet-choice-card-text.sc-duet-choice > div
		// #damage-time > duet-fieldset > div > duet-choice:nth-child(2) > div.duet-choice-card-flextainer.sc-duet-choice > div > label > div.duet-choice-card-text.sc-duet-choice > div
		CF.clickOnElement(TimeOfTheInjury_RadioButton, timeOfTheInjury, "Radio Button");
		CF.enterValuesInTextBox(Date_DamageWasDone_TextBox, "Date", strDate);
		CF.enterValuesInTextBox(Time_DamageWasDone_TextBox, "Time", strTime);
		CF.clickOnShadowElementWithScrollView(ShadowHost_Next_TimeOfInjury, Next_Button, "Next", "Button");

		return new LT_FilingAClaim_Page(scriptHelper);
	}

	public LT_FilingAClaim_Page SelectVechileTypeAndMoveNext(String vehicleName) {
		strVehicleName = vehicleName;
		CF.clickOnElement(SelectVehicleType_RadioButton, "Vehicle Type", "Radio Button");
		CF.clickOnShadowElementWithScrollView(Shadow_Next_VehicleDamage, Next_Button, "Next", "Button");

		return new LT_FilingAClaim_Page(scriptHelper);
	}
}
