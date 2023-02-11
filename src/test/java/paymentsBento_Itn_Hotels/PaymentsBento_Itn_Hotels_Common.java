package test.java.  paymentsBento_Itn_Hotels;

import static org.junit.Assert.assertTrue;

import java.awt.*;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import test.java.paymentsBento_com.PaymentsBento_Itn_Common;


public class PaymentsBento_Itn_Hotels_Common extends PaymentsBento_Itn_Common {
	String Hotel_URL = "";
	String inurl = "https://qa2new.cleartrip.com";
	String aeurl = "https://qa2.cleartrip.ae";
	String bhurl = "https://qa2bh.cleartrip.com";
	String qaurl = "https://qa2qa.cleartrip.com";
	String kwurl = "https://qa2kw.cleartrip.com";
	String saurl = "https://qa2.cleartrip.sa";
	String omurl = "https://qa2om.cleartrip.com";
	String meurl = "https://qa2me.cleartrip.com";

	String GV_number = "3000331034062731";
	String GV_pin = "169139";
	String hotelPrice_Itinerary = null;
	String hotelPrice_PaymentPage = null;

	String hotelName_DetailsPage1 = "royal-inn-2628930";

	public String  hotelName_DetailsPage2 = "holiday-inn-express-suites-bengaluru-racecourse-2052760";//725958

	public String  hotelName_DetailsPage = "2052760";

	public String hotelSearchUrl(String Domain)  throws Exception
	{
		Hotel_URL= "/hotels/results?city=Bangalore&state=Karnataka&country=IN&poi=&hotelId=&dest_code=32550&chk_in="+getDateTime(20, "dd/MM/yyyy")+"&chk_out="+getDateTime(21, "dd/MM/yyyy")+"&adults=2&childs=0&num_rooms=1&adults1=2&children1=0&";
		String SearchUrl = "";
		if(Domain=="IN") {
			SearchUrl=inurl+Hotel_URL;
		}
		return SearchUrl;
	}

	public String hotelDetailsUrl(String Domain, String HotelID)  throws Exception
	{

		Hotel_URL= "/hotels/details/"+HotelID+"?c="+getDateTime(70, "ddMMyy")+"|"+getDateTime(71, "ddMMyy")+"&r=2,0&ur=1";
		String SearchUrl = "";
		if(Domain=="IN") {
			SearchUrl=inurl+Hotel_URL;
		}
		Reporter.log(SearchUrl);
		System.out.println(SearchUrl);
		return SearchUrl;
	}

	public String hotelDetailsUrl_3Days(String Domain, String HotelID)  throws Exception
	{
		Hotel_URL= "/hotels/details/"+HotelID+"?c="+getDateTime(30, "ddMMyy")+"|"+getDateTime(32, "ddMMyy")+"&r=2,0&ur=1";
		String SearchUrl = "";
		if(Domain=="IN") {
			SearchUrl=inurl+Hotel_URL;
		}
		return SearchUrl;
	}


	public void hotelSearchPage(RemoteWebDriver driver, String HotelName, String Price) throws Exception {
		elementVisible(driver, getObjectPayment("Hotel_SRP_Book_Btn"), 20);
		textPresent_Log(driver, "Price per room, per night", 1);
		if(!HotelName.equals("")) {
			safeAutocomplete(driver, getObjectPayment("Hotel_SRP_HotelSearch_Text_Box"), getObjectPayment("Hotel_SRP_HotelSearch_Text_AJAX"), HotelName);
		}
		elementVisible(driver, getObjectPayment("Hotel_SRP_Book_Btn"), 5);
		safeClick(driver, getObjectPayment("Hotel_SRP_Book_Btn"));
		Thread.sleep(5000);
		String parent = driver.getWindowHandle();
		Set<String> win = driver.getWindowHandles();
		Iterator<String> page = win.iterator();
		while (page.hasNext()) {
			String child_window = page.next();
			if (!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				Thread.sleep(2000);
				driver.navigate().to(driver.getCurrentUrl());
				Thread.sleep(2000);
				Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
			}
			Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());

		}
		textPresent_Log(driver, "View all hotels in Bangalore", 10);
	}

	public void hotelsDetailsPage(RemoteWebDriver driver, String HotelName, String Price) throws Exception {
		if(textPresent(driver, "Sorry our servers are stumped with your request", 1)) {
			refreshPage(driver);
		}
		elementPresent_log(driver, getObjectPayment("Hotel_Details_Modify_Btn"), "Modify Button", 5);
		safeClick(driver, getObjectPayment("Hotel_Details_SelectRoom_Btn"));
		By selectRoomButton= By.xpath("");
		if(elementVisible(driver, getObjectPayment("Hotel_Details_Book_Btn"), 1)) {
			Thread.sleep(1000);
			selectRoomButton=getObjectPayment("Hotel_Details_Book_Btn");

		}
		else selectRoomButton=getObjectPayment("Hotel_Details_Book_Btn1");
		if(elementVisible(driver, selectRoomButton, 2)) {
			safeClick(driver, selectRoomButton);
		}
		//elementVisible(driver, getObjectPayment("Hotel_ContactPage_Continue_Btn_New"), 20);
		//Thread.sleep(5000);
		textPresent(driver, "Review your itinerary", 10);
		String parent=driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();
		Iterator<String> I1= s.iterator();
		String Child_URL="";
		while(I1.hasNext())
		{
			String child_window=I1.next();
			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);}
		}
		//textPresent(driver, "Review your itinerary", 10);
		Child_URL = driver.getCurrentUrl();
		driver.close(); // Closing Child window
		driver.switchTo().window(parent);
		driver.get(Child_URL);
		textPresent(driver, "Review your itinerary", 5);
		elementVisible(driver, getObjectPayment("Hotel_ContactPage_Continue_Btn_New"), 1);
	}

	public void hotelsItnPageOLD(RemoteWebDriver driver, String CouponGV, String PayType, String SignIN, String Contact) throws Exception {
		hotelsItnDetails(driver, CouponGV, PayType);
		hotelsItnSignIN(driver, SignIN, PayType);
		hotelsItnContact(driver, Contact, PayType);
	}

	public void hotelsItnPage(RemoteWebDriver driver, String CouponGV, String PayType, String SignIN, String Contact) throws Exception {
		textPresent(driver, "Review your itinerary", 5);
		refreshPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);
		textPresent(driver, "Add contact details", 1);
		elementVisible(driver, By.xpath("//input"), 5);
		safeType(driver, By.xpath("//input"), "1212121212");
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);
		//moveToGivenElementLocatorByActionClassAndClickByJS(driver, By.xpath("//div[5]/div/div/input"));
		safeType(driver, By.xpath("//div[5]/div/div/input"), "ctpayment@gmail.com");
		if(CouponGV.contains("GV")) {
			textPresent(driver, "Apply coupon or gift card",5);
			mouseHover(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			safeClick(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			if(CouponGV.equalsIgnoreCase("FullGV")) {
				String[] GV = getGVSCLP(10000);
				hotel_Apply_GV(driver, GV[0],GV[1]);
			//	hotel_Apply_GV(driver, GV_number,GV_pin);
			}
			else if(CouponGV.equalsIgnoreCase("PartialGV")){
				String[] GV = getGV(10);
				hotel_Apply_GV(driver, GV[0],GV[1]);
				GV = getGV(10);
				hotel_Apply_GV(driver, GV[0],GV[1]);
			}
			else if(CouponGV.equalsIgnoreCase("PartialGV_SCLP")) {
				String[] GV = getGVSCLP(10);
				hotel_Apply_GV(driver, GV[0],GV[1]);
			}
			else if(CouponGV.equalsIgnoreCase("PartialGV_SCLP_PopUp")) {
				hotel_Apply_GV(driver, PayType,SignIN);
			}

			else if(CouponGV.equalsIgnoreCase("PartialGV_SCLP_CLP")) {
				String[] GV = getGV(10);
				hotel_Apply_GV(driver, GV[0],GV[1]);
				GV = getGVSCLP(10);
				hotel_Apply_GV(driver, GV[0],GV[1]);
			}

		}
		else if(CouponGV.equalsIgnoreCase("COUPONCC")) {
			mouseHover(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			safeClick(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			safeType(driver, By.xpath("//div[3]/div/div[2]/div/input"), "CCHOTEL");
			WebElement element = driver.findElement(By.xpath("//div[2]/button"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			mouseHover(driver, By.xpath("//div[2]/div[3]/div/div/button"));
			safeClick(driver, By.xpath("//div[2]/div[3]/div/div/button"));
			textPresent_Log(driver,"Great! You just saved",5);
		}
		elementVisible(driver,getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"),5);
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);
		elementVisible(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"), 1);
		Thread.sleep(1000);
		mouseHover(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"));
		Thread.sleep(1000);
		safeClick(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"));
		Thread.sleep(1000);
		safeClick(driver, getObjectPayment("Hotel_ContactPage_Salutation_Mr"));
		safeType(driver, getObjectPayment("Hotel_ContactPage_FirstName_TextBox"), "Kiran");
		safeType(driver, getObjectPayment("Hotel_ContactPage_LastName_TextBox"), "Kumar");
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);
		safeClick(driver, getObjectPayment("Hotel_ContactPage_Continue_Btn_New"));
	}


	public void hotel_Apply_GV(RemoteWebDriver driver, String GV_Number, String GV_Pin) throws Exception {
		safeType(driver, By.xpath("//div[3]/div/div[2]/div/input"), GV_Number);
		elementVisible(driver, By.xpath("//div[2]/input"), 5);
		mouseHover(driver, By.xpath("//div[2]/input"));
		safeClick(driver, By.xpath("//div[2]/input"));
		safeType(driver, By.xpath("//div[2]/input"), GV_Pin);
		safeClick(driver, By.xpath("//div[2]/div[3]/button"));
		textPresent_Log(driver, "redeemed from your gift card", 10);
	}

	public void hotelsItnDetails(RemoteWebDriver driver, String CouponGV, String PayType) throws Exception {
		textPresent(driver, "Review your itinerary", 20);
		refreshPage(driver);
		Thread.sleep(2000);
		if(CouponGV.equalsIgnoreCase("PartialGV")) {
			textPresent(driver, "Apply coupon or gift card",5);
			/*String[] GV = getGV(10);
			mouseHover(driver, By.xpath("//input"));
			safeClick(driver, By.xpath("//input"));
			safeType(driver, By.xpath("//input"),GV[0]);
			elementVisible(driver, By.xpath("//div[2]/div/input"), 5, "GV pin not diplayed");
			mouseHover(driver, By.xpath("//div[2]/div/input"));
			safeClick(driver, By.xpath("//div[2]/div/input"));
			safeType(driver, By.xpath("//div[2]/div/input"),GV[1]);
			safeClick(driver, By.xpath("//button"));
			elementPresent_log(driver, By.xpath("//div[2]/div/div/div[3]/div[2]"), "GV Success", 10);
			textPresent(driver, "has been redeemed for this booking", 5);*/
			String[] GV = getGV(10);
			mouseHover(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			safeClick(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			safeType(driver, By.xpath("//div[3]/div/div[2]/div/input"), GV[0]);
			elementVisible(driver, By.xpath("//div[2]/input"), 5);
			mouseHover(driver, By.xpath("//div[2]/input"));
			safeClick(driver, By.xpath("//div[2]/input"));
			safeType(driver, By.xpath("//div[2]/input"),GV[1]);
			safeClick(driver, By.xpath("//div[2]/div[3]/button"));
			textPresent_Log(driver, "redeemed from your gift card", 5);
		}
		else if(CouponGV.equalsIgnoreCase("FullGV")) {
			textPresent(driver, "Apply coupon or gift card",5);
		/*	mouseHover(driver, By.xpath("//input"));
			safeClick(driver, By.xpath("//input"));
			safeType(driver, By.xpath("//input"),GV_number);
			elementVisible(driver, By.xpath("//div[2]/div/input"), 5, "GV pin not diplayed");
			mouseHover(driver, By.xpath("//div[2]/div/input"));
			safeClick(driver, By.xpath("//div[2]/div/input"));
			safeType(driver, By.xpath("//div[2]/div/input"),GV_pin);
			safeClick(driver, By.xpath("//button"));
			elementPresent_log(driver, By.xpath("//div[2]/div/div/div[3]/div[2]"), "GV Success", 10);
			textPresent(driver, "has been redeemed for this booking", 5);*/
			//String[] GV = getGV(10);
			mouseHover(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			safeClick(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			safeType(driver, By.xpath("//div[3]/div/div[2]/div/input"), GV_number);
			elementVisible(driver, By.xpath("//div[2]/input"), 5);
			mouseHover(driver, By.xpath("//div[2]/input"));
			safeClick(driver, By.xpath("//div[2]/input"));
			safeType(driver, By.xpath("//div[2]/input"),GV_pin);
			safeClick(driver, By.xpath("//div[2]/div[3]/button"));
			textPresent_Log(driver, "redeemed from your gift card", 10);
		}
		else if(CouponGV.equalsIgnoreCase("COUPONCC")) {
			/*mouseHover(driver, By.xpath("//input"));
			safeClick(driver, By.xpath("//input"));
			safeType(driver, By.xpath("//input"),"HOTELTEST123");
			safeClick(driver, By.xpath("//button"));
			elementPresent_log(driver, By.xpath("//form/div[3]/div[2]"), "Coupon Success", 10);
			textPresent(driver, "Great! You just saved", 5);*/
			mouseHover(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			safeClick(driver, By.xpath("//div[3]/div/div[2]/div/input"));
			safeType(driver, By.xpath("//div[3]/div/div[2]/div/input"), "HOTELCC");
			WebElement element = driver.findElement(By.xpath("//div[2]/button"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(2000);
			mouseHover(driver, By.xpath("//div[2]/div[3]/div/div/button"));
			safeClick(driver, By.xpath("//div[2]/div[3]/div/div/button"));
			textPresent_Log(driver,"Great! You just saved",5);
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		elementVisible(driver, getObjectPayment("Hotel_ItnPage_Continue_Btn"), 5);
		Thread.sleep(2000);
		smartClick(driver, getObjectPayment("Hotel_ItnPage_Continue_Btn"));
		smartClick(driver, getObjectPayment("Hotel_ItnPage_Continue_Btn2"));
	}

	public void hotelsItnSignIN(RemoteWebDriver driver, String SignIN, String PayType) throws Exception {
		elementPresent_log(driver, getObjectPayment("Hotel_SignINPage_Continue_Btn"), "Signin Step button", 30);
		textPresent(driver, "Add contact details", 1);
		//elementPresent_log(driver, getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"), "Signin Step button", 30);
		elementVisible(driver, getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"), 5);
		//mouseHover(driver,getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"));
		WebElement element = driver.findElement(getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		mouseHover(driver, getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"));
		safeType(driver,getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"), "1211212121");
		safeType(driver,getObjectPayment("Hotel_SignINPage_EmailID_TextBox"), "ct_wallet_patial@cleartrip.com");
		elementVisible(driver, getObjectPayment("Hotel_SignINPage_Continue_Btn"), 5);
		safeClick(driver, getObjectPayment("Hotel_SignINPage_Continue_Btn"));
		Reporter.log("Clicked on continue - SignIN block");
	}

	public void hotelsItnContact(RemoteWebDriver driver, String Contact, String PayType) throws Exception {
		elementPresent_log(driver, getObjectPayment("Hotel_ContactPage_FirstName_TextBox"), "Traveller Contine button", 10);
		textPresent(driver, "Add traveller details", 1);
		elementVisible(driver,getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"),5);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,600)");
		elementVisible(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"), 5);
		Thread.sleep(1000);
		mouseHover(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"));
		safeClick(driver, getObjectPayment("Hotel_ContactPage_Salutation_Dropdown"));
		safeClick(driver, getObjectPayment("Hotel_ContactPage_Salutation_Mr"));
		safeType(driver, getObjectPayment("Hotel_ContactPage_FirstName_TextBox"), "Kiran");
		safeType(driver, getObjectPayment("Hotel_ContactPage_LastName_TextBox"), "Kumar");
		hotelPrice_Itinerary=  getText(driver, By.xpath("//div/div[2]/div/div/div/div[3]/p"));
		hotelPrice_Itinerary = hotelPrice_Itinerary.replace("₹", "").replace(",", "");
		driver.findElement(getObjectPayment("Hotel_ContactPage_Continue_Btn")).sendKeys(Keys.PAGE_DOWN);
		elementVisible(driver, getObjectPayment("Hotel_ContactPage_Continue_Btn"), 5);
		WebElement ele=driver.findElement(getObjectPayment("Hotel_ContactPage_Continue_Btn"));
		ele.sendKeys(Keys.END);
		elementVisible(driver,getObjectPayment("Hotel_ContactPage_Continue_Btn"),5);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		elementVisible(driver, getObjectPayment("Hotel_ContactPage_Continue_Btn"), 5);
		Thread.sleep(1000);
		safeClick(driver,getObjectPayment("Hotel_ContactPage_Continue_Btn"));
		Reporter.log("Clicked on continue - traveller block");
	}

	public void hotelsPayment_Page_Validation(RemoteWebDriver driver, String PayType, String Domain) throws Exception {
		if(textPresent(driver, "Oops, Something went wrong.", 1)) {
			refreshPage(driver);
		}
		if(textPresent(driver, "Oops, Something went wrong.", 1)) {
			Assert.assertTrue(false);
		}
		if(!(PayType.contains("GV")||PayType.contains("WALLET"))) {
			hotelPrice_PaymentPage=  getText(driver, By.xpath("//p[2]/span")).replace("₹ ", "").replace(",", "");
			int payment_Price=Integer.parseInt(hotelPrice_PaymentPage);
			int itinerary_Price=Integer.parseInt(hotelPrice_Itinerary);
			int itinerary_Price_Plus_ConvFee=itinerary_Price+170; // added conv fee
			System.out.println("Payment page price  :"+payment_Price+" itinerary_Price_Plus_ConvFee : "+itinerary_Price_Plus_ConvFee);
			if(payment_Price!=itinerary_Price_Plus_ConvFee) {
				Reporter.log("Payment page price  :"+payment_Price+" itinerary_Price_Plus_ConvFee : "+itinerary_Price_Plus_ConvFee);
				//System.out.println("Payment page price  :"+payment_Price+" itinerary_Price_Plus_ConvFee : "+itinerary_Price_Plus_ConvFee);
				//Assert.assertTrue(false);
			}
		}
	}

	public void hotelsPaymentPage(RemoteWebDriver driver, String PaymentType, String CardNumber, String Domain, String PayType, String BankName, String TestDetails) throws Exception {
		//hotelsPayment_Page_Validation(driver, PayType, Domain);
		paymentPageHotels(driver, PaymentType, CardNumber, Domain, PayType, BankName, TestDetails);
	}
}