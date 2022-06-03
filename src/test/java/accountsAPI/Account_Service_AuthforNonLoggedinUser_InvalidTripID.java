package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_AuthforNonLoggedinUser_InvalidTripID extends AccountsCommon_API
{
	@Test
	public void Account_Service_nonloginser() throws IOException, JSONException{
		Response resp ;		
		resp =putCall("Account_Service_AuthforNonLoggedinUser_InvalidTripID", "");
		validation_AppleRegister_NullEmail( resp, "Account_Service_AuthforNonLoggedinUser_InvalidTripID", "");
		
		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());		
		*/
	}


}
