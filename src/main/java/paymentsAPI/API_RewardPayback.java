/* AUTHOR: PRAKHAR CHATTERJEE
 * EMAIL: prakhar.chatterjee@cleartrip.com
 * DOCUMENTATION: This Class focusses on automating all API's related to Reward Payback functionality.
 */


package paymentsAPI;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_RewardPayback extends API_PaymentCommon1  {

	

	@Test(priority=1)
	public void RewardPayBack_RewardCheckMobileLinked(){
		try{
			Response res = reward("PAYBACK_CheckMobileLinked","");
			validation_RewardPayback("PAYBACK_CheckMobileLinked",res);
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}

	@Test(priority=2)
	public void RewardPayBack_CheckCardLinked(){
		try{
			Response res = reward("PAYBACK_CheckCardLinked", "");
			validation_RewardPayback("PAYBACK_CheckCardLinked", res);

		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}
	
	@Test(priority=3)
	public void RewardPayback_CheckBalance_Mobile(){
		try{
			Response res = reward("PAYBACK_CheckBalance", "");
			validation_RewardPayback("PAYBACK_CheckBalance", res);
		}

		catch(Exception e){
			Reporter.log("Exception faced is" +e);
		}
	}

	@Test(priority=4)
	public void RewardPayback_CheckBalance_Card(){
		try{
			Response res = reward("PAYBACK_CheckBalance_card", "");
			validation_RewardPayback("PAYBACK_CheckBalance_card", res);
		}

		catch(Exception e){
			Reporter.log("Exception faced is" +e);
		}
	}
	


	@Test(priority=5)
	public void RewardPayback_ValidateCardDetails(){
		try{
            Response res = reward("PAYBACK_Validate", "");
            validation_RewardPayback("PAYBACK_Validate", res);
			
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}



	@Test(priority=6)
	public void RewardPayback_redeemPoints(){
		try{
			Response res = reward("PAYBACK_RedeemPoints", "");
			validation_RewardPayback("PAYBACK_RedeemPoints", res);

		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}
	@Test(priority=7)
	public void RewardPayback_CheckEarnPoints(){
		try{
			Response res = reward("PAYBACK_CheckEarnPoints", "");
			validation_RewardPayback("PAYBACK_CheckEarnPoints", res);
		}

		catch(Exception e){
			Reporter.log("Exception is"+e);
		}
	}

	@Test(priority=8, dependsOnMethods = { "RewardPayback_redeemPoints" })
	public void RewardPayback_refundPoints(){
		try{
			Response res = reward("PAYBACK_RefundPoints", "");
			validation_RewardPayback("PAYBACK_RefundPoints", res);
		}
		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}

	@Test(priority=9)
	public void RewardPayback_earnNotUsed(){
		try{
			Response res = reward("PAYBACK_Earn", "");
			validation_RewardPayback("PAYBACK_Earn", res);
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}

	@Test(priority=10, dependsOnMethods = { "RewardPayback_earnNotUsed" })
	public void RewardPayback_reverseEarnNotUsed(){
		try{
			Response res = reward("PAYBACK_Reverseearn", "");
			validation_RewardPayback("PAYBACK_Reverseearn", res);	
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}

	@Test(priority=11, enabled=false)
	public void RewardPayback_forgotPassword(){
		try{
			Response res = reward("PAYBACK_Forgotpassword", "");
			validation_RewardPayback("PAYBACK_Forgotpassword", res);
		}

		catch(Exception e){
			Reporter.log("Exception is" +e);
		}
	}

}
