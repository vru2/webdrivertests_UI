// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_SuperCoins_Info extends API_PaymentCommon1 {
	
	@Test
	public void FK_SuperCoins_Info() throws IOException, JSONException{
		Response resp ;		
		resp = payGet1("SuperCoins_Info","");
		validation("SuperCoins_Info", resp);
	}
}
