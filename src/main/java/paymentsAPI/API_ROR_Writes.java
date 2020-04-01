// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_ROR_Writes extends API_PaymentCommon1
{
	@Test(alwaysRun=true)
	public void ROR_Fetch_PaymentByID() throws IOException, JSONException{
		Response resp ;		
		resp = payGet1("RORFetch_Pay_BY_ID","");	
		validation("RORFetch_Pay_BY_ID", resp);
	}
	
	@Test(alwaysRun=true)
	public void ROR_Fetch_RefundByID() throws IOException, JSONException{
		Response resp ;		
		resp = payGet1("RORFetch_Refund_BY_ID","");	
		validation("RORFetch_Refund_BY_ID", resp);
	}
	
	@Test(alwaysRun=true)
	public void ROR_Fetch_ProfileList() throws IOException, JSONException{
		Response resp ;		
		resp = payGet1("RORFetch_Profile_List","");	
		validation("RORFetch_Profile_List", resp);
	}
	
	@Test(alwaysRun=true)
	public void ROR_Update_ProfileList() throws IOException, JSONException{
		Response resp ;		
		resp = payPut("RORUpdate_Profile_List","");	
		validation("RORUpdate_Profile_List", resp);
	} 
	
	@Test(alwaysRun=true)
	public void ROR_Update_Refunds() throws IOException, JSONException{
		Response resp ;		
		resp = payPut("RORUpdate_Refund_List","");	
		validation("RORUpdate_Refund_List", resp);
	}
	
	@Test(alwaysRun=true)
	public void ROR_Create_ProfileList() throws Exception{
		Response resp ;		
		resp = payPost("ROR_Create_ProfileList","");	
		validation("ROR_Create_ProfileList", resp);
	}
	
	@Test(alwaysRun=true)
	public void ROR_Update_Payment() throws IOException, JSONException{
		Response resp ;		
		resp = payPut("RORUpdate_Payment","");	
		validation("", resp); // Validating only 200 status
	}
	
	
	
	
	@Test(alwaysRun=true)
	public void ROR_Create_Payments() throws Exception{
		Response resp ;		
		resp = payPost("RORCreate_Payment","");	
		validation("RORCreate_Payment", resp); 
	}
	

	@Test(alwaysRun=true)
	public void ROR_Search_ProfileList() throws Exception{
		Response resp ;		
		resp = payPost("ROR_Search_ProfileList","");	
		validation("ROR_Search_ProfileList", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_Create_Refunds() throws Exception{
		Response resp ;		
		resp = payPost("ROR_Create_Refunds","");	
		validation("ROR_Create_Refunds", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_Recon() throws Exception{
		Response resp ;		
		resp = payPost("ROR_Recon","");	
		validation("ROR_Recon", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_MultiSearch_Pay() throws Exception{
		Response resp ;		
		resp = payGet1("ROR_MultiSearch","");	
		validation("ROR_MultiSearch", resp); 
	}
	
	
	
	
}