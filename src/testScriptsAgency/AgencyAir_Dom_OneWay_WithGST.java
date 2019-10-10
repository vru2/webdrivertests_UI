package testScriptsAgency;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

public class AgencyAir_Dom_OneWay_WithGST extends Agency{  
			
	public RemoteWebDriver driver;

			@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDom")	  
			public void AgencyComDom_OW_WithGST(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
				driver.get(Agency_Url());
				  agency_SignIn(driver);
				  agencyAir_Oneway_Search(driver, FromCity, ToCity, From_Date, Adults, Childrens, Infants, Pref_Airline);
				  agencyAir_SRP_Domestic_Oneway(driver);
				  agencyAir_ItineraryPage(driver);
				  agencyAir_TravellerPage(driver, Adults, Childrens, Infants,"true","true");
				  String TripID=agencyAir_Paymentpage(driver, Payment_Type, "", Trip_Logger_Msg, Booking_Confirmation_Message);
				// System.out.println(TripID);
				 getGstDataFromTripXML(driver,TripID,false);
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

