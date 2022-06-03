// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsBento_com;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Coupon_Cashback extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);				
	}

	@Test
	public void Couponcashback() throws Exception {
		get_Bento_Url(driver, "AirCouponCash", "");
		bento_Select_PaymentType(driver, "NB");
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Cashback_Icon"), "Cashback Icon", 20);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Cashback_Text"), "Cashback text", 1);
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Coupon_Cashback_Details"), "Cashback details", 1);
		textPresent_Log(driver, "Earn ₹ 100 cashback with this booking", 1);
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