package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import junit.framework.Assert;

public class Identtiyservice_get_tokenthroughrefreshToken extends AccountsCommon_API
{

	@Test
	public void identtiyservice_get_tokenthroughCode() throws IOException, JSONException{

		Response resp ;		
		String Type = null;
		String url="https://qa2.cleartrip.com/ctauth/token";
		resp =postCallIdentitygettokenthroughrefreshtoken(url);
		
		String ReponseStr = resp.body().asString();
		Reporter.log("Response body "+Type +" : "+ resp.body().asString());

		int statusCode = resp.getStatusCode();
		System.out.println(statusCode);

		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();
		if(statusCode!=200) {
			Assert.assertTrue(false);
		}

		if(!ReponseStr.contains("Other than 401 status is displayed")){
		}
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		
		//String status = jsonPathEvaluator.getString("status");
		String token_type = jsonPathEvaluator.getString("token_type");
		
		/*if(!status.contains("UNAUTHORIZED")) {
			Assert.assertTrue(false);						
		}*/
		if(!token_type.contains("access_token")) {
			Assert.assertTrue(false);						
		}
		
	}
}
