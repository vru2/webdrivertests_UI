package paymentsBento_Itn_Hotels;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class IN_Wallet_Full extends PaymentsBento_Itn_Hotels_Common {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
	}

	@Test
	public void Hotel_IN_Wallet_Full() throws Exception {
		driver.manage().deleteAllCookies();
		driver.get(hotelDetailsUrl("IN","sri-balaji-paradise-2626774"));
		//driver.manage().addCookie(ctauth_partial_wallet);
		driver.manage().addCookie(hotelLogin);
		addwalletamount(1500);
		hotelsDetailsPage(driver, "", "");
		refreshPage(driver);
		hotelsItnPage(driver, "", "", "", "");
		hotelsPaymentPage(driver, "WALLET", "", "", "", "");
	} 


	public void addwalletamount1(int amount) throws Exception
	{
		Response resp;
		String url="http://172.17.51.86:8071/payments/wallet/cashback?emailId=c_wallet_partial@cleartrip.com&currency=INR&amount="+amount+"&tripRef=Q190729442390&expiryDate%20=31/12/22";
		resp=RestAssured.get(url);
		Reporter.log(resp.asString());
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
