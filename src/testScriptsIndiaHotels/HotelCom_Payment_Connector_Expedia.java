// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

public class HotelCom_Payment_Connector_Expedia extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID = null;
	
	@Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComPattaya")
	public void HotelCom_Booking_ExpediaCon(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		   driver.manage().deleteAllCookies();
	       driver.get(baseUrl);
	       SRP_URL_Debug_MultiRoom_Nights(driver, "com", "Pattaya", "", "TH","", 100);
	       hotelCom_SRP(driver, "","Supp_Expedia");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_LoginPage(driver, "SignIN", "");
		   hotelCom_TravelerPage(driver);
		   TripID =  hotelCom_PaymentPage(driver, "CREDITCARD", "Expedia Booking : ", Booking_Confirmation_Message);
		   if(MakePaymentOnlyInQA2){
			   driver.get(baseUrl+"/account/trips/"+TripID);
			   elementVisible(driver, By.xpath("//td[3]"), 20);
			   if(!getText(driver, By.xpath("//td[3]")).contains("1234")){
				   Reporter.log("Expedia is not having test credentials : "+getText(driver, By.xpath("//td[3]")));
				   Assert.assertTrue(false);
			   }
			   hotelCom_Account_Cancellation(driver, TripID,"");
			   driver.get(baseUrl+"/account/trips/"+TripID);
			   if(!elementVisible(driver, By.cssSelector("span.status.negative"), 20)){
				   Reporter.log("Expedia booking is not Cancelled : "+getText(driver, By.xpath("//td[4]/span")));
				   Assert.assertTrue(false);
			   }else Reporter.log("Expedia  credentials : "+getText(driver, By.xpath("//td[3]")));
			   hotelCom_Open_TripID_HQ(driver, TripID);
			   elementVisible(driver, By.xpath("//tr[3]/td[2]"), 10);
			   if(!getText(driver, By.xpath("//tr[3]/td[2]")).contains("Expedia")){
				   Reporter.log("Its not a expedia booking : "+getText(driver, By.xpath("//tr[3]/td[2]")));
				   Assert.assertTrue(false);		
			   }
			}
	}
	 
	@BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
	  }

	@AfterMethod (alwaysRun = true)
	 public void takeScreenshot(ITestResult _result) throws Exception{
		 screenshot(_result, driver);
	 }

	  @AfterClass
	  public void tearDown() throws Exception {
		  browserClose(driver);
	  }

}
