package test.java.  tripServices;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class AppNotification extends TripserviceCommon {
	Response resp;
	String url="http://app-notification-api.cltp.com:9001/notification";
	String params="{ \"ccId\": \"5cb5c2fbacfa9d0c0f0adf54\", \"message\": { \"content\": \"Tester notify\", \"title\": \"application_notification\" }, \"tags\": [ \"L2I\" ], \"to\": { \"emails\": [ \"vaishali.baronia@cleartrip.com|__g0be06471c8df4606a03170618905e591\" ], \"objectIds\": [ \"5d16a777b32c4d5119129e98\" ] }}";
	@Test
	public void appnotification() {
		Reporter.log(url);
		resp=RestAssured.given().
				when().
				log().all().
				body(params).
				headers("Accept","*/*").
				headers("Content-Type","application/json").
				post(url);
		   System.out.println(resp.asString());
		
		if(resp.statusCode()==200) {
			Reporter.log(resp.asString());
		    Reporter.log("Status code " + resp.statusCode());
			ResponseBody body= resp.getBody();
			String bodyAsString = body.asString();
			 Assert.assertEquals(bodyAsString.contains("status"), true ,"Response boday contains status");
			 Assert.assertNotNull("status");
			 Assert.assertEquals(bodyAsString.contains("notificationId"), true ,"Response boday contains notificationId");
			 Assert.assertNotNull("notificationId");
			 Assert.assertEquals(bodyAsString.contains("message"), true ,"Response boday contains Message is enqueued");
			 Assert.assertNotNull("message");
		}
				
	}

}
