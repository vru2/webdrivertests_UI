package paymentsBento_Itn_PWA;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import paymentsBento_Itn.PaymentsBento_Itn_Common;

public class PWA_Air_Common extends PaymentsBento_Itn_Common {
	
	public void PWA_Searchpagebook(RemoteWebDriver driver,String wallettype, String domain, String cardtype) throws Exception {
		textPresent(driver, "Departure time", 10);
		safeClick(driver, By.xpath("//div[2]/div/div[2]/div/div[2]/div"));
	}
	
	public void PWA_book_itnnew(RemoteWebDriver driver, String gv_coupon) throws Exception {
		PWA_Itinerary_Block(driver, gv_coupon);
		PWA_AddOns(driver,"");
		PWA_Traveller_Block(driver,"");
	}
	
	public void PWA_Itinerary_Block(RemoteWebDriver driver, String gv_coupon) throws Exception {
		textPresent(driver, "Review Itinerary", 10);
		safeClick(driver, By.xpath("//button"));
	}
	
	public void PWA_AddOns(RemoteWebDriver driver, String AddON) throws Exception {
		textPresent(driver, "Add ons", 10);
		safeClick(driver, By.xpath("//button"));
		textPresent(driver, "Skip", 10);
		safeClick(driver, By.xpath("//button"));
		safeClick(driver, By.xpath("//div[4]/div[3]"));
		Thread.sleep(5000);
		
	}
	
	public void PWA_Traveller_Block(RemoteWebDriver driver, String Traveller) throws Exception {
		textPresent(driver, "Add ons", 10);
		safeClick(driver, By.xpath("//button"));
		textPresent(driver, "Review Travellers", 10);
		safeClick(driver, By.name("title"));
		safeSelect(driver, By.name("title"), "Mr");
		safeClick(driver, By.name("firstName"));
		safeType(driver, By.name("firstName"), "Kiran");
		safeClick(driver, By.name("lastName"));
		safeType(driver, By.name("lastName"), "Kumar");
		safeClick(driver, By.name("phone"));
		safeType(driver, By.name("phone"), "1212121212");
		safeClick(driver, By.name("email"));
		safeType(driver, By.name("email"), "testcltp29@gmail.com");
		safeClick(driver, By.xpath("//button"));
	}
	
	public void PWA_PaymentPage(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType,String BankName) throws InterruptedException {
		textPresent(driver, "Payments", 10);
		
	}
		
	
}