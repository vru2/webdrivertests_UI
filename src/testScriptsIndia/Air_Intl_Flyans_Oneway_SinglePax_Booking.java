



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

import domainServices.India;



public class Air_Intl_Flyans_Oneway_SinglePax_Booking extends India {/*

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
	public static Object[][] B2CAirAIE_Connector() {
		String[] origin = { "RUH", "AMM" };
		String[] destination = { "JED", "RUH" };
		return new Object[][] { { origin, destination, "Flights", "OneWay",
				"lcc", "Flynas", "", "1", "0", "0", "credit card"} };
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void Air_Intl_FlynasMPax_Booking_OW(String[] fromSet,
			String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults,
			String children, String infants, String paymentMethod) throws Exception {

		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}

			airCom_HomepageSearch_Oneway(driver, fromSet[attempt],
					toSet[attempt], "10", adults, children, infants,
					flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);

			Thread.sleep(5000);
			searchUrl = driver.getCurrentUrl();
			// connectorSearch(driver, searchUrl);

			flightCountFailure = waitForElement(driver, 90,
					getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log(
						"No results found for this search. Making another attempt with different sectors.",
						true);
				attempt++;
				continue;
			}

			if (filterFlightsByPreferedAirline1(driver, flightPreference, 0)) {
				attempt++;
				continue;
			}

			airCom_SRP_Oneway(driver);
			driver.findElement(By.xpath("//div/dl/dd[2]/input[@id='itineraryBtn']")).click();
			System.out.println("able to click");
		//	insuranceBlock(driver, insuranceRequired);
			travellerDetails(driver, adults, children, infants, true, false,false);

			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 1);
					boolean error = false;
					if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");// workaround
					else if (paymentDone == false) {
						attempt++;
						Reporter.log(
								"Flight full error popped up. Re starting book process. Attempt number: "
										+ attempt, true);
						continue;
					}
					if (error) {
						attempt++;
						continue;
					}
				} else {
					Reporter.log(
							"Make Payment is set to false. Not attemptign Payment",
							true);
					bookingPassed = true;
					break;
				}
			} else {

				attempt++;
				Reporter.log(
						"Flight full error popped up. Re starting book process. Attempt number: "
								+ attempt, true);
				continue;
			}
			attempt++;
			bookingPassed = checkTripID(driver);

		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));

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

*/}