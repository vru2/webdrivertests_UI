package test.java.tripServices;

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
		Reporter.log("http://172.17.51.86:9031/trips/cancelledDetails?date=06-10-2020&productType=air");
		Response resp=RestAssured.given().
				      when().
				      log().all().
				      headers(headersForTripservicepostcall()).
				      get("http://172.17.51.86:9031/trips/cancelledDetails?date=06-10-2020&productType=air");
		    System.out.println(resp.asString());
			if(resp.statusCode()==200){
			Assert.assertNotNull("cancel_date");
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
		} /*
			 * else if(Host.equalsIgnoreCase("www")) { Reporter.log(
			 * "http://trip-service.cltp.com:9001/trips/cancelledInsurances?date=12-12-2019"
			 * ); Response resp=RestAssured.get(
			 * "http://trip-service.cltp.com:9001/trips/cancelledInsurances?date=12-12-2019"
			 * ); System.out.println(resp.asString()); if(resp.statusCode()==200){
			 * ResponseBody body= resp.getBody(); String bodyAsString = body.asString();
			 * Assert.assertNotNull("refund_id"); Reporter.log(bodyAsString); }else{
			 * Reporter.log("Status code : " + resp.statusCode()); assertTrue(false); } }
			 */else if(Host.equalsIgnoreCase("dev")) {
			Reporter.log("http://172.17.32.12:9031/trips/cancelledInsurances?date=08/07/2019");
			Response resp=RestAssured.given().
				      when().
				      log().all().
				      headers(headersForTripservicepostcall()).
				      get("http://172.17.51.86:9031/trips/cancelledInsurances?date=09-26-2019");
		    System.out.println(resp.asString());
			if(resp.statusCode()==200){
			Assert.assertNotNull("refund_id");
			}else{
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
