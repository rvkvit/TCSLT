package testscripts.scenario1;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.LT.framework.IterationOptions;
import com.LT.framework.selenium.Browser;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;



/**
 * Test for selection of Release and Bank portal
 * @author LT
 */
public class TC_2 extends TestCase
{
	@Test
	@Parameters("browser")
	public void runTC2(String browser) throws InvalidFormatException
	{
		testParameters.setCurrentTestDescription("Test for selection of Release and Bank portal");
		testParameters.setIterationMode(IterationOptions.RunAllIterations);
		if(browser.equalsIgnoreCase("chrome"))
			testParameters.setBrowser(Browser.Chrome);
		else if (browser.equalsIgnoreCase("firefox"))
			testParameters.setBrowser(Browser.Firefox);
		else if(browser.equalsIgnoreCase("internetExplorer"))
			testParameters.setBrowser(Browser.InternetExplorer);
		else if(browser.equalsIgnoreCase("edge"))
			testParameters.setBrowser(Browser.Edge);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}