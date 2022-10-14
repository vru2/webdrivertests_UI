package test.java.paymentsBento_Itn_Hotels_PWA;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_UPI extends PaymentsBento_Itn_Hotels_Common_PWA {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
	}

	@Test
	public void Hotel_IN_AmazonPay() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(hotelDetailsUrl("IN", hotelName_DetailsPage));
		hotelsDetailsPage(driver, "", "");
		hotelsItnPage(driver, "", "", "", "");
		Thread.sleep(5000);
		String URL = getURL(driver);
		driver=(RemoteWebDriver) getMobileDriver(driver);
		driver.get(URL);
		hotelsPaymentPage_PWA(driver,"UPI","","","","");
		Thread.sleep(5000);
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
