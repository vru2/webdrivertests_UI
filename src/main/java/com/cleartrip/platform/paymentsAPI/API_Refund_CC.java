// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import junit.framework.Assert;

public class API_Refund_CC extends API_PaymentCommon
{
	@Test
	public void paymentCC_API() throws IOException, JSONException, ClassNotFoundException, SQLException, InterruptedException{
			Response resp ;		
			ArrayList<String> db_CC = db_Refund_Common("CC");
			String RefundID = db_CC.get(4);
			Reporter.log("RefundID "+RefundID);
			resp = refund("refund", RefundID);
			validation("", resp);
			Thread.sleep(7000);
			ArrayList<String> db_refundStatusCC =  db_Refund_StatusCC(RefundID);
			Reporter.log("Refund Status for ID "+RefundID+" is "+db_refundStatusCC.get(0));
			if(!db_refundStatusCC.get(0).contains("D")) {
				Reporter.log("Refund Status should be D is displayed as "+db_refundStatusCC.get(0));
				Reporter.log("Deleting refund ID "+RefundID);
				db_Refund_Delete(RefundID);
				Assert.assertTrue(false);
			}
		
		}
	
}