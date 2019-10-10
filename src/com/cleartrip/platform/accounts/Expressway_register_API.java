// Framework - Cleartrip Automation
// Author - Sphurti


package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Expressway_register_API extends AccountsCommon_API{
	
	@Test
	public void registerUser() throws IOException, JSONException{
		
		Response resp ;		
  		resp = postCall("registerUser", "");
		validation(resp, "registerUser", "");	
		}
}
