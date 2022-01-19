package paymentsBento_Itn_Hotels;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import paymentsBento_Itn.PaymentsBento_Itn_Common;

public class PaymentsBento_Itn_Hotels_Common extends PaymentsBento_Itn_Common {
	String URL = "";
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
		URL= "/hotels/results?city=Bangalore&state=Karnataka&country=IN&poi=&hotelId=&dest_code=32550&chk_in="+getDateTime(10, "dd/MM/yyyy")+"&chk_out="+getDateTime(11, "dd/MM/yyyy")+"&adults=2&childs=0&num_rooms=1&adults1=2&children1=0&";
		String SearchUrl = "";
		if(Domain=="IN") {
			SearchUrl=inurl+URL;
		}
		return SearchUrl;
	}

	public void hotelSearchPage(RemoteWebDriver driver, String HotelName, String Price) throws Exception {
		elementVisible(driver, getObjectPayment("Hotel_SRP_Book_Btn"), 20);
		textPresent_Log(driver, "Price per room, per night", 1);
		safeAutocomplete(driver, getObjectPayment("Hotel_SRP_HotelSearch_Text_Box"), getObjectPayment("Hotel_SRP_HotelSearch_Text_AJAX"), HotelName);
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
				System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
				Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
				}
			Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
				
			}
		textPresent_Log(driver, "View all hotels in Bangalore", 10);
		}
	
	public void hotelDetailsPage(RemoteWebDriver driver, String HotelName, String Price) throws Exception {
		
	}
	
	public void hotelItn_Details(RemoteWebDriver driver, String CouponGV, String PayType) throws Exception {
		
	}
	
	public void hotelItn_SignIN(RemoteWebDriver driver, String SignIN, String PayType) throws Exception {
		
	}
	
	public void hotelItn_Contact(RemoteWebDriver driver, String Contact, String PayType) throws Exception {
		
	}
	
	public void hotelPayment_Page(RemoteWebDriver driver, String PayType, String BankType, String CardType) throws Exception {
		
	}
}