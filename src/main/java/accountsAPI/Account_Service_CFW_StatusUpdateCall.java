package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_CFW_StatusUpdateCall extends AccountsCommon_API
{
	@Test
	public void Account_Service_CFW_StatusUpdateCall() throws IOException, JSONException{
	
	Response resp ;		
	resp =postCall("Account_Service_CFW_StatusUpdateCall", "");
	validation( resp, "Account_Service_CFW_StatusUpdateCall", "");
	/*ResponseBody body = resp.getBody();
	System.out.println("Response of API is:" + body.asString());*/
}
}
