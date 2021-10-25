// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_FetchPay_Details extends API_PaymentCommon1
{
	@Test
	public void FetchPaydetails() {
		Response resp ;		
		resp = payGet("FetchPayDetails","");	
		validation("FetchPayDetails", resp);	
		}
}

