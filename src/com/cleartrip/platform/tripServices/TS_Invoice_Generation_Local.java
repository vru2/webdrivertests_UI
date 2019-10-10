package com.cleartrip.platform.tripServices;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TS_Invoice_Generation_Local extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Tripservicelocalputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url =  Service_Url("TRIPSERVICE_POST_CALL");
		resp=TripservicePostcallinvoicelocal(invoice_local,headersForTripservicepostcall(),url);
		validationforputcall(resp);		
		Response resp1;
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
			String url1=("http://172.17.26.11:9031/trips/"+tripref+"/local-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params5,headersForTripserviceputcall(),url1);
			validationforput(resp1);
			
		} else if(Host.equalsIgnoreCase("dev")) {
			String url1 = ("http://172.17.32.12:9031/trips/"+tripref+"/local-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params5,headersForTripserviceputcall(),url1);
			validationforput(resp1);	
		}
		else if(Host.equalsIgnoreCase("www")) {
			String url1=("http://trip-service.cltp.com:9001/trips/"+tripref+"/local-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params5,headersForTripserviceputcall(),url1);
			System.out.println(resp1.asString());
			validationforput(resp1);
		}	
		//String url1=Service_Url("TRIPSERVICE_PUT_CALL");
		Thread.sleep(4000);
		DBValidation_Txn(resp, "C");
		
		String Host1 = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
		Reporter.log("http://172.17.26.11:9031/trips?tripID="+tripref);
		Response resp2=RestAssured.get("http://172.17.26.11:9031/trips?tripID="+tripref);
		invoiceValidation(resp2);
		}else if(Host.equalsIgnoreCase("www")) {
			Reporter.log("http://trip-service.cltp.com:9001/trips?tripID="+tripref);
			Response resp2=RestAssured.get("http://trip-service.cltp.com:9001/trips?tripID="+tripref);
			invoiceValidation(resp2);
		}else if(Host.equalsIgnoreCase("dev")) {
			Reporter.log("http://172.17.32.12:9031/trips?tripID="+tripref);
			Response resp2=RestAssured.get("http://172.17.32.12:9031/trips?tripID="+tripref);
			invoiceValidation(resp2);		}
	}

}
