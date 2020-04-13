package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_Flyin_Signin extends AccountsCommon_API
{
	@Test
	public void flyinSignIn() throws IOException, JSONException{
	
	Response resp ;		
	resp =postCall("flyinsigninV1", "");
	validation( resp, "flyinsignin", "");
	
}



}
