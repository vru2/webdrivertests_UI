package tripServices;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_UpdateBooking_Hotel extends TripserviceCommon {

	@Test(groups={"Regression"})
	public void Tripservicehotelputcall() throws IOException, ClassNotFoundException, SQLException, InterruptedException
	{
		Response resp;
		String url =  Service_Url("TRIPSERVICE_POST_CALL");
		resp=TripservicePostcall(params1,headersForTripservicepostcall(),url);
		System.out.println(resp.asString());
		validationforputcall(resp); 
		Response resp1;
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
		String url1=("http://172.17.26.11:9031/trips/"+tripref+"/hotel-bookings/update-booking");
		Thread.sleep(2000);
		resp1=TripserviceHotelsPutcall(params4,headersForTripserviceputcall(),url1);
		validationforput(resp1);
			
		} else if(Host.equalsIgnoreCase("dev")) {
			String url1 = ("http://172.17.32.12:9031/trips/"+tripref+"/hotel-bookings/update-booking");
			resp1=TripserviceHotelsPutcall(params4,headersForTripserviceputcall(),url1);
			validationforput(resp1);	
		}
		/*
		 * else if(Host.equalsIgnoreCase("www")) { String url1 =
		 * ("http://172.21.48.21:9031/trips/"+tripref+"/hotel-bookings/update-booking");
		 * resp1=TripserviceHotelsPutcall(params4,headersForTripserviceputcall(),url1);
		 * validationforput(resp1); }
		 */
		Thread.sleep(4000);
		DBValidation_Txn(resp, "C");
			
	}	
		
 }
