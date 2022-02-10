package paymentsBento_Itn;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Bento_IN_PartialWallet_AMEX_Booking extends PaymentsBento_Itn_Common{
	
	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}
	
	@Test
	public void bento_partial_walletAmex() throws Exception {
		Response resp;
		resp=RestAssured.get("http://172.17.51.86:8071/payments/wallet/cashback?emailId=varalakshmivaru29@gmail.com&currency=INR&amount=10&tripRef=Q190729442390&expiryDate%20=31/12/21");
		Reporter.log(resp.asString());
		driver.manage().deleteAllCookies();
		driver.navigate().to(searchurl("IN"));
		Reporter.log(searchurl("IN"));
		Searchpagebook(driver,"Partial","com","");
	    book_itnnew(driver,"");
	    paymentPage(driver,"partial_wallet","","","","");
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