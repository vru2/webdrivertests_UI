package tripServicesProd;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TS_Invoice_Generation_Hotel extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Tripservicelocalputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url ="http://trip-service.cltp.com:9001/trips";
		resp=TripservicePostcallinvoicelocal(invoice_hotel,headersForTripservicepostcall(),url);
		validationforputcall(resp);		
		Response resp1;
			String url1=("http://trip-service.cltp.com:9001/trips/"+tripref+"/hotel-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params4,headersForTripserviceputcall(),url1);
			System.out.println(resp1.asString());
			validationforput(resp1);
			Reporter.log("http://trip-service.cltp.com:9001/trips?tripID="+tripref);
			Response resp2=RestAssured.get("http://trip-service.cltp.com:9001/trips?tripID="+tripref);
			invoiceValidation(resp2);
	}

}
