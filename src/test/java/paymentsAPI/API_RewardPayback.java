/* AUTHOR: PRAKHAR CHATTERJEE
 * EMAIL: prakhar.chatterjee@cleartrip.com
 * DOCUMENTATION: This Class focusses on automating all API's related to Reward Payback functionality.
 */


package test.java.  paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_RewardPayback extends API_PaymentCommon1  {

	

	@Test(priority=1)
	public void RewardPayBack_RewardCheckMobileLinked() throws Exception{
		Response res = reward("PAYBACK_CheckMobileLinked","");
		validation_RewardPayback("PAYBACK_CheckMobileLinked",res);
	}

	@Test(priority=2)
	public void RewardPayBack_CheckCardLinked() throws Exception{
		Response res = reward("PAYBACK_CheckCardLinked", "");
		validation_RewardPayback("PAYBACK_CheckCardLinked", res);
	}
	
	@Test(priority=3)
	public void RewardPayback_CheckBalance_Mobile() throws Exception{
		Response res = reward("PAYBACK_CheckBalance", "");
	validation_RewardPayback("PAYBACK_CheckBalance", res);
	}

	@Test(priority=4)
	public void RewardPayback_CheckBalance_Card() throws Exception{
		Response res = reward("PAYBACK_CheckBalance_card", "");
		validation_RewardPayback("PAYBACK_CheckBalance_card", res);
	}
	
	@Test(priority=5, dependsOnMethods= {"RewardPayback_CheckBalance_Card"})
	public void RewardPayback_ValidateCardDetails() throws Exception{
        Response res = reward("PAYBACK_Validate", "");
        validation_RewardPayback("PAYBACK_Validate", res);
	}


	@Test(priority=6, dependsOnMethods= {"RewardPayback_ValidateCardDetails"})
	public void RewardPayback_redeemPoints() throws Exception{
		Response res = reward("PAYBACK_RedeemPoints", "");
		validation_RewardPayback("PAYBACK_RedeemPoints", res);
	}

	@Test(priority=7)
	public void RewardPayback_CheckEarnPoints() throws Exception{
		Response res = reward("PAYBACK_CheckEarnPoints", "");
		validation_RewardPayback("PAYBACK_CheckEarnPoints", res);
	}

	@Test(priority=8)
	public void RewardPayback_refundPoints() throws Exception{
		Response res = reward("PAYBACK_RefundPoints", "");
		validation_RewardPayback("PAYBACK_RefundPoints", res);
	}

	@Test(priority=9)
	public void RewardPayback_earnNotUsed() throws Exception{
		Response res = reward("PAYBACK_Earn", "");
		validation_RewardPayback("PAYBACK_Earn", res);
	}

	@Test(priority=10, dependsOnMethods = { "RewardPayback_earnNotUsed" })
	public void RewardPayback_reverseEarnNotUsed() throws Exception{
		Thread.sleep(7000);
		Response res = reward("PAYBACK_Reverseearn", "");
		validation_RewardPayback("PAYBACK_Reverseearn", res);	
	}
/*
	@Test(priority=11, enabled=true)
	public void RewardPayback_forgotPassword() throws Exception{
		Response res = reward("PAYBACK_Forgotpassword", "");
		validation_RewardPayback("PAYBACK_Forgotpassword", res);
	}
*/
}
