// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_GV_Get extends API_PaymentCommon1
{
	@Test
	public void API_GVGet() throws ClassNotFoundException, SQLException  {
		Response resp ;		
		resp = rearchGV1("GET","");	
		validation("GV_GET", resp);
		}
	
}