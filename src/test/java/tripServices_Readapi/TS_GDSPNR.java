package tripServices_Readapi;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_GDSPNR extends TripserviceCommon {
	Response resp;
	String url=tsendpoint+gdspnr;
	
	@Test
	public void gdspnr()
	{
		Reporter.log(url);
		resp=RestAssured.get(url);
		Reporter.log(resp.asString());
		System.out.println(resp.asString());
		if(resp.getStatusCode()==200)
		{
			ResponseBody body= resp.getBody();
		    String bodyAsString = body.asString();
		    Assert.assertNotNull(bodyAsString);
		    Assert.assertEquals(bodyAsString.contains("trip_ref"),true,"Response body contains trip_ref");
		}
		else {
			Reporter.log("Status code : " + resp.statusCode());
			assertTrue(false);
		}
	}

}
