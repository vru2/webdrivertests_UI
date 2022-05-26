// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_DA_APP_Balance extends API_PaymentCommon1
{
	@Test
	public void paymentDA_BalanceI() throws IOException, JSONException{
		Response resp ;		
		resp = DAGet("DABalance","");	// Direct call to DA app
		validation("DABalance", resp);
		}
}