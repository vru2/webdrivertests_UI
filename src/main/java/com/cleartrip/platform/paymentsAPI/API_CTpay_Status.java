// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CTpay_Status extends API_PaymentCommon
{
	@Test
	public void PaymentCtPayStatus() throws Exception{
		Response resp ;		
		resp = rearchCtPay("STATUS","");	
		validation("STATUS", resp);	
		}
}