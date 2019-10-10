package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Userprofile_ExternalAPI_MobileApp extends AccountsCommon_API 
{

	@Test
	public void userprf_extAPI_Mobileapp() throws IOException, JSONException{
		Response resp ;		
		resp =postCall("userprofile_externalAPI_Mobileapp", "");
		System.out.println(resp.asString());
		validation( resp, "userprofile_externalAPI_Mobileapp", "");

		
	}



}
