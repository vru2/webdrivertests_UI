// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KNET_KW extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;
	
	@Test
	public void Knet() throws Exception {
		get_Bento_Url(driver, "AirKW", "KW");

		driver.manage().addCookie(ctauth_partial_wallet);
		refreshPage(driver);
		bento_Select_PaymentType(driver, "KNET");
		textPresent(driver, "Select a bank"	, 5);
		elementPresent(driver, By.xpath("//form/div/div/div[2]/div"));
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
