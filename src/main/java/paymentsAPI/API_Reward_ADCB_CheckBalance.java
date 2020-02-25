// Framework - Cleartrip Automation
// Author - Saloni Gothi

package paymentsAPI;

import java.util.Random;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reward_ADCB_CheckBalance extends API_PaymentCommon1
{
	
	Random rand = new Random();
	int n = rand.nextInt(9999999);
	String trackid = Integer.toString(n);
	String track = "CLR"+ trackid;
		
	@Test
	public void adcb_Checkbalance() throws Exception{
		Response resp ;		

		Reporter.log("------ Check Balance Started -------");

		resp = reward("ADCB_CheckBalance",track);	
		validation("adcb_checkBalance", resp);

	}
}
