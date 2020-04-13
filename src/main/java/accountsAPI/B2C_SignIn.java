package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class B2C_SignIn extends AccountsCommon_API
{

	@Test
	public void b2cSignIn() throws IOException, JSONException{
		Response resp ;		
		resp =postCall("b2csignin", "");
		System.out.println(resp.asString());
		validation( resp, "b2csignin", "");
/*
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/
		
		
	}
}
