// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CTpay_AddClient extends API_PaymentCommon
{
	@Test
	public void PaymentCtPayUpdate() throws Exception {
		Response resp ;		
		resp = rearchCtPay("ADD","");	
		validation("CTPAYADD", resp);	
		}
}
