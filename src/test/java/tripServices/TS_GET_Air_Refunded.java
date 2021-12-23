package tripServices;

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
	String Host = common.value("host");
	if(Host.equalsIgnoreCase("qa2")) {
	Reporter.log("Fetching from realtime db");
	Reporter.log("http://172.17.51.86:9031/trips/air/refunded?status=D&fromDate=2019-10-29&toDate=2019-12-03&dbFetchType=REAL_TIME");
	Response resp=RestAssured.get("http://172.17.51.86:9031/trips/air/refunded?status=D&fromDate=2019-10-29&toDate=2019-12-03&dbFetchType=REAL_TIME");
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
			/*
			 * Reporter.log("Fetching from archived db"); Reporter.log(
			 * "http://172.17.51.86:9031/trips/air/refunded?status=D&fromDate=2019-02-02&toDate=2019-04-30&dbFetchType=ARCHIVED"
			 * ); Response resp1=RestAssured.get(
			 * "http://172.17.51.86:9031/trips/air/refunded?status=D&fromDate=2019-01-01&toDate=2019-04-30&dbFetchType=ARCHIVED"
			 * ); System.out.println(resp1.asString()); if(resp1.statusCode()==200){
			 * ResponseBody body= resp1.getBody(); String bodyAsString = body.asString();
			 * Assert.assertNotNull(resp1); Reporter.log(bodyAsString); }else{
			 * Reporter.log("Status code : " + resp1.statusCode()); assertTrue(false); }
			 */
		} /*
			 * else if(Host.equalsIgnoreCase("www")) {
			 * Reporter.log("Fetching from realtime db"); Reporter.log(
			 * "http://trip-service.cltp.com:9001/trips/air/refunded?status=D&fromDate=2019-09-05&toDate=2019-09-12&dbFetchType=REAL_TIME"
			 * ); Response resp=RestAssured.get(
			 * "http://trip-service.cltp.com:9001/trips/air/refunded?status=D&fromDate=2019-09-05&toDate=2019-09-12&dbFetchType=REAL_TIME"
			 * ); System.out.println(resp.asString()); if(resp.statusCode()==200){
			 * ResponseBody body= resp.getBody(); String bodyAsString = body.asString();
			 * Assert.assertNotNull(resp); Reporter.log(bodyAsString); }else{
			 * Reporter.log("Status code : " + resp.statusCode()); assertTrue(false); }
			 * Reporter.log("Fetching from archived db"); Reporter.log(
			 * "http://trip-service.cltp.com:9001/trips/air/refunded?status=D&fromDate=2019-02-02&toDate=2019-04-30&dbFetchType=ARCHIVED"
			 * ); Response resp1=RestAssured.get(
			 * "http://trip-service.cltp.com:9001/trips/air/refunded?status=D&fromDate=2019-02-02&toDate=2019-04-30&dbFetchType=ARCHIVED"
			 * ); System.out.println(resp1.asString()); if(resp1.statusCode()==200){
			 * ResponseBody body= resp1.getBody(); String bodyAsString = body.asString();
			 * Assert.assertNotNull(resp1); Reporter.log(bodyAsString); }else{
			 * Reporter.log("Status code : " + resp1.statusCode()); assertTrue(false); } }
			 */else if(Host.equalsIgnoreCase("dev")) {
		Reporter.log("Fetching from realtime db");
		Reporter.log("http://172.17.32.12:9031/trips/air/refunded?status=D&fromDate=2019-09-01&toDate=2019-09-12&dbFetchType=REAL_TIME");
		Response resp=RestAssured.get("http://172.17.32.12:9031/trips/air/refunded?status=D&fromDate=2019-09-01&toDate=2019-09-12&dbFetchType=REAL_TIME");
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
		Reporter.log("http://172.17.32.12:9031/trips/air/refunded?status=D&fromDate=2019-01-01&toDate=2019-04-30&dbFetchType=ARCHIVED");
		Response resp1=RestAssured.get("http://172.17.32.12:9031/trips/air/refunded?status=D&fromDate=2019-01-01&toDate=2019-04-30&dbFetchType=ARCHIVED");
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
	
}
