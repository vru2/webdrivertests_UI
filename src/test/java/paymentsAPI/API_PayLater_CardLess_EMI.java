// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class API_PayLater_CardLess_EMI extends API_PaymentCommon1
{
	@Test
	public void PayLater_1()  {
		Response resp ;		
		resp = payGet("Hi_5_get_walletTnx","");	
		validation("Hi_5_get_walletTnx", resp);
	}
	
}