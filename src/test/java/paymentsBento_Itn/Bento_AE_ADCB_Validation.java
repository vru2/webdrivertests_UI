package paymentsBento_Itn;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_AE_ADCB_Validation extends PaymentsBento_Itn_Common {

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void bento_ae_booking() throws Exception {
		driver.manage().deleteAllCookies();
		/*
		 * driver.navigate().to(aeurl); Thread.sleep(2000);
		 */
		driver.navigate().to(aeurl+searchurl);
		System.out.println(aeurl+searchurl);
		Reporter.log(aeurl+searchurl);
		Searchpagebook(driver,"","","");
		noncom_itnpage(driver,"","ae");
	   if(textPresent(driver,"Pay to complete your booking", 30))
	    {
	      bento_paymentpage(driver,"AE-SC","ADCB","");
	   //  confirmation_page(driver);
	    }
	    else if(textPresent(driver,"Sorry, our servers are stumped with your request",1)||textPresent(driver,"Flight not available",1))
	    {
	    	System.out.println("Booking failed due to itn page issue");
	    	Reporter.log("Booking failed due to itn page issue");
	    	assertTrue(false);
	    }
	 else {
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