// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;
import java.sql.SQLException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Flyin_Wallet extends API_PaymentCommon1
{
	@Test
	public void API_GVGet() throws ClassNotFoundException, SQLException  {
		Response resp ;		
		resp = payGet("FlyinWallet","");	
		//validation("FlyinWallet", resp);
		System.out.println(resp);
		}
	
}