package testScriptsCorpAE;

import java.util.HashMap;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Dom_MC_SRP_Production extends Corporate {
	
	public RemoteWebDriver driver = null;
	public LinkedList<HashMap<String, String>> assertionLinkedList = new LinkedList<HashMap<String, String>>();
	String tripId = null;
	String baseUrl = null;
	String domain ="com";
	
	@DataProvider(name = "DomMuiticityAE")
	public static Object[][] CorpDomMuiticity() {
		String origin[] = { "MAA", "BLR", "DEL"};
		String destination[] = { "BLR", "DEL", "BOM"};
		return new Object[][] { { 2, origin, destination, "1", "1", "0", "MULTICITY", "", "", "gds", "ROUND", "Auto Refund", false , "CC", "Air Agency Dom MultiCity TripID : ", "Great, your booking went through successfully." } };
	}	
	
	@Test (dataProvider="DomMuiticityAE")
	public void AirCorpAE_DOM_MC_MultiPAX(int numberOfSectors, String[] fromSet, String[] toSet, String Adults, String Childrens, String Infants,
			String flight_mode, String flightPreference, String paymentMethod, String flight_type, String trip_type, String refundMethod,
			boolean insuranceRequired, String PaymentType, String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
			String dateSet[] = { "11", "14", "15" };		
			driver.get(Corp_AE_Url());
			corp_SignIn(driver);			
			airCorp_MC_Search(driver, numberOfSectors, fromSet, toSet, dateSet, Adults, Childrens, Infants, flightPreference);

			Thread.sleep(10000);
			elementPresent_Time(driver, getObject("AirCorp_SRP_Dom_MC_book_button"), 60);
			refreshPage(driver);
			corpNoResultsFound(driver);
		  elementVisible(driver, getObject("SRP_air_flightcount"), 20);
		  elementPresent_Time(driver, getObject("AirCorp_SRP_Dom_MC_book_button"), 20);
		  elementPresent(driver, getObject("AirCorp_SRP_Dom_MC_book_button"));
		  Reporter.log("Dom MC results are displayed");
	}

	@BeforeClass
	public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	}

	
	 @AfterMethod (alwaysRun = true)
	  public void takeScreenshot(ITestResult _result) throws Exception{
	   screenshot(_result, driver);
	  }
	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
		  browserClose(driver);  
	  }
}