// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CreateRecord extends API_PaymentCommon1
{
	@Test
	public void paymentCC_API() throws IOException, JSONException{
		Response resp ;		
		resp = payGet("CreateRecord","");	
		validation("CreateRecord", resp);
		}
}