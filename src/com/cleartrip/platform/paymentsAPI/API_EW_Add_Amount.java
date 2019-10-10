// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_Add_Amount extends API_PaymentCommon
{
	@Test
	public void Expressway_AddAmt() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("EW_AddAmt", "");	
		validation("EW_AddAmt", resp);
	}
}