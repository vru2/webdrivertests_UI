// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_Fetch extends API_PaymentCommon1
{
	@Test
	public void Wallet_GETWALLET()  {
		Response resp ;		
		resp = rearchWallet("WALLET_Fetch","");	
		validation("WALLET_Fetch_NotLogged", resp);
		resp = rearchWallet("WALLET_Fetch_LoggedIN","");	
		validation("WALLET_Fetch_LoggedIN", resp);
		}
	
}