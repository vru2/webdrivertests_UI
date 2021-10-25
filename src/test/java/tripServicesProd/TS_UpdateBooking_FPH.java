package tripServicesProd;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_UpdateBooking_FPH extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Tripserviceairputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url ="http://trip-service.cltp.com:9001/trips";
		resp=TripservicePostcall(param_fph_log,headersForTripservicepostcall(),url);
		validationforputcall(resp);		
		Response resp1;
		String url1 = ("http://trip-service.cltp.com:9001/trips/"+tripref+"/fph-bookings/update-booking");
		resp1=TripserviceHotelsPutcall(param_fph_update,headersForTripserviceputcall(),url1);
		validationforput(resp1);	
 }
}
