package test.java.  tripServices;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TS_GET_Cancelleddetails extends TripserviceCommon {
	
	@Test(groups={"Regression"})
	public void getCancelledDetails(){
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
			Reporter.log("http://trip-service-api.cltp.com:9001/trips/cancelledDetails?date=06-10-2022&productType=air");
			Response resp = RestAssured.given().
					when().
					log().all().
					headers(headersForTripservicepostcall()).
					get("http://trip-service-api.cltp.com:9001/trips/cancelledDetails?date=06-10-2022&productType=air");
			System.out.println(resp.asString());
			if (resp.statusCode() == 200) {
				Assert.assertNotNull("cancel_date");
			} else {
				Reporter.log("Status code : " + resp.statusCode());
				assertTrue(false);
			}
		}
	}
	
	public HashMap<String, Object> headersForTripservicepostcall(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		return headers;

	}

}
