package tripServices;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TS_Invoice_Generation_Air extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Tripserviceairputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url =  Service_Url("TRIPSERVICE_POST_CALL");
		Reporter.log(url);
		resp=TripservicePostcallinvoiceair(invoice_air,headersForTripservicepostcall(),url);
		validationforputcall(resp);		
		Response resp1;
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
		String url1 = ("http://172.17.51.86:9031/trips/"+tripref+"/air-bookings/update-booking");
		Reporter.log(url1);
		resp1=TripserviceHotelsPutcall(params3,headersForTripserviceputcall(),url1);
		validationforput(resp1);	
	} else if(Host.equalsIgnoreCase("dev")) {
		String url1 = ("http://172.17.32.12:9031/trips/"+tripref+"/air-bookings/update-booking");
		resp1=TripserviceHotelsPutcall(params3,headersForTripserviceputcall(),url1);
		validationforput(resp1);	
		} /*
			 * else if (Host.equalsIgnoreCase("www")) { String url1 =
			 * ("http://172.21.48.21:9031/trips/"+tripref+"/air-bookings/update-booking");
			 * resp1=TripserviceHotelsPutcall(params3,headersForTripserviceputcall(),url1);
			 * validationforput(resp1); }
			 */
		Thread.sleep(15000);		
		DBValidation_Txn(resp, "C");	

		String Host1 = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
		Reporter.log("http://172.17.51.86:9031/trips?tripID="+tripref);
		Response resp2=RestAssured.get("http://172.17.51.86:9031/trips?tripID="+tripref);
		invoiceValidation(resp2);
		}else if(Host.equalsIgnoreCase("www")) {
			Reporter.log("http://trip-service.cltp.com:9001/trips?tripID=190401816972");
			Response resp2=RestAssured.get("http://trip-service.cltp.com:9001/trips?tripID=190401816972");
			invoiceValidation(resp2);
		}else if(Host.equalsIgnoreCase("dev")) {
			Reporter.log("http://172.17.32.12:9031/trips?tripID=Q1904255576");
			Response resp2=RestAssured.get("http://172.17.32.12:9031/trips?tripID=Q1904255576");
			invoiceValidation(resp2);		}
	}
 }

