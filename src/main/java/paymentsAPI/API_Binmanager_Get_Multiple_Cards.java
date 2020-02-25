// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Binmanager_Get_Multiple_Cards extends API_PaymentCommon1
{
	
	@Test
	public void binmanager_MC_Credit () throws Exception{
		// Add credit card and issuer type mastercard
        Response resp;
		resp = BinDetail ("GetCard_Multi","");	
		validationforbindetail("Get_Card_Multi", resp);
	
	}
}
