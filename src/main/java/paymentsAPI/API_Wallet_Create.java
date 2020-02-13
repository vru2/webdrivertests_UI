// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_Create extends API_PaymentCommon
{
	@Test
	public void Wallet_GETWALLET()  {
		Response resp ;		
		resp = rearchWallet("WALLET_CREATE","");	
		validation("WALLET_CREATE", resp);
		}
	
}