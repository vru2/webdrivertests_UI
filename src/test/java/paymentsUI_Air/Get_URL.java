// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Get_URL extends PaymentUI_Common{
	
	
	@Test
	public void URL() throws Exception {
	String PayURL= getPayUI("Air", "");
	Reporter.log(PayURL);
	System.out.println(PayURL);
	}


	
}
