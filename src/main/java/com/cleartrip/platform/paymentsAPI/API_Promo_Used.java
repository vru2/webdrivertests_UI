// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Promo_Used extends API_PaymentCommon
{
	@Test
	public void PaymentCtPayCreate() {
		Response resp ;		
		resp = payGet("PromoUsed","");	
		validation("PromoUsed", resp);	
		}
}