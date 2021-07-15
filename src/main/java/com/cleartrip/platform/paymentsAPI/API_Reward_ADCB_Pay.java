// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.paymentsAPI;

import java.util.Random;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Reward_ADCB_Pay extends API_PaymentCommon
{
	
	Random rand = new Random();
	int n = rand.nextInt(9999999);
	String trackid = Integer.toString(n);
	String track = "CLR"+ trackid;
		
	@Test
	public void adcb_Pay() throws Exception{
		Response resp ;		

		Reporter.log("------ Check Balance Started -------");

		resp = reward("ADCB_CheckBalance",track);	
		validation("adcb_checkBalance", resp);

		Reporter.log("------ Send OTP Started -------");
								
		resp = adcb("sendOTP",track);	
		validation("adcb_sendOTP", resp);

		Reporter.log("------ Validate Started -------");		

		resp = adcb("validate",track);	
		validation("adcb_validate", resp);		
		
		Reporter.log("------ Pay Started -------");

		resp = adcb("pay",track);	
		validation("adcb_pay", resp);
		
		Reporter.log("------ Pay  Started cwith same trackID payment should not go through-------");
		
		resp = adcb("pay",track);	
		validation("adcb_pay2", resp);	

		Reporter.log("------ Pay 2nd time validation Passed -------");
	}
}
