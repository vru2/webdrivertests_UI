// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import java.io.IOException;
import java.util.Random;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_OLA_Promo extends API_PaymentCommon
{
	
	Random rand = new Random();
	int n = rand.nextInt(999);
	String PromoID = Integer.toString(n);
	
	@Test
	public void paymentOTP_Validate() throws Exception{
		Response resp ;		
		resp =  ola("Validate","", PromoID);	
		validation("OLAVALIDATE", resp);
	}
	
	
	
}
