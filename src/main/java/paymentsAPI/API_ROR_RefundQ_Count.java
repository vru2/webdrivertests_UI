// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;
import io.restassured.response.Response;

public class API_ROR_RefundQ_Count extends API_PaymentCommon1

{

	@Test(alwaysRun=true)
	public void ROR_RefundQ_Paginate() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingPaginate","");	
		validation_ROR("ReportingPaginate", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Paginate_Paytype() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingPaginatePayType","");	
		validation_ROR("ReportingPaginate", resp); 
	}
	
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_D() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountD","");	
		validation_ROR("BasicValidaton", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_D_DA() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountDDA","");	
		validation_ROR("BasicValidaton", resp); 
	}

	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_S_Tnx() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountSTnx","");	
		validation_ROR("BasicValidaton", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_S_Tnx_GW() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountSTnxGw","");	
		validation("BasicValidaton", resp); 
	}
	
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_S_PAYTYPE() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountSPayType","");	
		validation_ROR("BasicValidaton", resp); 
	}
	
		
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_D_CashCard() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountDCashCard","");	
		validation_ROR("BasicValidaton", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_P_PayType() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountPpayType","");	
		validation_ROR("BasicValidaton", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_Refund_UploadQ__FetchBy_refundID() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundUploadFetchRefundID","");	
		validation_ROR("ReportingRefundUploadFetch", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_Refund_UploadQ__FetchBy_tripID() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundUploadFetchTripID","");	
		validation_ROR("ReportingRefundUploadFetch", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_Refund_StausPost() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingStatusPost","");	
		validation_ROR("BasicValidaton", resp); 
	}
	
	/*@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_S_PAYTYPE_GW() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountSPayTypeGw","");	
		validation_ROR("BasicValidaton", resp); 
	}*/


}