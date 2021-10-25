package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class IdentityService_Signin_FlyinUser extends AccountsCommon_API
{
	@Test
	public void IdentityService_Signin() throws IOException, JSONException{	
		
		Response resp ;
  		resp =postCall("IdentityService_Signin_FlyinUser", "");
		validation( resp, "IdentityService_Signin_FlyinUser", "");
		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		*/
		
	}
}
