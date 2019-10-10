package com.cleartrip.platform.accounts;

import java.io.IOException;
import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;



public class Accounts_Health_Test_Url extends AccountsCommon_API
{
	@Test
	public void Healthtest() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("AcctHealthtestURL", "");
		validation( resp, "AcctHealthtestURL", "");
		
	}


}
