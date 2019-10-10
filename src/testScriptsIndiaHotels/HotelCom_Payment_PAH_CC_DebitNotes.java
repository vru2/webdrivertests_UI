// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dataServices.IndiaHotelDataProvider;
import domainServices.CHMM;

	public class HotelCom_Payment_PAH_CC_DebitNotes extends CHMM{
	public RemoteWebDriver driver;
	private String TripID =null;

  @Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPAHNew")
  public void PayAtHotelDebitNotes_523(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	   driver.manage().deleteAllCookies(); 
	   hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_PAHCC"), 1, "");//715940
	   hotelCom_ItineraryPage(driver, "PAHCC");
	   hotelCom_LoginPage(driver, "SignIN","");
	   hotelCom_TravelerPage_PAH(driver);  
	   TripID =  hotelCom_PaymentPage_PAH(driver, "PAHCC", "PAHCC debit notes: ", Booking_Confirmation_Message ); // invalid & Valid
	   if(MakePaymentOnlyInQA2){
	   hotelCom_ConfirmationPageValidation(driver, "PAHCC", "", "Reservation confirmed");
	   hotelCom_HQ_Status(driver, TripID, "Confirmed", "");
	   hotelCom_HQ_PAHValidation(driver);
	   hotelCom_Account_Status(driver, TripID, "CONFIRMED", "");
	   hotelCom_Account_Cancellation(driver, TripID,"PAHCC");
	   hotelCom_PAH_Noshow_ClaimRetention(driver, TripID);
	   hotelCom_HQ_Status(driver, TripID, "CC penalty collected", "");	   
	  /* hotelCom_Chmm_VerifyDebitNotes(driver, TripID);
	   hotelCom_HQ_Status(driver, TripID, "CC penalty collected", "");*/
	   
		//hotelCom_Open_TripID_HQ(driver, TripID);
		/*		elementVisible(driver, By.linkText("Penalty"), 5);
		safeClick(driver, By.linkText("Penalty"));
		elementVisible(driver, By.xpath("//td[7]"), 10);
		String HQpenalty  = getText(driver, By.xpath("//td[7]"));
		Reporter.log("HQPenalty: "+HQpenalty);
		*/
	  // validateXML(Hotel_Name, StartTag, EndTag, TagValue);
	   Reporter.log("https://qa2.cleartrip.com/hq/trips/"+TripID+"/xml");
	   Reporter.log("<sup-currency-markup>");
	   
	   }
	   }

  @BeforeClass
  public void setUp() throws Exception {	 
	  driver=(RemoteWebDriver) getDriver(driver);
	  String baseUrl = getBaseUrl( "com");
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