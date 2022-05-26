// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RP_ADCB_Full extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void AE_ADCB_Full() throws Exception {
		String PayUrl = getPayUI("AirAE", "AE");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		textNotPresent_List(driver, getObjectPayment("PayUI_Pay_Tabs"), "Net banking");
		payUI_Select_PaymentType(driver, "ADCB");
		textPresent_Log(driver, "Enter your ADCB card details", 5);		
		textPresent_Log(driver, "ADCB card no.", 5);
		validate_Currency(driver, "", "AED");
		payUI_Enter_PaymentDetails(driver, "ADCB", "","ADCBFULL");
		//payUI_Mock_ConfirmationPage(driver, PayUrl);
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
