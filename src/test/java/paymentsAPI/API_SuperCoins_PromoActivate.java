// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_SuperCoins_PromoActivate extends API_PaymentCommon1 {
	
	@Test
	public void FK_SuperCoins_ActivatePromo() throws Exception{
		Response resp ;		
		resp = rearchWallet("SuperCoins_ActivatePromo","");
		validation("SuperCoins_ActivatePromo", resp);
	}
}
