// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reporting_TS_Archived_V3 extends API_PaymentCommon1
{
	@Test
	public void Reporting_archivedAPI()  {
		Response resp ;		
		resp = Reporting("ReportingTS_Archived_V3", "");
		validation("ReportingTS_Archived_V3", resp);
		}
	
}