	// Framework - Cleartrip Automation
// Author - Kiran Kumar

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

	public class HotelCom_AmexTravel_HQ_Cancellation extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl2;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon")
  public void HotelCom_AmexTravel_MC_Validation(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl2);	  
	   hotelCom_HomepageSearch(driver, "Bangalore", CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	   hotelCom_SRP_Misc(driver, "", "AMEX", "");
	   //hotelCom_ItineraryPage(driver, "COUPONAMEX");
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_LoginPage(driver, "","");
	   hotelCom_TravelerPage(driver);	  
	   logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 20, "Payment Step has not loaded :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 5)) {
			} else {
			Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
		}
		waitElement(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 10);	   
		Reporter.log("Payment Type is : CC");
		waitElement(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 10);
		safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
		safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
		safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
		safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
		safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
		safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
		smartClick(driver, By.id("native_currency"));
		Thread.sleep(2000);
		
		safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));	
		
		elementPresent_log(driver, By.id("paymentBlockError"), "Amex Card Error message", 20);
		elementPresent_log(driver, By.xpath("//div[@id='CCTab']/dl/dd/small[3]"), "Enter Valid Amex card ", 2);
		
		textAssert(driver, By.id("paymentBlockError"), "Sorry, but we can't continue until you fix everything that's marked in RED");
		textAssert(driver, By.xpath("//div[@id='CCTab']/dl/dd/small[3]"), "Please enter a valid Amex card.");
  	}
  
	 @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon", dependsOnMethods="HotelCom_AmexTravel_MC_Validation")
	 public void HotelCom_AmexTravel_AmexCard(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
				  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		
	   String TripID = hotelCom_PaymentPage(driver, "AMEX", "Amex Travel Site TripID : ", Booking_Confirmation_Message);
	   if(MakePaymentOnlyInQA2){
		   elementPresent_log(driver, By.cssSelector("a.amexLogo.fLeft"), "Amex Logo", 5);
		   elementPresent_log(driver, By.xpath("//img[@alt='American Express Travel']"), "American Express Travel text", 1);
		   elementPresent_log(driver, By.linkText("Copyright © 2017 American Express Company American Express Banking Corp"), "Copyright", 1);
		   elementPresent_log(driver, By.linkText("Privacy"), "Privacy link ", 1);
		   if(!textPresent(driver, "Need Help? Please call 1860 266 9902 (7:30 am to 9:30 pm)", 1)){
			   Reporter.log("Need Help? Please call 1860 266 9902 (7:30 am to 9:30 pm) message is not displayed");
			   Assert.assertTrue(false);
		   }
		 driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
	   
		//hotelCom_Open_TripID_HQ(driver, TripID);
		safeType(driver, By.id("email"), dataFile.value("HotelEmailID"));
		safeType(driver, By.id("password"), dataFile.value("HotelPassword"));
		safeClick(driver, By.id("signInButton"));
		Thread.sleep(5000);
		 driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);	
		textPresent(driver, "Itinerary", 50);	
		elementPresent_log(driver, By.cssSelector("#booking-source > h2"), "WL Booking Text", 5);
		elementPresent_log(driver, By.xpath("//div/div[2]/dl/dd"), "WL URL", 5);
	/*	String WLURL = getText(driver, By.xpath("//div/div[2]/dl/dd"));
		if(WLURL.contains("travel-amex.co.in")){
			Reporter.log("travel-amex.co.in URL text is not displayed : "+WLURL);
			Assert.assertTrue(false);
		}*/
		safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Link"));
		elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), 30);
		safeType(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), "Test Booking");
		safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Button"));
	    elementNotPresent_Time(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), 20);
		elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Status"), 30);
		String Cancel_Status = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Status"));
		if(!Cancel_Status.contains("Cancelled")){
			Reporter.log("Trip Status after cancellation is : "+Cancel_Status);
			Assert.assertEquals(true, false);
			}	
	   }
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl2 = getAmexTravelUrl();
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