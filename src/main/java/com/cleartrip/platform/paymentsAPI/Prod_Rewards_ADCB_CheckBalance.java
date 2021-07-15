// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Prod_Rewards_ADCB_CheckBalance extends API_PaymentCommon
{
	@Test
	public void ADCB_Balance()  {
		Response resp ;		
		resp = prodAPIs("Rwd_ADCB_Balance","");	
		validation_Prod("Rwd_ADCB_Balance", resp);
		}
	
}