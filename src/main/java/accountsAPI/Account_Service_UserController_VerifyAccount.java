package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_UserController_VerifyAccount extends AccountsCommon_API
{
	@Test
	public void Account_Service_UserController_VerifyAccount() throws IOException, JSONException{
		Response resp ;		
		resp =putCall("Account_Service_UserController_VerifyAccount", "");
		validation( resp, "Account_Service_UserController_VerifyAccount", "");
		
	/*	ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());		*/
		
	}


}
