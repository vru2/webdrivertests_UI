package paymentsBento_Itn;

import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import paymentsBento_com.PaymentUI_Common_Bento;

public class PaymentsBento_Itn_Common extends PaymentUI_Common_Bento {
	public Cookie ctauth = new Cookie("ct-auth","kQqdrcVR8t4znRp8uzBQJgaacI%2B5mUEhQsXqP%2BGvCv9Sca3PAxik9%2FDoNKFAEq5S6nDr3dyz0gFHshmzL9GNaG4e8msn1sCvUt92FE1Hxz%2B449dUBXvxJapPKHtcbOExsOm%2BE43PNH%2FbzMr%2Bgv0v9PZIafGsbWEbtoycPG3UjA%2BzcqiD2kXHlH7Tnnt7Xdd%2B");
	public RemoteWebDriver driver;
	public Cookie cookie_Bento_Payment = new Cookie("isBentoPayment", "true");
    public Cookie ctauth_amex=new Cookie("ct-auth","2%2BtU1cPb8lJr0jvLEAtykB9OU0fk%2F%2BykRqo7fqGZ%2FgNdUi7dMNUxWo%2BLayLyBmIQH8YOfEGj8AeevvMX%2F4QnQkvAnTTp9N%2FfevCUsB0kyv34RHOgDXRfdndn%2FWd0KOXhimPy3b9gj7V56t4fbK1hHoIzQYBzwMa%2Fi72%2FqTSKtPUlKo9yE91%2BeAEj2Bi%2FZIx%2FcqFKCJETXpAxsR3%2FhUWMrg%3D%3D");
    protected String username = "varalakshmi.venkateshaiah@cleartrip.com";
	public Cookie ctauth_partial_wallet = new Cookie("ct-auth","Bk7N%2FtlW6UIM9%2Fv06RR0lzYwI2Wr5NoY6shicJ7wSEglXjP2rTXj7vKCCjzDFS1EH8YOfEGj8AeevvMX%2F4QnQkvAnTTp9N%2FfevCUsB0kyv34RHOgDXRfdndn%2FWd0KOXh%2F2AX0kdZPIqgx5R%2FHygKQrR425YROepvP0SdSctCUtkcciwXF7FvVYKJizsM6Az33Pdp0Z8op1wWr79u2xWoxw%3D%3D");
	public Cookie bentoitn = new Cookie("forcedBentoItn", "true");
	public Cookie bento = new Cookie("isBento", "true");

	String GV_number = "3000331032706754";
	String GV_pin = "161652";

	JavascriptExecutor jse = (JavascriptExecutor) driver;
	String contactnumber = "12345678";

	String searchurl = "/flights/results?adults=1&childs=0&infants=0&class=Economy&depart_date=29/05/2022&from=BLR&to=HYD&intl=n&origin=BLR - Bangalore, IN &destination=HYD - Hyderabad, IN &sd=1629707401889&rnd_one=O&sourceCountry=Hyderabad&destinationCountry=Bangalore";
	
	String searchurl1 ="/flights/results?adults=1&childs=0&infants=0&depart_date=05/05/2022&return_date=&intl=n&from=BLR&to=CCU&airline=&carrier=&sd=1641801867427&page=&sellingCountry=IN&ssfi=&flexi_search=&ssfc=&origin=BLR%20-%20Bangalore,%20IN&destination=CCU%20-%20Kolkata,%20IN&class=Economy";
	String qa2url = "https://qa2.cleartrip.com";
	String aeurl = "https://qa2.cleartrip.ae";
	String bhurl = "https://qa2bh.cleartrip.com";
	String qaurl = "https://qa2qa.cleartrip.com";
	String kwurl = "https://qa2kw.cleartrip.com";
	String saurl = "https://qa2.cleartrip.sa";
	String omurl = "https://qa2om.cleartrip.com";
	String meurl = "https://qa2me.cleartrip.com";

	// Booking via search page
	public void Searchpagebook(RemoteWebDriver driver,String wallettype, String domain, String cardtype) throws Exception 
	{
		driver.manage().addCookie(bentoitn);
		if (wallettype == "Partial") 
		{
			driver.manage().addCookie(ctauth_partial_wallet);
		} 
		/*
		 * else if(cardtype=="amex") { driver.manage().addCookie(ctauth_amex); }
		 */
		else {
			driver.manage().addCookie(ctauth);
		}
		driver.navigate().refresh();
		//Thread.sleep(9000);
		elementPresent_log(driver, getObjectPayment("Bento_Book_Button"), "Book", 30);
		 if(domain=="com")
		 {
			 if(elementVisible(driver,By.xpath("//img[@alt='Air India']"),5))
			 {
				 Actions actions=new Actions(driver);
	           	 actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				 smartClick(driver,By.xpath("//div[5]/div[2]/div/label[2]/div/span"));
			 }
			 Thread.sleep(1000);
			 if (elementVisible(driver, getObjectPayment("Bento_Indigo_Logo"), 5)) 
	      { 
			     elementVisible(driver, getObjectPayment("Bento_Book_Button"), 5);
			     Thread.sleep(1000);
			     smartClick(driver, getObjectPayment("Bento_Book_Button"));
			  }
			 else if (elementVisible(driver, getObjectPayment("Bento_Spicejet_Logo"), 3))
	      {
			      smartClick(driver, getObjectPayment("Bento_Book_Button"));
			  } 
			 else if (elementVisible(driver, getObjectPayment("Bento_Vistara_Logo"), 3)) 
	      {
	        smartClick(driver, getObjectPayment("Bento_Book_Button"));
			  }
				/*
				 * else { if (textPresent(driver,"Air India",5)) { Actions actions=new
				 * Actions(driver); actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				 * smartClick(driver,By.xpath("//div[5]/div[2]/div/label[2]/div/span")); }
				 * Thread.sleep(1000); if (elementVisible(driver,
				 * getObjectPayment("Bento_Indigo_Logo"), 5)) {
				 * smartClick(driver,By.xpath("//div[4]/button")); } else if
				 * (elementVisible(driver, getObjectPayment("Bento_Spicejet_Logo"), 3)) {
				 * smartClick(driver, getObjectPayment("Bento_Book_Button")); } else if
				 * (elementVisible(driver, getObjectPayment("Bento_Vistara_Logo"), 3)) {
				 * smartClick(driver, getObjectPayment("Bento_Book_Button")); } }
				 */
		 }
	  else
	  {
	       if (elementVisible(driver, getObjectPayment("Bento_Indigo_Logo"), 5)) 
	       {      
	    	      Thread.sleep(1000);
			      smartClick(driver,By.xpath("//div[4]/button"));
			   }
			   else if (elementVisible(driver, getObjectPayment("Bento_Spicejet_Logo"), 3))
	       {
			      smartClick(driver, getObjectPayment("Bento_Book_Button"));
			   } 
			   else if (elementVisible(driver, getObjectPayment("Bento_Vistara_Logo"), 3)) 
	       {
	          smartClick(driver, getObjectPayment("Bento_Book_Button"));
			   } 
			   else 
			   {
					System.out.println("LDAP was displayed");
					Reporter.log("LDAP was displayed");
				}
	   }
		Reporter.log("Clicked on Book");
		String parent = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) 
			{
				driver.switchTo().window(child_window);
				Thread.sleep(5000);
				driver.navigate().to(driver.getCurrentUrl());
				if(!elementVisible(driver,By.cssSelector("h2.fs-7.px-4.c-neutral-900.fw-600"), 5)) {
					textPresent_Log(driver, "Review your itinerary", 30);					
				}
				System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
				Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
		if(!textPresent(driver, "Review your itinerary", 1)) {
	     if (textPresent(driver, "Sorry, our servers are stumped with your request", 5)|| textPresent(driver, "Flight not available", 1)) 
	     {
					System.out.println("Booking failed due to itn page issue");
					Reporter.log("Booking failed due to itn page issue");
					assertTrue(false);
	     } 
		}
      }
    }
 }

	public void book_it(RemoteWebDriver driver, String gv_coupon) {
		Reporter.log("Itinerary page loaded");

	}

	public void book_itnnew(RemoteWebDriver driver, String gv_coupon) throws Exception {
		if(!textPresent(driver, "Review your itinerary", 1))  {
		if(elementVisible(driver,By.xpath("//div[5]/button"), 2))
		{
			safeClick(driver,By.xpath("//div[5]/button"));
			Thread.sleep(1000);
		}
		}
		Reporter.log("Itinerary page loaded");
		if (elementVisible(driver, getObjectPayment("Bento_Itn_Standard_Fee"), 3)) {
			safeClick(driver, getObjectPayment("Bento_Itn_Standard_Fee"));
			Reporter.log("Selected itn fee");
			if (gv_coupon == "GV") {
				Thread.sleep(1000);
				WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
				ele.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(2000);
				ele.sendKeys(GV_number);
				Reporter.log("Entered GV number");
				Thread.sleep(1000);
				// smartClick(driver,getObjectPayment("Bento_Itn_GV_Pin"));
				safeType(driver, getObjectPayment("Bento_Itn_GV_Pin"), GV_pin);
				Reporter.log("Entered GV pin");
				safeClick(driver, getObjectPayment("Bento_Itn_GV_Apply"));
				textPresent_Log(driver, "has been redeemed for this booking", 3);
				Reporter.log("GV applied Successfully");
			} else if (gv_coupon == "Coupon") {
				Thread.sleep(1000);
				WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
				ele.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(1000);
				ele.sendKeys("WALLET3");
				Thread.sleep(1000);
				Reporter.log("Entered Coupon details");
				smartClick(driver, getObjectPayment("Bento_Itn_Coupon_Apply"));
				if (textPresent_Log(driver, "Great! You just saved", 3) || textPresent_Log(driver,"Great! We'll add ₹179 in your Cleartrip Wallet",3))
				{	
					Reporter.log("Successfully applied coupon");
				}
			}
			elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue"), 2);
			/*WebElement ele2 = driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue"));
			ele2.sendKeys(Keys.ARROW_DOWN);
			ele2.sendKeys(Keys.ARROW_DOWN);*/
			scrollToElement(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
			elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue"), 2);
			safeClick(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
			Reporter.log("Clicked on fare continue");
		}
		else if(elementVisible(driver,getObjectPayment("Bento_Itn_fare_continue"),5))
		{
			if (gv_coupon == "GV") {
				elementVisible(driver, getObjectPayment("Bento_Itn_GV_Number"), 2);
				WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
				ele.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(2000);
				ele.sendKeys(GV_number);
				Reporter.log("Entered GV number");
				elementVisible(driver, getObjectPayment("Bento_Itn_GV_Pin"), 2);
				safeType(driver, getObjectPayment("Bento_Itn_GV_Pin"), GV_pin);
				Reporter.log("Entered GV pin");
				elementVisible(driver, getObjectPayment("Bento_Itn_GV_Apply"), 2);
				safeClick(driver, getObjectPayment("Bento_Itn_GV_Apply"));
				textPresent_Log(driver, "has been redeemed for this booking", 3);
				Reporter.log("GV applied Successfully");
			} else if (gv_coupon == "Coupon") {
				elementVisible(driver, getObjectPayment("Bento_Itn_GV_Number"), 2);
				WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
				ele.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(1000);
				ele.sendKeys("WALLET3");
				Reporter.log("Entered Coupon details");
				elementVisible(driver, getObjectPayment("Bento_Itn_Coupon_Apply"), 2);
				smartClick(driver, getObjectPayment("Bento_Itn_Coupon_Apply"));
			if (textPresent_Log(driver, "Great! You just saved", 3) || textPresent_Log(driver,"Great! We'll add ₹179 in your Cleartrip Wallet",3)) 
				{
					Reporter.log("Successfully applied coupon");
				}
			}
			elementVisible(driver, getObjectPayment("Bento_Itn_fare_continue"), 2);
		/*	WebElement ele2 = driver.findElement(getObjectPayment("Bento_Itn_fare_continue"));
			ele2.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
*/
			scrollToElement(driver, getObjectPayment("Bento_Itn_fare_continue"));
			smartClick(driver, getObjectPayment("Bento_Itn_fare_continue"));
			Reporter.log("Clicked on fare continue");
		}
		else if(elementVisible(driver,getObjectPayment("Bento_Itn_Standard_Fee1"),5))
		{ 
		  safeClick(driver,getObjectPayment("Bento_Itn_Standard_Fee1"));
		  Reporter.log("Selected itn fee"); 
		  if(gv_coupon=="GV") {
		  elementVisible(driver, getObjectPayment("Bento_Itn_GV_Number"), 2);
		  WebElement ele3 =driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
		  ele3.sendKeys(Keys.PAGE_DOWN); 
		  Thread.sleep(2000); 
		  ele3.sendKeys(GV_number);
		  Reporter.log("Entered GV number"); 
		  Thread.sleep(1000);
		  //smartClick(driver,getObjectPayment("Bento_Itn_GV_Pin"));
		  elementVisible(driver, getObjectPayment("Bento_Itn_GV_Pin"), 2);
		  safeType(driver,getObjectPayment("Bento_Itn_GV_Pin"),GV_pin);
		  Reporter.log("Entered GV pin");
		  safeClick(driver,getObjectPayment("Bento_Itn_GV_Apply"));
		  textPresent_Log(driver,"has been redeemed for this booking",3);
		  Reporter.log("GV applied Successfully"); 
		  } 
		  else if(gv_coupon=="Coupon") {
		  WebElement ele4 =driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
		  ele4.sendKeys(Keys.PAGE_DOWN); 
		  Thread.sleep(1000); 
		  ele4.sendKeys("WALLET3");
		  Thread.sleep(1000); 
		  Reporter.log("Entered Coupon details");
		  smartClick(driver,getObjectPayment("Bento_Itn_Coupon_Apply"));
		  Thread.sleep(2000);
		  if(textPresent_Log(driver,"Great! You just saved",3)||textPresent_Log(driver,"Great! You just saved 184 on your booking. Discount will be applied on the payments page.",3)) 
		  { 
		    Reporter.log("Successfully applied coupon"); 
		  } 
		 }
		  if(elementVisible(driver,getObjectPayment("Bento_Itn_Fare1_Continue"),2)) 
		 {
		  WebElement ele2=driver.findElement(getObjectPayment("Bento_Itn_Fare1_Continue"));
		  Thread.sleep(2000); 
		  ele2.sendKeys(Keys.ARROW_DOWN); 
		  Thread.sleep(3000);
		  driver.executeScript("return arguments[0].scrollIntoView();", ele2);
		  elementVisible(driver, getObjectPayment("Bento_Itn_Fare1_Continue"), 2);		  
		  smartClick(driver,getObjectPayment("Bento_Itn_Fare1_Continue"));
		  Reporter.log("Clicked on fare continue"); 
		  Thread.sleep(3000); 
		  }
		  else 
		  {
		  WebElement ele2=driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue"));
		  Thread.sleep(2000); 
		  ele2.sendKeys(Keys.ARROW_DOWN); 
		  Thread.sleep(3000);
		  driver.executeScript("return arguments[0].scrollIntoView();", ele2);
		  Thread.sleep(2000);
		  ele2.click(); 
		  Reporter.log("Clicked on fare continue"); 
		  }
		  
		  if(elementVisible(driver,getObjectPayment("Bento_Itn_Meal_Continue"),5)) 
		  {
		   WebElement ele4=driver.findElement(getObjectPayment("Bento_Itn_Meal_Continue"));
		   Thread.sleep(2000);
		   ele4.sendKeys(Keys.ARROW_DOWN);
		   Thread.sleep(3000);
		   driver.executeScript("return arguments[0].scrollIntoView();", ele4);
		   Thread.sleep(2000);
		   ele4.click();
		   Reporter.log("Clicked on meal continue"); 
		   Thread.sleep(2000);
		  }
		  
		  
		  }
		else
		{ 
		  Thread.sleep(1000);  
		  if(gv_coupon=="GV") {
		  WebElement ele3 =driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
		  ele3.sendKeys(Keys.PAGE_DOWN); 
		  Thread.sleep(2000); 
		  ele3.sendKeys(GV_number);
		  Reporter.log("Entered GV number"); 
		  elementVisible(driver, getObjectPayment("Bento_Itn_GV_Pin"), 2);	
		  safeType(driver,getObjectPayment("Bento_Itn_GV_Pin"),GV_pin);
		  Reporter.log("Entered GV pin");
		  safeClick(driver,getObjectPayment("Bento_Itn_GV_Apply"));
		  textPresent_Log(driver,"has been redeemed for this booking",3);
		  Reporter.log("GV applied Successfully"); 
		  } 
		  else if(gv_coupon=="Coupon") {
		  WebElement ele4 =driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
		  ele4.sendKeys(Keys.PAGE_DOWN); 
		  Thread.sleep(1000); 
		  ele4.sendKeys("WALLET3");
		  Thread.sleep(1000); 
		  Reporter.log("Entered Coupon details");
		  smartClick(driver,getObjectPayment("Bento_Itn_Coupon_Apply"));
		  if(textPresent_Log(driver,"Great! You just saved",3)||textPresent_Log(driver,"Great! You just saved 184 on your booking. Discount will be applied on the payments page.",3)) 
		  { 
		    Reporter.log("Successfully applied coupon"); 
		  } 
		 }
		  if(elementVisible(driver,getObjectPayment("Bento_Itn_Fare2_Continue"),2)) 
		 {
		  WebElement ele2=driver.findElement(getObjectPayment("Bento_Itn_Fare2_Continue"));
		  Thread.sleep(2000); 
		  ele2.sendKeys(Keys.ARROW_DOWN); 
		  Thread.sleep(3000);
		  driver.executeScript("return arguments[0].scrollIntoView();", ele2);
		  Thread.sleep(1000);
		  smartClick(driver,getObjectPayment("Bento_Itn_Fare2_Continue"));
		  Reporter.log("Clicked on fare continue"); 
		  }
		  else 
		  {
		  elementVisible(driver, getObjectPayment("Bento_Itn_Fare_Continue"), 2);	
		  WebElement ele2=driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue"));
		  Thread.sleep(2000); 
		  ele2.sendKeys(Keys.ARROW_DOWN); 
		  Thread.sleep(3000);
		  driver.executeScript("return arguments[0].scrollIntoView();", ele2);
		  Thread.sleep(2000);
		  ele2.click(); 
		  Reporter.log("Clicked on fare continue"); 
		  Thread.sleep(2000); 
		  }
		  
		  if(elementVisible(driver,getObjectPayment("Bento_Itn_Meal_Continue"),5)) 
		  {
		   elementVisible(driver, getObjectPayment("Bento_Itn_Meal_Continue"), 2);	
		   WebElement ele4=driver.findElement(getObjectPayment("Bento_Itn_Meal_Continue"));
		   Thread.sleep(2000);
		   ele4.sendKeys(Keys.ARROW_DOWN);
		   Thread.sleep(3000);
		   driver.executeScript("return arguments[0].scrollIntoView();", ele4);
		   Thread.sleep(2000);
		   ele4.click();
		   Reporter.log("Clicked on meal continue"); 
		  }
		  
		  
		  }
		 
		if (elementVisible(driver, getObjectPayment("Bento_Itn_Add_On_Skip"), 5)) {

			driver.findElement(getObjectPayment("Bento_Itn_Add_On_Skip")).sendKeys(Keys.PAGE_DOWN);
			Thread.sleep(2000);
			safeClick(driver, getObjectPayment("Bento_Itn_Add_On_Skip"));
			Reporter.log("Clicked on skip addons");
		}
		if(elementVisible(driver,getObjectPayment("Bento_Itn_Contact_Number1"),5))
		{
			safeClick(driver, getObjectPayment("Bento_Itn_Contact_Number1"));
			safeType(driver, getObjectPayment("Bento_Itn_Contact_Number1"), "1234567890");
			Reporter.log("Entered mobile number");
		}
		else
		{
		elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number"), 5);
		safeClick(driver, getObjectPayment("Bento_Itn_Contact_Number"));
		safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "1234567890");
		Reporter.log("Entered mobile number");
		}
		safeClick(driver, getObjectPayment("Bento_Itn_Username"));
		safeType(driver, getObjectPayment("Bento_Itn_Username"), username);
		Reporter.log("Entered user name");
		safeClick(driver, getObjectPayment("Bento_Itn_Contactinfo_Continue"));
		Reporter.log("Clicked on continue");
		safeClick(driver, getObjectPayment("Bento_Itn_User_Firstname"));
		safeType(driver, getObjectPayment("Bento_Itn_User_Firstname"), "Tester");
		Reporter.log("Entered first name");
		if(elementVisible(driver,getObjectPayment("Bento_Itn_User_Lastname"),2))
		{
			safeClick(driver,getObjectPayment("Bento_Itn_User_Lastname"));
			safeType(driver,getObjectPayment("Bento_Itn_User_Lastname"),"Test");
			Reporter.log("Entered last name");
		}
		else
		{
		 safeClick(driver, getObjectPayment("Bento_Itn_User_lastname"));
		 safeType(driver, getObjectPayment("Bento_Itn_User_lastname"), "Test");
		 Reporter.log("Entered last name");
		}
		if(elementVisible(driver, getObjectPayment("Bento_Itn_Select_gender"),1))
		{
			safeClick(driver, getObjectPayment("Bento_Itn_Select_gender"));
			safeClick(driver, getObjectPayment("Bento_Itn_Select_Female"));
			Reporter.log("Selected gender");
		}
		else
		{
		 safeClick(driver, getObjectPayment("Bento_Itn_Select_Gender"));
		 safeClick(driver, getObjectPayment("Bento_Itn_Select_Female"));
		 Reporter.log("Selected gender");
		}
		if(textPresent(driver, "Nationality", 2)||elementVisible(driver, getObjectPayment("Bento_Itn_Nationality"), 2)) {
			safeClick(driver, getObjectPayment("Bento_Itn_Nationality"));
			Thread.sleep(2000);/*
			safeType(driver, getObjectPayment("Bento_Itn_Nationality"), "india");
			safeClick(driver, getObjectPayment("Bento_Itin_Select_India"));*/
			mouseHover(driver, getObjectPayment("Bento_Itin_Select_India"));
			safeClick(driver, getObjectPayment("Bento_Itin_Select_India"));
			Reporter.log("Selected nationality");
		}
		Thread.sleep(2000);
		mouseHover(driver, getObjectPayment("Bento_Itn_Continue_Booking"));		
		//safeClick(driver, getObjectPayment("Bento_Itn_Continue_Booking"));
		driver.findElement(getObjectPayment("Bento_Itn_Continue_Booking")).click();
		Thread.sleep(2000);
		Reporter.log("Clicked on continue button to navigate to payments page");
	}

	public void noncom_itnpage(RemoteWebDriver driver, String gv_coupon, String domain) throws Exception {
		
		if(elementVisible(driver, By.cssSelector("h2.fs-7.px-4.c-neutral-900.fw-600"), 5)) {
		}
		else {
			if(elementVisible(driver,By.xpath("//div[5]/button"),1))
				{
					smartClick(driver,By.xpath("//div[5]/button"));
				}
		}
		Actions actions = new Actions(driver);
		if (domain == "ae" || domain == "sa") {
		/*	if (elementVisible(driver, getObjectPayment("Bento_aeitn_continue1"), 5)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_continue1"));
			} else*/ 
			smartClick(driver, getObjectPayment("Bento_aeitn_continue1"));
			
			if (elementVisible(driver, getObjectPayment("Bento_aeitn_removeinsurance"), 1)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_removeinsurance"));
				Thread.sleep(2000);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				Thread.sleep(1000);
				safeClick(driver, getObjectPayment("Bento_aeitn_continue"));
				Thread.sleep(3000);
			}
		} else {

			if (elementVisible(driver, getObjectPayment("Bento_aeitn_continue1"), 2)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_continue1"));
			} else {
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				Thread.sleep(1000);
				safeClick(driver, getObjectPayment("Bento_aeitn_continue"));
			}
		}

		if(elementVisible(driver,getObjectPayment("Bento_aeitn_skip"), 5))
		{
		Thread.sleep(1000);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		Thread.sleep(5000);
		smartClick(driver, getObjectPayment("Bento_aeitn_skip"));
		}
		elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number"), 10);
		safeClick(driver, getObjectPayment("Bento_Itn_Contact_Number"));
		safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "1");
		if (domain == "ae" || domain == "sa") {
			if (domain == "ae") {
				safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "12345678");
			} else if (domain == "sa") {
				safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "123456789");
			}
			Reporter.log("Entered mobile number");
			safeClick(driver, getObjectPayment("Bento_Itn_Username"));
			safeType(driver, getObjectPayment("Bento_Itn_Username"), username);
			Reporter.log("Entered user name");
			safeClick(driver, getObjectPayment("Bento_Itn_Contactinfo_Continue"));
			Reporter.log("Clicked on continue");
			safeClick(driver, getObjectPayment("Bento_aeitn_firstname"));
			safeType(driver, getObjectPayment("Bento_aeitn_firstname"), "Tester");
			if(elementVisible(driver,getObjectPayment("Bento_aeitn_Lastname"),2))
			{
				safeClick(driver,getObjectPayment("Bento_aeitn_Lastname"));
				safeType(driver,getObjectPayment("Bento_aeitn_Lastname"),"Test");
				Reporter.log("Entered last name");
			}
			else
			{
			   safeClick(driver, getObjectPayment("Bento_aeitn_lastname"));
			   safeType(driver, getObjectPayment("Bento_aeitn_lastname"), "Test");
			   Reporter.log("Entered last name");
			}
			
			if(elementVisible(driver, getObjectPayment("Bento_aeitn_select_Gender"),1))
			{
				safeClick(driver, getObjectPayment("Bento_aeitn_select_Gender"));
				safeClick(driver, getObjectPayment("Bento_ae_itn_select_Female"));
				Reporter.log("Selected gender");
			}
			else
			{
				safeClick(driver, getObjectPayment("Bento_aeitn_select_gender"));
				safeClick(driver, getObjectPayment("Bento_ae_itn_select_female"));
			 Reporter.log("Selected gender");
			}
			if (elementVisible(driver, getObjectPayment("Bento_ae_nationality"), 2)) {
				safeClick(driver, getObjectPayment("Bento_ae_nationality"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_ae_type_nationality"), "India");
				safeClick(driver, getObjectPayment("Bento_ae_select_india"));
			}
			if (elementVisible(driver, getObjectPayment("Bento_ae_Continuetopayments1"), 2)) {
				safeClick(driver, getObjectPayment("Bento_ae_Continuetopayments1"));
			} else if (elementVisible(driver, getObjectPayment("Bento_ae_Continuetopayments"), 2)) {
				safeClick(driver, getObjectPayment("Bento_ae_Continuetopayments"));
			}
		} else {

			safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "1234567890");
			Reporter.log("Entered mobile number");
			safeClick(driver, getObjectPayment("Bento_Itn_Username"));
			safeType(driver, getObjectPayment("Bento_Itn_Username"), username);
			Reporter.log("Entered user name");
			safeClick(driver, getObjectPayment("Bento_Itn_Contactinfo_Continue"));
			Reporter.log("Clicked on continue");
			safeClick(driver, getObjectPayment("Bento_aeitn_Firstname"));
			safeType(driver, getObjectPayment("Bento_aeitn_Firstname"), "Tester");
			if(elementVisible(driver,getObjectPayment("Bento_aeitn_lastname"),2))
			{
				safeClick(driver,getObjectPayment("Bento_aeitn_lastname"));
				safeType(driver,getObjectPayment("Bento_aeitn_lastname"),"Test");
				Reporter.log("Entered last name");
			}
			else
			{
			   safeClick(driver, getObjectPayment("Bento_aeitn_Lastname"));
			   safeType(driver, getObjectPayment("Bento_aeitn_Lastname"), "Test");
			   Reporter.log("Entered last name");
			}
			if(elementVisible(driver,By.xpath("//div[3]/div/input"),1))
			{
				safeClick(driver,By.xpath("//div[3]/div/input"));
				safeType(driver,By.xpath("//div[3]/div/input"),"Test");
				Reporter.log("Entered last name");
			}
			if(elementVisible(driver, getObjectPayment("Bento_aeitn_select_Gender"),2))
			{
				safeClick(driver, getObjectPayment("Bento_aeitn_select_Gender"));
				safeClick(driver, getObjectPayment("Bento_ae_itn_select_Female"));
				Reporter.log("Selected gender");
			}
			else
			{
				safeClick(driver, getObjectPayment("Bento_aeitn_select_gender"));
				safeClick(driver, getObjectPayment("Bento_ae_itn_select_female"));
			 Reporter.log("Selected gender");
			}
			
			
			if (elementVisible(driver, getObjectPayment("Bento_ae_nationality"), 1)) {
				safeClick(driver, getObjectPayment("Bento_ae_nationality"));
				safeType(driver, getObjectPayment("Bento_ae_type_nationality"), "India");
				safeClick(driver, getObjectPayment("Bento_ae_select_india"));
			}
			if (elementVisible(driver, getObjectPayment("Bento_ae_Continuetopayments1"), 1)) {
				safeClick(driver, getObjectPayment("Bento_ae_Continuetopayments1"));
			} else if (elementVisible(driver, getObjectPayment("Bento_ae_continuetopayment"), 1)) {
				safeClick(driver, getObjectPayment("Bento_ae_continuetopayment"));
			}
		}

		System.out.println("Clicked on continue button to navigate to payments page");
		Reporter.log("Clicked on continue button to navigate to payments page");

	}

	public void paymentPage(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType,String BankName) throws Exception {
		if(elementVisible(driver, getObjectPayment("Bento_Pay_PayToCompleteBooking_Txt"), 30))
			{
			 if(CardNumber=="ADCB"||PaymentType=="Phonepe"||PaymentType=="UPIScan")
			 {
				bento_paymentpage(driver,PaymentType, CardNumber,domain,PayType,BankName);
			 }
			else
			{
				bento_paymentpage(driver,PaymentType, CardNumber,domain,PayType,BankName);
				confirmation_page(driver, PaymentType, CardNumber);
			}
			}
		else if(textPresent(driver,"Sorry, our servers are stumped with your request",1)||textPresent(driver,"Flight not available",1))
			{
			System.out.println("Booking failed due to itn page issue");
			Reporter.log("Booking failed due to itn page issue");
			assertTrue(false);
			}
		else 
			{
			Reporter.log("Booking failed due to itn page issue");
			assertTrue(false);
			}
	}
		
	
	/**
	 * @param driver
	 * @param PaymentType
	 * @param CardNumber
	 * @param domain
	 * @param PayType
	 * @param BankName
	 * @throws Exception
	 */
	public void bento_paymentpage(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain,String PayType, String BankName) throws Exception {
		textPresent_Log(driver, "Pay to complete your booking", 5);
		System.out.println(driver.getCurrentUrl());
		Reporter.log(driver.getCurrentUrl());
         if (PaymentType == "storedcard") {
			if (textPresent(driver, "Cleartrip wallet", 3)) 
			{
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(1000);
				if(CardNumber == "4111")
				{
					safeClick(driver, getObjectPayment("Bento_Payment_Select_DC_Storedcard"));
					Reporter.log("Clicked on SC");
					Thread.sleep(1000);
					safeClick(driver, getObjectPayment("Bento_Payment_SC_DC_CVV"));
					safeType(driver, getObjectPayment("Bento_Payment_SC_DC_CVV"), "111");
					Reporter.log("Entered CVV");
					safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
					Reporter.log("Clicked on paynow");
					textPresent_Log(driver, "Please wait...", 5);
					textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 5);
					safeClick(driver, getObjectPayment("Bento_Payment_DC_Payment_Success"));
					textPresent_Log(driver, "Your booking is done", 5);
					Reporter.log("Payment done successfully");
				}
				else if(CardNumber=="5241")
				{
					safeClick(driver, getObjectPayment("Bento_Payment_SC_Razropay"));
						Reporter.log("Clicked on SC");
						Thread.sleep(1000);
						safeClick(driver, getObjectPayment("Bento_Payment_SC_Razropay_CVV"));
						safeType(driver, getObjectPayment("Bento_Payment_SC_Razropay_CVV"), "123");
						Reporter.log("Entered CVV");
						safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
						Reporter.log("Clicked on paynow");
						textPresent_Log(driver, "Please wait...", 5);
						textPresent_Log(driver, "Enter OTP", 10);
						safeClick(driver, getObjectPayment("Bento_Payment_Razropay_Pin"));
						safeType(driver,getObjectPayment("Bento_Payment_Razropay_Pin"),"0000");
						safeClick(driver,getObjectPayment("Bento_Payment_Razropay_Submit"));
						textPresent_Log(driver, "Your booking is done", 5);
						Reporter.log("Payment done successfully");
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
				textPresent_Log(driver, "Please wait...", 2);
				textPresent_Log(driver, "American", 2);
				textPresent_Log(driver, "ACS Emulator", 2);
				safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
				textPresent_Log(driver, "Your booking is done", 5);
				Reporter.log("Payment done successfully");
				}
			} 
         }
			
			if (PaymentType == "CC")	
				{
				smartClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				if(CardNumber=="4111") {	

					payUI_Enter_PaymentDetails(driver, "CC", "RAZORPAYDC","");
					/*
					safeClick(driver, getObjectPayment("Bento_Payment_Select_DC_Storedcard"));
					Reporter.log("Clicked on SC");
					Thread.sleep(1000);
					safeClick(driver, getObjectPayment("Bento_Payment_SC_DC_CVV"));
					safeType(driver, getObjectPayment("Bento_Payment_SC_DC_CVV"), "111");
					Reporter.log("Entered CVV");*/
					safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
					Reporter.log("Clicked on paynow");
					textPresent(driver, "Please wait...", 2);
					textPresent(driver, "Welcome to Razorpay Software Private Ltd Bank", 2);
					elementVisible(driver, getObjectPayment("Bento_Payment_DC_Payment_Success"), 10);
					safeClick(driver, getObjectPayment("Bento_Payment_DC_Payment_Success"));
					textPresent_Log(driver, "Your booking is done",10);
					Reporter.log("Payment done successfully");
				}
				else if(CardNumber=="5241")
				{
					/*safeClick(driver, getObjectPayment("Bento_Payment_SC_Razropay"));
						Reporter.log("Clicked on SC");
						Thread.sleep(1000);
						safeClick(driver, getObjectPayment("Bento_Payment_SC_Razropay_CVV"));
						safeType(driver, getObjectPayment("Bento_Payment_SC_Razropay_CVV"), "123");
						Reporter.log("Entered CVV");*/
					

						payUI_Enter_PaymentDetails(driver, "CC", "RAZORPAY","");
						safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
						Reporter.log("Clicked on paynow");
						textPresent(driver, "Please wait...", 5);
						textPresent(driver, "One Time Password (OTP) successfully sent to the phone number linked to your card ending with 0000.", 5);
						safeClick(driver, getObjectPayment("Bento_Payment_Razropay_Pin"));
						safeType(driver,getObjectPayment("Bento_Payment_Razropay_Pin"),"0000");
						safeClick(driver,getObjectPayment("Bento_Payment_Razropay_Submit"));
						textPresent_Log(driver, "Your booking is done", 10);
						Reporter.log("Payment done successfully");
				}
				else if(CardNumber=="3456")
				{				
						payUI_Enter_PaymentDetails(driver, "CC", "AMEX","");
						safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
						Reporter.log("Clicked on paynow");						
						elementPresent_log(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"), "Amex Bank ", 20);
						textPresent(driver, "ACS Emulator", 1);
						Reporter.log("Amex Auth page is displayed");
						safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Amex3DPage_Submit_Btn"));
						textPresent_Log(driver, "Your booking is done", 30);
						Reporter.log("Payment done successfully");
				}
				
				else
				{
				safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
				safeClick(driver, getObjectPayment("Bento_Payment_SC_CVV"));
				safeType(driver, getObjectPayment("Bento_Payment_SC_CVV"), "1234");
				Reporter.log("Entered CVV");
				safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
				Reporter.log("Clicked on paynow");
				textPresent_Log(driver, "Please wait...", 2);
				textPresent_Log(driver, "American", 2);
				textPresent_Log(driver, "ACS Emulator", 2);
				safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
				textPresent_Log(driver, "Your booking is done", 5);
				Reporter.log("Payment done successfully");
				}	
				}
			
		if (PaymentType == "EMI")	{
				safeClick(driver, getObjectPayment("PaymentPage_EMI_ICICIBank_Radio_Btn"));
				elementVisible(driver, getObjectPayment("PaymentPage_EMI_EnterCard_Details_btn"), 10);
				scrollToElement(driver, getObjectPayment("PaymentPage_EMI_EnterCard_Details_btn"));
				safeClick(driver, getObjectPayment("PaymentPage_EMI_EnterCard_Details_btn"));
				textPresent_Log(driver, "Selected EMI option", 5);
				
				textPresent_Log(driver, "Interest (Charged by Bank)", 5);

				elementVisible(driver, getObjectPayment("PaymentPage_EMI_Change_Plan_Button"), 5);		
				textPresent_Log(driver, "Enter credit card details", 5);
				Enter_CC_Details(driver, platform.value("RazorPay_Number"), platform.value("RazorPay_Month_UI"), platform.value("RazorPay_Year"), platform.value("RazorPay_CVV"));
				safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
				Reporter.log("Clicked on paynow");
				
				textPresent(driver, "One Time Password (OTP) successfully sent to the phone number linked to your card ending with 0000.", 5);
				safeClick(driver, getObjectPayment("Bento_Payment_Razropay_Pin"));
				safeType(driver,getObjectPayment("Bento_Payment_Razropay_Pin"),"0000");
				safeClick(driver,getObjectPayment("Bento_Payment_Razropay_Submit"));
				textPresent_Log(driver, "Your booking is done", 10);
				Reporter.log("Payment done successfully");
			}
		
		if (PaymentType == "wallet") {
			if (textPresent(driver, "Cleartrip wallet", 2)) {
				elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
				textPresent_Log(driver, "Your wallet balance is sufficient to pay for this booking", 2);
				safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
				Reporter.log("Clicked on paynow");
				textPresent_Log(driver, "Please wait...", 2);
				Reporter.log("Payment done successfully");
				Thread.sleep(3000);
			}
		}
		if (PaymentType == "nb") {
			if (textPresent(driver, "Cleartrip wallet", 2)) {
				
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(1000);

				bento_Select_PaymentType(driver, "NB");
				
				//safeClick(driver, getObjectPayment("Bento_Payment_NB"));
				Reporter.log("Clicked on NB");
			} else {
				Thread.sleep(1000);
				safeClick(driver, getObjectPayment("Bento_Payment_NB"));
				Reporter.log("Clicked on NB");
			}
			Thread.sleep(1000);
			safeClick(driver, getObjectPayment("Bento_Payment_NB_ICIC"));
			Reporter.log("Selected ICIC Bank");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			textPresent_Log(driver, "Please wait...", 2);
			textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 5);
			safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));
			Reporter.log("Payment done successfully");

		}
		if (PaymentType == "Phonepe") {
			if (textPresent(driver, "Cleartrip wallet", 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(2000);
				safeClick(driver, getObjectPayment("Bento_Payment_Wallet"));
				Reporter.log("Clicked on Wallet");
			} else {
				safeClick(driver, getObjectPayment("Bento_Payment_Wallet"));
				Reporter.log("Clicked on Wallet");
			}
			safeClick(driver, getObjectPayment("Bento_Payment_Wallet_Phonepe"));
			Reporter.log("Selected Phonepe");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			textPresent_Log(driver, "Please wait...", 5);
			textPresent_Log(driver, "Login to PhonePe", 5);
			//textPresent_Log(driver, "SEND OTP TO LOGIN", 5);
			textPresent(driver, "Scan&Pay via PhonePe App", 2);
			textPresent(driver, "PhonePe QR", 2);	
			Reporter.log("PhonePe Page Validated");
			System.out.println("PhonePe Page Validated");
		}
		if (PaymentType == "UPIScan") {
			if (textPresent(driver, "Cleartrip wallet", 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(1000);
				smartClick(driver, getObjectPayment("Bento_Payment_UPI"));
				Reporter.log("Clicked on UPI");
				Thread.sleep(2000);
			} else {
				safeClick(driver, getObjectPayment("Bento_Payment_UPI"));
				Reporter.log("Clicked on UPI");
				Thread.sleep(2000);
			}
			String Price = getText(driver, getObjectPayment("Bento_Pay_Total_Value"));
			System.out.println("PRICE "+Price);
			textPresent_Log(driver, "SCAN QR CODE", 5);
			elementPresent_log(driver, getObjectPayment("Bento_Payment_Paynow"), "Show QR Code", 5);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_QRCode"), "QR Image in Payment Page", 5);	
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));  //Click Show QR Code Button
			textPresent_Log(driver, "Powered by", 10);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_PoweredBy_Text"), "Powered by", 1);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_RazorPay_Image"), "Razorpay Image", 1);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Price"), "Price", 1);
			String QR_Price = getText(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Price"));
			if(!QR_Price.equals(Price)) {
				Reporter.log("Price in Payment page "+Price+" Price in QR page "+QR_Price);
				assertTrue(false);
			}
			String QRPage_URL = getURL(driver);
			Reporter.log(QRPage_URL);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Do_Not_Refresh_Text"), "Do not refresh text", 1);
			textPresent_Log(driver, "Scan here to pay with any UPI app", 1);
			textPresent_Log(driver, "Do not refresh this page", 1);
			textPresent_Log(driver, "while we check your payment status", 1);
			textPresent_Log(driver, "Cancel payment", 1);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_QRCODE_Image"), "QR Code image", 1);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_Gpay_Image"), "GPay Image", 1);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_PhonePe_Image"), "GPay Image", 1);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_PayTM_Image"), "PayTM Image", 1);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_CleartripLogo"), "Cleartrip logo", 1);
			elementPresent_log(driver, getObjectPayment("Bento_Pay_UPIScan_Page_CancelPay_Link"), "Cancel Payment link", 1);
			safeClick(driver, getObjectPayment("Bento_Pay_UPIScan_Page_CancelPay_Link"));
			textPresent_Log(driver, "Payment cancelled! If you have already paid, please wait for a few minutes before trying again", 10);
			
		}
		if (PaymentType == "UPI") {
			if (textPresent(driver, "Cleartrip wallet", 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(1000);
				smartClick(driver, getObjectPayment("Bento_Payment_UPI"));
				Reporter.log("Clicked on UPI");
				Thread.sleep(2000);
			} else {
				safeClick(driver, getObjectPayment("Bento_Payment_UPI"));
				Reporter.log("Clicked on UPI");
				Thread.sleep(2000);
			}
			safeClick(driver, getObjectPayment("Bento_Payment_UPI_ID"));
			safeType(driver, getObjectPayment("Bento_Payment_UPI_ID"), "9986696785@ybl");
			Reporter.log("Entered UPI Details");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow_UPI"));
			System.out.println("---------------------");
			Reporter.log("Clicked on paynow");
			textPresent(driver, "Please wait...", 2);
			textPresent(driver, "Please accept the collect request sent to your UPI app", 5);
			Reporter.log("Payment done successfully");
		}
		if (PaymentType == "GV") {
			elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
			textPresent_Log(driver, "Gift card", 2);
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			textPresent_Log(driver, "Please wait...", 2);
			Reporter.log("Payment done successfully");
		}
		if (PaymentType == "KNET") {

			bento_Select_PaymentType(driver, "KNET");
			elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_DropDown"), 30); 
			Reporter.log("KNet Bank Page displayed");
			textPresent_Log(driver, "Cleartrip Mea Fz Llc", 2);
			safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_DropDown"), "Knet Test Card [KNET1]");
			Thread.sleep(5000);
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"), 10);
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"));
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CardNumber"), "0000000001");
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Month"));
			safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Month"), "09");

			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Year"));
			safeSelect(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Exp_Year"), "2025");
			safeType(driver, getObjectPayment("MakePayment_NB_Bank_Knet_CVV"), "1234");
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Proceed"), 10);
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Proceed"));
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Confirm"), 10);
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_Confirm"));
			elementVisible(driver, getObjectPayment("MakePayment_NB_Bank_Knet_RedirectionPage"), 10);
			safeClick(driver, getObjectPayment("MakePayment_NB_Bank_Knet_RedirectionPage"));
			
		}
		if (PaymentType == "partial_wallet") {
			elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
			safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard1"));
			Reporter.log("Clicked on SC");
			safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
			Reporter.log("Clicked on SC");
			safeClick(driver, getObjectPayment("Bento_sc_amex_cvv"));
			Reporter.log("Clicked on SC");
			safeClick(driver, getObjectPayment("Bento_sc_amex_cvv"));
			safeType(driver, getObjectPayment("Bento_sc_amex_cvv"), "0000");
			Reporter.log("Entered CVV");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			textPresent_Log(driver, "Please wait...", 5);
			textPresent_Log(driver, "American", 5);
			textPresent_Log(driver, "ACS Emulator", 10);
			safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
			textPresent_Log(driver, "Your booking is done", 5);
			Reporter.log("Payment done successfully");
		}
		if (PaymentType == "Coupon") {
			elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
			textPresent_Log(driver, "Coupon code (WALLET3)", 2);
			if (textPresent(driver, "Cleartrip wallet", 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				smartClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
				Reporter.log("Clicked on SC");
			} else {
				smartClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
				Reporter.log("Clicked on SC");
			}
		/*	safeClick(driver, getObjectPayment("Bento_select_sc"));
			safeClick(driver, getObjectPayment("Bento_sc_cvv"));
			safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
		*/
			payUI_Enter_PaymentDetails(driver, "CC", "MASTER","");
			safeClick(driver, getObjectPayment("Bento_paynow"));
			elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
			  safeClick(driver, getObjectPayment("Bento_card_password"));
			  Thread.sleep(1000); 
			  safeType(driver, getObjectPayment("Bento_card_password"),"123456"); 
			  safeClick(driver, getObjectPayment("Bento_submit"));
		}
		if (PaymentType == "AE-SC") {
			if (elementVisible(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"), 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(2000);
				if(CardNumber=="4000")
				{
					safeClick(driver, getObjectPayment("Bento_sc_noon"));
					safeClick(driver, getObjectPayment("Bento_sc_noon_cvv"));
					safeType(driver, getObjectPayment("Bento_sc_noon_cvv"), "123");
					safeClick(driver, getObjectPayment("Bento_paynow"));
					elementVisible(driver, getObjectPayment("Bento_sc_noon_password"), 10);
					driver.switchTo().frame(0);
					Thread.sleep(1000);
					smartClick(driver, getObjectPayment("Bento_sc_noon_password"));
					Thread.sleep(1000);
					smartType(driver, getObjectPayment("Bento_sc_noon_password"), "1234");
					Thread.sleep(2000);
					safeClick(driver, getObjectPayment("Bento_sc_noon_password_submit"));
					textPresent_Log(driver, "Your booking is done", 5);
					Reporter.log("Payment done successfully");
				}
				else if(CardNumber=="4557")
				{
					safeClick(driver, getObjectPayment("Bento_sc_noon"));
					safeClick(driver, getObjectPayment("Bento_sc_payfort_cvv"));
					safeType(driver, getObjectPayment("Bento_sc_payfort_cvv"), "123");
					safeClick(driver, getObjectPayment("Bento_paynow"));
					elementVisible(driver, getObjectPayment("Bento_sc_payfort_password"), 5);
					safeClick(driver, getObjectPayment("Bento_sc_payfort_password"));
					Thread.sleep(1000);
					safeType(driver, getObjectPayment("Bento_sc_payfort_password"), "12345");
					safeClick(driver, getObjectPayment("Bento_sc_payfort_submit"));
					textPresent_Log(driver, "Your booking is done", 10);
					Reporter.log("Payment done successfully");
				}
				else if(CardNumber=="ADCB")
				{
				  safeClick(driver,getObjectPayment("Bento_ae_adcb_select"));
				  Reporter.log("Clicked on ADCB");
				  System.out.println("Clicked on ADCB");
				  Thread.sleep(1000);
				  safeClick(driver,getObjectPayment("Bento_ae_adcb_cardnumber"));
				  safeType(driver,getObjectPayment("Bento_ae_adcb_cardnumber"),"5264083966400083");
				  Reporter.log("Entered on ADCB card");
				  System.out.println("Entered on ADCB card");
				  safeClick(driver,getObjectPayment("Bento_ae_adcb_expirymonth"));
				  Thread.sleep(1000);
				  safeSelectByIndex(driver,getObjectPayment("Bento_ae_adcb_expirymonth"),6);
				  Reporter.log("Selected on ADCB card month");
				  System.out.println("Selected on ADCB card month");
				  safeClick(driver,getObjectPayment("Bento_ae_adcb_expiryyear"));
				  Thread.sleep(1000);
				  safeSelectByIndex(driver,getObjectPayment("Bento_ae_adcb_expiryyear"),4);
				  Reporter.log("Selected on ADCB card year");
				  System.out.println("Selected on ADCB card year");
				  safeClick(driver,getObjectPayment("Bento_ae_adcb_name"));
				  safeType(driver,getObjectPayment("Bento_ae_adcb_name"),"test");
				  Reporter.log("Entered on ADCB card name");
				  System.out.println("Entered on ADCB card name");
				  safeClick(driver,getObjectPayment("Bento_ae_adcb_cvv"));
				  safeType(driver,getObjectPayment("Bento_ae_adcb_cvv"),"123");
				  Reporter.log("Entered on ADCB card cvv");
				  System.out.println("Entered on ADCB card cvv");
				  safeClick(driver,getObjectPayment("Bento_ae_adcb_checkbalance"));
				  textPresent_Log(driver,"You will redeem AED",5);
				  Reporter.log("Verified ADCB balance");
				  System.out.println("Verified ADCB balance");
				  safeClick(driver,getObjectPayment("Bento_ae_adcb_pay"));
				  Thread.sleep(1000);
				  elementVisible(driver,getObjectPayment("Bento_ae_adcb_otp"),5);
				  textPresent_Log(driver,"Enter one time password",2);
				  textPresent_Log(driver,"You pay AED",2);
				  Reporter.log("Verified ADCB flow");
				  System.out.println("Verified ADCB flow");		  
				  
				}
				else
				{
				safeClick(driver, getObjectPayment("Bento_select_sc"));
				safeClick(driver, getObjectPayment("Bento_sc_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
				safeClick(driver, getObjectPayment("Bento_card_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_card_password"), "123456");
				safeClick(driver, getObjectPayment("Bento_submit"));
				textPresent_Log(driver, "Your booking is done", 5);
				Reporter.log("Payment done successfully");
				}

			} 
			else 
			{
				if(CardNumber=="4000")
				{
					safeClick(driver, getObjectPayment("Bento_sc_noon"));
					safeClick(driver, getObjectPayment("Bento_sc_noon_cvv"));
					safeType(driver, getObjectPayment("Bento_sc_noon_cvv"), "123");
					safeClick(driver, getObjectPayment("Bento_paynow"));
					elementVisible(driver, getObjectPayment("Bento_sc_noon_password"), 5);
					driver.switchTo().frame(0);
					Thread.sleep(1000);
					safeClick(driver, getObjectPayment("Bento_sc_noon_password"));
					Thread.sleep(1000);
					safeType(driver, getObjectPayment("Bento_sc_noon_password"), "1234");
					safeClick(driver, getObjectPayment("Bento_sc_noon_password_submit"));
					textPresent_Log(driver, "Your booking is done", 5);
					Reporter.log("Payment done successfully");
			
				}
				else if(CardNumber=="4557")
				{
					safeClick(driver, getObjectPayment("Bento_sc_payfort"));
					safeClick(driver, getObjectPayment("Bento_sc_payfort_cvv"));
					safeType(driver, getObjectPayment("Bento_sc_payfort_cvv"), "123");
					safeClick(driver, getObjectPayment("Bento_paynow"));
					elementVisible(driver, getObjectPayment("Bento_sc_payfort_password"), 5);
					safeClick(driver, getObjectPayment("Bento_sc_payfort_password"));
					Thread.sleep(1000);
					safeType(driver, getObjectPayment("Bento_sc_payfort_password"), "12345");
					safeClick(driver, getObjectPayment("Bento_sc_payfort_submit"));
					textPresent_Log(driver, "Your booking is done", 5);
					Reporter.log("Payment done successfully");
			
				}
				else
				{
				safeClick(driver, getObjectPayment("Bento_sc_payfort"));
				safeClick(driver, getObjectPayment("Bento_sc_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
				safeClick(driver, getObjectPayment("Bento_card_password"));
				Thread.sleep(1000);
				safeType(driver, getObjectPayment("Bento_card_password"), "123456");
				safeClick(driver, getObjectPayment("Bento_submit"));
				textPresent_Log(driver, "Your booking is done", 5);
				Reporter.log("Payment done successfully");
				}
			}
			
		}
		if (PaymentType == "OTH") {
			if (elementVisible(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"), 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(2000);
				safeClick(driver, getObjectPayment("Bento_Select_sc"));

			} else {
				safeClick(driver, getObjectPayment("Bento_Select_sc"));
			}
			if (domain == "sa") {
				if(CardNumber=="4557")
				{
					safeClick(driver, getObjectPayment("Bento_sc_payfort"));
					safeClick(driver, getObjectPayment("Bento_sc_payfort_cvv"));
					safeType(driver, getObjectPayment("Bento_sc_payfort_cvv"), "123");
					safeClick(driver, getObjectPayment("Bento_paynow"));
					elementVisible(driver, getObjectPayment("Bento_sc_payfort_password"), 5);
					safeClick(driver, getObjectPayment("Bento_sc_payfort_password"));
					Thread.sleep(1000);
					safeType(driver, getObjectPayment("Bento_sc_payfort_password"), "12345");
					safeClick(driver, getObjectPayment("Bento_sc_payfort_submit"));
					textPresent_Log(driver, "Your booking is done", 5);
					Reporter.log("Payment done successfully");
				}
				else if(CardNumber=="4242")
				{
					safeClick(driver, getObjectPayment("Bento_sa_sc_checkout"));
					safeClick(driver, getObjectPayment("Bento_sa_sc_checkout_cvv"));
					safeType(driver, getObjectPayment("Bento_sa_sc_checkout_cvv"), "100");
					safeClick(driver, getObjectPayment("Bento_paynow"));
					elementVisible(driver,getObjectPayment("Bento_sa_sc_checkout_password_submit"),5);

					Thread.sleep(10000);
					driver.switchTo().frame(0);
					safeClick(driver,getObjectPayment("Bento_sa_sc_checkout_password"));
					Thread.sleep(1000);
					safeType(driver, getObjectPayment("Bento_sa_sc_checkout_password"), "Checkout1!");
					safeClick(driver, getObjectPayment("Bento_sa_sc_checkout_password_submit"));
					textPresent_Log(driver, "Your booking is done", 5);
					Reporter.log("Payment done successfully");
				}
				else if(CardNumber=="4543")
				{
					safeClick(driver, getObjectPayment("Bento_sa_sc_checkout2d"));
					safeClick(driver, getObjectPayment("Bento_sa_sc_checkout2d_cvv"));
					safeType(driver, getObjectPayment("Bento_sa_sc_checkout2d_cvv"), "956");
					safeClick(driver, getObjectPayment("Bento_paynow"));
					elementVisible(driver,getObjectPayment("Bento_sa_sc_checkout_password_submit"),5);
					driver.switchTo().frame(0);
					Thread.sleep(2000);
					safeClick(driver, getObjectPayment("Bento_sa_sc_checkout_password"));
					Thread.sleep(1000);
					safeType(driver, getObjectPayment("Bento_sa_sc_checkout_password"), "Checkout1!");
					safeClick(driver, getObjectPayment("Bento_sa_sc_checkout_password_submit"));
					textPresent_Log(driver, "Your booking is done", 5);
					Reporter.log("Payment done successfully");
				}
				else
				{
				safeClick(driver, getObjectPayment("Bento_sc_payu"));
				safeClick(driver, getObjectPayment("Bento_sc_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				}
			} else {
				safeClick(driver, getObjectPayment("Bento_select_sc"));
				safeClick(driver, getObjectPayment("Bento_sc_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
				  safeClick(driver, getObjectPayment("Bento_card_password"));
				  Thread.sleep(1000); 
				  safeType(driver, getObjectPayment("Bento_card_password"),"123456"); 
				  safeClick(driver, getObjectPayment("Bento_submit"));
				 
			}
			textPresent_Log(driver, "Your booking is done", 10);
			Reporter.log("Payment done successfully");
		}
		if(PaymentType=="sc")
		{
			if (textPresent(driver, "Cleartrip wallet", 3)) 
			{
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
			}
				Thread.sleep(1000);
			safeClick(driver,getObjectPayment("Bento_select_cardsec"));
			payUI_Enter_PaymentDetails(driver,PayType,BankName,"");
			safeClick(driver, getObjectPayment("Bento_paynow"));
			if(CardNumber=="5123")
			{
				elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
				  safeClick(driver, getObjectPayment("Bento_card_password"));
				  Thread.sleep(1000); 
				  safeType(driver, getObjectPayment("Bento_card_password"),"123456"); 
				  safeClick(driver, getObjectPayment("Bento_submit"));
				 
			}
			textPresent_Log(driver, "Your booking is done", 10);
			Reporter.log("Payment done successfully");
			}
	}

	public void confirmation_page(RemoteWebDriver driver, String PaymentType, String CardNumber) throws Exception {
		elementPresent_log(driver, By.linkText("Get your ticket"), "Get your ticket", 30);
		textPresent_Log(driver, "You just booked", 2);
		textPresent_Log(driver, "Travelers in this trip", 2);
		textPresent_Log(driver, "Itinerary sent", 2);
		textPresent_Log(driver, "PAYMENT RECEIPT", 2);
		textPresent_Log(driver, "TOTAL CHARGE", 2);
		textPresent_Log(driver, "RATE BREAK UP", 2);
		/* textPresent_Log(driver,"Convenience Fee",2); */
		textPresent_Log(driver, "Total", 2);
		textPresent_Log(driver, "Travel plans change often.", 2);
		String tripid = driver.findElement(getObjectPayment("Bento_Confirmation_Page_Gettrip")).getText();
		System.out.println(PaymentType+" "+CardNumber+" : "+tripid);
		Reporter.log(PaymentType+" "+CardNumber+" : "+tripid);

	}

}
