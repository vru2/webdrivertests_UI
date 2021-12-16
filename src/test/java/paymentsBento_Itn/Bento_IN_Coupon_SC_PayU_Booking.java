package paymentsBento_Itn;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_IN_Coupon_SC_PayU_Booking extends PaymentsBento_Itn_Common {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}

	@Test
	public void bento_coupon() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(qa2url + searchurl);
		System.out.println(qa2url + searchurl);
		Reporter.log(qa2url + searchurl);
		Searchpagebook(driver, "","com","amex");
		book_itnnew(driver,"Coupon"); 
		paymentPage(driver,"Coupon","","","",""); 
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

}
