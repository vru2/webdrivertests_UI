// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_UI_GetConfig_ConvFee extends API_PaymentCommon1
{
	@Test
	public void UI_GetConfig_ConvFee() throws Exception {
		Response resp ;		
		resp = payGet1("GETUI_CONVFEE","");	
		validation("GETUI_CONVFEE", resp);	
		}
}