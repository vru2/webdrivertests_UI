package test.java.  paymentsBento_Itn_Hotels;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IN_EMI_RazorPay extends PaymentsBento_Itn_Hotels_Common {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
	}

	@Test
	public void Hotel_IN_EMI() throws Exception {
		driver.manage().deleteAllCookies();
	/*	driver.get(hotelDetailsUrl_3Days("IN", hotelName_DetailsPage));
		hotelsDetailsPage(driver, "", "");
		hotelsItnPage(driver, "", "", "", "");
		bento_Validation_Text(driver, "EMI", "");*/
		driver.get("https://qa2new.cleartrip.com/pay/hotel/444b1a78c870b4f12b67908ee3f84b73?lang=en");
		hotelsPaymentPage(driver,"EMI","RAZORPAY","","",""); 
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
