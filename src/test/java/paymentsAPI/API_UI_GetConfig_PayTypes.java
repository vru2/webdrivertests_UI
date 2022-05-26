// Framework - Cleartrip Automation Pay-411
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_UI_GetConfig_PayTypes extends API_PaymentCommon1
{
	@Test
	public void UI_GetConfig_Paytypes() throws Exception {
		Response resp ;		
		resp = payGet1("GETUI_PAYTYPES","");	
		validation("GETUI_PAYTYPES", resp);	
		}
}