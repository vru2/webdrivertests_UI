// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.CorporateHotels;

	public class HotelCorp_Connector_OYO extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test ( dataProviderClass = CorporateDataProvider.class,dataProvider="HotelCorp")
	public void CorpHotel_OYO_Connector(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  String HotelB2B_OYO = null;
		   if(ProductionUrl) {
			   HotelB2B_OYO=  dataFile.value("HotelB2B_OYO_Prod");
		   }
		   else {
			   HotelB2B_OYO =  dataFile.value("HotelB2B_OYO");
		   }
	  driver.get(Corp_Url());
	  corp_SignIn(driver);
	  corpHotel_HomePageSearch(driver, "Mumbai", CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	  corpHotel_SRP(driver, HotelB2B_OYO,"");
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  String TripID = corpHotel_PaymentPage(driver, "", "" , "Corp.com OYO Connector  : ", Booking_Confirmation_Message);
	  if(MakePaymentOnlyInQA2){
			driver.manage().deleteAllCookies();
			hotelCom_Open_TripID_HQ(driver, TripID);
			safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
			safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
			safeClick(driver, By.id("signInButton"));
			Thread.sleep(5000);
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
				if(!getText(driver, By.xpath("//tr[3]/td[3]")).contains("1234")){
				   Reporter.log("Expedia is not having test credentials : "+getText(driver, By.xpath("//td[3]")));
				  // Assert.assertTrue(false);
				}
				if(!getText(driver, By.xpath("//tr[3]/td[2]")).contains("OYORooms")){
					Reporter.log("Its not a oyo booking : "+getText(driver, By.xpath("//tr[3]/td[2]")));
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