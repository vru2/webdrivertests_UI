// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_PromoUsed extends API_PaymentCommon1
{
	@Test
	public void Wallet_Promo_used()  {
		Response resp ;		
		resp = rearchWallet("PROMOUSED","");	
		validation("wallet_PROMOUSED", resp);
		}
	
}