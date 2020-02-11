// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_Pay_AutoDebit extends API_PaymentCommon
{
	@Test
	public void Expressway_Pay() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("pay", "");	
		validation("EW_PAY", resp);
	}
	
	@Test(dependsOnMethods = {"Expressway_Pay"})
	public void Expressway_AutoDebit() throws IOException, JSONException{
		Response resp ;		
		resp = expressWay("Autodebit", "");	
		validation("EW_Autodebit", resp);
	}
}