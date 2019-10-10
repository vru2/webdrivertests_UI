package testScriptsMiddleEastAir;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;

public class MEAir_Intl_SplitScreen_SplRT_GDS_Booking extends AirCommonMethod {
	//Removed SPLRT after discussing with sphurthi
	public RemoteWebDriver driver = null;
	String tripId = null;
	String domain = "sa";

	@BeforeTest
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot",
					true);
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider(name = "AirIntlSplRT")
	public static Object[][] MeAirIntlSplRT() {
		String[] Origin = { "RUH", "AUH", "SHJ" };
		String[] Destination = { "JED", "JED", "DOH" };
		return new Object[][] { { Origin, Destination, "Flights", "OneWay", "",
				"Direct", "1", "0", "0", "credit card", false } };
	}

	@Test(dataProvider = "AirIntlSplRT")
	public void MEair_Intl_SplitScreen_SplRT_GDS_Booking_36478(String[] fromSet, String[] toSet, String app, String tripType, String flightPreference,
			String flightFilterType, String adults, String children, String infants, String paymentMethod,boolean insuranceRequired) throws Exception {

		Reporter.log("Test case " + this.getClass() + " started..",true);
		boolean bookingPassed = false;
		boolean flightFound = false;
		boolean splRTTab = true;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		do {
			driver.get(baseUrl);

			if(waitForElementVisibility(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"), 30))
				safeClick(driver, getObject("SAAirCom_Homepage_Language_Popup_English_Button"));
			
			
			if(driver.getCurrentUrl().equals("https://www.cleartrip.sa/ar/"))
			{
				safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu"));
				safeClick(driver, getObject("MEAirCom_HomePage_Language_Menu_English_Option"));
			}
			
			if (!checkIfAESignedIn(driver)) {
				driver.navigate().refresh();
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_RoundTrip(driver, fromSet[attempt], toSet[attempt], "10", "12", adults, children, infants,
					flightPreference, 1);
			
			Reporter.log("Search URL for attempt is :" + driver.getCurrentUrl(),true);
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for attempt "+(attempt+1)+".Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}
			ArrayList<String> filterByAirlines=new ArrayList<String>();
			filterByAirlines.add("Saudi Arabian Air");
			filterFlightsOnSrpByListOfAirlines(driver,filterByAirlines);
			//driver.get("https://qa2.cleartrip.sa/flights/international/results?from=DXB&to=DOH&depart_date=30/03/2018&return_date=03/04/2018&adults=1&childs=0&infants=0&dep_time=0&ret_time=0&class=Economy&airline=&carrier=&x=57&y=16&flexi_search=no&ssfc=true&ssfi=735319c82e-f33c-45ef-96eb-a12f7b100e23&page=loaded");
			elementPresent(driver,By.xpath("//button[@type='submit']"),90);
			List<WebElement> we=driver.findElements(By.xpath("//button[@type='submit']"));
			
			
				we.get(1).click();
			
			//driver.findElement(By.xpath("//button[@name='Book']")).click();
			//safeClick(driver,By.xpath("//button[@name='Book']"));
			//System.out.println("first one not worked");
			//safeClick(driver,By.xpath("//button[@type='submit'][2]"));
			//System.out.println("second one not worked");
			//safeClick(driver,By.xpath("//button[@type='submit'][1]"));
			/*if(elementPresent(driver,By.xpath(""))) {
				
			}*/
			//clickRoundTripBookButton(driver);
			//if(elementPresent(driver,By.xpath("//*[text()='Book' and @type='submit']"))) {
				//safeClick(driver,By.xpath("//div[@class='comboFares']/following-sibling::*[1]/*/button"));
			//}
			/*else {
			clickOneWayBookButton(driver);
			}*/
			
			/*Check if RT Tabs are present, else refresh page*/	
			/*splRTTab = checkSpecialRTComboTabForInternational(driver);
			if (!splRTTab) {
				Reporter.log("No SplRT Tabs not found for attempt:"+(attempt+1)+".Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}*/
			/*Selecting the SplRT fare flight and click book*/	
			//flightFound = meSelectSplRoundTripFlightIntl(driver, "gds", "");
			
			/*if (!flightFound) {
				Reporter.log("SplRT fare not found for attempt:"+(attempt+1)+".Making another attempt with different sectors.",true);
				attempt++;
				continue;
			}*/
			
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				//System.out.println("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
						
			scrollToElement(driver, getObject("air_review_itinerary_continue"));
			safeClick(driver, getObject("air_review_itinerary_continue"));
			travellerDetails(driver, adults, children, infants, true, false, false);
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
	 	//afterMethod(driver, _result);
	 }
}
