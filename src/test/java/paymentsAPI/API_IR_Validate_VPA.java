// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_IR_Validate_VPA extends API_PaymentCommon1
{
	@Test
	public void API_IR_ValidVPA() throws Exception  {
		Response resp ;		
		resp = payPost("IR_Valid_VPA","");	
		validation("IR_Valid_VPA", resp);
	}

	@Test
	public void API_IR_InValidVPA() throws Exception  {
		Response resp ;		
		resp = payPost("IR_InValid_VPA","");	
		validation("IR_InValid_VPA", resp);
	}
	
}

