// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_ROR_Reads extends API_PaymentCommon1
{

	
	@Test(alwaysRun=true)
	public void ROR_WalletFetch() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_WalletFetch","");	
		validation("ROR_WalletFetch", resp); 
	}
	
	
	@Test(alwaysRun=true)
	public void ROR_MultiSearch_Reads() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_MultiSearch_Reads","");	
		validation("ROR_MultiSearch_Reads", resp); 
	}
	
	/*
	@Test(alwaysRun=true)
	public void ROR_WalletFetch1() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_WalletFetch","");	
		validation("ROR_WalletFetch", resp); 
	}*/
}