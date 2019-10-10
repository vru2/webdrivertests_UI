package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

public class HotelCom_DetailsPage_ModifySearch extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	 @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComDPModifySearch")
	  public void HotelCom_DP_ModifySearch(String City,  String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		   driver.get(baseUrl);	  
		   hotelCom_DetailsPage_URL_Open1(driver, "com", "708801", 20);
		   CheckIn_Date = getDate( "dd");
		   CheckIn_Date = CheckIn_Date.substring(1);
		   CheckIn_Date = "1"+CheckIn_Date;
		   int DateInt = Integer.parseInt(CheckIn_Date);
		   DateInt = DateInt+1;
		   CheckOut_Date = Integer.toString(DateInt);
		   selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
		   selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		   safeSelect(driver, By.xpath("//fieldset[1]/select"), "More travellers...");
			hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
			String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
			String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
			Reporter.log("Searched Hotel for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
		   safeClick(driver, By.xpath("//*[@value='Modify search']"));
		   if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 5)) {
			   Reporter.log("Rooms Are unavailable for the selected dates");
				Assert.assertTrue(false);
			}
			elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 5);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
			Thread.sleep(500);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));	
		/*	hotelCom_ItineraryPage(driver, "");
			hotelCom_LoginPage(driver, "SignIN", "");
			hotelCom_TravelerPage(driver);
			hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
			hotelCom_ConfirmationPageValidation(driver, "", "", Booking_Confirmation_Message);*/
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




