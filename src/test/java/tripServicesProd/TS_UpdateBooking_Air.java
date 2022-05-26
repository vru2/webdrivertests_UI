package test.java.tripServicesProd;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_UpdateBooking_Air extends TripserviceCommon {

	@Test(groups={"Regression"})
	public void Tripserviceairputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url =  "http://trip-service.cltp.com:9001/trips";
		resp=TripservicePostcall(params,headersForTripservicepostcall(),url);
		validationforputcall(resp);		
		Response resp1;
		String url1 = ("http://trip-service.cltp.com:9001/trips/"+tripref+"/air-bookings/update-booking");
		resp1=TripserviceHotelsPutcall(params3,headersForTripserviceputcall(),url1);
		validationforput(resp1);
 }
}
