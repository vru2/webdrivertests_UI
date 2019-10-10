// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - July, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All right reserved.
package testScriptsBrowserHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import domainServices.IndiaHotels;

	public class HotelCom_Common extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl; 
	
	public void hotelCom_Step2SignIn(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		//   driver.manage().deleteAllCookies();
		 
		   
		   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		   hotelCom_SRP(driver, Hotel_Name,"");
		   Thread.sleep(20000);
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_LoginPage(driver, "SignIN", "");
		   hotelCom_TravelerPage(driver);
		   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message); 
	}

	public void hotelCom_HomepageSignInIE(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		   driver.manage().deleteAllCookies();
		   hotelCom_HomepageSignIn_IE(driver);
		   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		   hotelCom_SRP(driver, Hotel_Name,"");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_TravelerPage(driver);
		   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);  
	}
	
	public void hotelCom_HomepageSignIn(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		driver.manage().deleteAllCookies();   
		hotelCom_HomepageSignIn(driver, "");
		   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		   hotelCom_SRP(driver, Hotel_Name,"");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_TravelerPage(driver);
		   hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message); 
	}
	
	public String hotelCom_Coupon(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		   hotelCom_SRP(driver, Hotel_Name,"");
		   Thread.sleep(20000);
		   hotelCom_ItineraryPage(driver, "COUPON");
		   hotelCom_LoginPage(driver, "SignIN","");
		   hotelCom_TravelerPage(driver);
		   String TripID = hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
		   return TripID; 
	}
	

	public String hotelCom_Unsigned(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		   hotelCom_HomepageSearch(driver, City, CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
		   hotelCom_SRP(driver, Hotel_Name,"");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_LoginPage(driver, "Unsigned","");
		   hotelCom_TravelerPage(driver);
		   String TripID = hotelCom_PaymentPage(driver, Payment_Type, Logger_Msg, Booking_Confirmation_Message);
		   return TripID; 
	}
}