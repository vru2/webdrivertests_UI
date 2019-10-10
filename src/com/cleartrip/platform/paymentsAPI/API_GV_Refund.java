// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_GV_Refund extends API_PaymentCommon
{
	@Test
	public void API_GVRefund()  {
		Response resp ;		
		resp = rearchGV("REFUND","");	
		validation("GV_REFUND", resp);
		}
	
}