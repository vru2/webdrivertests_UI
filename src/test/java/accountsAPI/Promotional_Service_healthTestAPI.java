package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Promotional_Service_healthTestAPI extends AccountsCommon_API{

	@Test
	public void promotioanl_Service_healthtest() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Promotional_Service_healthTestAPI", "");
		validation( resp, "Promotional_Service_healthTestAPI", "");

		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());


	}
}
