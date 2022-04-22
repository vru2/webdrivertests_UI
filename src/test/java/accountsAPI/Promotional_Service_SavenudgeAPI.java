package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Promotional_Service_SavenudgeAPI  extends AccountsCommon_API  {
	@Test
	public void Promotional_Service_SavenudgeAPI() throws IOException, JSONException{
		Response resp ;		
		resp =putCall("Promotional_Service_SavenudgeAPI", "");
		validation( resp, "Promotional_Service_SavenudgeAPI", "");

	/*	ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/


	}
}
