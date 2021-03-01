// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import io.restassured.response.Response;
import paymentsUI_Air.PaymentUI_Common;


public class PaymentUI_Common_Bento extends PaymentUI_Common{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	public Cookie cookie_Bento_Payment = new Cookie("isBentoPayment", "true");
	public Cookie cookie_Parl_Wallet = new Cookie("ct-auth", "EVefRmmOWPSC8c9sPGbZGwZMgfl%2FLjP6yfQQAwhPONaOOIjRmfrMO5ubb5%2FGLWzguQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2me%2FiLCzDjUE8Mm7nMigogz0z84lf%2Bili9Xzawt1KbN%2FMNpQDroZvb3Q7ub%2BLj1YfofQs%2BDG9mD5DXvLFNSWqYz93GfvGpnfyFmIRy226HjYgQ%3D%3D");
	//public Cookie cookie_Full_Wallet = new Cookie("ct-auth", "O2HJIm5w1xSz%2BJuS4aDuK7gVEOwk4Wtqdan6btFwj4TyQ8aSq%2BF4m20vjT%2FZugfuD3habrO078UoXdzWM34lXZaLbE1jIpkEaANLn%2BHJadeW7kll2UfWWUfOoZLsVWTEc0QAdvEUdBA%2F1nzmjBhQdfKHBynkHeYDz6XwPLe1MJiHeiMeqTJEkDsxZaWHAi%2Bv");	
	public Cookie cookie_Full_Wallet = new Cookie("ct-auth", "QYqE9uech2apyQ1uZeSIm%2Biz6I1u9AiJowmGBaxiRKMqE8P953oYVntCR0SJJ7eQW%2FX9HXgO9kidldUQ8q4npkhV1B3OjqN%2Blj%2FywDNYZMjgUXM5JAnhVDA45gmzA8Pizn%2BSaRCsCJO4qK6o3WKDKpud0nmE4TDzaeJNsQHQ2mLsvmw%2FloTY%2FWDTIvGCxoTH");
	//ct_storedcard@cleartrip.com
	public Cookie cookie_StoredCard = new Cookie("ct-auth", "rbvyqtMD%2B%2B2D4IuzY4zuIRBY6YkAlHcLGjbPfY7%2FLBY2N%2F%2Buerv4PZWlJbAGD5wwbiqurLmLCTGawNuDxccW8ULcQXyAJl%2BKYgmuLWYgm7W8d3Cxpy9U3hwGuorQpT8%2ByP6ob0oI8PorTkCfLhoyGqJzZb9f1us8iRaXyQVLiLFXlVEbS%2BIomnNuo9OG71wq");	
	
	
	
	
	public String get_Bento_Url(RemoteWebDriver driver, String PayType, String Domain) throws Exception {
		String PayUrl = getPayUI(PayType, Domain);
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		driver.manage().addCookie(cookie_Bento_Payment);	
		refreshPage(driver);
		Reporter.log("PayUrl : " +PayUrl);
		return PayUrl;
	}
	
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
		if(!elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 5)) {
			Reporter.log("PayUI Page is not displayed");
			String UI_error = getText(driver, By.xpath("//h1"));
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
			bento_Select_PaymentType(driver, "CC");
			textPresent_Log(driver, "Pay to complete your booking", 1);
			textPresent_Log(driver, "Enter card details", 1);
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
			textPresentInElementAssert(driver, getObjectPayment("Bento_Pay_Button"), "Pay now", 2);
			String UPIIDPlaceholder = driver.findElement(getObjectPayment("Bento_Pay_UPI")).getAttribute("placeholder");
			if(!(UPIIDPlaceholder.equals("Enter your UPI ID"))) {
				Reporter.log("PlaceHoder is not correct "+UPIIDPlaceholder);
				Assert.assertTrue(false);
			}	
		}
		else if(PaymentType.equalsIgnoreCase("SC")) {
			driver.manage().addCookie(cookie_Parl_Wallet);
			refreshPage(driver);
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
			textPresent_Log(driver, "Flexifly", 1);
			textPresent_Log(driver, "TRAVEL_INSURANCE", 1);
			}
			textPresent_Log(driver, "Convenience fee", 1);
			//textPresent_Log(driver, "Cleartrip wallet", 1);
			WebElement ele= driver.findElement(getObjectPayment("Bento_Pay_PriceBreakup_ConvFee_Image"));
			Actions action = new Actions(driver);
			action.moveToElement(ele).perform();
			if(Domain.equals("IN")) {
				textPresent_Log(driver, "Includes a non-refundable convenience fee of ₹ 30 per traveller", 1);
				Reporter.log("Includes a non-refundable convenience fee of ₹ 30 per traveller -popup is displayed");
			}
			if(Domain.equals("AE")) {
				textPresent_Log(driver, "Includes a non-refundable convenience fee of AED", 1);
				textPresent_Log(driver, "10 per traveller", 1);
				
			}
			
			
		}
		
		else if(PaymentType.equalsIgnoreCase("Booking_SUMMARY")) {
			textPresent_Log(driver, "Booking summary" ,1);
			textPresent_Log(driver, "Bangalore", 1);
			textPresent_Log(driver, "(BLR)", 1);
			textPresent_Log(driver, "(BOM)", 1);
			if(Domain.equals("IN")) {
			textPresent_Log(driver, "09:20, Sat 24 Oct - 11:05, Sat 24 Oct", 1);
			textPresent_Log(driver, "5 travellers", 1);
			textPresent_Log(driver, "John Miller (M)", 1);
			textPresent_Log(driver, "Ashish Jain (M)", 1);
			textPresent_Log(driver, "Rohit Kumar (M)", 1);
			textPresent_Log(driver, "Mohit Verma (M)", 1);
			textPresent_Log(driver, "Sachin Reddy (M)", 1);
			}
			if(Domain.equals("AE")||Domain.equals("KW")||Domain.equals("QA")||Domain.equals("SA")||Domain.equals("OM")||Domain.equals("ME")||Domain.equals("BH")) {
				textPresent_Log(driver, "13:50, Sat 24 Oct - 15:35, Sat 24 Oct", 1);
				textPresent_Log(driver, "1 traveller", 1);
				textPresent_Log(driver, "test test (M)", 1);
			}		
			
			textPresent_Log(driver, "© 2006–2021 Cleartrip Pvt. Ltd.", 1);
			textPresent_Log(driver, "Completely safe and secure transactions", 1);
		}
		else if(PaymentType.equalsIgnoreCase("KNET")) {
			bento_Select_PaymentType(driver, "KNET");
			textPresent_Log(driver, "Select a bank", 1);
			elementPresent_log(driver, getObjectPayment("Bento_KNET_Radio_Button"), "Knet Radio button", 1);
			
		}
			
	}

	public void bento_Validation_UI(RemoteWebDriver driver, String PaymentType) throws Exception {
		if(PaymentType.equalsIgnoreCase("CC")) {
			bento_Select_PaymentType(driver, "CC");		
			safeClick(driver, getObjectPayment("Bento_Pay_Button"));
			textPresent_Log(driver, "Please enter a valid card number", 1);
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
			safeClick(driver, getObjectPayment("Bento_Pay_Button"));
			textPresent_Log(driver, "Please enter valid UPI id", 1);
		}
		else if(PaymentType.equalsIgnoreCase("SC")) {
			driver.manage().addCookie(cookie_Parl_Wallet);
			refreshPage(driver);		
			bento_Select_PaymentType(driver, "SC");		
			safeClick(driver, getObjectPayment("Bento_Pay_Button"));
			textPresent_Log(driver, "Enter CVV number", 1);
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
			textPresent(driver, "Save this card for faster checkout, we never save your CVV", 1);
			safeClick(driver, getObjectPayment("Bento_Pay_Expressway_Radio_Btn"));
		}
		else if(PaymentType.equalsIgnoreCase("XYZ")) {
			bento_Select_PaymentType(driver, "CC");		
			
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

				driver.switchTo().window(handle);					

			}
		}
			else if(PaymentType.equalsIgnoreCase("NB")) {
				bento_Select_PaymentType(driver, "NB");
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Axis_Img"), "Axis Bank", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_HDFC_Img"), "HDFC Bank", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_ICICI_Img"), "ICICI Bank", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_SBI_Img"), "SBI", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Kotak_Img"), "Kotak Bank", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Canara_Img"), "Canara Bank", 1);	
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Axis_Img"));
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_HDFC_Img"));
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_ICICI_Img"));
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_SBI_Img"));
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Kotak_Img"));
				safeClick(driver, getObjectPayment("Bento_Pay_NB_Popularbank_Canara_Img"));	
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
				Thread.sleep(5000);				
			}		
			else if(PaymentType.equalsIgnoreCase("Summary")) {
				elementPresent_log(driver, getObjectPayment("Bento_Pay_BookingSummary_Flight_Icon"), "Flight", 1);
				elementPresent_log(driver, getObjectPayment("Bento_Pay_BookingSummary_Traveller_Icon"), "Traveller", 1);				
			}
		
	}
	
	
	public void bento_Validation_Links(RemoteWebDriver driver, String PaymentType, String Domain) throws Exception {
		

		//=============================Booking Policy==================================//
		String  handle= driver.getWindowHandle();
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Booking_Policy"), "Booking Policy", 5);
		safeClick(driver, getObjectPayment("Bento_Pay_Booking_Policy"));
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		String BookingPolicyUrl = getURL(driver);
		Reporter.log("Booking Policy URL : "+BookingPolicyUrl);
		if(!BookingPolicyUrl.contains("qa2.cleartrip.com/flights/booking-policies")) {
			Reporter.log("BookingPolicyUrl URL : "+BookingPolicyUrl);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Cleartrip flight booking policy", 5);
		
		
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}

		//=============================terms & condition==================================//
		driver.switchTo().window(handle);
		
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Terms_and_Condition"), "Terms_and_Condition", 5);
		safeClick(driver, getObjectPayment("Bento_Pay_Terms_and_Condition"));
		Thread.sleep(5000);
		String BookingTerms = getURL(driver);
		Reporter.log("Booking Policy URL : "+BookingTerms);
		if(!BookingTerms.contains("qa2.cleartrip.com/terms")) {
			Reporter.log("BookingTerms URL : "+BookingTerms);
			//Assert.assertTrue(false);
		}
		//textPresent_Log(driver, "BookingTerms", 5);		
		
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		//=============================Privacy==================================//
		driver.switchTo().window(handle);		
		elementPresent_log(driver, getObjectPayment("Bento_Pay_Privacy_Policy"), "Privacy Policy", 5);
		safeClick(driver, getObjectPayment("Bento_Pay_Privacy_Policy"));
		Thread.sleep(5000);
		
		String BookingPrivacyURL = getURL(driver);
		Reporter.log("Booking Policy URL : "+BookingPrivacyURL);
		if(!BookingPrivacyURL.contains("/privacy/")) {
			Reporter.log("BookingPrivacy URL : "+BookingPrivacyURL);
			//Assert.assertTrue(false);
		}
	//	textPresent_Log(driver, "Cleartrip flight booking policy", 5);		
		
		
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
		
}
