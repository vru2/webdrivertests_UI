package com.cleartrip.platform.tripServices;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_PUT_Trips extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void PutTrips() throws Exception{
	Response resp;
	String url =  Service_Url("TRIPSERVICE_PUT_TRIPS");
	resp=TripservicePutTrips(params_putTrips,headersForTripserviceputtripscall(),url);
	validation_puttrips(resp);
	}
	

}
