// Framework - Cleartrip Automation
// Author - Saloni

package test.java.paymentsAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class API_Saved_PaymentModes_UI extends API_PaymentCommon1
{	
	
	@Test
	public void get_savedPayment_modes () throws Exception{
		Response resp ;		
		resp = rearchWallet("Saved_PaymentModes","");
		validation("Saved_PaymentModes", resp);
	}
}
