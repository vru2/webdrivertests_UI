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
	// public Cookie ctauth=new
	// Cookie("ct-auth","4eObn84%2Bk5aM7fqBP2kNvawrXVhbqTQbDGfyKoLD2KQOSQQpBHfijuyU%2FYJUc9OL8%2BbG%2BmR3IqzUF2rTzN7I5piHO4MxT9yU5Yto9sTx1rJfUfqwCOnfM5iisxvTtMxNphvkpCTs7Lhtzd44BCgvhDWsk5vS5U1OMMj3SmUJHCIJILOSPzhSOmPPD5DhsrFodefKYF%2FCGsqt2Jc5Neqc5eNslyD2knSAOhkndq4nRwdZcWFYlbS35tLLNHllptE3xY971Hav33t3IplfVZtxHyhQzFkkJlykZg0H%2Fu3zyt06tRi4rH7PPBC312VJneKDw%2FEnV%2FDTUW%2B%2FeKpKXG5Kls4al6piN%2Bbl7nn5pwjx0oT5Zv6fyW1WUEmtAlz3EDevQWOQ7N9ZaGtMfLQipc6G1JAQ3tYq%2FssUg6PQCwg%2B%2FeYJcA%2B1hpZo5%2BFbJz7QvrKoAQ0IvA7vEBDcKqzSKc3KUqCVTE%2B7vHyTj%2FPJ%2FnA2hEVDGDwCHr03QOhwwinKGMiVEXVIdSKo%2FFeskPXPTykpeWPZe%2BJ023nxHctXj2%2F1%2B9D%2FOLN5WXwkfmrzccdGByGgnju4QG5kcHvQZEYAoIHAKiiwrwKV7Ur%2FJZaW5lkRI%2B1VH8jWTdkSi3DV8IEVatWIYbHPxx5j8tGLTYvXyqa3GyGooJsBQGPrhYqSqttcFfCmllmQBvGGM%2FUBA02WuW%2B2%2By4L702JOuUbaS8cYCj92Jr946W5OVM%2B7BkTPuRhiv6a1umTXrxNoMIOPb5QKcEE3ziHnIiEIRJxv%2F3ov6iOZSoTqPCX0RTS2u2dGysRaY8IvwklhUIQrMMkC5iXqTFIOMcjpKJad%2FkktMiYmnIHokswV%2BuQoLK9RKIjReGFMlEhoadqhVIXczaZBYfQyW2WJZLB0%2BKY6qkiR7%2FdZ2ZdgKlRjfpDzM%2FSaJ7uLgu9co4H28DyHFPlv8VKHuyExhLe4200vlQVSlGINGubzED6jFKRDhCYDomMa7mU4aR%2BBFbBP0MoxTzQP7r8AuAEw44rfCcRJ7Sa0BFosw6zlKV%2FPVcZhVDeZhQlIHhVrn5mK1KYsbM54wrT4IOeuGEwVtJ2sh94UhlIgwQvE3RBvxmTAaJQhjp9%2FF0FQXZmOmg9QWShAhytzglleNq9FAsKyd8pWGJP28o42jsMz9GlJD75u%2BSX0zwsC%2Fh0Gq5hkf9X0B2u5KK4EfEoWtekR7Z7mXqUG8vigwDzk0LiGJmRFOeBKGCAh6drrlmpx%2BWlxikxm1q6vK4TiPMm3Pvy9HZMLEFU%2FbyG%2BuduJD%2BpNy06UlKdWfWn7N6rz0pV9ERPnA%2Fzxik1vK%2BlbxoIkTOxeVMYoTzx%2BRDEyo9MjCQlcI5K%2BXqy75cBiovhF%2FKcf00zq%2B4kHLbNgQWHsPxU03SG2PnNTTKvShsNtANjRoE31K8EhQCUxAy0RKUSZCsC9rC3NzCyiZQTvrXnMEr6FjJXE5K4ROWHnDjlNnuMOHjui7YmOJO2C5PBPoPgrJ%2B1aIzT21%2FzQzLiRo7keRlxWOHKbmV4hFcfNX%2FDY4ntaQ96pwUizeqbjD5p5svT%2BJUcF%2BcG8pmd8%2FIBe%2FhGz3kmIV4qcAxe1djoRcJom5g8g%2FfWWSBE8Rvhr3ZsFai68ZMULGQ4s1tKqJbnbr780dn%2FOSPEknKZPIv8dkfL3J%2B23%2FA1YIzFy95ByZvIgaTx5P5F8Hpykik9vgS9K%2FjiW0WH3IXVWki88SCUVi%2BVta4slJgS%2Be6rODov4AqGe9JHHmx4bSxUoOEdz%2BJeBexeeQ4x9jCiGxA%2BzgjmiBpgBO3HtA9aqWZN4q3J2ixi3GIqBCVEajm6X7f5eySokE%2FtnVhY4lE2NV91AmgTC9JPxlx29Cr0ngYx6%2FJR4sTtmeG9ZTFO6IxpJES3HaACXGG8iuQ7nT3DU%2BghAjFBd5HKozcaZuGOsw%2FxsSHcxCg3j9ehv%2Fk8%2BkWf%2FsxFfhjznpXvBUxH5gkp%2BsOErD2Q5NMWuyFdqg2Mei5Lvxe%2B2TobhYvcM6zkOqi%2B4%2F46qni8Dj4YppjJwoHqDuMu1qnOYRElc4aMUh6y%2B3X0VtqUQcKWMtV4GI1Hd1YXWkV4EHF80ojK%2FD%2Bm2NfEQuwtcwP5RlqZAjhYi%2BhaZbQm4THN1jIac%2BQMVECcA5YG78D9s%2BkvudTrT70vGXB09xwiz7KkbTzvVu8WKEAabfp0qI2DLe06E6%2BFEw%2Bb297hoRaUJOjNBMxFWnWNGEhNmjiNuK%2FT%2BFbl7FpgUg%2Bu057vX4zqbOrAC2P5zmLq3NP1zw5DUpM59SknIYnb8Ce9K2MTXDmfGnRDkkGOp2fhQ0%2BAw%2BzTy2NHSoFWgdaU2fw2pfPVBzVhjF0xUke73S8VOWyc2uO%2FEX9sg%2BRpvAlrZSVMY%2BTb8xy0l%2FT6WFPss44ETRQw6Ig66G%2F%2FMhEf4RmbIwovgQwOVIlAJR8a12guJZRrnk5mZ2AENQtzgUUeO%2FaWOYyAdL5UExD%2BfeJ8NdKorIczoq2at1mFoIGgnEk7t8KnGkImFah05R%2Boj1pg%2BzSqDApBxv%2BnKRPkddAWNPfmAMwe3%2By4vaCnp%2B%2BNOc1Sv6lKOkuuzCBY%2Budso0wBpKq9nyyBi8C%2F6cqt56zT%2BBzMGFfNBYVCik03cY4b7SWx6mz%2F2qGuZlSQx9iC3js%2BSiuUJ4PW6y8sWB6BqDCSoTW5tYtPJsndsgv7ZU4DjPkTdvAepvEbdIzFMJqF0fhPH7a1nVlHJvlFn1DKUmqJ0bCyMRPWFpH2q7DXnrq33hkOE1hVMVVE%2FJkKOw66J7UuK5HT1gHExMkIQ7OJUxhsPngckMCt19XGmirQSeV7Q4pgHJp97ptBoqcv9tQLnl4xkpjdddZi%2BfFlWWZSLKP8NNjGbIkjQdlq70uw9tQBU2QT4JTnqP%2FZp1PEdlsnbKyx3Mit36uLkaKB7geFSLzivGtYGbNlodU0SfQ0s%2FOgvfn1naVGf7viIjP1Q9Ds%2Fyk4VK%2FfP%2FsFAQpAyOWUJ8mCGDzIPb2EMK1DIhiN2T06YXg%2BZQxV7MgDzg%3D%3D");
	protected String username = "varalakshmi.venkateshaiah@cleartrip.com";
	public Cookie ctauth_partial_wallet = new Cookie("ct-auth","x%2F4IqrYI2rGRmYUEgqsFyJtuH64oYn%2FF9ao%2FFN%2BfplBVjyBPJv6PHh1sAW0TIa%2BBH8YOfEGj8AeevvMX%2F4QnQu5pne5K5EHLAFvUZ60PN8K8qX%2FBnweQFNfqHv2MpXaBrz1TDJgcj3KYoQs86tYYOkxbSn4KngmhppaUHjGty5%2F5GK2NTQEX8p2y7NWfDr9%2F");
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
	public void Searchpagebook(RemoteWebDriver driver, String wallettype) throws Exception {
		driver.manage().addCookie(bentoitn);
		if (wallettype == "Partial") {
			driver.manage().addCookie(ctauth_partial_wallet);
		} else {
			driver.manage().addCookie(ctauth);
		}
		driver.navigate().refresh();
		Thread.sleep(9000);
		elementPresent_log(driver, getObjectPayment("Bento_Book_Button"), "Book", 30);
		driver.findElement(getObjectPayment("Bento_Book_Button")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(getObjectPayment("Bento_Book_Button")).sendKeys(Keys.ARROW_DOWN);
		if (elementVisible(driver, getObjectPayment("Bento_Indigo_Logo"), 3)) {
			smartClick(driver, getObjectPayment("Bento_Book_Button"));
		} else if (elementVisible(driver, getObjectPayment("Bento_Spicejet_Logo"), 3)) {

			smartClick(driver, getObjectPayment("Bento_Book_Button"));
		} else if (elementVisible(driver, getObjectPayment("Bento_Airindia_Logo"), 3)) {

			smartClick(driver, getObjectPayment("Bento_Book_Button"));
		} else if (elementVisible(driver, getObjectPayment("Bento_Vistara_Logo"), 3)) {

			smartClick(driver, getObjectPayment("Bento_Book_Button"));
		} else {
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
			Thread.sleep(2000);
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
			Thread.sleep(2000);
		}
		 else 
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
		   Thread.sleep(1000);
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
		elementVisible(driver, getObjectPayment("Bento_Itn_Contact_Number"), 5);
		safeClick(driver, getObjectPayment("Bento_Itn_Contact_Number"));
		safeType(driver, getObjectPayment("Bento_Itn_Contact_Number"), "1234567890");
		Reporter.log("Entered mobile number");
		safeClick(driver, getObjectPayment("Bento_Itn_Username"));
		safeType(driver, getObjectPayment("Bento_Itn_Username"), username);
		Reporter.log("Entered user name");
		safeClick(driver, getObjectPayment("Bento_Itn_Contactinfo_Continue"));
		Reporter.log("Clicked on continue");
		safeClick(driver, getObjectPayment("Bento_Itn_User_Firstname"));
		safeType(driver, getObjectPayment("Bento_Itn_User_Firstname"), "Tester");
		Reporter.log("Entered first name");
		safeClick(driver, getObjectPayment("Bento_Itn_User_Lastname"));
		safeType(driver, getObjectPayment("Bento_Itn_User_Lastname"), "Test");
		Reporter.log("Entered last name");
		safeClick(driver, getObjectPayment("Bento_Itn_Select_Gender"));
		safeClick(driver, getObjectPayment("Bento_Itn_Select_Female"));
		Reporter.log("Selected gender");
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
				Thread.sleep(2000);

				safeClick(driver, getObjectPayment("Bento_aeitn_removeinsurance"));
				Thread.sleep(1000);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				safeClick(driver, getObjectPayment("Bento_aeitn_continue"));
				Thread.sleep(1000);
			}
		} else {

			if (elementVisible(driver, getObjectPayment("Bento_aeitn_continue1"), 20)) {
				safeClick(driver, getObjectPayment("Bento_aeitn_continue1"));
			} else {
				Thread.sleep(1000);
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				safeClick(driver, getObjectPayment("Bento_aeitn_continue"));
				Thread.sleep(1000);
			}
		}

		Thread.sleep(1000);
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
			safeClick(driver, getObjectPayment("Bento_aeitn_lastname"));
			safeType(driver, getObjectPayment("Bento_aeitn_lastname"), "Test");
			safeClick(driver, getObjectPayment("Bento_aeitn_select_gender"));
			safeClick(driver, getObjectPayment("Bento_ae_itn_select_female"));
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
			safeClick(driver, getObjectPayment("Bento_aeitn_Lastname"));
			safeType(driver, getObjectPayment("Bento_aeitn_Lastname"), "Test");
			safeClick(driver, getObjectPayment("Bento_aeitn_select_gender"));
			safeClick(driver, getObjectPayment("Bento_ae_itn_select_female"));
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

	public void bento_paymentpage(RemoteWebDriver driver, String PaymentType, String domain) throws Exception {
		Thread.sleep(2000);
		driver.manage().addCookie(cookie_Bento_Payment);
		driver.navigate().refresh();
		Thread.sleep(5000);
		textPresent_Log(driver, "Pay to complete your booking", 10);
		System.out.println(driver.getCurrentUrl());
		Reporter.log(driver.getCurrentUrl());

		if (PaymentType == "storedcard") {
			if (textPresent(driver, "Cleartrip wallet", 3)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
				Reporter.log("Clicked on SC");
			} else {
				safeClick(driver, getObjectPayment("Bento_Payment_Select_Storedcard"));
			}
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
				safeClick(driver, getObjectPayment("Bento_Payment_Wallet"));
				Reporter.log("Clicked on Wallet");
			} else {
				safeClick(driver, getObjectPayment("Bento_Payment_Wallet"));
				Reporter.log("Clicked on Walle");
			}
			safeClick(driver, getObjectPayment("Bento_Payment_Wallet_Phonepe"));
			Reporter.log("Selected Phonepe");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			textPresent_Log(driver, "Please wait...", 2);
			textPresent_Log(driver, "Welcome to Razorpay Software Private Ltd Bank", 5);
			safeClick(driver, getObjectPayment("Bento_Payment_NB_Payment_Success"));
			Reporter.log("Payment done successfully");

		}
		if (PaymentType == "UPI") {
			if (textPresent(driver, "Cleartrip wallet", 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				safeClick(driver, getObjectPayment("Bento_Payment_UPI"));
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
			safeClick(driver, getObjectPayment("Bento_Payment_SC1_CVV"));
			safeType(driver, getObjectPayment("Bento_Payment_SC1_CVV"), "1234");
			Reporter.log("Entered CVV details");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			textPresent_Log(driver, "Please wait...", 2);
			textPresent_Log(driver, "American", 5);
			textPresent_Log(driver, "ACS Emulator", 10);
			safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
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
			safeClick(driver, getObjectPayment("Bento_select_amex"));
			safeClick(driver, getObjectPayment("Bento_Payment_SC_CVV"));
			safeType(driver, getObjectPayment("Bento_Payment_SC_CVV"), "1234");
			Reporter.log("Entered CVV details");
			safeClick(driver, getObjectPayment("Bento_Payment_Paynow"));
			Reporter.log("Clicked on paynow");
			textPresent_Log(driver, "Please wait...", 2);
			textPresent_Log(driver, "American", 10);
			textPresent_Log(driver, "ACS Emulator", 15);
			safeClick(driver, getObjectPayment("Bento_Payment_AMC_SUBMIT"));
			textPresent_Log(driver, "Your booking is done", 10);
			Reporter.log("Payment done successfully");
		}
		if (PaymentType == "AE-SC") {
			if (elementVisible(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"), 2)) {
				safeClick(driver, getObjectPayment("Bento_Payment_Deselect_Wallet"));
				Reporter.log("Deselected wallet");
				Thread.sleep(2000);
				safeClick(driver, getObjectPayment("Bento_select_sc"));

			} else {
				safeClick(driver, getObjectPayment("Bento_select_sc"));
			}
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
				safeClick(driver, getObjectPayment("Bento_sc_payu"));
				safeClick(driver, getObjectPayment("Bento_sc_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
				safeClick(driver, getObjectPayment("Bento_paynow"));
			} else {
				safeClick(driver, getObjectPayment("Bento_sc_payu"));
				safeClick(driver, getObjectPayment("Bento_sc_cvv"));
				safeType(driver, getObjectPayment("Bento_sc_cvv"), "111");
				safeClick(driver, getObjectPayment("Bento_paynow"));
				elementVisible(driver, getObjectPayment("Bento_card_password"), 10);
				safeClick(driver, getObjectPayment("Bento_card_password"));
				safeType(driver, getObjectPayment("Bento_card_password"), "123456");
				Thread.sleep(1000);
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
