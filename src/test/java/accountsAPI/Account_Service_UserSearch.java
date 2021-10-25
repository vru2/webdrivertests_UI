package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Account_Service_UserSearch extends AccountsCommon_API
{
	@Test
	public void flyinusersearch() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("flyinusersearchV1", "");
		validation( resp, "flyinusersearch", "");


	}
}
