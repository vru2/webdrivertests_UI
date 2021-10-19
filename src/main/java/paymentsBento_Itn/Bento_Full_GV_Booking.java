package paymentsBento_Itn;

import static org.testng.Assert.assertTrue;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_Full_GV_Booking extends PaymentsBento_Itn_Common{

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}
	
	@Test
	public void bento_gv() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(qa2url+searchurl);
		System.out.println(qa2url+searchurl);
		Reporter.log(qa2url+searchurl);
	    Searchpagebook(driver,"","com");
	    book_itnnew(driver,"GV");
	    if(textPresent(driver,"Pay to complete your booking",20)) {

		    bento_paymentpage(driver,"GV","");
		    confirmation_page(driver);
		    
	   }
	   else if(textPresent(driver,"Sorry, our servers are stumped with your request",30)||textPresent(driver,"Flight not available",1))
	    {
	    	System.out.println("Booking failed due to itn page issue");
	    	Reporter.log("Booking failed due to itn page issue");
	    	assertTrue(false);
	    }
	   else {
		   bento_paymentpage(driver,"GV","");
		    confirmation_page(driver);
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
