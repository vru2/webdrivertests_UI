package tripServices;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_ProductType_Userid_Hotel extends TripserviceCommon{
	@Test
	public void producttype_userid_hotel() {
		Response resp;
		String url="http://172.17.26.11:9031/api/trips/HOTEL/41683432?startDateTime=2020-03-01T00:00:00.000+0530&endDateTime=2020-04-08T12:00:00.000+0530";
		resp=RestAssured.get(url);
		System.out.println(resp.asString());
		if(resp.statusCode()==200)
		{
			Reporter.log("Test case passed");
			Reporter.log(resp.body().asString());
			Reporter.log("Status code is :: " + resp.statusCode());
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			Assert.assertNotNull(bodyAsString);
		}else{
			Reporter.log("Status code is :: " + resp.statusCode());
			assertTrue(false);
		}
		}
}


