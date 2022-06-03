// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_IR_Save_VPA_Details extends API_PaymentCommon1
{
	@Test
	public void API_IR_SaveVPA() throws Exception  {
		Response resp ;		
		resp = payPost("IR_Save_VPA","");	
		validation("IR_Save_VPA", resp);
	}

	
}

