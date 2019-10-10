// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.payment.nodejs;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.cleartrip.platform.paymentsAPI.API_PaymentCommon;

import io.restassured.response.Response;

	public class Payment_NodeJS_Create_GV extends API_PaymentCommon{
	
	
  @Test 
  public void paymentNodeJS_CCBH() throws Exception {
	  for (int i = 0; i < 1; i++) {
		
	  String[] GV = getGV(10);
		System.out.println("GV "+GV[0]+":"+GV[1]);
  }
  }
  }
