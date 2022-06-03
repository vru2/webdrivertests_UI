// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EMI_Cache extends API_PaymentCommon1
{
	@Test
	public void EMICache() {
		Response resp ;		
		resp = payPut("EMICache","");	
		validation("EMICache", resp);	
		}
}

