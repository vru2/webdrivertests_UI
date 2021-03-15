// Framework - Cleartrip Automation Pay-411
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Qitaf_Redeem extends API_PaymentCommon1
{
	@Test
	public void QitafSendOTP() throws Exception {
		Response resp ;		
		resp = payPost("Qitaf_Redeem","");	
		validation("Qitaf_Redeem", resp);	
		}
}