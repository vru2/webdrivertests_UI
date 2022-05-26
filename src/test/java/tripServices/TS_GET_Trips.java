package test.java.tripServices;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GET_Trips extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void getTripService(){
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("qa2")) {
		Reporter.log("http://172.17.51.86:9031/trips?tripID=Q1902050303");
		Response resp=RestAssured.get("http://172.17.51.86:9031/trips?tripID=Q1902050303");
		if(resp.statusCode()==200){
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("trip_ref"), true ,"Response boday contains tripId");
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
		} /*
			 * else if(Host.equalsIgnoreCase("www")) {
			 * Reporter.log("http://trip-service.cltp.com:9001/trips?tripID=190401816972");
			 * Response resp=RestAssured.get(
			 * "http://trip-service.cltp.com:9001/trips?tripID=190401816972");
			 * if(resp.statusCode()==200){ ResponseBody body= resp.getBody(); String
			 * bodyAsString = body.asString();
			 * Assert.assertEquals(bodyAsString.contains("trip_ref"), true
			 * ,"Response boday contains tripId"); Reporter.log(bodyAsString); }else{
			 * Reporter.log("Status code : " + resp.statusCode()); assertTrue(false); } }
			 */else if(Host.equalsIgnoreCase("dev")) {
			Reporter.log("http://172.17.32.12:9031/trips?tripID=Q1904255576");
			Response resp=RestAssured.get("http://172.17.32.12:9031/trips?tripID=Q1904255576");
			if(resp.statusCode()==200){
				ResponseBody body= resp.getBody();
				String bodyAsString = body.asString();
				Assert.assertEquals(bodyAsString.contains("trip_ref"), true ,"Response boday contains tripId");
				Reporter.log(bodyAsString);
			}else{
				Reporter.log("Status code : " + resp.statusCode());
				assertTrue(false);
			}
		}
	}

}
