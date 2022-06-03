// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

public class API_DA_APP_Refund extends API_PaymentCommon1
{
	@Test
	public void paymentDA_Refund() throws IOException, JSONException{
		Response resp ;		
		resp = rearchPayment("DARefund","");	// Direct call to DA app
		Reporter.log("Response body " + resp.body().asString());
		if(!resp.body().asString().contains("status=S&message=success")) {
			Assert.assertTrue(false);
		}
		
	
		}
}