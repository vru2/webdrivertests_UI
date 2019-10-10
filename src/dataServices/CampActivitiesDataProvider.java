// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - MAR, 2015
// Author - Mohamed Faisal
// Copyright © 2012 cleartrip Travel. All right reserved.
package dataServices;

import org.testng.annotations.DataProvider;

import commonServices.WrapperMethod;

public class CampActivitiesDataProvider   extends WrapperMethod{
	
	  @DataProvider
	  public static Object [ ][ ] HotelComCoupon() {
	      return new Object [ ] [ ] { {  "Mumbai", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking", "Your booking is done"},
	    		  					  {  "Mumbai", "21", "22", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking", "Your booking is done"},
	    		  					  {  "Delhi", "10", "11", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking", "Your booking is done"},
	    		  					  {  "Delhi", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking", "Your booking is done"},
	    		  					  {  "Chennai", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking", "Your booking is done"},
	    		  					  {  "Hyderabad", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking", "Your booking is done"},
	    		  					  {  "Ooty", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking", "Your booking is done"},
	    		  					  {  "Bangalore", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking", "Your booking is done"},
	    		  					  {  "Delhi", "19", "20", "1", "1", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Hotels Coupon Booking", "Your booking is done"},};
	  }
}