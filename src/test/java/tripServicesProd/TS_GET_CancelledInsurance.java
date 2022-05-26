package test.java.tripServicesProd;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_CancelledInsurance extends TripserviceCommon {
	
	@Test(groups={"Regression"})
	public void getCancelledInsurance(){
			Reporter.log("http://trip-service.cltp.com:9001/trips/cancelledInsurances?date=12-12-2019");
			Response resp=RestAssured.get("http://trip-service.cltp.com:9001/trips/cancelledInsurances?date=12-12-2019");
			System.out.println(resp.asString());
			if(resp.statusCode()==200){
				ResponseBody body= resp.getBody();
				String bodyAsString = body.asString();
				Assert.assertNotNull("refund_id");
				Reporter.log(bodyAsString);
			}else{
				Reporter.log("Status code : " + resp.statusCode());
				assertTrue(false);
			}
		}
	public HashMap<String, Object> headersForTripservicepostcall(){
		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		return headers;

	}

}
