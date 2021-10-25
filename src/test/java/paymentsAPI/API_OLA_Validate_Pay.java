// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsAPI;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_OLA_Validate_Pay extends API_PaymentCommon1
{
	Random rand = new Random();
	int n = rand.nextInt(99999);
	String PromoID = Integer.toString(n);
	
	@Test
	public void paymentOTP_Validate() throws Exception{
		Response resp ;		
		resp =  ola("Validate","", PromoID);	
		validation("OLAVALIDATE", resp);
	}
	
	@Test(dependsOnMethods = {"paymentOTP_Validate"})
	public void paymentOTP_Pay() throws Exception{
		Response resp ;		
		resp =  ola("pay","", PromoID);	
		validation("OLAPAY", resp); 		
	}
	
	@Test(dependsOnMethods = {"paymentOTP_Pay"})
	public void paymentOTP_CheckStat() throws Exception{
		Response resp ;		
		resp =  ola("Stat","", PromoID);	
		validation("OLAStat", resp);
	}
	
	@Test(dependsOnMethods = {"paymentOTP_CheckStat"})
	public void paymentOTP_PromoStat() throws Exception{
		Response resp ;		
		resp =  ola("PromoStatus","", PromoID);
		validation("OLAPromoStatus", resp);
	}
}
