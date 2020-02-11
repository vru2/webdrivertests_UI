// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Promo_Activate extends API_PaymentCommon
{
	@Test
	public void Promo_Activate() throws IOException, JSONException{
		Response resp ;		
		resp = payGet("PromoActive", "");
		
		validation("PromoActive", resp);
	}
}