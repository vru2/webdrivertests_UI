// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Prod_PaymentService_Get_TW_Wallets extends API_PaymentCommon1
{
	@Test
	public void Pay_getCardTypes()  {
		Response resp ;		
		resp = prodAPIs("Pay_Get_TW_Wallets","");	
		validation_Prod("Pay_Get_TW_Wallets", resp);
		
		}
	
}