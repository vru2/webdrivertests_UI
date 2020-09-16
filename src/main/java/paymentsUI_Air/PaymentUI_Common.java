// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import domains.PaymentNodeJS;
import io.restassured.response.Response;
import paymentsAPI.API_PaymentCommon1;


public class PaymentUI_Common extends PaymentNodeJS{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	Cookie cookie_Parl_Wallet = new Cookie("ct-auth", "g%2BN18HeWvZyEk7HQeFThJxsObDgYiHvhM4u28wBQ4BejFU9Ke4ME8Tw3kDrS%2BYaXuQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2me%2FiLCzDjUE8Mm7nMigogz0dlw4XnBuAMvrrzg32ECZZREQ3m1%2Fwdl0v8nRObVcrkLkfySvhcY68HkUF0r5u%2Bu1GYGQ%2FU5486qrW%2F0RUIlB1Q%3D%3D");
	Cookie cookie_Full_Wallet = new Cookie("ct-auth", "5zoM9zvEgPvd1fO%2BsJylFp4hvaybBzUzp2ilDBfOdXvOg%2BIVENg%2BHdsz3cA98%2B5BD3habrO078UoXdzWM34lXZaLbE1jIpkEaANLn%2BHJadeW7kll2UfWWUfOoZLsVWTER2KXP0MBz2Ucg2wdtjfomKwrrYOshnOlUWyYWat6SeV2Tt6lvwTzivgXCSht22Dws");	
	Cookie cookie_Stored_Card = new Cookie("ct-auth", "3cZX3Pk7YZLQGkv5lH%2BqMisg41mHr4%2BV5LnkFlBYXSW7TbjXLYl7j8XVySMQUxQsuv18jxT4Krq%2BnZKZgt%2FgtsPPZuvu7kgJgSXq9dBmctulsdFnuefY%2Fk4K%2FkHUuDj%2BnitdvoouxVugJ172IcDxp41NeKUSgTMU9EpGlYfZJ60e5yZIWxI28YU6CxlbH7FH");
	

	public void payUI_Select_PaymentType(RemoteWebDriver driver, String PayType) throws Exception {
		for (int i = 0; i < 10; i++) {			
			if(textPresent(driver, "System error", 1)) {
			Reporter.log("There's something wrong with our system");			
			Assert.assertTrue(false);
		} else if(textPresent(driver, "Oops, Something went wrong", 1)) {
			Reporter.log("Oops something wrong with our system");			
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
			PayType = "ADCB touchPoints";
			break;
		case "SC":
			PayType = "Stored Card";
			break;
		case "KNET":
			PayType = "KNET";
			break;
		default:
			PayType = "Credit Card";
			break;
		}		
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), PayType);	
	}
	
	public void payUI_Select_PaymentType_PWA(RemoteWebDriver driver, String PayType) throws Exception {
		for (int i = 0; i < 10; i++) {			
			if(textPresent(driver, "System error", 1)) {
			Reporter.log("There's something wrong with our system");			
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Select payment option", 1)) {
			break;
		}
		}
		if(!elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 10)) {
			Reporter.log("PayUI Page is not displayed");					
			Assert.assertTrue(false);
		}
		switch (PayType) {
		case "CC":
			PayType = "DEBIT/CREDIT CARDS";
			break;
		case "DC":
			PayType = "DEBIT/CREDIT CARDS";
			break;
		case "NB":
			PayType = "NET BANKING";
			break;
		case "TW":
			PayType = "WALLETS";
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
		scrollSmooth(driver, 100);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), PayType);			
	}
	
	
	public void payUI_Error_Validation_PWA(RemoteWebDriver driver, By errorMessage, By errorMessagePopUP, String ErrorText) throws Exception {
		elementPresent_log(driver, errorMessagePopUP, "error Popup", 5);
		String ErrorMessage = getText(driver, errorMessagePopUP);

		//System.out.println("Error message is "+ErrorMessage+" instead of "+ErrorText);
		if(!ErrorMessage.contains(ErrorText)) {
			Reporter.log("Error message is "+ErrorMessage+" instead of "+ErrorText);
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, errorMessage, "error text", 5);	
		textPresent(driver, ErrorText, 1);
		textPresent_Log(driver, ErrorText, 1);
	}
	
	public void payUI_Error_Validation(RemoteWebDriver driver,  By errorMessagePopUP, String ErrorText) throws Exception {
		elementPresent_log(driver, errorMessagePopUP, "error Popup", 5);
		String ErrorMessage = getText(driver, errorMessagePopUP);

		//System.out.println("Error message is "+ErrorMessage+" instead of "+ErrorText);
		if(!ErrorMessage.contains(ErrorText)) {
			Reporter.log("Error message is "+ErrorMessage+" instead of "+ErrorText);
			Reporter.log("Error message banner not shown");
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, errorMessagePopUP, "error text", 5);	
		textPresent_Log(driver, ErrorText, 1);
	}
	
	public void payUI_PageLoader(RemoteWebDriver driver) throws Exception {
		elementNotVisible(driver, getObjectPayment("PayUI_PageLoader_Spinner"), 10);
		elementNotVisible(driver, getObjectPayment("PayUI_PageLoader_Shimmer"), 10);
	}
		
	
	public void validate_Currency (RemoteWebDriver driver, String Domain, String Currency) throws Exception {
		String Total_Price = getText(driver, getObjectPayment("PayUI_Total_Pay_Value"));
		if(!Total_Price.contains(Currency)) {
			Reporter.log("Total Price doesn't contain Currency : "+Currency+" : "+Total_Price);
			Assert.assertTrue(false);			
		}
		Reporter.log("Total Price "+Total_Price);		
	}
	
	public void validate_Currency_PWA (RemoteWebDriver driver, String Domain, String Currency) throws Exception {
		String Total_Price = getText(driver, getObjectPayment("PWA_PaymentPage_TotalPrice"));
		if(!Total_Price.contains(Currency)) {
			Reporter.log("Total Price doesn't contain Currency : "+Currency+" : "+Total_Price);
			Assert.assertTrue(false);			
		}
		Reporter.log("Total Price "+Total_Price);		
	}
	
	
	
	public void payUI_Enter_PaymentDetails(RemoteWebDriver driver, String PayType, String BankName) throws Exception {
		switch (PayType) {
		case "CC":
			payUI_Select_CC(driver, BankName);
			break;
		case "DC":
			payUI_Select_DC(driver, BankName);
			break;
		case "NB":
			payUI_Select_NB(driver, BankName);
			break;
		case "TW":
			break;
		case "UPI":
			payUI_Select_UPI(driver, BankName);
			break;
		case "ADCB":
			payUI_Select_ADCB(driver, BankName);
			break;
		case "KNET":
			payUI_Select_KNET(driver, BankName);
			break;
		default:
			break;
		}		
	}
	
	
	public void payUI_Enter_PaymentDetails_PWA(RemoteWebDriver driver, String PayType, String BankName) throws Exception {
		switch (PayType) {
		case "DEBIT/CREDIT CARDS":
			payUI_Select_CARD_PWA(driver, BankName);
			break;
		case "NET BANKING":
			payUI_Select_NB_PWA(driver, BankName);
			break;
		case "TW":
			payUI_Select_TW_PWA(driver, BankName);
			break;
		case "UPI":
			payUI_Select_UPI_PWA(driver, BankName);
			break;
		case "ADCB":
			break;
		default:
			break;
		}		
	}
		
	public void payUI_Select_CC(RemoteWebDriver driver, String BankName) throws Exception {		
		elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
		textPresent_Log(driver, "Enter your credit card details", 1);
		switch (BankName) {
			case "MASTER":
			Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;	
			case "AMEX":
			Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
			break;			
			case "AMEXTRAIN":
			Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
			break;
			case "CAPTCHA":
			Enter_CC_Details(driver, "512345678901234", platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;
			case "CHECKOUT":
			Enter_CC_Details(driver, platform.value("SACheckOut_Number"), platform.value("SACheckOut_Month_UI"), platform.value("SACheckOut_Year"), platform.value("SACheckOut_CVV"));
			break;
			case "PAYFORT":
			Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;	
			case "NOON":
			Enter_CC_Details(driver, platform.value("Noon_Number"), platform.value("Noon_Month_UI"), platform.value("Noon_Year"), platform.value("Noon_CVV"));
			break;
			case "RAZORPAY":
			Enter_CC_Details(driver, platform.value("RazorPay_Number"), platform.value("RazorPay_Month_UI"), platform.value("RazorPay_Year"), platform.value("RazorPay_CVV"));
			break;
		}
		if(common.value("Bento_Payment").equalsIgnoreCase("true")||BankName.contains("TRAIN")) {			
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

	public void payUI_Select_DC(RemoteWebDriver driver, String BankName) throws Exception {		
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
	public void payUI_Select_ADCB(RemoteWebDriver driver, String BankName) throws Exception {		
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
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_CheckBlance_Btn"));		
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
		textPresent_Log(driver, "Available Balance", 1);
		textPresent_Log(driver, "Balance TouchPoints", 1);
		textPresent_Log(driver, "Amount to redeem", 1);
		textPresent_Log(driver, "Total payable", 1);
		textPresent_Log(driver, "Amount redeemed", 1);
		textPresent_Log(driver, "Balance payable", 1);		
		safeType(driver, getObjectPayment("PaymentPage_ADCB_Redeem_Amount_TextBox"), "50");

		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
	//	safeType(driver, getObjectPayment("PaymentPage_ADCB_OTP"), platform.value("ADCBCard_OTP"));
		
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
	
	public void payUI_Select_KNET(RemoteWebDriver driver, String BankName) throws Exception {		
		elementVisible(driver, getObjectPayment("PaymentPage_Knet_RadioButton"), 5);
		safeClick(driver, getObjectPayment("PaymentPage_Knet_RadioButton"));
		elementPresent_log(driver, getObjectPayment("PaymentPage_Knet_Image"), "", 1);

		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {		
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Reporter.log("Make Payment button is Clicked");
		}
		
	}
	
	public void payUI_Select_CARD_PWA(RemoteWebDriver driver, String BankName) throws Exception {		
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_CC_Number"), 5);
	//	textPresent_Log(driver, "DEBIT/CREDIT CARDS", 1);
		Thread.sleep(5000);
		switch (BankName) {
			case "MASTER":
				Enter_CARD_Details_PWA(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_EXP_PWA"), platform.value("MasterCard_CVV"));
			break;	
			case "AMEX":
				Enter_CARD_Details_PWA(driver, platform.value("AmexCard_Number"), platform.value("PWAAmexCard_Expiry"), platform.value("AmexCard_CVV"));
			break;
			case "CAPTCHA":
				Enter_CARD_Details_PWA(driver, "512345678901234", platform.value("MasterCard_EXP_PWA"), platform.value("MasterCard_CVV"));
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
	
	public void Enter_CARD_Details_PWA(RemoteWebDriver driver, String CCNumber, String CCExp, String CVV) throws Exception {
		Reporter.log("Card Details +\n"+ CCNumber +"\n " + CCExp +" " + CVV);
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Number"), CCNumber);
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Expiry"), CCExp);
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_CVV"), CVV);
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
			if (textPresent(driver, "AXIS SIMULATOR", 10)|| textPresent(driver, "CYBER SIMULATOR", 10)) {
				Reporter.log("PayU OTP page is displayed");
				smartType(driver, By.id("password"), "123456");
				smartClick(driver, By.id("submitBtn"));	
			}
			else if (!textPresent(driver,"Payment successful", 1)){
				Reporter.log("Payment is not sucessfull");
				Assert.assertTrue(false);
		}
		}else if(BankName.equalsIgnoreCase("AMEX")||BankName.equalsIgnoreCase("AMEXTRAIN")) {
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"), "Amex Bank ", 20);
			textPresent(driver, "ACS Emulator", 1);
			Reporter.log("Amex Auth page is displayed");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"));
		}else if(BankName.equalsIgnoreCase("Citibank")||BankName.equalsIgnoreCase("CitibankPopular")) {
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 20);
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), "Citi Bank  ", 1);
			Reporter.log("CitiBank Auth page is displayed");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
		}else if(BankName.equalsIgnoreCase("Hdfc Bank")) {
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), 20);
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), "Tech Process Bank ", 1);
			Reporter.log("HDFCBank Auth page is displayed");
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_UserName"), "test");
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Password"), "test");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn"));
			Thread.sleep(5000);
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_IntermitentText"), 5);
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_TechProcess_Submit_Btn2"));
		}else if(BankName.equalsIgnoreCase("ICICI Bank")) {
			elementVisible(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Logo"), 20);
			elementPresent_log(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Logo"), "Razorpay Bank ", 1);
			textPresent(driver, "Welcome to Razorpay Bank", 1);
			Reporter.log("RazorPay Auth page is displayed");
			safeClick(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Submit"));	
		}
		else if(BankName.equalsIgnoreCase("CAPTCHA")) {
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 20);
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), "Citi Bank ", 1);
			Reporter.log("CitiBank Auth page is displayed");
			safeSelect(driver, By.cssSelector("select[name=\"PAID\"]"), "N");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
		}else if(BankName.equalsIgnoreCase("Knet")) {
			textPresent_Log(driver, "Cleartrip Mea Fz Llc", 30);
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Knet_DropDown"), "Knet Bank ", 2);
			Reporter.log("Knet Bank page is displayed");
			safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_DropDown"), "Knet Test Card [KNET1]");
			Thread.sleep(5000);
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"), 5);
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"), "0000000001");
			safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Month"), "09");
			safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Year"), "2021");
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CVV"), "1234");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Proceed"));
			textPresent_Log(driver, "Billing Information", 20);
			
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Confirm"));
			textPresent(driver, "If You Are Not Redirected Automatically In 30 Seconds", 10);
			smartClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_RedirectionPage"));
		}else if(BankName.equalsIgnoreCase("PhonePE")) {
			if(!(textPresent(driver, "9986696785", 10)||elementPresent_log(driver, By.id("mobileNumber"), "PhonePe homepage", 30))) {
				Reporter.log("phonepe page is not displayed");
				Assert.assertTrue(false);	
			}
			Reporter.log("PhonePE page is displayed");
		}else if(BankName.equalsIgnoreCase("AmazonPay")) {
			/*elementPresent_log(driver, By.id("mobileNumber"), "PhonePe homepage", 30);
			Reporter.log("PhonePE page is displayed");*/
		}		
		else if(BankName.equalsIgnoreCase("Checkout")) {
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), 30);
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Logo"), 20);
			Thread.sleep(2000);
			safeType(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), "Checkout1!");
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Continue_Btn"));		
		}
		else if(BankName.equalsIgnoreCase("PayFort")) {
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), 30);
			elementVisible(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Logo"), 20);
			Thread.sleep(2000);
			safeType(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Password_Txt"), "Checkout1!");
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("MakePayment_PgCred_SA_Checkout_Continue_Btn"));
		}
		else if(BankName.equalsIgnoreCase("Noon")) {
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Submit_Btn"), 10);
			textPresent(driver, "Please submit your Verified by Visa password", 1);
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Password_TxtBx"), "Noon pay redirection ", 1);
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Password_TxtBx"),"1234");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Noon_Submit_Btn"));
		}
		else if(BankName.equalsIgnoreCase("RazorPay")) {
			safeClick(driver, getObjectPayment("MakePayment_Pay_Btn_CC"));
			textPresent_Log(driver, "Welcome to Razorpay Bank", 30);
			elementVisible(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Logo"), 1);
			safeClick(driver, getObjectPayment("PaymentPage_RazorPayCC_Page_Submit"));	
		}
	
	}
		

	public void payUI_Select_NB(RemoteWebDriver driver, String BankName) throws Exception {		
			elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 5);
			textPresent_Log(driver, "Popular banks", 1);
			//textPresent_Log(driver, "", 1);
			if(BankName.contains("CAPTCHA")) {
				safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), "Citibank");
			}
			else if(BankName.contains("CitibankPopular")) {
				safeClick(driver, getObjectPayment("PaymentPage_NB_PopularBanks_Citi"));
			}
			else safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), BankName);

			if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			payUI_BankPage(driver, BankName);
			}
	}	
	
	public void payUI_Select_UPI(RemoteWebDriver driver, String BankName) throws Exception {	

		textPresent(driver, "Select UPI partner to make your payment", 5);
		elementVisible(driver, getObjectPayment("SelectPayment_UPI_PhonePe"), 5);
		safeClick(driver, getObjectPayment("SelectPayment_UPI_PhonePe"));		

		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_BankPage(driver, BankName);
		}
	}	
	
	

	public void payUI_Select_NB_PWA(RemoteWebDriver driver, String BankName) throws Exception {		
			elementVisible(driver, getObjectPayment("PWA_PaymentPage_Select_NB"), 5);
			textPresent_Log(driver, "Choose another bank", 1);
			//textPresent_Log(driver, "", 1);
			if(BankName.contains("CAPTCHA")) {
				safeClick(driver, getObjectPayment("PWA_PaymentPage_Select_NB"));
				textPresent(driver, "All Other Banks", 5);
				safeType(driver, getObjectPayment("PWA_NETBANKING_Page_NB_TextBox"), "Citibank");
				safeClick(driver, getObjectPayment("PWA_NETBANKING_Page_NB_AJAX"));
				Thread.sleep(1000);				
			}
			
			else if(BankName.equalsIgnoreCase("CitibankPopular")) {
				/*safeClick(driver, getObjectPayment("PWA_PaymentPage_Select_NB"));
				textPresent(driver, "All Other Banks", 5);
				safeType(driver, getObjectPayment("PWA_NETBANKING_Page_NB_TextBox"), "Citibank");*/
				safeClick(driver, getObjectPayment("PWA_PaymentPage_NB_Popularbank_CITI"));
				Thread.sleep(1000);				
			}
			else 
			{
				safeClick(driver, getObjectPayment("PWA_PaymentPage_Select_NB"));
				textPresent(driver, "All Other Banks", 5);
				safeType(driver, getObjectPayment("PWA_NETBANKING_Page_NB_TextBox"), BankName);
				safeClick(driver, getObjectPayment("PWA_NETBANKING_Page_NB_AJAX"));
				Thread.sleep(1000);
				String BankName1 = getText(driver, getObjectPayment("PWA_PaymentPage_NB_Name"));
				if(!BankName1.equalsIgnoreCase(BankName)) {
					Reporter.log("Selcted bank is : "+BankName1+" Expected bank is "+BankName);
					Assert.assertTrue(false);;
				}
			}

			if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
			safeClick(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"));
			Thread.sleep(5000);
			payUI_BankPage(driver, BankName);
			}
	}

	public void payUI_Select_UPI_PWA(RemoteWebDriver driver, String BankName) throws Exception {		
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_SelectPhonePe"), 5);
		Reporter.log("Phonepe is displayed");		
		safeClick(driver, getObjectPayment("PWA_PaymentPage_SelectPhonePe"));

		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Reporter.log("Make Payment button is Clicked");
		Thread.sleep(5000);
		payUI_BankPage(driver, BankName);
		}
}

	public void payUI_Select_TW_PWA(RemoteWebDriver driver, String TWType) throws Exception {		
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_AmazonPay"), 5);
		Reporter.log("AmazonPay is displayed");		

		scrollSmooth(driver, 100);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_AmazonPay"));
		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Reporter.log("Make Payment button is Clicked");
		Thread.sleep(5000);
		payUI_BankPage(driver, TWType);
		}
}
	
	
	public void payUI_Mock_ConfirmationPage(RemoteWebDriver driver, String PayUrl) throws InterruptedException {

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
	
	public void payUI_Mock_ConfirmationPage_Train(RemoteWebDriver driver, String PayUrl) throws InterruptedException {

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
