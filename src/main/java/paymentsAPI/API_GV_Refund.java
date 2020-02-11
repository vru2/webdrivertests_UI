// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_GV_Refund extends API_PaymentCommon
{
	@Test
	public void API_GVRefund() throws ClassNotFoundException, SQLException  {
		Response resp ;		
		resp = rearchGV("REFUND","");	
		validation("GV_REFUND", resp);
		}
	
}