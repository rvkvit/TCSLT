package businesscomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.LT.framework.selenium.WebDriverUtil;

import pages.LT_BankPortal_Page;
import pages.LT_NordeaBankPortalSignIn_Page;
import pages.LT_ReleaseFlagSelection_Page;
import pages.LT_SSN_Page;
//import bsh.util.Util;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;
import businesscomponents.CommonFunctions;


/**
 * Functional Components class
 * @author LT TCS
 */
public class FunctionalComponents extends ReusableLibrary
{
	private WebDriverUtil driverUtil;
	CommonFunctions CF = new CommonFunctions(scriptHelper);
	/**
	 * Constructor to initialize the component library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public FunctionalComponents(ScriptHelper scriptHelper)
	{
		super(scriptHelper);
		driverUtil = new WebDriverUtil(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, 30);
	public String errorMessage;
	public String strMessage;
	public String strCode;
	public String strAppend;
	public String strBatchName;
	public String InvoiceNum;



	public void invokeApplication()
	{
		/*
		 * Clear all temp files and IE driver instances
		 */

		/*
		 try {
			 Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			 } catch (final IOException e) {
			 throw new RuntimeException("Failed to run bat file");
			 }
		 */
		//driver.get(properties.getProperty("ApplicationUrl"));
		String strEnv = dataTable.getData("General_Data", "Environment");
		String strUrl = dataTable.getData("General_Data", "URL");
		if(strEnv.contains("Production")){
			/*
			 * Enter production url 
			 */
			driver.get(strUrl); // modified for utility
		}
		else if(strEnv.contains("Dev")){
			/*
			 * Dev URL
			 */
			driver.get(strUrl);
		}
		else{
			/*
			 * Test url
			 */
			driver.get(strUrl);
		}

	}


	public void selectReleaseFlag() throws InterruptedException
	{
		LT_ReleaseFlagSelection_Page RF = new LT_ReleaseFlagSelection_Page(scriptHelper);
		RF.SelectReleaseFlag();
		RF.SaveSelection();



	}

	public void selectBankPortalAndLogin() throws InterruptedException
	{
		LT_BankPortal_Page BP = new LT_BankPortal_Page(scriptHelper);
		BP.SelectBankPortal();
		LT_NordeaBankPortalSignIn_Page NBPS = new LT_NordeaBankPortalSignIn_Page(scriptHelper);
		NBPS.LoginToNordeaPortal();
		LT_SSN_Page SP = new LT_SSN_Page(scriptHelper);
		SP.EnterSSNonLT();
		
	}
	
	public void loginError() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String error = CF.getText(By.id("login-error-header"));
		dataTable.putData("General_Data", "Error", error);
	}
	




}