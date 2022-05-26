// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_ROR_Reads2 extends API_PaymentCommon1
{

	@Test(alwaysRun=true)
	public void ROR_Update_Payment() throws IOException, JSONException{
		Response resp ;		
		resp = payPut("RORUpdate_Payment","");	
		validation("", resp); // Validating only 200 status
	}
	
	
	
}