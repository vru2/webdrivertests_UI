package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_GetuserdetailsbyAPIKey extends AccountsCommon_API
{
	@Test
	public void Account_Service_companysearch() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Account_Service_GetuserdetailsbyAPIKey", "");
		validation( resp, "Account_Service_GetuserdetailsbyAPIKey", "");

		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/


	}
}
