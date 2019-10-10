// Framework - Cleartrip Automation
// Author - Saloni Gothi

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reporting extends API_PaymentCommon
{
	
	@Test
	public void reportingAPI () throws Exception{
		Response resp ;		
		resp = Reporting ("Reporting","");	
		validation("Reporting", resp);
								
	}
	
	}
