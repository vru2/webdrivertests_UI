// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_RevertPromo extends API_PaymentCommon1
{
	@Test
	public void Wallet_REVERTPROMO()  {
		Response resp ;		
		resp = rearchWallet("REVERTPROMO","");	
		validation("wallet_REVERTPROMO", resp);
		}	
}