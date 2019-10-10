// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsAgency;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.AgencyHotels;

	public class AgencyHotel_Connector_OYO extends AgencyHotels{
	public RemoteWebDriver driver;
	String TripID = null;
	
  @Test
  public void agencyHotel_Cancel_OYO() throws Exception {
	  String HotelB2B_OYO = null;
	   if(ProductionUrl) {
		   HotelB2B_OYO=  dataFile.value("HotelB2B_OYO_Prod");
	   }
	   else {
		   HotelB2B_OYO =  dataFile.value("HotelB2B_OYO");
	   }
		agencyHotel_SrpUrl(driver, "Mumbai", "Maharashtra", "IN", "2", 24, 25);
	  	agencyHotel_SRP(driver, HotelB2B_OYO,"");
		agencyHotel_Itinerarypage(driver);
		agencyHotel_Travellerpage(driver);
		TripID = agencyHotel_Paymentpage(driver, "", "", "Agency Oyo  :", "Great, your booking went through successfully.");
		if(MakePaymentOnlyInQA2){
		driver.manage().deleteAllCookies();
		hotelCom_Open_TripID_HQ(driver, TripID);
		safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
		safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
		safeClick(driver, By.id("signInButton"));
		Thread.sleep(2000);
		   safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Link"));
			safeType(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), "Test Booking");
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Button"));
		    elementNotPresent_Time(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), 20);  		    
			elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Status"), 20);
			String Cancel_Status = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Status"));
			if(!Cancel_Status.contains("Cancelled")){
				Reporter.log("Trip Status after cancellation is : "+Cancel_Status);
				Assert.assertTrue(false);
			}			
			elementVisible(driver, By.xpath("//td[3]"), 20);
			if(!getText(driver, By.xpath("//tr[3]/td[2]")).contains("OYORooms")){
				Reporter.log("Its not a OYO booking : "+getText(driver, By.xpath("//tr[3]/td[2]")));
				Assert.assertTrue(false);		
		   }		    		
		}
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