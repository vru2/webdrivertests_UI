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


import test.java.paymentsBento_Itn.PaymentsBento_Itn_Common;
import test.java.paymentsUI_Air.GV_NB;

public class PaymentsBento_Itn_Hotels_Common extends PaymentsBento_Itn_Common {
	String Hotel_URL = "";
	String inurl = "https://qa2.cleartrip.com";
	String aeurl = "https://qa2.cleartrip.ae";
	String bhurl = "https://qa2bh.cleartrip.com";
	String qaurl = "https://qa2qa.cleartrip.com";
	String kwurl = "https://qa2kw.cleartrip.com";
	String saurl = "https://qa2.cleartrip.sa";
	String omurl = "https://qa2om.cleartrip.com";
	String meurl = "https://qa2me.cleartrip.com";

	String GV_number = "3000331034254943";
	String GV_pin = "158662";
	String hotelPrice_Itinerary = null;
	String hotelPrice_PaymentPage = null;

	String hotelName_DetailsPage1 = "royal-inn-2628930";

	public String  hotelName_DetailsPage = "holiday-inn-express-suites-bengaluru-racecourse-2052760";

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
		Hotel_URL= "/hotels/details/"+HotelID+"?c="+getDateTime(27, "ddMMyy")+"|"+getDateTime(28, "ddMMyy")+"&r=2,0&ur=1";
		String SearchUrl = "";
		if(Domain=="IN") {
			SearchUrl=inurl+Hotel_URL;
		}
		System.out.println(SearchUrl);
		return SearchUrl;
	}

	public String hotelDetailsUrl_3Days(String Domain, String HotelID)  throws Exception
	{
		Hotel_URL= "/hotels/details/"+HotelID+"?c="+getDateTime(30, "ddMMyy")+"|"+getDateTime(33, "ddMMyy")+"&r=2,0&ur=1";
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
		//refreshPage(driver);
		if(textPresent(driver, "Sorry our servers are stumped with your request", 1)) {
			refreshPage(driver);
		}
		elementPresent_log(driver, getObjectPayment("Hotel_Details_Modify_Btn"), "Modify Button", 30);
		safeClick(driver, getObjectPayment("Hotel_Details_SelectRoom_Btn"));
		elementPresent_log(driver, getObjectPayment("Hotel_Details_Book_Btn"), "Book Button", 5);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		elementVisible(driver, getObjectPayment("Hotel_Details_Book_Btn"), 5);
		WebElement element = driver.findElement(getObjectPayment("Hotel_Details_Book_Btn"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		mouseHover(driver, getObjectPayment("Hotel_Details_Book_Btn"));
		Thread.sleep(1000);
		smartClick(driver, getObjectPayment("Hotel_Details_Book_Btn"));
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
		textPresent_Log(driver, "Review your itinerary", 30);
		Child_URL = driver.getCurrentUrl();
		//driver.close(); // Closing Child window
		driver.switchTo().window(parent);
		driver.get(Child_URL);
		textPresent_Log(driver, "Review your itinerary", 20);
		driver.manage().addCookie(cookie_Bento_Hotels);
	}

	public void hotelsItnPage(RemoteWebDriver driver, String CouponGV, String PayType, String SignIN, String Contact) throws Exception {
		hotelsItnDetails(driver, CouponGV, PayType);
		hotelsItnSignIN(driver, SignIN, PayType);
		hotelsItnContact(driver, Contact, PayType);
	}


	public void hotelsItnDetails(RemoteWebDriver driver, String CouponGV, String PayType) throws Exception {
		textPresent(driver, "Review your itinerary", 20);
		if(elementVisible(driver, By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Continue with Email'])[1]/preceding::*[name()='svg'][3]"),5)) {
			Thread.sleep(2000);
			safeClick(driver, By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Continue with Email'])[1]/preceding::*[name()='svg'][3]"));
		}
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
			textPresent_Log(driver, "redeemed from your gift card", 5);
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
		WebElement element = driver.findElement(getObjectPayment("Hotel_ItnPage_Continue_Btn"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		mouseHover(driver, getObjectPayment("Hotel_ItnPage_Continue_Btn"));
		Thread.sleep(2000);
		smartClick(driver, getObjectPayment("Hotel_ItnPage_Continue_Btn"));
	}

	public void hotelsItnSignIN(RemoteWebDriver driver, String SignIN, String PayType) throws Exception {
		elementPresent_log(driver, getObjectPayment("Hotel_SignINPage_Continue_Btn"), "Signin Step button", 30);
		textPresent(driver, "Add contact details", 1);
		//elementPresent_log(driver, getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"), "Signin Step button", 30);
		elementVisible(driver, getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"), 5);
		mouseHover(driver,getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"));
		safeType(driver,getObjectPayment("Hotel_SignINPage_PhoneNo_TextBox"), "1211212121");
		safeType(driver,getObjectPayment("Hotel_SignINPage_EmailID_TextBox"), "ct_wallet_patial@cleartrip.com");
		elementVisible(driver, getObjectPayment("Hotel_SignINPage_Continue_Btn"), 5);
		safeClick(driver, getObjectPayment("Hotel_SignINPage_Continue_Btn"));
		Reporter.log("Clicked on continue - SignIN block");
	}

	public void hotelsItnContact(RemoteWebDriver driver, String Contact, String PayType) throws Exception {
		elementPresent_log(driver, getObjectPayment("Hotel_ContactPage_Continue_Btn"), "Traveller Contine button", 10);
		textPresent(driver, "Add traveller details", 1);
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
				System.out.println("Payment page price  :"+payment_Price+" itinerary_Price_Plus_ConvFee : "+itinerary_Price_Plus_ConvFee);
				//Assert.assertTrue(false);
			}
		}
	}

	public void hotelsPaymentPage(RemoteWebDriver driver, String PaymentType, String CardNumber, String Domain, String PayType, String BankName) throws Exception {
		//hotelsPayment_Page_Validation(driver, PayType, Domain);
		paymentPageHotels(driver, PaymentType, CardNumber, Domain, PayType, BankName);
	}
}