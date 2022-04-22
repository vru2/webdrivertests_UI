package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Promotional_Service_ValidatereferralLink extends AccountsCommon_API {
	@Test
	public void Promotional_Service_ValidatereferralLink() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Promotional_Service_ValidatereferralLink", "");
		validation( resp, "Promotional_Service_ValidatereferralLink", "");

		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/


	}
}
