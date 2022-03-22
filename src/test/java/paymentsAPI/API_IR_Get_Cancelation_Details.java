// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_IR_Get_Cancelation_Details extends API_PaymentCommon1
{
	@Test
	public void IR_CancelDetails_CancelTnx()  {
		Response resp ;		
		resp = payGet("IR_Fetch_Cancel_Details_CancelTnx","");	
		validation("IR_Fetch_Cancel_Details_CancelTnx", resp);
	}
	
	@Test
	public void IR_CancelDetails_UserID()  {
		Response resp ;		
		resp = payGet("IR_Fetch_Cancel_Details_UserID","");	
		validation("IR_Fetch_Cancel_Details_UserID", resp);
	}
}