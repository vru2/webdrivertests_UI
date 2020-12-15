package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_AppleRegister_NullEmail extends AccountsCommon_API
{

	@Test
	public void Account_Service_AppleRegister_NullEmail() throws IOException,JSONException
	{

		Response resp;
		resp = postCall("Account_Service_AppleRegister_NullEmail","");

		validation_AppleRegister_NullEmail(resp,"Account_Service_AppleRegister_NullEmail","");

		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		System.out.println(resp.statusCode());*/

	}
}
