package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import junit.framework.Assert;

public class CFW_Active_status extends AccountsCommon_API
{

	@Test
	public void cfw_active_status() throws IOException, JSONException{

		Response resp ;		
		String Type = null;
		resp =postCall("cfw_active_status", "");
		//validation( resp, "cfw_active_status", "");
		String ReponseStr = resp.body().asString();
		Reporter.log("Response body "+Type +" : "+ resp.body().asString());
		
		int statusCode = resp.getStatusCode();
		
		Reporter.log("statusCode: " + statusCode);
		JsonPath jsonPathEvaluator = resp.jsonPath();
		if(statusCode!=401) {
			Assert.assertTrue(false);
		}
		
		if(!ReponseStr.contains("Weak password used.")){
		}/*
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/
	}
}