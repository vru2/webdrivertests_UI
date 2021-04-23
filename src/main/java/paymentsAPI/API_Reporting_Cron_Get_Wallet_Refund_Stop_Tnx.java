// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reporting_Cron_Get_Wallet_Refund_Stop_Tnx extends API_PaymentCommon1
{
	@Test
	public void Cron_refund_wall_stoped()  {
		Response resp ;		
		resp = rearchWallet("GETWALLET_Refund_Stop_Tnx","");	
		validation("GETWALLET_Refund_Stop_Tnx", resp);
		}
}