package test.java.  tripServicesProd;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_UpdateBooking_Trains extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Tripservicetrainputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url ="http://trip-service.cltp.com:9001/trips";
		resp=TripservicePostcall(params6,headersForTripservicepostcall(),url);
		validation(resp);		
		Response resp1;
			String url1 = ("http://trip-service.cltp.com:9001/trips/"+tripref+"/train-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params7,headersForTripserviceputcall(),url1);
			validationforputtrains(resp1);
			System.out.println(resp1.asString());
 }

}
