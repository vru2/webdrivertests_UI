// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_Coupon extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_Coupon1() throws Exception {
		String PayUrl = getPayUI("AirCoupon", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType_PWA(driver, "NET BANKING");
		safeClick(driver, getObjectPayment("PWA_PaymentPage_Select_NB"));
		textPresent(driver, "All Other Banks", 5);
		safeType(driver, getObjectPayment("PWA_NETBANKING_Page_NB_TextBox"), "Citibank");
		safeClick(driver, getObjectPayment("PWA_NETBANKING_Page_NB_AJAX"));
		//safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
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
