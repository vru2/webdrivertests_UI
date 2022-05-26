// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Wallet_GetDeduction extends API_PaymentCommon1
{
	@Test
	public void Wallet_GETDEDUCTION()  {
		Response resp ;		
		resp = rearchWallet("GETDEDUCTION","");	
		validation("wallet_GETDEDUCTION", resp);
		}
	
}