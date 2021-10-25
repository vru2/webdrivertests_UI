// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EMI_Resources extends API_PaymentCommon1
{
	@Test
	public void EMIResources() {
		Response resp ;		
		resp = payPut("EMIResources","");	
		validation("EMIResources", resp);	
		}
}

