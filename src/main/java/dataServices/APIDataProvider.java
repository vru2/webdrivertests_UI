package dataServices;

import org.testng.annotations.DataProvider;

public class APIDataProvider {

	// for book
	@DataProvider(name = "oneAdultIntl")
	public static Object[][] oneAdultIntl() {
		String[] origin = {"BOM","DEL","SIN","DEL"};
		String[] destination = {"SIN","DXB","BLR","DXB"};
		String[] carrier = {"AI","9W","9W","AI"};
		return new Object[][] { { origin,destination,carrier, "1", "0" } };
		//return new Object[][] { { "MAA", "CMB", "AI", "1", "0" } };
	}
	@DataProvider(name = "oneAdultIntlbeta")
	public static Object[][] oneAdultIntlbeta() {
		//String[] origin = {"MAA","DEL","MAA","DEL"};
		//String[] destination = {"CMB","DXB","CMB","DXB"};
		//String[] carrier = {"AI","9W","9W","AI"};
		return new Object[][] { { "MAA","CMB","AI", "1", "0" } };
		//return new Object[][] { { "MAA", "CMB", "AI", "1", "0" } };
	}
	
	
	
	
	@DataProvider(name = "oneAdultIntlIndigo")
	public static Object[][] oneAdultIntlIndigo() {
		return new Object[][] { { "BLR", "SIN", "6E", "1", "0" } };
	}
	
	@DataProvider(name = "oneAdultIntlSG")
	public static Object[][] oneAdultIntlSG() {
		String[] origin = {"MAA","CMB","DEL","DXB"};
	String[] destination = {"CMB","MAA","DXB","DEL"};
		String[] carrier = {"SG","9W","9W","AI"};
	//	return new Object[][] { { "MAA","CMB","SG", "1", "0" } };
		return new Object[][] { { origin,destination, "SG", "1", "0" } };
	}
	@DataProvider(name = "oneAdultIntlSGBooking")
	public static Object[][] oneAdultIntlSGBooking() {
		//String[] origin = {"MAA","CMB","DEL","DXB"};
	//String[] destination = {"CMB","MAA","DXB","DEL"};
		//String[] carrier = {"SG","9W","9W","AI"};
	return new Object[][] { { "DEL","DXB","SG", "1", "0" } };
		//return new Object[][] { { origin,destination, "SG", "1", "0" } };
	}
	@DataProvider(name = "oneAdultIntl6E")
	public static Object[][] oneAdultIntl6E() {
		return new Object[][] { { "DEL", "DXB", "6E", "1", "0" } };
	}
	@DataProvider(name = "oneAdultIntlG9")
	public static Object[][] oneAdultIntlG9() {
		return new Object[][] { { "DEL", "DXB", "G9", "1", "0" } };
	}

	@DataProvider(name = "oneAdultDomAmadeus")
	public static Object[][] oneAdultDomAmadeus() {
		String[] origin = {"DEL","DEL","DEL","DEL"};
		String[] destination = {"BOM","BLR","BOM","BLR"};
		String[] carrier = {"UK","9W","9W","AI"};
		return new Object[][] { { origin,destination,carrier, "1", "0" } };
	}

	@DataProvider(name = "oneAdultOneChildDOMVia")
	public static Object[][] oneAdultOneChildDOMVia() {
		String[] origin = {"DEL","BLR","DEL","BLR"};
		String[] destination = {"BOM","AMD","BOM","AMD"};
		String[] carrier = {"6E","SG","SG","6E"};
		
		
		return new Object[][] { {origin,destination,carrier, "1", "1", "0" }, };

	}
	
	
	
	
	
	
	
	// for search
	@DataProvider(name = "AIRLINE_CODE_INVALID")
	public static Object[][] AIRLINE_CODE_INVALID() {
		return new Object[][] { { "BOM", "BLR", "2016-07-18", "AIX", "1", "0" } };
	}

	@DataProvider(name = "Api_AIRPORT_CODE_INVALID_D")
	public static Object[][] Api_AIRPORT_CODE_INVALID_D() {
		return new Object[][] { { "BOM", "BBB", "2016-07-18", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_AIRPORT_CODE_INVALID_O")
	public static Object[][] Api_AIRPORT_CODE_INVALID_O() {
		return new Object[][] { { "BBB", "BLR", "2016-07-18", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_CABIN_TYPE_INVALID")
	public static Object[][] Api_CABIN_TYPE_INVALID() {
		return new Object[][] { { "BOM", "BLR", "2016-08-18", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_DEPART_DATE_INVALID_EXPIRED")
	public static Object[][] Api_DEPART_DATE_INVALID_EXPIRED() {
		return new Object[][] { { "BOM", "BLR", "2016-07-07", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_DEPARTDATE_INVALID_300")
	public static Object[][] Api_DEPARTDATE_INVALID_300() {
		return new Object[][] { { "BOM", "BLR", "2016-08-07", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_DEPARTURE_AND_ARRIVAL_SAME")
	public static Object[][] Api_DEPARTURE_AND_ARRIVAL_SAME() {
		return new Object[][] { { "BLR", "BLR", "2016-08-07", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_DEPARTURE_DATE_FORMAT_INVALID")
	public static Object[][] Api_DEPARTURE_DATE_FORMAT_INVALID() {
		return new Object[][] { { "BOM", "BLR", "27-2016-08", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_DESTIN_Empty")
	public static Object[][] Api_DESTIN_Empty() {
		return new Object[][] { { "BOM", "", "2016-08-07", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_INFANT_COUNT_INVALID")
	public static Object[][] Api_INFANT_COUNT_INVALID() {
		return new Object[][] { { "BOM", "BLR", "2016-08-07", "AI", "1", "0", "2" } };
	}

	@DataProvider(name = "Api_Invalid_Auth")
	public static Object[][] Api_Invalid_Auth() {
		return new Object[][] { { "BOM", "BLR", "2016-08-07", "AI", "1", "0", "0" } };
	}

	@DataProvider(name = "Api_INVALID_PAX")
	public static Object[][] Api_INVALID_PAX() {
		return new Object[][] { { "BOM", "BLR", "2016-08-07", "AI", "0", "0", "0" } };
	}

	@DataProvider(name = "Api_NUMBER_OF_PASSENGERS_EXCEEDED")
	public static Object[][] Api_NUMBER_OF_PASSENGERS_EXCEEDED() {
		return new Object[][] { { "BOM", "BLR", "2016-08-07", "AI", "10", "0", "0" } };
	}

	@DataProvider(name = "Api_Onway_Adult1_Dom")
	public static Object[][] Api_Onway_Adult1_Dom() {
		return new Object[][] { { "BOM", "BLR", "2016-08-07", "AI", "1", "0", "0" } };
	}

	@DataProvider(name = "Api_Onway_Adult1_Intl")
	public static Object[][] Api_Onway_Adult1_Intl() {
		return new Object[][] { { "BOM", "DEL", "2016-08-07", "AI", "1", "0", "0" } };
	}

	@DataProvider(name = "Api_ORIGIN_Empty")
	public static Object[][] Api_ORIGIN_Empty() {
		return new Object[][] { { "", "SIN", "2016-08-07", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_RETURN_DATE_FORMAT_INVALID")
	public static Object[][] Api_RETURN_DATE_FORMAT_INVALID() {
		return new Object[][] { { "BOM", "BLR", "2016-08-07", "12-08-2016", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_RETURN_DATE_INVALID")
	public static Object[][] Api_RETURN_DATE_INVALID() {
		return new Object[][] { { "BOM", "BLR", "2016-08-07", "2016-08-01", "AI", "1", "0" } };
	}

	@DataProvider(name = "Api_Roundtrip_Adult1_Dom")
	public static Object[][] Api_Roundtrip_Adult1_Dom() {
		return new Object[][] { { "BOM", "BLR", "2016-08-07", "2016-08-11", "AI", "1", "0", "0" } };
	}

	@DataProvider(name = "Api_Roundtrip_Adult1_Intl")
	public static Object[][] Api_Roundtrip_Adult1_Intl() {
		return new Object[][] { { "BLR", "DEL", "2016-08-07", "2016-08-11", "6E", "1", "0", "0" } };
	}

	@DataProvider(name = "Api_Roundtrip_Adult9_Infant9")
	public static Object[][] Api_Roundtrip_Adult9_Infant9() {
		return new Object[][] { { "MAA", "BLR", "2016-08-07", "2016-08-11", "6E", "9", "0", "9" } };
	}

	@DataProvider(name = "OW_Carrier")
	public static Object[][] OW_Carrier() {
		return new Object[][] { { "9W" }, { "AI" }, { "6E" }, { "SG" } };
	}

	@DataProvider(name = "RT_Carrier")
	public static Object[][] RT_Carrier() {
		return new Object[][] { { "9W" }, { "AI" }, { "6E" }, { "SG" } };
	}

}
