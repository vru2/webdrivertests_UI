package test.java.  tripServicesProd;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_LogBooking_Hotel extends TripserviceCommon {
	
	@Test(groups={"Regression"})
	public void hotelPostCall() throws IOException, ClassNotFoundException, SQLException, InterruptedException{
		Response resp;
		String url = "http://trip-service.cltp.com:9001/trips";
		resp=TripservicePostcall(params1,headersForTripservicepostcall(),url);
		Validation(resp);
	    System.out.println(resp.asString());
		
	}
}
