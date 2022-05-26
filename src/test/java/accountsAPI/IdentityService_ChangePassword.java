package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class IdentityService_ChangePassword extends AccountsCommon_API
{

	@Test
	public void IdentityService_ChangePassword() throws IOException,JSONException
	{
		Response resp;
		resp = postCall("IdentityService_ChangePassword","");
		
		validation_user_update(resp,"IdentityService_ChangePassword","");
				
		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		System.out.println(resp.statusCode());*/
		
	}
}
