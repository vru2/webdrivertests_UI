// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class API_StoredCards_New extends API_PaymentCommon1 {
	
	@Test
	public void API_SavedCards_neww() throws Exception{
		Response resp ;		
		resp = payGet1("SavedCards","");
		validation("SavedCards", resp);
	}

}