// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsUI;

import org.testng.annotations.Test;

import test.java.paymentsAPI.API_PaymentCommon1;

	public class Payment_NodeJS_Create_GV extends API_PaymentCommon1{
	
	
  @Test  
  public void paymentNodeJS_CCBH() throws Exception {
	  for (int i = 0; i < 1; i++) {
		
	  String[] GV = getGV(10);
		System.out.println("GV "+GV[0]+":"+GV[1]);
  }
  }
  }
