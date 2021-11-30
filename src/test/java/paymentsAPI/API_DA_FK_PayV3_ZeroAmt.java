// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_DA_FK_PayV3_ZeroAmt extends API_PaymentCommon1
{
	@Test
	public void paymentDA_FK_API() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPayment("DAPayFKZeroAmt","");	
		validation("DAPayFKZeroAmt", resp);
		}
}