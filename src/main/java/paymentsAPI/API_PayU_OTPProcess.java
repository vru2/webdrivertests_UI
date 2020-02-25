// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class API_PayU_OTPProcess extends API_PaymentCommon1
{
	@Test
	public void paymentOTP_Process() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPaymentOTP("INIT","","");	
		JsonPath jsonPathEvaluator = resp.jsonPath();
		String paymentID = jsonPathEvaluator.getString("id");
		paymentID = paymentID.replace("[", "").replace("]","");
		validation("INIT", resp);
		resp = rearchPaymentOTP("PROCESS","",paymentID);	
		validation("PROCESS", resp);
		
	}
}
