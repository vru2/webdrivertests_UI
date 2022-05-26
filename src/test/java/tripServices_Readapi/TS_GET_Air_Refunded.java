package test.java.tripServices_Readapi;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_Air_Refunded extends TripserviceCommon{
	@Test(groups={"Regression"})
	public void getAirRefunded() throws Exception{
	String url=tsendpoint+airrefundedrealtime;
	String url1=tsendpoint+airrefundedarchived;
	Reporter.log("Fetching from realtime db");
	Reporter.log(url);
	Response resp=RestAssured.get(url);
	System.out.println(resp.asString());
	if(resp.statusCode()==200){
		ResponseBody body= resp.getBody();
		String bodyAsString = body.asString();
		Assert.assertNotNull(resp);
		Reporter.log(bodyAsString);
	}else{
		Reporter.log("Status code : " + resp.statusCode());
		assertTrue(false);
	}
		Reporter.log("Fetching from archived db");
		Reporter.log(url1);
		Response resp1=RestAssured.get(url1);
		System.out.println(resp1.asString());
		if(resp1.statusCode()==200){
			ResponseBody body= resp1.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(resp1);
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp1.statusCode());
			assertTrue(false);
		}
	}
	
}

