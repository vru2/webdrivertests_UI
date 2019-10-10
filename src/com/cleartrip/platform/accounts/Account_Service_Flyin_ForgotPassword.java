package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_Flyin_ForgotPassword extends AccountsCommon_API
{
	@Test
	public void flyinforgetpasswordV2() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("flyinforgetpasswordV2", "");
		validation( resp, "flyinforgetpasswordV2", "");

		
	}


}
