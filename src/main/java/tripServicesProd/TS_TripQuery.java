package tripServicesProd;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_TripQuery extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void postTripQuery() throws IOException{
		Response resp;
		String url = "http://trip-service.cltp.com:9001/trips/query";
			resp=TripserviceTripQuery(tripquery_prod,headersForTripservicepostgraphql(),url);
			System.out.println(resp.asString());
			validationforgraphql(resp);
	}
}
