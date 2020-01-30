package tripServices;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_TripQuery extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void postTripQuery() throws IOException{
		Response resp;
		String url =  Service_Url("TRIPSERVICE_POST_TRIPQUERY");
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
			resp=TripserviceTripQuery(tripquery_qa,headersForTripservicepostgraphql(),url);
			System.out.println(resp.asString());
			validationforgraphql(resp);
		}
		if(Host.equalsIgnoreCase("www")){
			resp=TripserviceTripQuery(tripquery_prod,headersForTripservicepostgraphql(),url);
			System.out.println(resp.asString());
			validationforgraphql(resp);
		}
		if(Host.equalsIgnoreCase("dev")){
			resp=TripserviceTripQuery(tripquery_qa,headersForTripservicepostgraphql(),url);
			validationforgraphql(resp);
		}
	}
}
