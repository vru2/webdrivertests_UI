package testScriptsIndia;

import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

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

public class AirDom_OW_Duration_Check_for_PROD_BETA extends AirCommonMethod{


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
		String[] origin = { "del"};
		String[] destination = {"bom"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "Direct", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}

	@Test(dataProvider = "B2CAirOWLCC")
	public void Air_OW_Coupon_Booking(String[] fromSet, String[] toSet, String app, String tripType, String flight_type,
			String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired, String refundMethod) throws Exception {

			
		boolean bookingPassed = false;
		boolean flightCountFailure = true;
		boolean paymentDone = false;
		int attempt = 0;
		HashMap<String,String> hp=new HashMap<String,String>();
				
		
			durationCheck(driver,"beta",hp,fromSet[attempt],toSet[attempt],attempt);
			durationCheck(driver,"www",hp,fromSet[attempt],toSet[attempt],attempt);
			String flightnumber=hp.get("betaflightdetails").split("-")[1];
			Reporter.log("flightnumber="+flightnumber);
			//System.out.println("flightnumber="+flightnumber);
			Reporter.log("beta duration="+hp.get("betaduration"));
			//System.out.println("beta duration="+hp.get("betaduration"));
			Reporter.log("prod duration="+hp.get("prodduration"));
			//System.out.println("prod duration="+hp.get("prodduration"));
			Reporter.log("beta flight details="+hp.get("betaflightdetails"));
			//System.out.println("beta flight details="+hp.get("betaflightdetails"));
			Reporter.log("prod flight details="+hp.get("prodflightdetails"));
			//System.out.println("prod flight details="+hp.get("prodflightdetails"));
			Assert.assertEquals(hp.get("betaduration"),hp.get("prodduration"),"mismatch in duration ");
			Assert.assertEquals(hp.get("betaflightdetails"),hp.get("prodflightdetails"),"mismatch in duration ");
			//
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
