// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - MAR, 2015
// Author - Mohamed Faisal
// Copyright © 2012 cleartrip Travel. All right reserved.
package dataServices;

import org.testng.annotations.DataProvider;

import commonServices.WrapperMethod;

public class IndiaDataProvider   extends WrapperMethod{
	
	  @DataProvider
	  public static Object [ ][ ] AirCom_Dom_OW_LCC() {
	      return new Object [ ] [ ] { { "Bangalore", "New Delhi", "19", "20", "1", "0", "0","","SpiceJet","AirCom HomePage Signin TripID : "}};
	      										
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] AirCom_Dom_RT_LCC() {
	      return new Object [ ] [ ] { { "Bangalore", "New Delhi", "19", "20", "1", "0", "0","","SpiceJet","AirCom HomePage Signin TripID : "}};
	      										
	  }
	  
	  @DataProvider(name = "AirOneWayDomestic")
		public static Object[][] B2CAirOWLCCDomFullRefund() {
			String[] origin = { "del", "bom", "kochin" };
			String[] destination = { "goa", "maa", "mangalore" };
			return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0", "credit card",
					false, "Full Refund" } };
		}
	  
	  @DataProvider
	  public static Object [ ][ ] AirCom_Dom_SplRT_LCC() {
	      return new Object [ ] [ ] { { "New Delhi", "Bombay", "19", "25", "1", "0", "0","","","AirCom HomePage Signin TripID : "}};
	      										
	  }
	  
	  @DataProvider(name = "B2CAirDomMultiCity")
		public static Object[][] B2CAirMultiCityLCC() {
			String origin[] = { "DEL", "BOM", "MAA" };
			String destination[] = { "BOM", "MAA", "BLR" };
			return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "lcc", "ROUND", "Auto Refund", false } };
		}
	  
	  
	  @DataProvider(name = "B2CAirIntlMultiCity")
		public static Object[][] B2CAirMultiCityGDS() {
			String origin[] = { "DXB", "DEL", "MAA"};
			String destination[] = { "DEL", "SIN", "CMB"};
			return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "gds", "ROUND", "Auto Refund", false } };
		}
	  

	  @DataProvider
	  public static Object [ ][ ] HotelComCoupon() {
	      return new Object [ ] [ ] { { "Bangalore", "27", "28", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "HotelCom Coupon TripID : "}};
	  }
	
	  public static Object [ ][ ] HotelComNameSearch() {
	      return new Object [ ] [ ] { { "Bangalore", "6", "7", "1", "2", "2", "3", "2", "0", "0", "0", "0", "Grand Inn", "", "HotelCom Name Search TripID : "}};

	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelComPAX() {
	      return new Object [ ] [ ] { { "Bangalore", "12", "13", "1", "2", "2", "3", "2", "1", "1", "1", "1", "", "", "HotelCom PAX Search TripID : "},
	    		  					  { "New Delhi", "13", "14", "2", "1", "1", "1", "1", "1", "1", "1", "1", "", "", "HotelCom PAX Search TripID : "}};
	  }
	  
	  @DataProvider
	  public static Object [ ][ ] HotelCom() {
	      return new Object [ ] [ ] { { "Bangalore", "19", "20", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "", "HotelCom HomePage Signin TripID : "}};
	  }
	  
}
