// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CTpay_UpdateClient extends API_PaymentCommon1
{
	@Test
	public void PaymentCtPayUpdate() throws Exception {
		Response resp ;		
		resp = rearchCtPay("UPDATE","");	
		validation("UPDATE", resp);	
		}
}
