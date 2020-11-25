// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_RevertPromoNew extends API_PaymentCommon1
{
	@Test
	public void Wallet_REVERTPROMO()  {
		Response resp ;		
		resp = rearchWallet("REVERTPROMONEW","");	
		validation("REVERTPROMONEW", resp);
		}
	
}