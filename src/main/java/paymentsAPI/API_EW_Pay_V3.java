// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_Pay_V3 extends API_PaymentCommon
{
	@Test
	public void Expressway_Pay() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("payV3", "");	
		validation("EW_PAY", resp);
	}
}