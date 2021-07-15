// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import java.io.IOException;
import org.json.JSONException;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class API_Wallet extends API_PaymentCommon
{
	@Test
	public void paymentWallet_API() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPayment("WALLET","");	
		validation("WALLET", resp);	
		}
}