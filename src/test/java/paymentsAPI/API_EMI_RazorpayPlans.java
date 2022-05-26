// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EMI_RazorpayPlans extends API_PaymentCommon1
{
	@Test
	public void EMIRazorpay() {
		Response resp ;		
		resp = payGet("EMIRazorpay","");	
		validation("EMIRazorpay", resp);	
		}
}

