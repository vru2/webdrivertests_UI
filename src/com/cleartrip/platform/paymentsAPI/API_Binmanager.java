// Framework - Cleartrip Automation
// Author - Saloni Gothi

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Binmanager extends API_PaymentCommon
{
	
	@Test
	public void binmanager () throws Exception{
		Response resp ;		
		resp = bin_manager ("Binmanager","");	
		validation("Binmanager", resp);
								
	}
	
	}
