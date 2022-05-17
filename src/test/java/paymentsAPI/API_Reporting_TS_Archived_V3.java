// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reporting_TS_Archived_V3 extends API_PaymentCommon1
{

	@Test (alwaysRun=true)
	public void Reporting_archivedAPI_False()  {
		Response resp ;		
		resp = Reporting("ReportingTS_Archived_V3_False", "");
		validation("ReportingTS_Archived_V3_False", resp);
		}

	@Test (alwaysRun=true)
	public void Reporting_archivedAPI_True()  {
		Response resp ;		
		resp = Reporting("ReportingTS_Archived_V3_True", "");
		validation("ReportingTS_Archived_V3_True", resp);
		}

/*	@Test (alwaysRun=true)
	public void Reporting_archivedAPI_New()  {
		Response resp ;		
		resp = Reporting("ReportingTS_Archived_V_New", "");
		validation("ReportingTS_Archived_V_New", resp);
		}*/
	
	
	
	
}