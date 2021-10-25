package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Account_Service_PWA_SignIn_API extends AccountsCommon_API
{
	@Test
	public void Account_Service_PWA_SignIn_API() throws IOException, JSONException{
	
	Response resp ;		
	resp =postCall("Account_Service_PWA_SignIn_API", "");
	validation( resp, "Account_Service_PWA_SignIn_API", "");
	
}

}
