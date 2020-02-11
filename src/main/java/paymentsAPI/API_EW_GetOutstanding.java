// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_GetOutstanding extends API_PaymentCommon
{
	@Test
	public void Expressway_getOutstandingAmt() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("getOutstanding", "");	
		validation("EW_OUTSTANDING", resp);
	}
}