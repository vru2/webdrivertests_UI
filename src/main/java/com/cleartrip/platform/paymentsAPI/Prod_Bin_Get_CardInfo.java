// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Prod_Bin_Get_CardInfo extends API_PaymentCommon
{
	@Test
	public void Wallet_GetCurrency()  {
		Response resp ;		
		resp = prodAPIs("Bin_Get_CardInfo","");	
		validation_Prod("Bin_Get_CardInfo", resp);
		}
	
}