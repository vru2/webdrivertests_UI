// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsUI_Air;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Amendment extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void CC_Amendment() throws Exception {
		String PayUrl = getPayUI("AirAmend", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		if(textPresent(driver, "Includes a convenience fee of", 1)){
			Reporter.log("convenience fee text is displayed");
			Assert.assertTrue(false);
		}	

		payUI_Select_PaymentType(driver, "DC");
		if(textPresent(driver, "Includes a convenience fee of", 1)){
			Reporter.log("convenience fee text is displayed");
			Assert.assertTrue(false);
		}	

		payUI_Select_PaymentType(driver, "TW");
		if(textPresent(driver, "Includes a convenience fee of", 1)){
			Reporter.log("convenience fee text is displayed");
			Assert.assertTrue(false);
		}	
		payUI_Select_PaymentType(driver, "UPI");
		if(textPresent(driver, "Includes a convenience fee of", 1)){
			Reporter.log("convenience fee text is displayed");
			Assert.assertTrue(false);
		}		
		payUI_Select_PaymentType(driver, "NB");
		if(textPresent(driver, "Includes a convenience fee of", 1)){
			Reporter.log("convenience fee text is displayed");
			Assert.assertTrue(false);
		}	
		textPresent_Log(driver, "Paid before", 1);
		payUI_Enter_PaymentDetails(driver, "NB", "Axis Bank","");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	  
	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
	
}
