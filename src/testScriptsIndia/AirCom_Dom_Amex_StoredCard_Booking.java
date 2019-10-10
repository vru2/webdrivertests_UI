package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;
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
/*
TestCase Description: Add a meal to indigo booking with 1 pax 
*/

public class AirCom_Dom_Amex_StoredCard_Booking extends AirCommonMethod { 
	
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";
	boolean warningFound = false;

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

		String[] origin = { "bom","BLR"};
		String[] destination = {"del","BOM"};

		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "Vistara", "Direct", "2", "0", "0",
				"storedcardamex", false} };
	}
	
	@Test(dataProvider = "B2CAirOWLCC")
	public void Air_Dom_amexStoredCard_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

			
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		/*HttpHost host = new HttpHost("172.16.56.111",4444); 
		DefaultHttpClient client = new DefaultHttpClient(); 
		URL testSessionApi = new URL("http://172.16.56.111:4444/grid/api/testsession?session=" +  ((RemoteWebDriver) driver).getSessionId()); 
		BasicHttpEntityEnclosingRequest r = new 
		BasicHttpEntityEnclosingRequest("POST", testSessionApi.toExternalForm()); 
		HttpResponse response  = client.execute(host,r);
		StringBuffer sb = new StringBuffer();
		String str="";
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while((str=br.readLine())!=null){
			//System.out.println(str);
			   sb.append(str);
		}
		////System.out.println("reger " + sb.toString());
		
		JSONObject jsonObject = XML.toJSONObject(sb.toString());
		Reporter.log(jsonObject.toString());
		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));   
		String proxyID = object.get("proxyId").toString();
		//System.out.println(proxyID.split("//")[1].split(":")[0]);*/

		
				
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				b2cStoredCard(driver);
			}
			
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					common.value("gdsairline"),attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			//System.out.println();
			
			flightCountFailure = checkFlightsCount1(driver);
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++; 
				continue;
			}
		
			warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
			if (warningFound) {
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
			
			insuranceBlock(driver, insuranceRequired);
			
			travellerDetails(driver, adults, children, infants, false, false, false);
			
			Boolean reachedPaymentStep = airconditionWatcher(driver);
			if (reachedPaymentStep) {
				if ((common.value("makePayment").equals("true"))) {
					paymentDone = b2cPayment(driver, paymentMethod, 0);
					boolean error = false;
					if (paymentDone == true)
						error = recheckAirlinePrice(driver, "testFlag");//workaround
					else if (paymentDone == false) {
						attempt++;
						Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
						continue;
					}
					if (error) {
						attempt++;
						continue;
					}
				} else {
					bookingPassed = true;
					break;
				}
			} else {
				attempt++;
				Reporter.log("Flight full error popped up. Re starting book process. Attempt number: " + attempt);
				continue;
			}
			attempt++;
			//bookingPassed = checkBookingStatus(driver);
			bookingPassed = checkTripID(driver);
			/*if (reachedPaymentStep) {
				paymentDone = b2cPayment(driver, paymentMethod,3);
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
			bookingPassed = checkBookingStatus(driver);
			*/
			
		} while (!bookingPassed && attempt < 2);
		assertTrue("Booking failed after 3 attempts", ((attempt < 2) && (bookingPassed)));
		
	}
	
	
	@AfterClass
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














