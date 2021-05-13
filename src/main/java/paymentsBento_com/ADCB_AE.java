// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ADCB_AE extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;
	
	@Test
	public void ADCB() throws Exception {
		get_Bento_Url(driver, "AirAE", "AE");
		bento_Select_PaymentType(driver, "ADCB");
		textPresent(driver, "Pay to complete your booking", 5);	
		textPresent(driver, "Enter ADCB card details", 1);		
		textPresent_Log(driver, "ADCB card number", 1);
		textPresent_Log(driver, "Expiry date", 1);
		textPresent_Log(driver, "Card holder Name", 1);
		textPresent_Log(driver, "CVV", 1);
		validate_Currency(driver, "", "AED");
		payUI_Enter_PaymentDetails(driver, "ADCB", "","ADCBPARTIAL");
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
