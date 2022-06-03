// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_Promo_CashBackDetails extends API_PaymentCommon1
{
	@Test
	public void PromoCashBack()  {
		Response resp ;		
		resp = rearchWallet("CASHBACK_DETAILS","");	
		validation("wallet_CASHBACK_DETAILS", resp);
		}
	
}