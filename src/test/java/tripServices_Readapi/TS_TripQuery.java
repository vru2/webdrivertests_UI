package test.java.tripServices_Readapi;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_TripQuery extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void postTripQuery() throws IOException{
		Response resp;
		String url =  tsendpoint+updatequery;
			Reporter.log(url);
			resp=TripserviceTripQuery(tripquery_qa,headersForTripservicepostgraphql(),url);
			System.out.println(resp.asString());
			validationforgraphql(resp);
	}
}
