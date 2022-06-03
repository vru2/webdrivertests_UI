package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Flyin_Accounts_Signup extends AccountsCommon_API
{
	@Test
	public void flyinSignup() throws IOException, JSONException{
	
	Response resp ;		
	resp =postCall("flyinsignup", "");
	validation( resp, "flyinsignup", "");
/*
	ResponseBody body = resp.getBody();
	System.out.println("Response of API is:" + body.asString());*/
	
}
	
}
