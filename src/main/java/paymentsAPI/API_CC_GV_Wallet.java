// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CC_GV_Wallet extends API_PaymentCommon1
{
	@Test
	public void paymentCC_API() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPayment("CCGVWL","");	
		validation("CCGVWL", resp);
		}
}