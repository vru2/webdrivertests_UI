// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import java.util.Random;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reward_ADCB_Pay extends API_PaymentCommon1
{
	
	Random rand = new Random();
	int n = rand.nextInt(9999999);
	String trackid = Integer.toString(n);
	String track = "CLTP"+ trackid;
		
	@Test(priority = 1, alwaysRun = true)
	public void adcb_CheckBalance() throws Exception{
		Response resp ;		

		Reporter.log("------ Check Balance Started -------");

		resp = reward("ADCB_CheckBalance",track);	
		validation("adcb_checkBalance", resp);
	}
		

		@Test(priority = 2, alwaysRun = true)
		public void ADCBOTP ()throws Exception {

		Reporter.log("------ Send OTP Started -------");
								
		resp = adcb("sendOTP",track);	
		validation("adcb_sendOTP", resp);
		}

		@Test(priority = 3, alwaysRun = true)
		public void ADCBValidate ()throws Exception  {
		Reporter.log("------ Validate Started -------");		

		resp = adcb("validate",track);	
		validation("adcb_validate", resp);	
		}


		@Test(priority = 4, alwaysRun = true)
		public void ADCBPay ()throws Exception{
		Reporter.log("------ Pay Started -------");

		resp = adcb("pay",track);	
		validation("adcb_pay", resp);
		} 


	/*	@Test(priority = 5, alwaysRun = true)
		public void ADCBPayDuplicate ()throws Exception{
		Reporter.log("------ Pay  Started cwith same trackID payment should not go through-------");
		
		resp = adcb("pay",track);	
		validation("adcb_pay2", resp);	

		Reporter.log("------ Pay 2nd time validation Passed -------");
		}*/
}
