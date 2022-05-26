package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_UserExistence_withID extends AccountsCommon_API{
	@Test
	public void Account_Service_userexistence() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Account_Service_UserExistence_withID", "");
		validation( resp, "Account_Service_UserExistence_withEmailIDandDomain", "");

		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
*/

	}
}
