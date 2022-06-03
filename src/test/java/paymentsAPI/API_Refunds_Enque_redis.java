// Fra[mework - Cleartrip Automation
// Author - Saloni Gothi

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Refunds_Enque_redis extends API_PaymentCommon1
{
	
	@Test
	public void Enque_refund() throws Exception {
		Response resp ;		
		resp = payPost("refund_Enque","Params_Enque_refunds");	
		validation("refund_Enque", resp);	
		}	
	
}