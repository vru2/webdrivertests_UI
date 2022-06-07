// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class API_Hi5_Get_Trnx extends API_PaymentCommon1
{
	@Test
	public void Hi5_getTrnx() {
		Response resp;
		resp = payGet2("Hi5_GetTrnx", "");
		validation("Hi5_GetTrnx", resp);
	}
}