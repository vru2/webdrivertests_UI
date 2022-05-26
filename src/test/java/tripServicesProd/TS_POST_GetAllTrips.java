package test.java.tripServicesProd;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_POST_GetAllTrips extends TripserviceCommon {
	
	
	@Test(groups={"Regression"})
	public void airPostCall() throws Exception{
		Response resp;
		String url ="http://trip-service.cltp.com:9001/api/trips?size=20";
			resp=TripserviceGetAllTrips(params_getalltrip_prod,headersForTripservicepostcall(),url);
		    Thread.sleep(3000);
	    validationforgetalltrips(resp);
	}
}
