// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_Get_Wallet_Trnx extends API_PaymentCommon1
{
	@Test
	public void Wallet_GETWALLET()  {
		Response resp ;		
		resp = rearchWallet("GETWALLET_Trnx","");	
		validation("wallet_GETWALLET_Trnx", resp);
		}
	
}