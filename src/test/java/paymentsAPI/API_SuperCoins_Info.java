// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_SuperCoins_Info extends API_PaymentCommon1 {
	
	@Test
	public void FK_SuperCoins_Info() throws Exception {
		Response resp ;		
		resp = payPost("SuperCoins_Info","");
		validation("SuperCoins_Info", resp);
	}
}
