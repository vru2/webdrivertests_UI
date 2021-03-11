// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EMI_NoonPlans extends API_PaymentCommon1
{
	@Test
	public void EMINoon() {
		Response resp ;		
		resp = payGet("EMINoon","");	
		validation("EMINoon", resp);	
		}
}

