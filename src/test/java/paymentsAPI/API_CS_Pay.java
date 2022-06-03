package test.java.  paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CS_Pay extends API_PaymentCommon1 {
	
	@Test
	public void paymentCS_API() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPayment("CSPay","");
		validation("CSPay", resp);
	}
}
