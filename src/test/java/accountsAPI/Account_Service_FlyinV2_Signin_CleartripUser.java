package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_FlyinV2_Signin_CleartripUser extends AccountsCommon_API
{


	@Test
	public void Account_Service_FlyinV2_Signin_CleartripUser() throws IOException, JSONException{
		
		Response resp ;		
		resp =postCall("flyinsigninV2_CleartripUser", "");
		validation( resp, "flyinsigninV2_CleartripUser", "");
		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/
		

}
}

