package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Flipkart_Accounts_service_GetUserDetails extends AccountsCommon_API
{
	@Test
	public void flipkartgetuserdetails() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Flipkart_Accounts_service_GetUserDetails", "");
		validation( resp, "Flipkart_Accounts_service_GetUserDetails", "");

		/*ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/

}
}
