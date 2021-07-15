// Framework - Cleartrip Automation
// Author - Varalakshmi

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Binmanager_Get_Card extends API_PaymentCommon
{
	
	@Test
	public void Get_Card () throws Exception{
		// Add credit card and issuer type mastercard
        Response resp;
		resp = BinDetail ("GetCard",Params_Singlebincard_Credit);	
		validationforbindetail("Get_Card", resp);
	
	}
}
