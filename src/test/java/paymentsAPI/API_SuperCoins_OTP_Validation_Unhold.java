// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class API_SuperCoins_OTP_Validation_Unhold extends API_PaymentCommon1 {
	String OTP = null;
	
	@Test (priority = 1)
	public void FK_SuperCoins_OTP() throws Exception{
		
		//======================= Send OTP ================================//
		Response resp =null;		
		resp = payPost("SuperCoins_SendOTP","");
		validation("SuperCoins_SendOTP", resp);		
		JsonPath jsonPathEvaluator = resp.jsonPath();
		OTP= jsonPathEvaluator.getString("amount");
		OTP = OTP.substring(0, 6);

		//======================= Validate OTP ==============================//
		resp = payPost("SuperCoins_ValidateOTP",OTP);
		validation("SuperCoins_ValidateOTP", resp);
	}

	@Test (priority = 2) 
	public void FK_SuperCoins_InvalidOTP() throws Exception{
		
		//======================= Invalid OTP ===================================//
		Response resp =null;
		resp = payPost("SuperCoins_ValidateOTP","123456");
		validation("SuperCoins_ValidateOTP_Invalid", resp);
	}

	@Test (priority = 3)
	public void FK_SuperCoins_Unhold() throws Exception{
		
		//======================= Unhold ===================================//
		Response resp =null;
		resp = payPost("SuperCoins_Unhold","");
		validation("SuperCoins_Unhold", resp);
	}

}
