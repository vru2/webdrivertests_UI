package test.java.  tripServices;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_POST_Pahcommqueue extends TripserviceCommon {
	@Test(groups={"Regression"})
	public void postPahcommqueue() throws IOException, InterruptedException{
		String url=Service_Url("TRIPSERVICE_GET_PAHCOMMQUEUE");
		System.out.println(url);
		Response resp=RestAssured.post(url);
		System.out.println(resp.asString());
		if(resp.statusCode()==200){
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("trip_ref"), true ,"Response boday contains trip_ref");
			Assert.assertEquals(bodyAsString.contains("contact_email"), true ,"Response boday contains contact_email");
			Assert.assertEquals(bodyAsString.contains("hotel_id"), true ,"Response boday contains hotel_id");
			Assert.assertEquals(bodyAsString.contains("active"), true ,"Response boday contains active");
			Assert.assertEquals(bodyAsString.contains("trip_name"), true ,"Response boday contains trip_name");
			Reporter.log(bodyAsString);
		}else{
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
		
	}

}
