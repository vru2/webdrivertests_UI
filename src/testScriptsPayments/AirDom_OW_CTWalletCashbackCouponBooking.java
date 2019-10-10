package testScriptsPayments;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataServices.HQDataProvider;
import domainServices.HQ;
import domainServices.AirCommonMethod;

public class AirDom_OW_CTWalletCashbackCouponBooking extends AirCommonMethod 
{

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception 
	{
		this.driver = getDriver(driver);
		if (driver == null) 
		{
			Reporter.log("Error in initial setup. Exiting without screenshot",true);
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	
	 @DataProvider(name = "B2CAirCTWalletCashback") public static Object[][] B2CAirCTWalletCashback() { String[] origin =
	 {"del", "blr", "kolkata"}; String[] destination = {"bom", "maa", "cochin"}; return new Object[][] { { origin, destination,
	 "Flights", "OneWay", "", "Direct", "1", "0", "0", "credit card", false, "Auto Refund"}}; }
	 

	@BeforeMethod
	public void beforeMethod() 
	{
		timer();
	}
	
	@Test(dataProvider = "B2CAirCTWalletCashback")
	public void airDom_OW_CTWalletCashbackCouponBooking_17050(String[] fromSet, String[] toSet, String app, String tripType,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception
	{

		Reporter.log("Test case " + this.getClass() + " started", true);

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		String cashback="awaited";

		HQ hq = new HQ();

		do 
		{
			driver.get(baseUrl);
			
			if (!checkIfSignedIn(driver)) 
			{
				airCom_HomepageSignInForWallet(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,	flightPreference, attempt);
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl(), true);
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.", true);
				attempt++;
				continue;
			}
			
			WebElement we = pickFirstFlight(driver);
			if (we != null) 
			{
				bookButtonDom(we);
			}
			else 
			{
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.", true);
				attempt++;
				continue;
			}
			
			cheaperRateBlock(driver);
			
			cashback = Apply_CouponCode(driver, "DOMOWCB");
			
			insuranceBlock(driver, insuranceRequired);
			
			travellerDetails(driver, adults, children, infants, false, false, false);
			
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) 
			{
				if ((common.value("makePayment").equals("true"))) 
				{
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");//workaround
					else if (paymentDone == false) 
					{
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt,true);
						continue;
					}
					if (error) 
					{
						attempt++;
						continue;
					}
				}
				else 
				{
					Reporter.log("Make Payment is set to false. Not attemptign Payment", true);
					bookingPassed = true;
					break;
				}
			}
			else 
			{				
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt, true);
				continue;
			}
			attempt++;
			bookingPassed = checkBookingStatus(driver);
		} 
		while (!bookingPassed && attempt < 3);
		
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));

		if (paymentDone) 
		{
			tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
			signOut(driver, domain);
			Thread.sleep(15000);
		}

		driver.get(getBaseUrl(domain) + "/hq");
		hq.signInHQ(driver);
		hq.confirmTripLoad(driver, tripId, domain);
		String status;
		Boolean converted = false;
		attempt = 0;
		do 
		{
			attempt++;
			Thread.sleep(10000);
			driver.navigate().refresh();
			status = hq.getBookingStatus(driver);
			Reporter.log("Booking status is " + status, true);
			// System.out.println("Booking status is " + status);
			if (hq.checkIfThisStatusForAtleastOneSegment(driver, "Booking Failed")) 
			{
				if (check_booking_failure) 
				{
					Reporter.log("Booking failed, confirmed from HQ. Error!", true);
					// System.out.println("Booking failed, confirmed from HQ. Error!");
					assertTrue("Failure!", false);
				}
				if (!converted) 
				{
					hq.processOfflineConversion(driver, tripId);
					hq.confirmTripLoad(driver, tripId, domain);
					converted = true;
					Thread.sleep(1000);
					status = hq.getBookingStatus(driver);
				} 
				else
				{
					Reporter.log("Trip converted offline, still status is Booking Failed! Error!", true);
					// System.out.println("Trip converted offline, still status is Booking Failed! Error!");
					assertTrue("Failure!", false);
				}
			}
		} 
		while (!hq.checkIfStatusConfirmedForAllSegment(driver) && attempt < 3);

		driver.get("https://qa2.cleartrip.com/hq/people/" + dataFile.value("AirUserIdKeyForWallet"));
		Thread.sleep(1000);

		boolean amountAdded = false;
		for (int i = 0; i < 18 && amountAdded == false; i++) 
		{
			if (waitElement(driver, By.linkText(tripId), 5)) 
			{
				assertTrue("Wrong amount credited to wallet. Error!", driver.findElement(getObject("Air_HQ_People_wallet_Txn_Amount")).getText().contains(cashback));
				amountAdded = true;
				break;
			}
			else 
			{
				Thread.sleep(10000);
				driver.navigate().refresh();
				Thread.sleep(1000);
			}
		}

		if (amountAdded) 
		{
			Reporter.log("Amount added: " + driver.findElement(getObject("Air_HQ_People_wallet_Txn_Amount")).getText(), true);
			Reporter.log("Test case " + this.getClass() + " passed successfully", true);
			//System.out.println("Test case " + this.getClass() + " passed successfully");
		} else 
		{
			Reporter.log("Waited around 3 mins still amount not added. Error!", true);
			// System.out.println("Waited around 3 mins still amount not added. Error!");
			assertTrue(false);
		}
	}

	@AfterClass
	public void closeSelenium() throws Exception 
	{
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception 
	{
		afterMethod(driver, _result);
	}
}
