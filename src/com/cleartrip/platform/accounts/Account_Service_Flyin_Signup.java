package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import com.sun.jna.Function.PostCallRead;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import groovy.json.JsonException;


public class Account_Service_Flyin_Signup extends AccountsCommon_API
{
	@Test
	public void signupV1() throws IOException,JSONException
	{
		Response resp;
		resp = postCall("flyinsignupV1","");
		
		validation(resp,"flyinsignup","");
				
	}
	

}


