package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Flyin_Regression_Partner_Social_signup extends AccountsCommon_API
{

	@Test
	
	public void Flyinsocialsignup_flyinregression() throws IOException, JSONException
	{
	Response resp;
	resp = postCall("flyinsocialsignupV2_flyinregression", "");
	validation(resp, "flyinsocialsignupV2_flyinregression","");
	
	/*ResponseBody body = resp.getBody();
	System.out.println("Response of API is:" + body.asString());*/
	
	}
}
