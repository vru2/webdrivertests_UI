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

public class Full_GV extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void NB_GV_Pay() throws Exception {
		String PayUrl = getPayUI("AirGVFull", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);	  
		String GVText=getText(driver, By.xpath("//div[3]/div[4]/div"));
		if(!GVText.contains("3000331032473351")) {
			Reporter.log("GV is not displayed"+GVText);
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Enter your credit card details", 2)) {
			Reporter.log("for full GV aother pay options are displayed - Enter your credit card details");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare", 2);
		String FullGVPay = getText(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Assert.assertEquals("Complete Booking", FullGVPay);
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Reporter.log("Scripts should be fixed after Air integration");
		Assert.assertTrue(false);
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
