package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class IdentityService_AddClient extends AccountsCommon_API
{
	@Test
	public void identityserviceaddclient() throws IOException, JSONException{	
		
		Response resp ;
  		resp =postCall("identityserviceaddclient", "");
		validation( resp, "identityserviceaddclient", "");
		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/
		
		
	}
	
}
