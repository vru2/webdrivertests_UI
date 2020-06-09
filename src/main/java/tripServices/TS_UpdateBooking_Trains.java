package tripServices;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_UpdateBooking_Trains extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void Tripservicetrainputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url =  Service_Url("TRIPSERVICE_POST_CALL");
		Reporter.log(url);
		resp=TripservicePostcall(params6,headersForTripservicepostcall(),url);
		validation(resp);		
		Response resp1;
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
		String url1 = ("http://172.17.26.11:9031/trips/"+tripref+"/train-bookings/update-booking");
		Reporter.log(url1);
		resp1=TripserviceHotelsPutcall(params7,headersForTripserviceputcall(),url1);
		validationforputtrains(resp1);	
		}  else if(Host.equalsIgnoreCase("dev")) {
			String url1 = ("http://172.17.32.12:9031/trips/"+tripref+"/train-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params7,headersForTripserviceputcall(),url1);
			validationforputtrains(resp1);	
		}
		/*
		 * else if(Host.equalsIgnoreCase("www")) { String url1 =
		 * ("http://172.21.48.21:9031/trips/"+tripref+"/train-bookings/update-booking");
		 * resp1=TripserviceHotelsPutcall(params7,headersForTripserviceputcall(),url1);
		 * validationforputtrains(resp1); }
		 */	
		Thread.sleep(4000);
		 DBValidation_TxnTrains(resp, "C");	
 }

}
