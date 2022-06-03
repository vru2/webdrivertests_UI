// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Hi5_Get_WalletTnx extends API_PaymentCommon1
{
	@Test
	public void Hi5_get_walletTnx()  {
		Response resp ;		
		resp = payGet("Hi_5_get_walletTnx","");	
		validation("Hi_5_get_walletTnx", resp);
	}
	
}