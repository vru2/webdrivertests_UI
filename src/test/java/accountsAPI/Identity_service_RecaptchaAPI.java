package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Identity_service_RecaptchaAPI extends AccountsCommon_API
{
	@Test
	public void recpatch() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("Identity_service_RecaptchaAPI", "");
		validation( resp, "Identity_service_RecaptchaAPI", "");

	}
}
