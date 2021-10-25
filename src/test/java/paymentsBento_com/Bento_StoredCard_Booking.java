package paymentsBento_com;


import static org.testng.Assert.assertTrue;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_StoredCard_Booking extends PaymentUI_Common_Bento{
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}
	
	@Test
	public void bento_storedcard() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(searchurl);
		System.out.println(searchurl);
		Reporter.log(searchurl);
	    Searchpagebook(driver,"");
	    book_itnnew(driver,"");
	    if(elementVisible(driver,getObjectPayment("Bento_Payment_PayText"),30)) {
	    	 bento_paymentpage(driver,"storedcard");
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
