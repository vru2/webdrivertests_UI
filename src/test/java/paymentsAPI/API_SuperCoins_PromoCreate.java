// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_SuperCoins_PromoCreate extends API_PaymentCommon1 {
	
	Random rand = new Random();
	int n = rand.nextInt(9999999);
	String tripID1 =Integer.toString(n);
	String tripID = "Q210"+ tripID1;
	
	@Test
	public void FK_SuperCoins_CreatePromo() throws Exception{
		Response resp ;		
		resp = payPost("SuperCoins_CreatePromo",tripID);
		validation("SuperCoins_CreatePromo", resp);
	}
}
