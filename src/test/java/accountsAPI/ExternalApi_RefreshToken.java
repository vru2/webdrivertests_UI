package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ExternalApi_RefreshToken extends AccountsCommon_API
{
	@Test
	public void refreshtoken() throws IOException, JSONException{
		
		Response resp ;		
  		resp = postCall("externalApi_Refreshtoken", "");
		validation(resp,"externalApi_Refreshtoken","");		
		}

}
