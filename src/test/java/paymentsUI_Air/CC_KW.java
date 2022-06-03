// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CC_KW extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void CC_PayU_Pay_KW() throws Exception {
		String PayUrl = getPayUI("AirKW", "KW");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl); 
		refreshPage(driver);
		textNotPresent_List( driver, getObjectPayment("PayUI_Pay_Tabs"), "Net banking");
		payUI_Select_PaymentType(driver, "CC");
		String Total_Price = getText(driver, getObjectPayment("PayUI_Total_Pay_Value"));
		if(!Total_Price.contains(".00")) {
			Reporter.log("Total dosent contain decimal .00 "+Total_Price);
			Assert.assertTrue(false);			
		}
		validate_Currency(driver, "", "KWD");
		payUI_Enter_PaymentDetails(driver, "CC", "MASTER","");
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
