// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class API_MIS_Report extends API_PaymentCommon1
{
	@Test
	public void MIS_report_PL()  {
		Response resp ;		
		resp = payGet("MIS_Report","PL");
		validation("MIS_Report_PL", resp);
	}

	@Test
	public void MIS_report_CC()  {
		Response resp ;
		resp = payGet("MIS_Report","CC");
		validation("MIS_Report_CC", resp);
	}
}