package test.java.  tripServices;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TS_ProductType_Userid_Train extends TripserviceCommon{
	@Test
	public void producttype_userid_train() {
		Response resp;
		String url="http://172.17.51.86:9031/api/trips/TRAIN/41683432?startDateTime=2019-01-01T00:00:00.000+0530&endDateTime=2020-04-08T12:00:00.000+0530";
		Reporter.log(url);
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


