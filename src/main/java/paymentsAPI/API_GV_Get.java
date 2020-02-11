// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_GV_Get extends API_PaymentCommon
{
	@Test
	public void API_GVGet() throws ClassNotFoundException, SQLException  {
		Response resp ;		
		resp = rearchGV("GET","");	
		validation("GV_GET", resp);
		}
	
}