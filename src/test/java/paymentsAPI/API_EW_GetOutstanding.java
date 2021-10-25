// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;

/*ct.services.payment.expway.plus.lease.time.in.seconds				
ct.services.payment.expway.plus.booking.lock.prefix					
*/
import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_EW_GetOutstanding extends API_PaymentCommon1
{
	@Test
	public void Expressway_getOutstandingAmt() throws IOException, JSONException{
		Response resp ;		
		Reporter.log("Expressway");
		resp = expressWay("getOutstanding", "");	
		validation("EW_OUTSTANDING", resp);
	}
}