package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_CreateWalletAPI extends AccountsCommon_API
{
	@Test
	public void Account_Service_CreateWalletAPI() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("Account_Service_CreateWalletAPI", "");
		validation( resp, "Account_Service_CreateWalletAPI", "");
		
		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/
		

	}
}
