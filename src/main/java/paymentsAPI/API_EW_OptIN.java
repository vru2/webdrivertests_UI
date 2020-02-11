// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_OptIN extends API_PaymentCommon
{
	@Test
	public void Expressway_UserDetails() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("otpIN", "");	
		validation("EW_OPTIN", resp);
	}
}