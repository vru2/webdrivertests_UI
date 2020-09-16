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

public class GV_NB extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void NB_GV_Pay() throws Exception {
		String PayUrl = getPayUI("AirGV", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);	  
		String GVText=getText(driver, By.xpath("//div[3]/div[4]/div"));
		if(!GVText.contains("Gift card (3000331035955930)")) {
			Reporter.log("GV is not displayed "+GVText);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Gift card", 1);
		Reporter.log("Gift card (3000331035955930) text is displayed");
		payUI_Select_PaymentType(driver, "NB");
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
