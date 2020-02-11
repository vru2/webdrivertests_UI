// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

public class API_Refund_DC extends API_PaymentCommon
{
	@Test
	public void RefundAPI_DC() throws IOException, JSONException, ClassNotFoundException, SQLException, InterruptedException{
		if(common.value("PaymentServer").equalsIgnoreCase("new")) {
			Response resp ;		
			ArrayList<String> db_DC = db_Refund_Common("DC");
			String RefundID = db_DC.get(2);
			Reporter.log("RefundID "+RefundID);
			resp = refund("refund", RefundID);
			validation("", resp);
			Thread.sleep(7000);
			ArrayList<String> db_refundStatusDC =  db_Refund_StatusCC(RefundID);
			Reporter.log("Refund Status for ID "+RefundID+" is "+db_refundStatusDC.get(0));
			if(!db_refundStatusDC.get(0).contains("D")) {
				Reporter.log("Refund Status should be D is displayed as "+db_refundStatusDC.get(0));
				Assert.assertTrue(false);
			}
			}else Reporter.log("Script not excuted in Old payments");
	} 
}