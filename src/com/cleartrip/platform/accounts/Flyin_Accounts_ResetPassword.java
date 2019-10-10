package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Flyin_Accounts_ResetPassword extends AccountsCommon_API
{
	@Test
	public void flyinresetpassword() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("flyinresetpassword", "");
		validation( resp, "flyinresetpassword", "");


	}

}
