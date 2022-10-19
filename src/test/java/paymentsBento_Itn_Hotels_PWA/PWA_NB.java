package test.java.paymentsBento_Itn_Hotels_PWA;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_NB extends PaymentsBento_Itn_Hotels_Common_PWA {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
	}

	@Test
	public void Hotel_IN_AmazonPay() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get("https://qa2.cleartrip.com/pay/hotel/UV1SeEJCRhBAMTIyUQ==?lang=en");
		hotelsPaymentPage_PWA(driver,"NB","","","","");
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
