// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CTpay_GetUrl extends API_PaymentCommon
{
	@Test
	public void PaymentCtPayCreate() throws Exception {
		Response resp ;		
		resp = rearchCtPay("GETURL","");	
		validation("GETURL", resp);	
		}
}