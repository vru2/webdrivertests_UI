// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_Pay_Multi_V2 extends API_PaymentCommon1
{
	@Test
	public void Expressway_Pay() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("payMultiV3", "");	
		validation("EW_PAY_Multi", resp);
	}
}