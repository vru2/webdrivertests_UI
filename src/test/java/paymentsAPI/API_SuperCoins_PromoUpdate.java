// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import org.testng.annotations.Test;
import io.restassured.response.Response;

public class API_SuperCoins_PromoUpdate extends API_PaymentCommon1 {
	
	@Test
	public void FK_SuperCoins_UpdatePromo() throws Exception{

		String date= getDateTime(5, "dd-MM-yyyy");
		Response resp ;		
		resp = rearchWallet("SuperCoins_UpdatePromo",date);
		validation("SuperCoins_UpdatePromo", resp);
		
	}
}
