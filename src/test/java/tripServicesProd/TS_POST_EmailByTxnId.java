package test.java.tripServicesProd;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_POST_EmailByTxnId extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void getEmail() throws IOException, InterruptedException{
		String url="http://trip-service.cltp.com:9001/trips/email/178615328";
		Response resp=RestAssured.post(url);
		System.out.println(resp.asString());
		if(resp.statusCode()==200){
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(bodyAsString.contains("email_trackers"));
			Assert.assertEquals(bodyAsString.contains("trip_ref"), true ,"Response boday contains trip_ref");
			Assert.assertEquals(bodyAsString.contains("hotel_id"), true ,"Response boday contains hotel_id");
			Assert.assertEquals(bodyAsString.contains("email_type"), true ,"Response boday contains email_type");
			Assert.assertEquals(bodyAsString.contains("txn_id"), true ,"Response boday contains txn_id");
			Assert.assertEquals(bodyAsString.contains("voucher_number"), true ,"Response boday contains voucher_number");
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
		
	}


}
