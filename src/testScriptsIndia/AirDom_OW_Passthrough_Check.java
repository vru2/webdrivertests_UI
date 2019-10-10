package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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

import domainServices.AirCommonMethod;

public class AirDom_OW_Passthrough_Check extends AirCommonMethod{
	 
	
	
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

	@DataProvider(name = "B2CAirOWGDS")
	public static Object[][] B2CAirOWGDS() {
		String[] origin = { "del","del","blr"};
		String[] destination = {"bom","bom","bom"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "Vistara", "", "1", "0", "0",
				"credit card", false} };
	}
	
	@Test(dataProvider = "B2CAirOWGDS")
	public void airDom_OW_Passthrough_Check_184(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception {

			
		boolean bookingPassed = false;
		boolean warningFound = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;

		
				
		do {
			driver.get(baseUrl);
			if (!checkIfSignedIn(driver)) {
				airCom_HomepageSignInForHQScripts(driver, domain);
			}
			
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "15", adults, children, infants,
					"Vistara",attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl());
			
			flightCountFailure = waitForElement(driver,90,getObject("AirCom_SRP_Oneway_BookButton"));
			if (!flightCountFailure) {
				Reporter.log("No results found for this search. Making another attempt with different sectors.");
				//System.out.println("No results found for this search. Making another attempt with different sectors.");
				attempt++;
				continue;
			}
			filterFlightsByPreferedAirline1(driver,"Vistara", 0);
		
			/*warningFound = filterFlightsByLCCOrGDS1(driver, flight_type, 0);
			if (warningFound) {
				attempt++;
				continue;
			}*/
			
			//airCom_SRP_Oneway(driver);
			
			WebElement we = pickFirstFlight(driver);
			if (we != null) {
				bookButtonDom(we);
			} else {
				Reporter.log("No flight satisfies the required criteria among the loaded results. Trying with new combination.");
				attempt++;
				continue;
			}
		
			boolean failAfterBookButton = checkIfFailAfterBookButton(driver);
			if (failAfterBookButton) {
				Reporter.log("Redirected back to SRP after clicking on book button. Making another attempt");
				attempt++;
				continue;
			}
			cheaperRateBlock(driver);
			assertionLinkedList = captureItineraryData(driver);
			
			insuranceBlock(driver, insuranceRequired);
			
			travellerDetails(driver, adults, children, infants, false, false, false);
		
			airconditionWatcher(driver);
			
		
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
		bookingPassed = checkTripID(driver);
		/*String TripID = getText(driver, getObject("AirCom_Confirmation_TripID"));
		//driver.get("http://10.10.21.107:9080/trips/"+TripID);
		DefaultHttpClient client = new DefaultHttpClient();
	//	System.out.println("http://10.10.21.107:9080/trips/"+TripID);
		HttpGet get = new HttpGet(new URI("http://10.10.21.107:9080/trips/"+TripID));
		//HttpGet get = new HttpGet(new URI("http://10.10.21.107:9080/trips/Q1702080733"));
		HttpResponse response = client.execute(get);
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer sb = new StringBuffer();
		String str="";
		while((str=br.readLine())!=null){
			sb.append(str);
		}
		System.out.println("---------"+sb);
		String sb1=sb.toString();
		 String [ ] getFOP = sb1.split("<fop>");
         String [ ] FOP = getFOP[1].split("</fop>");
        System.out.println("<FOP>               = " +FOP[0]);
         Reporter.log("<FOP>               = " + FOP[0]);
         String FOP1=FOP[0];
         Assert.assertEquals(true,FOP1.contains("CC-PBN/CTAMEX"),"pass through failed");*/
	} while (!bookingPassed && attempt < 3);
		assertTrue("Booking failed after 3 attempts", ((attempt < 4) && (bookingPassed)));
		String TripID = getText(driver, getObject("AirCom_Confirmation_TripID"));
		//driver.get("http://10.10.21.107:9080/trips/"+TripID);
		DefaultHttpClient client = new DefaultHttpClient();
	//	System.out.println("http://10.10.21.107:9080/trips/"+TripID);
		HttpGet get = new HttpGet(new URI("http://172.17.12.187:9080/trips/"+TripID));
		//HttpGet get = new HttpGet(new URI("http://10.10.21.107:9080/trips/Q1702080733"));
		HttpResponse response = client.execute(get);
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer sb = new StringBuffer();
		String str="";
		while((str=br.readLine())!=null){
			sb.append(str);
		}
		//
		//System.out.println("---------"+sb);
		String sb1=sb.toString();
		 String [ ] getFOP = sb1.split("<fop>");
         String [ ] FOP = getFOP[1].split("</fop>");
        System.out.println("<FOP>               = " +FOP[0]);
         Reporter.log("<FOP>               = " + FOP[0]);
         
         String FOP1=FOP[0];
         Assert.assertEquals(true,FOP1.contains("CC-PBN/CLEARTRIP"),"pass through failed");
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
