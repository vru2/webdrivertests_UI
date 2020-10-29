// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Validate_RT extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void RT_Validate() throws Exception {
		String PayUrl = getPayUI("AirRT", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		textPresent(driver, "Flight itinerary", 10);
		elementPresent(driver, By.xpath("//div[2]/div[2]/div[1]"));
		String from = getText(driver, By.xpath("//div[2]/div[2]/div[1]/div[1]/div[1]"));
		String to = getText(driver, By.xpath("//div[2]/div[2]/div[2]/div[1]/div[1]"));
		if(!(to.contains("Bangalore")&&to.contains("Chennai"))) {
			Reporter.log("to "+to);
			Assert.assertTrue(false);
		}
		if(!(from.contains("Bangalore")&&to.contains("Chennai"))) {
			Reporter.log("from "+from);
			Assert.assertTrue(false);
		}
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
