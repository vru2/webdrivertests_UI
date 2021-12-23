// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_SuperCoins_MobileLinked extends API_PaymentCommon1 {
	
	@Test
	public void FK_SuperCoins_MobileLinked() throws IOException, JSONException{
		Response resp ;		
		resp = payGet1("SuperCoins_MobileLinked","");
		validation("SuperCoins_MobileLinked", resp);
	}
}