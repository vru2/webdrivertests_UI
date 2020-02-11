// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Validate extends API_PaymentCommon
{
	@Test
	public void paymentValidate_API() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPayment("VALIDATE","");	
		validation("VALIDATE", resp);
		}
}