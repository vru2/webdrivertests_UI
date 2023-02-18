package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Flipkart_Accounts_service_JWTtoken_Cleartrip extends AccountsCommon_API 
{
	@Test
	public void flipkartjwttoken() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Flipkart_Accounts_service_JWTtoken_Cleartrip", "");
		validationjwt( resp, "Flipkart_Accounts_service_JWTtoken_Cleartrip", "");

		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());

}
}
