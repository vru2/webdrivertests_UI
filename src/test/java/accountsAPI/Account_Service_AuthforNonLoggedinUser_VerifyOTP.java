package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_AuthforNonLoggedinUser_VerifyOTP extends AccountsCommon_API
{

	@Test
	public void Account_Service_nonloginser() throws IOException, JSONException{
		Response resp ;		
		resp =putCall("Account_Service_AuthforNonLoggedinUser_VerifyOTP", "");
		validation_AppleRegister_NullEmail( resp, "Account_Service_AuthforNonLoggedinUser_VerifyOTP", "");
		
		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());	*/	
		
	}
}
