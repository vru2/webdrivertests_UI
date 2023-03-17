package test.java.payment_Hotels_Desktop;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.commonUI.PaymentsUI_Common_Desktop_Hotels;

public class IN_GV_SCLP_GV_NB extends PaymentsUI_Common_Desktop_Hotels {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
	}

	@Test
	public void Hotel_IN_WL_GV_CC_Amex() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(hotelDetailsUrl("IN", hotelName_DetailsPage));
		addwalletamount(10, "ct_wallet_partial@cleartrip.com");
		hotelsDetailsPage(driver, "", "");
		hotelsItnPage(driver, "PartialGV_SCLP", "", "", "");
		driver.manage().addCookie(hotelLogin);
		hotelsPaymentPage(driver,"NB","","Hotels","","", "SCLP WL + NB : ");// Invalid Coupon Validation
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