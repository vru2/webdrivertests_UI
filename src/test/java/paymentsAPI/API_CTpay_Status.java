// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_CTpay_Status extends API_PaymentCommon1
{
	@Test
	public void PaymentCtPayStatus() throws Exception{
		Response resp ;		
		resp = rearchCtPay("STATUS","");	
		validation("STATUS", resp);	
		}
}