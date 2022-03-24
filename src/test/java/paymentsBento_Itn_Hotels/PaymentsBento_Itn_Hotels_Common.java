package paymentsBento_Itn_Hotels;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import paymentsBento_Itn.PaymentsBento_Itn_Common;

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
	
	public String hotelSearchUrl(String Domain)  throws Exception
	{	
		Hotel_URL= "/hotels/results?city=Bangalore&state=Karnataka&country=IN&poi=&hotelId=&dest_code=32550&chk_in="+getDateTime(10, "dd/MM/yyyy")+"&chk_out="+getDateTime(11, "dd/MM/yyyy")+"&adults=2&childs=0&num_rooms=1&adults1=2&children1=0&";
		String SearchUrl = "";
		if(Domain=="IN") {
			SearchUrl=inurl+Hotel_URL;
		}
		return SearchUrl;
	}
	
	public String hotelDetailsUrl(String Domain, String HotelID)  throws Exception
	{	
		Hotel_URL= "/hotels/details/"+HotelID+"?c="+getDateTime(30, "ddMMyy")+"|"+getDateTime(31, "ddMMyy")+"&r=2,0";
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
				Thread.sleep(5000);
				driver.navigate().to(driver.getCurrentUrl());
				Thread.sleep(5000);
				Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
				}
			Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
				
			}
		textPresent_Log(driver, "View all hotels in Bangalore", 10);
		}
	
	public void hotelsDetailsPage(RemoteWebDriver driver, String HotelName, String Price) throws Exception {
		refreshPage(driver);
		elementPresent_log(driver, getObjectPayment("Hotel_Details_HotelName"), "Hotel name in details page", 30);
		safeClick(driver, getObjectPayment("Hotel_Details_SelectRoom_Btn"));
		elementVisible(driver, getObjectPayment("Hotel_Details_Book_Btn"), 5);
		safeClick(driver, getObjectPayment("Hotel_Details_Book_Btn"));
		String parent=driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();
		Iterator<String> I1= s.iterator();
		String Child_URLs="";
		while(I1.hasNext())
		{
		String child_window=I1.next();
		if(!parent.equals(child_window))
		{driver.switchTo().window(child_window);}
		}
		textPresent_Log(driver, "Book in four simple steps", 30);
		Child_URLs = driver.getCurrentUrl();
		driver.switchTo().window(parent);		
		driver.get(Child_URLs);
	}
	
	public void hotelsItnPage(RemoteWebDriver driver, String CouponGV, String PayType, String SignIN, String Contact) throws Exception {
		hotelsItnDetails(driver, CouponGV, PayType);
		hotelsItnSignIN(driver, SignIN, PayType);
		hotelsItnContact(driver, Contact, PayType);
	}
		
	
	public void hotelsItnDetails(RemoteWebDriver driver, String CouponGV, String PayType) throws Exception {
		refreshPage(driver);
		elementPresent_log(driver, By.xpath("//div[16]/button"), "Itinerary page button", 30);
		textPresent(driver, "Review your itinerary", 1);
		mouseHover(driver, By.xpath("//div[16]/button"));
		safeClick(driver, By.xpath("//div[16]/button"));
		//safeClick(driver, By.xpath("//div[@id='root']/div/main/div[2]/div/div/article/div[2]/div/div/div[16]/button"));
	}
	
	public void hotelsItnSignIN(RemoteWebDriver driver, String SignIN, String PayType) throws Exception {
		elementPresent_log(driver, By.xpath("//div[7]/button"), "Signin Step button", 30);
		textPresent(driver, "Add contact details", 1);
		safeType(driver,By.xpath("//div[2]/div/input"), "1211212121");
		safeType(driver,By.xpath("//div[4]/div/div/input"), "ct_wallet_patial@cleartrip.com");
		
		safeClick(driver, By.xpath("//div[7]/button"));
	}
	
	public void hotelsItnContact(RemoteWebDriver driver, String Contact, String PayType) throws Exception {
		elementPresent_log(driver, By.xpath("//div[10]/div/button"), "Traveller Contine button", 2);
		textPresent(driver, "Add traveller details", 1);
		mouseHover(driver, By.xpath("//div[3]/div/div/div/button"));
		safeClick(driver, By.xpath("//div[3]/div/div/div/button"));
		safeClick(driver, By.xpath("//li"));
		safeType(driver, By.name("firstName"), "Kiran");
		safeType(driver, By.name("lastName"), "Kumar");
		mouseHover(driver, By.xpath("//div[10]/div/button"));
		safeClick(driver, By.xpath("//div[10]/div/button"));
	}
	
	public void hotelsPayment_Page_Validation(RemoteWebDriver driver, String PayType, String Domain) throws Exception {
		
	}
	
	public void hotelsPaymentPage(RemoteWebDriver driver, String PaymentType, String CardNumber, String Domain, String PayType, String BankName) throws Exception {
		hotelsPayment_Page_Validation(driver, PayType, Domain);
		paymentPageHotels(driver, PaymentType, CardNumber, Domain, PayType, BankName);
	}
}