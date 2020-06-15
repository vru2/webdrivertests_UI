package tripServices;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Eventbus_API extends TripserviceCommon{
	
	String url="http://172.17.26.11:9070/event/publish";
	Response resp;
	String s=RandomStringUtils.randomNumeric(3);
	String params="{\"eventContent\":\"test"+s+"\",\"eventName\": \"test\",\"eventReference\":\"test\"}";
	@Test
	public void eventbus() {
		Reporter.log(url);
		resp=RestAssured.given().
		 when().
		 log().all().
		 body(params).
		 headers("Accept","application/json").
		 headers("Content-Type","application/json").
		 post(url);
	System.out.println(resp.asString());
	if(resp.statusCode()==200) {
		Reporter.log(resp.asString());
	    Reporter.log("Status code " + resp.statusCode());
		ResponseBody body= resp.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("data"), true ,"Response boday contains data");
		Assert.assertNotNull("data");
		Assert.assertEquals(bodyAsString.contains("success"), true ,"Response boday contains success");
		Assert.assertNotNull("success");
	}
		 
	
	}
}
