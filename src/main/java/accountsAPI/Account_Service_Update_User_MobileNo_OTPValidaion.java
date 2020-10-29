package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_Update_User_MobileNo_OTPValidaion extends AccountsCommon_API
{
	@Test
	public void Account_Service_Update_User_MobileNo_OTPValidaion() throws IOException,JSONException
	{
		Response resp;
		resp = postCall("Account_Service_Update_User_MobileNo_OTPValidaion","");
		
		validation_user_update_MobileOTP(resp,"Account_Service_Update_User_MobileNo_OTPValidaion","");
		/*		
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		System.out.println(resp.statusCode());*/
		
	}
}
