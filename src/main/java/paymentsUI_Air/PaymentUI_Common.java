// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.UrlFetchWebConnection;

import io.restassured.internal.util.SafeExceptionRethrower;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import paymentsAPI.API_PaymentCommon1;


public class PaymentUI_Common extends API_PaymentCommon1{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;

	protected String qaurl = "https://qa2.cleartrip.com";
	protected String qaurlae = "https://qa2.cleartrip.ae";
	protected String qaurlbh = "https://bh.cleartrip.com";
	protected String qaurlkw = "https://kw.cleartrip.com";
	protected String qaurlom = "https://om.cleartrip.com";
	protected String qaurlsa = "https://qa2.cleartrip.sa";
	protected String qaurlus = "https://qa2.cleartrip.us";
	protected String qaurlqa = "https://qa.cleartrip.com";
	Cookie cookie_Parl_Wallet = new Cookie("ct-auth", "u8ikyrIDWHzYjvgXGe7DcSilWSeNdD3sGtbSzvPZYHJsqbodZst%2B%2F0ze9bW1F%2F23uQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2me%2FiLCzDjUE8Mm7nMigogz0cui%2F5Lc2RncHKyY%2FG5jXeVJ2Z%2BJW4q4d2%2BSGAnvG%2FbfJ2a5%2BLtDuDuClv7XsKTWXoRahiCr1K%2B3iYGbIxo%2FJPQ%3D%3D");
	Cookie cookie_Full_Wallet = new Cookie("ct-auth", "5zoM9zvEgPvd1fO%2BsJylFp4hvaybBzUzp2ilDBfOdXvOg%2BIVENg%2BHdsz3cA98%2B5BD3habrO078UoXdzWM34lXZaLbE1jIpkEaANLn%2BHJadeW7kll2UfWWUfOoZLsVWTER2KXP0MBz2Ucg2wdtjfomKwrrYOshnOlUWyYWat6SeV2Tt6lvwTzivgXCSht22Dws");	
	Cookie cookie_Stored_Card = new Cookie("ct-auth", "3cZX3Pk7YZLQGkv5lH%2BqMisg41mHr4%2BV5LnkFlBYXSW7TbjXLYl7j8XVySMQUxQsuv18jxT4Krq%2BnZKZgt%2FgtsPPZuvu7kgJgSXq9dBmctulsdFnuefY%2Fk4K%2FkHUuDj%2BnitdvoouxVugJ172IcDxp41NeKUSgTMU9EpGlYfZJ60e5yZIWxI28YU6CxlbH7FH");
	public String fetchPaymentURL(Response resp){
		String payurl="";
		JsonPath jsonPathEvaluator = resp.jsonPath();
		payurl = jsonPathEvaluator.getString("payment_url");
		return payurl;
	}
	
	public String fetchPaymentTripID(Response resp){
		String tripID="";
		JsonPath jsonPathEvaluator = resp.jsonPath();
		tripID = jsonPathEvaluator.getString("trip_ref");
		return tripID;
	}
	
	public String getPayUI(String PayType, String Domain) throws Exception {
		resp = payUIget(PayType,Domain);
		if(Domain.equalsIgnoreCase("AE")) {
			qaurl=qaurlae;
		}else if(Domain.equalsIgnoreCase("BH")) {
			qaurl=qaurlbh;
		}else if(Domain.equalsIgnoreCase("KW")) {
			qaurl=qaurlkw;
		}else if(Domain.equalsIgnoreCase("OM")) {
			qaurl=qaurlom;
		}else if(Domain.equalsIgnoreCase("QA")) {
			qaurl=qaurlqa;
		}else if(Domain.equalsIgnoreCase("SA")) {
			qaurl=qaurlsa;
		}else if(Domain.equalsIgnoreCase("US")) {
			qaurl=qaurlus;
		}
		Url = qaurl+ fetchPaymentURL(resp);
		//System.out.println("Payment URL : " +Url);
		Reporter.log("Payment URL : " +Url);
		String TripID = fetchPaymentTripID(resp);
		//System.out.println("TripID : "+TripID);
		Reporter.log("TripID : "+TripID);
		return Url;
	}
	
	public void payUI_Select_PaymentType(RemoteWebDriver driver, String PayType) throws Exception {
		for (int i = 0; i < 10; i++) {			
			if(textPresent(driver, "System error", 1)) {
			Reporter.log("There's something wrong with our system");			
			Assert.assertTrue(false);
		}
		if(elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 1)) {
			break;
		}
		}
		if(!elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10)) {
			Reporter.log("PayUI Page is not displayed");
			String UI_error = getText(driver, By.xpath("//h1"));
			Reporter.log(UI_error);			
			Assert.assertTrue(false);
		}
		switch (PayType) {
		case "CC":
			PayType = "Credit Card";
			break;
		case "DC":
			PayType = "Debit Card";
			break;
		case "NB":
			PayType = "Net Banking";
			break;
		case "TW":
			PayType = "Wallets";
			break;
		case "UPI":
			PayType = "UPI";
			break;
		case "ADCB":
			PayType = "";
			break;
		case "SC":
			PayType = "Stored Card";
			break;
		default:
			break;
		}		
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), PayType);	
	}
	
	public void payUI_PageLoader(RemoteWebDriver driver) throws Exception {
		elementNotVisible(driver, getObjectPayment("PayUI_PageLoader_Spinner"), 10);
		elementNotVisible(driver, getObjectPayment("PayUI_PageLoader_Shimmer"), 10);
	}
		
	
	public void payUI_Enter_PaymentDetails(RemoteWebDriver driver, String PayType, String BankName) throws Exception {
		switch (PayType) {
		case "CC":
			payUI_Select_CC(driver, BankName);
			break;
		case "DC":
			payUI_Select_CC(driver, BankName);
			break;
		case "NB":
			payUI_Select_NB(driver, BankName);
			break;
		case "TW":
			break;
		case "UPI":
			break;
		case "ADCB":
			break;
		default:
			break;
		}		
	}
		
	public void payUI_Select_CC(RemoteWebDriver driver, String BankName) throws Exception {		
			elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
			textPresent_Log(driver, "Enter your credit card details.", 1);
			switch (BankName) {
				case "MASTER":
				Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
				break;	
				case "AMEX":
				Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
				break;
				case "CAPTCHA":
				Enter_CC_Details(driver, "512345678901234", platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
				break;
			}
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
	
	public void Enter_CC_Details(RemoteWebDriver driver, String CCNumber, String CCExpMonth, String CCExpYear, String CVV) throws Exception {
		Reporter.log("Card Details +\n"+ CCNumber +"\n " + CCExpMonth  +" " + CCExpYear +" " + CVV);
		safeType(driver, getObjectPayment("PaymentPage_CreditCard_Number"), CCNumber);
		safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"));
		safeSelect(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"), CCExpMonth);
		safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"));
		safeSelect(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"), CCExpYear);
		safeType(driver, getObjectPayment("PaymentPage_CreditCard_Name"), "test");
		safeType(driver, getObjectPayment("PaymentPage_CreditCard_CVV"), CVV);
	}
	
	
	public void Enter_DC_Details(RemoteWebDriver driver, String CCNumber, String CCExpMonth, String CCExpYear, String CVV) throws Exception {
		Reporter.log("Card Details +\n"+ CCNumber +"\n " + CCExpMonth  +" " + CCExpYear +" " + CVV);
		safeType(driver, getObjectPayment("PaymentPage_DebitCard_Number"), CCNumber);
		safeClick(driver, getObjectPayment("PaymentPage_DebitCard_Exp_Month"));
		safeSelect(driver, getObjectPayment("PaymentPage_DebitCard_Exp_Month"), CCExpMonth);
		safeClick(driver, getObjectPayment("PaymentPage_DebitCard_Exp_Year"));
		safeSelect(driver, getObjectPayment("PaymentPage_DebitCard_Exp_Year"), CCExpYear);
		safeType(driver, getObjectPayment("PaymentPage_DebitCard_Name"), "test");
		safeType(driver, getObjectPayment("PaymentPage_DebitCard_CVV"), CVV);
	}
	
	public void payUI_BankPage(RemoteWebDriver driver, String BankName) throws Exception {		
		elementNotVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10);		
		if(BankName.equalsIgnoreCase("MASTER")) {
			if (textPresent(driver, "AXIS SIMULATOR", 10)) {
				Reporter.log("PayU OTP page is displayed");
				smartType(driver, By.id("password"), "123456");
				smartClick(driver, By.id("submitBtn"));	
			}
			else if (!textPresent(driver,"Payment successful", 1)){
				Reporter.log("Payment is not sucessfull");
				Assert.assertTrue(false);
		}
		}else if(BankName.equalsIgnoreCase("AMEX")) {
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"), "Amex Bank page not displayed", 20);
			textPresent(driver, "ACS Emulator", 1);
			Reporter.log("Amex Auth page is displayed");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"));
		}else if(BankName.equalsIgnoreCase("Citibank")) {
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), "Citi Bank page not displayed", 30);
			Reporter.log("CitiBank Auth page is displayed");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
		}else if(BankName.equalsIgnoreCase("Hdfc Bank")) {
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), "Tech Process Bank page not displayed", 30);
			Reporter.log("HDFCBank Auth page is displayed");
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), "test");
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Password"), "test");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn"));
			Thread.sleep(5000);
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_IntermitentText"), 5);
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn2"));
		}else if(BankName.equalsIgnoreCase("ICICI Bank")) {
			elementPresent_log(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Logo"), "Razorpay Bank page not displayed", 30);
			textPresent(driver, "Welcome to Razorpay Bank", 1);
			Reporter.log("RazorPay Auth page is displayed");
			safeClick(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Submit"));	
		}
		else if(BankName.equalsIgnoreCase("CAPTCHA")) {
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), "Citi Bank page not displayed", 30);
			Reporter.log("CitiBank Auth page is displayed");
			safeSelect(driver, By.cssSelector("select[name=\"PAID\"]"), "N");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
		}
			
	}
		
	
	public void payUI_Select_NB(RemoteWebDriver driver, String BankName) throws Exception {		
			elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 5);
			textPresent_Log(driver, "Popular Banks", 1);
			//textPresent_Log(driver, "", 1);
			if(BankName.contains("CAPTCHA")) {
				safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), "Citibank");
			}
			else safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), BankName);
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			payUI_BankPage(driver, BankName);
	}	
	
	public void payUI_Mock_ConfirmationPage(RemoteWebDriver driver, String PayUrl) throws InterruptedException {
		for (int i = 0; i <=10; i++) {
			String returnUrl  = getURL(driver);
			if(returnUrl.contains("paymentservice/return")) {
				Reporter.log("Refreshing PayUI page to check the Payment Status");
				driver.get(PayUrl);	
				textPresent_Log(driver, "Payment successful", 10);
				textPresent_Log(driver, "view your booking details and Trip ID", 5);
				break;
			}
			else if(i==1) {
				driver.get(PayUrl);	
				textPresent_Log(driver, "Payment successful", 10);
				break;
			}
			else if(i==10){
				Reporter.log("Payment is not sucessful");
				Assert.assertTrue(false);
			}
			Thread.sleep(1000);
			}
	}
	

	
	
}
