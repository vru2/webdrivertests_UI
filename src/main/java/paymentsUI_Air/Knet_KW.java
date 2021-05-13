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

public class Knet_KW extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void KNETKW() throws Exception {
		String PayUrl = getPayUI("AirKW", "KW");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		//payUI_Select_PaymentType(driver, "NB");
		//Assert.assertTrue(false);// validate the Knet logo
		
		
		payUI_Select_PaymentType(driver, "KNET");
		elementPresent_log(driver, By.xpath("//div[2]/div/div/div/div/div/ul/li"), "Knet button	", 1);
		if(	elementPresent(driver, By.cssSelector("li.Datalist__item.radio-item-list__item.is-active"), 1)) {
			Reporter.log("Knet is selected by default");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//div[2]/div/div/div/div/div/ul/li"));
		if(	!elementPresent(driver, By.cssSelector("li.Datalist__item.radio-item-list__item.is-active"), 1)) {
			Reporter.log("Knet is not selected ");
			Assert.assertTrue(false);
		}
		payUI_Enter_PaymentDetails(driver, "KNET", "KNET","");
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
