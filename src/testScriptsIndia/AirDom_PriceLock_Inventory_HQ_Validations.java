package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

import domainServices.HQ;
import domainServices.AirCommonMethod;

public class AirDom_PriceLock_Inventory_HQ_Validations extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	String tripId = null;
	String domain = "com";
	HashMap<String, Double> hp = new HashMap<String, Double>();

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) { 
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@Test(dataProvider = "B2CAirPricelock")
	public void Air_PriceLock_Inventory_HQ_Validations (String[] origin, String[] destination, String tripType, String flight_type,
			String[] flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		int balanceInPayBlock = 0;

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				if (!checkIfSignedIn(driver)) {
					airCom_HomepageSignInForHQScripts(driver, domain);
				}
			}
			//safeClick(driver, getObject("AirSideAppButtonXPath"));

			airCom_HomepageSearch_Oneway_Pricelock(driver,origin[attempt],destination[attempt],
					adults, children, infants, flightPreference[attempt], attempt);

			Reporter.log("Search URL is : " + driver.getCurrentUrl()); // capture itinerary
			System.out.println(driver.getCurrentUrl());

			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));

			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}

			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}

			Reporter.log("Itinerary RUL : " + driver.getCurrentUrl());
			System.out.println("Itinerary RUL : " + driver.getCurrentUrl());

			safeClick(driver, getObject("AirCom_SRP_Oneway_BookButton"));
			insuranceBlockProcess(driver, insuranceRequired); // to uncheck Insurance if pre-selected

			// If pricelock option not found refresh url and check 
			boolean gotLock= false;
			int priceLockAttempt = 0;
			while(!gotLock && priceLockAttempt < 3){
				if (elementPresent(driver, getObject("AirCom_BookStep_PriceLock_rBtn"))){
					safeClick(driver, getObject("AirCom_BookStep_PriceLock_rBtn"));
					gotLock=true;
				}
				else{
					Reporter.log("refreshing page to make avail_lock call again...");
					System.out.println("refreshing page to make avail_lock call again...");
					driver.navigate().refresh();
					Thread.sleep(5000);
				}
				priceLockAttempt++;
			}
			if (!gotLock) {
				attempt++;
				continue;
			}

			//priceLockAmountAssertionInBookStep(driver, hp);
			safeClick(driver, getObject("air_review_itinerary_continue"));
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);

			if (reachedPaymentStep) {
				String balanceInPayBlockString = getText(driver, getObject("AirCom_BookStep_PriceLock_Balance_WithPGfee"));
				balanceInPayBlockString=balanceInPayBlockString.replaceAll(",", "");
				balanceInPayBlock = Integer.parseInt(balanceInPayBlockString);

				paymentDone = b2cPayment(driver, paymentMethod, 1);
				if (paymentDone == true)
					recheckAirlinePrice(driver);
				else if (!(common.value("makePayment").equals("true")))
				{
					Reporter.log("Make Payment Flag is set to True. Hence Ignoring payment and setting booking passed as true.",true);
					bookingPassed = true;
					break;
				}
				else if (paymentDone == false) {
					attempt++;
					Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
					continue;
				}
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
				continue;
			}
			attempt++;
			
			bookingPassed = checkBookingStatus(driver);
			
			System.out.println("Pricelock 1st Payment done: "+driver.getCurrentUrl());
			Reporter.log("PriceLock Booking 1st payment Done " + getText(driver, getObject("AirCom_Confirmation_TripID")));
			System.out.println("PriceLock Booking is Done " + getText(driver, getObject("AirCom_Confirmation_TripID")));
			tripId=getText(driver, getObject("AirCom_Confirmation_TripID"));

		} while (!bookingPassed && attempt < 3);
		
		//Verification of Lock Type in HQ-Trips
		Thread.sleep(10000);
		driver.get(getBaseUrl(domain) + "/hq/trips/" + tripId);
		System.out.println(driver.getCurrentUrl());
		Reporter.log(driver.getCurrentUrl());
		
		waitElement(driver, getObject("AirCom_PriceLock_HqTrips_Yellow_Banner"), 180);
		String lockType = getText(driver, getObject("AirCom_PriceLock_HqTrips_Yellow_Banner")); //Inventory
		boolean checkLockType=false;
		if (lockType.contains("Inventory")){
			Reporter.log(tripId+ ": Lock Type verified as 'Inventory' in HQ-Trips");
			System.out.println(tripId+ ": Lock Type verified as 'Inventory' in HQ-Trips");
			String airPnr=getText(driver, getObject("AirCom_PriceLock_HqTrips_AirlinePNR"));
			String airTicket=getText(driver, getObject("AirCom_PriceLock_HqTrips_TicketNum"));
			
			if(!airPnr.isEmpty() && !airTicket.isEmpty()){
				checkLockType=true;
			}
		}
		Assert.assertTrue(checkLockType, "PriceLock trip is not of Inventory lock type");
		Reporter.log(tripId+ ": Lock Type 'Inventory' - 'AirLine PNR' and 'Ticketing' is done as expected.");
		
		//Verification of PriceLock Trips in Flexi Pay Trips Queue
		Thread.sleep(10000);
		driver.get(getBaseUrl(domain) + "/hq/workflows/flexi_payments/air?trip_ref=" + tripId);
		Reporter.log(driver.getCurrentUrl());
		String pendingAmountString = "";
		int pendingAmount = 0;
		if(elementPresent_Time(driver, getObject("AirCom_FlexiPayQueue_Pending_Amount"), 15))
		{
			pendingAmountString = getText(driver, getObject("AirCom_FlexiPayQueue_Pending_Amount"));
			pendingAmount = Integer.parseInt(pendingAmountString);
		}
		else
		{
			Assert.fail("Trip Not found in the Flexipay Queue!");
		}
/*		boolean balanceMatch=false;
		if(balance==pendingAmount){
			balanceMatch=true;
		}
		Assert.assertTrue(pass, "Mismatch in balance amount in flexiPay Queue");*/
		
		Assert.assertEquals(pendingAmount, balanceInPayBlock, "Mismatch in balance amount in flexiPay Queue");
		Reporter.log("Pricelock trip Pending Amount Veified in FlexiPay Queue");
		
		}


	@DataProvider(name = "B2CAirPricelock")
	public static Object[][] B2CAirOWPricelock() {
		String[] origin = { "del", "bom"};
		String[] destination = {"bom","del"};
		String[] airline = {"SpiceJet","Vistara"};
		return new Object[][] { { origin, destination, "OneWay", "lcc", airline , "Direct", "1", "0", "0",
			"credit card", false}};
	}


	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception {
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		//System.out.println("Test Case:" + _result.getMethod().getMethodName());
	}

}
