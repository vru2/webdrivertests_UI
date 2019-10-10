package testScriptsHQOthers;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.AirCommonMethod;
import domainServices.HQ;

public class HQRefundCalculationRequestQueue extends AirCommonMethod {


	 
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
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

	@Test(dataProvider = "B2CAirOWLCC")
	public void air_Intl_OW_IndiGo_AddBaggage_Booking_216(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

			
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		boolean warningFound = false;
				
		//do {
			driver.get(baseUrl);
			tripId = getAPITripId(domain, "ctest", "email_id", "user_id");
			driver.get(getBaseUrl(domain) + "/hq");
			signInHQ(driver);
			confirmTripLoad(driver, tripId, domain);
			String status;
			status = getBookingStatus(driver);
			refundCalculationRequestQueueVerification(driver,tripId);
						
			
		} 
		
		

	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = {"del","del","del"};
		String[] destination = {"sin","bkk","dxb"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "IndiGo", "", "1", "0", "0",
				"credit card", false} };
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
