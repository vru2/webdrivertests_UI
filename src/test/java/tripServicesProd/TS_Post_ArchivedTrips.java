package test.java.tripServicesProd;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_Post_ArchivedTrips extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void upcomingTripsPostCall() throws Exception{
		Response resp;
		String url ="http://trip-service.cltp.com:9001/api/trips?size=20&dbFetchType=ARCHIVED";
			resp=TripserviceGetUpcomingTrips(params_getupcomingtrip_prod,headersForTripservicepostcall(),url);
		    Thread.sleep(3000);
	    validationforgetalltrips(resp);
	    System.out.println(resp.asString());
	}

}
