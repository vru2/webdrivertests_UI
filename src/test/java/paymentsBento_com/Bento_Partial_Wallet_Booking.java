package paymentsBento_com;

import static org.testng.Assert.assertTrue;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Bento_Partial_Wallet_Booking extends PaymentUI_Common_Bento{
	
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
		driver.navigate().to(searchurl);
	    System.out.println(searchurl);
	    Reporter.log(searchurl);
		Searchpagebook(driver,"Partial");
	    book_itnnew(driver,"");
	    if(elementVisible(driver,getObjectPayment("Bento_Payment_PayText"),30)) {
	    	bento_paymentpage(driver,"partial_wallet");
		    confirmation_page(driver);    
	   }
	   else if(textPresent(driver,"Sorry, our servers are stumped with your request",30)||textPresent(driver,"Flight not available",30))
	    {
	    	System.out.println("Booking failed due to itn page issue");
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

