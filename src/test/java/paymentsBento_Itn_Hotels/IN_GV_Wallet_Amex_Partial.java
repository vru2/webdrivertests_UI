package test.java.  paymentsBento_Itn_Hotels;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IN_GV_Wallet_Amex_Partial extends PaymentsBento_Itn_Hotels_Common {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
	}

	@Test
	public void Hotel_IN_WL_GV_CC_Amex() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(hotelDetailsUrl("IN", hotelName_DetailsPage));
		driver.manage().addCookie(hotelLogin);
		addwalletamount(10, "ct_wallet_partial@cleartrip.com");
		hotelsDetailsPage(driver, "", "");
		hotelsItnPage(driver, "PartialGV", "", "", "");
		hotelsPaymentPage(driver,"CC","3456","","WALLET","", "MULTI GV + WL + AMEX : ");
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
