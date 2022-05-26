package test.java.tripServicesProd;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_PUT_Trips extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void PutTrips() throws Exception{
	Response resp;
	String url ="http://trip-service.cltp.com:9001/trips";
	resp=TripservicePutTrips(params_putTrips,headersForTripserviceputtripscall(),url);
	validation_puttrips(resp);
	}
	

}
