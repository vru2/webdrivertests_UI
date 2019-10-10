
// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.payment.nodejs;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import domainServices.PaymentNodeJS;

		public class GV_Creation extends PaymentNodeJS{
		public RemoteWebDriver driver;
		
	  @Test 
	  public void GV() throws Exception {
		 for (int j = 0; j <1; j++) {
		           GV_Creation(driver, "1200");
			}
		  }
	  }