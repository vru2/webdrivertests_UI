// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CTpay_URLcreation extends API_PaymentCommon1
{
	@Test
	public void CreateURL_CTpay() throws Exception {
		Response resp ;		
		resp = rearchCtPay("CreateURL","");	
		validation("CreateURL", resp);	
		}
}
