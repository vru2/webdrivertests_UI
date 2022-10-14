package test.java.paymentsBento_Itn_Hotels_PWA;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import test.java.paymentsBento_Itn_Hotels.PaymentsBento_Itn_Hotels_Common;

import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class PaymentsBento_Itn_Hotels_Common_PWA extends PaymentsBento_Itn_Hotels_Common {

	public void hotelsPaymentPage_PWA(RemoteWebDriver driver, String PaymentType, String CardNumber, String Domain, String PayType, String BankName) throws Exception {
		//hotelsPayment_Page_Validation(driver, PayType, Domain);
		paymentPageHotels_PWA(driver, PaymentType, CardNumber, Domain, PayType, BankName);
	}

	public void paymentPageHotels_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType,String BankName) throws Exception {
		if(elementVisible(driver, getObjectPayment("PWA_PaymentPage_ConvFee_Txt"), 30))
		{
			bento_paymentpage_PWA(driver,PaymentType, CardNumber,domain,PayType,BankName);
			if(!(CardNumber=="ADCB"||PaymentType=="Phonepe"||PaymentType=="UPIScan"||PayType=="Googlecaptcha"))
			{
				confirmation_page_hotel_PWA(driver, PaymentType, CardNumber);
			}
		}
		else if(textPresent(driver,"Sorry, our servers are stumped with your request",1)||textPresent(driver,"Flight not available",1))
		{
			Reporter.log("Booking failed due to itn page issue");
			assertTrue(false);
		}
		else
		{
			Reporter.log("Booking failed due to itn page issue");
			assertTrue(false);
		}
	}

	public void bento_paymentpage_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		textPresent_Log(driver, "Includes a convenience fee of", 20);
		System.out.println(driver.getCurrentUrl());
		Reporter.log(driver.getCurrentUrl());


		if (PaymentType.equalsIgnoreCase("WALLET")||PayType.equalsIgnoreCase("WALLET"))
		{
			safeClick(driver, getObjectPayment("PWA_PaymentPage_Wallet_Toggle"));
			Reporter.log("Selected wallet");
			Thread.sleep(2000);
		}
		switch (PaymentType) {
			case "storedcard":
				bento_pay_StoredCard_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "storedcardUPI":
				bento_pay_StoredCard_UPI_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "CC":
				bento_pay_CC_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "EMI":
				bento_pay_EMI_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "NB":
				bento_pay_NB_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "TW":
				bento_pay_TW_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "wallet":
				bento_pay_Wallet_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "Phonepe":
				bento_pay_PhonePe_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "UPI":
				bento_pay_UPI_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "GV":
				bento_pay_GV_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "GV_Partial":
				bento_pay_GV_Partial_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "partial_wallet":
				bento_pay_PartialWallet_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "Coupon":
				bento_pay_Coupon_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "sc":
				bento_pay_SC_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
			case "OTH":
				bento_pay_Others_PWA(driver, PaymentType, CardNumber, domain, PayType, BankName);
				break;
		}

	}

	public void bento_pay_StoredCard_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA(driver, "SC");
		textPresent_Log(driver, "Select a saved payment mode", 5);
		if(CardNumber == "4111")
		{
			Thread.sleep(1000);
			safeClick(driver, getObjectPayment("Bento_Payment_Select_DC_Storedcard"));
			Reporter.log("Clicked on SC");
			Thread.sleep(1000);
			safeClick(driver, getObjectPayment("Bento_Payment_SC_DC_CVV"));
			safeType(driver, getObjectPayment("Bento_Payment_SC_DC_CVV"), "111");
			Reporter.log("Entered CVV");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
			{
				safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
				Thread.sleep(1000);
			}
			textPresent_Log(driver, "Please wait...", 5);
			textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 5);
			safeClick(driver, getObjectPayment("Bento_Payment_DC_Payment_Success"));
			textPresent_Log(driver, "Your booking is done", 5);
			Reporter.log("Payment done successfully");
		}
		else if(CardNumber=="5241") {
			if (domain.equals("HOTELS")) {
				safeClick(driver, getObjectPayment("Bento_Payment_SC_Razropay_CVV_HOTELS"));
				safeType(driver, getObjectPayment("Bento_Payment_SC_Razropay_CVV_HOTELS"), "123");
			}
		}
		else if(CardNumber=="7777") {
			if (domain.equals("HOTELS")) {
				safeClick(driver, getObjectPayment("Bento_Payment_SC_Tokenized_CVV_7777"));
				safeType(driver, getObjectPayment("Bento_Payment_SC_Tokenized_CVV_7777"), "123");
				//safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			/*textPresent_Log(driver, "Bank Demo", 20);

			safeClick(driver, getObjectPayment("Bento_Payment_SC_Tokenized_CVV_7777_Success"));*/
				//Thread.sleep(5000);
			}
		}
		else{
			safeClick(driver, getObjectPayment("Bento_Payment_SC_Razropay_CVV"));
			safeType(driver, getObjectPayment("Bento_Payment_SC_Razropay_CVV"), "123");
		}
		Reporter.log("Entered CVV");
		safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
		Reporter.log("Clicked on paynow");
		if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),1))
		{
			safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
			Thread.sleep(1000);
		}
		if(textPresent(driver, "Enter OTP", 20)) {

			textPresent_Log(driver, "Enter OTP", 10);
			safeClick(driver, getObjectPayment("Bento_Payment_Razropay_Pin"));
			safeType(driver,getObjectPayment("Bento_Payment_Razropay_Pin"),"0000");
			safeClick(driver,getObjectPayment("Bento_Payment_Razropay_Submit"));
			textPresent_Log(driver, "Your booking is done", 5);
			Reporter.log("Payment done successfully");
		}
		else if(textPresent(driver, "Bank Demo", 1)) {
			if(elementVisible(driver, By.xpath("//button[@onclick='submitSuccess()']"), 5))
			{
				safeClick(driver, By.xpath("//button[@onclick='submitSuccess()']"));
			}
		}

		else
		{
			safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
			Reporter.log("Clicked on SC");
			safeClick(driver, getObjectPayment("Bento_Payment_SC_CVV"));
			safeType(driver, getObjectPayment("Bento_Payment_SC_CVV"), "1234");
			Reporter.log("Entered CVV");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			if(elementVisible(driver,getObjectPayment("Bento_Payment_Skip_Securecard"),2))
			{
				safeClick(driver,getObjectPayment("Bento_Payment_Skip_Securecard"));
				Thread.sleep(1000);
			}
			textPresent_Log(driver, "Please wait...", 2);
			textPresent_Log(driver, "American", 2);
			textPresent_Log(driver, "ACS Emulator", 2);
			safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
			textPresent_Log(driver, "Your booking is done", 5);
			Reporter.log("Payment done successfully");
		}
	}

	public void bento_pay_StoredCard_UPI_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_EMI_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_NB_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_TW_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);
	}

	public void bento_pay_Wallet_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_PhonePe_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}
	public void bento_pay_UPI_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		scrollSmooth(driver, 400);
		payUI_Select_PaymentType_PWA( driver, PaymentType);
		textPresent_Log(driver, "Add new UPI address", 10);
		safeType(driver, getObjectPayment("PWA_PaymentPage_UPI_TextBox"),"kiran@ybl");
		safeClick(driver, getObjectPayment("PWA_PaymentPage_Pay_Button"));
		Thread.sleep(5000);
	}

	public void bento_pay_GV_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_GV_Partial_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_PartialWallet_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_CC_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_Coupon_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_SC_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void bento_pay_Others_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		payUI_Select_PaymentType_PWA( driver, PaymentType);

	}

	public void payUI_Select_PaymentType_PWA(RemoteWebDriver driver, String PayType) throws Exception {
		for (int i = 0; i < 10; i++) {
			if(textPresent(driver, "System error", 1)) {
				Reporter.log("There's something wrong with our system");
				org.junit.Assert.assertTrue(false);
			} else if(textPresent(driver, "Oops, Something went wrong", 1)) {
				Reporter.log("Oops something wrong with our system");
				org.junit.Assert.assertTrue(false);
			}
			if(elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs_PWA"), 1)) {
				break;
			}
		}
		if(!elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs_PWA"), 10)) {
			Reporter.log("PayUI Page is not displayed");
			Assert.assertTrue(false);
		}
		switch (PayType) {
			case "CC":
				PayType = "DEBIT/CREDIT CARDS";
				break;
			case "DC":
				PayType = "ADD NEW CARD";
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
				PayType = "ADCB touchPoints";
				break;
			case "SC":
				PayType = "SAVED PAYMENT MODES";
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
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs_PWA"), PayType);
	Thread.sleep(5000);
	}


	public void confirmation_page_hotel_PWA(RemoteWebDriver driver, String PaymentType, String CardNumber) throws Exception {
		textPresent_Log(driver,"Booking confirmed", 30);
		String tripId = getText(driver, By.xpath("//li[3]/p[2]"));
		System.out.println("TripID"+tripId);
		Reporter.log("TripID"+tripId);
	}



	}