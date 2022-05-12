// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Hi5_Get_WalletBalance extends API_PaymentCommon1
{
	@Test
	public void Hi5_get_wallet()  {
		Response resp ;		
		resp = payGet("Hi_5_get_wallet","");	
		validation("Hi_5_get_wallet", resp);
	}
}