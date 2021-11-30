// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;
import java.util.Random;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_DA_FK_Refund_Create extends API_PaymentCommon1
{
	@Test
	public void paymentDA_FK_RefundCreate() throws IOException, JSONException{
		
		
		Response resp ;		
		resp = rearchPayment("DAFKRefundCreate","");	
		validation("DAFKRefundCreate", resp);
		}
}