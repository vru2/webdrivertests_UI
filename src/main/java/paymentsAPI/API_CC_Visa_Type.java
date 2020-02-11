// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CC_Visa_Type extends API_PaymentCommon
{
	@Test
	public void paymentCC_API() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPayment("CCVISA","");	
		validation("CC", resp);
		}
}