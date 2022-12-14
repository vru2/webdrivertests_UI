// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_IR_Create_Refund extends API_PaymentCommon1
{
	
	@Test
	public void API_IR_CreateRefund_Instant_VPA() throws Exception  {
		Response resp ;		
		resp = payPost("IR_Create_Refund","");	
		validation("IR_Create_Refund", resp);
	}
	@Test
	public void API_IR_CreateRefund_Normal() throws Exception  {
		Response resp ;		
		resp = payPost("IR_Create_Refund_Normal","");	
		validation("IR_Create_Refund_Normal", resp);
	}

	@Test
	public void API_IR_CreateRefund_WALLET() throws Exception  {
		Response resp ;
		resp = payPost("IR_Create_Refund_Wallet","");
		validation("IR_Create_Refund_Normal", resp);
	}

	@Test
	public void API_IR_CreateRefund_WALLET_PL() throws Exception  {
		Response resp ;
		resp = payPost("IR_Create_Refund_Wallet_PL","");
		validation("IR_Create_Refund_Normal", resp);
	}

}

