package test.java.  paymentsBento_Itn_Hotels;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IN_UPI_Failure_Success extends PaymentsBento_Itn_Hotels_Common {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
	}

	@Test
	public void Hotel_IN_UPI() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(hotelDetailsUrl("IN", hotelName_DetailsPage));
		hotelsDetailsPage(driver, "", "");
		hotelsItnPage(driver, "", "", "", "");
		hotelsPaymentPage(driver, "UPI", "", "", "Failure", "", "UPI failure : ");  // failure case
		hotelsPaymentPage(driver, "UPI", "", "", "", "", "UPI : ");
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