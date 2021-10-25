package paymentsAPI;


import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Refunds_Fetch extends API_PaymentCommon1 {
	
	@Test(priority=1)
	public void fetchRefundsMultiple() throws IOException, JSONException{
		Response resp ;		
		resp = fetchrefunds("RefundsTrip","");	
		//System.out.println(resp);
		validation("RefundsTrip", resp);
		}


	@Test(priority=2)
	public void fetchRefundsByTripRef() throws IOException, JSONException{
		Response resp ;		
		resp = fetchrefunds("RefundsTripRef","");	
		validation("RefundsTripRef", resp);
		}
	
}
