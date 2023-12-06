package testscripts.scenario1;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.LT.framework.IterationOptions;
import com.LT.framework.selenium.Browser;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;



/**
 * Test for selection of Release and Bank portal
 * @author LT
 */
public class TC_1 extends TestCase
{
	@Test
	public void runTC4() throws InvalidFormatException
	{
		testParameters.setCurrentTestDescription("Test for selection of Release and Bank portal");
		testParameters.setIterationMode(IterationOptions.RunAllIterations);
		testParameters.setBrowser(Browser.Chrome);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}