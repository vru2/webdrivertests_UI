// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reward_PayBack_CheckBalance extends API_PaymentCommon1
{	
	Random rand = new Random();
	int n = rand.nextInt(9999999);
	String trackid = Integer.toString(n);
	String track = "CLR"+ trackid;

	@Test
	public void payback_Checkbalance() throws Exception{
		Response resp ;		
		resp = reward("PAYBACK_CheckBalance","");	
		validation("PAYBACK_CheckBalance", resp);
	}
}
