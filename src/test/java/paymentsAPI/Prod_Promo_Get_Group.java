// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Prod_Promo_Get_Group extends API_PaymentCommon1
{
	@Test
	public void Promo_Get_Group()  {
		Response resp ;		
		resp = prodAPIs("Promo_Get_Group","");	
		validation_Prod("Promo_Get_Group", resp);
		}
	
}