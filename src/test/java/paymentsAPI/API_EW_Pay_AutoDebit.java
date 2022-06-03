// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_Pay_AutoDebit extends API_PaymentCommon1
{
	@Test(priority=1)
	public void Expressway_Pay() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("payV3", "");	
		validation("EW_PAY", resp);
	}
	
	@Test(priority=2)
	public void Expressway_AutoDebit() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("Autodebit", "");	
		validation("EW_Autodebit", resp);
	}
}