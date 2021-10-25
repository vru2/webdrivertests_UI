package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class IdentityService_ctAuth_noLoginCookie extends AccountsCommon_API
{


	@Test
	public void IdentityService_ctAuth_noLoginCookie() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("IdentityService_ctAuth_noLoginCookie", "");
		validation( resp, "IdentityService_ctAuth_noLoginCookie", "");

		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());


	}
}

