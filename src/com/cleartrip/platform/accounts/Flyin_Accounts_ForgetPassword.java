package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Flyin_Accounts_ForgetPassword extends AccountsCommon_API
{
	@Test
	public void flyinforgetpassword() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("flyinforgetpassword", "");
		validation( resp, "flyinforgetpassword", "");
		

			

	}


}
