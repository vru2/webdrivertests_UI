// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Fetch_GW_Failure extends API_PaymentCommon1
{
	@Test
	public void APIFetch_Status() {
		Response resp ;		
		resp = payGet("FetchGWFailure","");	
		validation("FetchGWFailure", resp);	
		}
}

