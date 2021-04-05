// Framework - Cleartrip Automation
// Author - Saloni Gothi

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reporting_Refund_Status extends API_PaymentCommon1
{

	@Test(alwaysRun=true)
	public void reporting_refundStatus_reportAPI () throws Exception{
		Response resp ;		
		resp = Reporting ("ReportingRefundStatus","");	
		validation("ReportingRefundStatus", resp);
								
	}
	
		
	}
