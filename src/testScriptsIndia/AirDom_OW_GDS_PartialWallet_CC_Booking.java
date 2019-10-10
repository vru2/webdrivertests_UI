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

public class AirDom_OW_GDS_PartialWallet_CC_Booking extends AirCommonMethod{

	
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
	public void airDom_OW_GDS_PartialWallet_CC_Booking_182(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

			
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		DefaultHttpClient clientSearch = null;
		
				
		do {

			clientSearch = new DefaultHttpClient();
			//String hash=calculateHash("SHA-256","5152532|Bangalore-chennai|Q1612160133|PROMOTION|CREDIT|10|03-DEC-2019|2483|cl3@rTri9");
			String hash=calculateHash("SHA-256","5152632|Bangalore-chennai|Q1612160133|PROMOTION|CREDIT|10|03-DEC-2019|2483|cl3@rTri9");
			//System.out.println("--"+hash);
			
			String postString="{   \"trip-details\": \"Bangalore-chennai\",   \"trip-ref\": \"Q1612160133\",   \"event\": \"PROMOTION\",   \"txn-type\": \"CREDIT\",   \"amount\": 10,   \"expiry-date\": \"03-DEC-2019\",   \"caller-id\": \"2483\" }";
			HttpPost WalletCall = new HttpPost(new URI("http://172.17.12.145:9080/payment/service/ctwallets/5152632/transactions"));
			//System.out.println(postString);
			StringEntity params = new StringEntity(postString);
			WalletCall.setEntity(params);

			WalletCall.setHeader("Content-Type","application/json");
			WalletCall.setHeader("checksum",hash);
			//itinenaryCall.setHeader("ct-auth","5BxMuOSis0JljdTWtFUBtPEJeisWc7ORPQuftjv%2Fq3vrwDTmofe0mqDDw%2BGyTMRIJTjekiio%2F%2B4f4RNTGIksHFmWekpOKj4dW23a5g9LnAAeyGy2XhRUCXamWNyuNabJJBy8gSQzoZuRm4LCKApNAi87mCSPQ%2FNDPUJD%2B8uLEYXQFzg3Egqd%2Bwntpi2g1Bx8");
			HttpResponse itinenaryResponse = clientSearch.execute(WalletCall);
			HttpEntity entityIti = itinenaryResponse.getEntity();
			
			Document docIti =null;
			BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
			String str12 ="";
			StringBuffer sb12 =new StringBuffer();
			while((str12=br12.readLine())!=null){
				sb12.append(str12);
				
			}
			//System.out.println(sb12);
			JSONObject WalletDetails = new JSONObject(sb12.toString());

			//System.out.println("WalletDetails="+WalletDetails);
						//System.out.println(itinenaryId.getString("itinerary_id"));
			
			
			
		
			
			
			
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				CTWallet1(driver,"partial");
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					common.value("gdsairline"),attempt);
			/*airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					"IndiGo",attempt);*/
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
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
			/*JSONObject gv = giftVoucherCreation(15000);

			// insuranceBlock(driver, insuranceRequired);
			applyGiftVoucher(driver, gv.get("gvNumber").toString(), gv.get("gvPin").toString());*/
			insuranceBlock(driver, insuranceRequired);			
						
			
				
				
			travellerDetails(driver, adults, children, infants, false, false, false);
				
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			//PaymentRetry(driver, "wallet");
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
			bookingPassed = checkTripID(driver);
			
		} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
	
	}
	
	
		@DataProvider(name = "B2CAirOWLCC")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = { "bom","del","blr"};
			String[] destination = {"del","bom","bom"};
			return new Object[][] { { origin, destination, "Flights", "OneWay", "", "IndiGo", "Direct", "1", "0", "0",
					"credit card", false, "Full Refund" } };
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
