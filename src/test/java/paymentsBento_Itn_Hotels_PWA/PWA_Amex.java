package test.java.paymentsBento_Itn_Hotels_PWA;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_Amex extends PaymentsBento_Itn_Hotels_Common_PWA {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getMobileDriver(driver);
	}

	@Test
	public void Hotel_IN_AmazonPay() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://qa2new.cleartrip.com/pay/hotel/1f1161f7a7b90ce272ab9393d1e67839?lang=en");
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
