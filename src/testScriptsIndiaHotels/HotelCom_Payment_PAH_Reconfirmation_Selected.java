// PAH scripts
//Script done by Punith.A

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.CHMM;

	public class HotelCom_Payment_PAH_Reconfirmation_Selected extends CHMM{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPAH")
  public void PayAtHotel_529(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {

	  	driver.manage().deleteAllCookies(); 
	  	driver.get(CHMM_URL(driver, "com"));	
	  	CHMM_SignIN(driver, "");
	  	CHMM_PAH_Cnfrm_ReCnfrm_Change(driver, "Reconfirm");

	  	driver.manage().deleteAllCookies(); 
	  	hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAHConfirm"), 60, "");
	  	hotelCom_AddCookie(driver);
	  	hotelCom_ItineraryPage(driver, "PAH");
	  	hotelCom_TravelerPage_PAH(driver);  
		String TripID =hotelCom_PaymentPage_PAH(driver, "PAH", Logger_Msg, Booking_Confirmation_Message );
	  // String TripID = hotelCom_PAH_SinglePage(driver, "", Logger_Msg, "Reservation confirmed");

		driver.manage().deleteAllCookies();
		driver.get(CHMM_URL(driver, "com"));	
		CHMM_SignIN(driver, "");
		CHMM_Ledger_Trip_Search_Status_Verification(driver, TripID, "Reconfirmed");
  }

  @BeforeClass
  public void setUp() throws Exception {	 
	  driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
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