package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Promotional_Service_GenerateReferralLink_InvalidAuth extends AccountsCommon_API {
	@Test
	public void Promotional_Service_GenerateReferralLink() throws IOException, JSONException{
		Response resp ;		
		resp =postCall("Promotional_Service_GenerateReferralLink_InvalidAuth", "");
		validationjwt( resp, "Promotional_Service_GenerateReferralLink_InvalidAuth", "");

		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/


	}
}
