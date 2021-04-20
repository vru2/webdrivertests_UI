package paymentsBento_com;

import static org.testng.Assert.assertTrue;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_Full_GV_Booking extends PaymentUI_Common_Bento{

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}
	
	@Test
	public void bento_gv() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(searchurl);
		System.out.println(searchurl);
		Reporter.log(searchurl);
	    Searchpagebook(driver);
	    book_itnnew(driver,"GV");
	    if(textPresent(driver,"Sorry, our servers are stumped with your request",30)||textPresent(driver,"Flight not available",30))
	    {
	    	System.out.println("Booking failed due to itn page issue");
	    	Reporter.log("Booking failed due to itn page issue");
	    	assertTrue(false);
	    }
	    else
	    {
	    bento_paymentpage(driver,"GV");
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
