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
import org.w3c.dom.Document;

import domainServices.AirCommonMethod;

public class AirDom_OW_Effective_Price_Check extends AirCommonMethod {

	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	boolean flowCorrect = false;
	String domain = "com";

	@BeforeClass
	public void startSelenium() throws Exception 
	{
		this.driver = getDriver(driver);
		if (driver == null) 
		{
			Reporter.log("Error in initial setup. Exiting without screenshot", true);
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider(name = "B2CAirOWLCC")
	public static Object[][] B2CAirOWLCCDomFullRefund() 
	{
		String[] origin = { "blr", "blr" };
		String[] destination = { "del", "maa" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "Direct", "1", "0", "0",
				"ctwallet", false } };
	}

	@BeforeMethod
	public void beforeMethod() 
	{
		timer();
	}
	
	@Test(groups = "Regression", dataProvider = "B2CAirOWLCC")
	public void airDom_OW_Effective_Price_Check_36452(String[] fromSet, String[] toSet, String app, String tripType,
			String flight_type, String flightPreference, String flightFilterType, String adults, String children, String infants,
			String paymentMethod, boolean insuranceRequired) throws Exception 
	{

		Reporter.log("Test case " + this.getClass() + " started",true);
		boolean flightCountFailure = true;
		int attempt = 0;

		do 
		{
			
			
			driver.get(baseUrl);
			
			//Signing In
			if (!checkIfSignedIn(driver)) 
			{
				CTWallet1(driver,"partial");
			}
			
			Reporter.log("Adding Money to wallet.", true);
			moneyToPratialWallet("PROMOTION", "CREDIT", "100");
						
			// Searching
			airCom_HomepageSearch_Oneway(driver, fromSet[attempt], toSet[attempt], "10", adults, children, infants,
					flightPreference, attempt);
			Reporter.log("Search URL is : " + driver.getCurrentUrl(), true);
			
			flightCountFailure = checkFlightsCount(driver);
			if (!flightCountFailure) 
			{
				Reporter.log("No results found for this search. Making another attempt with different sectors.",true);
				attempt++;
				moneyToPratialWallet("REDEEM", "DEBIT", "100");
				continue;
			}
			
			Reporter.log("Undoing Adding Money to wallet.", true);
			moneyToPratialWallet("REDEEM", "DEBIT", "100");
			
			//Verifying Effective Price Details
			verifyEffectivePrice_SRP(driver, true, tripType);
			attempt++;

		} 
		while (!flightCountFailure && attempt < 2);
		assertTrue("Search failed after 2 attempts", ((attempt < 3) && (flightCountFailure)));
		
	}

	@AfterClass(alwaysRun = true)
	public void closeSelenium() throws Exception
	{
		driver.close();
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception 
	{
		afterMethod(driver, _result);
	}
}
