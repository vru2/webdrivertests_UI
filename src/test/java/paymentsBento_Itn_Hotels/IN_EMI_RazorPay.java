package test.java.  paymentsBento_Itn_Hotels;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
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
		//System.setproperty("webdriver.chrome.driver", "chromedriver");
		//Webdriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get(hotelDetailsUrl("IN", hotelName_DetailsPage));
		hotelsDetailsPage(driver, "", "");
		hotelsItnPage(driver, "", "", "", "");
		bento_Validation_Text(driver, "EMI", "");
		hotelsPaymentPage(driver,"EMI","RAZORPAY","","EMI","", "EMI Razorpay : " );
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
