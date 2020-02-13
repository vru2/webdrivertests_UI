// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_Get_Wallet_All extends API_PaymentCommon
{
	@Test
	public void Wallet_GETWALLET()  {
		Response resp ;		
		resp = rearchWallet("GETWALLET_ALL","");	
		validation("wallet_GETWALLET_ALL", resp);
		}
	
}