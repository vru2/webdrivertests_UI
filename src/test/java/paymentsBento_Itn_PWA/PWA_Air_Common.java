package paymentsBento_Itn_PWA;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import paymentsBento_Itn.PaymentsBento_Itn_Common;

public class PWA_Air_Common extends PaymentsBento_Itn_Common {
	
	public void PWA_Searchpagebook(RemoteWebDriver driver,String wallettype, String domain, String cardtype) throws Exception 
	{
		
	}
	
	public void PWA_book_itnnew(RemoteWebDriver driver, String gv_coupon) throws Exception {
		PWA_Itinerary_Block(driver, gv_coupon);
		PWA_AddOns(driver,"");
		PWA_Contact_Block(driver,"");
		PWA_Traveller_Block(driver,"");
	}
	
	public void PWA_Itinerary_Block(RemoteWebDriver driver, String gv_coupon) throws Exception {
		
	}
	
	public void PWA_AddOns(RemoteWebDriver driver, String AddON) throws Exception {
		
	}
	
	public void PWA_Traveller_Block(RemoteWebDriver driver, String Traveller) throws Exception {
		
	}
	
	public void PWA_Contact_Block(RemoteWebDriver driver, String SignIN) throws Exception {
		
	}
	
	public void PWA_PaymentPage(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType,String BankName) {
		
	}
		
	
}