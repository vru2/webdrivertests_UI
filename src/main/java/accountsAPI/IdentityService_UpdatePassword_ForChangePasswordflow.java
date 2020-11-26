package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class IdentityService_UpdatePassword_ForChangePasswordflow extends AccountsCommon_API
{

	
	@Test
	public void ForChangePasswordflow() throws IOException, JSONException{
		Response resp ;		
		resp =putCall("IdentityService_UpdatePassword_ForChangePasswordflow", "");
		validation_user_update_MobileOTP( resp, "IdentityService_UpdatePassword_ForChangePasswordflow", "");
		
	/*	ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());		*/
		
	}
}
