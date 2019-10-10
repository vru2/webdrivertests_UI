package testScriptsPayments;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class AirCom_OW_Full_GiftVoucher_Booking extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";

	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "bom", "maa" };
		String[] destination = { "del", "blr" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "1", "0", "0", "giftvoucher",
				false } };
	}

	@Test(groups = "Regression", dataProvider = "B2CAirOWLCC")
	public void airCom_OW_Full_GiftVoucher_Booking_156(String[] fromSet, String[] toSet, String app, String tripType,
			String flight_type, String flightPreference, String adults, String children, String infants, String paymentMethod,
			boolean insuranceRequired) throws Exception {

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;

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
			//airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				// System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			airCom_SRP_Oneway(driver);
			JSONObject gv = giftVoucherCreation(15000);
			applyGiftVoucher(driver, gv.get("gvNumber").toString(), gv.get("gvPin").toString());
			insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, false, false, false);
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");//workaround
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt,true);
						continue;
					}
					if (error) {
						attempt++;
						continue;
					}
				} else {
					Reporter.log("Make Payment is set to false. Not attemptign Payment", true);
					bookingPassed = true;
					break;
				}
			} else {
				
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt, true);
				continue;
			}
			attempt++;
			bookingPassed = checkBookingStatus(driver);
		} while (!bookingPassed && attempt < 2);
		assertTrue("Booking failed after 3 attempts", ((attempt < 3) && (bookingPassed)));

		if (paymentDone) {
			tripId = getTripId(driver, getObject("AirCom_Confirmation_TripID"));
			Reporter.log("Trip booked is : " + tripId);

			if (bookingPassed && paymentMethod.equals("giftvoucher")) {
				String GF = driver.findElement(getObject("AirCom_GiftVoucher_ConfirmationPageTxt")).getText();
				assertTrue("GiftVoucher Booking is Not Done", GF.contains("gift card"));
			}
		}
	}

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	  }

	  @AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}
	  
	  @AfterClass
	  public void tearDown() throws Exception {
		  browserClose(driver);	  
	  }
}