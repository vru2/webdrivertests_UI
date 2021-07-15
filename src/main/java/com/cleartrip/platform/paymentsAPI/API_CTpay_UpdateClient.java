// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;
import io.restassured.response.Response;

public class API_CTpay_UpdateClient extends API_PaymentCommon
{
	@Test
	public void PaymentCtPayUpdate() throws Exception {
		Response resp ;		
		resp = rearchCtPay("UPDATE","");	
		validation("UPDATE", resp);	
		}
}
