package tripServices;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TS_POST_GetAllTrips extends TripserviceCommon {
	
	
	@Test(groups={"Regression"})
	public void airPostCall() throws Exception{
		Response resp;
		String url =  Service_Url("TRIPSERVICE_GETALL_TRIPS");
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
	    resp=TripserviceGetAllTrips(params_getalltrip,headersForTripservicepostcall(),url);
		} else if(Host.equalsIgnoreCase("dev")) {
		    resp=TripserviceGetAllTrips(params_getalltrip,headersForTripservicepostcall(),url);
			}
		else{
			resp=TripserviceGetAllTrips(params_getalltrip_prod,headersForTripservicepostcall(),url);
		    Thread.sleep(3000);
		}
	    validationforgetalltrips(resp);
	}
}
