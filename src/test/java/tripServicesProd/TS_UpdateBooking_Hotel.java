package test.java.tripServicesProd;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_UpdateBooking_Hotel extends TripserviceCommon {

	@Test(groups={"Regression"})
	public void Tripservicehotelputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url = "http://trip-service.cltp.com:9001/trips";
		resp=TripservicePostcall(params1,headersForTripservicepostcall(),url);
		System.out.println(resp.asString());
		validationforputcall(resp); 
		Response resp1;
		String url1 = ("http://trip-service.cltp.com:9001/trips/"+tripref+"/hotel-bookings/update-booking");
		resp1=TripserviceHotelsPutcall(params4,headersForTripserviceputcall(),url1);
		validationforput(resp1);
			
	}	
		
 }
