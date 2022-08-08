// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class API_SuperCoins_Hold_Unhold extends API_PaymentCommon1 {

	@Test
	public void FK_SuperCoins_Hold() throws Exception {
		Response resp ;
		resp = payPost("SuperCoins_OTPLess_Hold","");
		validation("SuperCoins_OTPLess_Hold", resp);
	}

	@Test
	public void FK_SuperCoins_Unhold() throws Exception {
		Response resp ;
		resp = payPost("SuperCoins_OTPLess_Unhold","");
		validation("SuperCoins_OTPLess_Unhold", resp);
	}
}
