package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Accounts_Service_fetchuserdetails_withReferer extends AccountsCommon_API
{
	@Test
	public void Account_Service_fetchuser() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Accounts_Service_fetchuserdetails_withReferer", "");
		validation( resp, "Accounts_Service_fetchuserdetails_withoutReferer", "");

		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());


	}

}
