// Framework - Cleartrip Automation
// Author - Kiran Kumar

package dataServices;

import org.testng.annotations.DataProvider;
import commonServices.WrapperMethod;

public class MiddleEastDataProvider   extends WrapperMethod{

	 @DataProvider
	  public static Object [ ][ ] HotelAE() {
	      return new Object [ ] [ ] { {  "Mumbai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels AE Booking : ", "Your booking is done"}};
	  }
	 

	 @DataProvider
	  public static Object [ ][ ] HotelAEIntl() {
	      return new Object [ ] [ ] { {  "Dubai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels AE Intl Booking : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	  public static Object [ ][ ] HotelAEPAH() {
	      return new Object [ ] [ ] { {  "Dubai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Dubai Youth Hostel", "PAH", "Hotels AE PAH Booking : ", "Your booking is done"}};
	  }
	 
	 
	 @DataProvider
	  public static Object [ ][ ] HotelAECoupon() {
	      return new Object [ ] [ ] { {  "New Delhi", "19", "20", "1", "1", "1", "1", "2", "0", "0", "0", "0", "", "", "Hotels AE Coupon Booking : ", "Your booking is done"}};
	  }
	 
	 @DataProvider
	  public static Object [ ][ ] HotelAEMultiCombo() {
	      return new Object [ ] [ ] {{ "Chennai", "16", "19", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "CREDITCARD", "Hotel AE MultiNights CC Booking TripID : ", "We have confirmed your booking & emailed you all the details."},
	    		  					 { "Goa", "14", "15", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "WALLET", "Hotel AE MultiNights Wallet Booking TripID : ", "We have confirmed your booking & emailed you all the details."},
	    		  					 { "Mumbai", "14", "15", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "STOREDCARD", "Hotel AE MultiPAX StoredCard Booking TripID : ", "We have confirmed your booking & emailed you all the details."}};
	  }

	  @DataProvider
	  public static Object [ ][ ] HotelAEPartPay() {
	      return new Object [ ] [ ] { { "Bangalore", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Ramanashree", "DEBITCARD", "Hotel AE Part Pay Unavailable : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelBHPartPay() {
	      return new Object [ ] [ ] { {  "http://bh.cleartrip.com", "http://betabh.cleartrip.com", "Mumbai", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Ramanashree", "DEBITCARD", "Hotel AE Part Pay Unavailable : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelOMPartPay() {
	      return new Object [ ] [ ] { {  "http://om.cleartrip.com", "http://betaom.cleartrip.com", "Mumbai", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Ramanashree", "DEBITCARD", "Hotel AE Part Pay Unavailable : ", "Your booking is done"}};
	  }

	  @DataProvider
	  public static Object [ ][ ] HotelKWPartPay() {
	      return new Object [ ] [ ] { {  "http://kw.cleartrip.com", "http://betakw.cleartrip.com", "Mumbai", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Ramanashree", "DEBITCARD", "Hotel AE Part Pay Unavailable : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelQAPartPay() {
	      return new Object [ ] [ ] { {  "http://qa.cleartrip.com", "http://betaqa.cleartrip.com", "New Delhi", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Ramanashree", "DEBITCARD", "Hotel AE Part Pay Unavailable : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelAEHold() {
	      return new Object [ ] [ ] { { "Mumbai", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Galaxy's Vaibhav", "DEBITCARD", "Hotel AE Hold Booking : ", "Your booking is done"}};
	  }

	  @DataProvider
	  public static Object [ ][ ] HotelAEGiftVoucher() {
	      return new Object [ ] [ ] { { "New Delhi", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "OYO Rooms Jayadeva Shopper's Stop", "GIFTVOUCHER", "Hotel AE Gift Voucher : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelAEWalletCT() {
	      return new Object [ ] [ ] { {  "Ooty ", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Modern Villa", "WALLET", "Hotel AE Wallet Booking : ", "Your booking is done"}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast() {
	      return new Object [ ] [ ] { { "http://bh.cleartrip.com", "http://betabh.cleartrip.com",  "Chennai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels BH Booking : ", "Your booking is done"},
	    		  								 { "http://kw.cleartrip.com", "http://betakw.cleartrip.com",  "Chennai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels KW Booking : ", "Your booking is done"},
	    		  								 { "http://qa.cleartrip.com", "http://betaqa.cleartrip.com",  "Mumbai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels QA Booking : ", "Your booking is done"},
	    		  								 { "http://www.cleartrip.sa", "http://beta.cleartrip.sa",  "Kolkata", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels SA Booking : ", "Your booking is done"},
	    		  								 { "http://om.cleartrip.com", "http://betaom.cleartrip.com",  "Pune", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels OM Booking : ", "Your booking is done"},
	    		  								 { "http://om.cleartrip.com", "http://betaom.cleartrip.com",  "Pune", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels OM Booking : ", "Your booking is done"}};
	  }
	
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_BH() {
	      return new Object [ ] [ ] { { "http://bh.cleartrip.com", "http://betabh.cleartrip.com",  "Mumbai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels BH Booking : ", "Your booking is done"},
	    		  							};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_Intl_BH() {
	      return new Object [ ] [ ] { { "http://bh.cleartrip.com", "http://betabh.cleartrip.com",  "Dubai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels BH Booking : ", "Your booking is done"},
	    		  							};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_KW() {
	      return new Object [ ] [ ] { { "http://kw.cleartrip.com", "http://betakw.cleartrip.com",  "New Delhi", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels KW Booking : ", "Your booking is done"},
	    		  							};
	  }

	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_SA() {
	      return new Object [ ] [ ] { { "http://qa2.cleartrip.sa", "http://www.cleartrip.sa",  "New Delhi", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels SA Booking : ", "Your booking is done"},
	    		  							};
	  }

	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_ME() {
	      return new Object [ ] [ ] { { "http://me.cleartrip.com", "http://me.cleartrip.com",  "Bangalore", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels SA Booking : ", "Your booking is done"},
	    		  							};
	  }
	  

	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_Intl_KW() {
	      return new Object [ ] [ ] { { "http://kw.cleartrip.com", "http://betakw.cleartrip.com",  "Dubai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels KW Intl Booking : ", "Your booking is done"},
	    		  							};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_QA() {
	      return new Object [ ] [ ] { { "http://qa.cleartrip.com", "http://betaqa.cleartrip.com",  "Mumbai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels QA Booking : ", "Your booking is done"},
	    		  							};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_Intl_QA() {
	      return new Object [ ] [ ] { { "http://qa.cleartrip.com", "http://betaqa.cleartrip.com",  "Dubai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels QA intl Booking : ", "Your booking is done"},
	    		  							};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_Intl_SA() {
	      return new Object [ ] [ ] { { "http://qa2.cleartrip.sa", "http://www.cleartrip.sa",  "Dubai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels SA intl Booking : ", "Your booking is done"},
	    		  							};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_Intl_ME() {
	      return new Object [ ] [ ] { { "http://me.cleartrip.com", "http://me.cleartrip.com",  "Dubai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels SA intl Booking : ", "Your booking is done"},
	    		  							};
	  }
	  	  
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_OM() {
	      return new Object [ ] [ ] { { "https://om.cleartrip.com", "https://betaom.cleartrip.com",  "Pune", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels OM Booking : ", "Your booking is done"},
	    		  							};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelMiddleEast_Intl_OM() {
	      return new Object [ ] [ ] { { "https://om.cleartrip.com", "https://betaom.cleartrip.com",  "Dubai", "20", "21", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "", "Hotels OM intl Booking : ", "Your booking is done"},
	    		  							};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelOMHold() {
	      return new Object [ ] [ ] { {"https://om.cleartrip.com", "https://betaom.cleartrip.com", "Mumbai", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Galaxy's Vaibhav", "DEBITCARD", "Hotel OM Hold Booking : ", "Your booking is done"},};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelBHHold() {
	      return new Object [ ] [ ] { {"https://bh.cleartrip.com", "https://betabh.cleartrip.com", "Mumbai", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Galaxy's Vaibhav", "DEBITCARD", "Hotel BH Hold Booking : ", "Your booking is done"},};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelKWHold() {
	      return new Object [ ] [ ] { {"https://kw.cleartrip.com", "https://betakw.cleartrip.com", "Mumbai", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Galaxy's Vaibhav", "DEBITCARD", "Hotel KW Hold Booking : ", "Your booking is done"},};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelQAHold() {
	      return new Object [ ] [ ] { {"https://qa.cleartrip.com", "https://betaqa.cleartrip.com", "Mumbai", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "Hotel Galaxy's Vaibhav", "DEBITCARD", "Hotel AE Hold Booking : ", "Your booking is done"},};
	  }
	  
}