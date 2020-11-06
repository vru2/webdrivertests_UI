// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_NB_HDFC extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_NBHDFC() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		
		Thread.sleep(5000);
		
		
		
		
		payUI_Select_PaymentType_PWA(driver, "NET BANKING");
		Thread.sleep(5000);
		
		
		
		
		
		
		//safeClick(driver, getObjectPayment("PWA_PaymentPage_NB_Tab"));
		payUI_Enter_PaymentDetails_PWA(driver, "NET BANKING", "HDFC BANK");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getMobileDriver(driver);
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
