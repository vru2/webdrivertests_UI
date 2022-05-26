// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_SuperCoins_CheckBalance extends API_PaymentCommon1 {
	
	@Test
	public void FK_SuperCoins_CheckBalance() throws Exception{
		Response resp ;		
		resp = payPost("SuperCoins_CheckBalance","");
		validation("SuperCoins_CheckBalance", resp);
	}
}
