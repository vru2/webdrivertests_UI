package test.java.commonUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import test.java.commonUI.PaymentsBento_Common_PWA;

import static org.testng.Assert.assertTrue;

public class PaymentsBento_Itn_Hotels_Common_PWA extends PaymentsBento_Common_PWA {

	public void hotelsPaymentPage_PWA(RemoteWebDriver driver, String PaymentType, String CardNumber, String Domain, String PayType, String BankName, String SucessFail) throws Exception {
		//hotelsPayment_Page_Validation(driver, PayType, Domain);
		//paymentPageHotels_Destop_to_PWA(driver, "");
		paymentPageHotels_PWA(driver, PaymentType, CardNumber, Domain, PayType, BankName, SucessFail);
	}

	public void paymentPageHotels_Destop_to_PWA(RemoteWebDriver driver, String PayType) throws Exception {
		textPresent(driver,"Pay to complete your booking",30);
		String URL = getURL(driver);
		driver=(RemoteWebDriver) getMobileDriver(driver);
		driver.get(URL);
		Object webDriver = driver;
		((JavascriptExecutor) webDriver).executeScript("window.focus();");
	}

	public void paymentPageHotels_PWA(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType,String BankName, String SuccessFail) throws Exception {
		//if(elementVisible(driver, getObjectPayment("PWA_PaymentPage_ConvFee_Txt"), 30))
		if(!textPresent(driver,"NET BANKING",5))
		{
			bento_Paymentpage_PWA(driver,PaymentType, CardNumber,domain,PayType,BankName, SuccessFail);
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


	public void confirmation_page_hotel_PWA(RemoteWebDriver driver, String PaymentType, String CardNumber) throws Exception {
		textPresent_Log(driver,"Booking confirmed", 30);
		String tripId = getText(driver, By.xpath("//li[3]/p[2]"));
		System.out.println("TripID"+tripId);
		Reporter.log("TripID"+tripId);
	}



	}