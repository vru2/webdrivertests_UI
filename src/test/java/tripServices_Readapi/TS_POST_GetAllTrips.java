package test.java.tripServices_Readapi;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_POST_GetAllTrips extends TripserviceCommon {
	
	
	@Test(groups={"Regression"})
	public void airPostCall() throws Exception{
		Response resp;
		String url =  tsendpoint+triplist;
			Reporter.log(url);
			resp=TripserviceGetAllTrips(params_getalltrip,headersForTripservicepostcall(),url);
	    System.out.println(resp.asString());
	    validationforgetalltrips(resp);

 }
}