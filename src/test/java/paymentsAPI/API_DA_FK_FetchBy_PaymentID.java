// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_DA_FK_FetchBy_PaymentID extends API_PaymentCommon1
{
	@Test
	public void paymentDA_FK_FetchBy_PayID() throws IOException, JSONException{
		Response resp ;		
		resp = DAGet("DAFK_FetchBy_PaymentID","");	
		validation("DAFK_FetchBy_PaymentID", resp);
		}
}