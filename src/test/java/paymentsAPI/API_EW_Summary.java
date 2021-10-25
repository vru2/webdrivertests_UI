// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_Summary extends API_PaymentCommon1
{
	@Test
	public void Expressway_Summary() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("EW_Summary", "");	
		validation("EW_Summary", resp);
	}
}