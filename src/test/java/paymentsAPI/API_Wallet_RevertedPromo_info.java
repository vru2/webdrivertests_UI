// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_RevertedPromo_info extends API_PaymentCommon1
{
	@Test
	public void Wallet_REVERTPROMO()  {
		Response resp ;		
		resp = rearchWallet("REVERTEDPROMO","");	
		validation("wallet_REVERTEDPROMO", resp);
		}
	
}