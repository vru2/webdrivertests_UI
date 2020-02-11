// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.util.Random;

public class API_OLA_PromoStatus extends API_PaymentCommon
{
	
	Random rand = new Random();
	int n = rand.nextInt(999);
	String PromoID = Integer.toString(n);
	
}
