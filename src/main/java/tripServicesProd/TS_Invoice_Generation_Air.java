package tripServicesProd;

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
		String url ="http://trip-service.cltp.com:9001/trips";
		resp=TripservicePostcallinvoiceair(invoice_air,headersForTripservicepostcall(),url);
		validationforputcall(resp);		
		Response resp1;
		String url1 = ("http://172.21.48.21:9031/trips/"+tripref+"/air-bookings/update-booking");
		resp1=TripserviceHotelsPutcall(params3,headersForTripserviceputcall(),url1);
		validationforput(resp1);
			Reporter.log("http://trip-service.cltp.com:9001/trips?tripID=190401816972");
			Response resp2=RestAssured.get("http://trip-service.cltp.com:9001/trips?tripID=190401816972");
			invoiceValidation(resp2);
	}
 }

