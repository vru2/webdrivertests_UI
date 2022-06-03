// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsAPI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

public class API_Refund_GV extends API_PaymentCommon1
{
	@Test
	public void paymentGV_API() throws IOException, JSONException, ClassNotFoundException, SQLException, InterruptedException{
			Response resp ;		
			ArrayList<String> db_GV = db_Refund_Common("GV");
			String RefundID = db_GV.get(1);
			Reporter.log("RefundID "+RefundID);
			resp = refund("refund", RefundID);
			validation("", resp);
			Thread.sleep(7000);
			ArrayList<String> db_refundStatusGV =  db_Refund_StatusCC(RefundID);
			Reporter.log("Refund Status for ID "+RefundID+" is "+db_refundStatusGV.get(0));
			if(!db_refundStatusGV.get(0).contains("D")) {
				Reporter.log("Refund Status should be D is displayed as "+db_refundStatusGV.get(0));
				Assert.assertTrue(false);
			}
		}
	
}