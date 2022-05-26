// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reporting_TS_V3 extends API_PaymentCommon1
{
	@Test
	public void Reporting_TS_V3()  {
		Response resp ;		
		resp = Reporting("ReportingTS_V3", "");
		validation("ReportingTS_V3", resp);
		}
	
}