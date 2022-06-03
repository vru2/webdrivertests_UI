// Framework - Cleartrip Automation
// Author - Varalakshmi

package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Binmanager_Get_Card extends API_PaymentCommon1
{
	
	@Test
	public void Get_Card () throws Exception{
		// Add credit card and issuer type mastercard
        Response resp;
		resp = BinDetail ("GetCard",Params_Singlebincard_Credit);	
		validationforbindetail("Get_Card", resp);
	
	}
}
