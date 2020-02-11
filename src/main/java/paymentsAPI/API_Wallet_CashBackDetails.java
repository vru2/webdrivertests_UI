// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_CashBackDetails extends API_PaymentCommon
{
	@Test
	public void Wallet_CashBack()  {
		Response resp ;		
		resp = rearchWallet("CASHBACK_DETAILS_WALLET","");	
		validation("wallet_CASHBACK_DETAILS_WALLETS", resp);
		}
	
}