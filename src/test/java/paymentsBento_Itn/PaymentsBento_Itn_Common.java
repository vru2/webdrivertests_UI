package paymentsBento_Itn;

import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import paymentsUI_Air.PaymentUI_Common;

public class PaymentsBento_Itn_Common extends PaymentUI_Common {
	public Cookie ctauth = new Cookie("ct-auth","kQqdrcVR8t4znRp8uzBQJgaacI%2B5mUEhQsXqP%2BGvCv9Sca3PAxik9%2FDoNKFAEq5S6nDr3dyz0gFHshmzL9GNaG4e8msn1sCvUt92FE1Hxz%2B449dUBXvxJapPKHtcbOExsOm%2BE43PNH%2FbzMr%2Bgv0v9PZIafGsbWEbtoycPG3UjA%2BzcqiD2kXHlH7Tnnt7Xdd%2B");
	public RemoteWebDriver driver;
	public Cookie cookie_Bento_Payment = new Cookie("isBentoPayment", "true");
    public Cookie ctauth_amex=new Cookie("ct-auth","2%2BtU1cPb8lJr0jvLEAtykB9OU0fk%2F%2BykRqo7fqGZ%2FgNdUi7dMNUxWo%2BLayLyBmIQH8YOfEGj8AeevvMX%2F4QnQkvAnTTp9N%2FfevCUsB0kyv34RHOgDXRfdndn%2FWd0KOXhimPy3b9gj7V56t4fbK1hHoIzQYBzwMa%2Fi72%2FqTSKtPUlKo9yE91%2BeAEj2Bi%2FZIx%2FcqFKCJETXpAxsR3%2FhUWMrg%3D%3D");
    protected String username = "varalakshmi.venkateshaiah@cleartrip.com";
	public Cookie ctauth_partial_wallet = new Cookie("ct-auth","Bk7N%2FtlW6UIM9%2Fv06RR0lzYwI2Wr5NoY6shicJ7wSEglXjP2rTXj7vKCCjzDFS1EH8YOfEGj8AeevvMX%2F4QnQkvAnTTp9N%2FfevCUsB0kyv34RHOgDXRfdndn%2FWd0KOXh%2F2AX0kdZPIqgx5R%2FHygKQrR425YROepvP0SdSctCUtkcciwXF7FvVYKJizsM6Az33Pdp0Z8op1wWr79u2xWoxw%3D%3D");
	public Cookie bentoitn = new Cookie("forcedBentoItn", "true");
	public Cookie bento = new Cookie("isBento", "true");
	String GV_number = "3000331039198274";
	String GV_pin = "266685";
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	String contactnumber = "12345678";

	String searchurl = "/flights/results?adults=1&childs=0&infants=0&class=Economy&depart_date=31/05/2022&from=BLR&to=HYD&intl=n&origin=BLR - Bangalore, IN &destination=HYD - Hyderabad, IN &sd=1629707401889&rnd_one=O&sourceCountry=Hyderabad&destinationCountry=Bangalore";
	String qa2url = "https://qa2.cleartrip.com";
	String aeurl = "https://qa2.cleartrip.ae";
	String bhurl = "https://qa2bh.cleartrip.com";
	String qaurl = "https://qa2qa.cleartrip.com";
	String kwurl = "https://qa2kw.cleartrip.com";
	String saurl = "https://qa2.cleartrip.sa";
	String omurl = "https://qa2om.cleartrip.com";
	String meurl = "https://qa2me.cleartrip.com";

	// Booking via search page
	public void Searchpagebook(RemoteWebDriver driver, String wallettype, String domain, String cardtype) throws Exception {
		driver.manage().addCookie(bentoitn);
		if (wallettype == "Partial") {
			driver.manage().addCookie(ctauth_partial_wallet);
		} 
		/*
		 * else if(cardtype=="amex") { driver.manage().addCookie(ctauth_amex); }
		 */
		else {
			driver.manage().addCookie(ctauth);
		}
		driver.navigate().refresh();
		Thread.sleep(9000);
		elementPresent_log(driver, getObjectPayment("Bento_Book_Button"), "Book", 30);
	    if(domain=="com")
	    {
		 Actions actions=new Actions(driver);
		 actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	 	if(textPresent(driver,"Air India",5))
		{
			smartClick(driver,By.xpath("//div[5]/div[2]/div/label[2]/div/span"));
		}
	    }
		Thread.sleep(4000);
		 Actions actions=new Actions(driver);
		 actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_UP).perform();
		if (elementVisible(driver, getObjectPayment("Bento_Indigo_Logo"), 5)) {
			smartClick(driver,By.xpath("//div[4]/button"));
		} 
		else if (elementVisible(driver, getObjectPayment("Bento_Spicejet_Logo"), 3)) {

			smartClick(driver, getObjectPayment("Bento_Book_Button"));
		} 
		else if (elementVisible(driver, getObjectPayment("Bento_Vistara_Logo"), 3)) {

			smartClick(driver, getObjectPayment("Bento_Book_Button"));
		} 
		else {
			System.out.println("LDAP was displayed");
			Reporter.log("LDAP was displayed");
		}
		Thread.sleep(6000);
		Reporter.log("Clicked on Book");
		String parent = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				Thread.sleep(5000);
				driver.navigate().to(driver.getCurrentUrl());
				Thread.sleep(2000);
				textPresent_Log(driver, "Review your itinerary", 160);
				System.out.println(driver.switchTo().window(child_window).getCurrentUrl());
				Reporter.log(driver.switchTo().window(child_window).getCurrentUrl());
				if (textPresent(driver, "Sorry, our servers are stumped with your request", 30)
						|| textPresent(driver, "Flight not available", 30)) {
					System.out.println("Booking failed due to itn page issue");
					Reporter.log("Booking failed due to itn page issue");
					assertTrue(false);
				}
			}

		}
	}

	public void book_it(RemoteWebDriver driver, String gv_coupon) {
		Reporter.log("Itinerary page loaded");

	}

	public void book_itnnew(RemoteWebDriver driver, String gv_coupon) throws Exception {

		Reporter.log("Itinerary page loaded");
		if (elementVisible(driver, getObjectPayment("Bento_Itn_Standard_Fee"), 3)) {
			safeClick(driver, getObjectPayment("Bento_Itn_Standard_Fee"));
			Reporter.log("Selected itn fee");
			Thread.sleep(1000);
			if (gv_coupon == "GV") {
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
				WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
				ele.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(1000);
				ele.sendKeys("DOMOW");
				Thread.sleep(1000);
				Reporter.log("Entered Coupon details");
				smartClick(driver, getObjectPayment("Bento_Itn_Coupon_Apply"));
				if (textPresent_Log(driver, "Great! You just saved", 3) || textPresent_Log(driver,
						"Great! You just saved 184 on your booking. Discount will be applied on the payments page.",
						3)) {
					Reporter.log("Successfully applied coupon");
				}
			}
			Thread.sleep(2000);
			WebElement ele2 = driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue"));
			ele2.sendKeys(Keys.ARROW_DOWN);
			ele2.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(3000);
			safeClick(driver, getObjectPayment("Bento_Itn_Fare_Continue"));
			Reporter.log("Clicked on fare continue");
			Thread.sleep(3000);
		}
		else if(elementVisible(driver,getObjectPayment("Bento_Itn_fare_continue"),5))
		{
			Thread.sleep(1000);
			if (gv_coupon == "GV") {
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
				WebElement ele = driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
				ele.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(1000);
				ele.sendKeys("DOMOW");
				Thread.sleep(1000);
				Reporter.log("Entered Coupon details");
				smartClick(driver, getObjectPayment("Bento_Itn_Coupon_Apply"));
				if (textPresent_Log(driver, "Great! You just saved", 3) || textPresent_Log(driver,
						"Great! You just saved 184 on your booking. Discount will be applied on the payments page.",
						3)) {
					Reporter.log("Successfully applied coupon");
				}
			}
			Thread.sleep(2000);
			WebElement ele2 = driver.findElement(getObjectPayment("Bento_Itn_fare_continue"));
			ele2.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(5000);
			smartClick(driver, getObjectPayment("Bento_Itn_fare_continue"));
			Reporter.log("Clicked on fare continue");
			Thread.sleep(3000);
		}
		else if(elementVisible(driver,getObjectPayment("Bento_Itn_Standard_Fee1"),5))
		{ 
		  safeClick(driver,getObjectPayment("Bento_Itn_Standard_Fee1"));
		  Thread.sleep(1000); 
		  Reporter.log("Selected itn fee"); 
		  if(gv_coupon=="GV") {
		  WebElement ele3 =driver.findElement(getObjectPayment("Bento_Itn_GV_Number"));
		  ele3.sendKeys(Keys.PAGE_DOWN); 
		  Thread.sleep(2000); 
		  ele3.sendKeys(GV_number);
		  Reporter.log("Entered GV number"); 
		  Thread.sleep(1000);
		  //smartClick(driver,getObjectPayment("Bento_Itn_GV_Pin"));
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
		  ele4.sendKeys("DOMOW");
		  Thread.sleep(1000); 
		  Reporter.log("Entered Coupon details");
		  smartClick(driver,getObjectPayment("Bento_Itn_Coupon_Apply"));
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
		  Thread.sleep(1000);
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
		  ele2.click(); 
		  Reporter.log("Clicked on fare continue"); 
		  Thread.sleep(3000); 
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
		  Thread.sleep(1000);
		  //smartClick(driver,getObjectPayment("Bento_Itn_GV_Pin"));
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
		  ele4.sendKeys("DOMOW");
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
		  Thread.sleep(2000); 
		  }
		  else 
		  {
		  WebElement ele2=driver.findElement(getObjectPayment("Bento_Itn_Fare_Continue"));
		  Thread.sleep(2000); 
		  ele2.sendKeys(Keys.ARROW_DOWN); 
		  Thread.sleep(3000);
		  driver.executeScript("return arguments[0].scrollIntoView();", ele2);
		  ele2.click(); 
		  Reporter.log("Clicked on fare continue"); 
		  Thread.sleep(2000); 
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
		if(elementVisible(driver,getObjectPayment("Bento_Itn_User_lastname"),2))
		{
			safeClick(driver,getObjectPayment("Bento_Itn_User_lastname"));
			safeType(driver,getObjectPayment("Bento_Itn_User_lastname"),"Test");
			Reporter.log("Entered last name");
		}
		else
		{
		 safeClick(driver, getObjectPayment("Bento_Itn_User_Lastname"));
		 safeType(driver, getObjectPayment("Bento_Itn_User_Lastname"), "Test");
		 Reporter.log("Entered last name");
		}
		if(elementVisible(driver, getObjectPayment("Bento_Itn_Select_gender"),2))
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
		 if (elementVisible(driver, getObjectPayment("Bento_Itn_Nationality"), 5)) {
			safeClick(driver, getObjectPayment("Bento_Itn_Nationality"));
			Thread.sleep(2000);
			safeType(driver, getObjectPayment("Bento_Itn_Nationality"), "india");
			safeClick(driver, getObjectPayment("Bento_Itin_Select_India"));
			Reporter.log("Selected nationality");
		}
		safeClick(driver, getObjectPayment("Bento_Itn_Continue_Booking"));
		Thread.sleep(2000);
		Reporter.log("Clicked on continue button to navigate to payments page");
	}

	public void noncom_itnpage(RemoteWebDriver driver, String gv_coupon, String domain) throws Exception {
		Actions actions = new Actions(driver);
		if (domain == "ae" || domain == "sa") {
			if (elementVisible(driver, getObjectPayment("Bento_aeitn_continue1"), 20)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_continue1"));
			} else if (elementVisible(driver, getObjectPayment("Bento_aeitn_removeinsurance"), 20)) {
				Thread.sleep(3000);

				safeClick(driver, getObjectPayment("Bento_aeitn_removeinsurance"));
				Thread.sleep(3000);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				safeClick(driver, getObjectPayment("Bento_aeitn_continue"));
				Thread.sleep(3000);
			}
		} else {

			if (elementVisible(driver, getObjectPayment("Bento_aeitn_continue1"), 20)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_continue1"));
			} else {
				Thread.sleep(3000);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				safeClick(driver, getObjectPayment("Bento_aeitn_continue"));
				Thread.sleep(3000);
			}
		}

		Thread.sleep(3000);
		if(elementVisible(driver,getObjectPayment("Bento_aeitn_skip"),2))
		{
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		safeClick(driver, getObjectPayment("Bento_aeitn_skip"));
		Thread.sleep(3000);
		}
		elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number"), 5);
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
			
			
			if (elementVisible(driver, getObjectPayment("Bento_ae_nationality"), 2)) {
				safeClick(driver, getObjectPayment("Bento_ae_nationality"));
				safeType(driver, getObjectPayment("Bento_ae_type_nationality"), "India");
				safeClick(driver, getObjectPayment("Bento_ae_select_india"));
			}
			if (elementVisible(driver, getObjectPayment("Bento_ae_Continuetopayments1"), 2)) {
				safeClick(driver, getObjectPayment("Bento_ae_Continuetopayments1"));
			} else if (elementVisible(driver, getObjectPayment("Bento_ae_continuetopayment"), 2)) {
				safeClick(driver, getObjectPayment("Bento_ae_continuetopayment"));
			}
		}

		System.out.println("Clicked on continue button to navigate to payments page");
		Reporter.log("Clicked on continue button to navigate to payments page");

	}

	public void bento_paymentpage(RemoteWebDriver driver, String PaymentType,String CardNumber,String domain) throws Exception {
		Thread.sleep(2000);
		driver.manage().addCookie(cookie_Bento_Payment);
		driver.navigate().refresh();
		Thread.sleep(5000);
		textPresent_Log(driver, "Pay to complete your booking", 10);
		System.out.println(driver.getCurrentUrl());
		Reporter.log(driver.getCurrentUrl());
		Thread.sleep(1000);
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
			} else {
				if(CardNumber=="4111")
				{
					safeClick(driver, getObjectPayment("Bento_Payment_Select_DC_Storedcard"));
					Reporter.log("Clicked on SC");
					Thread.sleep(1000);
					safeClick(driver, getObjectPayment("Bento_Payment_SC_DC_CVV"));
					safeType(driver, getObjectPayment("Bento_Payment_SC_DC_CVV"), "111");
					Reporter.log("Entered CVV");
					safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
					Reporter.log("Clicked on paynow");
					textPresent_Log(driver, "Please wait...", 2);
					textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 2);
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
						textPresent_Log(driver, "One Time Password (OTP) successfully sent to the phone number linked to your card ending with 0000.", 5);
						safeClick(driver, getObjectPayment("Bento_Payment_Razropay_Pin"));
						safeType(driver,getObjectPayment("Bento_Payment_Razropay_Pin"),"0000");
						safeClick(driver,getObjectPayment("Bento_Payment_Razropay_Submit"));
						textPresent_Log(driver, "Your booking is done", 5);
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
				safeClick(driver, getObjectPayment("Bento_Payment_NB"));
				Reporter.log("Clicked on NB");
			} else {
				safeClick(driver, getObjectPayment("Bento_Payment_NB"));
				Reporter.log("Clicked on NB");
			}
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
			textPresent(driver, "SEND OTP TO LOGIN", 5);
			textPresent(driver, "Scan&Pay via PhonePe App", 2);
			textPresent(driver, "PhonePe QR", 2);	
			Reporter.log("PhonePe Page Validated");
			System.out.println("PhonePe Page Validated");
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
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			textPresent_Log(driver, "Please wait...", 2);
			textPresent_Log(driver, "Please accept the collect request sent to your UPI app", 5);
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
		if (PaymentType == "partial_wallet") {
			elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
			safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard1"));
			Reporter.log("Clicked on SC");
			/*
			 * safeClick(driver, getObjectPayment("Bento_Payment_SC1_CVV"));
			 * safeType(driver, getObjectPayment("Bento_Payment_SC1_CVV"), "1234");
			 * Reporter.log("Entered CVV details"); safeClick(driver,
			 * getObjectPayment("Bento_Payment_Paynow")); Reporter.log("Clicked on paynow");
			 * textPresent_Log(driver, "Please wait...", 2); textPresent_Log(driver,
			 * "American", 5); textPresent_Log(driver, "ACS Emulator", 10);
			 * safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
			 */
			safeClick(driver, getObjectPayment("Bento_Payment_select_sc"));
			safeClick(driver, getObjectPayment("Bento_Payment_select_sc_cvv"));
			safeType(driver, getObjectPayment("Bento_Payment_select_sc_cvv"), "111");
			safeClick(driver, getObjectPayment("Bento_paynow"));
			elementVisible(driver, getObjectPayment("Bento_card_password"), 5);
			  safeClick(driver, getObjectPayment("Bento_card_password"));
			  Thread.sleep(1000); 
			  safeType(driver, getObjectPayment("Bento_card_password"),"123456"); 
			  safeClick(driver, getObjectPayment("Bento_submit"));
			 
			textPresent_Log(driver, "Your booking is done", 5);
			Reporter.log("Payment done successfully");
		}
		if (PaymentType == "Coupon") {
			elementVisible(driver, getObjectPayment("Bento_Payment_Paynow"), 2);
			textPresent_Log(driver, "Coupon code (DOMOW)", 2);
			if (textPresent(driver, "Cleartrip wallet", 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
				Reporter.log("Clicked on SC");
			} else {
				safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
				Reporter.log("Clicked on SC");
			}
			/*
			 * safeClick(driver, getObjectPayment("Bento_select_amex")); safeClick(driver,
			 * getObjectPayment("Bento_Payment_SC_CVV")); safeType(driver,
			 * getObjectPayment("Bento_Payment_SC_CVV"), "1234");
			 * Reporter.log("Entered CVV details"); safeClick(driver,
			 * getObjectPayment("Bento_Payment_Paynow")); Reporter.log("Clicked on paynow");
			 * textPresent_Log(driver, "Please wait...", 2); textPresent_Log(driver,
			 * "American", 10); textPresent_Log(driver, "ACS Emulator", 15);
			 * safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
			 * textPresent_Log(driver, "Your booking is done", 10);
			 * Reporter.log("Payment done successfully");
			 */
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
					elementVisible(driver, getObjectPayment("Bento_sc_noon_password"), 5);
					safeClick(driver, getObjectPayment("Bento_sc_noon_password"));
					Thread.sleep(1000);
					safeType(driver, getObjectPayment("Bento_sc_noon_password"), "1234");
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
					driver.switchTo().frame(0);
					Thread.sleep(1000);
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
	}

	public void confirmation_page(RemoteWebDriver driver) throws Exception {
		elementPresent_log(driver, By.linkText("Get your ticket"), "Get your ticket", 10);
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
		System.out.println(tripid);
		Reporter.log(tripid);

	}

}
