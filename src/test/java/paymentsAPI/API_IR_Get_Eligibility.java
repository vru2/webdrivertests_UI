// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_IR_Get_Eligibility extends API_PaymentCommon1
{
	@Test
	public void InstantRefund_geteligibility_NB()  {
		Response resp ;		
		resp = payGet("IR_Eligibility_NB","");	
		validation("IR_Eligibility_NB", resp);
	}
	
	@Test
	public void InstantRefund_geteligibility_DC()  {
		Response resp ;		
		resp = payGet("IR_Eligibility_DC","");	
		validation("IR_Eligibility_NB", resp);
	}
	
	@Test
	public void InstantRefund_geteligibility_CC()  {
		Response resp ;		
		resp = payGet("IR_Eligibility_CC","");	
		validation("IR_Eligibility_CC", resp);
	}
	
	@Test
	public void InstantRefund_geteligibility_GV()  {
		Response resp ;		
		resp = payGet("IR_Eligibility_GV","");	
		validation("IR_Eligibility_CC_Non", resp);
	}
	
	@Test
	public void InstantRefund_geteligibility_CC_Non()  {
		Response resp ;		
		resp = payGet("IR_Eligibility_CC_Non","");	
		validation("IR_Eligibility_CC_Non", resp);
	}
}