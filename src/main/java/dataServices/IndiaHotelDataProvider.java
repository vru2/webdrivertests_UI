// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - May, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package dataServices;

import org.testng.annotations.DataProvider;
import commonServices.WrapperMethod;

public class IndiaHotelDataProvider   extends WrapperMethod{
	
	 @DataProvider
	 public static Object [ ][ ] HotelComPAX() {
	      return new Object [ ] [ ] { { "Bangalore", "14", "15", "2", "2", "2", "3", "1", "1", "0", "1", "1", "", "DEBITCARD", "Hotel DC payment TripID : ", "Your booking is done"},
	    		  					  { "Ooty",      "15", "16", "1", "2", "1", "1", "1", "1", "0", "1", "1", "", "WALLET",     "Hotel Wallet Payment TripID : ", "Your booking is done"},
	    		  					  { "Mumbai",    "23", "24", "1", "1", "1", "1", "1", "1", "0", "1", "1", "", "STOREDCARD", "Hotel Stored Card Payment TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComMultiDays() {
	      return new Object [ ] [ ] { { "New Delhi", "13", "16", "2", "1", "1", "1", "1", "1", "0", "1", "1", "", "CREDITCARD","Hotel Multidays TripID : ", "Your booking is done"}};
	 }
	 
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComTravelerOptions() {
	      return new Object [ ] [ ] { {  "Bangalore", "18", "19", "1 room, 1 adult", "", "", "", "", "0", "0", "0", "0", "", "CC", "Hotels Traveller Option 1 room 1 adult TripID : ", "Your booking is done"},
					  {  "Bangalore", "20", "21", "1 room, 2 adults", "", "", "", "", "0", "0", "0", "0", "", "CC", "Hotels Traveller Option 1 room 2 adults TripID : ", "Your booking is done"},
					  {  "New Delhi", "23", "24", "2 rooms, 4 adults", "", "", "", "", "0", "0", "0", "0", "", "CC", "Hotels Traveller Option 2 room 4 adults TripID : ", "Your booking is done"}};
	 }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComHomePageNameSearch() {
	      return new Object [ ] [ ] { { "Hotel Mahaveer, Bangalore", "11", "12", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotel Homepage Name Search TripID : ", "Your booking is done"}};
	  }
	  
	 @DataProvider
	 public static Object [ ][ ] HotelComNameSearch() {
	      return new Object [ ] [ ] { { "Bangalore", "12", "13", "1", "2", "2", "3", "2", "0", "0", "0", "0", "Hotel Raj Residency", "", "Hotel Srp Name Search TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComPAH() {
	      return new Object [ ] [ ] { { "Bangalore", "12", "13", "1", "2", "2", "3", "2", "0", "0", "0", "0", "Ample Inn", "PAH", "Hotel Pay @ Hotel TripID : ", "Reservation confirmed"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComPAHNew() {
	      return new Object [ ] [ ] { { "Haridwar", "12", "13", "1", "2", "2", "3", "2", "0", "0", "0", "0", "Hotel Alpana", "PAHNEW", " Pay @ Hotel with CC info TripID : ", "Reservation confirmed"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComPartPay() {
	      return new Object [ ] [ ] { { "Bangalore", "12", "13", "1", "2", "2", "3", "2", "0", "0", "0", "0", "Hotel Lucky Inn", "", "Hotel Partpay Hotel TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelProdConnector() {
	      return new Object [ ] [ ] { {  "Dubai", "18", "19", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Prod connector Booking " , "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelAirBNB() {
	      return new Object [ ] [ ] { {  "Dubai", "18", "19", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels AirBNB Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComCoupon() {
	      return new Object [ ] [ ] { {  "Pune", "19", "20", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComGoodies() {
	      return new Object [ ] [ ] { {  "Darjeeling", "West+Bengal", "IN", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "The Pinewood Hotel", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComMumbai() {
	      return new Object [ ] [ ] { {  "Mumbai", "Maharashtra", "IN", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComBangalore() {
	      return new Object [ ] [ ] { {  "Bangalore", "Karnataka", "IN", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComNewDelhi() {
	      return new Object [ ] [ ] { {  "New+Delhi", "Delhi", "IN", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }	 

	 @DataProvider
	 public static Object [ ][ ] HotelComPune() {
	      return new Object [ ] [ ] { {  "Pune", "Maharashtra", "IN", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComOoty() {
	      return new Object [ ] [ ] { {  "Ooty", "Tamil+Nadu", "IN", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }

	 @DataProvider
	 public static Object [ ][ ] HotelComChennai() {
	      return new Object [ ] [ ] { {  "Chennai", "Tamil+Nadu", "IN", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }

	 @DataProvider
	 public static Object [ ][ ] HotelComHyderabad() {
	      return new Object [ ] [ ] { {  "Hyderabad", "Andhra+Pradesh", "IN", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComDubai() {
	      return new Object [ ] [ ] { {  "Singapore", "", "SG", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComPattaya() {
	      return new Object [ ] [ ] { {  "Pattaya ", "", "TH", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider  //5footway.inn Project Bugis
	 public static Object [ ][ ] HotelComSingapore() {
	      return new Object [ ] [ ] { {  "Dubai", "", "SG", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "The Fairmont Dubai", "", "Hotels Booking Intl Retry TripID : ", "Your booking is done"}};
	  }

	 @DataProvider
	 public static Object [ ][ ] HotelComAreaSpecific() {
	      return new Object [ ] [ ] { {  "Thane, Mumbai", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels Area Specific Booking TripID : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	 public static Object [ ][ ] HotelComDeal() {
	      return new Object [ ] [ ] { {  "Bangalore", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Keys Hotel Hosur Road", "DEBITCARD", "Hotels Deal of the day Booking TripID : ", "Your booking is done"}};
	  }

	  @DataProvider
	  public static Object [ ][ ] HotelComGiftVoucher() {
	      return new Object [ ] [ ] { {  "Kolkata", "24", "25", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Shree Shyam Guest House", "GIFTVOUCHER", "Hotels Gift Voucher TripID : ", "Your booking is done"}};
	  } //Hotel Lav-Kush Deluxe
	 
	  @DataProvider
	  public static Object [ ][ ] HotelCom() {
	      return new Object [ ] [ ] { { "Bangalore", "22", "23", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotel TripID : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelComModalWindow_V2() {
	      return new Object [ ] [ ] { { "Bangalore", "22", "23", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Fortune Select JP Cosmos", "DEBITCARD", "Hotel TripID : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelComModalWindow_Old() {
	      return new Object [ ] [ ] { { "Dubai", "22", "23", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Eureka Hotel", "DEBITCARD", "Hotel TripID : ", "Your booking is done"}};
	  }	    
	  
	  @DataProvider
	  public static Object [ ][ ] HotelComHold() {
	      return new Object [ ] [ ] { { "Hyderabad", "21", "22", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Sunshine Grand", "HOLD", "Hotel Hold TripID : ", "Your booking is done"}};
	  }

	  @DataProvider
	  public static Object [ ][ ] HotelComAmex() {
	      return new Object [ ] [ ] { { "Bangalore", "23", "24", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "AMEX", "Hotel Amex Card TripID : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelComPayU() {
	      return new Object [ ] [ ] { { "Bangalore", "14", "15", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "PAYU", "Hotel PayU TripID : ", "Your booking is done"}};
	  }

	  @DataProvider
	  public static Object [ ][ ] HotelComPayTM() {
	      return new Object [ ] [ ] { { "New Delhi", "14", "15", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "PAYTM", "Hotel PayTM TripID : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelComStoredCard() {
	      return new Object [ ] [ ] { { "Mumbai", "22", "23", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "StoredCard", "Hotel StoredCard TripID : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelComNetBanking() {
	      return new Object [ ] [ ] { { "Mumbai", "23", "24", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "NETBANKING", "Hotel NetBanking TripID : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelComHQPromo() {
	      return new Object [ ] [ ] { { "Mumbai", "22", "23", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Silver Moon", "", "Hotel TripID : ", "Your booking is done"}};
	  }	
	  
	  @DataProvider
	  public static Object [ ][ ] HotelComCHMMPromo() {
	      return new Object [ ] [ ] { { "New Delhi", "22", "24", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Goodnight", "", "Hotel TripID : ", "Your booking is done"}};
	  }	
	  
	  @DataProvider
		 public static Object [ ][ ] HotelComDPModifySearch() {
		      return new Object [ ] [ ] { {  "Bangalore", "19", "20", "2", "2", "2", "0", "0", "1", "1", "5", "6", "", "", "Hotels Details Page Modify Booking TripID : ", "Your booking is done"}};
		  }
	  
	  @DataProvider
		 public static Object [ ][ ] HotelComAmexIntlSearch() {
		      return new Object [ ] [ ] { {  "Dubai", "19", "20", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking TripID : ", "Your booking is done"}};
		  }
}