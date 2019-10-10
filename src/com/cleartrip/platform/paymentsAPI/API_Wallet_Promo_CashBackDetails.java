// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_Promo_CashBackDetails extends API_PaymentCommon
{
	@Test
	public void PromoCashBack()  {
		Response resp ;		
		resp = rearchWallet("CASHBACK_DETAILS","");	
		validation("wallet_CASHBACK_DETAILS", resp);
		}
	
}