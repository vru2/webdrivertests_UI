package testScriptsPayments;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
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
import domainServices.AirCommonMethod;

public class B2CAirCheckPaymentOptions extends AirCommonMethod {
	public RemoteWebDriver driver = null;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}


	@Test( dataProvider = "B2CAirOWLCC")
	public void airCom_Dom_AmexCard_Booking_157(String[] fromSet, String[] toSet, String app, String tripType,
			String flight_type, String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

		boolean flightCountFailure = true;
		boolean reachedPayment = false;
		int attempt = 0;

		String Check_In_Date = putDate1(20);
		String Check_In_Date1 = putDate1(50);
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			if(attempt==0) {
			driver.get(baseUrl+"/flights/results?from="+fromSet[attempt]+"&to="+toSet[attempt]+"&depart_date="+Check_In_Date+"&adults="+adults+"&childs="+children+"&infants="+infants+"&class=Economy&airline=&carrier=&intl=n");
			}if(attempt==1) {
				driver.get(baseUrl+"/flights/results?from="+fromSet[attempt]+"&to="+toSet[attempt]+"&depart_date="+Check_In_Date1+"&adults="+adults+"&childs="+children+"&infants="+infants+"&class=Economy&airline=&carrier=&intl=n");
				}
			//airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,	flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				// System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			airCom_SRP_Oneway(driver);

			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				// System.out.println("Redirected back to SRP after clicking on book button. Making another attempt"); attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			// assertionLinkedList = captureItineraryData(driver);
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				reachedPayment = true;
				assertTrue("Cleartrip Wallet option not present. Error!", isElementPresent(driver, By.id("walletOptOut")));
				assertTrue("Credit Card option not present. Error!",
						isElementPresent(driver, getObject("AirCom_BookStep4_CreditCard_Tab")));
				assertTrue("Debit Card option not present. Error!",
						isElementPresent(driver, getObject("AirCom_BookStep4_DebitCard_Tab")));
				assertTrue("NetBanking option not present. Error!",
						isElementPresent(driver, getObject("AirCom_BookStep4_NetBanking_Tab")));
				assertTrue("EMI option not present. Error!", isElementPresent(driver, getObject("AirCom_BookStep4_EMI_Tab")));
				assertTrue("Wallets option not present. Error!",
						isElementPresent(driver, getObject("AirCom_BookStep4_Wallets_Tab")));

				Reporter.log("Test case " + this.getClass() + " passed successfully",true);
				System.out.println("Test case " + this.getClass() + " passed successfully");
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + ++attempt);
				continue;
			}
		} while (attempt < 2 && !reachedPayment);
		assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (reachedPayment)));
		Reporter.log("Test case " + this.getClass() + " passed successfully");
		System.out.println("Test case " + this.getClass() + " passed successfully");
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "bom", "MAA" };
		String[] destination = { "del", "BLR" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}

}
