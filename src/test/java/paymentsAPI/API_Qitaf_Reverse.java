// Framework - Cleartrip Automation Pay-411
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Qitaf_Reverse extends API_PaymentCommon1
{
	@Test
	public void QitafReverse() throws Exception {
		Response resp ;		
		resp = payPost("Qitaf_Reverse","");	
		validation("Qitaf_Reverse", resp);	
		}
}