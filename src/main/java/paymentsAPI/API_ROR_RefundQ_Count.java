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
		validation_ROR("ReportingPaginatePayType", resp); 
	}

	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_D() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountD","");	
		validation_ROR("ReportingRefundQCountD", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_D_DA() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountDDA","");	
		validation_ROR("ReportingRefundQCountDDA", resp); 
	}

	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_S_Tnx() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountSTnx","");	
		validation_ROR("ReportingRefundQCountSTnx", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_S_Tnx_GW() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountSTnxGw","");	
		validation("ReportingRefundQCountSTnxGw", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_S_PAYTYPE() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountSPayType","");	
		validation_ROR("ReportingRefundQCountSPayType", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_S_PAYTYPE_GW() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountSPayTypeGw","");	
		validation_ROR("ReportingRefundQCountSPayTypeGw", resp); 
	}
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_D_CashCard() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountDCashCard","");	
		validation_ROR("ReportingRefundQCountDCashCard", resp); 
	}
	
	
	@Test(alwaysRun=true)
	public void ROR_RefundQ_Count_Status_P_PayType() throws Exception{
		Response resp ;		
		resp = Reporting("ReportingRefundQCountPpayType","");	
		validation_ROR("ReportingRefundQCountPpayType", resp); 
	}

}