// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_GV_Capture extends API_PaymentCommon1{



	@Test
	public void API_GVCapture() throws ClassNotFoundException, SQLException  {
		Response resp ;
		resp = rearchGV("CAPTURE","");	
		validation("GV_CAPTURE", resp);
	}
}