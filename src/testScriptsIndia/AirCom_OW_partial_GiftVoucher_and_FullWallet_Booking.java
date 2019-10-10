package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import domainServices.AirCommonMethod;

public class AirCom_OW_partial_GiftVoucher_and_FullWallet_Booking extends AirCommonMethod{

	
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
	
	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "blr","del"};
		String[] destination = { "MAA","BOM"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "", "1", "0", "0",
				"ctwallet", false, "Full Refund" } };
	} 
	
	@Test(dataProvider = "B2CAirOWLCC")
	public void Air_Dom_OW_Full_GV_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

		DefaultHttpClient clientSearch = null;
		
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		
		int attempt = 0;

		

		
		do {

			clientSearch = new DefaultHttpClient();
			
			//Create GV
			
			String hash=calculateHash("SHA-256","INR|1000|ravikumar.valmiki@cleartrip.com|1|cleartrip");
			
			//System.out.println("--"+hash);
			
			String postString="{ \"currency\":\"INR\", \"amount\":1000, \"userEmail\":\"ravikumar.valmiki@cleartrip.com\", \"paymentId\":1 }";
			HttpPost itinenaryCall = new HttpPost(new URI("https://apiqa.cleartrip.com/payment/gv/create"));
			System.out.println(postString);
			StringEntity params = new StringEntity(postString);
			itinenaryCall.setEntity(params);

			itinenaryCall.setHeader("Content-Type","application/json");
			itinenaryCall.setHeader("checksum",hash);
			//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
			HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
			HttpEntity entityIti = itinenaryResponse.getEntity();
			
			Document docIti =null;
			BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
			String str12 ="";
			StringBuffer sb12 =new StringBuffer();
			while((str12=br12.readLine())!=null){
				sb12.append(str12);
			}

			JSONObject gvJSON = new JSONObject(sb12.toString());

			System.out.println("giftvoucher details="+gvJSON);
			String gvnumber=gvJSON.get("gvPin").toString();
			String gvpin=gvJSON.get("gvNumber").toString();
		
						//System.out.println(itinenaryId.getString("itinerary_id"));
			
			
			
		
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				CTWallet(driver);
			}
			// safeClick(driver, getObject("AirSideAppButtonXPath"));
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,flightPreference,attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			warningFound = selectingStop(flightFilterType, driver, 0);
			if (warningFound) {
				attempt++;
				continue;
			}
			if (!flightPreference.equals("")) {									
				warningFound = filterFlightsByPreferedAirline1(driver, flightPreference, 0);
				if (warningFound) {
					attempt++;
					continue;
				}
			}
			
			airCom_SRP_Oneway(driver);
			
			/*WebElement we = pickFirstFlight(driver);
			if (we != null) {
				bookButtonDom(we);
			} else {
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
				attempt++;
				continue;
			}*/
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			assertionLinkedList = captureItineraryData(driver);
			//insuranceBlock(driver, insuranceRequired);
			//giftVoucherPartial(driver, insuranceRequired,gvnumber,gvpin);
			
			travellerDetails(driver, adults, children, infants, false, false, false);
			
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				paymentDone = b2cPayment(driver, paymentMethod, 1);
				//System.out.println("checking paymentdone="+paymentDone);
				if (paymentDone == true)
					recheckAirlinePrice(driver);
				else if (!(common.value("makePayment").equals("true")))
					break;
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
			bookingPassed = checkTripID(driver);
			if (bookingPassed && paymentMethod.equals("giftvoucher")){
				//System.out.println(driver.getCurrentUrl());
				String GF = driver.findElement(getObject("AirCom_GiftVoucher_ConfirmationPageTxt")).getText();
				//System.out.println(GF);
				assertTrue("GiftVoucher Booking is Not Done", GF.contains("gift card"));
			}
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
	
		
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
