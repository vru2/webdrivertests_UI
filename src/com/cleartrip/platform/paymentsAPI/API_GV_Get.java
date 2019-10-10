// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_GV_Get extends API_PaymentCommon
{
	@Test
	public void API_GVGet()  {
		Response resp ;		
		resp = rearchGV("GET","");	
		validation("GV_GET", resp);
		}
	
}