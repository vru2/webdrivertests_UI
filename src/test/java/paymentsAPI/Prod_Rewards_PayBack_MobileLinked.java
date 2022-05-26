
// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Prod_Rewards_PayBack_MobileLinked extends API_PaymentCommon1
{
	@Test
	public void Get_CardTypes()  {
		Response resp ;		
		resp = prodAPIs("Rwd_PayBack_Mobile","");	
		validation_Prod("Rwd_PayBack_Mobile", resp);
		}
	
}