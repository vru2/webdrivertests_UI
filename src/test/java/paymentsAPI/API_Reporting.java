// Framework - Cleartrip Automation
// Author - Saloni Gothi

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reporting extends API_PaymentCommon1
{

	@Test(alwaysRun=true)
	public void reportingAPI () throws Exception{
		Response resp ;		
		resp = Reporting ("Reporting","");	
		validation("Reporting", resp);
								
	}
	
	@Test(alwaysRun=true)
	public void reportingPay () throws Exception{
		Response resp ;		
		resp = Reporting ("ReportingPAYID","");	
		validation("ReportingPAYID", resp);
								
	}
		
	}
