// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;


import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validation_UI_IN extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation() throws Exception {
		get_Bento_Url(driver, "Air", "");
		bento_Validation_UI(driver,"CC");
	}
	
	@Test (priority=2)
	public void NB_Validation() throws Exception {
		bento_Validation_UI(driver,"NB");
	}
	
	@Test (priority=3)
	public void TP_Wallet_Validation() throws Exception {
		bento_Validation_UI(driver,"TW");
	}

	@Test (priority=4)
	public void UPI_Validation() throws Exception {
		bento_Validation_UI(driver,"UPI");
		
	}	

	@Test (priority=5)
	public void StoredCard_Validation_Text() throws Exception {
		bento_Validation_UI(driver,"SC");		
	}	 
	
	/*
	@Test (priority=6)
	public void PayPal_Validation() throws Exception {
		
	}	*/
	
	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
	
}