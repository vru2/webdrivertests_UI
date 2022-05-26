package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_LinkDepositAccount extends AccountsCommon_API
{
	@Test
	public void linkdepositaccount() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("Account_Service_LinkDepositAccount", "");
		validation_Linkdepositaccount( resp, "Account_Service_LinkDepositAccount", "");
		
		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());	
		*/

	}
}
