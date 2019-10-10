// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - MAR, 2015
// Author - Mohamed Faisal
// Copyright © 2012 cleartrip Travel. All right reserved.
package dataServices;

import org.testng.annotations.DataProvider;

import commonServices.WrapperMethod;

public class TrainsDataProvider  extends WrapperMethod {
	
	  @DataProvider
	  public static Object [ ][ ] B2cTrains_General() {
	      return new Object [ ] [ ] { { "New Delhi (NDLS)","Chennai Central (MAS)","Sleeper (SL)","10","1","0","0","0","CREDIT CARD","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed"}};
	     
	  }
	  
	  public static Object [] [] B2cTrains_UnsignedUser(){
		  return new Object [ ] [ ] { { "New Delhi (NDLS)","Chennai Central (MAS)","Sleeper (SL)","10","1","0","0","0","CREDIT CARD","B2C Trains HomePage SignIn General Booking with Adult=1","Your Booking is confirmed"}};
		     		  
	  }
	 
	  
}
