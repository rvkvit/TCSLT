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



public class LT_ReleaseFlagSelection_Page extends MasterPage{
	private static WebElement element = null;
	CommonFunctions CF = new CommonFunctions(scriptHelper);
	public LT_ReleaseFlagSelection_Page(ScriptHelper scriptHelper)
	{
		super(scriptHelper);	
	}

	/*
	 * Locator details
	 */
	By ReleaseMotor1_CheckBox = By.xpath(".//div[contains(text(),'RELEASE_MOTOR1')]//ancestor::label[1]//div[2]//div");
	By Save_Button = By.xpath(".//*[contains(text(),'Save')]");
	
	WebDriverWait wait = new WebDriverWait(driver, 10);

	public LT_ReleaseFlagSelection_Page SelectReleaseFlag() throws InterruptedException{

		CF.clickOnElement(ReleaseMotor1_CheckBox, "Release Motor 1", "Checkbox");
		return new LT_ReleaseFlagSelection_Page(scriptHelper);
	}
	
	public LT_ReleaseFlagSelection_Page SaveSelection() {
		CF.scrollToElementAndClick(driver, driver.findElement(Save_Button));
		return new LT_ReleaseFlagSelection_Page(scriptHelper);
	}





}
