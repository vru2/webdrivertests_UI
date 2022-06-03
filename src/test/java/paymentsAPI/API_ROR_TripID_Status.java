// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_ROR_TripID_Status extends API_PaymentCommon1
{

	
	@Test(alwaysRun=true)
	public void ROR_tripStatus() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_TripStatus","");	
		validation("ROR_TripStatus", resp); 
	}
}