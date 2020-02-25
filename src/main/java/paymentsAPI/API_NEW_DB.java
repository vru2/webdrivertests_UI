// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_NEW_DB extends API_PaymentCommon1
{
	@Test
	public void paymentCC_API() throws IOException, JSONException, ClassNotFoundException, SQLException, InterruptedException{
			Response resp ;		
			ArrayList<String> MySQL_DB = MySQL_DB("");

			String RefundID = MySQL_DB.get(0);
			System.out.println("RefundID "+RefundID);
		
		}
	
}