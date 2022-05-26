// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CTpay_GetUrl extends API_PaymentCommon1
{
	@Test
	public void PaymentCtPayCreate() throws Exception {
		Response resp ;		
		resp = rearchCtPay("GETURL","");	
		validation("GETURL", resp);	
		}
}