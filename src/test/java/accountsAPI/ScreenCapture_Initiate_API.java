package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class ScreenCapture_Initiate_API extends AccountsCommon_API
{

	@Test
	public void screencaptureInitiate() throws IOException, JSONException{	
		Response resp ;		
		resp =postCall("screencaptureInitiate", "");
		validation( resp, "screencaptureInitiate", "");

		ResponseBody body = resp.getBody();

		System.out.println("Response of API is:" + body.asString());



	}


}
