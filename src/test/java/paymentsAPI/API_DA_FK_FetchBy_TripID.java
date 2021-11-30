// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_DA_FK_FetchBy_TripID extends API_PaymentCommon1
{
	@Test
	public void paymentDA_FK_FetchBy_TripID() throws IOException, JSONException{
		Response resp ;		
		resp = DAGet("DAFK_FetchBy_TripID","");	
		validation("DAFK_FetchBy_TripID", resp);
		}
}