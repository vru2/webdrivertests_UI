// Framework - Cleartrip Automation
// Author - Saloni

package paymentsAPI;

import java.util.Random;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reward_PayBack extends API_PaymentCommon1
{	
	Random rand = new Random();
	int n = rand.nextInt(9999999);
	String trackid = Integer.toString(n);
	String track = "CLRTRPS"+ trackid;

	@Test
	public void payback_Checkbalance() throws Exception{
		Response resp ;		
		Reporter.log("------ Check Balance Started -------");
		resp = reward("PAYBACK_CheckBalance","");	
		validation("PAYBACK_CheckBalance", resp);

		Reporter.log("------ Check Balance with Card Started -------");
		resp = reward("PAYBACK_CheckBalance_card","");	
		validation("PAYBACK_CheckBalance", resp);

		Reporter.log("------ Validate Started -------");
		resp = reward("PAYBACK_Validate","");	
		validation("PAYBACK_Validate", resp);
		Reporter.log("------ Check Earn Points Started -------");
		
		resp = reward("PAYBACK_CheckEarnPoints","");	
		validation("PAYBACK_CheckEarnPoints", resp);
		Reporter.log("------ Check Mobile Linked Started -------");
		
		resp = reward("PAYBACK_CheckMobileLinked","");	
		validation("PAYBACK_CheckMobileLinked", resp);
		Reporter.log("------ Check Card linked Started -------");
		
		resp = reward("PAYBACK_CheckCardLinked","");	
		validation("PAYBACK_CheckCardLinked", resp);
		

		Reporter.log("------ Pay Started -------");

		resp = reward("PAYBACK_Pay",track);	
		validation("PAYBACK_Pay", resp);
//		
//		resp = reward("PAYBACK_Pay_WT","");	
//		validation("PAYBACK_Pay_WT", resp);
//		Reporter.log("------ PAYBACK_Pay_WT Passed -------");
		Reporter.log("------ PAYBACK_Pay_WT_MultiGV Started -------");   
		
		resp = reward("PAYBACK_Pay_WT_MultiGV","");	
		validation("PAYBACK_Pay_WT_MultiGV", resp);
		Reporter.log("------ PAYBACK_Pay_WT_MultiGV_CC Started -------");
		
		resp = reward("PAYBACK_Pay_WT_MultiGV_CC","");
		System.out.println(resp.body().asString());
		validation("PAYBACK_Pay_WT_MultiGV_CC", resp);

		Reporter.log("------ Earn Started -------");
		resp = reward("PAYBACK_Earn",track);	
		validation("PAYBACK_Earn", resp);
		
		Reporter.log("------ Reverse Earn Started -------");
		
		
		resp = reward("PAYBACK_Reverseearn",track);	
		validation("PAYBACK_Reverseearn", resp);
		Reporter.log("------ Forgot password Started -------");
		
		resp = reward("PAYBACK_Forgotpassword","");	
		validation("PAYBACK_Forgotpassword", resp);
		

	}
}
