// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NB_GV extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void NB_GV_Pay() throws Exception {
		String PayUrl = getPayUI("AirGV", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);	  
		String GVText=getText(driver, By.xpath("//div[3]/div[4]/div"));
		if(!GVText.contains("3000331039428010")) {
			Reporter.log("GV is not displayed"+GVText);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Gift Card", 1);
		payUI_Select_PaymentType(driver, "NB");
		payUI_Enter_PaymentDetails(driver, "NB", "Citibank");
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
