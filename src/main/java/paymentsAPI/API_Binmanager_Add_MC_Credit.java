// Framework - Cleartrip Automation
// Author - Varalakshmi

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Binmanager_Add_MC_Credit extends API_PaymentCommon
{
	
	@Test
	public void binmanager_MC_Credit () throws Exception{
		// Add credit card and issuer type mastercard
        Response resp;
		resp = BinDetail ("ADD_MC_Credit",Params_Singlebincard_Credit);	
		validationforbindetail("ADD_Card", resp);	
	}
}
