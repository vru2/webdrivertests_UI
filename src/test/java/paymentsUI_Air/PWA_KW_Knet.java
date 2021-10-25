// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_KW_Knet extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_KW_MASTER() throws Exception {
		String PayUrl = getPayUI("AirKW", "KW");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		payUI_Select_PaymentType_PWA(driver, "KNET");
		elementPresent_log(driver, By.cssSelector("li.Datalist__item.radio-item-list__item.pwa-list-item"), "Knet Radio button", 5);
		elementPresent_log(driver, By.cssSelector("image"), "Knet Image", 1);
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
