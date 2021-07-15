// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_RevertPromo extends API_PaymentCommon
{
	@Test
	public void Wallet_REVERTPROMO()  {
		Response resp ;		
		resp = rearchWallet("REVERTPROMO","");	
		validation("wallet_REVERTPROMO", resp);
		}
	
}