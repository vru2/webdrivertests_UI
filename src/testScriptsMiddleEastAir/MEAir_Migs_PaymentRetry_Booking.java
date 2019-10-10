
package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
import dataServices.IndiaDataProvider;
import domainServices.AirCommonMethod;

	public class MEAir_Migs_PaymentRetry_Booking extends AirCommonMethod{
		public RemoteWebDriver driver = null;
		public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
		String tripId = null;
		String baseUrl = null;
		String domain ="ae";

		@Test(dataProviderClass =IndiaDataProvider.class,dataProvider="AirOneWayDomestic")
		public void MEAir_PaymentRetry_MigsCard_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference, String flightFilterType,
				String resultClickRow, String adults, String children, String infants,//TODO:resultClickRow not used, user name pass to be fixed
				String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {
	  
			boolean flagFareRules = false;
			boolean flagCancellation = false;
			boolean bookingPassed = false;
			boolean result = true;
			boolean reschedule = false;
			boolean warningFound = false;
			boolean flagCancelConfirm = false;
			boolean flightCountFailure = true;
			boolean paymentDone = false;
			List<String> HQFlow = null;
			int attempt = 0;

			driver.get(baseUrl);
	  //driver.get(baseUrl);
	   airCom_HomepageSignIn(driver,domain);
	   airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants, flightFilterType,1);// TODO:add more parameters
		Reporter.log("Search URL is : " + driver.getCurrentUrl());
		
	   airCom_SRP_Oneway(driver);
	   //airCom_BS1_ReviewItinerary(driver);
	   assertionLinkedList = captureItineraryData(driver);
		insuranceBlock(driver, insuranceRequired);
		travellerDetails(driver, adults, children, infants, false, false, false);
		
	   //payment retry
	   
	   elementVisible(driver, getObject("AirCom_BookStep4_CreditCard_Tab"), 200);
		safeClick(driver, getObject("AirCom_BookStep4_CreditCard_Tab"));
		safeType(driver, getObject("AirCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
		safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Month"), "03");
		safeSelect(driver, getObject("AirCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
		safeType(driver, getObject("AirCom_BookStep4_CreditCard_HolderNamer"), dataFile.value("MasterCard_HolderName"));
		safeType(driver, getObject("AirCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
		safeClick(driver, getObject("AirCom_BookStep4_MakePayment_Button"));
		Thread.sleep(5000);
		textPresent(driver, "Oops, your payment didn’t work", 60000);
		assertEquals("Oops, your payment didn’t work", driver.findElement(By.cssSelector("h1")).getText());
		Reporter.log("Payment retry page displayed");
		//System.out.println("Payment retry page displayed");
	   String Trip_Logger_Msg = null;
	   airCom_BS_Payment(driver, Trip_Logger_Msg);
	 	   
	   
	}

		@BeforeClass
		public void startSelenium() throws Exception {
			this.driver = getDriver(driver);
			if (driver == null) {
				Reporter.log("Error in initial setup. Exiting without screenshot");
				throw new SkipException("Skipping Test: ");
			}
			baseUrl = common.value("meairurl");
		}
  
  
  
  @AfterMethod (alwaysRun = true)
  public void takeScreenshot(ITestResult _result) throws Exception{
   screenshot(_result, driver);
  }
  
  @AfterClass
  public void tearDown() throws Exception {
	  driver.quit();    
  }

}