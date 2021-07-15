// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_GV extends API_PaymentCommon
{
	@Test
	public void paymentGV_API() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPayment("GV","");	
		validation("GV", resp);
		}
}