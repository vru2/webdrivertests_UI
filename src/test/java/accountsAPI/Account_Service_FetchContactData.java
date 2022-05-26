package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Account_Service_FetchContactData extends AccountsCommon_API
{
	@Test
	public void Account_Service_fetchcontactdata() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("Account_Service_FetchContactData", "");
		validation( resp, "Account_Service_FetchContactData", "");

	}
}
