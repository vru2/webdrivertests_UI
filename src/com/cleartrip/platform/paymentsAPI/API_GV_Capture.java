// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_GV_Capture extends API_PaymentCommon{
	
	@Test
	public void API_GVCapture()  {
		Response resp ;		
		resp = rearchGV("CAPTURE","");	
		validation("GV_CAPTURE", resp);
		}
	
}