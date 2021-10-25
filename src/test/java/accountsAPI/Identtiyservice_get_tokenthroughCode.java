package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import junit.framework.Assert;

public class Identtiyservice_get_tokenthroughCode  extends AccountsCommon_API
{

	@Test
	public void identtiyservice_get_tokenthroughCode() throws IOException, JSONException{

		Response resp ;		
		String Type = null;
		String url="https://qa2.cleartrip.com/ctauth/token";
		resp =postCallIdentitygettokenthroughcode(url);
		
		
		String ReponseStr = resp.body().asString();
		Reporter.log("Response body "+Type +" : "+ resp.body().asString());

		int statusCode = resp.getStatusCode();
		System.out.println(statusCode);

		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();
		if(statusCode!=401) {
			 
			Assert.assertTrue(false);
		}

		if(!ReponseStr.contains("Other than 401 status is displayed")){
		}
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		
		String status = jsonPathEvaluator.getString("message");
		
		if(!status.contains("Invalid RedirectUri or Client!")) {
			Assert.assertTrue(false);						
		}
		
	}
}
