// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.commonUI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import test.java.commonAPI.API_PaymentCommon;


public class PaymentUI_Commonold extends API_PaymentCommon {
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;


	public Cookie ctauthOLD = new Cookie("ct-auth", "kQqdrcVR8t4znRp8uzBQJgaacI%2B5mUEhQsXqP%2BGvCv9Sca3PAxik9%2FDoNKFAEq5S6nDr3dyz0gFHshmzL9GNaG4e8msn1sCvUt92FE1Hxz%2B449dUBXvxJapPKHtcbOExsOm%2BE43PNH%2FbzMr%2Bgv0v9PZIafGsbWEbtoycPG3UjA%2BzcqiD2kXHlH7Tnnt7Xdd%2B");
	public Cookie ctauth = new Cookie("ct-auth", "sJUONuyZDDPfTH%2BmG7oAtQeGQcHRjdLz9zF5EgBlchm5V%2F9sTn8LOdxqj35OBpme6nDr3dyz0gFHshmzL9GNaHOIvSzMqIKQCtJCbK1tSKgV%2BL7U6ooYH8i4J5EcOuBONoXhtAHa7NHmAmxdXipy2Q9D1Cb%2FImNyrUOegGbah%2FyoBMPzz%2FQiEiCw5q%2B2kAvsfPZ1MMq6EJr6sVI7VaVzbIGGV6A3Nv62ofdJLMeX59IdZGvtFXP18OHjIOoFpWFGkS1sn3WNAlm38%2FZOcdd3IjHEO18EgEydpVtNDzf6yr6faKrVCts6PecZbivI%2Bte60tjp1DhSV%2B2jxoL0zxvbMTYesQSARdekXP6oq0AMWLH%2BjvbXcUOrzBt3ykAdcnMIUTtjggX6YfQpO6VAcAmr8QLAwWMAoVsihoCMPIyMqJnxutVqwqAKEr63AAOZlv9K");
	public Cookie cookie_Bento_Payment = new Cookie("isBentoPayment", "true");
	public Cookie ctauth_amex = new Cookie("ct-auth", "2%2BtU1cPb8lJr0jvLEAtykB9OU0fk%2F%2BykRqo7fqGZ%2FgNdUi7dMNUxWo%2BLayLyBmIQH8YOfEGj8AeevvMX%2F4QnQkvAnTTp9N%2FfevCUsB0kyv34RHOgDXRfdndn%2FWd0KOXhimPy3b9gj7V56t4fbK1hHoIzQYBzwMa%2Fi72%2FqTSKtPUlKo9yE91%2BeAEj2Bi%2FZIx%2FcqFKCJETXpAxsR3%2FhUWMrg%3D%3D");
	protected String username = "testcltp29@gmail.com";
	//public Cookie ctauth_partial_wallet = new Cookie("ct-auth","FPFASUH3u8BIfPi6n5SA9LXbDIclCp0%2BkRN%2Fw9pKarLL3y3qdEGiZQIuqxIl9f3EH8YOfEGj8AeevvMX%2F4QnQu5pne5K5EHLAFvUZ60PN8K8qX%2FBnweQFNfqHv2MpXaBWdVRYJKk4obdFibGNlCsKQmjgQzzYO8qxSqmDKTEZk5nFNSZ6oZBVnN8BGz7Phhf");

	public Cookie ctauth_partial_wallet = new Cookie("ct-auth", "GZj199N%2FGbMVLbJmPWBgoaby%2BMCazMpV1i9dChHMZOf5g4XIcmYYYosgaQ0VGvp7vdb22kmKGrhj2VAI20Altj%2BnYqVAYRNgDmQE40E8Yzj4r0PCpRdMCg9e5Ry40QoNtmqkcQw0MDAlaO6MaiGWQSfRPc2%2BxtmdnrZa56VAd4A%3D"); // 65243938  5252525252 PH
	public Cookie ctauth_partial_wallet1 = new Cookie("ct-auth", "Bk7N%2FtlW6UIM9%2Fv06RR0lzYwI2Wr5NoY6shicJ7wSEglXjP2rTXj7vKCCjzDFS1EH8YOfEGj8AeevvMX%2F4QnQkvAnTTp9N%2FfevCUsB0kyv34RHOgDXRfdndn%2FWd0KOXh%2F2AX0kdZPIqgx5R%2FHygKQrR425YROepvP0SdSctCUtkcciwXF7FvVYKJizsM6Az33Pdp0Z8op1wWr79u2xWoxw%3D%3D");
	public Cookie hotelLogin = new Cookie("ct-auth", "wXRMsuJtL9WgArSZlNMx0zMrAAXuo%2Fx75FAjg9yx7%2BaP3TmjQOZ3nIiLDZWVzahbuQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2mdq5YlcDpr1NhocHCzFUnhnFzQr4qtRb2xhSWwELLVrIm931R0DjQqCU3guA6McTHvxrx7uoG8MaIjFbgrbUFuLCIQVMwmZPuPWYE%2BZcIe2iQcBlNEUA6TPhFqzPj5kdtXzYYtxjGgBPls1FJB9t5ULG6UU2B4lpfRPn7nlPGDL%2Bh8wYo5RkzDxKQKqfs%2BRQNZv8wYRhTyYEQWZZEKWQzfg");
	public Cookie ctauth_Saved_Cards = new Cookie("ct-auth", "xQF1scG2KAjUKjb0nhbj7W5gh1ze39fzSEc%2F18%2BoP6PkPulxqJhDFt6Li6igz%2BaLRgUWITcUCW%2FPw%2Bea%2FYC76r1klNYcgXrCEAwPKA%2BIUFocRr9A4ypxdh%2BPZCq2fC%2BI26hEYAocTQWJaHIALF%2BbQSemi1L2QY4GGJ7EXBuvSvGmVYWMhCcUDL%2Bi44N5mAea2u4J%2BEE0fGu5VNbg3TPA9Q%3D%3D");
	public Cookie ctauth_PayLater = new Cookie("ct-auth", "J%2BdMd0LGgMI8a39GNJ8xHqzqXvFGWmTpxhpOSPgnVnR5rXIoRNAXiPWEaKB9yveSNxBOo2r5JZ%2FVmD3Z2PjPQXjBrq444%2F1uPr9TDoR7r0Fe4mCETJt4BFkyvt%2FwdjA%2F8xWIih%2BGLbZz3y8MqrXJA5iZUrITk7nqu1Igqg3F1qbgENsO1xgbhrKOyO1na3ElmWDw%2Feg43BsE%2Bojvv%2FgwqHnmaZS3pTnoqp6N0Ka3Y8A%3D");


	public Cookie fullwallet = new Cookie("ct-auth", "abohNkVTBrywcKccg24Aw9dJPtR30Z3dElXVUz9mBnzshjhM3ya2l7Lh72af1Vw1j9O3UYZZi4zJRwF%2Bio21NJjJfVdGhDt6EBXP56tMKTFBGHOsWoEpCRXEAPtdwG%2FQIaFneIx1HBPLs0RKghSuuS%2BfddzyZlIzJ29c3Vp4Ews%3D"); //65237343 1234123456

	public Cookie bentoitn = new Cookie("forcedBentoItn", "true");
	public Cookie bento = new Cookie("isBento", "true");
	public String itn_totalprice;
	public String pay_totalprice;


	String GV_number = "3000331036544999";
	String GV_pin = "104573";

	JavascriptExecutor jse = (JavascriptExecutor) driver;
	String contactnumber = "12345678";

	Cookie cookie_Parl_Wallet = new Cookie("ct-auth", "EVefRmmOWPSC8c9sPGbZGwZMgfl%2FLjP6yfQQAwhPONaOOIjRmfrMO5ubb5%2FGLWzguQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2me%2FiLCzDjUE8Mm7nMigogz0z84lf%2Bili9Xzawt1KbN%2FMNpQDroZvb3Q7ub%2BLj1YfofQs%2BDG9mD5DXvLFNSWqYz93GfvGpnfyFmIRy226HjYgQ%3D%3D");
	Cookie cookie_Full_Wallet = new Cookie("ct-auth", "kPSO4DekYXjX1NAa%2BV6x%2BPAuD85oXXc2x6ocNR2SCho5FiJNPfE0mcjCvYIvtvEzD3habrO078UoXdzWM34lXZaLbE1jIpkEaANLn%2BHJadeW7kll2UfWWUfOoZLsVWTE5e4M7AJPpksQjN%2BnOAVReuzUb6b257o%2Bo1tkm1ssHdnsn63Uy2JyxP3spA3W9e%2Fw");	
	Cookie cookie_Stored_CardNew = new Cookie("ct-auth", "EVefRmmOWPSC8c9sPGbZGwZMgfl%2FLjP6yfQQAwhPONaOOIjRmfrMO5ubb5%2FGLWzguQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2me%2FiLCzDjUE8Mm7nMigogz0z84lf%2Bili9Xzawt1KbN%2FMNpQDroZvb3Q7ub%2BLj1YfofQs%2BDG9mD5DXvLFNSWqYz93GfvGpnfyFmIRy226HjYgQ%3D%3D");
	Cookie cookie_Stored_Card = new Cookie("ct-auth", "3cZX3Pk7YZLQGkv5lH%2BqMisg41mHr4%2BV5LnkFlBYXSW7TbjXLYl7j8XVySMQUxQsuv18jxT4Krq%2BnZKZgt%2FgtsPPZuvu7kgJgSXq9dBmctulsdFnuefY%2Fk4K%2FkHUuDj%2BnitdvoouxVugJ172IcDxp41NeKUSgTMU9EpGlYfZJ60e5yZIWxI28YU6CxlbH7FH");
	Cookie cookie_Add_SC=new Cookie("ct-auth","CcNCE1HeA9xi0zLBvdgyipMrHyjrTOVaxM0bGlOeHZozx7q0nRs8lpmI1Yj3mhsDmKAAIK983rJHezZppJTjL%2Buyt1YsWGAmZnjGtzjP9wxHHXyajC%2Bt%2B1aDMXmoRrDZhqYD5As3rJQDNFIGCYzSR0PRSgXTL404cS4HrO2fJkk%3D");


	public void addwalletamount(int amount, String emailID) throws Exception {
		Response resp;
		String url = "http://172.29.20.92:9001/payments/wallet/cashback?emailId=" + emailID + "&currency=INR&amount=" + amount + "&expiryDate%20=30/04/24";
		System.out.println("url : " + url);
		resp = RestAssured.get(url);
		Reporter.log(resp.asString());
	}

	public void addwalletamount_UserID(int amount, String userID) throws Exception {
		Response resp;
		String url = "http://172.29.20.92:9001/payments/wallet/addcash?amount=" + amount + "&userId=" + userID + "&currency=INR";
		System.out.println("url : " + url);
		resp = RestAssured.get(url);

		Reporter.log(resp.asString());
	}

	public void payUI_Select_PaymentType(RemoteWebDriver driver, String PayType) throws Exception {
		for (int i = 0; i < 10; i++) {
			if(elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 1)) {
				break;
			}
			else if(textPresent(driver, "System error", 1)) {
			Reporter.log("There's something wrong with our system");			
			Assert.assertTrue(false);
		} else if(textPresent(driver, "Oops, Something went wrong", 1)) {
			Reporter.log("Oops something wrong with our system");			
			Assert.assertTrue(false);
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
			PayType = "Debit/Credit card";
			break;
		case "DC":
			PayType = "Debit Card";
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
			PayType = "ADCB touchPoints";
			break;
		case "SC":
			PayType = "Saved payment modes";
			break;
		case "KNET":
			PayType = "KNET";
			break;
		case "PayPal":
			PayType = "PayPal";
			break;
		case "EMI":
			PayType = "EMI";
			break;
		default:
			PayType = "Credit Card";
			break;
		}		
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), PayType);	
	}

	public void payUI_Select_PaymentType_PWA(RemoteWebDriver driver, String PayType) throws Exception {
		for (int i = 0; i < 2; i++) {		
			if(textPresent(driver, "PAYMENT MODES", 1)) {
				break;
			}
			else if(textPresent(driver, "System error", 1)) {
			Reporter.log("There's something wrong with our system");			
			Assert.assertTrue(false);
			}		
		}
		if(elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 5)) {			
		} else {
			Reporter.log("PayUI Page is not displayed");					
			//Assert.assertTrue(false);
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
		case "SC":
			PayType = "Stored Card";
			break;
		case "ADCB":
			PayType = "ADCB touchpoints";
			break;
		default:
			break;
		}
		scrollSmooth(driver, 100);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), PayType);			
	}

	public void validate_Currency (RemoteWebDriver driver, String Domain, String Currency) throws Exception {
		String Total_Price = getText(driver, getObjectPayment("PayUI_Total_Pay_Value"));
		if(!Total_Price.contains(Currency)) {
			Reporter.log("Total Price doesn't contain Currency : "+Currency+" : "+Total_Price);
			Assert.assertTrue(false);			
		}
		Reporter.log("Total Price "+Total_Price);		
	}
	
	public void payUI_Enter_PaymentDetails(RemoteWebDriver driver, String PayType, String BankName, String BookingType) throws Exception {
		switch (PayType) {
		case "CC":
			payUI_Select_CC(driver, BankName, BookingType);
			break;
		case "DC":
			payUI_Select_DC(driver, BankName, BookingType);
			break;
		case "NB":
			payUI_Select_NB(driver, BankName, BookingType);
			break;
		case "TW":
			break;
		case "UPI":
			payUI_Select_UPI(driver, BankName, BookingType);
			break;
		case "ADCB":
			payUI_Select_ADCB(driver, BankName, BookingType);
			break;
		case "KNET":
			payUI_Select_KNET(driver, BankName, BookingType);
			break;
		default:
			break;
		}		
	}

	public void payUI_Select_CC(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
		elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
		textPresent(driver, "Enter card details", 1);
		switch (BankName) {
			case "MASTER":
			Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;	
			case "AMEX":
			Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
			break;
			case "PAYTM":
			Enter_CC_Details(driver, "6080322940807777","Jan (01)","2025","123");
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
			case "INVALID":
			Enter_CC_Details(driver, "1212121212121212", platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;
			case "RAZORPAYDC":
			Enter_CC_Details(driver, platform.value("RazorPay_Number_DC"), platform.value("RazorPay_Month_UI_DC"), platform.value("RazorPay_Year_DC"), platform.value("RazorPay_CVV_DC"));
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

	public void payUI_Select_DC(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
		elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 5);
		textPresent_Log(driver, "Enter your debit card details.", 1);
		switch (BankName) {
			case "MASTER":
			Enter_CC_Details(driver, platform.value("MasterCard_Number"), platform.value("MasterCard_Month"), platform.value("MasterCard_Year"), platform.value("MasterCard_CVV"));
			break;
			case "RAZORPAYDC":
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

	public void payUI_Select_ADCB(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {
		bento_Select_PaymentType(driver, "ADCB");
		elementVisible(driver, getObjectPayment("PaymentPage_CreditCard_Number"), 20);
		textPresent(driver, "Pay to complete your booking", 1);	
		textPresent(driver, "Enter ADCB card details", 1);		
		textPresent_Log(driver, "ADCB card number", 1);
		textPresent_Log(driver, "Expiry date", 1);
		textPresent_Log(driver, "Card holder Name", 1);
		textPresent_Log(driver, "CVV", 1);
		validate_Currency(driver, "", "AED");		
		Reporter.log("Card Details +\n"+ platform.value("ADCBCard_Number") +"\n " + platform.value("ADCBCard_Expiry_Month")  +" " + platform.value("ADCBCard_Expiry_Year") +" " + platform.value("ADCBCard_CVV"));
		safeType(driver, getObjectPayment("PaymentPage_ADCB_Number"), "5264083966400083");
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_EXP_Month"));
		safeSelect(driver, getObjectPayment("PaymentPage_ADCB_EXP_Month"), platform.value("ADCBCard_Expiry_Month"));
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_EXP_Year"));
		safeSelect(driver, getObjectPayment("PaymentPage_ADCB_EXP_Year"), platform.value("ADCBCard_Expiry_Year"));
		safeType(driver, getObjectPayment("PaymentPage_ADCB_CardName"), "test");
		safeType(driver, getObjectPayment("PaymentPage_ADCB_CVV"), platform.value("ADCBCard_CVV"));
		String CheckBalance = getText(driver, getObjectPayment("PaymentPage_ADCB_CheckBlance_Btn"));	
		Assert.assertEquals(CheckBalance, "Check balance");		 
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_CheckBlance_Btn"));	
		Thread.sleep(10000);
		Reporter.log("Check balance is Clicked");
		if(textPresent(driver, "You have provided incorrect card details", 10)) {
			Reporter.log("You have provided incorrect card details message not displayed");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Please provide valid ADCB card details to redeem TouchPoints", 1)) {
			Reporter.log("Please provide valid ADCB card details to redeem TouchPoints message not displayed");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver,"You will redeem AED",5);
		String MakePay = getText(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Assert.assertEquals(MakePay, "Redeem");		
		textPresent_Log(driver,"Your ADCB card details",1);
		textPresent_Log(driver,"Redeem ADCB TouchPoints",1);
		textPresent_Log(driver,"Available balance",1);
		textPresent_Log(driver,"Amount to redeem",1);
		textPresent_Log(driver,"A minimum amount of AED",1);
		textPresent_Log(driver,"50 must be redeemed",1);
		textPresent_Log(driver, "ADCB TouchPoints", 1);
		elementPresent_log(driver, By.xpath("//a[contains(@href, 'https://adcbtouchpoints.com/tnc')]"), "ADCB View T&C", 1);
		elementVisible(driver, getObjectPayment("PaymentPage_ADCB_Redeem_Amount_TextBox"), 10);
		String TotalAmt = getValue(driver, getObjectPayment("PaymentPage_ADCB_Redeem_Amount_TextBox"));
		Thread.sleep(5000);
		safeType(driver, getObjectPayment("PaymentPage_ADCB_Redeem_Amount_TextBox"), "40");
		textPresent_Log(driver,"The amount is insufficient for redemption", 5);
		safeType(driver, getObjectPayment("PaymentPage_ADCB_Redeem_Amount_TextBox"), TotalAmt);
		textPresent_Log(driver,"You will redeem AED", 5);		
		if(BookingType.contains("ADCBPARTIAL")) {
			safeType(driver, getObjectPayment("PaymentPage_ADCB_Redeem_Amount_TextBox"), "AED 50");
			elementVisible(driver, By.xpath("//li[3]/p[2]"),20);
			String AED50 = getText(driver, By.xpath("//li[3]/p[2]"));
			Assert.assertEquals(AED50, "AED  50");
		}
		String ADCB_Card = getText(driver, By.cssSelector("p.fs-3.c-neutral-900"));
		if(!ADCB_Card.contains("5264 XXXX XXXX 0083")) {
			Reporter.log("5264 XXXX XXXX 0083 card details not shown after check balance");
			Assert.assertTrue(false);
		}		
		
		//-------- Checking Full Payment in ADCB-----------------------//
		safeClick(driver, By.cssSelector("span.c-secondary-500.fs-3.c-pointer"));
		textPresent_Log(driver, "Enter ADCB card details", 5);
		safeType(driver, getObjectPayment("PaymentPage_ADCB_Number"), platform.value("ADCBCard_Number"));
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_EXP_Month"));
		safeSelect(driver, getObjectPayment("PaymentPage_ADCB_EXP_Month"), platform.value("ADCBCard_Expiry_Month"));
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_EXP_Year"));
		safeSelect(driver, getObjectPayment("PaymentPage_ADCB_EXP_Year"), platform.value("ADCBCard_Expiry_Year"));
		safeType(driver, getObjectPayment("PaymentPage_ADCB_CardName"), "test");
		safeType(driver, getObjectPayment("PaymentPage_ADCB_CVV"), platform.value("ADCBCard_CVV"));
		safeClick(driver, getObjectPayment("PaymentPage_ADCB_CheckBlance_Btn"));
		textPresent_Log(driver,"You will redeem AED",5);		
		String Total = getText(driver, By.cssSelector("span.fs-6.fw-700"));
		Assert.assertEquals(Total, "AED  0");
		String RedeemBtn = getText(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Assert.assertEquals(RedeemBtn, "Redeem");			
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));		
		Reporter.log("Redeem button is Clicked");
		elementPresent_log(driver, By.id("OTP"), "OTP", 20);
		textPresent_Log(driver, "Enter one time password", 1);
		textPresent_Log(driver, "Enter OTP sent to your registered mobile number", 1);
		textPresent_Log(driver, "OTP has been successfully sent to above mobile number", 5);
		safeClick(driver, By.id("OTP"));
		textPresent_Log(driver, "Haven't received OTP?", 5);
		elementPresent_log(driver, By.xpath("//div[4]/p/span"), "resend OTP link", 5);
		safeType(driver, By.id("OTP"), "101010");
		elementPresent_log(driver, By.xpath("//form/button"), "Confirm OTP", 2);
		Reporter.log("Confirm OTP button is displayed");
		String ConfirmBt = getText(driver, By.xpath("//form/button"));
		Assert.assertEquals(ConfirmBt, "Confirm OTP");
		safeClick(driver, By.cssSelector("svg.c-neutral-900.c-pointer"));// click on close popup

		//-------- Checking Partial Payment in ADCB-----------------------//
		
		safeType(driver, getObjectPayment("PaymentPage_ADCB_Redeem_Amount_TextBox"), "AED 50");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		if(!BookingType.contains("API")) {
		textPresent_Log(driver, "Choose a payment option", 10);		// payu bank page
		}
		}
	
	public void payUI_Select_KNET(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
		elementVisible(driver, getObjectPayment("PaymentPage_Knet_RadioButton"), 5);
		safeClick(driver, getObjectPayment("PaymentPage_Knet_RadioButton"));
		elementPresent_log(driver, getObjectPayment("PaymentPage_Knet_Image"), "", 1);

		if(common.value("Bento_Payment").equalsIgnoreCase("true")|| BookingType.contains("TRAINS")) {		
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Reporter.log("Make Payment button is Clicked");
		}
		
	}

	public void Enter_CC_Details(RemoteWebDriver driver, String CCNumber, String CCExpMonth, String CCExpYear, String CVV) throws Exception {
		Thread.sleep(1000);
		Reporter.log("Card Details +\n"+ CCNumber +"\n " + CCExpMonth  +" " + CCExpYear +" " + CVV);
		safeType(driver, getObjectPayment("PaymentPage_CreditCard_Number"), CCNumber);
		safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"));
		safeSelect(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Month"), CCExpMonth);
		safeClick(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"));
		Thread.sleep(1000);
		safeSelect(driver, getObjectPayment("PaymentPage_CreditCard_Exp_Year"), CCExpYear);
		safeType(driver, getObjectPayment("PaymentPage_CreditCard_Name"), "test");
		safeType(driver, getObjectPayment("PaymentPage_CreditCard_CVV"), CVV);
		Thread.sleep(1000);
	}
	
	public void Enter_CARD_Details_PWA(RemoteWebDriver driver, String CCNumber, String CCExp, String CVV) throws Exception {
		Reporter.log("Card Details +\n"+ CCNumber +"\n " + CCExp +" " + CVV);
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Number"), CCNumber);
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Expiry"), CCExp);
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_CVV"), CVV);
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
		}else if(BankName.equalsIgnoreCase("Axis Bank")||BankName.equalsIgnoreCase("AxisBankPopular")) {
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), 20);
			elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"), "Citi Bank  ", 1);
			Reporter.log("CitiBank Auth page is displayed");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Citibank_Submit_Btn"));
		}
		
		else if(BankName.equalsIgnoreCase("Hdfc Bank")) {
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

	public void payUI_Select_NB(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
			elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 5);
			textPresent_Log(driver, "Popular banks", 1);
			//textPresent_Log(driver, "", 1);
			if(BankName.contains("CAPTCHA")) {
				safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), "Citibank");
			}
			else if(BankName.contains("CitibankPopular")) {
				safeClick(driver, getObjectPayment("PaymentPage_NB_PopularBanks_Citi"));
			}
			else if(BankName.contains("AxisbankPopular")) {
				safeClick(driver, getObjectPayment("PaymentPage_NB_PopularBanks_Axis"));
			}
			else {
				safeClick(driver, getObjectPayment("PayUI_NB_DropDown"));
				Thread.sleep(5000);
				safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), BankName);
			
			}

			if(common.value("Bento_Payment").equalsIgnoreCase("true")||BookingType.contains("TRAINS")) {
			safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
			payUI_BankPage(driver, BankName);
			}
			
	}	
	
	public void payUI_Select_UPI(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {	

		textPresent(driver, "Select UPI partner to make your payment", 5);
		elementVisible(driver, getObjectPayment("SelectPayment_UPI_PhonePe"), 5);
		safeClick(driver, getObjectPayment("SelectPayment_UPI_PhonePe"));		

		if(common.value("Bento_Payment").equalsIgnoreCase("true")||BookingType.contains("TRAINS")) {
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_BankPage(driver, BankName);
		}
	}	

	public void bento_Select_PaymentType(RemoteWebDriver driver, String PayType) throws Exception {
		for (int i = 0; i < 10; i++) {			
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
		if(!elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 10)) {
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
		case "PayPal":
			PayType = "PayPal";
			break;
		default:
			PayType = "Credit Card";
			break;
		}		
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), PayType);	
	}

	public void bento_Select_CC(RemoteWebDriver driver, String BankName, String BookingType) throws Exception {		
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
			elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 5);
			textPresent_Log(driver, "Popular banks", 1);
			//textPresent_Log(driver, "", 1);
			if(BankName.contains("CAPTCHA")) {
				safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), "Citibank");
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
	
}
