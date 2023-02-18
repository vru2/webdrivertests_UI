package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import junit.framework.Assert;

public class Deposit_Account_Link_V2 extends AccountsCommon_API
{
	@Test
	public void link_depositaccount() throws IOException, JSONException{

		Response resp ;		
		String Type = null;
		String url="http://accounts.cltp.com:9001/account/deposit_account/link/v2";
		resp =linkdepositaccount(url);
		
		String ReponseStr = resp.body().asString();
		Reporter.log("Response body "+Type +" : "+ resp.body().asString());

		int statusCode = resp.getStatusCode();
		System.out.println(statusCode);

		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();
		if(statusCode!=409) {
			Assert.assertTrue(false);
		}

		if(!ReponseStr.contains("Other than 409 status is displayed")){
		}
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		
		String status = jsonPathEvaluator.getString("status");
		String message = jsonPathEvaluator.getString("message");
		if(!status.contains("failed")) {
			Assert.assertTrue(false);						
		}
		if(!message.contains("RecordNotUnique"))
		{
			Assert.assertTrue(false);
		}
	}
	

}
