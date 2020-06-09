package tripServices;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_GraphQL extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void postGraphQL() throws IOException{
		Response resp;
		String url =  Service_Url("TRIPSERVICE_POST_GRAPHQL");
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
			resp=TripserviceGraphQL(graphql_qa,headersForTripservicepostgraphql(),url);
			System.out.println(resp.asString());
			validationforgraphql(resp);
		}
		/*
		 * if(Host.equalsIgnoreCase("www")){
		 * resp=TripserviceGraphQL(graphql_prod,headersForTripservicepostgraphql(),url);
		 * System.out.println(resp.asString()); validationforgraphql(resp); }
		 */
		if(Host.equalsIgnoreCase("dev")){
			resp=TripserviceGraphQL(graphql_qa,headersForTripservicepostgraphql(),url);
			validationforgraphql(resp);
		}
	}
}
