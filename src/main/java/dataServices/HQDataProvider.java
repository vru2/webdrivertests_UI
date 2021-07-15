// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - MAR, 2015
// Author - Mohamed Faisal
// Copyright © 2012 cleartrip Travel. All right reserved.
package dataServices;

import org.testng.annotations.DataProvider;

public class HQDataProvider {

	@DataProvider(name = "B2CAirOWLCCDomAccCancel")
	public static Object[][] B2CAirOWLCCDomAccCancel() {
		String[] origin = {"ccu", "hyd","del", "blr", "kolkata" };
		String[] destination = {"cok", "blr","bom", "maa", "cochin" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "IndiGo", "Direct", "1", "0", "0", "credit card",
				false, "Auto Refund" } };
	}

	@DataProvider(name = "B2CAirOWLCCDomManualRefund")
	public static Object[][] B2CAirOWLCCDomManualRefund() {
		String[] origin = { "del", "bom", "kochi" };
		String[] destination = { "goa", "maa", "mangalore" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false, "Manual Refund" } };
	}

	@DataProvider(name = "B2CAirOWLCCDomAutoRefund")
	public static Object[][] B2CAirOWLCCDomAutoRefund() {
		String[] origin = { "del", "bom", "kochi" };
		String[] destination = { "goa", "maa", "mangalore" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false, "Auto Refund" } };
	}

	@DataProvider(name = "B2CAirOWLCCDomFullRefund")
	public static Object[][] B2CAirOWLCCDomFullRefund() {
		String[] origin = { "del", "bom", "kochi" };
		String[] destination = { "goa", "maa", "mangalore" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false, "Full Refund" } };
	}

	@DataProvider(name = "B2CAirOWGDSDomAccCancel")
	public static Object[][] B2CAirOWGDSDomAccCancel() {
		String[] origin = { "del", "bom", "blr" };
		String[] destination = { "blr", "del", "goa" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Jet Airways", "Direct", "1", "0", "0", "credit card",
				false, "Auto Refund" } };
	}

	@DataProvider(name = "B2CAirOWGDSDomViaAutoRefund")
	public static Object[][] B2CAirOWGDSDomViaAutoRefund() {
		String[] origin = { "del", "bom", "blr" };
		String[] destination = { "blr", "del", "goa" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "Air India", "Non Direct", "1", "1", "0",
				"credit card", "Via", false, "Auto Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirRTGDSDomViaParPaxAutoRefund")
	public static Object[][] B2CAirRTGDSDomViaParPaxAutoRefund() {
		String[] origin = { "del", "bom", "blr" };
		String[] destination = { "blr", "del", "goa" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "Air India", "Non Direct", "1", "1", "0",
				"credit card", "Via", false, "Auto Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirMultiCityGDSDomManualRefund")
	public static Object[][] B2CAirMultiCityGDSDomManualRefund() {
		String origin[] = { "DEL", "BOM", "MAA" };
		String destination[] = { "BOM", "MAA", "HYD" };
		return new Object[][] { { 2, origin, destination, "1", "1", "0", "MULTICITY", "", "credit card", "gds", "ROUND",
				"Manual Refund", false } };
	}

	@DataProvider(name = "B2CAirMultiCityGDSDomViaAutoRefund")
	public static Object[][] B2CAirMultiCityGDSDomViaAutoRefund() {
		String origin[] = { "DEL", "BOM", "MAA" };
		String destination[] = { "BOM", "MAA", "HYD" };
		return new Object[][] { { 2, origin, destination, "1", "1", "1", "MULTICITY", "", "credit card", "Via", "gds", "ROUND",
				"Auto Refund", false } };
	}

	@DataProvider(name = "B2CAirMultiCityGDSDomViaParSectorAutoRefund")
	public static Object[][] B2CAirMultiCityGDSDomViaParSectorAutoRefund() {
		String origin[] = { "DEL", "BOM", "MAA" };
		String destination[] = { "BOM", "MAA", "HYD" };
		return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "Via", "gds", "ROUND",
				"Auto Refund", false } };
	}

	@DataProvider(name = "B2CAirMultiCityGDSDomViaParPaxAutoRefund")
	public static Object[][] B2CAirMultiCityGDSDomViaParPaxAutoRefund() {
		String origin[] = { "DEL", "BOM", "MAA" };
		String destination[] = { "BOM", "MAA", "HYD" };
		return new Object[][] { { 2, origin, destination, "1", "0", "0", "MULTICITY", "", "credit card", "Via", "gds", "ROUND",
				"Auto Refund", false } };
	}

	@DataProvider(name = "B2CAirOWGDSDomViaParSectorManualRefund")
	public static Object[][] B2CAirOWGDSDomViaParSectorManualRefund() {
		String[] origin = { "del", "bom", "blr" };
		String[] destination = { "blr", "del", "goa" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Non Direct", "1", "0", "0",
				"credit card", "Via", false, "Manual Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirOWGDSDomViaParSectorAutoRefund")
	public static Object[][] B2CAirOWGDSDomViaParSectorAutoRefund() {
		String[] origin = { "del", "bom", "blr" };
		String[] destination = { "blr", "del", "goa" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Non Direct", "1", "0", "0",
				"credit card", "Via", false, "Auto Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirOWGDSDomViaParSectorFullRefundAndAssert")
	public static Object[][] B2CAirOWGDSDomViaParSectorFullRefundAndAssert() {
		String[] origin = { "del", "bom", "blr" };
		String[] destination = { "blr", "del", "goa" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Non Direct", "1", "0", "0",
				"credit card", "Via", false, "Full Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirSplRNDGDSDomAccCancel")
	public static Object[][] B2CAirSplRNDGDSDomAccCancel() {
		String[] origin = { "del", "bom", "blr" };
		String[] destination = { "bom", "blr", "goa" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "Air India", "Direct", "1", "0", "0",
				"credit card", false, "Auto Refund" } };
	}

	@DataProvider(name = "B2CAirSplRNDLCCDomAccCancel")
	public static Object[][] B2CAirSplRNDLCCDomAccCancel() {
		String[] origin = { "del", "blr", "kolkata" };
		String[] destination = { "bom", "maa", "cochin" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "Direct", "1", "0", "0", "credit card", false,
				"Auto Refund" } };
	}
	
	@DataProvider(name = "B2CAirRTGDSLCCDomAccCancel")
	public static Object[][] B2CAirRTGDSLCCDomAccCancel() {
		String[] origin = { "del", "blr", "blr" };
		String[] destination = { "bom", "maa", "goa" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "Direct", "1", "0", "0", "credit card", false,
				"Auto Refund" } };
	}
	
	@DataProvider(name = "B2CAirRTLCCGDSDomAccCancel")
	public static Object[][] B2CAirRTLCCGDSDomAccCancel() {
		String[] origin = { "del", "blr", "blr" };
		String[] destination = { "bom", "maa", "goa" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "Direct", "1", "0", "0", "credit card", false,
				"Auto Refund" } };
	}
	
	@DataProvider(name = "B2CAirRTLCCLCCDomAccCancel")
	public static Object[][] B2CAirRTLCCLCCDomAccCancel() {
		String[] origin = { "del", "blr", "blr" };
		String[] destination = { "bom", "maa", "goa" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "Direct", "1", "0", "0", "credit card", false,
				"Auto Refund" } };
	}
	
	
	
	
	
	
	
	
	
	

	@DataProvider(name = "B2CAirOWGDSIntlHoldAutoRefund")
	public static Object[][] B2CAirOWGDSIntlHoldAutoRefund() {
		String origin[] = { "DEL", "BOM", "BOM" };
		String destination[] = { "DXB", "MCT", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Direct", "1", "0", "0", "credit card",
				false, "Auto Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirOWGDSIntlViaPartialPaxAutoRefund")
	public static Object[][] B2CAirOWGDSIntlViaPartialPaxAutoRefund() {
		String origin[] = { "DEL", "MAA", "BOM" };
		String destination[] = { "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Non Direct", "1", "1", "1",
				"credit card", "Via", false, "Auto Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirOWLCCIntlManualRefund")
	public static Object[][] B2CAirOWLCCIntlManualRefund() {
		String origin[] = { "DEL", "MAA", "BOM" };
		String destination[] = { "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "Direct", "1", "0", "0", "credit card", false,
				"Manual Refund" } };
	}

	@DataProvider(name = "B2CAirOWLCCIntlAutoRefund")
	public static Object[][] B2CAirOWLCCIntlAutoRefund() {
		String origin[] = { "DEL", "MAA", "BOM" };
		String destination[] = { "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "Direct", "1", "0", "0", "credit card", false,
				"Auto Refund" } };
	}

	@DataProvider(name = "B2CAirOWLCCIntlPartialPaxAutoRefund")
	public static Object[][] B2CAirOWLCCIntlPartialPaxAutoRefund() {
		String origin[] = { "DEL", "MAA", "BOM" };
		String destination[] = { "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "Direct", "1", "1", "1", "credit card", false,
				"Auto Refund" } };
	}

	@DataProvider(name = "B2CAirRNDGDSIntlAutoRefund")
	public static Object[][] B2CAirRNDGDSIntlAutoRefund() {
		String origin[] = { "DEL", "MAA", "BOM" };
		String destination[] = { "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "Air India", "Direct", "1", "1", "0",
				"credit card", false, "Auto Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirRNDGDSIntlPartialSegAutoRefund")
	public static Object[][] B2CAirRNDGDSIntlPartialSegAutoRefund() {
		String origin[] = { "DEL", "MAA", "BOM" };
		String destination[] = { "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "Air India", "Direct", "1", "1", "0",
				"credit card", false, "Auto Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirRNDGDSIntlPartialPaxAutoRefund")
	public static Object[][] B2CAirRNDGDSIntlPartialPaxAutoRefund() {
		String origin[] = { "DEL", "MAA", "BOM" };
		String destination[] = { "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "Air India", "direct", "1", "1", "0",
				"credit card", false, "Auto Refund", false, "gds" } };
	}

	@DataProvider(name = "B2CAirRNDGDSIntlMultiCarrierAutoRefund")
	public static Object[][] B2CAirRNDGDSIntlMultiCarrierAutoRefund() {
		String origin[] = { "DEL", "MAA", "BOM" };
		String destination[] = { "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "Non Direct", "1", "1", "0", "credit card",
				"MultiCarrier", false, "Auto Refund", false, "gds" } };
	}
	
	
	
	
	
	@DataProvider(name = "B2CAirOnlineAmendmentSGToAIFromHQ")
		public static Object[][] B2CAirOnlineAmendmentSGToAIFromHQ() {
		String[] origin = { "bom","del", "bom", "kochi" };
		String[] destination = { "maa","bom", "maa", "mangalore" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0",
		"credit card",
		false, "Auto Refund" } };
		}
	
	
	@DataProvider(name = "B2CAirOnlineAmendmentSplRndPartialAmend")
	public static Object[][] B2CAirOnlineAmendmentSplRndPartialAmend() {
		String[] origin = { "blr", "blr", "kolkata" };
		String[] destination = {"del", "maa", "cochin" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "Direct", "1", "0", "0", "credit card", false,
				"Auto Refund" } };
	}
	 
	@DataProvider(name = "B2CAirOnlineAmendmentAIToAIHigherBF")
	public static Object[][] B2CAirOnlineAmendmentAIToAIHigherBF() {
		String[] origin = { "blr","del", "bom", "kochi" };
		String[] destination = {"del", "goa", "maa", "mangalore" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "gds", "Air India", "Direct", "1", "0", "0",
				"credit card", false, "Auto Refund" } };
	}
	
	
	
	
	
	
	
	
	
	
	@DataProvider(name = "B2CAirOWGDSDomTicketingFailure")
	public static Object[][] B2CAirOWGDSDomTicketingFailure() {
		String[] origin = { "bom"};
		String[] destination = { "goa"};
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Direct", "1", "0", "0", "credit card",
				false, "Auto Refund" } };
	}
	
	@DataProvider(name = "B2CAirOWGDSIntlTicketingFailure")
	public static Object[][] B2CAirOWGDSIntlTicketingFailure() {
		String[] origin = { "bom" };
		String[] destination = { "dxb" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Direct", "1", "0", "0",
				"credit card", false } };
	}
	
	@DataProvider(name = "CouponConversionConvert")
	public static Object[][] CouponConversionConvert() {
		String[] origin = { "del", "hyd" };
		String[] destination = { "amd", "maa" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false } };
	}
	
	@DataProvider(name = "CouponConversionDomRNDCheck")
	public static Object[][] CouponConversionReject() {
		String[] origin = {"hyd", "del", "hyd" };
		String[] destination = {"maa", "amd", "maa" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false } };
	}
	
	@DataProvider(name = "CouponConversionIntlOW")
	public static Object[][] CouponConversionIntlOW() {
		String[] origin = { "del" };
		String[] destination = { "lhr" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "Air India", "Direct", "1", "0", "0",
				"credit card", false } };
	}
	
	@DataProvider(name = "CouponConversionIntlRNDCheck")
	public static Object[][] CouponConversionIntlRNDCheck() {
		String[] origin = { "del" };
		String[] destination = { "lhr" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "", "Air India", "Direct", "1", "0", "0",
				"credit card", false } };
	}

	@DataProvider(name = "HQTripTools")
	public static Object[][] HQTripTools() {
		String[] origin = { "del", "bom", "kochi" };
		String[] destination = { "goa", "maa", "mangalore" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "SpiceJet", "Direct", "1", "0", "0",
				"credit card", false, "Auto Refund" } };
	}
	
	@DataProvider(name = "B2CAirPaymentRetryWithInsurance")
	public static Object[][] B2CAirPaymentRetryWithInsurance() {
		String[] origin = { "del","del", "bom", "kochi" };
		String[] destination = {"bom", "goa", "maa", "mangalore" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "lcc", "", "", "1", "0", "0" } };
	}
	
	@DataProvider(name = "RefundCalculationRequestQueue")
	public static Object[][] RefundCalculationRequestQueue() {
		String[] origin = { "del", "bom", "blr" };
		String[] destination = { "blr", "del", "goa" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Air India", "Direct", "1", "0", "0", "credit card",
				false, "Auto Refund" } };
	}
	
	@DataProvider(name = "AutoTicketingSuccessIntl")
	public static Object[][] AutoTicketingSuccessIntl() {
		String[] origin = { "bom" };
		String[] destination = { "dxb" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "", "Jet Airways", "Direct", "1", "0", "0",
				"credit card", false } };
	}
	
	
	
	
	
	
	@DataProvider(name = "B2CPackageDom")
	public static Object[][] B2CPackageDom() {
		String[] origin = { "del", "blr", "kolkata" };
		String[] destination = { "bom", "chennai", "cochin" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Direct", "1", "1", "0", "credit card",
				false, "Auto Refund" } };
	}
	
	@DataProvider(name = "B2CPackageIntl")
	public static Object[][] B2CPackageIntl() {
		String[] origin = { "DEL", "CMB", "DXB" };
		String[] destination = { "Dubai", "chennai", "delhi" };
		return new Object[][] { { origin, destination, "Flights", "OneWay", "Direct", "1", "1", "0", "credit card",
				false, "Auto Refund" } };
	}
	
	
	
	
	
	
	
	@DataProvider(name = "B2CAirRNDGDSIntlStopOverAutoRefund")
	public static Object[][] B2CAirRNDGDSIntlStopOverAutoRefund() {
		String origin[] = { "DEL", "MAA", "BOM" };
		String destination[] = { "DXB", "CMB", "DXB" };
		return new Object[][] { { origin, destination, "Flights", "RoundTrip", "Air India", "Non Direct", "1", "1", "1",
				"credit card", "stopover", false, "Auto Refund", false, "gds" } };
	}
}
