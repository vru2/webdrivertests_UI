// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Mar, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package dataServices;

import org.testng.annotations.DataProvider;

import commonServices.WrapperMethod;

public class CorporateDataProvider extends WrapperMethod {

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "BLR", "MAA", "19", "20", "1", "0", "0", "" } };
	}

	@DataProvider
	public static Object[][] AirCorpInt() {
		return new Object[][] { { "MAA", "CMB", "21", "22", "1", "0", "0", ""} };
	}

	@DataProvider
	public static Object[][] AirCorpInt_Hold() {
		return new Object[][] { { "MAA", "CMB", "21", "22", "1", "0", "0", "", "AirCorp Int Oneway TripID : ",	"Great, your booking went through successfully." } };
	}

	@DataProvider
	public static Object[][] HotelCorp() {
		return new Object[][] { { "Bangalore", "19", "20", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEPOSITACCOUNT", "Corp Hotel Booking TripID : ", "Thanks for booking with Cleartrip" } };
	}
	
	@DataProvider
	public static Object[][] HotelCorpold() {
		return new Object[][] { { "Mumbai", "19", "20", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEPOSITACCOUNT", "Corp Hotel Booking TripID : ", "Thanks for booking with Cleartrip" } };
	}

	@DataProvider
	public static Object[][] HotelCorpIntl() {
		return new Object[][] { { "Pattaya", "19", "20", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEPOSITACCOUNT", "Corp Hotel Booking TripID : ", "Thanks for booking with Cleartrip" } };
	}
	
	@DataProvider
	public static Object[][] HotelCorpPAH() {
		return new Object[][] { { "Bangalore", "19", "20", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEPOSITACCOUNT", "Corp Hotel Booking TripID : ", "Thanks for booking with Cleartrip" } };
	}

	@DataProvider
	public static Object[][] HotelCorpMultiCombo() {
		return new Object[][] {
				{ "Bangalore", "17", "18", "2", "2", "2", "3", "2", "0", "0", "0", "0", "", "DEPOSITACCOUNT", "Corp Hotels MultiRooms DA TripID : ", "Thanks for booking with Cleartrip" },
				{ "New Delhi", "20", "23", "1", "2", "2", "3", "2", "0", "0", "0", "0", "", "CREDITCARD", "Corp Hotels MultiNights CC TripID : ", "Thanks for booking with Cleartrip" },
				{ "Mumbai", "19", "20", "1", "3", "2", "3", "2", "1", "1", "1", "0", "", "DEBITCARD", "Corp Hotels MultiPAX DC TripID : ", "Thanks for booking with Cleartrip" } };
	}
}
