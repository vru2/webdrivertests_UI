package test.java.payment_Hotels_Web;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_Desktop_Hotels;

public class Hotels_Web_CC_Razorpay extends PaymentsUI_Common_Desktop_Hotels {

		@BeforeClass
		public void startSelenium() throws Exception {
			this.driver = getDriver(driver);
		}

		@Test
		public void Hotel_IN_RazorPay() throws Exception {
			driver.manage().deleteAllCookies();
			driver.get(hotelDetailsUrl("IN", hotelName_DetailsPage));
			hotelsDetailsPage(driver, "", "");
			hotelsItnPage(driver, "", "", "", "");
			hotelsPaymentPage(driver,"CC","5241","","","", "524121 CC Razorpay : ");
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