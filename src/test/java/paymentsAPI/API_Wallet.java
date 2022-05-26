// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet extends API_PaymentCommon1
{
	@Test
	public void paymentWallet_API() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPayment("WALLET","");	
		validation("WALLET", resp);	
		}
}