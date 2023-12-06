package testscripts.scenario1;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.LT.framework.IterationOptions;
import com.LT.framework.selenium.Browser;

import supportlibraries.DriverScript;
import supportlibraries.TestCase;



/**
 * Test for book flight tickets and verify booking
 * @author LT
 */
public class TC_3 extends TestCase
{
	@Test
	public void runTC4() throws InvalidFormatException
	{
		testParameters.setCurrentTestDescription("Test for book flight tickets and verify booking");
		testParameters.setIterationMode(IterationOptions.RunAllIterations);
		testParameters.setBrowser(Browser.Chrome);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
}