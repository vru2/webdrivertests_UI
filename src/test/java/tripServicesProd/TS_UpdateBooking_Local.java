package tripServicesProd;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_UpdateBooking_Local extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Tripservicelocalputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url ="http://trip-service.cltp.com:9001/trips";
		resp=TripservicePostcall(params2,headersForTripservicepostcall(),url);
		validationforputcall(resp);		
		Response resp1;
			String url1=("http://trip-service.cltp.com:9001/trips/"+tripref+"/local-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params5,headersForTripserviceputcall(),url1);
			System.out.println(resp1.asString());
			validationforput(resp1);
	}
	
	
}