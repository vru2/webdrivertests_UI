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
		resp = payGet1("ROR_WalletGet_Reads","");	
		validation("ROR_WalletGet_Reads", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_MultiSearch_Reads() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_MultiSearch_Reads","");	
		validation("ROR_MultiSearch_Reads", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_MultiSearch_TripRef_Reads() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_MultiSearch_TripRef_Reads","");	
		validation("ROR_MultiSearch_TripRef_Reads", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_WalletTransactions() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_WalletTrnx_Reads","");	
		validation("ROR_WalletTrnx_Reads", resp); 
	}
		
	@Test(alwaysRun=true)
	public void ROR_WalletGet() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_WalletGet_Reads","");	
		validation("ROR_WalletGet_Reads", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_GV_Details() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_GV_Details","");	
		validation("ROR_GV_Details", resp); 
	}

	@Test(alwaysRun=true)
	public void ROR_Refund_Details() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_Refund_Details","");	
		validation("ROR_Refund_Details", resp); 
	}
	
}