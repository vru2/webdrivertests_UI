package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

/*
 * Added By: anil.patil@cleartrip.com
 * EBL-6672: Test case to make sure that we are preventing international credit/debit card payments for Air Asia flights to prevent fraud evidenced in production 
 */

public class AirIntl_AirAsia_International_CC_DC_FraudCheck extends AirCommonMethod{
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
	String searchUrl = null;

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"MAA","BLR", "TRZ"};
		String[] destination = {"KUAL","KUAL", "KUAL"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "Air Asia", "", "1", "0", "0",
				"credit card", false} };
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void airIntl_AirAsia_International_CC_DC_FraudCheck_(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		String DCPayment = "debit card";
		boolean ccFlag = false;
		boolean dcFlag = false;
				
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) 
			{
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
			
			Thread.sleep(5000);
			searchUrl = driver.getCurrentUrl();
			connectorSearch(driver, searchUrl);
			
			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}
			
			if(filterFlightsByPreferedAirline1(driver,flightPreference, 0))
			{
				attempt++;
				continue;
			}
			
			airCom_SRP_Oneway(driver);
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants,true, false, false);
			
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			
			if (reachedPaymentStep) 
			{
					paymentDone = b2cPayment(driver, paymentMethod, 5);
					boolean ccAttempt = checkTripID(driver);
					if (ccAttempt == false) 
					{
						if (driver.findElement(getObject("AirCom_BookStep4_Payment_CC_Error_Message")).getText().contains("Sorry"))
						{
							String errorMgs = driver.findElement(getObject("AirCom_BookStep4_Payment_CC_Error_Message")).getText();
							Reporter.log("AirAsia Intl CC Fraud payment prevented with error message : " + errorMgs, true);
							ccFlag=true;
						}
					}
					else
					{
						Assert.fail("Expected CC Payment failure with error message on clicking 'Make Payment Button', but payment re-direction happened..");
					}
					
					paymentDone = b2cPayment(driver, DCPayment, 7);
					boolean dcAttempt = checkTripID(driver);
					if (dcAttempt == false) 
					{
						if (driver.findElement(getObject("AirCom_BookStep4_Payment_DC_Error_Message")).getText().contains("Sorry"))
						{
							String errorMgs = driver.findElement(getObject("AirCom_BookStep4_Payment_CC_Error_Message")).getText();
							//assertTrue("Sorry", errorMgs.contains("Sorry"));
							Reporter.log("AirAsia Intl DC Fraud payment prevented with error message : " + errorMgs, true);
							dcFlag=true;
						}
					}
					else
					{
						Assert.fail("Expected DC Payment failure with error message on clicking 'Make Payment Button', but payment re-direction happened..");
					}
			}
			
			Assert.assertTrue(ccFlag && dcFlag , "Test failed:Payment error message not dispalyed");
		} while (!ccFlag && !dcFlag && attempt < 3);
		Assert.assertTrue(((attempt < 4)), "Test failed after 3 attempts");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}




}