package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Create_Traveller extends AccountsCommon_API {
	
	@Test
	public void createTraveller() throws IOException, JSONException{
		
		Response resp ;		
  		resp = postCall("createTraveller", "");
		validation(resp, "createTraveller", "");		
		}
	


}
