// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_UserDetails extends API_PaymentCommon1
{
	@Test
	public void Expressway_UserDetails() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("userDetails", "");	
		validation("EW_USERDETAILS", resp);
	}
}