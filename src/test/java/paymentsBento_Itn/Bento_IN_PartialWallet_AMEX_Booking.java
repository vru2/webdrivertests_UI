package paymentsBento_Itn;

import static org.testng.Assert.assertTrue;

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
	public void bento_partial_wallet() throws Exception {
		Response resp;
		resp=RestAssured.get("http://172.17.51.86:8071/payments/wallet/cashback?emailId=varalakshmivaru29@gmail.com&currency=INR&amount=150&tripRef=Q190729442390&expiryDate%20=31/12/21");
		System.out.println(resp.asString());
		Reporter.log(resp.asString());
		driver.manage().deleteAllCookies();
		driver.navigate().to(qa2url+searchurl);
	    System.out.println(qa2url+searchurl);
	    Reporter.log(qa2url+searchurl);
		Searchpagebook(driver,"Partial","com","");
	    book_itnnew(driver,"");
	    if(textPresent(driver,"Pay to complete your booking",20)) {
	    	bento_paymentpage(driver,"partial_wallet","","");
		    confirmation_page(driver);    
	   }
	   else if(textPresent(driver,"Sorry, our servers are stumped with your request",1)||textPresent(driver,"Flight not available",1))
	    {
	    	System.out.println("Booking failed due to itn page issue");
	    	Reporter.log("Booking failed due to itn page issue");
	    	assertTrue(false);
	    }
	   else
		  {
			  Reporter.log("Booking failed due to itn page issue");
			  assertTrue(false);
		  }
	    
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