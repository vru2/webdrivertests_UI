package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Flyin_Accounts_SignIn extends AccountsCommon_API
{
	@Test
	public void flyinSignIn() throws IOException, JSONException{
	
	Response resp ;		
	resp =postCall("flyinsignin", "");
	validation( resp, "flyinsignin", "");
	
}



}
