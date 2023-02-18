// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.commonUI;

import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import io.restassured.response.Response;


public class PaymentUI_Common_Bento extends PaymentUI_Common {
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	public Cookie cookie_Bento_Payment = new Cookie("isBentoPayment", "true");
	public Cookie cookie_Bento_Payment_EMI = new Cookie("payment_cc_emi_desktop", "true");
	public Cookie cookie_Bento_Hotels = new Cookie("bento_itin", "true");
	public Cookie cookie_Bento_HotelsNEW = new Cookie("h_exp16", "b");
	public Cookie cookie_Parl_Wallet = new Cookie("ct-auth", "EVefRmmOWPSC8c9sPGbZGwZMgfl%2FLjP6yfQQAwhPONaOOIjRmfrMO5ubb5%2FGLWzguQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2me%2FiLCzDjUE8Mm7nMigogz0z84lf%2Bili9Xzawt1KbN%2FMNpQDroZvb3Q7ub%2BLj1YfofQs%2BDG9mD5DXvLFNSWqYz93GfvGpnfyFmIRy226HjYgQ%3D%3D");
	//public Cookie cookie_Full_Wallet = new Cookie("ct-auth", "O2HJIm5w1xSz%2BJuS4aDuK7gVEOwk4Wtqdan6btFwj4TyQ8aSq%2BF4m20vjT%2FZugfuD3habrO078UoXdzWM34lXZaLbE1jIpkEaANLn%2BHJadeW7kll2UfWWUfOoZLsVWTEc0QAdvEUdBA%2F1nzmjBhQdfKHBynkHeYDz6XwPLe1MJiHeiMeqTJEkDsxZaWHAi%2Bv");	
	public Cookie cookie_Full_Wallet = new Cookie("ct-auth", "QYqE9uech2apyQ1uZeSIm%2Biz6I1u9AiJowmGBaxiRKMqE8P953oYVntCR0SJJ7eQW%2FX9HXgO9kidldUQ8q4npkhV1B3OjqN%2Blj%2FywDNYZMjgUXM5JAnhVDA45gmzA8Pizn%2BSaRCsCJO4qK6o3WKDKpud0nmE4TDzaeJNsQHQ2mLsvmw%2FloTY%2FWDTIvGCxoTH");
	//ct_storedcard@cleartrip.com
	public Cookie cookie_StoredCard = new Cookie("ct-auth", "rbvyqtMD%2B%2B2D4IuzY4zuIRBY6YkAlHcLGjbPfY7%2FLBY2N%2F%2Buerv4PZWlJbAGD5wwbiqurLmLCTGawNuDxccW8ULcQXyAJl%2BKYgmuLWYgm7W8d3Cxpy9U3hwGuorQpT8%2ByP6ob0oI8PorTkCfLhoyGqJzZb9f1us8iRaXyQVLiLFXlVEbS%2BIomnNuo9OG71wq");	
	protected String username="varalakshmi.venkateshaiah@cleartrip.com";
	public Cookie ctauth=new Cookie("ct-auth","4eObn84%2Bk5aM7fqBP2kNvawrXVhbqTQbDGfyKoLD2KQOSQQpBHfijuyU%2FYJUc9OL8%2BbG%2BmR3IqzUF2rTzN7I5piHO4MxT9yU5Yto9sTx1rJfUfqwCOnfM5iisxvTtMxNphvkpCTs7Lhtzd44BCgvhDWsk5vS5U1OMMj3SmUJHCIJILOSPzhSOmPPD5DhsrFodefKYF%2FCGsqt2Jc5Neqc5eNslyD2knSAOhkndq4nRwdZcWFYlbS35tLLNHllptE3xY971Hav33t3IplfVZtxHyhQzFkkJlykZg0H%2Fu3zyt06tRi4rH7PPBC312VJneKDw%2FEnV%2FDTUW%2B%2FeKpKXG5Kls4al6piN%2Bbl7nn5pwjx0oT5Zv6fyW1WUEmtAlz3EDevQWOQ7N9ZaGtMfLQipc6G1JAQ3tYq%2FssUg6PQCwg%2B%2FeYJcA%2B1hpZo5%2BFbJz7QvrKoAQ0IvA7vEBDcKqzSKc3KUqCVTE%2B7vHyTj%2FPJ%2FnA2hEVDGDwCHr03QOhwwinKGMiVEXVIdSKo%2FFeskPXPTykpeWPZe%2BJ023nxHctXj2%2F1%2B9D%2FOLN5WXwkfmrzccdGByGgnju4QG5kcHvQZEYAoIHAKiiwrwKV7Ur%2FJZaW5lkRI%2B1VH8jWTdkSi3DV8IEVatWIYbHPxx5j8tGLTYvXyqa3GyGooJsBQGPrhYqSqttcFfCmllmQBvGGM%2FUBA02WuW%2B2%2By4L702JOuUbaS8cYCj92Jr946W5OVM%2B7BkTPuRhiv6a1umTXrxNoMIOPb5QKcEE3ziHnIiEIRJxv%2F3ov6iOZSoTqPCX0RTS2u2dGysRaY8IvwklhUIQrMMkC5iXqTFIOMcjpKJad%2FkktMiYmnIHokswV%2BuQoLK9RKIjReGFMlEhoadqhVIXczaZBYfQyW2WJZLB0%2BKY6qkiR7%2FdZ2ZdgKlRjfpDzM%2FSaJ7uLgu9co4H28DyHFPlv8VKHuyExhLe4200vlQVSlGINGubzED6jFKRDhCYDomMa7mU4aR%2BBFbBP0MoxTzQP7r8AuAEw44rfCcRJ7Sa0BFosw6zlKV%2FPVcZhVDeZhQlIHhVrn5mK1KYsbM54wrT4IOeuGEwVtJ2sh94UhlIgwQvE3RBvxmTAaJQhjp9%2FF0FQXZmOmg9QWShAhytzglleNq9FAsKyd8pWGJP28o42jsMz9GlJD75u%2BSX0zwsC%2Fh0Gq5hkf9X0B2u5KK4EfEoWtekR7Z7mXqUG8vigwDzk0LiGJmRFOeBKGCAh6drrlmpx%2BWlxikxm1q6vK4TiPMm3Pvy9HZMLEFU%2FbyG%2BuduJD%2BpNy06UlKdWfWn7N6rz0pV9ERPnA%2Fzxik1vK%2BlbxoIkTOxeVMYoTzx%2BRDEyo9MjCQlcI5K%2BXqy75cBiovhF%2FKcf00zq%2B4kHLbNgQWHsPxU03SG2PnNTTKvShsNtANjRoE31K8EhQCUxAy0RKUSZCsC9rC3NzCyiZQTvrXnMEr6FjJXE5K4ROWHnDjlNnuMOHjui7YmOJO2C5PBPoPgrJ%2B1aIzT21%2FzQzLiRo7keRlxWOHKbmV4hFcfNX%2FDY4ntaQ96pwUizeqbjD5p5svT%2BJUcF%2BcG8pmd8%2FIBe%2FhGz3kmIV4qcAxe1djoRcJom5g8g%2FfWWSBE8Rvhr3ZsFai68ZMULGQ4s1tKqJbnbr780dn%2FOSPEknKZPIv8dkfL3J%2B23%2FA1YIzFy95ByZvIgaTx5P5F8Hpykik9vgS9K%2FjiW0WH3IXVWki88SCUVi%2BVta4slJgS%2Be6rODov4AqGe9JHHmx4bSxUoOEdz%2BJeBexeeQ4x9jCiGxA%2BzgjmiBpgBO3HtA9aqWZN4q3J2ixi3GIqBCVEajm6X7f5eySokE%2FtnVhY4lE2NV91AmgTC9JPxlx29Cr0ngYx6%2FJR4sTtmeG9ZTFO6IxpJES3HaACXGG8iuQ7nT3DU%2BghAjFBd5HKozcaZuGOsw%2FxsSHcxCg3j9ehv%2Fk8%2BkWf%2FsxFfhjznpXvBUxH5gkp%2BsOErD2Q5NMWuyFdqg2Mei5Lvxe%2B2TobhYvcM6zkOqi%2B4%2F46qni8Dj4YppjJwoHqDuMu1qnOYRElc4aMUh6y%2B3X0VtqUQcKWMtV4GI1Hd1YXWkV4EHF80ojK%2FD%2Bm2NfEQuwtcwP5RlqZAjhYi%2BhaZbQm4THN1jIac%2BQMVECcA5YG78D9s%2BkvudTrT70vGXB09xwiz7KkbTzvVu8WKEAabfp0qI2DLe06E6%2BFEw%2Bb297hoRaUJOjNBMxFWnWNGEhNmjiNuK%2FT%2BFbl7FpgUg%2Bu057vX4zqbOrAC2P5zmLq3NP1zw5DUpM59SknIYnb8Ce9K2MTXDmfGnRDkkGOp2fhQ0%2BAw%2BzTy2NHSoFWgdaU2fw2pfPVBzVhjF0xUke73S8VOWyc2uO%2FEX9sg%2BRpvAlrZSVMY%2BTb8xy0l%2FT6WFPss44ETRQw6Ig66G%2F%2FMhEf4RmbIwovgQwOVIlAJR8a12guJZRrnk5mZ2AENQtzgUUeO%2FaWOYyAdL5UExD%2BfeJ8NdKorIczoq2at1mFoIGgnEk7t8KnGkImFah05R%2Boj1pg%2BzSqDApBxv%2BnKRPkddAWNPfmAMwe3%2By4vaCnp%2B%2BNOc1Sv6lKOkuuzCBY%2Budso0wBpKq9nyyBi8C%2F6cqt56zT%2BBzMGFfNBYVCik03cY4b7SWx6mz%2F2qGuZlSQx9iC3js%2BSiuUJ4PW6y8sWB6BqDCSoTW5tYtPJsndsgv7ZU4DjPkTdvAepvEbdIzFMJqF0fhPH7a1nVlHJvlFn1DKUmqJ0bCyMRPWFpH2q7DXnrq33hkOE1hVMVVE%2FJkKOw66J7UuK5HT1gHExMkIQ7OJUxhsPngckMCt19XGmirQSeV7Q4pgHJp97ptBoqcv9tQLnl4xkpjdddZi%2BfFlWWZSLKP8NNjGbIkjQdlq70uw9tQBU2QT4JTnqP%2FZp1PEdlsnbKyx3Mit36uLkaKB7geFSLzivGtYGbNlodU0SfQ0s%2FOgvfn1naVGf7viIjP1Q9Ds%2Fyk4VK%2FfP%2FsFAQpAyOWUJ8mCGDzIPb2EMK1DIhiN2T06YXg%2BZQxV7MgDzg%3D%3D");
	public Cookie ctauth_partial_wallet=new Cookie("ct-auth","x%2F4IqrYI2rGRmYUEgqsFyJtuH64oYn%2FF9ao%2FFN%2BfplBVjyBPJv6PHh1sAW0TIa%2BBH8YOfEGj8AeevvMX%2F4QnQu5pne5K5EHLAFvUZ60PN8K8qX%2FBnweQFNfqHv2MpXaBrz1TDJgcj3KYoQs86tYYOkxbSn4KngmhppaUHjGty5%2F5GK2NTQEX8p2y7NWfDr9%2F");
	public Cookie bentoitn=new Cookie("forcedBentoItn","true");

	String GV_number = "3000331036453430";
	String GV_pin = "152279";
	String searchurl="https://qa2.cleartrip.com/flights/results?adults=1&childs=0&infants=0&depart_date=27/12/2021&return_date=&intl=n&from=HYD&to=BLR&class=Economy&airline=&carrier=&sd=1617771121185&page=&sellingCountry=IN&ssfi=&flexi_search=&ssfc=&origin=HYD%20-%20Hyderabad,%20IN%20&destination=BLR%20-%20Bangalore,%20IN";
	

	

	
	public void bento_Select_PaymentType(RemoteWebDriver driver, String PayType) throws Exception {
		for (int i = 0; i < 2; i++) {			
			if(textPresent(driver, "System error", 1)) {
			Reporter.log("There's something wrong with our system");			
			Assert.assertTrue(false);
		} else if(textPresent(driver, "Oops, Something went wrong", 1)) {
			Reporter.log("Oops something wrong with our system");			
			Assert.assertTrue(false);
		}
		if(elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 1)) {
			break;
		}
		
		}
		if(textPresent(driver, "Your wallet balance is sufficient", 2)) {
			safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
		}
		if(!elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 5)) {
			Reporter.log("PayUI Page is not displayed");
			String UI_error = getText(driver, By.xpath("//nav/div/p"));
			Reporter.log(UI_error);			
			Assert.assertTrue(false);
		}
		switch (PayType) {
		case "CC":
			PayType = "Debit/Credit card";
			break;
		case "NB":
			PayType = "Net banking";
			break;
		case "TW":
			PayType = "Wallets";
			break;
		case "UPI":
			PayType = "UPI";
			break;
		case "EMI":
				PayType = "EMI";
				break;
		case "ADCB":
			PayType = "ADCB TouchPoints";
			break;
		case "SC":
			PayType = "Saved cards";
			break;
		case "KNET":
			PayType = "KNET";
			break;
		case "PayPal":
			PayType = "PayPal";
			break;
		default:
			PayType = "Debit/Credit card";
			break;
		}		
		safeClickList(driver, getObjectPayment("Bento_Pay_Tabs"), PayType);	
	}
	
	
	public void bento_Select_PaymentType_AR(RemoteWebDriver driver, String PayType) throws Exception {
		for (int i = 0; i < 2; i++) {			
			if(textPresent(driver, "System error", 1)) {
			Reporter.log("There's something wrong with our system");			
			Assert.assertTrue(false);
		} else if(textPresent(driver, "Oops, Something went wrong", 1)) {
			Reporter.log("Oops something wrong with our system");			
			Assert.assertTrue(false);
		}
		if(elementVisible(driver, getObjectPayment("Bento_Pay_Tabs_AR"), 1)) {
			break;
		}
		}
		if(!elementVisible(driver, getObjectPayment("Bento_Pay_Tabs_AR"), 5)) {
			Reporter.log("PayUI Page is not displayed");
			String UI_error = getText(driver, By.xpath("//h1"));
			Reporter.log(UI_error);			
			Assert.assertTrue(false);
		}
		switch (PayType) {
		case "CC":
			PayType = "بطاقة مدى / البطاقة الإئتمانية";
			break;
		case "ADCB":
			PayType = "ADCB TouchPoints";
			break;
		case "PayPal":
			PayType = "باي بال";
			break;
		default:
			PayType = "Debit/Credit card";
			break;
		}		
		safeClickList(driver, getObjectPayment("Bento_Pay_Tabs_AR"), PayType);	
	}
	
	public void bento_Enter_PaymentDetails(RemoteWebDriver driver, String PayType, String BankName, String BookingType) throws Exception {
		switch (PayType) {
		case "CC":
			bento_Select_CC(driver, BankName, BookingType);
			break;
		case "DC":
			bento_Select_DC(driver, BankName, BookingType);
			break;
		case "NB":
			bento_Select_NB(driver, BankName, BookingType);
			break;
		case "TW":
			break;
		case "UPI":
			bento_Select_UPI(driver, BankName, BookingType);
			break;
		case "ADCB":
			bento_Select_ADCB(driver, BankName, BookingType);
			break;
		case "KNET":
			bento_Select_KNET(driver, BankName, BookingType);
			break;
		default:
			break;
		}		
	}
	
	public void bento_Select_CC(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
		elementVisible(driver, getObjectPayment("Bento_Pay_CreditCard_Number"), 5);
		textPresent_Log(driver, "Enter card details", 1);
		switch (BankName) {
			case "MASTER":
			bento_Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;	
			case "AMEX":
			bento_Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
			break;			
			case "AMEXTRAIN":
			bento_Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
			break;
			case "CAPTCHA":
			bento_Enter_CC_Details(driver, "512345678901234", platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;
			case "CHECKOUT":
			bento_Enter_CC_Details(driver, platform.value("SACheckOut_Number"), platform.value("SACheckOut_Month_UI"), platform.value("SACheckOut_Year"), platform.value("SACheckOut_CVV"));
			break;
			case "PAYFORT":
			bento_Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;	
			case "NOON":
			bento_Enter_CC_Details(driver, platform.value("Noon_Number"), platform.value("Noon_Month_UI"), platform.value("Noon_Year"), platform.value("Noon_CVV"));
			break;
			case "RAZORPAY":
			bento_Enter_CC_Details(driver, platform.value("RazorPay_Number"), platform.value("RazorPay_Month_UI"), platform.value("RazorPay_Year"), platform.value("RazorPay_CVV"));
			break;
		}
		if(common.value("Bento_Payment").equalsIgnoreCase("true")||BookingType.contains("TRAINS")) {			
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		
		Reporter.log("Make Payment button is Clicked");
		if(textPresent(driver, "Internal server error", 5)) {
			Reporter.log("Internal server error is displayed after Clicking Make Payment");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Hmm, something's not right", 1)) {
			Reporter.log("Hmm, something's not right. is displayed after Clicking Make Payment");
			Assert.assertTrue(false);
		}		

		if(textPresent(driver, "Error in credentials entered. Verify your details and try again", 1)) {
			Reporter.log("Error in credentials entered. Verify your details and try again is displayed after Clicking Make Payment");
			Assert.assertTrue(false);
		}
		if(!BankName.contains("CAPTCHA")) {
		payUI_BankPage(driver, BankName);
		}
		}
	}
	
	public void bento_Enter_CC_Details(RemoteWebDriver driver, String CCNumber, String CCExpMonth, String CCExpYear, String CVV) throws Exception {
		Reporter.log("Card Details +\n"+ CCNumber +"\n " + CCExpMonth  +" " + CCExpYear +" " + CVV);
		safeType(driver, getObjectPayment("Bento_Pay_CreditCard_Number"), CCNumber);
		safeClick(driver, getObjectPayment("Bento_Pay_CreditCard_Exp_Month"));
		safeSelect(driver, getObjectPayment("Bento_Pay_CreditCard_Exp_Month"), CCExpMonth);
		safeClick(driver, getObjectPayment("Bento_Pay_CreditCard_Exp_Year"));
		safeSelect(driver, getObjectPayment("Bento_Pay_CreditCard_Exp_Year"), CCExpYear);
		safeType(driver, getObjectPayment("Bento_Pay_CreditCard_Name"), "test");
		safeType(driver, getObjectPayment("Bento_Pay_CreditCard_CVV"), CVV);
	}

	public void bento_Select_DC(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
		elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
		textPresent_Log(driver, "Enter your debit card details.", 1);
		switch (BankName) {
			case "MASTER":
			Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;	
		}

		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Reporter.log("Make Payment button is Clicked");
		if(textPresent(driver, "Internal server error", 5)) {
			Reporter.log("Internal server error is displayed after Clicking Make Payment");
			Assert.assertTrue(false);
		}
		if(!BankName.contains("CAPTCHA")) {
		payUI_BankPage(driver, BankName);
		}
		}
}
	
	public void bento_Select_ADCB(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
		elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
		textPresent_Log(driver, "Enter your ADCB card details", 1);		
		Reporter.log("Card Details +\n"+ platform.value("ADCBCard_Number") +"\n " + platform.value("ADCBCard_Expiry_Month")  +" " + platform.value("ADCBCard_Expiry_Year") +" " + platform.value("ADCBCard_CVV"));
		safeType(driver, getObjectPayment("PaymentPage_ADCB_Number"), platform.value("ADCBCard_Number"));
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_EXP_Month"));
		safeSelect(driver, getObjectPayment("PaymentPage_ADCB_EXP_Month"), platform.value("ADCBCard_Expiry_Month"));
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_EXP_Year"));
		safeSelect(driver, getObjectPayment("PaymentPage_ADCB_EXP_Year"), platform.value("ADCBCard_Expiry_Year"));
		safeType(driver, getObjectPayment("PaymentPage_ADCB_CardName"), "test");
		safeType(driver, getObjectPayment("PaymentPage_ADCB_CVV"), platform.value("ADCBCard_CVV"));
		String CheckBalance = getText(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Assert.assertEquals(CheckBalance, "Check touchPoint balance");		
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_CheckBlance_Btn"));		
		Reporter.log("Check balance is Clicked");
		if(textPresent(driver, "You have provided incorrect card details", 10)) {
			Reporter.log("You have provided incorrect card details");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Unable to process your request. Please try again later", 1)) {
			Reporter.log("Unable to process your request. Please try again later");
			Assert.assertTrue(false);
		}
		elementVisible(driver, getObjectPayment("PaymentPage_ADCB_Redeem_Amount_TextBox"), 10);
		textPresent_Log(driver, "A minimum amount of AED", 1);
		textPresent_Log(driver, "Available balance", 1);
		textPresent_Log(driver, "Balance touchPoints", 1);
		textPresent_Log(driver, "Amount to redeem", 1);
		textPresent_Log(driver, "A minimum amount of AED", 1);
		textPresent_Log(driver, "50 must be redeemed", 1);		 
		textPresent_Log(driver, "Total payable", 1);
		textPresent_Log(driver, "Amount redeemed", 1);
		textPresent_Log(driver, "Balance payable", 1);
		if(BookingType.contains("ADCBPARTIAL")) {
			safeType(driver, getObjectPayment("PaymentPage_ADCB_Redeem_Amount_TextBox"), "AED 50");
			elementVisible(driver, By.xpath("//li[3]/p[2]"),20);
			String AED50 = getText(driver, By.xpath("//li[3]/p[2]"));
			Assert.assertEquals(AED50, "AED  50");
		}
		if(BookingType.contains("ADCBFULL")) {		
			String Total = getText(driver, By.cssSelector("span.fs-6.fw-700"));
			String Balance = getText(driver, By.xpath("//li[5]/p[2]"));
			Assert.assertEquals(Total, "AED  0");
			Assert.assertEquals(Balance, "AED  0");
			String RedeemBtn = getText(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			Assert.assertEquals(RedeemBtn, "Redeem");			
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));		
			Reporter.log("Redeem button is Clicked");
			elementPresent_log(driver, By.id("OTP"), "OTP", 20);
			textPresent_Log(driver, "Enter One-Time Password", 1);
			textPresent_Log(driver, "Booking amount", 1);
			textPresent_Log(driver, "Enter the OTP sent to your registered mobile number", 1);
			textPresent_Log(driver, "Haven't received the OTP?", 1);
			elementPresent_log(driver, By.linkText("Resend"), "resend OTP link", 1);
			safeType(driver, By.id("OTP"), "101010");
			elementPresent_log(driver, By.xpath("//form/button"), "Confirm OTP", 2);
			Reporter.log("Confirm OTP button is displayed");
			String ConfirmBt = getText(driver, By.xpath("//form/button"));
			Assert.assertEquals(ConfirmBt, "Confirm OTP");		
		}
		}

	public void bento_Select_KNET(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
		elementVisible(driver, getObjectPayment("PaymentPage_Knet_RadioButton"), 5);
		safeClick(driver, getObjectPayment("PaymentPage_Knet_RadioButton"));
		elementPresent_log(driver, getObjectPayment("PaymentPage_Knet_Image"), "", 1);

		if(common.value("Bento_Payment").equalsIgnoreCase("true")|| BookingType.contains("TRAINS")) {		
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Reporter.log("Make Payment button is Clicked");
		}
		
	}
	
	public void bento_Select_NB(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
			elementVisible(driver, getObjectPayment("Bento_Pay_NB_DropDown"), 5);
			textPresent_Log(driver, "Popular banks", 1);
			//textPresent_Log(driver, "", 1);
			if(BankName.contains("CAPTCHA")) {
				safeSelect(driver, getObjectPayment("Bento_Pay_NB_DropDown"), "Citibank");
			}
			else if(BankName.contains("BOI")) {
				safeClick(driver, getObjectPayment("Bento_Pay_NB_DropDown"));
				//safeSelect(driver, getObjectPayment("Bento_Pay_NB_DropDown"), "Bank of india");
				safeSelectByText(driver, getObjectPayment("Bento_Pay_NB_DropDown"), "Bank of India");
				
			}
			else if(BankName.contains("CitibankPopular")) {
				safeClick(driver, getObjectPayment("PaymentPage_NB_PopularBanks_Citi"));
			}
			else if(BankName.contains("AxisbankPopular")) {
				safeClick(driver, getObjectPayment("PaymentPage_NB_PopularBanks_Axis"));
			}
			else safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), BankName);

			if(common.value("Bento_Payment").equalsIgnoreCase("true")||BookingType.contains("TRAINS")) {
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			payUI_BankPage(driver, BankName);
			}
			
	}	
	
	public void bento_Select_UPI(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {	

		textPresent(driver, "Select UPI partner to make your payment", 5);
		elementVisible(driver, getObjectPayment("SelectPayment_UPI_PhonePe"), 5);
		safeClick(driver, getObjectPayment("SelectPayment_UPI_PhonePe"));		

		if(common.value("Bento_Payment").equalsIgnoreCase("true")||BookingType.contains("TRAINS")) {
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_BankPage(driver, BankName);
		}
	}	
	
	public void bento_Mock_ConfirmationPage(RemoteWebDriver driver, String PayUrl) throws InterruptedException {

		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
		for (int i = 0; i <=10; i++) {
			String returnUrl  = getURL(driver);
			if(returnUrl.contains("paymentservice/return")) {
				Reporter.log("Refreshing PayUI page to check the Payment Status");
				driver.get(PayUrl);	
				textPresent_Log(driver, "Payment successful", 20); 
				Reporter.log("Payment successful");			
				break;
			}
			else if(i!=10) {
				driver.get(PayUrl);
				if(textPresent(driver, "Payment successful", 5)) {
					Reporter.log("Payment successful");					
					break;
				}
			}
			else if(textPresent_Log(driver, "Oops, Something went wrong", 1)) {
				Reporter.log("Oops! Your payment failed.");
				Assert.assertTrue(false);
			}			
			else Assert.assertTrue(false);
					
			Thread.sleep(1000);
			}
		}
	}
	
	public void bento_Validation_Text(RemoteWebDriver driver, String PaymentType, String Domain) throws Exception {
		if(PaymentType.equalsIgnoreCase("CC")) {
			if(Domain.equals("FLYIN")) {
				elementPresent_log(driver, getObjectPayment("FlyIN_Logo"), "FlyIN_Logo"	, 5);
				String title = driver.getTitle();
				if(!title.contains("Flyin | Pay securely")) {
					Reporter.log("Page title "+title);
					Assert.assertTrue(false);
				}
			}
			else if(Domain.contains("FLYINAR")) {
				elementPresent_log(driver, getObjectPayment("FlyIN_Logo"), "FlyIN_Logo"	, 5);
				String title = driver.getTitle();
				if(title.contains("Flyin | Pay securely")) {
					Reporter.log("Page title "+title);
					Assert.assertTrue(false);
				}
			}
			else {
				String title = driver.getTitle();
				if(!title.contains("Cleartrip | Pay securely")) {
					Reporter.log("Page title "+title);
					Assert.assertTrue(false);
				}
			}
			if(Domain.equals("FLYINAR")) {
				bento_Select_PaymentType_AR(driver, "CC");
				textPresent_Log(driver, "ادفع لإتمام الحجز", 1); 
				textPresent_Log(driver, "ستبدال نقاط قطاف لهذا الحجز", 1);
				textPresent_Log(driver, "بطاقة مدى / البطاقة", 1);
				textPresent_Log(driver, "الدفع بالبطاقة", 1);
				textPresent_Log(driver, "رقم البطاق", 1);
				textPresent_Log(driver, "تاريخ الانتهاء", 1);
				textPresent_Log(driver, "إسم صاحب البطاقة", 1);
				textPresent_Log(driver, "رمز التحقق من البطاقة", 1);
				textPresent_Log(driver, "السعر الإجمالي، شامل جميع الضرائب", 1);
				textPresent_Log(driver, "ر.س.", 1);
				textPresent_Log(driver, "المبلغ المطلوب", 1);
				textPresent_Log(driver, "ملخص الحجز", 1);
				textPresent_Log(driver, "استبدال نقاط قطاف لهذا الحجز", 1);
				
				
				textPresent_Log(driver, "استبدل الآن", 1);
				
				//  price BREAKUP
				textPresent_Log(driver, "المبلغ المطلوب", 1);
				textPresent_Log(driver, "السعر الأساسي (1 مسافر", 1);
				textPresent_Log(driver, "الضرائب والرسوم", 1);
				textPresent_Log(driver, "رسوم أخرى", 1);
				
				//  booking Summary
				
				textPresent_Log(driver, "ملخص الحجز", 1);
				textPresent_Log(driver, "1 مسافر", 1);
				
			//  Terms & conditions
				textPresent_Log(driver, "لسعر الإجمالي، شامل جميع", 1);
				textPresent_Log(driver, "الضرائب", 1);
				textPresent_Log(driver, "© 2006–2021 Saudi Ebreez Company", 1);
				
				
				
				
				
				textNotPresent_Log(driver, "You pay", 1);
				textNotPresent_Log(driver, "Base fare (1 traveller)", 1);
				textNotPresent_Log(driver, "Taxes and fees", 1);
				textNotPresent_Log(driver, "Flexifly", 1);
				textNotPresent_Log(driver, "Travel Insurance", 1);

				textNotPresent_Log(driver, "Pay to complete your booking", 1);
				textNotPresent_Log(driver, "Enter card details", 1);
				textNotPresent_Log(driver, "Card number", 1);
				textNotPresent_Log(driver, "Expiry date", 1);
				textNotPresent_Log(driver, "Card holder Name", 1);
				textNotPresent_Log(driver, "Pay to complete your booking", 1);
				//textNotPresent_Log(driver, "CVV", 1);
				
				//paypal
				

				safeClick(driver, By.xpath("//nav/div[3]"));
				textPresent_Log(driver, "الدفع بواسطة باي بال", 1);
				
			}			
			else {
			bento_Select_PaymentType(driver, "CC");
			textPresent_Log(driver, "Pay to complete your booking", 5);
			if(!Domain.equals("FLYIN")) {
				textPresent_Log(driver, "Enter card details", 1);
				}
			else textPresent_Log(driver, "Card Payment", 1);
			textPresent_Log(driver, "Card number", 1);
			textPresent_Log(driver, "Expiry date", 1);
			textPresent_Log(driver, "Card holder Name", 1);
			textPresent_Log(driver, "Pay to complete your booking", 1);
			textPresent_Log(driver, "CVV", 1);
			String CardPlaceHolder = driver.findElement(getObjectPayment("Bento_Pay_CreditCard_Number")).getAttribute("placeholder");
			String NamePlaceHolder = driver.findElement(getObjectPayment("Bento_Pay_CreditCard_Name")).getAttribute("placeholder");
			String CVVPlaceHolder = driver.findElement(getObjectPayment("Bento_Pay_CreditCard_CVV")).getAttribute("placeholder");
			if(!(CardPlaceHolder.equals("Enter card number")&&NamePlaceHolder.equals("Name as on card")&&CVVPlaceHolder.equals("CVV"))) {
				Reporter.log("PlaceHoder is not correct "+CardPlaceHolder+" "+NamePlaceHolder+" "+CVVPlaceHolder);
				Assert.assertTrue(false);
			}
			textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
		//	textPresent_Log(driver, "and the terms and conditions of Cleartrip", 1);
			textPresent_Log(driver, "Total, inclusive of all taxes", 1);
			textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Pay now", 2);
			}
		}
		else if (PaymentType.equalsIgnoreCase("EMI")) 
		{
            Thread.sleep(3000);
			textPresent_Log(driver, "Pay to complete your booking", 20);
			driver.manage().addCookie(cookie_Bento_Payment_EMI);
			refreshPage(driver);
			/*if(elementVisible(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"),5))
			{
			 safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
			 Thread.sleep(5000);
			}*/
			bento_Select_PaymentType(driver, "EMI");
			textPresent_Log(driver, "Pay to complete your booking", 1);
			textPresent_Log(driver, "Choose an EMI option", 1);
			textPresent_Log(driver, "Cleartrip does not levy any charges for availing EMI. Charges, if any, are levied by the bank. Please check with your bank for charges related to interest, processing fees, refund or pre-closure", 1);
			textPresent_Log(driver, "Show banks with No Cost EMI", 1);
			textPresent_Log(driver, "Popular banks", 1);
			textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
			textPresent_Log(driver, "Total, inclusive of all taxes", 1);
			//textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Enter card details", 2);
			textPresentInElementAssert(driver, getObjectPayment("PaymentPage_EMI_Pay_Btn"), "Continue", 1);
			//elementPresent_log(driver, getObjectPayment("PaymentPage_EMI_ICICIBank_Radio_Btn"), "EMI radio btn", 1);
			
			}
		else if (PaymentType.equalsIgnoreCase("QITAF")) 
		{
			elementPresent_log(driver, getObjectPayment("PaymentPage_QITAF_Redeem_Link"), "Redeem link", 10);
			elementPresent_log(driver, getObjectPayment("PaymentPage_QITAF_Logo"), "Qitaf logo", 10);	
			textPresent_Log(driver, "Redeem Qitaf reward points for this booking.", 1);		
			safeClick(driver, getObjectPayment("PaymentPage_QITAF_Redeem_Link"));
			elementPresent_log(driver, getObjectPayment("PaymentPage_QITAF_Mobile_Number_Text"), "Redeem link", 10);
			safeClick(driver, getObjectPayment("PaymentPage_QITAF_Redeem_Link"));  // Clicking on cancel link
			elementNotPresent_Time(driver, getObjectPayment("PaymentPage_QITAF_Mobile_Number_Text"), 5);
			safeClick(driver, getObjectPayment("PaymentPage_QITAF_Redeem_Link"));// Clicking on redeem link
			safeType(driver, getObjectPayment("PaymentPage_QITAF_Mobile_Number_Text"), "9986696785");
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("PaymentPage_QITAF_Request_Passcode_Btn"));
			elementPresent_log(driver, getObjectPayment("PaymentPage_QITAF_Invalid_Mobile_Error"), "error mesage", 5);
			textPresent_Log(driver, "Powered by STC.", 1);
			textPresent_Log(driver, "Invalid mobile number", 1);
			textPresent_Log(driver, "Registered Qitaf number", 1);
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("PaymentPage_QITAF_KnowMore_Link"));			
			Thread.sleep(5000);
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			String KnowMoreURL = getURL(driver);
			Reporter.log("Booking Policy URL : "+KnowMoreURL);
			if(Domain.equals("FLYIN")) {
				if(!KnowMoreURL.contains("https://www.flyin.com/qitaf")) {
					Reporter.log("Know more URL : "+KnowMoreURL);
					Assert.assertTrue(false);
				}
				textPresent_Log(driver, "Redeem your Qitaf points", 5);
			}
			
				
		}
		
		else if(PaymentType.equalsIgnoreCase("NB")) {
			bento_Select_PaymentType(driver, "NB");
			textPresent_Log(driver, "Pay to complete your booking", 1);		
			textPresent_Log(driver, "Select a bank", 1);
			textPresent_Log(driver, "Popular banks", 1);
			textPresent_Log(driver, "All other banks", 1);		
			textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
			textPresent_Log(driver, "Total, inclusive of all taxes", 1);
			textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Pay now", 2);
		}
		else if(PaymentType.equalsIgnoreCase("TW")) {
			bento_Select_PaymentType(driver, "TW");		
			textPresent_Log(driver, "Pay to complete your booking", 1);		
			textPresent_Log(driver, "Select a wallet", 1);
			textPresent_Log(driver, "Available wallets", 1);
			textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
			textPresent_Log(driver, "Total, inclusive of all taxes", 1);
			textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Pay now", 2);
		}
		else if(PaymentType.equalsIgnoreCase("UPI")) {
			bento_Select_PaymentType(driver, "UPI");
			textPresent_Log(driver, "Pay to complete your booking", 1);
			textPresent_Log(driver, "Enter UPI ID", 1);
			textPresent_Log(driver, "UPI ID", 1);
			textPresent_Log(driver, "Payment request will be sent to the phone no. linked to your UPI ID", 1);
			textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
			textPresent_Log(driver, "Total, inclusive of all taxes", 1);
			textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Show QR Code", 2);
			String UPIIDPlaceholder = driver.findElement(getObjectPayment("Bento_Pay_UPI")).getAttribute("placeholder");
			if(!(UPIIDPlaceholder.equals("Enter your UPI ID"))) {
				Reporter.log("PlaceHoder is not correct "+UPIIDPlaceholder);
				Assert.assertTrue(false);
			}	
		}
		else if(PaymentType.equalsIgnoreCase("SC")) {
			driver.manage().addCookie(cookie_Parl_Wallet);
			refreshPage(driver);
			Thread.sleep(5000);
			smartClick(driver, By.cssSelector("span.checkbox__mark.bs-border.bc-neutral-500.bw-1.ba"));			
			bento_Select_PaymentType(driver, "SC");
			textPresent_Log(driver, "Pay to complete your booking", 1);
			textPresent_Log(driver, "Select a saved card", 1);
			textPresent_Log(driver, "Saved cards", 1);
			textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
			textPresent_Log(driver, "Total, inclusive of all taxes", 1);
			textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Pay now", 2);
		}
		else if(PaymentType.equalsIgnoreCase("PayPal")) {
			bento_Select_PaymentType(driver, "PayPal");
			textPresent_Log(driver, "Pay to complete your booking" ,1);	
			textPresent_Log(driver, "Pay using PayPal" ,1);	
			elementPresent_log(driver, getObjectPayment("Bento_Pay_PayPal_RadioBtn"), "paypal radio button image", 1);
		}
		else if(PaymentType.equalsIgnoreCase("ADCB")) {
			bento_Select_PaymentType(driver, "ADCB");
			textPresent_Log(driver, "Pay to complete your booking" ,1);	
			textPresent_Log(driver, "Enter ADCB card details" ,1);	
			textPresent_Log(driver, "ADCB card number" ,1);	
			textPresent_Log(driver, "Expiry date" ,1);	
			textPresent_Log(driver, "Card holder Name" ,1);	
			textPresent_Log(driver, "CVV" ,1);	
		}
		else if(PaymentType.equalsIgnoreCase("PRICE_BREAKUP")) {
			textPresent_Log(driver, "You pay", 1);
			textPresent_Log(driver, "Base fare (1 traveller)", 1);
			textPresent_Log(driver, "Taxes and fees", 1);
			if(Domain.equals("IN")) {
			/*textPresent_Log(driver, "Flexifly", 1);
			textPresent_Log(driver, "Travel Insurance", 1);*/
			}
			if(Domain.equals("FLYIN")) {

				textPresent_Log(driver, "Other fee", 1);
			}
			else textPresent_Log(driver, "Convenience fee", 1);
			//textPresent_Log(driver, "Cleartrip wallet", 1);
			WebElement ele= driver.findElement(getObjectPayment("Bento_Pay_PriceBreakup_ConvFee_Image"));
			Actions action = new Actions(driver);
			action.moveToElement(ele).perform();
			if(Domain.equals("IN")) {
				textPresent_Log(driver, "Includes a non-refundable convenience fee of ₹ 100 per traveller", 1); 
				Reporter.log("Includes a non-refundable convenience fee of ₹ 30 per traveller -popup is displayed");
			}
			if(Domain.equals("AE")) {
				textPresent_Log(driver, "Includes a non-refundable convenience fee of AED", 1);
				textPresent_Log(driver, "10 per traveller", 1);
				
			}
			else if(Domain.equals("FLYIN")) {
				textPresent_Log(driver, "Includes a non-refundable other fee of SAR", 1);
				textPresent_Log(driver, "11 per traveller", 1);
				
			}
			
			
		}
		
		else if(PaymentType.equalsIgnoreCase("Booking_SUMMARY")) {
			textPresent_Log(driver, "Booking summary" ,1);
			textPresent_Log(driver, "Bangalore", 1);
			textPresent_Log(driver, "(BLR)", 1);
			textPresent_Log(driver, "(HYD)", 1);
			if(Domain.equals("IN")) {
			//textPresent_Log(driver, "09:20, Sat 24 Oct - 11:05, Sat 24 Oct", 1);
			textPresent_Log(driver, "1 traveller", 1);
			textPresent_Log(driver, "(F)", 1);
		/*	textPresent_Log(driver, "Tester Test", 1);
			textPresent_Log(driver, "(F)", 1);*/
			}
			if(Domain.equals("AE")||Domain.equals("KW")||Domain.equals("QA")||Domain.equals("SA")||Domain.equals("OM")||Domain.equals("ME")||Domain.equals("BH")) {
				//textPresent_Log(driver, "13:50, Sat 24 Oct - 15:35, Sat 24 Oct", 1);
				textPresent_Log(driver, "1 traveller", 1);
				textPresent_Log(driver, "(F)", 1);
				//textPresent_Log(driver, "test test (M)", 1);
			}		
			if(Domain.endsWith("FLYIN")) {
				textPresent_Log(driver, "© 2006–2022 Saudi Ebreez Company", 1);
				
			}else textPresent_Log(driver, "© 2006–2022 Cleartrip Pvt. Ltd.", 1);
			textPresent_Log(driver, "Completely safe and secure transactions", 1);
		}
		else if(PaymentType.equalsIgnoreCase("KNET")) {
			bento_Select_PaymentType(driver, "KNET");
			textPresent_Log(driver, "Select a bank", 1);
			elementPresent_log(driver, getObjectPayment("Bento_KNET_Radio_Button"), "Knet Radio button", 1);
			
		}
		else if(PaymentType.equalsIgnoreCase("Failure_Banner")) {
			textPresent_Log(driver, "UPI payments are having high failure rate, try other payment mode", 1);
			elementPresent_log(driver, By.xpath("//div[@id='root']/div/main/div/div[2]/div/div/div[2]"), "Note", 1);
			elementPresent_log(driver, By.xpath("//div[@id='root']/div/main/div/div[2]/div/div/div/div"), "Text G/W failure", 1);
		}
			
	}

	public void bento_Validation_UI(RemoteWebDriver driver, String PaymentType) throws Exception {
		if(PaymentType.equalsIgnoreCase("CC")) {
			bento_Select_PaymentType(driver, "CC");		
			safeClick(driver, getObjectPayment("Bento_Pay_Button"));
			textPresent_Log(driver, "Please enter a valid card number", 5);
			textPresent_Log(driver, "Please enter a valid expiry month", 1);
			textPresent_Log(driver, "Please enter a valid expiry year", 1);
			textPresent_Log(driver, "Please enter a valid name", 1);
			textPresent_Log(driver, "Please enter a valid cvv", 1);
			safeClick(driver, getObjectPayment("Bento_Pay_Uncheck_Rules"));
			textPresent_Log(driver, "Please accept the terms and conditions to proceed with this booking", 1);
			safeClick(driver, getObjectPayment("Bento_Pay_Uncheck_Rules"));
			Boolean Error_Terms = textPresent(driver, "Please accept the terms and conditions to proceed with this booking", 1);
			if(Error_Terms) {
				Assert.assertTrue(false);;		
			}
		}
		else if(PaymentType.equalsIgnoreCase("NB")) {
			bento_Select_PaymentType(driver, "NB");		
			safeClick(driver, getObjectPayment("Bento_Pay_Button"));
			textPresent_Log(driver, "Please select your bank", 1);				
			}
		else if(PaymentType.equalsIgnoreCase("TW")) {
			bento_Select_PaymentType(driver, "TW");		
			safeClick(driver, getObjectPayment("Bento_Pay_Button"));
			textPresent_Log(driver, "Please select any wallet", 1);				
			}
		else if(PaymentType.equalsIgnoreCase("UPI")) {
			bento_Select_PaymentType(driver, "UPI");		
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow_UPI"));
			textPresent_Log(driver, "Please enter valid UPI id", 1);
		}
		else if(PaymentType.equalsIgnoreCase("SC")) {
			//driver.manage().addCookie(cookie_Parl_Wallet);
			driver.manage().addCookie(ctauth_partial_wallet);
			
			refreshPage(driver);		
			Thread.sleep(1000);
			bento_Deselect_Wallet();
			//smartClick(driver, By.cssSelector("span.checkbox__mark.bs-border.bc-neutral-500.bw-1.ba"));
			bento_Select_PaymentType(driver, "SC");		
			safeClick(driver, getObjectPayment("Bento_Pay_Button"));
			textPresent_Log(driver, "Enter CVV number", 1);
			textPresent_Log(driver, "Save my card as per the RBI guidelines", 1);
			elementPresent_log(driver, By.xpath("//span/div"), "RBI Know More link", 5);
			safeClick(driver, By.xpath("//span/div"));
			//elementPresent_log(driver, By.xpath("//div[7]/div/div/div"), "Secure your card as per RBI guide lines", 5);
			textPresent_Log(driver, "Secure your card as per RBI guidelines", 5);
			textPresent_Log(driver, "When you opt-in your card is secured and you get faster checkout in future transactions", 1);
			textPresent_Log(driver, "Transact faster without entering card details every time", 1);
			textPresent_Log(driver, "As per RBI, Starting 1st Jan’22 all your card information will be secured with card", 1);
			//textPresent_Log(driver, "networks (VISA, Mastercard & Rupay) ensuring your card number is not misused", 1);
			refreshPage(driver);
		}
		else if(PaymentType.equalsIgnoreCase("ADCB")) {
			bento_Select_PaymentType(driver, "ADCB");	
			safeClick(driver, getObjectPayment("Bento_ADCB_Redeem_Button"));
			textPresent_Log(driver, "Please enter a valid card number", 1);
			textPresent_Log(driver, "Please enter a valid expiry month", 1);
			textPresent_Log(driver, "Please enter a valid expiry year", 1);
			textPresent_Log(driver, "Please enter a valid name", 1);
			textPresent_Log(driver, "Please enter a valid cvv", 1);
			
		}
		else if(PaymentType.equalsIgnoreCase("Expressway")) {
			bento_Select_PaymentType(driver, "CC");
			safeType(driver, getObjectPayment("PaymentPage_CreditCard_Number"), "4761360075863216");
			//mouseHoverClick(driver, getObjectPayment("PaymentPage_CreditCard_Number"));
			elementPresent_log(driver, By.xpath("//div[15]/div/label/div/span"),"Save Card check box", 10);
			textPresent_Log(driver, "Save my card as per the RBI guidelines", 1);
		}
		
		else if(PaymentType.equalsIgnoreCase("Expressway_Popup")) {
			bento_Select_PaymentType(driver, "CC");		
			Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			elementPresent_log(driver, By.xpath("//div[5]/div/div/div"), "Secure your card as per RBI guildelines", 5);
			String No = getText(driver, By.xpath("//div[10]/button"));
			
			String Yes = getText(driver, By.xpath("//button[2]"));
			
			if(!No.contains("No, skip")) {
				Reporter.log("No, skip text not displayed");
				Assert.assertTrue(false);
			} 
			if(!Yes.contains("Yes, secure")) {
				Reporter.log("Yes, secure text not displayed");
				Assert.assertTrue(false);				
			}
			
				elementPresent_log(driver, By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='No, skip'])[1]/preceding::*[name()='svg'][1]"), "", 5);
						
		}
		else if(PaymentType.equalsIgnoreCase("ABC")) {
			bento_Select_PaymentType(driver, "CC");		
			
		}
		
	}
	
	public void bento_Validation_Images(RemoteWebDriver driver, String PaymentType, String Domain) throws Exception {
		if(PaymentType.equalsIgnoreCase("CC")) {
			bento_Select_PaymentType(driver, "CC");
			if(Domain.endsWith("IN")) {				
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_MasterCard_Img"), "MasterCard", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_Visa_Img"), "Visa", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_Amex_Img"), "AMEX", 1);
				//elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_TextBox_Mada_Img"), "Mada CC textbox image", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_TextBox_Visa_Img"), "Visa CC textbox image", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_TextBox_Master_Img"), "Master CC textbox image", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_TextBox__Maestro_Img"), "Maestro CC textbox image", 1);
				
				//======================================Visa image============================================//
				String  handle= driver.getWindowHandle();			
				safeClick(driver, getObjectPayment("Bento_Pay_CC_Visa_Img"));
				Thread.sleep(5000);
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				String visaUrl = getURL(driver);
				Reporter.log("Visa Image URL : "+visaUrl);
				if(!visaUrl.contains("usa.visa.com/support/consumer/security.html")) {
					Reporter.log("Visa Image URL : "+visaUrl);
					Assert.assertTrue(false);
				}
				textPresent_Log(driver, "Peace of mind", 5);
				driver.switchTo().window(handle);
				
				//======================================Master image============================================//
				safeClick(driver, getObjectPayment("Bento_Pay_CC_MasterCard_Img"));
				Thread.sleep(5000);
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				String masterCardUrl = getURL(driver);
				Reporter.log("masterCardUrl Image URL : "+visaUrl);
				if(!masterCardUrl.contains("https://www.mastercard.us/en-us.html")) {
					Reporter.log("masterCard Image URL : "+masterCardUrl);
					Assert.assertTrue(false);
				}
				elementPresent_log(driver, By.xpath("//div[@id='onetrust-consent-sdk']/div"), "MasterCard Webpage element", 10);
				driver.switchTo().window(handle);

				//======================================AMEX image============================================//
				safeClick(driver, getObjectPayment("Bento_Pay_CC_Amex_Img"));
				Thread.sleep(5000);
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				String amexCardUrl = getURL(driver);
				Reporter.log("amexCardUrl Image URL : "+amexCardUrl);
				if(!amexCardUrl.contains("www.americanexpress.com")) {
					Reporter.log("masterCard Image URL : "+amexCardUrl);
					Assert.assertTrue(false);
				}
				elementPresent_log(driver, By.xpath("//img[@alt='American Express']"), "Amex Logo", 10);
				driver.switchTo().window(handle);
			}
			else if(Domain.endsWith("AE")) {
				
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_MasterCard_Img"), "MasterCard", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_Visa_Img"), "Visa", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_Amex_Img"), "AMEX", 1);
				//elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_TextBox_Mada_Img"), "Mada CC textbox image", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_TextBox_Visa_Img"), "Visa CC textbox image", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_TextBox_Master_Img"), "Master CC textbox image", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_CC_TextBox__Maestro_Img"), "Maestro CC textbox image", 1);
				
				//======================================Visa image============================================//
				String  handle= driver.getWindowHandle();			
				safeClick(driver, getObjectPayment("Bento_Pay_CC_Visa_Img"));
				Thread.sleep(5000);
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				String visaUrl = getURL(driver);
				Reporter.log("Visa Image URL : "+visaUrl);
				if(!visaUrl.contains("usa.visa.com/support/consumer/security.html")) {
					Reporter.log("Visa Image URL : "+visaUrl);
					Assert.assertTrue(false);
				}
				textPresent_Log(driver, "Peace of mind", 5);
				driver.switchTo().window(handle);
				
				//======================================Master image============================================//
				safeClick(driver, getObjectPayment("Bento_Pay_CC_MasterCard_Img"));
				Thread.sleep(5000);
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				String masterCardUrl = getURL(driver);
				Reporter.log("masterCardUrl Image URL : "+visaUrl);
				if(!masterCardUrl.contains("https://www.mastercard.us/en-us.html")) {
					Reporter.log("masterCard Image URL : "+masterCardUrl);
					Assert.assertTrue(false);
				}
				elementPresent_log(driver, By.xpath("//div[@id='onetrust-consent-sdk']/div"), "MasterCard Webpage element", 10);
				driver.switchTo().window(handle);

				//======================================AMEX image============================================//
				safeClick(driver, getObjectPayment("Bento_Pay_CC_Amex_Img"));
				Thread.sleep(5000);
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				String amexCardUrl = getURL(driver);
				Reporter.log("amexCardUrl Image URL : "+amexCardUrl);
				if(!amexCardUrl.contains("www.americanexpress.com")) {
					Reporter.log("masterCard Image URL : "+amexCardUrl);
					Assert.assertTrue(false);
				}
				elementPresent_log(driver, By.xpath("//img[@alt='American Express']"), "Amex Logo", 10);
				driver.switchTo().window(handle);
				
				
			}
		}
			else if(PaymentType.equalsIgnoreCase("NB")) {
				bento_Select_PaymentType(driver, "NB");
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Axis_Img"), "Axis Bank", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_ICICI_Img"), "ICICI Bank", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_SBI_Img"), "SBI", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Kotak_Img"), "Kotak Bank", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Canara_Img"), "Canara Bank", 1);	
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Axis_Img"));
				//safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_HDFC_Img"));
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_ICICI_Img"));
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_SBI_Img"));
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Kotak_Img"));
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Canara_Img"));	
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_SBI_Img"), "SBI Bank", 1);
				safeClick(driver, By.name("bankId"));
				safeSelect(driver, By.name("bankId"),"Bank of India");
			}
			else if(PaymentType.equalsIgnoreCase("TW")) {
				bento_Select_PaymentType(driver, "TW");
				safeClick(driver, By.xpath("//div[1]/div[2]/div"));
				safeClick(driver, By.xpath("//div[2]/div[2]/div")); 
				safeClick(driver, By.xpath("//div[3]/div[2]/div"));
				safeClick(driver, By.xpath("//div[4]/div[2]/div"));
				safeClick(driver, By.xpath("//div[5]/div[2]/div"));
				safeClick(driver, By.xpath("//div[6]/div[2]/div"));
				safeClick(driver, By.xpath("//div[7]/div[2]/div"));
				safeClick(driver, By.xpath("//div[8]/div[2]/div"));
			}		
			else if(PaymentType.equalsIgnoreCase("Summary")) {
				elementPresent_log(driver, getObjectPayment("Bento_Pay_BookingSummary_Flight_Icon"), "Flight", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_BookingSummary_Traveller_Icon"), "Traveller", 1);				
			}
			else if(PaymentType.equalsIgnoreCase("ADCB")) {
				String  handle= driver.getWindowHandle();
				driver.switchTo().window(handle);
				bento_Select_PaymentType(driver, "ADCB");
				safeClick(driver, getObjectPayment("Bento_Pay_ADCB_Logo"));
				Thread.sleep(5000);
				for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
				String ADCBUrl = getURL(driver);
				Reporter.log("ADCB Image URL : "+ADCBUrl);
				if(!ADCBUrl.contains("www.adcb.com")) {
					Reporter.log("ADCBUrl Image URL : "+ADCBUrl);
					Assert.assertTrue(false);
				}
				Reporter.log("ADCBUrl Image URL : "+ADCBUrl);
				driver.switchTo().window(handle);
			}
		
	}
	
	
	public void bento_Validation_Links(RemoteWebDriver driver, String PaymentType, String Domain) throws Exception {

		//=============================terms & condition==================================//
		String  handle= driver.getWindowHandle();
		driver.switchTo().window(handle);		
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Terms_and_Condition"), "Terms_and_Condition", 5);
		safeClick(driver, getObjectPayment("Bento_Pay_Terms_and_Condition"));
		Thread.sleep(2000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		String BookingTerms = getURL(driver);
		Reporter.log("Booking Terms URL : "+BookingTerms);
		if(Domain.equals("FLYIN")) {
		if(!BookingTerms.contains(".flyin.com/termsOfUse.en.html")) {
			Reporter.log("BookingTerms URL : "+BookingTerms);
			//Assert.assertTrue(false);
		}
		}
		else if (Domain.equals("AE")) {
		if(!BookingTerms.contains("qa2.cleartrip.ae/terms")) { 
			Reporter.log("BookingTerms URL : "+BookingTerms);
			Assert.assertTrue(false);
		}
		}
		else {
			if(!BookingTerms.contains("qa2.cleartrip.com/terms")) { 
			Reporter.log("BookingTerms URL : "+BookingTerms);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Cleartrip Terms of Service", 5);
		}
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		//=============================Privacy==================================//
		driver.switchTo().window(handle);		
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Privacy_Policy"), "Privacy Policy", 5);
		safeClick(driver, getObjectPayment("Bento_Pay_Privacy_Policy"));
		Thread.sleep(2000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}		
		String BookingPrivacyURL = getURL(driver);
		Reporter.log("Booking Privacy URL : "+BookingPrivacyURL);
		if(Domain.equals("FLYIN")) {
			if(!BookingTerms.contains(".flyin.com/termsOfUse.en.html")) {
				Reporter.log("BookingTerms URL : "+BookingTerms);
				//Assert.assertTrue(false);
			}
			}
		else {if(!BookingPrivacyURL.contains("/privacy/")) {
			Reporter.log("BookingPrivacy URL : "+BookingPrivacyURL);
			Assert.assertTrue(false);
		}
		//textPresent_Log(driver, "Cleartrip is fanatical about protecting your privacy", 5); 
		
		textPresent_Log(driver, "We value the trust you place in us and recognize the importance of secure transactions and information privacy", 5);
		}
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}

		//=============================Booking Policy==================================//
		driver.switchTo().window(handle);				
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Booking_Policy"), "Booking Policy", 5);
		safeClick(driver, getObjectPayment("Bento_Pay_Booking_Policy"));
		Thread.sleep(2000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		String BookingPolicyUrl = getURL(driver);
		System.out.println(BookingPolicyUrl + " BookingPolicyUrl");
		if(Domain.equals("FLYIN")) {
			if(!BookingPolicyUrl.contains("https://www.flyin.com/termsOfUse.en.html#btc")) {
				Reporter.log("BookingPolicyUrl URL : "+BookingPolicyUrl);
				Assert.assertTrue(false);
			}
			textPresent_Log(driver, "The agreement between the client and Flyin.com", 5);
		}
		else if(Domain.equals("AE")) {
			if(!BookingPolicyUrl.contains("qa2.cleartrip.ae/flights/booking-policies")) {
				Reporter.log("BookingPolicyUrl URL : "+BookingPolicyUrl);
				Assert.assertTrue(false);
			}
		}
		else {
		if(!BookingPolicyUrl.contains("qa2.cleartrip.com/flights/booking-policies")) {
			Reporter.log("BookingPolicyUrl URL : "+BookingPolicyUrl);
			Assert.assertTrue(false);
		}
		}	
		textPresent_Log(driver, "Cleartrip flight booking policy - blank Page in QA", 5);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	}
		
	
	public void bento_Validate_Currency(RemoteWebDriver driver, String Domain, String Currency) throws Exception {
		String Total_Price = getText(driver, getObjectPayment("Bento_Pay_Total_Value"));
		if(!Total_Price.contains(Currency)) {
			Reporter.log("Total Price doesn't contain Currency : "+Currency+" : "+Total_Price);
			//System.out.println("Total Price doesn't contain Currency : "+Currency+" : "+Total_Price);
			Assert.assertTrue(false);			
		}
		Reporter.log("Total Price "+Total_Price);	
	}
	
	// Booking via search page
	public void Searchpagebook(RemoteWebDriver driver, String wallettype) throws Exception
	{
		 driver.manage().addCookie(bentoitn);
		 if(wallettype=="Partial")
		 {
			 driver.manage().addCookie(ctauth_partial_wallet);
		 }
		 else
		 {
		  driver.manage().addCookie(ctauth);
		 }
		 driver.navigate().refresh();
		 Thread.sleep(3000);
		 elementPresent_log(driver,getObjectPayment("Bento_Book_Button"),"Book",10);
		if(elementVisible(driver,getObjectPayment("Bento_Indigo_Logo"),3))
		{
			smartClick(driver,getObjectPayment("Bento_Book_Button"));
		}
		else if(elementVisible(driver,getObjectPayment("Bento_Spicejet_Logo"),1))
		{
			smartClick(driver,getObjectPayment("Bento_Book_Button"));
		}
		else if(elementVisible(driver,getObjectPayment("Bento_Airindia_Logo"),1))
		{
			smartClick(driver,getObjectPayment("Bento_Book_Button"));
		}
		else if(elementVisible(driver,getObjectPayment("Bento_Vistara_Logo"),1))
		{
			smartClick(driver,getObjectPayment("Bento_Book_Button"));
		}
		  else
		  {
			  System.out.println("LDAP was displayed");
			  Reporter.log("LDAP was displayed");
		  }
		Thread.sleep(6000);
		Reporter.log("Clicked on Book");
		String parent=driver.getWindowHandle();
		Set<String>s1=driver.getWindowHandles();
		Iterator<String> I1= s1.iterator();
		while(I1.hasNext())
		{
		  String child_window=I1.next();
		  if(!parent.equals(child_window))
		  {
		    driver.switchTo().window(child_window);
		    Thread.sleep(5000);
		    driver.navigate().to(driver.getCurrentUrl());
		    textPresent_Log(driver,"Review your itinerary",160);
		    System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
		    Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
		   
		  }

		  }
	}


	public void book_itnnew(RemoteWebDriver driver,String gv_coupon) throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Reporter.log("Itinerary page loaded");
		if(elementVisible(driver,getObjectPayment("Bento_Itn_Standard_Fee"),10))
		{
			safeClick(driver,getObjectPayment("Bento_Itn_Standard_Fee"));
			Reporter.log("Selected itn fee");
			Thread.sleep(6000);
			if(gv_coupon=="GV")
			{
				WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
				jse.executeScript("arguments[0].scrollIntoView(true);",ele);
				Thread.sleep(1000);
				jse.executeScript("arguments[0].click()", ele);
				Thread.sleep(1000);
			    ele.sendKeys(GV_number);
			    Reporter.log("Entered GV number");
			  safeClick(driver,getObjectPayment("Bento_Itn_GV_Pin"));
			  safeType(driver,getObjectPayment("Bento_Itn_GV_Pin"),GV_pin);
			  Reporter.log("Entered GV pin");
			  safeClick(driver,getObjectPayment("Bento_Itn_GV_Apply"));
			  textPresent_Log(driver,"has been redeemed for this booking",10);
			  Reporter.log("GV applied Successfully");
			 }
			else if(gv_coupon=="Coupon")
			{
				WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
	            jse.executeScript("arguments[0].scrollIntoView(true);",ele);
				Thread.sleep(2000);
				jse.executeScript("arguments[0].click()", ele);
				Thread.sleep(1000);
				ele.sendKeys("DOMOW");
				Reporter.log("Entered Coupon details");
			   safeClick(driver,getObjectPayment("Bento_Itn_Coupon_Apply"));
			   textPresent_Log(driver,"Great! You just saved",10);
			   Reporter.log("Successfully applied coupon");
			}
			WebElement ele2=driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue"));
			jse.executeScript("arguments[0].click()", ele2);
			Reporter.log("Clicked on fare continue");
				Thread.sleep(2000);
		}
		
		else
		{
		safeClick(driver,getObjectPayment("Bento_Itn_Standard_Fee1"));
		Thread.sleep(1000);
		WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_Fare1_Continue"));
		
		jse.executeScript("arguments[0].click()", ele);
		Reporter.log("Selected itn fee");
		if(gv_coupon=="GV")
		{
			WebElement ele1 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
			jse.executeScript("arguments[0].scrollIntoView(true);",ele);
			Thread.sleep(1000);
			jse.executeScript("arguments[0].click()", ele);
			Thread.sleep(1000);
		    ele.sendKeys(GV_number);
		    Reporter.log("Entered GV number");
		  safeClick(driver,getObjectPayment("Bento_Itn_GV_Pin"));
		  safeType(driver,getObjectPayment("Bento_Itn_GV_Pin"),GV_pin);
		  Reporter.log("Entered GV pin");
		  safeClick(driver,getObjectPayment("Bento_Itn_GV_Apply"));
		  textPresent_Log(driver,"has been redeemed for this booking",3);
		  Reporter.log("GV applied Successfully");
		 }
		else if(gv_coupon=="Coupon")
		{
			WebElement ele1 = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
			jse.executeScript("arguments[0].scrollIntoView(true);",ele);
			Thread.sleep(1000);
			jse.executeScript("arguments[0].click()", ele);
			Thread.sleep(1000);
			ele.sendKeys("DOMOW");
			Reporter.log("Entered Coupon details");
		   safeClick(driver,getObjectPayment("Bento_Itn_GV_Apply"));
		   textPresent_Log(driver,"Great! You just saved",3);
		   Reporter.log("Successfully applied coupon");
		 }
	   // driver.findElement(By.xpath("//div[18]/div[1]/button[1]")).click();
		Thread.sleep(2000);
		if(elementVisible(driver,getObjectPayment("Bento_Itn_Meal_Continue"),5))
		{
		 WebElement ele1=driver.findElement(getObjectPayment("Bento_Itn_Meal_Continue"));
		 jse.executeScript("arguments[0].click()",ele1);
		 Reporter.log("Clicked on meal continue");
		}
	}
		if(elementVisible(driver,getObjectPayment("Bento_Itn_Add_On_Skip"),5))
		{
			WebElement ele1=driver.findElement(getObjectPayment("Bento_Itn_Add_On_Skip"));
		    jse.executeScript("arguments[0].click()",ele1);
		    Reporter.log("Clicked on skip addons");
			//safeClick(driver,getObjectPayment("Bento_Itn_Add_On_Skip"));
		}
		elementVisible(driver,getObjectPayment("Bento_Itn_Contact_Number"),5);
		safeClick(driver,getObjectPayment("Bento_Itn_Contact_Number"));
		safeType(driver,getObjectPayment("Bento_Itn_Contact_Number"),"1234567890");
		Reporter.log("Entered mobile number");
		safeClick(driver,getObjectPayment("Bento_Itn_Username"));
		safeType(driver,getObjectPayment("Bento_Itn_Username"),username);
		Reporter.log("Entered user name");
		safeClick(driver,getObjectPayment("Bento_Itn_Contactinfo_Continue"));
		Reporter.log("Clicked on continue");
		safeClick(driver,getObjectPayment("Bento_Itn_User_Firstname"));
		safeType(driver,getObjectPayment("Bento_Itn_User_Firstname"),"Tester");
		Reporter.log("Entered first name");
		safeClick(driver,getObjectPayment("Bento_Itn_User_Lastname"));
		safeType(driver,getObjectPayment("Bento_Itn_User_Lastname"),"Test");
		Reporter.log("Entered last name");
		safeClick(driver,getObjectPayment("Bento_Itn_Select_Gender"));
		safeClick(driver,getObjectPayment("Bento_Itn_Select_Female"));
		Reporter.log("Selected gender");
		if(elementVisible(driver,getObjectPayment("Bento_Itn_Nationality"),5))
		{
			safeClick(driver,getObjectPayment("Bento_Itn_Nationality"));
			safeType(driver,getObjectPayment("Bento_Itn_Nationality"),"india");
			safeClick(driver,getObjectPayment("Bento_Itin_Select_India"));
			Reporter.log("Selected nationality");
		}
		safeClick(driver,getObjectPayment("Bento_Itn_Continue_Booking"));
		Reporter.log("Clicked on continue button to navigate to payments page");
		
		/*
		 * if(textPresent_Log(driver,"Paying completely via Cleartrip wallet!",10)) {
		 * safeClick(driver,By.cssSelector("label.checkbox-round > svg > g > path")); }
		 * else { textPresent_Log(driver,"Enter your credit card details",30); }
		 */
		  
	}
	
	public void bento_Deselect_Wallet() throws InterruptedException, Exception 
	{
		if(textPresent(driver,"Cleartrip wallet",1))
	    {
			safeClick(driver,getObjectPayment("Bento_Payment_Deselect_Wallet"));
			Reporter.log("Deselected wallet");
	    }
	}

	public String bento_paymentpage(RemoteWebDriver driver,String PaymentType) throws Exception
	{
		String payURL = driver.getCurrentUrl();
		Thread.sleep(2000);
	    driver.manage().addCookie(cookie_Bento_Payment);
	    driver.navigate().refresh();
	    Thread.sleep(5000);
	    elementVisible(driver,getObjectPayment("Bento_Payment_PayText"),10);
	    textPresent_Log(driver,"Pay to complete your booking",1);
	    System.out.println(driver.getCurrentUrl());
	    Reporter.log(driver.getCurrentUrl());
	  if(PaymentType=="storedcard")
	  {
		bento_Deselect_Wallet();
		safeClick(driver,getObjectPayment("Bento_Payment_Select_Storedcard"));
    	Reporter.log("Clicked on SC");
		safeClick(driver,getObjectPayment("Bento_Payment_SC_CVV"));
	    safeType(driver,getObjectPayment("Bento_Payment_SC_CVV"),"1234");
	    Reporter.log("Entered CVV");
	    safeClick(driver,getObjectPayment("Bento_Payment_Paynow"));
	    Reporter.log("Clicked on paynow");
	    /*textPresent_Log(driver,"Please wait...",2);
	    textPresent_Log(driver,"American",2);*/
	    textPresent_Log(driver,"ACS Emulator",20);
	    safeClick(driver,getObjectPayment("Bento_Payment_AMC_SUBMIT"));
	    textPresent_Log(driver,"Your booking is done",5);
	    Reporter.log("Payment done successfully");
	 }
	  else if(PaymentType=="wallet")
	{
		if(textPresent_Log(driver,"Cleartrip wallet", 2))
	    {
			elementVisible(driver,getObjectPayment("Bento_Payment_Paynow"),2);
			textPresent_Log(driver,"Your wallet balance is sufficient to pay for this booking",2);
			safeClick(driver,getObjectPayment("Bento_Payment_Paynow"));
			 Reporter.log("Clicked on paynow");
			textPresent_Log(driver,"Please wait...",2);
			Reporter.log("Payment done successfully");
		}
		
	}
	  else if(PaymentType=="nb")
	{

		 bento_Deselect_Wallet();
		 safeClick(driver,getObjectPayment("Bento_Payment_NB"));
		 Reporter.log("Clicked on NB");
		safeClick(driver,getObjectPayment("Bento_Payment_NB_ICIC"));
		Reporter.log("Selected ICIC Bank");
		safeClick(driver,getObjectPayment("Bento_Payment_Paynow"));
		Reporter.log("Clicked on paynow");
		//textPresent_Log(driver,"Please wait...",2);
		textPresent_Log(driver,"Welcome to Razorpay Software Private Ltd Bank",10);
		safeClick(driver,getObjectPayment("Bento_Payment_NB_Payment_Success"));
		Reporter.log("Payment done successfully");
		
	}
	  else if(PaymentType=="UPI")
	{

			bento_Deselect_Wallet();
			safeClick(driver,getObjectPayment("Bento_Payment_UPI"));
			 Reporter.log("Clicked on UPI");
		safeClick(driver,getObjectPayment("Bento_Payment_UPI_ID"));
		safeType(driver,getObjectPayment("Bento_Payment_UPI_ID"),"9986696785@ybl");
		 Reporter.log("Entered UPI Details");
		safeClick(driver,getObjectPayment("Bento_Payment_Paynow"));
		 Reporter.log("Clicked on paynow");
		textPresent_Log(driver,"Please wait...",2);
		textPresent_Log(driver,"Please accept the collect request sent to your UPI app",5);
		 Reporter.log("Payment done successfully");
	}
	  else if(PaymentType=="GV")
	{

		bento_Deselect_Wallet();
		elementVisible(driver,getObjectPayment("Bento_Payment_Paynow"),2);
		textPresent_Log(driver,"Gift card",2);
		safeClick(driver,getObjectPayment("Bento_Payment_Paynow"));
		 Reporter.log("Clicked on paynow");
		textPresent_Log(driver,"Please wait...",2);
		 Reporter.log("Payment done successfully");
	}
	if(PaymentType=="partial_wallet")
	{
		 elementVisible(driver,getObjectPayment("Bento_Payment_Paynow"),2);
		 safeClick(driver,getObjectPayment("Bento_Payment_Select_Storedcard1"));
		 Reporter.log("Clicked on SC");
		  safeClick(driver,getObjectPayment("Bento_Payment_SC1_CVV"));
		    safeType(driver,getObjectPayment("Bento_Payment_SC1_CVV"),"1234");
		    Reporter.log("Entered CVV details");
		    safeClick(driver,getObjectPayment("Bento_Payment_Paynow"));
		    Reporter.log("Clicked on paynow");
		    textPresent_Log(driver,"Please wait...",2);
		    textPresent_Log(driver,"American",5);
		    textPresent_Log(driver,"ACS Emulator",10);
		    safeClick(driver,getObjectPayment("Bento_Payment_AMC_SUBMIT"));
		    textPresent_Log(driver,"Your booking is done",5);
		    Reporter.log("Payment done successfully");
	}
	else if(PaymentType=="Coupon")
	{
		elementVisible(driver,getObjectPayment("Bento_Payment_Paynow"),2);
		textPresent_Log(driver,"Coupon code (DOMOW)",2);

		bento_Deselect_Wallet();
		safeClick(driver,getObjectPayment("Bento_Payment_Select_Storedcard"));
		Reporter.log("Clicked on SC");
		safeClick(driver,getObjectPayment("Bento_Payment_SC_CVV"));
	    safeType(driver,getObjectPayment("Bento_Payment_SC_CVV"),"1234");
	    Reporter.log("Entered CVV details");
	    safeClick(driver,getObjectPayment("Bento_Payment_Paynow"));
	    Reporter.log("Clicked on paynow");
	    textPresent_Log(driver,"Please wait...",2);
	    textPresent_Log(driver,"American",2);
	    textPresent_Log(driver,"ACS Emulator",2);
	    safeClick(driver,getObjectPayment("Bento_Payment_AMC_SUBMIT"));
	    textPresent_Log(driver,"Your booking is done",5);
	    Reporter.log("Payment done successfully");
	}
	return payURL;
	}

	public void confirmation_page(RemoteWebDriver driver) throws Exception
	{
		 elementPresent_log(driver,By.linkText("Get your ticket"),"Get your ticket",5);
		 textPresent_Log(driver,"You just booked",1);
		 textPresent_Log(driver,"Travelers in this trip",1);
		 textPresent_Log(driver,"Itinerary sent",1);
		 textPresent_Log(driver,"PAYMENT RECEIPT",1);
		 textPresent_Log(driver,"TOTAL CHARGE",1);
		 textPresent_Log(driver,"RATE BREAK UP",1);
		 textPresent_Log(driver,"Convenience Fee",1);
		 textPresent_Log(driver,"Total",1);
		 textPresent_Log(driver,"Travel plans change often.",1);
		 String tripid=driver.findElement(getObjectPayment("Bento_Confirmation_Page_Gettrip")).getText();
	     System.out.println("Trip ID is : "+tripid);
	     Reporter.log("Trip ID is : "+tripid);
	     
	}
			
		
}