// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

public class Prod_PaymentService_Get_TW_Wallets extends API_PaymentCommon
{
	@Test
	public void Pay_getCardTypes()  {
		Response resp ;		
		resp = prodAPIs("Pay_Get_TW_Wallets","");	
		validation_Prod("Pay_Get_TW_Wallets", resp);
		
		}
	
}