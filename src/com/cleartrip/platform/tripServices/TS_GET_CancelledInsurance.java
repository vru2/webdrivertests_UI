package com.cleartrip.platform.tripServices;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import javax.management.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_CancelledInsurance extends TripserviceCommon {
	
	@Test(groups={"Regression"})
	public void getCancelledInsurance() throws JSONException{
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
		Reporter.log("http://172.17.26.11:9031/trips/cancelledInsurances?date=08/07/2019");
		Response resp=RestAssured.given().
				      when().
				      log().all().
				      headers(headersForTripservicepostcall()).
				      get("http://172.17.26.11:9031/trips/cancelledInsurances?date=08/07/2019");
		    System.out.println(resp.asString());
			if(resp.statusCode()==200){
			Assert.assertNotNull("TRIP_REF");
			Assert.assertNotNull("TRANSACTION_TYPE");
			Assert.assertNotNull("SOURCE_TYPE");
			Assert.assertNotNull("REFUND_RECORD_ID");
			Assert.assertNotNull("REFUND_ID");
			Assert.assertNotNull("PRICING_ID");
			Assert.assertNotNull("AIR_BOOKING_TYPE");
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
		}else if(Host.equalsIgnoreCase("www")) {
			Reporter.log("http://trip-service.cltp.com:9001/trips/cancelledInsurances?date=08/07/2019");
			Response resp=RestAssured.get("http://trip-service.cltp.com:9001/trips/cancelledInsurances?date=08/07/2019");
			System.out.println(resp.asString());
			if(resp.statusCode()==200){
				ResponseBody body= resp.getBody();
				String bodyAsString = body.asString();
				Assert.assertNotNull("TRIP_REF");
				Assert.assertNotNull("TRANSACTION_TYPE");
				Assert.assertNotNull("SOURCE_TYPE");
				Assert.assertNotNull("REFUND_RECORD_ID");
				Assert.assertNotNull("REFUND_ID");
				Assert.assertNotNull("PRICING_ID");
				Assert.assertNotNull("AIR_BOOKING_TYPE");
				Reporter.log(bodyAsString);
			}else{
				Reporter.log("Status code : " + resp.statusCode());
				assertTrue(false);
			}
		}else if(Host.equalsIgnoreCase("dev")) {
			Reporter.log("http://172.17.32.12:9031/trips/cancelledInsurances?date=08/07/2019");
			Response resp=RestAssured.get("http://172.17.32.12:9031/trips/cancelledInsurances?date=08/07/2019");
			System.out.println(resp.asString());
			if(resp.statusCode()==200){
				ResponseBody body= resp.getBody();
				String bodyAsString = body.asString();
				Assert.assertNotNull("TRIP_REF");
				Assert.assertNotNull("TRANSACTION_TYPE");
				Assert.assertNotNull("SOURCE_TYPE");
				Assert.assertNotNull("REFUND_RECORD_ID");
				Assert.assertNotNull("REFUND_ID");
				Assert.assertNotNull("PRICING_ID");
				Assert.assertNotNull("AIR_BOOKING_TYPE");
				Reporter.log(bodyAsString);
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
