// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.AgencyHotels;

	public class AgencyHotel_Connector_Prod_Trust extends AgencyHotels{
	public RemoteWebDriver driver;
	String TripID = null;
	
  @Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="HotelAgency", groups="Regression")
  public void agencyHotel_TrustProd(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, 
			String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2, 
			String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  String HotelB2B_Trust = null;
	   if(ProductionUrl) {
		   HotelB2B_Trust=  dataFile.value("HotelB2B_Trust_Prod");
	   }
	   else {
		   HotelB2B_Trust =  dataFile.value("HotelB2B_Trust");
	   }
	    agencyHotel_SrpUrl_HotelName(driver, "Mumbai", "Maharashtra", "IN", "1", 50, 51 , HotelB2B_Trust);		
	  	//agencyHotel_HomepageSearch(driver, "Mumbai", CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	  	agencyHotel_SRP(driver, "","");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		agencyHotel_Paymentpage(driver, "NETBANKINGPROD", "", "Agency Trust Connector NB  :", Booking_Confirmation_Message);		
	}

  @BeforeClass
  public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
  }
  
  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
		browserClose(driver);    
  }
}