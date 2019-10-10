// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Mar, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package dataServices;

import org.testng.annotations.DataProvider;

import commonServices.WrapperMethod;

public class AgencyDataProvider extends WrapperMethod{
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyDom() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "15", "16", "1", "0", "0", "", "DEPOSITACCOUNT", "Air Agency Dom Oneway TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyRT() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "25", "26", "1", "0", "0", "", "CREDITCARD", "Air Agency Dom RT TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencySPLRT() {
	      return new Object [ ] [ ] { { "BOM", "DEL", "26", "28", "1", "0", "0", "", "CREDITCARD", "Air Agency Dom Spl RT TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyDomPAX() {
	      return new Object [ ] [ ] { { "BLR", "DEL", "19", "20", "1", "1", "1", "", "CREDITCARD", "Air Agency '111' PAX Oneway TripID : ", "Great, your booking went through successfully."},
	    		  					  { "BOM", "DEL", "15", "22", "2", "0", "0", "", "CREDITCARD", "Air Agency '200' PAX Oneway TripID : ", "Great, your booking went through successfully."},
	    		  					  { "MAA", "BLR", "16", "23", "2", "1", "0", "", "CREDITCARD", "Air Agency '210' PAX Oneway TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyDomPAX111() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "19", "20", "1", "1", "1", "", "CREDITCARD", "Air Agency '111' PAX Oneway TripID : ", "Great, your booking went through successfully."}};
	  }
	

	@DataProvider
	  public static Object [ ][ ] AirAgencyDomPAX200() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "19", "20", "2", "0", "0", "", "CREDITCARD", "Air Agency '200' PAX Oneway TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyDomPAX210() {
	      return new Object [ ] [ ] { { "BOM", "DEL", "19", "20", "2", "1", "0", "", "CREDITCARD", "Air Agency '210' PAX Oneway TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyPaymentDC() {
	      return new Object [ ] [ ] { { "BOM", "DEL", "20", "21", "1", "0", "0", "", "DEBITCARD", "Air Agency Debit Card TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyPaymentDA() {
	      return new Object [ ] [ ] { { "BOM", "DEL", "20", "21", "1", "0", "0", "", "DEPOSITACCOUNT", "Air Agency Deposit Account TripID : ", "Great, your booking went through successfully."}};
	  }

	@DataProvider
	  public static Object [ ][ ] AirAgencyPaymentCC() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "20", "21", "1", "0", "0", "", "CREDITCARD", "Air Agency CC TripID : ", "Great, your booking went through successfully."}};
	  }
	
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyDomPayment() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "20", "21", "1", "0", "0", "", "DEBITCARD", "Air Agency Debit Card TripID : ", "Great, your booking went through successfully."},
	    		  					  { "BOM", "DEL", "21", "22", "1", "0", "0", "", "CREDITCARD", "Air Agency Credit Card TripID : ", "Great, your booking went through successfully."},
	    		  					{ "DEL", "BOM", "21", "22", "1", "0", "0", "", "DEPOSITACCOUNT", "Air Agency DepositAccount TripID : ", "Great, your booking went through successfully."},
	    		  					  { "DEL", "BOM", "10", "11", "1", "0", "0", "", "ITZ", "Air Agency CashCard ITZ", ""}};
	  }
	
	@DataProvider(name = "AgencyDomMuiticity")
		public static Object[][] AgencyDomMuiticity() {
			String origin[] = { "MAA", "BLR", "DEL"};
			String destination[] = { "BLR", "DEL", "BOM"};
			return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "gds", "ROUND", "Auto Refund", false , "DEPOSITACCOUNT", "Air Agency Dom MultiCity TripID : ", "Great, your booking went through successfully." } };
		}
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyDomOWCancel() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "23", "25", "1", "0", "0", "", "DEPOSITACCOUNT", "Air Agency Dom Oneway Cancelled TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyIntOneway() {
	      return new Object [ ] [ ] { { "DXB", "DEL", "15", "16", "1", "0", "0", "", "DEPOSITACCOUNT", "Air Agency Intl Oneway TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyIntRT() {
	      return new Object [ ] [ ] { { "DEL", "SIN", "14", "15", "1", "0", "0", "", "DEPOSITACCOUNT", "Air Agency Intl Rnd Trip TripID : ", "Great, your booking went through successfully."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] AirAgencyIntHoldOneway() {
	      return new Object [ ] [ ] { { "DEL", "DXB", "9", "10", "1", "0", "0", "", "DEPOSITACCOUNT", "Air Agency Intl Hold Oneway TripID : ", "Great, your booking went through successfully."}};
	  }
		
	 @DataProvider(name = "AgencyIntMuiticity")
		public static Object[][] AgencyIntMuiticity() {
			String origin[] = { "BOM", "MAA", "CMB"};
			String destination[] = { "BOM", "CMB", "BLR"};
			return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "gds", "ROUND", "Auto Refund", false , "DEPOSITACCOUNT", "Air Agency Intl MultiCity TripID : ", "Great, your booking went through successfully." } };
	 }
	
	@DataProvider
	  public static Object [ ][ ] CTPhoneIVR() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "19", "20", "1", "0", "0", "", "IVR", "CTPhone Air IVR Booking TripID : ", "Great, your booking went through successfully."}}; 
	 }
	
	@DataProvider
	  public static Object [ ][ ] CTPhoneTechProcess() {
	      return new Object [ ] [ ] { { "DEL", "BOM", "19", "20", "1", "0", "0", "", "TECHPROCESS", "CTPhone Air Tech Process Booking TripID : ", "Great, your booking went through successfully."}}; 
	 }
	 
	@DataProvider
	  public static Object [ ][ ] HotelAgencyPAXandPaymentCombo() {
	      return new Object [ ] [ ] { { "New Delhi", "20", "21", "2", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEBITCARD", "Agency Hotels 2 rooms DC  TripID : ", 			"We have confirmed your booking & emailed you all the details."},
	    		  					  { "Ooty", "19", "21", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "CREDITCARD", 	"Agency Hotels MultiNights CC TripID : ", 		"We have confirmed your booking & emailed you all the details."},
	    		  					  { "Mumbai", "19", "20", "1", "3", "2", "3", "2", "1", "1", "1", "0", "", "DEPOSITACCOUNT", "Agency Hotels 3 adults 1 child DA TripID : ", "We have confirmed your booking & emailed you all the details."}};
	 }

	@DataProvider
	  public static Object [ ][ ] HotelAgency() {
	      return new Object [ ] [ ] { { "Mumbai", "23", "24", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEPOSITACCOUNT", "Agency Hotels Booking TripID : ", "We have confirmed your booking & emailed you all the details."}};
	  }
	
	@DataProvider
	  public static Object [ ][ ] HotelAgencyDelhi() {
	      return new Object [ ] [ ] { { "New Delhi", "23", "24", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEPOSITACCOUNT", "Agency Hotels Booking TripID : ", "We have confirmed your booking & emailed you all the details."}};
	  }
	

	@DataProvider
	  public static Object [ ][ ] HotelAgencyMumbai() {
	      return new Object [ ] [ ] { { "Mumbai", "23", "24", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEPOSITACCOUNT", "Agency Hotels Booking TripID : ", "We have confirmed your booking & emailed you all the details."}};
	  }
	

	@DataProvider
	  public static Object [ ][ ] HotelAgencyIntl() {
	      return new Object [ ] [ ] { { "Singapore", "23", "24", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEPOSITACCOUNT", "Agency Hotels Booking TripID : ", "We have confirmed your booking & emailed you all the details."}};
	  }
	
	
	@DataProvider
	  public static Object [ ][ ] HotelCTPhoneIVR() {
	      return new Object [ ] [ ] { { "Bangalore", "20", "21", "1", "1", "1", "3", "2", "0", "0", "0", "0", "", "IVR", "CTPhone Hotels IVR Booking TripID : ", 			"We have confirmed your booking & emailed you all the details."}};
	 }
	
	@DataProvider
	  public static Object [ ][ ] HotelCTPhoneTechProcess() {
	      return new Object [ ] [ ] { { "Ooty", "20", "21", "2", "2", "2", "3", "2", "0", "0", "0", "0", "", "TECHPROCESS", "CTPhone Hotels Tech Process Booking TripID : ", "We have confirmed your booking & emailed you all the details."}};
	 }
	
}