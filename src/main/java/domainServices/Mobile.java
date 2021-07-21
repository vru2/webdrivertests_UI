// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2016
// Author - Mohamed Faisal
// Copyright © 2016 Cleartrip Travel. All rights reserved.
package domainServices;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import commonServices.WrapperMethod;

public class Mobile extends IndiaHotels {
	public String commonURL;
	public RemoteWebDriver driver;
	private String baseUrl = "https://" + common.value("host") + common.value("url") + "com";
	public String TripID = null;
	String Airline = "";

	public void mobileCom_SignIn(RemoteWebDriver driver) throws Exception {
		// driver.navigate().refresh();
		Thread.sleep(2000);
		//safeClick(driver, By.className("Header__menu"));
		// safeClick(driver,By.xpath("//*[text()='Menu']"));
		safeClick(driver, By.xpath("//*[text()='Settings']"));
		safeType(driver, getObject("MobileWeb_Trains_EmailIDR"), dataFile.value("TrainsEmailID"));
		safeType(driver, getObject("MobileWeb_Trains_PasswordR"), "9663806628");
		safeClick(driver, getObject("MobileWeb_Trains_LoginR"));
		// Reporter.log("Signing In...", true);
		//driver.findElementByClassName("Header__menuText").click();
		safeClick(driver,By.id("menuBtn"));
		//		scrollToElement(driver, getObject("MobileCom_Air_HomePage_signin"));
		//	safeClick(driver, getObject("MobileCom_Air_HomePage_signin"));

		/*if (!waitForElementVisibility(driver, getObject("MobileCom_Air_HomePage_UserName"), 30)) {
			Reporter.log("Sign In Page not Loadedin 30 seconds.", true);
			Assert.fail("Sign In page not Loaded!");
		}
		safeType(driver, getObject("MobileCom_Air_HomePage_UserName"), dataFile.value("Mobile_EmailID"));
		safeType(driver, getObject("MobileCom_Air_HomePage_Pwd"), dataFile.value("Mobile_Password"));
		safeClick(driver, getObject("MobileCom_Air_HomePage_SignIN_Button"));*/

	}

	public void mobileCom_SignIn_ForExpressway(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		Reporter.log("Signing In...", true);

		scrollToElement(driver, getObject("MobileCom_Air_HomePage_signin"));
		safeClick(driver, getObject("MobileCom_Air_HomePage_signin"));

		if (!waitForElementVisibility(driver, getObject("MobileCom_Air_HomePage_UserName"), 30)) {
			Reporter.log("Sign In Page not Loadedin 30 seconds.", true);
			Assert.fail("Sign In page not Loaded!");
		}
		safeType(driver, getObject("MobileCom_Air_HomePage_UserName"), dataFile.value("Mobile_Expressway_EmailID"));
		safeType(driver, getObject("MobileCom_Air_HomePage_Pwd"), dataFile.value("Mobile_Expressway_Password"));
		safeClick(driver, getObject("MobileCom_Air_HomePage_SignIN_Button"));

	}

	public void mobileCom_Hotel_SignIn(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		// safeClick(driver, getObject("MobileCom_Air_HomePage_Flight_Tab"));
		Thread.sleep(2000);
		if (elementPresent(driver, By.id("menuBtn"), 1)) {
			safeClick(driver, By.id("menuBtn"));
		}
		safeClick(driver, getObject("MobileCom_Air_HomePage_signin"));
		elementVisible(driver, getObject("MobileCom_Air_HomePage_UserName"), 10);
		safeType(driver, getObject("MobileCom_Air_HomePage_UserName"), dataFile.value("HotelEmailID"));
		safeType(driver, getObject("MobileCom_Air_HomePage_Pwd"), dataFile.value("HotelPassword"));
		Thread.sleep(2000);
		safeClick(driver, getObject("MobileCom_Air_HomePage_SignIN_Button"));
	}

	public void mobileCom_SignIn1(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		// safeClick(driver, getObject("MobileCom_Air_HomePage_Flight_Tab"));
		Thread.sleep(2000);
		if (elementPresent(driver, By.id("menuBtn"), 1)) {
			safeClick(driver, By.id("menuBtn"));
		}
		safeClick(driver, getObject("MobileCom_Air_HomePage_signin"));
		Thread.sleep(2000);
		safeType(driver, getObject("MobileCom_Air_HomePage_UserName"), dataFile.value("Mobilehotel_EmailID"));
		Thread.sleep(2000);
		safeType(driver, getObject("MobileCom_Air_HomePage_Pwd"), dataFile.value("Mobilehotel_Password"));
		Thread.sleep(2000);
		safeClick(driver, getObject("MobileCom_Air_HomePage_SignIN_Button"));
		Thread.sleep(5000);

	}

	public void mobileCom_Air_HomepageSearch_Oneway(RemoteWebDriver driver, String FromCity, String ToCity,
			String From_Date, String To_Date, String Adults, String Childrens, String Infants) throws Exception {
		driver.navigate().refresh();

		smartClick(driver, getObject("MobileCom_Air_HomePage_Flight_Tab"));
		/*
		 * if(driver.getCurrentUrl().contains("v2")) {
		 * Reporter.log("PWA page loaded. Switching back now...", true);
		 * driver.get(getMobile_Web_URL()); driver.navigate().refresh(); }
		 */
		safeClick(driver, getObject("MobileCom_Air_HomePage_Flight_Oneway"));

		if (!waitForElementVisibility(driver, getObject("MobileCom_Air_HomePage_From_Flight"), 30)) {
			Reporter.log("Flight Search Page not Loaded in 30 seconds.", true);
			Assert.fail("Flight Search page not Loaded!");
		}
		safeAutocomplete(driver, getObject("MobileCom_Air_HomePage_From_Flight"),
				getObject("MobileCom_Air_HomePage_Flight_Ajax"), FromCity);
		safeAutocomplete(driver, getObject("MobileCom_Air_HomePage_To_Flight"),
				getObject("MobileCom_Air_HomePage_Flight_Ajax"), ToCity);
		selectCalendarDate(driver, getObject("MobileCom_Air_HomePage_Departure_Calendar"),
				getObject("MobileCom_Air_HomePage_Calendar_NextMonth"), 1, From_Date);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Adults_DropDown"), Adults);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Childrens_DropDown"), Childrens);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Infants_DropDown"), Infants);

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//p[@class='action']/button")));
			//driver.findElement(By.xpath("//p[@class='action']/button")).click();
		} catch (Exception e) {
			safeClick(driver, getObject("MobileCom_Air_HomePage_Search_Button"));
		}
		// driver.navigate().refresh();
	}

	public boolean waitForElement(RemoteWebDriver driver, int time, By by) throws Exception {

		// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int i = 0;
		boolean elementActiveFlag = false;
		// TODO change for debug mode
		long timerNow = new Date().getTime();
		for (i = 0; (new Date().getTime() - timerNow) / 1000 <= time; i++) {
			if (elementPresent(driver, by, 1)) {
				elementActiveFlag = true;
				break;
			}
			/*
			 * else if(elementPresent(driver,By.xpath(
			 * "//android.widget.ImageView[contains(@resource-id,'moeCloseButton')]"),1)){
			 * safeClick(driver,By.xpath(
			 * "//android.widget.ImageView[contains(@resource-id,'moeCloseButton')]")); }
			 */
			Thread.sleep(1000);
		}
		// System.out.println((new Date().getTime() - timerNow) / 1000 + " seconds taken
		// for " + by + " to load");
		if (!elementActiveFlag) {
			// System.out.println("Element By "+by+ " Not Loaded in"+ time +"Seconds");
		}

		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return elementActiveFlag;
	}

	public void pwa_signIn(RemoteWebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Sign In')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		// Thread.sleep(500);
		safeClick(driver, getObject("MobileWeb_Trains_SignInR"));
		safeType(driver, getObject("MobileWeb_Trains_EmailIDR"), "ravikumar.valmiki@cleartrip.com");
		safeType(driver, getObject("MobileWeb_Trains_PasswordR"), "Valmiki123");
		/*
		 * safeType(driver,getObject("MobileWeb_Trains_EmailIDR"),dataFile.value(
		 * "AirUserIdForHQScripts"));
		 * safeType(driver,getObject("MobileWeb_Trains_PasswordR"),dataFile.value(
		 * "AirPasswordForHQScripts"));
		 */

		safeClick(driver, getObject("MobileWeb_Trains_LoginR"));

	}

	public String getModifiedDate(RemoteWebDriver driver, String date) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		c.add(Calendar.DATE, Integer.parseInt(date));
		String convertedDate = dateFormat.format(c.getTime());
		return convertedDate;

	}

	//yashmin
	public void pwa_Air_Homepage_MultiPAX(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,
			String To_Date, String Adult1, String Child1, String Infants, String triptype) throws Exception {
		safeClick(driver, By.id("flight-search"));
		int x = 0;
		if (triptype.equalsIgnoreCase("rt")) { 
			//	safeClick(driver, By.xpath("//*[text()='Round trip']"));
			//To pick pwa Rt section
			safeClick(driver, By.xpath("//div/*[text()='Round Trip']"));

		}

		if (elementPresent(driver, By.xpath("//p[text()='From']"), 3)) {
			driver.findElement(By.xpath("//*[text()='From']/parent::*/*[2]")).click();
		}
		if (elementPresent(driver, By.xpath("//*[text()='Origin']"), 3)) {
			driver.findElement(By.xpath("//*[text()='Origin']/parent::*/*[2]")).click();

		}
		Thread.sleep(1000);
		safeType(driver, getObject("MobileWeb_Trains_AutocompleteR"), FromCity);
		safeClick(driver, getObject("MobileWeb_Trains_OriginDropdownR"));
		//Thread.sleep(2000);
		// elementClickable(driver,By.xpath("//*[text()='Destination']/parent::*/*[2]"),10);
		if (elementPresent(driver, By.xpath("//p[text()='Destination']"), 3)) {
			driver.findElement(By.xpath("//*[text()='Destination']/parent::*/*[2]")).click();
		}
		if (elementPresent(driver, By.xpath("//p[text()='To']"), 3)) {
			driver.findElement(By.xpath("//*[text()='To']/parent::*/*[2]")).click();
		}

		// safeClick(driver, By.xpath("//*[text()='Destination']/parent::*/*[2]"));
		safeType(driver, getObject("MobileWeb_Trains_AutocompleteR"), ToCity);
		safeClick(driver, getObject("MobileWeb_Trains_OriginDropdownR"));
		/*
		 * String convertedDate=getModifiedDate(driver,"10"); Calendar c =
		 * Calendar.getInstance(); SimpleDateFormat dateFormat = new
		 * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt("10"));
		 * String convertedDate = dateFormat.format(c.getTime()); String
		 * a=Month.of(Integer.parseInt(convertedDate.split("-")[1])).name().toLowerCase(
		 * ); String b=String.valueOf(a.charAt(0)).toUpperCase();
		 * System.out.println("b value="+b); String
		 * z=a.replaceFirst(String.valueOf(a.charAt(0)),b);
		 * System.out.println("--------"+z+" "+convertedDate.split("-")[2]);
		 */

		if (x == 0) {
			if (triptype.equalsIgnoreCase("rt")) {
				String convertedDate = getModifiedDate(driver, "10");
				/*
				 * Calendar c = Calendar.getInstance(); SimpleDateFormat dateFormat = new
				 * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt("10"));
				 * String convertedDate = dateFormat.format(c.getTime());
				 */
				/*
				 * String
				 * a=Month.of(Integer.parseInt(convertedDate.split("-")[1])).name().toLowerCase(
				 * ); String b=String.valueOf(a.charAt(0)).toUpperCase();
				 * System.out.println("b value="+b); String
				 * z=a.replaceFirst(String.valueOf(a.charAt(0)),b);
				 * System.out.println("--------"+z+" "+convertedDate.split("-")[2]);
				 */
				safeClick(driver, By.xpath("//*[contains(text(),'Depart')]/parent::*/*[2]"));
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate + "')]")));
				js1.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate + "')]")));
				// js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td/button[contains(@aria-label,'"+z+"
				// "+convertedDate.split("-")[2].replaceFirst("^0+(?!$)", "")+"')]")));

				/*
				 * JavascriptExecutor js3 = (JavascriptExecutor) driver; WebElement element3 =
				 * driver.findElement(By.
				 * xpath("//*[@class='CalendarDay CalendarDay--valid' and button[contains(text(),'1')]]"
				 * )); js3.executeScript("arguments[0].scrollIntoView(true);", element3);
				 */
				// safeClick(driver,getObject("MobileWeb_Trains_DateselectR"));
				/*
				 * System.out.
				 * println("//*[@class='CalendarDay__button' and button[contains(aria-label,'z"+
				 * convertedDate.split("-")[2]+"'"); safeClick(driver,By.
				 * xpath("//*[@class='CalendarDay__button' and button[contains(aria-label,'z"+
				 * convertedDate.split("-")[2]+"'"));
				 */
				String convertedDate1 = getModifiedDate(driver, "13");
				/*
				 * Calendar c = Calendar.getInstance(); SimpleDateFormat dateFormat = new
				 * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt("10"));
				 * String convertedDate = dateFormat.format(c.getTime());
				 */
				/*
				 * String a1=Month.of(Integer.parseInt(convertedDate1.split("-")[1])).name().
				 * toLowerCase(); String b1=String.valueOf(a1.charAt(0)).toUpperCase();
				 * System.out.println("b value="+b1); String
				 * z1=a1.replaceFirst(String.valueOf(a1.charAt(0)),b1);
				 * System.out.println("--------"+z1+" "+convertedDate1.split("-")[2].
				 * replaceFirst("^0+(?!$)", ""));
				 */
				// JavascriptExecutor js = (JavascriptExecutor) driver;

				js1.executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate1 + "')]")));
				js1.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate1 + "')]")));
				/// js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td/button[contains(@aria-label,'"+z1+"
				/// "+convertedDate1.split("-")[2].replaceFirst("^0+(?!$)", "")+"')]")));
				/*
				 * Calendar c = Calendar.getInstance(); SimpleDateFormat dateFormat = new
				 * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt(15));
				 * String convertedDate = dateFormat.format(c.getTime());
				 */

				// driver.findElement(By.xpath("//*[contains(text(),'Jan')]//..//../*[2]/*/*[1]/*[1]")).click();
				// driver.findElement(By.xpath("//*[contains(text(),'Jan')]//..//../*[2]/*/*[1]/*[3]")).click();*/
				//
				/*
				 * JavascriptExecutor js = (JavascriptExecutor) driver; WebElement element2 =
				 * driver.findElement(By.
				 * xpath("//*[@class='CalendarDay CalendarDay--valid' and button[contains(text(),'1')]]"
				 * )); js.executeScript("arguments[0].scrollIntoView(true);", element2);
				 */
				// driver.findElement(By.xpath("//*[@class='CalendarDay CalendarDay--valid' and
				// button[contains(text(),'13')]]")).click();
				Thread.sleep(3000);
				/*
				 * JavascriptExecutor js = (JavascriptExecutor) driver; JavascriptExecutor js1 =
				 * (JavascriptExecutor) driver; WebElement element2 = driver.findElement(By.
				 * xpath("//td[@class='CalendarDay CalendarDay--valid CalendarDay--selected-start CalendarDay--selected-end']//..//following-sibling::tr[1]"
				 * )); js.executeScript("arguments[0].scrollIntoView(true);", element2);
				 * js1.executeScript("arguments[0].click();",driver.findElement(By.
				 * xpath("//td[@class='CalendarDay CalendarDay--valid CalendarDay--selected-start CalendarDay--selected-end']//..//following-sibling::tr[1]/td[1]"
				 * )));
				 */ } else {
					 String convertedDate = getModifiedDate(driver, "10");
					 /*
					  * Calendar c = Calendar.getInstance(); SimpleDateFormat dateFormat = new
					  * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt("10"));
					  * String convertedDate = dateFormat.format(c.getTime());
					  */
					 String a = Month.of(Integer.parseInt(convertedDate.split("-")[1])).name().toLowerCase();
					 String b = String.valueOf(a.charAt(0)).toUpperCase();
					 System.out.println("b value=" + b);
					 String z = a.replaceFirst(String.valueOf(a.charAt(0)), b);
					 System.out.println("--------" + z + " " + convertedDate.split("-")[2]);
					 safeClick(driver, By.xpath("//*[text()='Depart']/parent::*/*[2]"));
					 JavascriptExecutor js1 = (JavascriptExecutor) driver;
					 js1.executeScript("arguments[0].scrollIntoView(true);",
							 driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate + "')]")));
					 js1.executeScript("arguments[0].click();",
							 driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate + "')]")));
					 // js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td/button[contains(@aria-label,'"+z+"
					 // "+convertedDate.split("-")[2].replaceFirst("^0+(?!$)", "")+"')]")));
					 // driver.findElement(By.xpath("//td/button[contains(@aria-label,'December
					 // 30')]")).click();
					 // System.out.println("//*[@class='CalendarDay__button' and
					 // button[contains(aria-label,'"+z+" "+convertedDate.split("-")[2]+"')]]");
					 // safeClick(driver,By.xpath("//*[@class='CalendarDay__button' and
					 // button[contains(aria-label,'"+z+" "+convertedDate.split("-")[2]+"')]]"));
					 // safeClick(driver,By.xpath("//*[text()='Depart on']/parent::*/*[2]"));
					 // safeClick(driver,getObject("MobileWeb_Trains_DateselectR"));
				 }
			// safeClick(driver,By.xpath("//*[text()='Depart on']/parent::*/*[2]"));
		}
		// if(type.equalsIgnoreCase("rac") || type.equalsIgnoreCase("tatkal") ||
		// type.equalsIgnoreCase("wl")) {
		// safeClick(driver,getObject("MobileWeb_Trains_DateR"));
		/*
		 * if(x==0) { safeClick(driver,getObject("MobileWeb_Trains_DateRACR")); }
		 */
		// safeClick(driver,getObject("MobileWeb_Trains_DateRACR"));
		/*
		 * } else { safeClick(driver,getObject("MobileWeb_Trains_DateselectR")); }
		 */
		if (Adult1 != "1" || Child1 != "0") {
			safeClick(driver, getObject("MobileWeb_Trains_TravellerR"));
		}
		if (Adult1 != "1") {

			waitForElement(driver, 10, getObject("MobileWeb_Trains_AdultR"));
			Thread.sleep(2000);
			for (int i = 1; i < Integer.parseInt(Adult1); i++) {
				safeClick(driver, getObject("MobileWeb_Trains_AdultR"));
			}

		}
		if (Child1 != "0") {

			waitForElement(driver, 10, getObject("MobileWeb_Trains_ChildR"));
			Thread.sleep(2000);
			for (int i = 1; i < Integer.parseInt(Child1) + 1; i++) {
				safeClick(driver, getObject("MobileWeb_Trains_ChildR"));
			}

		}
		if (Infants != "0") {

			waitForElement(driver, 10, getObject("MobileWeb_Trains_InfantR"));
			Thread.sleep(2000);
			for (int i = 1; i < Integer.parseInt(Child1) + 1; i++) {
				safeClick(driver, getObject("MobileWeb_Trains_InfantR"));
			}

		}
		if (elementPresent(driver, getObject("Mobileweb_Air_traveller_continue_HP"), 10)) {
			safeClick(driver, getObject("Mobileweb_Air_traveller_continue_HP"));
		}
		WebElement element1 = (new WebDriverWait(driver, 50))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Depart']/parent::*/*[2]")));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(getObject("MobileWeb_Trains_Search"));
		js1.executeScript("arguments[0].scrollIntoView(true);", element12);
		js1.executeScript("arguments[0].click();", driver.findElement(getObject("MobileWeb_Trains_Search")));
		// safeClick(driver,getObject("MobileWeb_Trains_Search"));

	}

	public void pwa_Air_Homepage(RemoteWebDriver driver, String FromCity, String ToCity, String From_Date,
			String To_Date, String Adult1, String Child1, String Infants, String triptype) throws Exception {
		//safeClick(driver, By.xpath("//a/div[2]/h5[contains(text(),'Flights')]"));
		//System.out.println("Clicked on flights");
		int x = 0;
		if (triptype.equalsIgnoreCase("rt")) { 
			//	safeClick(driver, By.xpath("//*[text()='Round trip']"));
			//To pick pwa Rt section
			if(isElementVisible(driver,By.xpath("//div/*[text()='Round trip']")))
			{
				safeClick(driver, By.xpath("//div/*[text()='Round trip']"));
			}
			else if(isElementVisible(driver,By.xpath("//div/*[text()='Round Trip']")))
			{
				safeClick(driver, By.xpath("//div[@class='Tabs__item']/p[contains(text(),'Round Trip')]"));
			}
		}

		if (elementPresent(driver, By.xpath("//p[text()='From']"), 3)) {
			driver.findElement(By.xpath("//*[text()='From']/parent::*/*[2]")).click();
		}
		if (elementPresent(driver, By.xpath("//*[text()='Origin']"), 3)) {
			driver.findElement(By.xpath("//*[text()='Origin']/parent::*/*[2]")).click();

		}
		Thread.sleep(1000);
		//safeType(driver, getObject("MobileWeb_Trains_AutocompleteR"), FromCity);

		safeType(driver, getObject("PWAOrigingOW"), FromCity);
		//safeType(driver, getObject("PWAOriging"), FromCity);
		safeClick(driver, getObject("MobileWeb_Trains_OriginDropdownR"));
		//Thread.sleep(2000);
		// elementClickable(driver,By.xpath("//*[text()='Destination']/parent::*/*[2]"),10);
		if (elementPresent(driver, By.xpath("//p[text()='Destination']"), 3)) {
			driver.findElement(By.xpath("//*[text()='Destination']/parent::*/*[2]")).click();
		}
		if (elementPresent(driver, By.xpath("//p[text()='To']"), 3)) 
		{
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[text()='To']/parent::*/*[2]")).click();
		}
		Thread.sleep(3000);
		// safeClick(driver, By.xpath("//*[text()='Destination']/parent::*/*[2]"));
		safeType(driver, getObject("PWAOrigingOW"), ToCity);
		safeClick(driver, getObject("MobileWeb_Trains_OriginDropdownR"));
		Thread.sleep(3000);
		/*
		 * String convertedDate=getModifiedDate(driver,"10"); Calendar c =
		 * Calendar.getInstance(); SimpleDateFormat dateFormat = new
		 * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt("10"));
		 * String convertedDate = dateFormat.format(c.getTime()); String
		 * a=Month.of(Integer.parseInt(convertedDate.split("-")[1])).name().toLowerCase(
		 * ); String b=String.valueOf(a.charAt(0)).toUpperCase();
		 * System.out.println("b value="+b); String
		 * z=a.replaceFirst(String.valueOf(a.charAt(0)),b);
		 * System.out.println("--------"+z+" "+convertedDate.split("-")[2]);
		 */

		if (x == 0) {
			if (triptype.equalsIgnoreCase("rt")) {
				String convertedDate = getModifiedDate(driver, "10");
				/*
				 * Calendar c = Calendar.getInstance(); SimpleDateFormat dateFormat = new
				 * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt("10"));
				 * String convertedDate = dateFormat.format(c.getTime());
				 */
				/*
				 * String
				 * a=Month.of(Integer.parseInt(convertedDate.split("-")[1])).name().toLowerCase(
				 * ); String b=String.valueOf(a.charAt(0)).toUpperCase();
				 * System.out.println("b value="+b); String
				 * z=a.replaceFirst(String.valueOf(a.charAt(0)),b);
				 * System.out.println("--------"+z+" "+convertedDate.split("-")[2]);
				 */
				safeClick(driver, By.xpath("//p[contains(text(),'Depart')]"));
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate + "')]")));
				js1.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate + "')]")));
				// js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td/button[contains(@aria-label,'"+z+"
				// "+convertedDate.split("-")[2].replaceFirst("^0+(?!$)", "")+"')]")));

				/*
				 * JavascriptExecutor js3 = (JavascriptExecutor) driver; WebElement element3 =
				 * driver.findElement(By.
				 * xpath("//*[@class='CalendarDay CalendarDay--valid' and button[contains(text(),'1')]]"
				 * )); js3.executeScript("arguments[0].scrollIntoView(true);", element3);
				 */
				// safeClick(driver,getObject("MobileWeb_Trains_DateselectR"));
				/*
				 * System.out.
				 * println("//*[@class='CalendarDay__button' and button[contains(aria-label,'z"+
				 * convertedDate.split("-")[2]+"'"); safeClick(driver,By.
				 * xpath("//*[@class='CalendarDay__button' and button[contains(aria-label,'z"+
				 * convertedDate.split("-")[2]+"'"));
				 */
				String convertedDate1 = getModifiedDate(driver, "13");
				/*
				 * Calendar c = Calendar.getInstance(); SimpleDateFormat dateFormat = new
				 * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt("10"));
				 * String convertedDate = dateFormat.format(c.getTime());
				 */
				/*
				 * String a1=Month.of(Integer.parseInt(convertedDate1.split("-")[1])).name().
				 * toLowerCase(); String b1=String.valueOf(a1.charAt(0)).toUpperCase();
				 * System.out.println("b value="+b1); String
				 * z1=a1.replaceFirst(String.valueOf(a1.charAt(0)),b1);
				 * System.out.println("--------"+z1+" "+convertedDate1.split("-")[2].
				 * replaceFirst("^0+(?!$)", ""));
				 */
				// JavascriptExecutor js = (JavascriptExecutor) driver;

				js1.executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate1 + "')]")));
				js1.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate1 + "')]")));
				/// js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td/button[contains(@aria-label,'"+z1+"
				/// "+convertedDate1.split("-")[2].replaceFirst("^0+(?!$)", "")+"')]")));
				/*
				 * Calendar c = Calendar.getInstance(); SimpleDateFormat dateFormat = new
				 * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt(15));
				 * String convertedDate = dateFormat.format(c.getTime());
				 */

				// driver.findElement(By.xpath("//*[contains(text(),'Jan')]//..//../*[2]/*/*[1]/*[1]")).click();
				// driver.findElement(By.xpath("//*[contains(text(),'Jan')]//..//../*[2]/*/*[1]/*[3]")).click();*/
				//
				/*
				 * JavascriptExecutor js = (JavascriptExecutor) driver; WebElement element2 =
				 * driver.findElement(By.
				 * xpath("//*[@class='CalendarDay CalendarDay--valid' and button[contains(text(),'1')]]"
				 * )); js.executeScript("arguments[0].scrollIntoView(true);", element2);
				 */
				// driver.findElement(By.xpath("//*[@class='CalendarDay CalendarDay--valid' and
				// button[contains(text(),'13')]]")).click();
				Thread.sleep(3000);
				/*
				 * JavascriptExecutor js = (JavascriptExecutor) driver; JavascriptExecutor js1 =
				 * (JavascriptExecutor) driver; WebElement element2 = driver.findElement(By.
				 * xpath("//td[@class='CalendarDay CalendarDay--valid CalendarDay--selected-start CalendarDay--selected-end']//..//following-sibling::tr[1]"
				 * )); js.executeScript("arguments[0].scrollIntoView(true);", element2);
				 * js1.executeScript("arguments[0].click();",driver.findElement(By.
				 * xpath("//td[@class='CalendarDay CalendarDay--valid CalendarDay--selected-start CalendarDay--selected-end']//..//following-sibling::tr[1]/td[1]"
				 * )));
				 */ } else {
					 String convertedDate = getModifiedDate(driver, "10");
					 /*
					  * Calendar c = Calendar.getInstance(); SimpleDateFormat dateFormat = new
					  * SimpleDateFormat("yyyy-MM-dd"); c.add(Calendar.DATE, Integer.parseInt("10"));
					  * String convertedDate = dateFormat.format(c.getTime());
					  */
					 String a = Month.of(Integer.parseInt(convertedDate.split("-")[1])).name().toLowerCase();
					 String b = String.valueOf(a.charAt(0)).toUpperCase();
					 System.out.println("b value=" + b);
					 String z = a.replaceFirst(String.valueOf(a.charAt(0)), b);
					 System.out.println("--------" + z + " " + convertedDate.split("-")[2]);
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("//*[text()='Depart']/parent::*/*[2]")).click();
					 Thread.sleep(2000);
					 JavascriptExecutor js1 = (JavascriptExecutor) driver;
					 Thread.sleep(3000);
					 js1.executeScript("arguments[0].scrollIntoView(true);",

							 driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate + "')]")));
					 Thread.sleep(2000);
					 js1.executeScript("arguments[0].click();",

							 driver.findElement(By.xpath("//*[contains(@datetime,'" + convertedDate + "')]")));
					 // js1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//td/button[contains(@aria-label,'"+z+"
					 // "+convertedDate.split("-")[2].replaceFirst("^0+(?!$)", "")+"')]")));
					 // driver.findElement(By.xpath("//td/button[contains(@aria-label,'December
					 // 30')]")).click();
					 // System.out.println("//*[@class='CalendarDay__button' and
					 // button[contains(aria-label,'"+z+" "+convertedDate.split("-")[2]+"')]]");
					 // safeClick(driver,By.xpath("//*[@class='CalendarDay__button' and
					 // button[contains(aria-label,'"+z+" "+convertedDate.split("-")[2]+"')]]"));
					 // safeClick(driver,By.xpath("//*[text()='Depart on']/parent::*/*[2]"));
					 // safeClick(driver,getObject("MobileWeb_Trains_DateselectR"));
				 }
			// safeClick(driver,By.xpath("//*[text()='Depart on']/parent::*/*[2]"));
		}
		// if(type.equalsIgnoreCase("rac") || type.equalsIgnoreCase("tatkal") ||
		// type.equalsIgnoreCase("wl")) {
		// safeClick(driver,getObject("MobileWeb_Trains_DateR"));
		/*
		 * if(x==0) { safeClick(driver,getObject("MobileWeb_Trains_DateRACR")); }
		 */
		// safeClick(driver,getObject("MobileWeb_Trains_DateRACR"));
		/*
		 * } else { safeClick(driver,getObject("MobileWeb_Trains_DateselectR")); }
		 */
		if (Adult1 != "1" || Child1 != "0") {
			safeClick(driver, getObject("MobileWeb_Trains_TravellerR"));
		}
		if (Adult1 != "1") {

			waitForElement(driver, 10, getObject("MobileWeb_Trains_AdultR"));
			Thread.sleep(2000);
			for (int i = 1; i < Integer.parseInt(Adult1); i++) {
				safeClick(driver, getObject("MobileWeb_Trains_AdultR"));

			}

		}
		if (Child1 != "0") {

			waitForElement(driver, 10, getObject("MobileWeb_Trains_ChildR"));
			Thread.sleep(2000);
			for (int i = 1; i < Integer.parseInt(Child1) + 1; i++) {
				safeClick(driver, getObject("MobileWeb_Trains_ChildR"));
			}

		}
		if (Infants != "0") {

			waitForElement(driver, 10, getObject("MobileWeb_Trains_InfantR"));
			Thread.sleep(2000);
			for (int i = 1; i < Integer.parseInt(Child1) + 1; i++) {
				safeClick(driver, getObject("MobileWeb_Trains_InfantR"));
			}

		}
		if (elementPresent(driver, getObject("Mobileweb_Air_traveller_continue_HP"), 10)) {
			safeClick(driver, getObject("Mobileweb_Air_traveller_continue_HP"));
		}
		WebElement element1 = (new WebDriverWait(driver, 50))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Depart']/parent::*/*[2]")));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		WebElement element12 = driver.findElement(getObject("MobileWeb_Trains_Search"));

		js1.executeScript("arguments[0].scrollIntoView(true);", element12);
		js1.executeScript("arguments[0].click();", driver.findElement(getObject("MobileWeb_Trains_Search")));
		Thread.sleep(3000);
		// safeClick(driver,getObject("MobileWeb_Trains_Search"));

	}

	public void mobileCom_SD_Air_HomepageSearch_Oneway(RemoteWebDriver driver, String FromCity, String ToCity,
			String From_Date, String To_Date, String Adults, String Childrens, String Infants) throws Exception {
		driver.navigate().refresh();
		Thread.sleep(5000);
		safeAutocomplete(driver, getObject("MobileCom_Air_HomePage_From_Flight"),
				getObject("MobileCom_Air_HomePage_Flight_Ajax"), FromCity);
		safeAutocomplete(driver, getObject("MobileCom_Air_HomePage_To_Flight"),
				getObject("MobileCom_Air_HomePage_Flight_Ajax"), ToCity);
		selectCalendarDate(driver, getObject("MobileCom_Air_HomePage_Departure_Calendar"),
				getObject("MobileCom_Air_HomePage_Calendar_NextMonth"), 1, From_Date);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Adults_DropDown"), Adults);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Childrens_DropDown"), Childrens);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Infants_DropDown"), Infants);
		safeClick(driver, getObject("MobileCom_Air_HomePage_Search_Button"));
	}

	public void mobileCom_SD_Air_HomepageSearch_RoundTrip(RemoteWebDriver driver, String FromCity, String ToCity,
			String From_Date, String To_Date, String Adults, String Childrens, String Infants) throws Exception {
		driver.navigate().refresh();

		// safeClick(driver, getObject("MobileCom_Air_HomePage_Flight_RT"));
		safeClick(driver, By.id("searchForm"));
		safeClick(driver, By.xpath("//*[@id='FlightSearch']/table/tbody/tr[1]/td/label[2]"));
		safeAutocomplete(driver, getObject("MobileCom_Air_HomePage_From_Flight"),
				getObject("MobileCom_Air_HomePage_Flight_Ajax"), FromCity);
		Thread.sleep(2000);
		safeAutocomplete(driver, getObject("MobileCom_Air_HomePage_To_Flight"),
				getObject("MobileCom_Air_HomePage_Flight_Ajax"), ToCity);
		selectCalendarDate(driver, getObject("MobileCom_Air_HomePage_Departure_Calendar"),
				getObject("MobileCom_Air_HomePage_Calendar_NextMonth"), 1, From_Date);
		Thread.sleep(2000);
		selectCalendarDate(driver, getObject("MobileCom_Air_HomePage_Return_Calendar"),
				getObject("MobileCom_Air_HomePage_Calendar_NextMonth"), 0, To_Date);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Adults_DropDown"), Adults);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Childrens_DropDown"), Childrens);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Infants_DropDown"), Infants);
		safeClick(driver, getObject("MobileCom_Air_HomePage_Search_Button"));
	}

	public void mobileCom_Air_HomepageSearch_RoundTrip(RemoteWebDriver driver, String FromCity, String ToCity,
			String From_Date, String To_Date, String Adults, String Childrens, String Infants) throws Exception {
		driver.navigate().refresh();
		/*
		 * if(elementPresent(driver,By.id("branch-banner-close2"),1)){
		 * safeClick(driver,By.xpath("branch-banner-close2")); }
		 */
		Thread.sleep(5000);
		if (elementVisible(driver, getObject("MobileCom_Air_HomePage_Flight_Tab"), 1)) {
			safeClick(driver, getObject("MobileCom_Air_HomePage_Flight_Tab"));
		}
		safeClick(driver, getObject("MobileCom_Air_HomePage_Flight_RT"));
		safeAutocomplete(driver, getObject("MobileCom_Air_HomePage_From_Flight"),
				getObject("MobileCom_Air_HomePage_Flight_Ajax"), FromCity);
		Thread.sleep(2000);
		safeAutocomplete(driver, getObject("MobileCom_Air_HomePage_To_Flight"),
				getObject("MobileCom_Air_HomePage_Flight_Ajax"), ToCity);
		selectCalendarDate(driver, getObject("MobileCom_Air_HomePage_Departure_Calendar"),
				getObject("MobileCom_Air_HomePage_Calendar_NextMonth"), 1, From_Date);
		Thread.sleep(2000);
		selectCalendarDate(driver, getObject("MobileCom_Air_HomePage_Return_Calendar"),
				getObject("MobileCom_Air_HomePage_Calendar_NextMonth"), 0, To_Date);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Adults_DropDown"), Adults);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Childrens_DropDown"), Childrens);
		safeSelect(driver, getObject("MobileCom_Air_HomePage_Infants_DropDown"), Infants);
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//p[@class='action']/button")));
			// driver.findElement(By.xpath("//p[@class='action']/button")).click();
		} catch (Exception e) {
			safeClick(driver, getObject("MobileCom_Air_HomePage_Search_Button"));
		}
	}

	public void mobileCom_Air_SRP_Filter(RemoteWebDriver driver, String Carrier1, String Carrier2, String Carrier3)
			throws Exception {
		Boolean found = false;
		if (!waitForElementVisibility(driver, getObject("MobileCom_Air_SRP_Filter"), 90)) {
			Reporter.log("SRP Not loaded in 90 seconds", true);
			Assert.fail("SRP Not loaded in 90 seconds");
		}

		safeClick(driver, getObject("MobileCom_Air_SRP_Filter"));
		;

		elementVisible(driver, getObject("MobileCom_Air_SRP_Filter_Title"), 1);

		for (int i = 1; i <= 10; i++) {
			// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
			// driver.findElement(By.xpath("//fieldset[@id='airlines_filter']/label["+i+"]/span[2]")));
			if (elementVisible(driver, By.xpath("//fieldset[@id='airlines_filter']/label[" + i + "]/span[2]"), 1)) {
				String Flight = getText(driver, By.xpath("//fieldset[@id='airlines_filter']/label[" + i + "]/span[2]"));
				// System.out.println(Flight);

				if (Flight.contains(Carrier1) || Flight.contains(Carrier2) || Flight.contains(Carrier3)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
							driver.findElement(By.xpath("//fieldset[@id='airlines_filter']/label[" + i + "]/span[2]")));
					safeClick(driver, By.xpath("//fieldset[@id='airlines_filter']/label[" + i + "]/span[2]"));
					found = true;
				}
			} else
				i = 11;
		}

		if (!found) {
			Reporter.log("None of the following Airlines were found in the results: " + Carrier1 + " " + Carrier2 + " "
					+ Carrier3);
			Assert.fail("None of the following Airlines were found in the results: " + Carrier1 + " " + Carrier2 + " "
					+ Carrier3);
		}

		Thread.sleep(600);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
				driver.findElement(getObject("MobileCom_Air_SRP_Filter_Submit")));
		safeClick(driver, getObject("MobileCom_Air_SRP_Filter_Submit"));
	}

	public void mobileCom_Air_SRP_Filter1(RemoteWebDriver driver, String Carrier) throws Exception {
		//try {
		Thread.sleep(2000);
		//safeClick(driver, getObject("MobileCom_Air_SRP_Filter"));
		//} catch (Exception e) {
		driver.findElement(By.xpath("//button[@class='Fab']")).click();
		//}
		elementVisible(driver, getObject("MobileCom_Air_SRP_Filter_Title"), 3);

		for (int i = 1; i <= 10; i++) {
			System.out.println(i);
			// if(elementVisible(driver, By.xpath("//div/form/fieldset[3]/label["+i+"]"),
			// 1)){
			String Flight = getText(driver, By.xpath("//fieldset[@id='airlines_filter']/label[" + i + "]/span[2]"));
			System.out.println(Carrier);

			System.out.println(Flight);
			if (Flight.equalsIgnoreCase(Carrier)) {
				safeClick(driver, By.xpath("//fieldset[@id='airlines_filter']/label[" + i + "]/span[2]"));
				break;
			}
			// }

			// else i=11;
		}

		Thread.sleep(600);
		safeClick(driver, getObject("MobileCom_Air_SRP_Filter_Submit"));
	}

	public String getSRPPrice(RemoteWebDriver driver) throws Exception {
		String SRP = getText(driver, By.xpath("//dl[@class='kvp']/dd[3]")).split(" ")[1].replace(",", "");
		return SRP;
	}

	public void mobileCom_SD_Air_SRP_Filter(RemoteWebDriver driver, String Carrier1, String Carrier2, String Carrier3)
			throws Exception {

		// safeClick(driver,getObject("MobileCom_Air_SRP_Filter"));
		safeClick(driver, By.xpath("//*[@id='wrap']/h1/a[@class='pillButton fRight']"));
		// elementVisible(driver,getObject("MobileCom_Air_SRP_Filter_Title"), 1);
		elementVisible(driver, By.xpath("//*[@id='wrap']/h1"), 1);

		for (int i = 1; i <= 10; i++) { // [1]
			if (elementVisible(driver, By.xpath("//*[@id='airlines_filter']/label[" + i + "]"), 1)) {
				String Flight = getText(driver, By.xpath("//*[@id='airlines_filter']/label[" + i + "]"));
				if (Flight.contains("IndiGo") || Flight.contains("SpiceJet") || Flight.contains("Air India")) {
					safeClick(driver, By.xpath("//*[@id='airlines_filter']/label[" + i + "]"));
				}
			}

			else
				i = 11;
		}
		Thread.sleep(600);
		// safeClick(driver,getObject("MobileCom_Air_SRP_Filter_Submit"));
		safeClick(driver, By.xpath("//*[@id='wrap']/form/p/button"));
	}

	public int mobileCom_Air_Paymentpage_PriceValidation(RemoteWebDriver driver, int Price_int) throws Exception {

		WebElement element1 = (new WebDriverWait(driver, 90))
				.until(ExpectedConditions.visibilityOfElementLocated(getObject("MobileCom_Air_PaymentPage_Price")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(getObject("MobileCom_Air_PaymentPage_Price"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		String paymentPage_price = getText(driver, getObject("MobileCom_Air_PaymentPage_Price")).replace("INR ", "")
				.replace(",", "");
		printInfo("Price in Payment Page/ Step : " + paymentPage_price);
		Reporter.log("Price in Payment Page/ Step : " + paymentPage_price);
		int paymentPage_price_int = Integer.parseInt(paymentPage_price);
		if (elementVisible(driver, getObject("MobileCom_Air_PaymentPage_ConveninceFee"), 60)) {

			String paymentPage_Coveniprice = getText(driver, getObject("MobileCom_Air_PaymentPage_ConveninceFee"))
					.replace("A non-refundable Convenience fee of Rs. ", "")
					.replace(" per passenger is applicable on this booking.", "");
			paymentPage_Coveniprice = paymentPage_Coveniprice.replace("INR ", "");
			if (paymentPage_Coveniprice.contains(",")) {
				paymentPage_Coveniprice = paymentPage_Coveniprice.replace(",", "");
			}
			int paymentPage_Coveniprice_int = Integer.parseInt(paymentPage_Coveniprice);
			printInfo("Convenience fee : " + paymentPage_Coveniprice);
			Reporter.log("Convenience fee : " + paymentPage_Coveniprice);
			int PaymentPage_Price = (paymentPage_price_int - paymentPage_Coveniprice_int);
			if (!(PaymentPage_Price == Price_int)) {

				Reporter.log("amuont is not equal");
			}

		}

		return paymentPage_price_int;
	}

	public boolean PGCheck(RemoteWebDriver driver, String srpprice) throws Exception {
		WebElement element1 = (new WebDriverWait(driver, 120))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Card')]")));
		boolean pgcheck = true;
		String pgfees;
		String paymentpage;

		elementPresent(driver, By.id("mb_rate_rule"), 5);
		pgfees = getText(driver, By.id("mb_rate_rule")).split(" ")[1];
		paymentpage = driver.findElement(By.id("totalAmont")).getText().split(" ")[1].replace(",", "");

		Reporter.log("SRP Price: " + srpprice + "\n PG Fees: " + pgfees + "\n Payment Page Total: " + paymentpage,
				true);
		if ((NumberUtils.toDouble(srpprice) + NumberUtils.toDouble(pgfees)) != (NumberUtils.toDouble(paymentpage))) {
			pgcheck = false;
			Reporter.log("PG Fee is not added to SRP price.", true);
		}

		return pgcheck;
	}

	public void mobileCom_Air_ConfirmationPage_PriceValidation(RemoteWebDriver driver, int paymentPage_price_int)
			throws Exception {

		String Confiramation_price = null;
		for (int i = 0; i <= 6; i++) {
			String XPATH = "//dl[3]/dt[" + i + "]";
			if (elementPresent_Time(driver, By.xpath(XPATH), 1)) {
				String Price_Type = getText(driver, By.xpath(XPATH));
				if (Price_Type.contains("Total")) {
					Confiramation_price = getText(driver, By.xpath("//dl[3]/dd[" + i + "]/strong")).replace("INR ", "");

					if (Confiramation_price.contains(",")) {
						Confiramation_price = Confiramation_price.replace(",", "");
					}
					break;
				}
			}
		}

		// String Confiramation_price = getText(driver,
		// getObject("MobileCom_Air_ConfirmationPage_Price"));
		// Confiramation_price = Confiramation_price.replace("INR ", "").replace(",",
		// "");
		int Confirmation_price_int = Integer.parseInt(Confiramation_price);
		printInfo("Price in Trip Confirmation Page : " + Confirmation_price_int);
		Reporter.log("Price in Trip Confirmation Page : " + Confirmation_price_int);
		System.out.println(paymentPage_price_int + "     " + Confirmation_price_int);
		if (!(paymentPage_price_int == Confirmation_price_int)) {

			Reporter.log("Price in Confirmation Page ' " + Confirmation_price_int
					+ " is not equal to price in Payment page ' " + paymentPage_price_int + " '");
			Assert.assertEquals(true, false);
		}
	}

	public void mobileCom_Air_Itinerarypage_PriceValidation(RemoteWebDriver driver, int Price_int) throws Exception {
		WebElement element1 = (new WebDriverWait(driver, 90))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div/form/dl/dt[1]")));
		String Price_Total_Int = null;
		for (int i = 1; i <= 5; i++) {
			String Price_Text = "//html/body/div/form/dl/dt[" + i + "]";
			String Price_Int = "//html/body/div/form/dl/dd[" + i + "]";
			String Price_Detail = getText(driver, By.xpath(Price_Text));
			Price_Total_Int = getText(driver, By.xpath(Price_Int));
			if (Price_Detail.contains("Total")) {
				break;
			}
		}
		// String
		// itn_price=getText(driver,getObject("MobileCom_Air_ItineraryPage_Price")).replace("INR
		// ", "").replace(",", "");
		printInfo("Price in Itinerary Page : " + Price_Total_Int);
		Reporter.log("Price in Itinerary Page : " + Price_Total_Int);
		System.out.println();
		Price_Total_Int = Price_Total_Int.replace("INR ", "");
		if (Price_Total_Int.contains(",")) {
			Price_Total_Int = Price_Total_Int.replace(",", "");
		}
		int itn_price_int = Integer.parseInt(Price_Total_Int);
		if (!(Price_int == itn_price_int)) {
		}
	}

	public int mobileCom_Air_SRP_PriceValidation(RemoteWebDriver driver, String Carrier1, String Carrier2,
			String Carrier3) throws Exception {
		String Price;
		/*
		 * int size = driver.findElements(By.tagName("iframe")).size();
		 * System.out.println("iframe size="+size); Reporter.log("iframe size="+size);
		 * 
		 * if(size==1){ driver.switchTo().frame(0); } else if(size==2){
		 * driver.switchTo().frame(1); } else if(size==3){ driver.switchTo().frame(2); }
		 * else if(size==4){ driver.switchTo().frame(3); }
		 * if(elementPresent(driver,By.id("closeBanner"),2)){
		 * //driver.switchTo().frame(1);
		 * driver.findElement(By.id("closeBanner")).click(); }
		 */
		mobileCom_Air_SRP_Filter(driver, Carrier1, Carrier2, Carrier3);
		try {
			Price = driver.findElement(getObject("MobileCom_Air_SRP_Price")).getText().replace(",", "");
			// Price = getText(driver, getObject("MobileCom_Air_SRP_Price")).replace(",",
			// "");
		} catch (Exception e) {
			System.out
			.println("---------" + getText(driver, By.xpath("//p[@class='pricing']/strong")).replace(",", ""));
			Price = getText(driver, By.xpath("//p[@class='pricing']/strong")).replace(",", "");
		}
		int Price_int = Integer.parseInt(Price);
		printInfo("Price in Search Results Page : " + Price);
		Reporter.log("Price in Search Results Page : " + Price);
		try {
			driver.findElement(getObject("MobileCom_Air_SRP_Air_Segment")).click();
			// safeClick(driver, getObject("MobileCom_Air_SRP_Air_Segment"));
		} catch (Exception e) {
			driver.findElement(By.xpath("//li[@class='item'][1]")).click();
		}
		return Price_int;
	}

	public void mobileCom_Air_Intl_SRP_Filter(RemoteWebDriver driver) throws Exception {
		Thread.sleep(2000);
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("arguments[0].click();", driver.findElement(getObject("MobileCom_Air_SRP_Filter")));
		// safeClick(driver,getObject("MobileCom_Air_SRP_Filter"));
		List<WebElement> we = driver.findElements(By.xpath("//label"));
		// System.out.println(we.size());
		// System.out.println(we.get(we.size()-1).getText());
		for (int i = 1; i <= we.size() - 3; i++) {

			String label = "//div/div/div/div/div/form/div/fieldset/label[" + i + "]/span[2]";
			if (elementVisible(driver, By.xpath(label), 1)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath(label)));
				// safeClick(driver, By.xpath(label));
				String Flight = getText(driver, By.xpath(label));
				// if(Flight.contains("Air
				// India")||Flight.contains("Indigo")||Flight.contains("Spicejet")||Flight.contains("Jet
				// Airways")){
				if (Flight.contains("Emirates") || Flight.contains("Jet Airways") || Flight.contains("Air India")
						|| Flight.contains("Vistara")) {
					JavascriptExecutor js1 = (JavascriptExecutor) driver;
					js1.executeScript("arguments[0].click();", driver.findElement(By.xpath(label)));
					// safeClick(driver, By.xpath(label));
				}
			}
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.id("fSubmit"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", driver.findElement(By.id("fSubmit")));
		Thread.sleep(2000);
		// safeClick(driver, By.id("fSubmit"));
	}

	public void mobileCom_Air_Intl_SRP_Filter_GST(RemoteWebDriver driver, String carrier) throws Exception {
		safeClick(driver, By.xpath("//a[@class='clearField']"));
		safeClick(driver, By.xpath("//span[@class='filterLink']"));
		List<WebElement> we = driver.findElements(By.xpath("//label"));
		// System.out.println(we.size());
		// System.out.println(we.get(we.size()-1).getText());
		for (int i = 1; i <= we.size() - 3; i++) {

			String label = "//div/div/div/div/div/form/div/fieldset/label[" + i + "]/span[2]";
			if (elementVisible(driver, By.xpath(label), 1)) {
				safeClick(driver, By.xpath(label));
				String Flight = getText(driver, By.xpath(label));
				// if(Flight.contains("Air
				// India")||Flight.contains("Indigo")||Flight.contains("Spicejet")||Flight.contains("Jet
				// Airways")){
				if (Flight.contains(carrier)) {
					safeClick(driver, By.xpath(label));

				}
			}
		}

		safeClick(driver, By.id("fSubmit"));
		try {
			driver.findElement(By.xpath("//li[@class='item clearfix'][0]")).click();
			safeClick(driver, By.xpath("//li[@class='item clearfix'][0]"));
		} catch (Exception e) {
			driver.findElement(By.xpath("//div[@class='onward']")).click();
		}
	}

	public void mobileCom_SD_Air_Intl_SRP_Filter(RemoteWebDriver driver) throws Exception {

		List<WebElement> we = driver.findElements(By.xpath("//li[@class='item clearfix']"));

		we.get(0).click();

		// safeClick(driver,getObject("MobileCom_Air_SRP_Filter"));
		// safeClick(driver, By.id("book_flight_form"));
		/*
		 * List<WebElement> we=driver.findElements(By.xpath("//label"));
		 * //System.out.println(we.size());
		 * //System.out.println(we.get(we.size()-1).getText()); for(int
		 * i=1;i<=we.size()-3;i++){
		 * 
		 * String label =
		 * "//div/div/div/div/div/form/div/fieldset/label["+i+"]/span[2]";
		 * if(elementVisible(driver, By.xpath(label), 1)){ safeClick(driver,
		 * By.xpath(label)); String Flight = getText(driver, By.xpath(label));
		 * //if(Flight.contains("Air India")||Flight.contains("Indigo")||Flight.contains
		 * ("Spicejet")||Flight.contains("Jet Airways")){
		 * if(Flight.contains("Indigo")||Flight.contains("Spicejet")||Flight.
		 * contains("Jet Airways")){ safeClick(driver, By.xpath(label)); } } }
		 */

		// safeClick(driver, By.xpath("//*[@id='ExpressCheckoutHolder']/p/button"));
	}

	public void mobileCom_Air_SRP_Oneway(RemoteWebDriver driver, String carrier1, String carrier2, String carrier3)
			throws Exception {
		Thread.sleep(6000);
		if (elementPresent(driver, By.id("branch-banner-close2"), 1)) {
			safeClick(driver, By.xpath("branch-banner-close2"));
		}
		if (!elementPresent(driver, getObject("MobileCom_Air_SRP_Air_Segment"), 1)) {
			/*
			 * int size = driver.findElements(By.tagName("iframe")).size();
			 * System.out.println("iframe size="+size); Reporter.log("iframe size="+size);
			 * 
			 * if(size==1){ driver.switchTo().frame(0); } else if(size==2){
			 * driver.switchTo().frame(1); } else if(size==3){ driver.switchTo().frame(2); }
			 * else if(size==4){ driver.switchTo().frame(3); }
			 * if(elementPresent(driver,By.id("closeBanner"),2)){
			 * //driver.switchTo().frame(1);
			 * driver.findElement(By.id("closeBanner")).click(); }
			 * 
			 */}
		Reporter.log("Search URL: " + driver.getCurrentUrl(), true);
		mobileCom_Air_SRP_Filter(driver, carrier1, carrier2, carrier3);
		Thread.sleep(9000);
		// Assert.assertEquals(elementPresent(driver,
		// By.xpath("//li[@class='item'][1]"), 1),true,"SRP not loaded");
		// Reporter.log(driver.getCurrentUrl(),true);
		// Assert.assertEquals(elementPresent(driver, By.id("one-way-results"),
		// 1),true,"SRP not loaded");
		safeClick(driver, By.xpath("//li[@class='item'][1]"));// MobileCom_Air_SRP_Air_Segment

		/*
		 * if(URL.contains("qa2.cleartrip.com")||URL.contains("qa2.cleartrip.ae")){
		 * //System.out.println("loop1"); safeClick(driver,
		 * getObject("MobileCom_Air_SRP_Air_Segment"));//MobileCom_Air_SRP_Air_Segment
		 * //System.out.println(""); } // if(!URL.contains(".com")||
		 * URL.contains("bh.")) else { //System.out.println("loop2"); safeClick(driver,
		 * getObject("MobileBH_Air_SRP_Air_Segment"));//MobileBH_Air_SRP_Air_Segment
		 * 
		 * }
		 */

	}

	public void pwa_SRP_Filter(RemoteWebDriver driver, String oneway, String roundtrip, String carrier, String triptype,
			String flightpreference) throws Exception {

		List<WebElement> we;
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].click();",driver.findElement(By.xpath(
		 * "//*[text()='Filter']")));
		 */
		safeClick(driver, By.xpath("//*[text()='Filter']"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.xpath("//*[text()='Preferred Airlines']"));
		js1.executeScript("arguments[0].scrollIntoView(true);", element3);
		if (triptype == "rt") {
			// we=driver.findElements(By.xpath("//*[text()='Preferred
			// Airlines']/parent::*/*[6]/*/p"));
			we = driver.findElements(By.xpath("//*[text()='Preferred Airlines']/parent::*//../*[6]/*"));
		} else {
			we = driver.findElements(By.xpath("//*[text()='Preferred Airlines']/parent::*//../*[5]/*"));
		}
		for (int i = 0; i < we.size(); i++) {
			int x = i + 1;
			System.out.println("flight name=" + we.get(i).getText());
			if (flightpreference.equalsIgnoreCase("all")) {
				if (we.get(i).getText().equalsIgnoreCase("air india") || we.get(i).getText().equalsIgnoreCase("indigo")
						|| we.get(i).getText().equalsIgnoreCase("spicejet")) {
					if (!we.get(i).getText().equalsIgnoreCase("air india")) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						WebElement element = we.get(i).findElement(By.xpath("./div"));
						js.executeScript("arguments[0].scrollIntoView(true);", element);
					}
					Thread.sleep(3000);
					we.get(i).findElement(By.xpath("./div")).click();

					// System.out.println("//*[text()='Preferred
					// Airlines']/parent::*/*[5]/*["+x+"]/*[1]/*");
					// safeClick(driver,By.xpath("//*[text()='Preferred
					// Airlines']/parent::*/*[5]/*["+x+"]"));

				}
			} else {
				/// System.out.println(flightpreference);
				if ((we.get(i).getText().trim()).equalsIgnoreCase(flightpreference)) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					WebElement element = we.get(i).findElement(By.xpath("./p"));
					js.executeScript("arguments[0].scrollIntoView(true);", element);
					we.get(i).findElement(By.xpath("./p")).click();

					// System.out.println("//*[text()='Preferred
					// Airlines']/parent::*/*[5]/*["+x+"]/*[1]/*");
					// safeClick(driver,By.xpath("//*[text()='Preferred
					// Airlines']/parent::*/*[5]/*["+x+"]"));

				}

			}

		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.xpath("//button[contains(text(),'Apply')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element2);
		safeClick(driver, By.xpath("//button[contains(text(),'Apply')]"));
		// driver.manage().window().maximize();
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver; WebElement element =
		 * driver.findElement(By.xpath("//button[contains(text(),'Apply')]"));
		 * safeClick(driver,By.xpath("//button[contains(text(),'Apply')]"));
		 * js.executeScript("arguments[0].scrollIntoView(true);", element);
		 */
		Thread.sleep(8000);
		/*
		 * WebElement element1 =
		 * driver.findElement(By.xpath("//button[contains(text(),'Apply')]")); //Used
		 * points class to get x and y coordinates of element. Point point =
		 * element1.getLocation(); int xcord = point.getX();
		 * System.out.println("Position of the webelement from left side is "+xcord
		 * +" pixels"); int ycord = point.getY();
		 * System.out.println("Position of the webelement from top side is "+ycord
		 * +" pixels"); // Using Actions class Actions action = new Actions(driver);
		 * //clicking on the logo based on x coordinate and y coordinate //you will be
		 * redirecting to the home page of softwaretestingmaterial.com
		 * action.moveToElement(element1, xcord, ycord).click().build().perform();
		 */

	}

	public void pwa_Air_SRP(RemoteWebDriver driver, String triptype, String flightpreference) throws Exception {
		// public void pwa_Air_SRP(RemoteWebDriver driver) throws Exception {
		//WebElement element1 = (new WebDriverWait(driver,30))
		// .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Filter']")));
		// pwa_SRP_Filter(driver,"","","",triptype,flightpreference);
		Thread.sleep(3000);
		WebElement element2 = (new WebDriverWait(driver, 30))

				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[@class='Fab Fab-default']")));
		Thread.sleep(5000);
		/*driver.switchTo().frame("branch-banner-iframe");
		safeClick(driver, By.id("branch-banner-close1"));
		driver.switchTo().parentFrame();*/

		if (elementPresent(driver, By.xpath("//button[text()='Book']"), 10)) {
			//safeClick(driver, By.xpath("//button[text()='Book']"));
			safeClick(driver, By.xpath("//div[@class='Sticky Sticky--top']/ul[2]/li/div[2]"));
		} else {

			safeClick(driver, By.xpath("//li[1]"));
		}

		if (elementPresent(driver, By.xpath("//p[contains(text(),'Choose from')]"), 3)) {
			safeClick(driver, By.xpath("//li[1]"));
		} else if (elementPresent(driver, By.xpath("//p[contains(text(),'Combination')]"), 3)) {
			safeClick(driver, By.xpath("//button[text()='Continue booking']"));
		} else if (elementPresent(driver, By.xpath("//button[contains(text(),'booking')]"), 3)) {
			safeClick(driver, By.xpath("//button[contains(text(),'booking')]"));
		}
		else 
		{
			safeClick(driver,By.xpath("//button[@class='Button Button--full Button--primary']"));
		}

	}








	public void pwa_MEAir_SRP(RemoteWebDriver driver, String triptype, String flightpreference) throws Exception {
		// public void pwa_Air_SRP(RemoteWebDriver driver) throws Exception {
		//WebElement element1 = (new WebDriverWait(driver,30))
		// .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Filter']")));
		// pwaMEAir_SRP_Filter(driver,"","","",triptype,flightpreference);
		WebElement element2 = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='Fab']")));
		Thread.sleep(5000);
		/*driver.switchTo().frame("branch-banner-iframe");
		safeClick(driver, By.id("branch-banner-close1"));
		driver.switchTo().parentFrame();*/

		if (elementPresent(driver, By.xpath("//button[text()='Book']"), 10)) {
			safeClick(driver, By.xpath("//button[text()='Book']"));
		} else {

			safeClick(driver, By.xpath("//li[1]"));
		}

		if (elementPresent(driver, By.xpath("//p[contains(text(),'Choose from')]"), 3)) {
			safeClick(driver, By.xpath("//li[1]"));
		} else if (elementPresent(driver, By.xpath("//p[contains(text(),'Combination')]"), 3)) {
			safeClick(driver, By.xpath("//button[text()='Continue booking']"));
		} else if (elementPresent(driver, By.xpath("//button[contains(text(),'Book')]"), 3)) {
			safeClick(driver, By.xpath("//button[contains(text(),'Book')]"));
		}


	}
	public void nonCombineCheck(RemoteWebDriver driver) {

	}

	public void sortingAndFiltering(RemoteWebDriver driver) throws Exception {
		Thread.sleep(3000);
		WebElement element2 = (new WebDriverWait(driver, 30))

				// .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Filter']")));
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='Fab']")));
		/*Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='Fab']")).click();
		driver.findElement(By.className("Sheet__secondaryAction")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Apply filters']")).click();*/

		Reporter.log(driver.getCurrentUrl(), true);
		Thread.sleep(3000);
		ArrayList all = new ArrayList();
		ArrayList all1 = new ArrayList();
		ArrayList all2 = new ArrayList();
		ArrayList desc = new ArrayList();
		ArrayList desc1 = new ArrayList();
		ArrayList desc2 = new ArrayList();
		ArrayList desc3 = new ArrayList();
		ArrayList desc4 = new ArrayList();
		ArrayList desc5 = new ArrayList();

		priceCheck(driver, all);
		safeClick(driver, By.xpath("//*[contains(text(),'Price')]"));
		Thread.sleep(3000);

		priceCheck(driver, desc);

		Collections.reverse(desc);
		desc3 = desc;

		Collections.sort(desc);

		int j = 0;

		safeClick(driver, By.xpath("//*[contains(text(),'Duration')]"));

		Thread.sleep(3000);
		durationCheck(driver, all2);
		safeClick(driver, By.xpath("//*[contains(text(),'Duration')]"));
		Thread.sleep(3000);

		desc2 = durationCheck(driver, desc2);
		Collections.reverse(desc2);
		desc4 = desc2;

		Collections.sort(desc2);

		safeClick(driver, By.xpath("//*[contains(@label,'Time')]/p"));
		Thread.sleep(2000);

		timeCheck(driver, all1);
		safeClick(driver, By.xpath("//*[contains(@label,'Time')]"));
		Thread.sleep(2000);
		timeCheck(driver, desc1);
		Collections.reverse(desc);
		desc5 = desc1;
		// System.out.println("--------------------------------"+desc5);
		Collections.sort(desc1);
		// System.out.println("--------------------------------"+desc1);

		// System.out.println(all2);
		List copy = new ArrayList(all);
		List copy1 = new ArrayList(all1);
		List copy2 = new ArrayList(all2);
		Collections.sort(copy);
		Collections.sort(copy1);
		Collections.sort(copy2);
		// System.out.println("copy==="+copy);
		// System.out.println("copy1==="+copy1);
		// System.out.println("copy2===="+copy2);
		// System.out.println(copy2.equals(all2));
		Assert.assertEquals((copy.equals(all)), true);
		Assert.assertEquals((copy1.equals(all1)), true);
		Assert.assertEquals((copy2.equals(all2)), true);
		Assert.assertEquals((desc3.equals(desc)), true);
		Assert.assertEquals((desc5.equals(desc1)), true);
		Assert.assertEquals((desc4.equals(desc2)), true);
	}

	public ArrayList durationCheck(RemoteWebDriver driver, ArrayList all) {
		List<WebElement> we = driver.findElements(By.xpath("//time/parent::*/p"));
		for (int i = 0; i < we.size(); i++) {

			// System.out.println("duration ----"+we.get(i).getText().split("\\|")[0]);
			int c = we.get(i).getText().split("\\|")[0].split(" ").length;
			// System.out.println(we.get(i).getText().split("\\|")[0]);
			// System.out.println(we.get(i).getText().split("\\|")[0].split(" ").length);

			if (c == 2) {
				// System.out.println(we.get(i).getText().split("\\|")[0]);
				// System.out.println("--------"+we.get(i).getText().split("\\|")[0].split("
				// ")[1].replaceAll("[^\\d.]", ""));

				int mhour = Integer
						.parseInt(we.get(i).getText().split("\\|")[0].split(" ")[0].replaceAll("[^\\d.]", "")) * 60;
				// System.out.println("mhour======"+mhour);
				int mmin = Integer
						.parseInt(we.get(i).getText().split("\\|")[0].split(" ")[1].replaceAll("[^\\d.]", ""));
				// System.out.println("mmnin============="+mmin);
				int totaldur = mhour + mmin;
				all.add(totaldur);
				// System.out.println(all2);
			}
			if (c == 1) {
				// System.out.println(we.get(i).getText().split("\\|")[0].split(" ")[0]);
				if (we.get(i).getText().split("\\|")[0].split(" ")[0].contains("h")) {
					int mhour = Integer
							.parseInt(we.get(i).getText().split("\\|")[0].split(" ")[0].replaceAll("[^\\d.]", "")) * 60;
					all.add(mhour);
				} else {
					int mmin = Integer
							.parseInt(we.get(i).getText().split("\\|")[0].split(" ")[0].replaceAll("[^\\d.]", ""));
					all.add(mmin);
				}
			}

		}
		return all;
	}

	public ArrayList priceCheck(RemoteWebDriver driver, ArrayList all) {
		// List<WebElement>
		// we=driver.findElements(By.xpath("//li[contains(@role,'menuitem')]/*[3]/*/*
		// and not(contains(@class ='LowSeat'))"));
		List<WebElement> we = driver.findElements(By.xpath("//li[contains(@role,'menuitem')]/*[3]/*"));

		for (int i = 0; i < we.size(); i++) {
			List<WebElement> we1 = we.get(i).findElements(By.xpath("./p"));

			if (we1.size() == 2) {

				all.add(Integer.parseInt(we1.get(1).getText().substring(1).replace(",", "").trim()));
			} else {
				all.add(Integer.parseInt(we1.get(0).getText().substring(1).replace(",", "").trim()));
			}
			// System.out.println("size ="+we.size());
			// System.out.println(we.get(i).getText().substring(1).replace(",",""));

			// ArrayList all=new ArrayList();

		}
		return all;
	}

	public ArrayList timeCheck(RemoteWebDriver driver, ArrayList all) {
		List<WebElement> we = driver.findElements(By.xpath("//li[@role='menuitem']/*/*[1]/time"));
		for (int i = 0; i < we.size(); i++) {
			//
			// Reporter.log(we.get(i).getText(),true);
			String a = we.get(i).getText().split(" ")[0].split(":")[1];
			// Reporter.log(we.get(i).getText().split(" ")[0],true);
			int y = Integer.parseInt(a.trim());
			int x = ((Integer.parseInt(we.get(i).getText().split(" ")[0].split(":")[0]) * 60));
			// Reporter.log(String.valueOf(x),true);
			// Reporter.log(String.valueOf(y),true);
			int z = x + y;
			// Reporter.log("z value="+z,true);

			all.add(z);
			// Reporter.log(String.valueOf(all),true);
		}
		return all;
	}

	public void checkSPRTPrice(RemoteWebDriver driver) throws Exception {
		//Thread.sleep(3000);

		/*driver.switchTo().frame("branch-banner-iframe");
		safeClick(driver, By.id("branch-banner-close1"));
		driver.switchTo().parentFrame();*/

		WebElement element1 = (new WebDriverWait(driver,50))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='Fab']")));
		Thread.sleep(3000);


		if(elementPresent(driver, By.xpath("//button[@class='Fab']"))){
			safeClick(driver, By.xpath("//button[@class='Fab']"));
			Thread.sleep(3000);
			driver.findElement(By.className("Sheet__secondaryAction")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[text()='Apply filters']")).click();
		}

		List<WebElement> we = driver.findElements(By.xpath("//*[text()='All flights']//../../*/*[1]"));

		//for (int i = 1; i < we.size(); i++) {
		for (int i = 1; i < 1; i++) {
			System.out.println(we.size());
			we.get(i).click();
			// System.out.println("airline="+we.get(i).findElement(By.xpath("./div[1]")).getText());
			System.out.println("price="
					+ we.get(i).findElement(By.xpath("./div[2]")).getText().substring(1).replace(",", "").trim());
			List<WebElement> we1 = driver.findElements(By.xpath("//*[contains(@class,'selected')][1]"));

			// List<WebElement>
			// we2=driver.findElements(By.xpath("//*[contains(@class,'selected')][1]/*[1]/*/p"));
			System.out.println("price 1="
					+ we1.get(0).findElement(By.xpath("./div/p[2]")).getText().substring(1).replace(",", "").trim());
			System.out.println("-------------------" + we1.get(1).findElement(By.xpath("./div/p[2]")).getText());
			System.out.println("price 2="
					+ we1.get(1).findElement(By.xpath("./div/p[2]")).getText().substring(1).replace(",", "").trim());
			System.out
			.println("flight code 0=" + we1.get(0).findElement(By.xpath("./*[1]/*/p")).getText().split("-")[0]);
			System.out
			.println("flight code 1=" + we1.get(1).findElement(By.xpath("./*[1]/*/p")).getText().split("-")[0]);
			int highlightedprice = Integer.parseInt(
					we.get(i).findElement(By.xpath("./div[2]")).getText().substring(1).replace(",", "").trim());
			int onwardprice = Integer.parseInt(
					we1.get(0).findElement(By.xpath("./div/p[2]")).getText().substring(1).replace(",", "").trim());
			int returnprice = Integer.parseInt(
					we1.get(1).findElement(By.xpath("./div/p[2]")).getText().substring(1).replace(",", "").trim());
			System.out.println("highlightedprice=" + highlightedprice);
			System.out.println("onwardprice=" + onwardprice);
			System.out.println("returnprice=" + returnprice);
			int total = onwardprice + returnprice;
			Assert.assertEquals(highlightedprice, (onwardprice + returnprice));
			System.out.println("-----------------------------------------------------------------------------------");

		}


		// List<WebElement>
		// we1=driver.findElements(By.xpath("//*[contains(@class,'selected')][1]"));
		// List<WebElement>
		// we2=driver.findElements(By.xpath("//*[contains(@class,'selected')][1]/*[1]/*/p"));
		// System.out.println("price
		// 1="+we1.get(0).findElement(By.xpath("./div/p[2]")).getText());
		// System.out.println("price
		// 2="+we1.get(1).findElement(By.xpath("./div/p[2]")).getText());
		// System.out.println("flight code
		// 0="+we1.get(0).findElement(By.xpath("./*[1]/*/p")).getText().split("-")[0]);
		// System.out.println("flight code
		// 1="+we1.get(1).findElement(By.xpath("./*[1]/*/p")).getText().split("-")[0]);*/

	}

	public <IJavaScriptExecutor> void mobileCom_Air_SRP_Oneway1(RemoteWebDriver driver, String carrier) throws Exception {


		/*if (!elementPresent(driver, getObject("MobileCom_Air_SRP_Air_Segment"), 1)) {
		}*/

		/*mobileCom_Air_SRP_Filter1(driver, carrier);
		String URL = getURL(driver);*/

		//safeClick(driver, By.xpath("//li[@class='item'][1]"));// MobileCom_Air_SRP_Air_Segment
		if (elementPresent(driver, By.xpath("//button[text()='Book']"), 10)) {
			safeClick(driver, By.xpath("//button[text()='Book']"));
		} 
		else 
			Thread.sleep(3000);
		{
			if (elementPresent(driver, By.xpath("//div/ul//div/li[@role='menuitem'][4]"), 3)) {
				driver.findElement(By.xpath("//div/ul//div/li[@role='menuitem'][2]")).click();
			}
			//driver.findElement(By.xpath("//li[@role='menuitem'][1]")).click();

			waitForElement(driver,20,By.xpath("//*[text()='Total']//..//../*[3]/*[2]"));

		}


	}

	public void mobileCom_Air_IntlSRP_(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		Thread.sleep(9000);
		if (!elementPresent(driver, getObject("MobileCom_Air_SRP_Air_IntlSegment"), 1)) {
			/*
			 * int size = driver.findElements(By.tagName("iframe")).size();
			 * System.out.println("iframe size="+size); Reporter.log("iframe size="+size);
			 * 
			 * if(size==1){ driver.switchTo().frame(0); } else if(size==2){
			 * driver.switchTo().frame(1); } else if(size==3){ driver.switchTo().frame(2); }
			 * else if(size==4){ driver.switchTo().frame(3); }
			 * if(elementPresent(driver,By.id("closeBanner"),2)){
			 * //driver.switchTo().frame(1);
			 * driver.findElement(By.id("closeBanner")).click(); }
			 */}
		elementVisible(driver, getObject("MobileCom_Air_SRP_Air_IntlSegment"), 100);
		safeClick(driver, getObject("MobileCom_Air_SRP_Air_IntlSegment"));
		System.out.println(getObject("MobileCom_Air_SRP_Air_IntlSegment"));
		Thread.sleep(2000);
		mobileCom_Air_Intl_SRP_Filter(driver);
		// driver.navigate().refresh();
		Thread.sleep(2000);
		if (elementVisible(driver, By.xpath("//div/div/div/div/div/div/p[1]/strong"), 20)) {
			String ErrorMsg = getText(driver, By.xpath("//div/div/div/div/div/div/p[1]/strong"));
			printInfo(ErrorMsg);
			Reporter.log(ErrorMsg);
		} /*
		 * List<WebElement> weList=driver.findElements(By.xpath("//*[@class=\"INR\"]"));
		 * weList.get(0).click();
		 */

		if (elementVisible(driver, By.xpath("//ul[@id='topTen']/li"), 1)) {
			List<WebElement> we1 = driver.findElements(By.xpath("//ul[@id='topTen']/li"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", we1.get(0));
			js.executeScript("arguments[0].click();", we1.get(0));
			// we1.get(0).click();
		}
		if (elementVisible(driver, By.xpath("//button[@id='splitResBookBtn']"), 1)) {
			safeClick(driver, By.xpath("//button[@id='splitResBookBtn']"));
		}
	}

	public void mobileIntlSRPOW(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		Reporter.log("Search URL: " + driver.getCurrentUrl(), true);

		if (waitForElementVisibility(driver, By.xpath("//div[contains(@class,'blockMessage bad')]"), 10)) {
			Reporter.log("Error in Search!", true);
			Reporter.log("Message: " + getText(driver, By.xpath("//div[contains(@class,'blockMessage bad')]")), true);
			Assert.fail("Error in Search!!");
		}

		if (!waitForElementVisibility(driver, By.xpath("//*[text()='Recommended itineraries for your search']"), 120)) {
			Reporter.log("Search Results not loaded in 120 seconds. SRP load failure!", true);
			Assert.fail("Search Results not loaded in 120 seconds. SRP load failure!");
		}

		mobileCom_Air_Intl_SRP_Filter(driver);

		if (elementPresent(driver, By.xpath("//button[text()='Book']"), 2)) {
			safeClick(driver, By.xpath("//button[text()='Book']"));
		} else {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[@class='item clearfix'][1]")));
			// safeClick(driver,By.xpath("//li[@class='item clearfix'][1]"));
		}
		if (elementPresent(driver, By.xpath("//li[@class='item clearfix'][1]"), 5)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[@class='item clearfix'][1]")));
			// safeClick(driver,By.xpath("//li[@class='item clearfix'][1]"));
		}
		if (elementPresent(driver, By.xpath("//*[contains(text(),'flights at the same price')]"), 2)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[1]")));
			safeClick(driver, By.xpath("//li[@class='item clearfix'][1]"));
		} else if (elementPresent(driver, By.xpath("//p[contains(text(),'Combination')]"), 2)) {
			safeClick(driver, By.xpath("//button[text()='Book']"));
		}
	}

	public void mobileCom_Air_IntlSRP_RT(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		Reporter.log("Search URL: " + driver.getCurrentUrl(), true);

		if (waitForElementVisibility(driver, By.xpath("//div[contains(@class,'blockMessage bad')]"), 10)) {
			Reporter.log("Error in Search!", true);
			Reporter.log("Message: " + getText(driver, By.xpath("//div[contains(@class,'blockMessage bad')]")), true);
			Assert.fail("Error in Search!!");
		}

		if (!waitForElementVisibility(driver, By.xpath("//*[text()='Recommended itineraries for your search']"), 120)) {
			Reporter.log("Search Results not loaded in 120 seconds. SRP load failure!", true);
			Assert.fail("Search Results not loaded in 120 seconds. SRP load failure!");
		}

		if (!elementPresent(driver, getObject("MobileCom_Air_SRP_Air_IntlSegment"), 1)) {
			/*
			 * int size = driver.findElements(By.tagName("iframe")).size();
			 * System.out.println("iframe size="+size); Reporter.log("iframe size="+size);
			 * 
			 * if(size==1){ driver.switchTo().frame(0); } else if(size==2){
			 * driver.switchTo().frame(1); } else if(size==3){ driver.switchTo().frame(2); }
			 * else if(size==4){ driver.switchTo().frame(3); }
			 * if(elementPresent(driver,By.id("closeBanner"),2)){
			 * //driver.switchTo().frame(1);
			 * driver.findElement(By.id("closeBanner")).click(); }
			 */}
		elementVisible(driver, getObject("MobileCom_Air_SRP_Air_IntlSegment"), 100);
		safeClick(driver, getObject("MobileCom_Air_SRP_Air_IntlSegment"));
		// System.out.println(getObject("MobileCom_Air_SRP_Air_IntlSegment"));
		Thread.sleep(2000);
		mobileCom_Air_Intl_SRP_Filter(driver);
		// driver.navigate().refresh();
		Thread.sleep(2000);
		if (elementVisible(driver, By.xpath("//div/div/div/div/div/div/p[1]/strong"), 20)) {
			String ErrorMsg = getText(driver, By.xpath("//div/div/div/div/div/div/p[1]/strong"));
			// printInfo(ErrorMsg);
			Reporter.log(ErrorMsg, true);
		} /*
		 * List<WebElement> weList=driver.findElements(By.xpath("//*[@class=\"INR\"]"));
		 * weList.get(0).click();
		 */

		if (elementVisible(driver, By.xpath("//ul[@id='topTenRt']/li"), 1)) {
			List<WebElement> we1 = driver.findElements(By.xpath("//ul[contains(@id,'topTenRt')]/li/div[2]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", we1.get(0));
			// we1.get(1).click();
		}
		if (elementVisible(driver, By.xpath("//button[@id='splitResBookBtn']"), 1)) {
			safeClick(driver, By.xpath("//button[@id='splitResBookBtn']"));
		}
	}

	public void mobileCom_SD_Air_IntlSRP_Oneway(RemoteWebDriver driver) throws Exception {
		mobileCom_SD_Air_Intl_SRP_Filter(driver);
		/*
		 * int size = driver.findElements(By.tagName("iframe")).size();
		 * System.out.println("iframe size="+size); Reporter.log("iframe size="+size);
		 * 
		 * if(size==1){ driver.switchTo().frame(0); } else if(size==2){
		 * driver.switchTo().frame(1); } else if(size==3){ driver.switchTo().frame(2); }
		 * else if(size==4){ driver.switchTo().frame(3); }
		 * if(elementPresent(driver,By.id("closeBanner"),2)){
		 * //driver.switchTo().frame(1);
		 * driver.findElement(By.id("closeBanner")).click(); }
		 */

		// elementVisible(driver,getObject("MobileCom_Air_SRP_Air_IntlSegment"), 100);
		// safeClick(driver,getObject("MobileCom_Air_SRP_Air_IntlSegment"));
		// System.out.println("top ten clicking");
		/*
		 * safeClick(driver, By.id("topTen")); //System.out.println("top ten CLICKED");
		 * //safeClick(driver, By.xpath("//*[@id='topTen']/li[1]"));
		 * mobileCom_SD_Air_Intl_SRP_Filter(driver); Thread.sleep(5000);
		 * if(elementVisible(driver, By.xpath("//div/div/div/div/div/div/p[1]/strong"),
		 * 20)){ String ErrorMsg=getText(driver,
		 * By.xpath("//div/div/div/div/div/div/p[1]/strong")); printInfo(ErrorMsg);
		 * Reporter.log(ErrorMsg); }List<WebElement>
		 * weList=driver.findElements(By.xpath("//*[@class=\"INR\"]"));
		 * weList.get(0).click();
		 * if(elementVisible(driver,By.xpath("//button[@id='splitResBookBtn']"),1)){
		 * safeClick(driver,By.xpath("//button[@id='splitResBookBtn']")); }
		 */
	}

	public void mobileCom_Air_SRP_RoundTrip(RemoteWebDriver driver, String carrier1, String carrier2, String carrier3)
			throws Exception {
		mobileCom_Air_SRP_Filter(driver, carrier1, carrier2, carrier3);

		try {
			driver.findElement(By.xpath("//li[@class='item'][1]")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//li[@class='item discounted'][1]")).click();
		}

		mobileCom_Air_SRP_Filter(driver, carrier1, carrier2, carrier3);
		String URL = driver.getCurrentUrl();
		// if(URL.contains(".com"))
		{
			elementVisible(driver, getObject("MobileCom_Air_SRP_Air_Return_Segment"), 1);
			Thread.sleep(1000);
			List<WebElement> we = driver.findElements(By.xpath("//li[contains(@class,'item')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", we.get(2));
			System.out.println(we.size());
		}
		/*
		 * else if(!URL.contains(".com")) { elementVisible(driver,
		 * getObject("MobileCom_Air_SRP_Air_Return_Segment"), 1); Thread.sleep(1000);
		 * List<WebElement> we=driver.findElements(By.xpath("//li[@class='item']"));
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].click();",we.get(1));
		 * System.out.println(we.size()); }
		 */

	}

	public void mobileCom_Air_SRP_RoundTrip1(RemoteWebDriver driver, String carrier1) throws Exception {
		Thread.sleep(9000);
		if (!elementPresent(driver, getObject("MobileCom_Air_SRP_Air_Return_Segment"), 1)) {
		}
		System.out.println(carrier1);
		mobileCom_Air_SRP_Filter1(driver, carrier1);
		String URL = getURL(driver);
		if (URL.contains(".com")) {
			if (elementPresent(driver, By.xpath("//li[@class='item']"), 2)) {
				safeClick(driver, By.xpath("//li[@class='item'][3]"));
			} else {
				safeClick(driver, By.xpath("//li[@class='item discounted'][1]"));
			}
		} else if (!URL.contains(".com")) {
			safeClick(driver, getObject("MobileAE_Air_SRP_RT_Air_Segment"));
		}
		// safeClick(driver, getObject("MobileCom_Air_SRP_RT_Air_Segment"));
		mobileCom_Air_SRP_Filter1(driver, carrier1);
		if (URL.contains(".com")) {
			elementVisible(driver, getObject("MobileCom_Air_SRP_Air_Return_Segment"), 1);
			Thread.sleep(1000);
			if (elementPresent(driver, By.xpath("//li[@class='item'][3]"), 2)) {
				safeClick(driver, By.xpath("//li[@class='item'][3]"));
			} else {
				safeClick(driver, By.xpath("//li[@class='item discounted'][1]"));
			}
			/*
			 * List<WebElement> we=driver.findElements(By.xpath("//li[@class='item'][3]"));
			 * System.out.println("--------------"+we.size()); we.get(2).click();
			 */
			// safeClick(driver,By.xpath("//li[@class='item' and contains(@onclick,'return
			// showProgress')]/li[1]") );
			// safeClick(driver, getObject("MobileCom_Air_SRP_Air_Return_Segment"));

		}
		//
		else if (!URL.contains(".com")) {
			elementVisible(driver, getObject("MobileAE_Air_SRP_Air_Return_Segment"), 100);
			safeClick(driver, getObject("MobileAE_Air_SRP_Air_Return_Segment"));
		}

	}

	public void mobileCom_SD_Air_SRP_RoundTrip(RemoteWebDriver driver, String GoAir, String SpiceJet, String Indigo)
			throws Exception {
		if (!elementPresent(driver, By.xpath("//time[@class='dep']"), 1)) {
			/*
			 * int size = driver.findElements(By.tagName("iframe")).size();
			 * System.out.println("iframe size="+size); Reporter.log("iframe size="+size);
			 * 
			 * if(size==1){ driver.switchTo().frame(0); } else if(size==2){
			 * driver.switchTo().frame(1); } else if(size==3){ driver.switchTo().frame(2); }
			 * else if(size==4){ driver.switchTo().frame(3); }
			 * if(elementPresent(driver,By.id("closeBanner"),2)){
			 * //driver.switchTo().frame(1);
			 * driver.findElement(By.id("closeBanner")).click(); } try{
			 * driver.switchTo().frame(0); driver.findElement(By.id("closeBanner")).click();
			 * } catch(Exception e){
			 * 
			 * }
			 */}
		mobileCom_SD_Air_SRP_Filter(driver, Indigo, SpiceJet, GoAir);

		String URL = getURL(driver);
		List<WebElement> we = driver.findElements(By.xpath("//time[@class='dep']"));
		Test1: for (int i = 0; i < we.size(); i++) {
			//// System.out.println(we.get(i).getText());
			we.get(1).click();
			break Test1;
		}
		// safeClick(driver, By.id("wrap"));

		if (URL.contains(".com")) {
			safeClick(driver, By.xpath("//ul/li[1]"));
		} else if (!URL.contains(".com")) {
			safeClick(driver, getObject("MobileAE_Air_SRP_RT_Air_Segment"));
		}
		// safeClick(driver, getObject("MobileCom_Air_SRP_RT_Air_Segment"));
		// safeClick(driver, By.xpath("//*[@id=wrap]/div[2]/ul/li"));

		// System.out.println("onward flight selected");
		Thread.sleep(5000);

		mobileCom_SD_Air_SRP_Filter(driver, Indigo, SpiceJet, GoAir);

		Thread.sleep(5000);

		List<WebElement> we1 = driver.findElements(By.xpath("//time[@class='dep']"));
		Test: for (int i = 0; i < we1.size(); i++) {
			//// System.out.println(we1.get(i).getText());
			we1.get(2).click();
			break Test;
		}
		// safeClick(driver, By.id("wrap"));
		if (URL.contains(".com")) {

			// elementVisible(driver, getObject("MobileCom_Air_SRP_Air_Return_Segment"),
			// 100);
			// safeClick(driver, getObject("MobileCom_Air_SRP_Air_Return_Segment"));
			elementVisible(driver, By.xpath("//*[@id='wrap']/div[@style='display:block']/ul/li[1]"), 20);
			// safeClick(driver,
			// By.xpath("//*[@id='wrap']/div[@style='display:block']/ul/li[1]"));
			// System.out.println("return flight selected");
		} else if (!URL.contains(".com")) {
			elementVisible(driver, getObject("MobileAE_Air_SRP_Air_Return_Segment"), 100);
			safeClick(driver, getObject("MobileAE_Air_SRP_Air_Return_Segment"));

		}

	}

	public void mobileCom_Air_ReviewItineraryPage(RemoteWebDriver driver, String BookingType) throws Exception {
		if (waitForElementVisibility(driver, By.xpath("//div[contains(@class,'blockMessage bad')]"), 5)) {
			Reporter.log("Create Itinearary Failed!", true);
			Reporter.log("Message: " + getText(driver, By.xpath("//div[contains(@class,'blockMessage bad')]")), true);
			Assert.fail("Create Itinearary Failed!");
		}

		if (elementPresent(driver, By.xpath("//li[@class='item clearfix']"), 1)) {
			driver.findElement(By.xpath("//li[@class='item clearfix']")).click();
		}

		if (elementPresent(driver, By.xpath("//ul[@class='review']/li/p/strong"), 10)) {
			Reporter.log("Airline: " + getText(driver, By.xpath("//ul[@class='review']/li/p/strong")), true);
		}

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//button[@type='submit']"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(getObject("MobileCom_Air_ItineraryPage_Continue_Button"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			safeClick(driver, getObject("MobileCom_Air_ItineraryPage_Continue_Button"));
		}
	}
	public String mobileCom_Air_ReviewItineraryPageTotal(RemoteWebDriver driver, String BookingType) throws Exception {
		if (waitForElementVisibility(driver, By.xpath("//div[contains(@class,'blockMessage bad')]"), 5)) {
			Reporter.log("Create Itinearary Failed!", true);
			Reporter.log("Message: " + getText(driver, By.xpath("//div[contains(@class,'blockMessage bad')]")), true);
			Assert.fail("Create Itinearary Failed!");
		}

		if (elementPresent(driver, By.xpath("//li[@class='item clearfix']"), 1)) {
			driver.findElement(By.xpath("//li[@class='item clearfix']")).click();
		}

		if (elementPresent(driver, By.xpath("//ul[@class='review']/li/p/strong"), 10)) {
			Reporter.log("Airline: " + getText(driver, By.xpath("//ul[@class='review']/li/p/strong")), true);
		}
		String ItiTotal=driver.findElement(By.xpath("//*[text()='Total']//../../*/dd[3]")).getText();
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//button[@type='submit']"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(getObject("MobileCom_Air_ItineraryPage_Continue_Button"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			safeClick(driver, getObject("MobileCom_Air_ItineraryPage_Continue_Button"));
		}
		return ItiTotal;
	}
	public String pwa_Air_ReviewItineraryPage(RemoteWebDriver driver) throws Exception {
		//WebElement element2 = (new WebDriverWait(driver, 60))
		//	.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Baggage')]")));
		// Thread.sleep(6000);
		int timecount = 1;
		if (elementPresent(driver, By.xpath("//*[contains(@class,'Modal')]"), 60)) {
			driver.switchTo().activeElement();
			if (elementPresent(driver, By.xpath("//*[text()='Okay']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Okay']")).click();
			}
			if (elementPresent(driver, By.xpath("//*[text()='Continue']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Continue']")).click();
			}
		}
		/*
		 * WebDriverWait wait = new WebDriverWait(driver,10);
		 * wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("");
		 */
		Thread.sleep(3000);
		waitForElement(driver,20,By.xpath("//*[text()='Total']//..//../*[3]/*[2]"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		//WebElement element1 = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		//js1.executeScript("arguments[0].scrollIntoView(true);", element1);
		String totalinitinerary = driver.findElement(By.xpath("//*[text()='Total']//..//../*[3]/*[2]")).getText();
		waitForElement(driver, 5, By.xpath("//*[text()='Standard fare']"));
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		//js.executeScript("arguments[0].scrollIntoView(true);", element);
		safeClick(driver,By.xpath("//*[text()='Standard fare']"));
		Thread.sleep(1000);
		safeClick(driver, By.xpath("//button[contains(text(),'Continue')]"));
		Thread.sleep(3000);
		if(elementPresent(driver, By.xpath("//button[contains(text(),'Next')]"), 60)) {

			safeClick(driver,By.xpath("//button[contains(text(),'Next')]"));
			safeClick(driver,By.xpath("//button[contains(text(),'Next')]"));
			safeClick(driver,By.xpath("//button[contains(text(),'Done')]"));

			//safeClick(driver, By.xpath("//a[@class='Modal__action Modal__action--primary']"));

		}
		else {

			waitForElement(driver, 5, By.xpath("//span[@class='fs-15 fw-500']"));

		}

		return totalinitinerary.substring(1).replace(",", "");
		//return Airline;


	}



	public void pwa_Air_Bookstep2(RemoteWebDriver driver, String Adult1, String Child1, String infant1, String gst)
			throws Exception {
		int Adult = Integer.parseInt(Adult1);
		int Children = Integer.parseInt(Child1);
		int infant = Integer.parseInt(infant1);
		boolean bpresent = false;
		if (elementPresent(driver, By.xpath("//*[contains(@class,'Modal')]"), 20)) {
			driver.switchTo().activeElement();
			if (elementPresent(driver, By.xpath("//*[text()='Okay']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Okay']")).click();
			}
			if (elementPresent(driver, By.xpath("//*[text()='Continue']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Continue']")).click();
			}
		}
		// elementPresent(driver,getObject("MobileWeb_Trains_In_Travellers_Text"),10);
		WebElement element1 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(getObject("PWATrainsPhone")));

		// waitForElement(driver,10,By.xpath("//*[contains(text(),'Adult')]"));
		for (int i = 1; i < Adult + 1; i++) {
			Thread.sleep(2000);
			if (elementPresent(driver, By.xpath("//*[text()='Adult " + i + "']"), 2)) {
				safeClick(driver, By.xpath("//*[text()='Adult " + i + "']//..//..//../*[2]"));
				bpresent = true;
			}
			Thread.sleep(2000);
			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mr");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_FirstNameR"), "Test");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_LastNameR"), "Test");
			if (elementPresent(driver, getObject("MobileWeb_Trains_In_Travellers_AgeR"), 1)) {
				safeType(driver, getObject("MobileWeb_Trains_In_Travellers_AgeR"), "29");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Date of birth']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Date of birth']//..//../*[2]")).sendKeys("29051985");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport No.']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport No.']//..//../*[2]")).sendKeys("Fk12345");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport issuing country']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport issuing country']//..//../*[2]")).click();
				//safeClick(driver, By.xpath("//*[text()='India']//..//../*[1]"));
				//safeType(driver, By.xpath("//input[@placeholder='Select Country']"), "India");
				/*
				 * Thread.sleep(3000); JavascriptExecutor js = (JavascriptExecutor) driver;
				 * WebElement element = driver.findElement(By.xpath("//li/p[text()='India']"));
				 * js.executeScript("arguments[0].scrollIntoView(true);", element);
				 */
				safeClick(driver, By.xpath("//li/p[text()='India']"));

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport expiry date']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport expiry date']//..//../*[2]")).sendKeys("02012025");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Visa']"), 1)) {
				Select title1 = new Select(driver.findElement(By.name("visa")));
				title1.selectByVisibleText("Business");
				Thread.sleep(2000);

			}
			if(elementPresent(driver,By.xpath("//label[contains(text(),'Nationality')]"),1))
			{
				safeClick(driver,By.xpath("//label[contains(text(),'Nationality')]"));

				safeClick(driver, By.xpath("//li/p[text()='India']"));
			}
			// *[text()='Add New Debit / Credit Card']/parent::*/*[1]
			if (bpresent) {
				safeClick(driver, getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));
			}

		}

		for (int i = 1; i < Children + 1; i++) {
			// System.out.println("//*[text()='Adult "+i+"')]");
			safeClick(driver, By.xpath("//*[text()='Child " + i + "']//..//..//../*[2]"));

			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mstr");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_FirstNameR"), "Test");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_LastNameR"), "Test");
			if (elementPresent(driver, By.xpath("//*[text()='Date of birth']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Date of birth']//..//../*[2]")).sendKeys("05062010");
			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport No.']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport No.']//..//../*[2]")).sendKeys("Fk12345");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport issuing country']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport issuing country']//..//../*[2]")).click();
				//	safeClick(driver, By.xpath("//*[text()='India']//..//../*[1]']"));
				//safeType(driver, By.xpath("//input[@id='autocomplete-30530']"), "India");
				/*
				 * Thread.sleep(3000); JavascriptExecutor js = (JavascriptExecutor) driver;
				 * WebElement element = driver.findElement(By.xpath("//li/p[text()='India']"));
				 * js.executeScript("arguments[0].scrollIntoView(true);", element);
				 */
				safeClick(driver, By.xpath("//li/p[text()='India']"));

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport expiry date']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport expiry date']//..//../*[2]")).sendKeys("02012025");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Visa']"), 1)) {
				Select title1 = new Select(driver.findElement(By.name("visa")));
				title1.selectByVisibleText("Business");
				Thread.sleep(2000);

			}
			safeClick(driver, By.xpath("//button[text()='Continue booking']"));
			//safeClick(driver, getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));

		}
		for (int i = 1; i < infant + 1; i++) {
			// System.out.println("//*[text()='Adult "+i+"')]");
			safeClick(driver, By.xpath("//*[text()='Infant " + i + "']//..//..//../*[2]"));

			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mstr");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_FirstNameR"), "Test");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_LastNameR"), "Test");
			if (elementPresent(driver, By.xpath("//*[text()='Date of birth']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Date of birth']//..//../*[2]")).sendKeys("05062017");
			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport No.']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport No.']//..//../*[2]")).sendKeys("Fk12345");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport issuing country']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport issuing country']//..//../*[2]")).click();
				//	safeType(driver, By.xpath("//input[@placeholder='Select Country']"), "India");
				/*
				 * Thread.sleep(3000); JavascriptExecutor js = (JavascriptExecutor) driver;
				 * WebElement element = driver.findElement(By.xpath("//li/p[text()='India']"));
				 * js.executeScript("arguments[0].scrollIntoView(true);", element);
				 */
				safeClick(driver, By.xpath("//li/p[text()='India']"));

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport expiry date']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport expiry date']//..//../*[2]")).sendKeys("02012025");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Visa']"), 1)) {
				Select title1 = new Select(driver.findElement(By.name("visa")));
				title1.selectByVisibleText("Business");
				Thread.sleep(2000);

			}
			safeClick(driver, By.xpath("//button[text()='Continue booking']"));
			//safeClick(driver, getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));
		}
		elementPresent(driver, getObject("MobileWeb_Trains_In_Travellers_Text"), 10);
		if (gst.equalsIgnoreCase("gst")) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(getObject("PWATrainsGSTTab"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			safeClick(driver, getObject("PWATrainsGSTTab"));
			elementPresent(driver, getObject("PWAAirGSTNumber"), 10);
			safeType(driver, getObject("PWAAirGSTNumber"), "22AAAAA0000A1Z5");
			safeType(driver, getObject("PWAAirGSTHolderName"), "cleartrip");
			safeType(driver, getObject("PWAAirGSTCity"), "Bangalore");
			safeClick(driver, getObject("PWAAirGSTContinue"));

		}
		//Yashmin -VAT Booking Code
		if(isElementVisible(driver,By.xpath("//p[@class='fs-15']")))

		{
			System.out.println("VAt details found");
			safeClick(driver,By.xpath("//p[@class='fs-15']"));

			if(elementPresent(driver,By.xpath("//div[@class='Sticky__fake']")))
			{
				driver.findElement(By.xpath("//input[@name='number']")).sendKeys("12345678956");
				driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Cleartrip");
				driver.findElement(By.xpath("//input[@name='address']")).sendKeys("JpNagar");
				driver.findElement(By.xpath("//button[@class='Button Button--full Button--secondary']")).click();
			}
		}



		elementPresent(driver, getObject("MobileWeb_Trains_In_Travellers_Text"), 10);
		if (elementPresent(driver, getObject("PWATrainsPhone"), 10)) {
			safeType(driver, getObject("PWATrainsPhone"), "1234567890");

		}
		// System.out.println("---------"+driver.findElement(By.xpath("//input[@name='email']")).getAttribute("disabled"));
		if ((driver.findElement(getObject("PWATrainsEMail")).getAttribute("disabled")) == null) {
			Thread.sleep(2000);
			safeType(driver, getObject("PWATrainsEMail"), "jitendra.ha@cleartrip.com");
		}
		if (elementPresent(driver, By.xpath("//*[text()='I have a Cleartrip account']"), 1)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[text()='I have a Cleartrip account']/parent::*"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			driver.findElement(By.xpath("//*[text()='I have a Cleartrip account']/parent::*")).click();
			if (elementPresent(driver, By.xpath("//*[text()='Password']"))) {
				safeType(driver, By.xpath("//*[text()='Password']//..//../*[2]"), "9663806628");
			}
		}
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(getObject("MobileWeb_Air_In_Travellers_Details_Continue_ButtonR"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		safeClick(driver, getObject("MobileWeb_Air_In_Travellers_Details_Continue_ButtonR"));*/
		safeClick(driver,By.xpath("//button[@class='Button Button--full Button--primary']"));
		System.out.println("Continue button on boostep2 is clicked");

	}

	//Yashmin -Unsigned bookstep2 Code
	public void pwa_MEAir_Unsigned_Bookstep2(RemoteWebDriver driver, String Adult1, String Child1, String infant1, String gst)
			throws Exception {
		int Adult = Integer.parseInt(Adult1);
		int Children = Integer.parseInt(Child1);
		int infant = Integer.parseInt(infant1);
		boolean bpresent = false;
		if (elementPresent(driver, By.xpath("//*[contains(@class,'Modal')]"), 20)) {
			driver.switchTo().activeElement();
			if (elementPresent(driver, By.xpath("//*[text()='Okay']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Okay']")).click();
			}
			if (elementPresent(driver, By.xpath("//*[text()='Continue']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Continue']")).click();
			}
		}
		// elementPresent(driver,getObject("MobileWeb_Trains_In_Travellers_Text"),10);
		WebElement element1 = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(getObject("PWATrainsPhone")));

		// waitForElement(driver,10,By.xpath("//*[contains(text(),'Adult')]"));
		for (int i = 1; i < Adult + 1; i++) {
			Thread.sleep(2000);
			if (elementPresent(driver, By.xpath("//*[text()='Adult " + i + "']"), 2)) {
				safeClick(driver, By.xpath("//*[text()='Adult " + i + "']//..//..//../*[2]"));
				bpresent = true;
			}
			Thread.sleep(2000);
			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mr");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_FirstNameR"), "Test");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_LastNameR"), "Test");
			if (elementPresent(driver, getObject("MobileWeb_Trains_In_Travellers_AgeR"), 1)) {
				safeType(driver, getObject("MobileWeb_Trains_In_Travellers_AgeR"), "29");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Date of birth']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Date of birth']//..//../*[2]")).sendKeys("29051985");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport No.']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport No.']//..//../*[2]")).sendKeys("Fk12345");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport issuing country']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport issuing country']//..//../*[2]")).click();
				//safeClick(driver, By.xpath("//*[text()='India']//..//../*[1]"));
				//safeType(driver, By.xpath("//input[@placeholder='Select Country']"), "India");
				/*
				 * Thread.sleep(3000); JavascriptExecutor js = (JavascriptExecutor) driver;
				 * WebElement element = driver.findElement(By.xpath("//li/p[text()='India']"));
				 * js.executeScript("arguments[0].scrollIntoView(true);", element);
				 */
				safeClick(driver, By.xpath("//li/p[text()='India']"));

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport expiry date']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport expiry date']//..//../*[2]")).sendKeys("02012025");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Visa']"), 1)) {
				Select title1 = new Select(driver.findElement(By.name("visa")));
				title1.selectByVisibleText("Business");
				Thread.sleep(2000);

			}
			if(elementPresent(driver,By.xpath("//label[contains(text(),'Nationality')]"),1))
			{
				safeClick(driver,By.xpath("//label[contains(text(),'Nationality')]"));
				//safeType(driver,By.xpath("//label[contains(text(),'Nationality')]"),"India");
				//safeType(driver, By.xpath("//div[@class='Sticky Sticky--top']/div[2]"),"India");
				//	safeType(driver,By.xpath("//div[@class='Sticky__fake']/div[2]"),"India");
				//driver.findElement(By.xpath("//div[@class='Sticky Sticky--top']/div[2]")).sendKeys("India");
				//safeType(driver,By.xpath("//div[@class='Sticky Sticky--top']/div[2]"),India);
				safeClick(driver, By.xpath("//li/p[text()='India']"));
			}
			//			else{
			//				safeType(driver,By.xpath("//div[@class='Sticky__fake']/div[2]"),"India");
			//			}
			// *[text()='Add New Debit / Credit Card']/parent::*/*[1]
			if (bpresent) {
				safeClick(driver, getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));
			}

		}

		for (int i = 1; i < Children + 1; i++) {
			// System.out.println("//*[text()='Adult "+i+"')]");
			safeClick(driver, By.xpath("//*[text()='Child " + i + "']//..//..//../*[2]"));

			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mstr");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_FirstNameR"), "Test");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_LastNameR"), "Test");
			if (elementPresent(driver, By.xpath("//*[text()='Date of birth']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Date of birth']//..//../*[2]")).sendKeys("05062010");
			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport No.']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport No.']//..//../*[2]")).sendKeys("Fk12345");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport issuing country']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport issuing country']//..//../*[2]")).click();
				//	safeClick(driver, By.xpath("//*[text()='India']//..//../*[1]']"));
				//safeType(driver, By.xpath("//input[@id='autocomplete-30530']"), "India");
				/*
				 * Thread.sleep(3000); JavascriptExecutor js = (JavascriptExecutor) driver;
				 * WebElement element = driver.findElement(By.xpath("//li/p[text()='India']"));
				 * js.executeScript("arguments[0].scrollIntoView(true);", element);
				 */
				safeClick(driver, By.xpath("//li/p[text()='India']"));

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport expiry date']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport expiry date']//..//../*[2]")).sendKeys("02012025");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Visa']"), 1)) {
				Select title1 = new Select(driver.findElement(By.name("visa")));
				title1.selectByVisibleText("Business");
				Thread.sleep(2000);

			}
			safeClick(driver, By.xpath("//button[text()='Continue booking']"));
			//safeClick(driver, getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));

		}
		for (int i = 1; i < infant + 1; i++) {
			// System.out.println("//*[text()='Adult "+i+"')]");
			safeClick(driver, By.xpath("//*[text()='Infant " + i + "']//..//..//../*[2]"));

			Select title = new Select(driver.findElement(By.name("title")));
			title.selectByVisibleText("Mstr");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_FirstNameR"), "Test");
			safeType(driver, getObject("MobileWeb_Trains_In_Travellers_LastNameR"), "Test");
			if (elementPresent(driver, By.xpath("//*[text()='Date of birth']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Date of birth']//..//../*[2]")).sendKeys("05062017");
			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport No.']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport No.']//..//../*[2]")).sendKeys("Fk12345");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport issuing country']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport issuing country']//..//../*[2]")).click();
				//	safeType(driver, By.xpath("//input[@placeholder='Select Country']"), "India");
				/*
				 * Thread.sleep(3000); JavascriptExecutor js = (JavascriptExecutor) driver;
				 * WebElement element = driver.findElement(By.xpath("//li/p[text()='India']"));
				 * js.executeScript("arguments[0].scrollIntoView(true);", element);
				 */
				safeClick(driver, By.xpath("//li/p[text()='India']"));

			}
			if (elementPresent(driver, By.xpath("//*[text()='Passport expiry date']"), 1)) {
				driver.findElement(By.xpath("//*[text()='Passport expiry date']//..//../*[2]")).sendKeys("02012025");
				// safeType(driver,By.xpath("//*[text()='DOB']//..//../*[2]"),"29051985");

			}
			if (elementPresent(driver, By.xpath("//*[text()='Visa']"), 1)) {
				Select title1 = new Select(driver.findElement(By.name("visa")));
				title1.selectByVisibleText("Business");
				Thread.sleep(2000);

			}
			safeClick(driver, By.xpath("//button[text()='Continue booking']"));
			//safeClick(driver, getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));
		}
		elementPresent(driver, getObject("MobileWeb_Trains_In_Travellers_Text"), 10);
		if (gst.equalsIgnoreCase("gst")) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(getObject("PWATrainsGSTTab"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			safeClick(driver, getObject("PWATrainsGSTTab"));
			elementPresent(driver, getObject("PWAAirGSTNumber"), 10);
			safeType(driver, getObject("PWAAirGSTNumber"), "22AAAAA0000A1Z5");
			safeType(driver, getObject("PWAAirGSTHolderName"), "cleartrip");
			safeType(driver, getObject("PWAAirGSTCity"), "Bangalore");
			safeClick(driver, getObject("PWAAirGSTContinue"));
			// safeClick(driver,By.xpath("//p[contains(text(),'Use GSTIN for this
			// booking')]"));
			// elementPresent(driver,By.xpath("//p[contains(text(),'GST Number')]"),10);
			// safeType(driver,By.xpath("//p[contains(text(),'GST
			// Number')]//..//../*[1]/*[2]"),"22AAAAA0000A1Z5");
			// safeType(driver,By.xpath("//p[contains(text(),'Holder
			// Namer')]//..//../*[1]/*[2]"),"cleartrip");
			// safeType(driver,By.xpath("//p[contains(text(),'Holder
			// Address')]//..//../*[1]/*[2]"),"Bangalore");
			// safeClick(driver,By.xpath("//button[contains(text(),'Continue Booking')]"));
		}

		/*if (gst.equalsIgnoreCase("gst")) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(getObject("PWATrainsGSTTab"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			safeClick(driver, getObject("PWATrainsGSTTab"));
			elementPresent(driver, getObject("PWAAirGSTNumber"), 10);
			safeType(driver, getObject("PWAAirGSTNumber"), "22AAAAA0000A1Z5");
			safeType(driver, getObject("PWAAirGSTHolderName"), "cleartrip");
			safeType(driver, getObject("PWAAirGSTCity"), "Bangalore");
			safeClick(driver, getObject("PWAAirGSTContinue"));
			// safeClick(driver,By.xpath("//p[contains(text(),'Use GSTIN for this
			// booking')]"));
			// elementPresent(driver,By.xpath("//p[contains(text(),'GST Number')]"),10);
			// safeType(driver,By.xpath("//p[contains(text(),'GST
			// Number')]//..//../*[1]/*[2]"),"22AAAAA0000A1Z5");
			// safeType(driver,By.xpath("//p[contains(text(),'Holder
			// Namer')]//..//../*[1]/*[2]"),"cleartrip");
			// safeType(driver,By.xpath("//p[contains(text(),'Holder
			// Address')]//..//../*[1]/*[2]"),"Bangalore");
			// safeClick(driver,By.xpath("//button[contains(text(),'Continue Booking')]"));
		}*/


		elementPresent(driver, getObject("MobileWeb_Trains_In_Travellers_Text"), 10);
		if (elementPresent(driver, getObject("PWATrainsPhone"), 10)) {
			safeType(driver, getObject("PWATrainsPhone"), "1234567890");

		}
		// System.out.println("---------"+driver.findElement(By.xpath("//input[@name='email']")).getAttribute("disabled"));
		if ((driver.findElement(getObject("PWATrainsEMail")).getAttribute("disabled")) == null) {
			Thread.sleep(2000);
			safeType(driver, getObject("PWATrainsEMail"), "yashmin.b@cleartrip.com");
		}
		if (elementPresent(driver, By.xpath("//*[text()='I have a Cleartrip account']"), 1)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[text()='I have a Cleartrip account']/parent::*"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			//driver.findElement(By.xpath("//*[text()='I have a Cleartrip account']/parent::*")).click();
			/*if (elementPresent(driver, By.xpath("//*[text()='Password']"))) {
				safeType(driver, By.xpath("//*[text()='Password']//..//../*[2]"), "9663806628");
			}*/
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(getObject("MobileWeb_Air_In_Travellers_Details_Continue_ButtonR"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		safeClick(driver, getObject("MobileWeb_Air_In_Travellers_Details_Continue_ButtonR"));

	}
	public void checkAPNS(RemoteWebDriver driver) throws Exception {
		WebElement element1 = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(getObject("PWATrainsPhone")));
		Assert.assertEquals(getText(driver, By.xpath("//*[text()='Title']")), "Title");
		Assert.assertEquals(getText(driver, By.xpath("//*[text()='First name']")), "First name");
		Assert.assertEquals(getText(driver, By.xpath("//*[text()='Last name']")), "Last name");
		Assert.assertEquals(getText(driver, By.xpath("//*[text()='Passport No.']")), "Passport No.");
		Assert.assertEquals(getText(driver, By.xpath("//*[text()='Passport expiry date']")), "Passport expiry date");
		Assert.assertEquals(getText(driver, By.xpath("//*[text()='Passport issuing country']")),
				"Passport issuing country");
		Assert.assertEquals(getText(driver, By.xpath("//*[text()='Visa']")), "Visa");
		Assert.assertEquals(getText(driver, By.xpath("//*[text()='Mobile']")), "Mobile");
		Assert.assertEquals(getText(driver, By.xpath("//*[text()='Email']")), "Email");
		Assert.assertEquals(getText(driver, By.xpath("//p[contains(text(),'Use GSTIN for this booking')]")),
				"Use GSTIN for this booking (optional)");
		// p[contains(text(),'Use GSTIN for this booking')]

	}

	public String checkPGFee(RemoteWebDriver driver) throws Exception {

		Thread.sleep(5000);
		WebElement element1 = (new WebDriverWait(driver, 90))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Card')]")));
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//WebElement element = driver.findElement(By.xpath("//*[text()='Total booking amount']//../*[2]"));
		//js.executeScript("arguments[0].scrollIntoView(true);", element);
		String pgfees = driver.findElement(By.xpath("//div//p[contains(.,'per traveller included')]")).getText();
		System.out.println("pgfees=" + pgfees.split(" ")[1]);
		return pgfees.split(" ")[1];
	}



	/*yashmin */
	public String pwa_MEAir_ReviewItineraryPage(RemoteWebDriver driver) throws Exception {

		int timecount = 1;
		if (elementPresent(driver, By.xpath("//*[contains(@class,'Modal')]"), 60)) {
			driver.switchTo().activeElement();
			if (elementPresent(driver, By.xpath("//*[text()='Okay']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Okay']")).click();
			}
			if (elementPresent(driver, By.xpath("//*[text()='Continue']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Continue']")).click();
			}
		}

		Thread.sleep(3000);
		waitForElement(driver,20,By.xpath("//*[text()='Total']//..//../*[3]/*[2]"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		/*Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='coupon']")).sendKeys("DOMOW");
		driver.findElement(By.xpath("//p[@class='fs-15 c-blue']"));*/
		//String totalinitinerary = driver.findElement(By.xpath("//*[text()='Total']//..//../*[3]/*[2]")).getText();


		String totalinitinerary = driver.findElement(By.xpath("//span[@class='fs-14 fw-400 c-black-50']")).getText();
		System.out.println("Itinerary Price"+ totalinitinerary);
		safeClick(driver, By.xpath("//button[contains(text(),'Continue')]"));
		Thread.sleep(3000);
		//safeClick(driver,By.xpath("//button[text()='Skip this step']"));
		return totalinitinerary.substring(1).replace(",", "");


	}

	public String pwa_MEAir_ReviewItineraryCoupon(RemoteWebDriver driver,String coupon) throws Exception {

		int timecount = 1;
		if (elementPresent(driver, By.xpath("//*[contains(@class,'Modal')]"), 60)) {
			driver.switchTo().activeElement();

			if (elementPresent(driver, By.xpath("//*[text()='Okay']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Okay']")).click();
			}
			if (elementPresent(driver, By.xpath("//*[text()='Continue']"), 20)) {
				driver.findElement(By.xpath("//*[text()='Continue']")).click();
			}
		}

		Thread.sleep(3000);
		waitForElement(driver,20,By.xpath("//*[text()='Total']//..//../*[3]/*[2]"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;


		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='coupon']")).sendKeys(coupon);
		driver.findElement(By.xpath("//li/p[contains(text(),'Apply')]")).click();

		String totalinitinerary = driver.findElement(By.xpath("//span[@class='fs-14 fw-400 c-black-50']")).getText();

		//System.out.println("Applied coupon" + driver.findElement(By.xpath("//p/span[contains(text(),'Great')]")).getText());



		if(isElementVisible(driver,By.xpath("//p[@class='Modal__copy']")))
		{
			System .out.println("Invalid coupon getting in if block");
			driver.switchTo().activeElement();
			safeClick(driver,By.xpath("//a[@class='Modal__action Modal__action--primary']"));
		}

		Thread.sleep(3000);

		safeClick(driver, By.xpath("//button[@class='Button Button--full Button--primary']"));

		Thread.sleep(3000);

		return totalinitinerary.substring(1).replace(",", "");


	}
	/*yashmin */
	public void pwa_MEAir_MakePayment(RemoteWebDriver driver, String Payment_Type, String Trip_Logger_Msg,
			String Booking_Confirmation_Text) throws Exception {
		Thread.sleep(5000);
		if (!waitForElementVisibility(driver, By.xpath("//*[contains(text(),'Card')]"), 120)) {
			Reporter.log("Payment Page not loaded in 120 seconds.", true);
			Assert.fail("Test case faied!");
		}


		String TripID = null;
		Thread.sleep(5000);

		if (Payment_Type == "CREDITCARD") {
			//Thread.sleep(5000);
			safeClick(driver, By.xpath("//p[contains(text(),'Card')]"));
			if (elementPresent(driver, By.xpath("//*[text()='Add New Debit / Credit Card']"), 10)) {
				safeClick(driver, By.xpath("//*[text()='Add New Debit / Credit Card']/parent::*/*[1]"));
			}


			safeType(driver, getObject("MobileWeb_Trains_In_Payment_CardNoR"), dataFile.value("CC_MasterCard_Number"));
			safeType(driver, getObject("MobileWeb_Trains_In_Payment_ExpiryDateR"), "0520");

			safeType(driver, getObject("MobileWeb_Trains_In_Payment_CVVR"), dataFile.value("CC_MasterCard_CVV"));
			JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement element = driver.findElement(By.xpath("//button[text()='Make payment']"));
			element.click();



			waitForElement(driver, 90, By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));


			if (elementPresent(driver, By.xpath("//*[@class='branch-banner-close']"), 10)) {
				safeClick(driver, By.xpath("//*[@class='branch-banner-close']"));
			}



			String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
			Reporter.log("Trip ID=" + tripID, true);}



	} 
	public void pwa_Air_MakePayment(RemoteWebDriver driver, String Payment_Type, String Trip_Logger_Msg,
			String Booking_Confirmation_Text) throws Exception {
		Thread.sleep(5000);
		if (!waitForElementVisibility(driver, By.xpath("//p[contains(text(),'Payment')]"), 120)) {
			Reporter.log("Payment Page not loaded in 120 seconds.", true);
			Assert.fail("Test case faied!");
		}


		//WebElement element1 = (new WebDriverWait(driver, 90))
		//.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Card')]")));
		// logMessagePageNotLoaded(driver,
		// getObject("HotelCom_BookStep4_CreditCard_Tab"), 30, "Payment Step has not
		// loaded");
		String TripID = null;
		Thread.sleep(5000);
		// waitForElement(driver,10,By.xpath("//p[(text(),'Card')]"));	
		if (Payment_Type == "CREDITCARD") {
			//Thread.sleep(5000);
			safeClick(driver, By.xpath("//p[contains(text(),'Card')]"));
			if (elementPresent(driver, By.xpath("//*[text()='Add New Debit / Credit Card']"), 10)) {
				safeClick(driver, By.xpath("//*[text()='Add New Debit / Credit Card']/parent::*/*[1]"));
			}

			// safeClick(driver,getObject("TrainsCCLink"));
			safeType(driver, getObject("MobileWeb_Trains_In_Payment_CardNoR"), dataFile.value("CC_MasterCard_Number"));
			safeType(driver, getObject("MobileWeb_Trains_In_Payment_ExpiryDateR"), "0520");

			/*
			 * safeSelect(driver,getObject("CC_MasterCard_Exp_Month"),dataFile.value(
			 * "CC_MasterCard_Exp_Month"));
			 * safeSelect(driver,getObject("CC_MasterCard_Exp_Year"),dataFile.value(
			 * "CC_MasterCard_Exp_Year"));
			 * safeType(driver,getObject("CC_MasterCard_HolderName"),dataFile.value(
			 * "CC_MasterCard_HolderName"));
			 */
			safeType(driver, getObject("MobileWeb_Trains_In_Payment_CVVR"), dataFile.value("CC_MasterCard_CVV"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//button[text()='Make payment']"));
			//js.executeScript("arguments[0].scrollIntoView(true);", element);
			if ((common.value("makePayment").equals("true"))) {
				safeClick(driver, getObject("MobileWeb_Trains_In_Payment_ButtonR"));

				// mobileTrains_paymentIntersitialPage(driver);
				//waitForElement(driver, 20, By.xpath("//input[@type='submit']"));
				//Thread.sleep(5000);				
				//safeClick(driver, By.xpath("//input[@type='submit']"));
				waitForElement(driver, 10, getObject("air_amex_payment_button"));
				Thread.sleep(3000);
				//safeClick(driver, getObject("air_amex_payment_button"));
				//Thread.sleep(5000);
				String url = driver.getCurrentUrl();
				System.out.println("---------" + url.split("itinerary")[1]);
				String itiappend = url.split("itinerary")[1];

				// if(common.value("pwaairurl").contains("http://192")) {
				// driver.get("http://192.168.45.202:3000/m/v2/flights/itinerary"+itiappend);
				// waitForElement(driver,10,By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
				// String tripID=driver.findElement(By.xpath("//p[text()='Trip
				// ID']/parent::*/*[2]")).getText();
				// Reporter.log("Trip ID="+tripID,true);
				// }
				// else if(common.value("pwaairurl").contains("https://devpwa")){
				// driver.get("https://devpwa.cleartrip.com/m/v2/flights/itinerary"+itiappend);
				// waitForElement(driver,10,By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
				// String tripID=driver.findElement(By.xpath("//p[text()='Trip
				// ID']/parent::*/*[2]")).getText();
				// Reporter.log("Trip ID="+tripID,true);
				// }
				// else {
				// driver.get("https://qa2.cleartrip.com/m/v2/flights/itinerary"+itiappend);
				// waitForElement(driver,10,By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
				// String tripID=driver.findElement(By.xpath("//p[text()='Trip
				// ID']/parent::*/*[2]")).getText();
				// Reporter.log("Trip ID="+tripID,true);
				// }

				/*
				 * String url=driver.getCurrentUrl();
				 * System.out.println("---------"+url.split("itinerary")[1]); String
				 * itiappend=url.split("itinerary")[1];
				 * driver.get("http://192.168.45.202:3000/m/v2/flights/itinerary"+itiappend);
				 */
				// waitForElement(driver,10,By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
				// String tripID=driver.findElement(By.xpath("//p[text()='Trip
				// ID']/parent::*/*[2]")).getText();
				// Reporter.log("Trip ID="+tripID,true);

				waitForElement(driver, 90, By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));


				if (elementPresent(driver, By.xpath("//*[@class='branch-banner-close']"), 10)) {
					safeClick(driver, By.xpath("//*[@class='branch-banner-close']"));
				}


				String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
				Reporter.log("Trip ID=" + tripID, true);

			}
		} else if (Payment_Type == "amex") {
			if (elementPresent(driver, By.xpath("//*[text()='Add New Debit / Credit Card']"), 10)) {
				safeClick(driver, By.xpath("//*[text()='Add New Debit / Credit Card']/parent::*/*[1]"));
			}
			safeClick(driver, By.xpath("//p[contains(text(),'Card')]"));
			// safeClick(driver,getObject("TrainsCCLink"));
			safeType(driver, getObject("MobileWeb_Trains_In_Payment_CardNoR"), dataFile.value("AmexCard_Number_Hotel"));
			safeType(driver, getObject("MobileWeb_Trains_In_Payment_ExpiryDateR"), "0521");

			/*
			 * safeSelect(driver,getObject("CC_MasterCard_Exp_Month"),dataFile.value(
			 * "CC_MasterCard_Exp_Month"));
			 * safeSelect(driver,getObject("CC_MasterCard_Exp_Year"),dataFile.value(
			 * "CC_MasterCard_Exp_Year"));
			 * safeType(driver,getObject("CC_MasterCard_HolderName"),dataFile.value(
			 * "CC_MasterCard_HolderName"));
			 */
			safeType(driver, getObject("MobileWeb_Trains_In_Payment_CVVR"), "1234");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//button[text()='Make payment']"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			//if ((common.value("makePayment").equals("true"))) {
			safeClick(driver, getObject("MobileWeb_Trains_In_Payment_ButtonR"));

			// mobileTrains_paymentIntersitialPage(driver);
			waitForElement(driver, 10, getObject("air_amex_payment_button"));
			Thread.sleep(3000);
			safeClick(driver, getObject("air_amex_payment_button"));
			Thread.sleep(5000);
			String url = driver.getCurrentUrl();
			System.out.println("---------" + url.split("itinerary")[1]);
			String itiappend = url.split("itinerary")[1];
			if (common.value("pwaairurl").contains("http://192")) {
				driver.get("http://192.168.45.202:3000/m/v2/flights/itinerary" + itiappend);
				waitForElement(driver, 10, By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
				String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
				Reporter.log("Trip ID=" + tripID, true);
			} else if (common.value("pwaairurl").contains("https://devpwa")) {
				driver.get("https://devpwa.cleartrip.com/m/v2/flights/itinerary" + itiappend);
				waitForElement(driver, 10, By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
				String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
				Reporter.log("Trip ID=" + tripID, true);
			} else {
				driver.get("https://qa2.cleartrip.com/m/flights/itinerary" + itiappend);
				waitForElement(driver, 10, By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
				String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
				Reporter.log("Trip ID=" + tripID, true);
			}

		}


		else if (Payment_Type.equalsIgnoreCase("Net Banking")) {
			// waitForElement(driver,10,By.xpath("//input[contains(text(),'Search
			// banks')]"));
			// waitElement(driver, getObject("trainsNetBanking_Tab"), 10);

			safeClick(driver, getObject("MobileWeb_Trains_In_Payment_NetBanking_ButtonR"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Thread.sleep(2000);
			WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Choose another bank')]"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			safeClick(driver, getObject("MobileWeb_Trains_In_Payment_SearchOtherBankR"));
			Thread.sleep(1000);
			waitForElement(driver, 10, getObject("MobileWeb_Trains_AutocompleteR"));
			//State Bank of India bank of india
			Thread.sleep(3000);
			driver.switchTo().activeElement();

			//driver.findElement(By.xpath("//div[@class='pwa-bank--search-wrapper bg-white']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='pwa-bank--search-wrapper bg-white']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@class='fs-3 cardFadeIn']")).sendKeys("ICICI Bank");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//p[contains(text(),'ICICI Bank')]")).click();
			//	safeType(driver, getObject("MobileWeb_Trains_AutocompleteR"), "ICICI Bank");
			List<WebElement> we = driver.findElements(By.xpath("//li/p"));
			for (int i = 1; i < we.size(); i++) {
				System.out.println("-------------------------------" + we.get(i).getText());
				if (we.get(i).getText().equalsIgnoreCase("ICICI Bank")) {

					we.get(i).click();

					break;
				}

				Thread.sleep(2000);
			}
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", driver.findElement(By.xpath("//p[contains(text(),'Pay now')]")));
			Thread.sleep(3000);


			//	safeClick(driver, By.xpath("//p[contains(text(),'Pay now')]"));
			//Booking_Confirmation_TexsafeClick(driver, By.linkText("Return to Biller Site"));
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[contains(text(),'Success')]")).click();
			waitForElement(driver, 50, By.xpath("//h5[contains(text(),'Booking confirmed')]"));
			Thread.sleep(5000);
			String url = driver.getCurrentUrl();
			System.out.println("---------" + url.split("itinerary")[1]);
			waitForElement(driver, 10, By.xpath("//h5[contains(text(),'Booking confirmed')]"));
			if(driver.findElement(By.xpath("//h5[contains(text(),'Booking confirmed')]")).isDisplayed())
			{
				System.out.println("Payment is Success and booking is confirmed");
			}
			else
			{
				System.out.println("payment failed.");
			}
			/*String url = driver.getCurrentUrl();
			System.out.println("---------" + url.split("itinerary")[100]);
			String itiappend = url.split("itinerary")[1].replaceAll("info", "payment-retry");
			System.out.println(itiappend);
			if (common.value("pwaairurl").contains("http://192")) {
				driver.get("http://192.168.45.202:3000/m/v2/flights/itinerary" + itiappend);
				if (elementPresent(driver, By.xpath("//*[contains(@class,'Modal')]"), 20)) {
					driver.switchTo().activeElement();
					driver.findElement(By.xpath("//*[text()='Retry']")).click();
				}
			} else if (common.value("pwaairurl").contains("https://devpwa")) {
				driver.get("https://devpwa.cleartrip.com/m/v2/flights/itinerary" + itiappend);
				if (elementPresent(driver, By.xpath("//*[contains(@class,'Modal')]"), 20)) {
					driver.switchTo().activeElement();
					driver.findElement(By.xpath("//*[text()='Retry']")).click();
				}
			} else {

				driver.get("https://qa2.cleartrip.com/m/flights/itinerary" + itiappend);
				if (elementPresent(driver, By.xpath("//*[contains(@class,'Modal')]"), 20)) {
					driver.switchTo().activeElement();
					driver.findElement(By.xpath("//*[text()='Retry']")).click();
				}
			 
		}
*/	}

	else if (Payment_Type == "storedcard") {
		ArrayList al = new ArrayList();
		List<WebElement> we = driver
				.findElements(By.xpath("//li[@role='menuitem'  and contains(@class,'RadioList')]"));
		for (int i = 0; i < we.size() - 1; i++) {
			if (we.get(i).getText().equals("5123 45XX XXXX 2346")) {
				we.get(i).click();
			}
			System.out.println(we.get(i).getText());
			al.add(we.get(i).getText());
		}
		//Assert.assertEquals(true, al.contains("3456 78XX XXXX 007"));
		//Assert.assertEquals(true, al.contains("5123 45XX XXXX 2346"));
		safeType(driver, getObject("MobileWeb_Trains_In_Payment_CVVR"), dataFile.value("CC_MasterCard_CVV"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//button[text()='Make payment']"));
		safeClick(driver, getObject("MobileWeb_Trains_In_Payment_ButtonR"));
		//js.executeScript("arguments[0].scrollIntoView(true);", element);
		waitForElement(driver, 20, getObject("air_amex_payment_button"));
		Thread.sleep(3000);
		//safeClick(driver, getObject("air_amex_payment_button"));
		//Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		System.out.println("---------" + url.split("itinerary")[1]);
		String itiappend = url.split("itinerary")[1];
		waitForElement(driver, 90, By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));

		if (elementPresent(driver, By.xpath("//*[@class='branch-banner-close']"), 10)) {
			safeClick(driver, By.xpath("//*[@class='branch-banner-close']"));
		}

		String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
		Reporter.log("Trip ID=" + tripID, true);


		//if ((common.value("makePayment").equals("true"))) {
		//safeClick(driver, getObject("MobileWeb_Trains_In_Payment_ButtonR"));

		// mobileTrains_paymentIntersitialPage(driver);
		//waitForElement(driver, 10, getObject("air_amex_payment_button"));
		//Thread.sleep(3000);
		//safeClick(driver, getObject("air_amex_payment_button"));
		//Thread.sleep(5000);
		//String url = driver.getCurrentUrl();
		//System.out.println("---------" + url.split("itinerary")[1]);
		//String itiappend = url.split("itinerary")[1];
		//if (common.value("pwaairurl").contains("http://192")) {
		//	driver.get("http://192.168.45.202:3000/m/v2/flights/itinerary" + itiappend);
		//	waitForElement(driver, 10, By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
		//	String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
		//	Reporter.log("Trip ID=" + tripID, true);
		//} else if (common.value("pwaairurl").contains("https://devpwa")) {
		//	driver.get("https://devpwa.cleartrip.com/m/v2/flights/itinerary" + itiappend);
		//	waitForElement(driver, 10, By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
		//	String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
		//	Reporter.log("Trip ID=" + tripID, true);
		//} else {
		//	driver.get("https://qa2.cleartrip.com/m/flights/itinerary" + itiappend);
		//	waitForElement(driver, 10, By.xpath("//p[text()='Trip ID']/parent::*/*[2]"));
		//	String tripID = driver.findElement(By.xpath("//p[text()='Trip ID']/parent::*/*[2]")).getText();
		//	Reporter.log("Trip ID=" + tripID, true);
		//}
		//}

		// System.out.println("----------------------------------"+al.contains("3456
		// 78XX XXXX 007"));
	}

	// return TripID;

}

public void mobileCom_SD_Air_ReviewItineraryPage(RemoteWebDriver driver, String BookingType) throws Exception {
	safeClick(driver, By.xpath("//button[@type='submit']"));
}

public void mobileCom_Air_TravelerPage(RemoteWebDriver driver) throws Exception {
	Reporter.log(
			"Itinerary ID: " + driver.getCurrentUrl().split("/itinerary/")[1].split("/mobile-traveller-details")[0],
			true);

	safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Title_Dropdown"), dataFile.value("Title"));
	safeType(driver, getObject("MobileCom_Air_TravelerPage_Adult_FirstName"), dataFile.value("First_Name"));
	safeType(driver, getObject("MobileCom_Air_TravelerPage_Adult_LastName"), dataFile.value("Last_Name"));
	safeType(driver, getObject("MobileCom_Air_TravelerPage_Mobile_Number"), dataFile.value("Mobile_Number"));
	if (elementVisible(driver, getObject("MobileCom_Air_TravelerPage_Email"), 1)) {
		safeType(driver, getObject("MobileCom_Air_TravelerPage_Email"), dataFile.value("Mobile_EmailID"));
	}
	// if(gst){
	// if(Airline.equalsIgnoreCase("indigo")){
	// System.out.println(getText(driver,By.xpath("//ul[@class='review']/li/p/strong")));
	/*
	 * safeClick(driver,By.id("toggleGst"));
	 * safeType(driver,By.id("gst_number"),"27AAAAA0000A1Z1");
	 * safeType(driver,By.id("gst_holder_name"),"test");
	 * safeType(driver,By.xpath("//textarea[@placeholder='Enter GSTIN Holder Name']"
	 * ),"banga");
	 */
	// safeClick(driver,By.xpath("//select[@name='gst[stateCode]']"));
	// safeClick(driver,By.xpath("//option[@data-state-short='MH']"));

	// }
	// }
	scrollToElement(driver, getObject("MobileCom_Air_TravelerPage_Continue_Button"));
	safeClick(driver, getObject("MobileCom_Air_TravelerPage_Continue_Button"));
}

public void mobile_Air_TravelerPage_with_GST(RemoteWebDriver driver, boolean gst, String carrier) throws Exception {
	String gstdata = "";
	safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Title_Dropdown"), dataFile.value("Title"));
	safeType(driver, getObject("MobileCom_Air_TravelerPage_Adult_FirstName"), dataFile.value("First_Name"));
	safeType(driver, getObject("MobileCom_Air_TravelerPage_Adult_LastName"), dataFile.value("Last_Name"));
	safeType(driver, getObject("MobileCom_Air_TravelerPage_Mobile_Number"), dataFile.value("Mobile_Number"));
	if (elementVisible(driver, getObject("MobileCom_Air_TravelerPage_Email"), 1)) {
		safeType(driver, getObject("MobileCom_Air_TravelerPage_Email"), dataFile.value("Mobile_EmailID"));
	}
	// if(carrier.equalsIgnoreCase("spicejet")){
	gstdata = "22AAAAA0000A1Z5";
	// }
	/*
	 * else if(carrier.equalsIgnoreCase("goair") ||
	 * carrier.equalsIgnoreCase("indigo")){ gstdata="27AAAAA0000A1Z1"; } else
	 * if(carrier.equalsIgnoreCase("air_asia")){ gstdata="22AAAAA0000A1Z1"; } else {
	 * gstdata="27AAAAA0000A1Z1"; }
	 */
	if (gst) {
		// if(Airline.equalsIgnoreCase("indigo")){
		// System.out.println(getText(driver,By.xpath("//ul[@class='review']/li/p/strong")));
		safeClick(driver, By.id("toggleGst"));
		safeType(driver, By.id("gst_number"), gstdata);
		safeType(driver, By.id("gst_holder_name"), "cleartrip");
		// safeType(driver,By.xpath("//textarea[@placeholder='Enter GSTIN Holder
		// Address']"),"cleartrip");
		// safeClick(driver,By.xpath("//select[@name='gst[stateCode]']"));
		// safeClick(driver,By.xpath("//option[@data-state-short='MH']"));

	}
	/*
	 * else{Select cities = new Select(driver.findElement(By.id("gstStateCode")));
	 * cities.selectByVisibleText("Chhattisgarh"); //fruits.selectByIndex(1);
	 * safeClick(driver,By.id("gstStateCode"));
	 * safeClick(driver,By.xpath("//option[@data-state-short='KA']")); }
	 */
	// }
	safeClick(driver, getObject("MobileCom_Air_TravelerPage_Continue_Button"));
	Reporter.log(driver.getCurrentUrl(), true);
}

public void mobileCom_Air_IntlTravelerPage(RemoteWebDriver driver) throws Exception {
	Reporter.log("Itinerary ID: " + driver.getCurrentUrl()
	.split("https://" + common.value("host") + ".cleartrip.com/m/flights/itinerary/")[1]
			.split("/mobile-traveller-details")[0],
			true);

	if (!waitForElementVisibility(driver, getObject("MobileCom_Air_TravelerPage_Title_Dropdown"), 30)) {
		Reporter.log("Traveller Details page not loaded in 30 seconds!", true);
		Assert.fail("Traveller Details page not loaded in 30 seconds!");
	} else {
		safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Title_Dropdown"), dataFile.value("Title"));
		safeType(driver, getObject("MobileCom_Air_TravelerPage_Adult_FirstName"), dataFile.value("AFname1"));
		safeType(driver, getObject("MobileCom_Air_TravelerPage_Adult_LastName"), dataFile.value("ALname1"));

		if (waitForElementVisibility(driver, getObject("MobileCom_Air_TravelerPage_Adult_Dob"), 1)) {
			safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Adult_Dob"), dataFile.value("IDobday1"));
			safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Adult_Mob"),
					dataFile.value("IDobmonth1Mobile"));
			safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Adult_Yob"), dataFile.value("Yob"));
		}

		if (waitForElementVisibility(driver, getObject("MobileCom_Air_TravelerPage_Adult_VisaType"), 5)) {
			safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Adult_VisaType"), dataFile.value("visatype"));
		}

		if (waitForElementVisibility(driver, By.xpath("//input[contains(@title, 'passport number')]"), 1)) {
			safeClick(driver, By.xpath("//input[contains(@title, 'passport number')]"));
			safeType(driver, By.xpath("//input[contains(@title, 'passport number')]"), "Fa122345");
		}

		if (waitForElementVisibility(driver, By.xpath("//input[contains(@title, 'issuing country')]"), 1)) {
			safeType(driver, By.xpath("//input[contains(@title, 'issuing country')]"), "India");
			// driver.findElement(By.xpath("//input[contains(@title, 'issuing
			// country')]")).sendKeys(Keys.RETURN);
			// safeClick(driver,
			// By.xpath("/ul[@id='autocompleteOptionsContainer']/li[contains(@class,
			// 'highlight highlight')]/a[contains(@class, 'xh-highlight')]"));
			driver.findElement(By.xpath("//ul[@class='aa']/*[1]")).click();
		}

		if (waitForElementVisibility(driver, By.id("AdultPPDay1"), 1)) {
			safeSelect(driver, By.id("AdultPPDay1"), "5");
		}

		if (waitForElementVisibility(driver, By.id("AdultPPMonth1"), 1)) {
			safeSelect(driver, By.id("AdultPPMonth1"), "May");
		}

		if (waitForElementVisibility(driver, By.name("AdultPPYear1"), 1)) {
			safeSelect(driver, By.id("AdultPPYear1"), "2021");
		}

		if (waitForElementVisibility(driver, By.name("IDCardtype"), 1)) {
			safeSelect(driver, By.id("MasterPax"), "Passport");
			safeType(driver, By.xpath("//input[contains(@title, 'ID no.')]"), "Fa122345");

		}

		safeType(driver, getObject("MobileCom_Air_TravelerPage_Mobile_Number"), dataFile.value("Mobile_Number"));
		safeType(driver, getObject("MobileCom_Air_TravelerPage_Email"), dataFile.value("Mobile_EmailID"));

		scrollToElement(driver, getObject("MobileCom_Air_TravelerPage_Continue_Button"));
		safeClick(driver, getObject("MobileCom_Air_TravelerPage_Continue_Button"));
	}
}

public void mobileCom_Air_IntlTravelerPage_With_GST(RemoteWebDriver driver, String carrier) throws Exception {
	String gstdata;
	Reporter.log(driver.getCurrentUrl(), true);
	if (elementVisible(driver, getObject("MobileCom_Air_TravelerPage_Title_Dropdown"), 20)) {
		safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Title_Dropdown"), dataFile.value("Title"));
		safeType(driver, getObject("MobileCom_Air_TravelerPage_Adult_FirstName"), dataFile.value("AFname1"));
		safeType(driver, getObject("MobileCom_Air_TravelerPage_Adult_LastName"), dataFile.value("ALname1"));
		if (elementVisible(driver, getObject("MobileCom_Air_TravelerPage_Adult_Dob"), 1)) {
			safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Adult_Dob"), dataFile.value("IDobday1"));
			safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Adult_Mob"),
					dataFile.value("IDobmonth1Mobile"));
			safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Adult_Yob"), dataFile.value("Yob"));
		}
		if (elementPresent_Time(driver, getObject("MobileCom_Air_TravelerPage_Adult_VisaType"), 5)) {
			safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Adult_VisaType"), dataFile.value("visatype"));
		}
	}

	// if(carrier.equalsIgnoreCase("spicejet")){
	gstdata = "22AAAAA0000A1Z5";
	// }
	/*
	 * else if(carrier.equalsIgnoreCase("goair") ||
	 * carrier.equalsIgnoreCase("indigo")){ gstdata="27AAAAA0000A1Z1"; } else
	 * if(carrier.equalsIgnoreCase("air_asia")){ gstdata="22AAAAA0000A1Z1"; } else {
	 * gstdata="27AAAAA0000A1Z1"; }
	 */
	// if(gst){
	// if(Airline.equalsIgnoreCase("indigo")){
	// System.out.println(getText(driver,By.xpath("//ul[@class='review']/li/p/strong")));
	safeClick(driver, By.id("toggleGst"));
	safeType(driver, By.id("gst_number"), gstdata);
	safeType(driver, By.id("gst_holder_name"), "cleartrip");
	/*
	 * safeType(driver,By.id("gst_number"),gstdata);
	 * safeType(driver,By.id("gst_holder_name"),"test"); Thread.sleep(2000); try{
	 * driver.findElement(By.
	 * xpath("//textarea[@placeholder='Enter GSTIN Holder Name'])")).click();
	 * driver.findElement(By.
	 * xpath("//textarea[@placeholder='Enter GSTIN Holder Name'])")).sendKeys(
	 * "cleartrip"); } catch(Exception e){ driver.findElement(By.xpath(
	 * "//div[@id='gstDetails']/div/table/tbody/tr[3]/td/textarea")).click();
	 * driver.findElement(By.xpath(
	 * "//div[@id='gstDetails']/div/table/tbody/tr[3]/td/textarea")).sendKeys(
	 * "cleartrip"); }
	 */
	// safeType(driver,By.xpath("//textarea[@placeholder='Enter GSTIN Holder
	// Name']"),"cleartrip");
	/*
	 * if(elementVisible(driver,By.
	 * xpath("//input[@title='Adult 1's passport number']"),1)){
	 * safeClick(driver,By.xpath("//input[@title='Adult 1's passport number']"));
	 * safeType(driver,By.xpath("//input[@title='Adult 1's passport number']"),
	 * "Fa122345"); }
	 * if(elementVisible(driver,By.xpath("//input[@title='adultPPIssuingCountry1']")
	 * ,1)){
	 * 
	 * safeSelect(driver,By.xpath("//input[@title='adultPPIssuingCountry1']"),
	 * "India"); } if(elementVisible(driver,By.id("AdultPPDay1"),1)){
	 * safeSelect(driver,By.id("AdultPPDay1"),"05"); }
	 * if(elementVisible(driver,By.name("AdultPPYear1"),1)){
	 * safeSelect(driver,By.id("AdultPPYear1"),"2019"); }
	 */

	Thread.sleep(100);
	safeType(driver, getObject("MobileCom_Air_TravelerPage_Mobile_Number"), dataFile.value("Mobile_Number"));
	safeType(driver, getObject("MobileCom_Air_TravelerPage_Email"), dataFile.value("Mobile_EmailID"));
	Thread.sleep(9000);
	safeClick(driver, getObject("MobileCom_Air_TravelerPage_Continue_Button"));
}

public void mobileCom_Air_Expressway_Itinerary(RemoteWebDriver driver) throws Exception {

	/*
	 * elementPresent_Time(driver, By.xpath("//*[@id='wrap']/ul/li/p[1]/span[2]"),
	 * 50); String AirlineName [] =
	 * driver.findElement(By.xpath("//*[@id='wrap']/ul/li/p[1]/span[2]")).getText().
	 * split(" "); String AirlineCode = AirlineName[0];
	 * if(elementVisible(driver,getObject(
	 * "MobileCom_Air_TravelerSection_Expressway_Change_Button"), 5)){
	 * safeClick(driver,getObject(
	 * "MobileCom_Air_TravelerSection_Expressway_Change_Button"));
	 * if(AirlineCode.contains("AI")||AirlineCode.contains("9W")||AirlineCode.
	 * contains("9W-K")){ Select TravellersTitle = new
	 * Select(driver.findElement(getObject(
	 * "MobileCom_Air_TravelerSection_Expressway_Title_DropDown")));
	 * TravellersTitle.selectByVisibleText("Mr");
	 * 
	 * 
	 * safeSelect(driver, getObject("MobileCom_Air_TravelerPage_Title_Dropdown"),
	 * dataFile.value("Title")); safeType(driver,
	 * getObject("MobileCom_Air_TravelerPage_Adult_FirstName"),
	 * dataFile.value("AFname1")); safeType(driver,
	 * getObject("MobileCom_Air_TravelerPage_Adult_LastName"),
	 * dataFile.value("ALname1")); safeSelect(driver,
	 * getObject("MobileCom_Air_TravelerSection_Expressway_Title_DropDown"),
	 * dataFile.value("Title")); safeType(driver,
	 * getObject("MobileCom_Air_TravelerSection_Expressway_FirstName"),
	 * dataFile.value("AFname1")); safeType(driver,
	 * getObject("MobileCom_Air_TravelerSection_Expressway_LastName"),
	 * dataFile.value("ALname1")); safeType(driver,
	 * getObject("MobileCom_Air_TravelerSection_Expressway_MobileNumber"),
	 * dataFile.value("Mobile_Number")); safeType(driver,
	 * getObject("MobileCom_Air_TravelerPage_Mobile_Number"),
	 * dataFile.value("Mobile_Number")); safeClick(driver,
	 * getObject("MobileCom_Air_TravelerSection_Expressway_SaveButton")); }else
	 * if(AirlineCode.contains("6E")||AirlineCode.contains("SG")||AirlineCode.
	 * contains("G8")||AirlineCode.contains("I5")){ Select TravellersTitle = new
	 * Select(driver.findElement(getObject(
	 * "MobileCom_Air_TravelerSection_Expressway_Title_DropDown")));
	 * TravellersTitle.selectByVisibleText("Mr"); safeType(driver,
	 * getObject("MobileCom_Air_TravelerSection_Expressway_FirstName"),
	 * dataFile.value("First_Name")); safeType(driver,
	 * getObject("MobileCom_Air_TravelerSection_Expressway_LastName"),
	 * dataFile.value("Last_Name")); safeType(driver,
	 * getObject("MobileCom_Air_TravelerSection_Expressway_MobileNumber"),
	 * dataFile.value("Mobile_Number")); safeClick(driver,
	 * getObject("MobileCom_Air_TravelerSection_Expressway_SaveButton")); } }else {
	 */
	/*
	 * if(elementPresent(driver,By.id("changeButton"),1)){
	 * safeClick(driver,By.id("changeButton")); }
	 */
	printInfo("No change button found");/*
	 * Select TravellersTitle = new Select(driver.findElement(getObject(
	 * "MobileCom_Air_TravelerSection_Expressway_Title_DropDown")));
	 * TravellersTitle.selectByVisibleText("Mr");
	 */

	if (waitForElementVisibility(driver, By.xpath("//div[contains(@class,'blockMessage bad')]"), 5)) {
		Reporter.log("Create Itinearary Failed!", true);
		Reporter.log("Message: " + getText(driver, By.xpath("//div[contains(@class,'blockMessage bad')]")), true);
		Assert.fail("Create Itinearary Failed!");
	}

	safeSelect(driver, getObject("MobileCom_Air_TravelerSection_Expressway_Title_DropDown"), "Mr");
	safeType(driver, getObject("MobileCom_Air_TravelerSection_Expressway_FirstName"), dataFile.value("First_Name"));
	safeType(driver, getObject("MobileCom_Air_TravelerSection_Expressway_LastName"), dataFile.value("Last_Name"));
	safeType(driver, getObject("MobileCom_Air_TravelerSection_Expressway_MobileNumber"),
			dataFile.value("Mobile_Number"));
}

public void mobileCom_Air_Expressway_Itinerary_GST(RemoteWebDriver driver, String carrier, boolean gst)
		throws Exception {
	String gstdata;

	Select title = new Select(driver.findElement(By.name("title")));
	title.selectByVisibleText("Mr");
	safeType(driver, getObject("MobileWeb_Trains_In_Travellers_FirstNameR"), "test");
	Thread.sleep(1000);
	safeType(driver, getObject("MobileWeb_Trains_In_Travellers_FirstNameR"), "test");
	Thread.sleep(1000);
	safeType(driver, getObject("MobileWeb_Trains_In_Travellers_LastNameR"), "test");
	/*safeType(driver, getObject("MobileWeb_Trains_In_Travellers_FirstNameR"), "test");
		Thread.sleep(1000);*/
	if (elementPresent(driver, getObject("PWATrainsPhone"), 10)) {
		safeType(driver, getObject("PWATrainsPhone"), "1234567890");

	}

	if ((driver.findElement(getObject("PWATrainsEMail")).getAttribute("disabled")) == null) {
		//Thread.sleep(2000);
		safeType(driver, getObject("PWATrainsEMail"), "test@cleartrip.com");
	}




	/*if (!textPresent(driver, "Use GSTIN for this booking (Optional)", 2)) {
			Reporter.log("Use GSTIN for this booking (Optional) Message is not displayed");

			safeClick(driver, By.xpath("//*[text()='Use GSTIN for this booking (optional)']"));


			if (gst) {
				// if(carrier.equalsIgnoreCase("spicejet")){

				gstdata = "22AAAAA0000A1Z5";
				// }

	 * else if(carrier.equalsIgnoreCase("goair") ||
	 * carrier.equalsIgnoreCase("indigo")){ gstdata="27AAAAA0000A1Z1"; } else
	 * if(carrier.equalsIgnoreCase("air_asia")){ gstdata="22AAAAA0000A1Z1"; } else {
	 * gstdata="27AAAAA0000A1Z1"; }

				// if(gst){
				// if(Airline.equalsIgnoreCase("indigo")){
				// System.out.println(getText(driver,By.xpath("//ul[@class='review']/li/p/strong")));
				//	System.out.println(getText(driver, By.id("gst_number")));
				safeType(driver,By.xpath("//div[@id='root']//input[@name='number']"), "22AAAAA0000A1Z1");
				safeType(driver,By.xpath("//div[@id='root']//input[@name='name']"), "jitendra");
				safeType(driver,By.xpath("//div[@id='root']//input[@name='address']"), "cleartrip");

				safeClick(driver, By.xpath("//button[@class='Button Button--full Button--secondary']"));

	 * try{ driver.findElement(By.
	 * xpath("//textarea[@placeholder='Enter GSTIN Holder Name'])")).clear();
	 * driver.findElement(By.
	 * xpath("//textarea[@placeholder='Enter GSTIN Holder Name'])")).sendKeys(
	 * "cleartrip"); } catch(Exception e){ driver.findElement(By.xpath(
	 * "//div[@id='gstDetails']/div/table/tbody/tr[3]/td/textarea")).clear();
	 * driver.findElement(By.xpath(
	 * "//div[@id='gstDetails']/div/table/tbody/tr[3]/td/textarea")).sendKeys(
	 * "cleartrip"); }

			} else {
				driver.findElement(By.id("gst_number")).clear();
				driver.findElement(By.id("gst_holder_name")).clear();
				driver.findElement(By.xpath("//div[@id='gstDetails']/div/table/tbody/tr[3]/td/textarea")).clear();
				safeClick(driver, By.id("toggleGst"));
				Select state = new Select(driver.findElement(By.id("gstStateCode")));
				state.selectByVisibleText("Chhattisgarh");
			}
	 */
	safeClick(driver, getObject("MobileWeb_Trains_In_Travellers_Details_Continue_ButtonR"));
	Thread.sleep(2000);
	//}
}

public void mobileCom_Air_MakePaymentExpwayPage(RemoteWebDriver driver, String Trip_Logger_Msg) throws Exception {
	safeType(driver, getObject("MobileCom_Air_PaymentPage_SC_CVV"), dataFile.value("Master_CC_CVV"));
	if (MakePaymentTrue && !ProductionUrl) {
		safeClick(driver, getObject("MobileCom_Air_PaymentPage_Payment_Button"));
		textPresent(driver, "Great, your booking went through", 500);
		elementVisible(driver, getObject("MobileCom_Air_ConfirmationPage_TripID"), 5);
		String TripID = getText(driver, getObject("MobileCom_Air_ConfirmationPage_TripID")).replace("Trip ID: ",
				"");
		logger.info(TripID + " for " + Trip_Logger_Msg);
		Reporter.log(TripID + " for " + Trip_Logger_Msg);
	} else {
		printInfo("Either URL is pointing to Production or Payment flag is true");
	}
}

public void mobileCom_Air_MakePaymentExpwayPage_WithCancellation(RemoteWebDriver driver, String Trip_Logger_Msg)
		throws Exception {
	safeType(driver, getObject("MobileCom_Air_PaymentPage_SC_CVV"), dataFile.value("Master_CC_CVV"));
	if (MakePaymentOnlyInQA2) {
		safeClick(driver, getObject("MobileCom_Air_PaymentPage_Payment_Button"));
		textPresent(driver, "Great, your booking went through", 500);
		elementVisible(driver, getObject("MobileCom_Air_ConfirmationPage_TripID"), 5);
		String TripID = getText(driver, getObject("MobileCom_Air_ConfirmationPage_TripID")).replace("Trip ID: ",
				"");
		;
		logger.info(TripID + " for " + Trip_Logger_Msg);
		Reporter.log(TripID + " for " + Trip_Logger_Msg);
		mobileCom_Air_CancellationFlow(driver, TripID);
	} else {
		printInfo("Either URL is pointing to Production or Payment flag is true");
	}
}

public void mobileCom_Air_CancellationFlow(RemoteWebDriver driver, String TripID) throws Exception {
	Thread.sleep(2000);
	System.out.println(common.value("url"));
	driver.get("https://qa2.cleartrip.com/");
	List<WebElement> we = driver.findElements(By.xpath("//b"));
	Test: for (int i = 0; i < we.size(); i++) {
		// we.get(i).getText();
		// //System.out.println( we.get(i).getText());
		if (we.get(i).getText().equalsIgnoreCase("trips")) {
			we.get(i).click();
			break Test;
		}
	}
	Thread.sleep(5000);
	boolean TripPage500 = isElementPresent(driver, getObject("MobileCom_TripsPage_500Error"));
	if (TripPage500) {
		driver.navigate().refresh();
		mobileCom_Air_CancellationSteps(driver, TripID);
	} else {
		mobileCom_Air_CancellationSteps(driver, TripID);
	}
}

public void mobileCom_Air_CancellationSteps(RemoteWebDriver driver, String TripID) throws Exception {
	Thread.sleep(2000);
	safeType(driver, getObject("MobileCom_Air_HomePage_UserName"), dataFile.value("Mobile_EmailID"));
	Thread.sleep(2000);
	safeType(driver, getObject("MobileCom_Air_HomePage_Pwd"), dataFile.value("Mobile_Password"));
	Thread.sleep(2000);
	safeClick(driver, getObject("MobileCom_Air_HomePage_SignIN_Button"));
	elementVisible(driver, getObject("MobileCom_HomePage_Trips_PageText"), 20);
	String TripsText = getText(driver, getObject("MobileCom_HomePage_Trips_PageText"));
	Assert.assertEquals("Trips", TripsText);
	printInfo(TripID);
	driver.get("https://qa2.cleartrip.com/account/trips/" + TripID + "/cancel");
	Thread.sleep(2000);
	if (!textPresent(driver, "This trip cannot be cancelled online", 5)) {
		elementVisible(driver, getObject("MobileCom_HomePage_Trips_CancellationPage1_Text"), 20);
		String CancellationText = getText(driver, getObject("MobileCom_HomePage_Trips_CancellationPage1_Text"));
		Assert.assertEquals("Cancellations", CancellationText);
		safeClick(driver, getObject("MobileCom_HomePage_Trips_CancellationPage1_Travellers_Click"));
		// safeClick(driver,
		// getObject("MobileCom_HomePage_Trips_CancellationPage1_SeeRefundAmount_Button"));
		safeClick(driver, By.xpath("//footer/nav/ul/li[1]/button"));
		textPresent(driver, "Refund amount upon cancellation", 20);
		textAssert(driver, By.xpath("//form/div/p"), "Do you want to proceed with the cancellation?");
		safeClick(driver, By.xpath("//footer/nav/ul/li[1]/button"));
		textPresent(driver, "Your tickets were cancelled successfully", 20);
		textAssert(driver, By.xpath("//div/div/h3"), "Your tickets were cancelled successfully");
	} else {
		Reporter.log("This trip cannot be cancelled online : Message is displayed");
		logger.info("This trip cannot be cancelled online : Message is displayed");
	}
	/*
	 * String CancelPage2Text=getText(driver, getObject(
	 * "MobileCom_HomePage_Trips_CancellationPage2_Total_RefundAmount_Text"));
	 * Assert.assertEquals("Total refund", CancelPage2Text); safeClick(driver,
	 * getObject(
	 * "MobileCom_HomePage_Trips_CancellationPage2_RefundToPaymentOption_RadioButton"
	 * )); safeClick(driver, By.className("primary")); if (waitElement(driver,
	 * getObject(
	 * "MobileCom_HomePage_Trips_CancellationPage3_TicketsCancelled_Message"),
	 * 500)&&isElementPresent(driver, getObject(
	 * "MobileCom_HomePage_Trips_CancellationPage3_TicketsCancelled_Message"))){
	 * String CancelPage3Text=getText(driver, getObject(
	 * "MobileCom_HomePage_Trips_CancellationPage3_TicketsCancelled_Message"));
	 * Assert.assertEquals("Your tickets were cancelled successfully",
	 * CancelPage3Text); safeClick(driver,
	 * getObject("MobileCom_HomePage_Trips_CancellationPage3_BackToTrips_Button"));
	 * Thread.sleep(2000); String TripsText2 =getText(driver,
	 * getObject("MobileCom_HomePage_Trips_PageText")); Assert.assertEquals("Trips",
	 * TripsText2); }else{ printInfo("cancellation failed"); }
	 */
}

public String mobileCom_Air_MakePaymentPage(RemoteWebDriver driver, String Booking_Type, String Payment_Type,
		String Trip_Logger_Msg) throws Exception {
	if (!waitForElementVisibility(driver, By.xpath("//body/div/div"), 120)) {
		Reporter.log("Payment Page not loaded in 120 seconds.", true);
		Assert.fail("Test case faied!");
	}

	String TripID = null;
	if (Payment_Type.equals("")) {
		safeType(driver, getObject("MobileCom_Air_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
		safeType(driver, getObject("MobileCom_Air_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
		safeSelect(driver, getObject("MobileCom_Air_PaymentPage_CC_Expiry_Month"),
				dataFile.value("Master_CC_Expiry_Month"));
		safeSelect(driver, getObject("MobileCom_Air_PaymentPage_CC_Expiry_Year"),
				dataFile.value("Master_CC_Expiry_Year"));
		safeType(driver, getObject("MobileCom_Air_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));

		try {
			Reporter.log("PG / Convinience Fee Details: " + getText(driver, By.id("mb_rate_rule")), true);
		} catch (Exception e) {
			Reporter.log("PG fee Not Available", true);
		}

		if (Booking_Type.equalsIgnoreCase("COUPON")) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(getObject("MobileCom_Air_PaymentPage_Coupon_Edit_Box"));
			//js1.executeScript("arguments[0].scrollIntoView(true);", element);
			safeType(driver, getObject("MobileCom_Air_PaymentPage_Coupon_Edit_Box"), "SHACHI");
			safeClick(driver, getObject("MobileCom_Air_PaymentPage_Coupon_Apply_Button"));
			if (waitForElementVisibility(driver, getObject("MobileCom_Air_PaymentPage_Coupon_Success_Msg"), 30)) {
				String Coupon_Amt = getText(driver, getObject("MobileCom_Air_PaymentPage_Coupon_Success_Msg"));
				Reporter.log("Coupon message: " + Coupon_Amt, true);

				if (!Coupon_Amt.contains("CASHBACK")) {
					Reporter.log("Coupon SHACHI is not working.", true);
					Assert.fail("Coupon Amount mismatch.");
				}
			} else {
				Reporter.log("Coupon Message not displayed in 30 seconds", true);
				Assert.fail("Coupon Message not displayed in 30 seconds");
			}
		}
	} else if (Payment_Type.equalsIgnoreCase("EXPRESSWAY")) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.id("payByStoredCard"));
		//js.executeScript("arguments[0].scrollIntoView(true);", element);
		safeType(driver, getObject("MobileCom_Air_PaymentPage_SC_CVV"), dataFile.value("Master_CC_CVV"));
	} else if (Payment_Type.equalsIgnoreCase("NBRetry")) {
		Reporter.log("Initiating NB Payment", true);
		driver.findElement(By.id("payByNetBanking")).click();

		try {
			Reporter.log("PG / Convinience Fee Details: " + getText(driver, By.id("mb_rate_rule")), true);
		} catch (Exception e) {
			Reporter.log("PG fee Not Available", true);
		}

		Select bank = new Select(driver.findElement(By.id("transferBank")));
		bank.selectByVisibleText("Bank of India");
		driver.findElement(By.id("ExpressCheckout")).click();
		elementPresent(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"), 5);

		Reporter.log("Reached BOI page. URL: " + driver.getCurrentUrl(), true);
		safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
		driver.findElement(By.xpath("//p/a[@class='btn primary']")).click();

		Reporter.log("Back to payment retry page. Initiating CC Payment", true);
		safeType(driver, getObject("MobileCom_Air_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
		safeType(driver, getObject("MobileCom_Air_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
		safeSelect(driver, getObject("MobileCom_Air_PaymentPage_CC_Expiry_Month"),
				dataFile.value("Master_CC_Expiry_Month"));
		safeSelect(driver, getObject("MobileCom_Air_PaymentPage_CC_Expiry_Year"),
				dataFile.value("Master_CC_Expiry_Year"));
		safeType(driver, getObject("MobileCom_Air_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));

		try {
			Reporter.log("PG / Convinience Fee Details: " + getText(driver, By.id("mb_rate_rule")), true);
		} catch (Exception e) {
			Reporter.log("PG fee Not Available", true);
		}

		if (Booking_Type.equalsIgnoreCase("COUPON")) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(getObject("MobileCom_Air_PaymentPage_Coupon_Edit_Box"));
			//js1.executeScript("arguments[0].scrollIntoView(true);", element);
			safeType(driver, getObject("MobileCom_Air_PaymentPage_Coupon_Edit_Box"), "SHACHI");
			safeClick(driver, getObject("MobileCom_Air_PaymentPage_Coupon_Apply_Button"));
			if (waitForElementVisibility(driver, getObject("MobileCom_Air_PaymentPage_Coupon_Success_Msg"), 30)) {
				String Coupon_Amt = getText(driver, getObject("MobileCom_Air_PaymentPage_Coupon_Success_Msg"));
				Reporter.log("Coupon message: " + Coupon_Amt, true);

				if (!Coupon_Amt.contains("CASHBACK")) {
					Reporter.log("Coupon SHACHI is not working.", true);
					Assert.fail("Coupon Amount mismatch.");
				}
			} else {
				Reporter.log("Coupon Message not displayed in 30 seconds", true);
				Assert.fail("Coupon Message not displayed in 30 seconds");
			}
		}

	}

	if (MakePaymentOnlyInQA2) {
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(getObject("MobileCom_Air_PaymentPage_Payment_Button"));
		//js1.executeScript("arguments[0].scrollIntoView(true);", element12);
		safeClick(driver, getObject("MobileCom_Air_PaymentPage_Payment_Button"));

		Thread.sleep(15000);
		if (elementPresent_Time(driver, getObject("AirCom_Interstitial_Amex_Confirmation_Dropdown"), 60)) {
			elementVisible(driver, getObject("air_amex_payment_selectAuth"), time_verylong);
			safeClick(driver, getObject("air_amex_payment_button"));
		}

		if (textPresent(driver, "Oops! Flight not found", 5)) {
			Reporter.log("Oops ! flight not found error : is dispalyed.", true);
			Assert.fail("Oops ! flight not found error : is dispalyed. Not a script Error!");
		}
		elementVisible(driver, getObject("MobileCom_Air_ConfirmationPage_TripID"), 50);

		textPresent(driver, "Great, your booking went through", 5);
		TripID = getText(driver, getObject("MobileCom_Air_ConfirmationPage_TripID"));

		logger.info(TripID + " for " + Trip_Logger_Msg);
		Reporter.log(TripID + " for " + Trip_Logger_Msg, true);

		if (TripID.contains("Sorry, we were unable to complete your request")) {
			Reporter.log("Message Displayed is: " + "Sorry, we were unable to complete your request", true);
			Assert.fail("Message Displayed is: "
					+ "Sorry, we were unable to complete your request. Not a script error!");
		}
	} else {
		Reporter.log("Make Payment Flag Set to False. Hence not attempting Payment.", true);
	}

	return TripID;

}

/*
 * Added by: prashanth.k@cleartrip.com This method returns the mobile URL based
 * on the value that has been set for host in common.properties
 */
public String getMobile_Web_URL() {
	if (common.value("host").equalsIgnoreCase("qa2")) {
		Reporter.log("URL: " + common.value("murl"), true);
		return common.value("murl");
	} else if (common.value("host").equalsIgnoreCase("www")) {
		Reporter.log("URL: " + common.value("murl_prod"), true);
		return common.value("murl_prod");
	} else {
		Reporter.log(
				"Unable to construct murl for the specified host. Please check host value in common.properties",
				true);
		Assert.fail("Failed!");
		return null;
	}
}

/*
 * Added by: prashanth.k@cleartrip.com This method returns the mobile URL based
 * on the value that has been set for host in common.properties
 */
public String getMobile_Web_URL_ME(String domain) {
	if (domain.equalsIgnoreCase("ae")) {
		if (common.value("host").equalsIgnoreCase("qa2")) {
			Reporter.log("URL: " + common.value("murl_ae"), true);
			return common.value("murl_ae");
		} else if (common.value("host").equalsIgnoreCase("www")) {
			Reporter.log("URL: " + common.value("murl_prod_ae"), true);
			return common.value("murl_prod_ae");
		} else {
			Reporter.log(
					"Unable to construct murl for the specified host. Please check host value in common.properties",
					true);
			Assert.fail("Failed!");
			return null;
		}
	} else if (domain.equalsIgnoreCase("sa")) {
		if (common.value("host").equalsIgnoreCase("qa2")) {
			Reporter.log("URL: " + common.value("murl_sa"), true);
			return common.value("murl_sa");
		} else if (common.value("host").equalsIgnoreCase("www")) {
			Reporter.log("URL: " + common.value("murl_prod_sa"), true);
			return common.value("murl_prod_sa");
		} else {
			Reporter.log(
					"Unable to construct murl for the specified host. Please check host value in common.properties",
					true);
			Assert.fail("Failed!");
			return null;
		}
	}
	return null;
}

// ---------------------------------------------------------Hotel
// Scripts---------------------------------------------------//

public void mobileCom_Hotel_HomepageSearch(RemoteWebDriver driver, String City, String From_Date, String To_Date,
		String Adults, String Childrens) throws Exception {
	driver.navigate().refresh();
	if (elementVisible(driver, By.id("menuBtn"), 1)) {
		safeClick(driver, By.id("menuBtn"));
	}
	if (elementVisible(driver, getObject("MobileCom_Hotel_HomePage_Hotel_Tab"), 1)) {
		safeClick(driver, getObject("MobileCom_Hotel_HomePage_Hotel_Tab"));
	}
	safeAutocomplete(driver, getObject("MobileCom_Hotel_HomePage_City"),
			getObject("MobileCom_Hotel_HomePage_Hotel_Ajax"), City);
	selectCalendarDate(driver, getObject("MobileCom_Hotel_HomePage_CheckIN_Calendar"),
			getObject("MobileCom_Hotel_HomePage_Calendar_NextMonth"), 1, From_Date);
	Thread.sleep(1000);
	selectCalendarDate(driver, getObject("MobileCom_Hotel_HomePage_CheckOut_Calendar"),
			getObject("MobileCom_Hotel_HomePage_Calendar_NextMonth"), 0, To_Date);
	safeSelect(driver, getObject("MobileCom_Hotel_HomePage_Adults_DropDown"), Adults);
	safeSelect(driver, getObject("MobileCom_Hotel_HomePage_Childrens_DropDown"), Childrens);
	// try{
	// safeClick(driver,
	// getObject("MobileCom_Hotel_HomePage_Search_Button_For_Single_Pax"));
	// }
	// catch(Exception e){
	safeClick(driver, By.id("submitBtn"));
	// }
}

public void mobileCom_Hotel_HomepageMultiPaxRoom(RemoteWebDriver driver, String City, String From_Date,
		String To_Date, String Rooms, String AdultsInRoom1, String AdultsInRoom2, String ChildInRoom1,
		String ChildInRoom2, String ChildAgeInRoom1, String ChildAgeInRoom2, String Hotel_Name,
		String Trip_Logger_Msg) throws Exception {
	driver.navigate().refresh();
	if (elementVisible(driver, By.id("menuBtn"), 1)) {
		safeClick(driver, By.id("menuBtn"));
	}
	safeClick(driver, getObject("MobileCom_Hotel_HomePage_Hotel_Tab"));
	safeAutocomplete(driver, getObject("MobileCom_Hotel_HomePage_City"),
			getObject("MobileCom_Hotel_HomePage_Hotel_Ajax"), City);
	selectCalendarDate(driver, getObject("MobileCom_Hotel_HomePage_CheckIN_Calendar"),
			getObject("MobileCom_Hotel_HomePage_Calendar_NextMonth"), 1, From_Date);
	Thread.sleep(1000);
	selectCalendarDate(driver, getObject("MobileCom_Hotel_HomePage_CheckOut_Calendar"),
			getObject("MobileCom_Hotel_HomePage_Calendar_NextMonth"), 0, To_Date);
	Select RoomsDropDown = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_Rooms_DropDown")));
	RoomsDropDown.selectByVisibleText(Rooms);
	Select AdultDropDown1 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_AdultsInRoom1")));
	AdultDropDown1.selectByVisibleText(AdultsInRoom1);
	Select AdultDropDown2 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_AdultsInRoom2")));
	AdultDropDown2.selectByVisibleText(AdultsInRoom2);
	Select ChildDropDown1 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_ChildInRoom1")));
	ChildDropDown1.selectByVisibleText(ChildInRoom1);
	Select ChildDropDown2 = new Select(driver.findElement(getObject("MobileCom_Hotel_HomePage_ChildInRoom2")));
	ChildDropDown2.selectByVisibleText(ChildInRoom2);
	Select ChildAgeDropDown1 = new Select(
			driver.findElement(getObject("MobileCom_Hotel_HomePage_ChildAgeInRoom1")));
	ChildAgeDropDown1.selectByVisibleText(ChildAgeInRoom1);
	Select ChildAgeDropDown2 = new Select(
			driver.findElement(getObject("MobileCom_Hotel_HomePage_ChildAgeInRoom2")));
	ChildAgeDropDown2.selectByVisibleText(ChildAgeInRoom2);
	safeClick(driver, getObject("MobileCom_Hotel_HomePage_Search_Button_MultiPax"));
}

public void mobileCom_Hotel_Package_Rates(RemoteWebDriver driver, String Hotel_Name) throws Exception {
	elementNotVisible(driver, getObject("MobileCom_Hotel_SRP_HoldOn_Search_Message"), 60);
	String srpText = getText(driver, getObject("MobileCom_Hotel_SRP_Text"));
	Assert.assertEquals("Price", srpText);
	safeClick(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"));
	for (int i = 1; i <= 10; i++) {
		String Hotel_SRP_XPATH = "//div/ul/li[" + i + "]/div";
		if (elementPresent_Time(driver, By.xpath(Hotel_SRP_XPATH), 5)) {
			safeClick(driver, By.xpath(Hotel_SRP_XPATH));
			elementVisible(driver, getObject("MobileCom_Hotel_DetailsPage_Select_Room"), 50);
			safeClick(driver, getObject("MobileCom_Hotel_DetailsPage_Select_Room"));
			for (int j = 2; j <= 5; i++) {
				String RoomType_Xpath = "//section/nav/ul[" + j + "]/li[1]";
				if (elementPresent_Time(driver, By.xpath(RoomType_Xpath), 1)) {
					String RoomType_Text = getText(driver, By.xpath(RoomType_Xpath));
					if (RoomType_Text.contains("Package")) {
						safeClick(driver, By.xpath(RoomType_Xpath));
						i = 11;
						break;
					}
				}

			}
			driver.navigate().back();
			driver.navigate().back();
		}
	}
}

public void mobileCom_Hotel_SRP(RemoteWebDriver driver, String Hotel_Name) throws Exception {
	Thread.sleep(2000);
	// logURL(driver);
	if (!elementPresent(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"), 5)) {
		/*
		 * int size = driver.findElements(By.tagName("iframe")).size();
		 * System.out.println("iframe size="+size); Reporter.log("iframe size="+size);
		 * if(size==1){ driver.switchTo().frame(0); } else if(size==2){
		 * driver.switchTo().frame(1); } else if(size==3){ driver.switchTo().frame(2); }
		 * else if(size==4){ driver.switchTo().frame(3); }
		 * if(elementPresent(driver,By.id("closeBanner"),2)){
		 * //driver.switchTo().frame(1);
		 * driver.findElement(By.id("closeBanner")).click(); }
		 */}
	/*
	 * elementVisible(driver,
	 * getObject("MobileCom_Hotel_SRP_HoldOn_Search_Message"),600); String srpText =
	 * getText(driver, getObject("MobileCom_Hotel_SRP_Text"));
	 * Assert.assertEquals("Price", srpText);
	 */
	elementVisible(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"), 10);
	safeClick(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"));
	if (!Hotel_Name.isEmpty()) {
		elementPresent_log(driver, getObject("MobileCom_Hotel_SRP_SearchBar"), "Search bar in SRP ", 10);
		Thread.sleep(1000);
		safeType(driver, getObject("MobileCom_Hotel_SRP_SearchBar"), Hotel_Name);
		Thread.sleep(1000);
		mouseHoverClick(driver, getObject("MobileCom_Hotel_SRP_SearchBar"));
		Thread.sleep(1000);
		safeClick(driver, getObject("MobileCom_Hotel_SRP_HotelSearch_Button"));
		if (!getText(driver, getObject("MobileCom_Hotel_SRP_HotelSearch_Name")).contains(Hotel_Name)) {
			Reporter.log(
					"Hotel name selected is : " + getText(driver, getObject("MobileCom_Hotel_SRP_HotelSearch_Name"))
					+ " and Hotel to be selected is  " + Hotel_Name);
			Assert.assertTrue(false);
		}
	}
	safeClick(driver, getObject("MobileCom_Hotel_SRP_Hotel"));
}

public void mobileCom_Hotel_SRP_Search_HotelName(RemoteWebDriver driver, String Hotel_Name) throws Exception {
	boolean hotelFound = false;
	if (!elementPresent(driver, getObject("MobileCom_Hotel_SRP_Hotel"), 1)) {
		/*
		 * int size = driver.findElements(By.tagName("iframe")).size();
		 * System.out.println("iframe size="+size); Reporter.log("iframe size="+size);
		 * if(size==1){ driver.switchTo().frame(0); } else if(size==2){
		 * driver.switchTo().frame(1); } else if(size==3){ driver.switchTo().frame(2); }
		 * else if(size==4){ driver.switchTo().frame(3); }
		 * if(elementPresent(driver,By.id("closeBanner"),2)){
		 * //driver.switchTo().frame(1);
		 * driver.findElement(By.id("closeBanner")).click(); }
		 */}
	/*
	 * elementVisible(driver,
	 * getObject("MobileCom_Hotel_SRP_HoldOn_Search_Message"),600); String srpText =
	 * getText(driver, getObject("MobileCom_Hotel_SRP_Text"));
	 * Assert.assertEquals("Price", srpText);
	 */
	// safeClick(driver,By.xpath("//span[@class='browseLink']"));
	// safeClick(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"));
	// safeType(driver, By.id("hotelsByName"), "Royal Opera");
	// mouseHover(driver, By.id("hotelsByName"));
	// safeClick(driver, By.id("hotelsByNameBtn"));
	List<WebElement> we = driver.findElements(By.xpath("//div[@class='hotelInfo']/strong/span"));
	test: for (int i = 0; i < we.size(); i++) {

		if (we.get(i).getText().equalsIgnoreCase("Ramada Encore Domlur")) {
			hotelFound = true;
			break test;
		}
	}
	Assert.assertEquals(hotelFound, true, "hotel didnt found");
	// System.out.println(getText(driver,By.xpath("//span[@class=''truncate]")));
	safeClick(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"));
	safeClick(driver, getObject("MobileCom_Hotel_SRP_Hotel"));
}

public void mobileCom_Hotel_SRP_PAH(RemoteWebDriver driver, String Hotel_Name) throws Exception, Exception {
	elementNotVisible(driver, getObject("MobileCom_Hotel_SRP_HoldOn_Search_Message"), 600);
	Thread.sleep(1000);
	String srpText = getText(driver, getObject("MobileCom_Hotel_SRP_Text"));
	Assert.assertEquals("Price", srpText);
	if (elementPresent_Time(driver, getObject("MobileCom_Hotel_SRP_HotelName_SearchBox"), 10)) {
		Thread.sleep(5000);
		safeType(driver, getObject("MobileCom_Hotel_SRP_HotelName_SearchBox"), Hotel_Name);
		safeClick(driver, getObject("MobileCom_Hotel_SRP_HotelName_SearchButton"));
	}
}

public void mobileCom_Hotel_Details(RemoteWebDriver driver) throws Exception {
	elementVisible(driver, getObject("MobileCom_Hotel_DetailsPage_Select_Room"), 20);
	safeClick(driver, getObject("MobileCom_Hotel_DetailsPage_Select_Room"));
}

public String mobileCom_Hotel_Open_URL(RemoteWebDriver driver, String Domain) throws Exception {
	String URL = null;
	if (Domain.equalsIgnoreCase("com")) {
		URL = baseUrl;
	}
	/*
	 * else if(Domain.equalsIgnoreCase("ae")){ URL = baseUrl_AE; }
	 */
	return URL;
}

public String mobileCom_Hotel_SRP_URL(RemoteWebDriver driver, String Domain, String City, String State)
		throws Exception {
	baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);
	String Date = putDate1(20);
	String Date1 = putDate1(21);
	String SRP_URL = baseUrl + "/m/hotels/results?city=" + City + "&state=" + State + "&country=IN&ckIn=" + Date
			+ "&ckOut=" + Date1 + "&noOfRooms=1&ad=1&cd=0";
	driver.get(SRP_URL);
	logURL(driver);
	Thread.sleep(2000);
	return SRP_URL;
}

public String mobileCom_Hotel_SRP_URL_Date(RemoteWebDriver driver, String Domain, String City, String State,
		int date, int date1) throws Exception {
	baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);
	String Date = putDate1(date);
	String Date1 = putDate1(date1);
	String SRP_URL = baseUrl + "/m/hotels/results?city=" + City + "&state=" + State + "&country=IN&ckIn=" + Date
			+ "&ckOut=" + Date1 + "&noOfRooms=1&ad=1&cd=0";
	driver.get(SRP_URL);
	logURL(driver);
	Thread.sleep(2000);
	return SRP_URL;

}

public String mobileCom_Hotel_Details_URL(RemoteWebDriver driver, String Domain, String City, String State,
		String Country, int CheckIn, int CheckOut, String HotelID) throws Exception {
	baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);
	String Date = putDate1(CheckIn);
	String Date1 = putDate1(CheckOut);
	String Details_URL = baseUrl + "/m/hotels/results?city=" + City + "&state=" + State + "&country=" + Country
			+ "&ckIn=" + Date + "&ckOut=" + Date1 + "&noOfRooms=1&ad=1&cd=0#checkAvailability?hotelId=" + HotelID;
	driver.get(Details_URL);
	logURL(driver);
	Thread.sleep(2000);
	return Details_URL;

}

public String mobileCom_Hotel_Details_URL_PartPay(RemoteWebDriver driver, String Domain, String City, String State,
		String Country, int CheckIn, int CheckOut, String HotelID) throws Exception {
	baseUrl = mobileCom_Hotel_Open_URL(driver, Domain);
	String Date = putDate1(CheckIn);
	String Date1 = putDate1(CheckOut);
	String Details_URL = baseUrl + "/m/hotels/results?city=" + City + "&country=" + Country + "&state=" + State
			+ "&ckIn=" + Date + "&ckOut=" + Date1 + "&noOfRooms=1&ad=1&cd=0&pahCCRequired=true#details?hotelId="
			+ HotelID;
	driver.get(Details_URL);
	logURL(driver);
	Thread.sleep(2000);
	return Details_URL;

}

public void mobileCom_Hotel_RoomType(RemoteWebDriver driver) throws Exception {
	elementVisible(driver, getObject("MobileCom_Hotel_DetailsPage_Text"), 2);
	if (elementVisible(driver, getObject("MobileCom_Hotel_RoomType_Pick_Room2"), 2)) {
		safeClick(driver, getObject("MobileCom_Hotel_RoomType_Pick_Room2"));
	} else {
		safeClick(driver, getObject("MobileCom_Hotel_RoomType_Pick_Room1"));
	}
}

public void mobileCom_Hotel_ItineraryPage(RemoteWebDriver driver, String Booking_Type) throws Exception {
	if (Booking_Type.equalsIgnoreCase("PAH")) {
		boolean pahRadioBtn = elementVisible(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"), 5);
		if (pahRadioBtn) {
			safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"));
		} else {
			Reporter.log("No PAH option available for selected hotel");
			Assert.assertTrue(false);
		}
		if (elementVisible(driver, By.xpath("//div[@id='couponCodeBlock']/div/label"), 1)) {
			Reporter.log("Coupon block is displayed after clicking P@H option");
			Assert.assertTrue(false);
		}
		if (!elementVisible(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_Text"), 1)) {
			Reporter.log("PAH text block is not displayed after clicking P@H option");
			Assert.assertTrue(false);
		}
		if (!getText(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_Text"))
				.contains("We will reserve your room until you confirm your sta")) {
			Reporter.log(
					"We will reserve your room until you confirm your stay text is not displayed after clicking P@H option");
			Assert.assertTrue(false);
		}
		if (!getText(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_Text")).contains("Pay@Hotel")) {
			Reporter.log("Pay@Hotel text is not displayed after clicking P@H option");
			Assert.assertTrue(false);
		}

	} else if (elementPresent(driver, By.id("paynow"), 2)) {
		safeClick(driver, By.id("paynow"));
	}
	if (Booking_Type.equalsIgnoreCase("COUPON")) {
		if (elementPresent(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Box"), 1)) {
			safeType(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Box"),
					dataFile.value("HotelB2B_Coupon"));
			safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_Coupon_Check_Button"));
			String Coupon_Sucess_Msg = getText(driver,
					getObject("MobileCom_Hotel_ItineraryPage_Coupon_Success_Msg"));
			if (!Coupon_Sucess_Msg.contains("Great! You just saved")) {
				Assert.assertEquals(true, false);
			}
		}
	} else if (Booking_Type.equalsIgnoreCase("PAHCC")) {
		boolean pahRadioBtn = elementVisible(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"), 5);
		if (pahRadioBtn) {
			safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"));
		} else {
			Reporter.log("No PAH option available for selected hotel");
			Assert.assertTrue(false);
		}
	}

	if (Booking_Type.equalsIgnoreCase("PARTPAY")) {
		elementPresent_log(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Button"),
				"Partpay radio button", 10);
		safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Button"));
		elementPresent_log(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Text"), "Partpay Text message",
				10);
		// safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Button"));
		Reporter.log("Part pay button selected");
		if (!getText(driver, getObject("MobileCom_Hotel_ItineraryPage_Partpay_Text"))
				.contains("Pay a token advance of")) {
			Reporter.log("Pay a token advance of : text is not displayed");
			Assert.assertTrue(false);
		}
	}
	/*
	 * if(!Booking_Type.equalsIgnoreCase("PARTPAY")){ Thread.sleep(2000);
	 * safeClick(driver,
	 * getObject("MobileCom_Hotel_ItineraryPage_PayNowFlow_Continue_Button")); }
	 */
	safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PayNowFlow_Continue_Button"));
}

public void mobileCom_Weekend_Getaway_ItineraryPage(RemoteWebDriver driver) throws Exception {
	// String WeekendItinerary = getText(driver,
	// By.xpath("//*[@id='itinBlock']/div[1]/div[2]/div[5]/nav/ul/li[1]/small"));
	// *[@id="highlights"]/div/div[2]/div[4]/small/a
	// *[@id="highlights"]/div/div[2]/div[5]/nav/ul/li[1]/small
	// Assert.assertEquals("CHECK-IN", WeekendItinerary);
	// Assert.assertEquals("View all amenities", WeekendItinerary);

	safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PayNowFlow_Continue_Button"));
}

public void mobileCom_Hotel_ItineraryPage_PAH(RemoteWebDriver driver) throws Exception {
	boolean pahRadioBtn = elementVisible(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"), 5);
	if (pahRadioBtn) {
		safeClick(driver, getObject("MobileCom_Hotel_ItineraryPage_PAH_RadioButton"));
	} else {
		printInfo("No PAH option available for selected hotel");
	}
}

public void MobileCom_SignInBookFlow(RemoteWebDriver driver) throws Exception {
	elementVisible(driver, getObject("MobileCom_Hotel_Step2SignIn_Text"), 10);
	String Step2Text = getText(driver, getObject("MobileCom_Hotel_Step2SignIn_Text"));
	Assert.assertEquals("Your email address", Step2Text);
	safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Email"), dataFile.value("Mobile_EmailID"));
	safeClick(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_CheckBox"));
	safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Password"), dataFile.value("Mobile_Password"));
	safeClick(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_Button"));

}

public void mobileCom_Hotel_Login(RemoteWebDriver driver, String SignIN) throws Exception {
	elementVisible(driver, getObject("MobileCom_Hotel_Step2SignIn_Text"), 10);
	String Step2Text = getText(driver, getObject("MobileCom_Hotel_Step2SignIn_Text"));
	Assert.assertEquals("Your email address", Step2Text);
	String Host = common.value("host");
	if (Host.equalsIgnoreCase("QA2") || Host.equalsIgnoreCase("hf")) {
		safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Email"), dataFile.value("HotelEmailID"));
	} else if (Host.equalsIgnoreCase("beta") || Host.equalsIgnoreCase("www")) {
		safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Email"), dataFile.value("HotelEmailIDProd"));
	}

	if (SignIN.equalsIgnoreCase("SIGNIN")) {
		if (elementVisible(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_CheckBox"), 1)) {
			safeClick(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_CheckBox"));
			Thread.sleep(2000);
			/*
			 * safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Password"),
			 * dataFile.value("HotelPassword")); Reporter.log("SignIN @ BookStep2");
			 */
			if (Host.equalsIgnoreCase("QA2") || Host.equalsIgnoreCase("hf")) {
				safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Password"),
						dataFile.value("HotelPassword"));
			} else if (Host.equalsIgnoreCase("beta") || Host.equalsIgnoreCase("www")) {
				safeType(driver, getObject("MobileCom_Hotel_Step2SignIn_Password"),
						dataFile.value("HotelPasswordProd"));
			}

		}
	}
	safeClick(driver, getObject("MobileCom_Hotel_Step2SignIn_SignIn_Button"));
}

public void mobileCom_Hotel_TravellerPage(RemoteWebDriver driver) throws Exception {
	String TravellerText = getText(driver, getObject("MobileCom_Hotel_TravellerPage_Text"));
	Assert.assertEquals("Name of guest", TravellerText);
	safeSelect(driver, getObject("MobileCom_Hotel_TravellerPage_Title"), dataFile.value("Title"));
	safeType(driver, getObject("MobileCom_Hotel_TravellerPage_FirstName"), dataFile.value("First_Name"));
	safeType(driver, getObject("MobileCom_Hotel_TravellerPage_LastName"), dataFile.value("Last_Name"));
	try {
		safeType(driver, By.id("payAtHotelMobileNumber"), dataFile.value("Mobile_Number"));
	} catch (Exception e) {
		safeType(driver, By.id("mobileNumber"), dataFile.value("Mobile_Number"));
	}
	safeType(driver, getObject("MobileCom_Hotel_TravellerPage_SpecialRequest"),
			dataFile.value("Hotel_Special_Request"));
	safeClick(driver, getObject("MobileCom_Hotel_TravellerPage_Continue_Button"));
}

public void mobileCom_Hotel_TravellerPage_GST(RemoteWebDriver driver, String GSTType) throws Exception {
	String TravellerText = getText(driver, getObject("MobileCom_Hotel_TravellerPage_Text"));
	Assert.assertEquals("Name of guest", TravellerText);
	safeSelect(driver, getObject("MobileCom_Hotel_TravellerPage_Title"), dataFile.value("Title"));
	safeType(driver, getObject("MobileCom_Hotel_TravellerPage_FirstName"), dataFile.value("First_Name"));
	safeType(driver, getObject("MobileCom_Hotel_TravellerPage_LastName"), dataFile.value("Last_Name"));
	try {
		safeType(driver, By.id("payAtHotelMobileNumber"), dataFile.value("Mobile_Number"));
	} catch (Exception e) {
		safeType(driver, By.id("mobileNumber"), dataFile.value("Mobile_Number"));
	}
	if (GSTType.equalsIgnoreCase("GSTEDIT")) {
		if (!textPresent(driver, "Use GSTIN for this booking (Optional)", 2)) {
			Reporter.log("Use GSTIN for this booking (Optional) Message is not displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//dl/dd[@class='gstCheckLabel']/small/a"));
		if (!elementVisible(driver, By.xpath("//div/dl[@class='horizontal mt10']/dt"), 5)) {
			Reporter.log("GSTIN Text is not displayed");
			Assert.assertTrue(false);
		}
		safeType(driver, By.id("gst_number"), "29 - FASDG1234H - 1Q1");
		safeType(driver, By.xpath("//div/div/div/div/dl[2]/dd/input"), "Cleartrip");
	} else if (GSTType.equalsIgnoreCase("GSTUnselect")) {
		if (!textPresent(driver, "Use GSTIN for this booking (Optional)", 2)) {
			Reporter.log("Use GSTIN for this booking (Optional) Message is not displayed");
			Assert.assertTrue(false);
		}
		Reporter.log("Unchecked GST check Box");
		UnChecking_Checkbox(driver, By.id("use_gst"));
	}

	safeType(driver, getObject("MobileCom_Hotel_TravellerPage_SpecialRequest"),
			dataFile.value("Hotel_Special_Request"));
	safeClick(driver, getObject("MobileCom_Hotel_TravellerPage_Continue_Button"));
}

public String mobileCom_Hotel_PaymentPage(RemoteWebDriver driver, String BookingType, String Trip_Logger_Msg)
		throws Exception {
	String TripID = null;
	if (BookingType.contains("PAHCC")) {
		if (!textPresent(driver,
				"This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now",
				5)) {
			Reporter.log("PAH Creadit Card text message is not displayed");
			Assert.assertTrue(false);
		}
	} else if (BookingType.contains("PARTPAY")) {
		textPresent_Log(driver, "Amount payable", 5);
		elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PartPay_Block"),
				"PartPay block in payment Page", 5);
	} else if (BookingType.equals("PAHOTP")) {
		String OTP = hotelComPAHSendOTP(driver);
		if (!textPresent(driver,
				"We've sent you a verification code to your mobile. Please enter the code to verify.", 10)) {
			Reporter.log(
					"We've sent you a verification code to your mobile. Please enter the code to verify. : message is not displayed");
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_Text"), "", 5);
		elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), "", 5);
		elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"), "", 5);
		Thread.sleep(5000);
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), OTP);
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"));
		elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SuccessMsg"), "", 5);
		if (!textPresent(driver, "Great, verified successfully!", 10)) {
			Reporter.log("Great, verified successfully! : message is not displayed");
			Assert.assertTrue(false);
		}
	} else if (BookingType.equalsIgnoreCase("NBRetryProd")) {
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_Tab"));
		safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
		elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"), 5);
		safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
		textPresent(driver,
				"Please double check the information before trying again or try using a different payment method",
				20);
		String PaymentRetryText = getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
		// Assert.assertEquals("Oops, your payment didn’t work",PaymentRetryText );
		// safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_SC_Tab"));
		// safeType(driver, getObject("MobileCom_Hotel_PaymentPage_SC_CVV"),
		// dataFile.value("MasterCard_CVV"));
		if (!textPresent(driver, "Oops, your payment didn’t work", 1)) {
			Reporter.log("Oops, your payment didn’t work : message is not displayed");
			// Assert.assertTrue(false);
		}

		if (!elementVisible(driver, By.cssSelector("p.subText.important"), 2)) {
			Reporter.log("Error message is not displayed");
			Assert.assertTrue(false);
		}
		if (!getText(driver, By.xpath("//h1")).contains("Oops, your payment did")) {
			Reporter.log("Error message is not displayed");
			Assert.assertTrue(false);
		}
	}
	if (MakePaymentOnlyInQA2) {
		if (BookingType.equalsIgnoreCase("CC") || BookingType.equals("") || BookingType.equals("PARTPAY")) {
			smartClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if (!getText(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text"))
					.contains("Enter your credit card details")) {
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"),
					dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"),
					dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"),
					dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"),
					dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		} else if (BookingType.equalsIgnoreCase("CCCYear")) {
			smartClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if (!getText(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text"))
					.contains("Enter your credit card details")) {
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"),
					dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"),
					dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"), "2025");
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"),
					dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));

			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
			// Oops, your payment didn’t work //div/h1
			// Your payment has not been authorized by your bank. Please check the card
			// number, CVV and expiry date //div[1]/div[1]/div[1]/p
			mobileCom_Hotel_PaymentPage_Authentication(driver, "");
			textPresent(driver, "able to process your payment", 30);
			safeClick(driver, By.id("retryLink"));
			textPresent(driver, "Oops, your payment ", 30);
			elementVisible(driver, By.xpath("//div/h1"), 50);

			if (!getText(driver, By.xpath("//div/h1")).contains("Oops, your payment did")) {
				Reporter.log("Oops, your payment didn’t work : message not displayed");
				Assert.assertTrue(false);
			}
			elementVisible(driver, By.xpath("//div[1]/div[1]/div[1]/p"), 10);
			// if(!getText(driver, By.xpath("//div[1]/div[1]/div[1]/p")).contains("Your
			// payment has not been authorized by your bank")){
			if (!textPresent(driver, "Your payment has not been authorized by your bank", 5)) {
				Reporter.log(
						"Your payment has not been authorized by your bank. Please check the card number, CVV and expiry date : message not displayed");
				Assert.assertTrue(false);
			}
			smartClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
			elementVisible(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text"), 10);
			if (!getText(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Text"))
					.contains("Enter your credit card details")) {
				Reporter.log("Enter your credit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"),
					dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"),
					dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"),
					dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"),
					dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));

		}

		else if (BookingType.equalsIgnoreCase("DC")) {
			smartClick(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Tab"));
			elementVisible(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Text"), 10);
			if (!getText(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Text"))
					.contains("Enter your debit card details")) {
				Reporter.log("Enter your debit card details message is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_DC_CardNumber"),
					dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Expiry_Month"),
					dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_DC_Expiry_Year"),
					dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_DC_CardName"),
					dataFile.value("Master_CC_Name"));
			// driver.findElement(By.id("BillName")).sendKeys("test");
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_DC_CVV"), dataFile.value("Master_CC_CVV"));
		} else if (BookingType.equalsIgnoreCase("PAHCC")) {
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
			textPresent(driver, "Enter your credit card details", 60);
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"),
					dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"),
					dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"),
					dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"),
					dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		} else if (BookingType.equalsIgnoreCase("PAHOTP2")) {
			elementPresent_log(driver, By.id("paymentOpen"), "Credit Card Verification", 5);
			if (!getText(driver, By.id("paymentOpen")).contains("Credit Card Verification")) {
				Reporter.log("Credit Card Verification : message is not displayed");
				Assert.assertTrue(false);
			}
			textPresent(driver, "Enter your credit card details", 60);
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"),
					dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"),
					dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"),
					dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"),
					dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		} else if (BookingType.equalsIgnoreCase("PAHCC2")) {
			elementPresent_log(driver, By.id("paymentOpen"), "Credit Card Verification", 5);
			if (!getText(driver, By.id("paymentOpen")).contains("Credit Card Verification")) {
				Reporter.log("Credit Card Verification : message is not displayed");
				Assert.assertTrue(false);
			}
			textPresent(driver, "Enter your credit card details", 30);
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"),
					dataFile.value("Master_CC_Number"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"),
					dataFile.value("Mobile_MasterCard_Exp_Month"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"),
					dataFile.value("Master_CC_Expiry_Year"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"),
					dataFile.value("Master_CC_Name"));
			safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		} else if (BookingType.equalsIgnoreCase("NBRetry")) {
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_Tab"));
			safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
			elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
			elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"), 5);
			safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
			textPresent(driver, "Oops, your payment didn’t work", 20);
			String PaymentRetryText = getText(driver,
					getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
			Assert.assertEquals("Oops, your payment didn’t work", PaymentRetryText);
			// safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_SC_Tab"));
			// safeType(driver, getObject("MobileCom_Hotel_PaymentPage_SC_CVV"),
			// dataFile.value("MasterCard_CVV"));
			if (!textPresent(driver, "Oops, your payment didn’t work", 5)) {
				Reporter.log("Oops, your payment didn’t work : message is not displayed");
				Assert.assertTrue(false);
			}
			if (!elementVisible(driver, By.cssSelector("p.subText.important"), 2)) {
				Reporter.log("Error message is not displayed");
				Assert.assertTrue(false);
			}
		}
		if (!BookingType.contains("NBRetry")) {
			Thread.sleep(2000);
			safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		}
	}
	return TripID;
}

public String mobileCom_Hotel_Second_PaymentPage(RemoteWebDriver driver, String BookingType, String Trip_Logger_Msg,
		String TripID) throws Exception {

	if (MakePaymentOnlyInQA2) {
		if (BookingType.contains("PAHOTP2")) {
			driver.get(baseUrl + "/account/trips/" + TripID + "/pahcc/review");
			mobileCom_Hotel_PaymentPage(driver, "PAHOTP2", "PAHOTP2");
			hotelCom_PaymentPage_Authentication(driver, "");
			elementPresent_log(driver, By.cssSelector("i.i16.pahBrand"), "P@H icon ", 60);
			elementPresent_log(driver, By.cssSelector("span.status.positive"), "Confirmed Status", 10);

			if (!textPresent(driver, "Your stay is confirmed. Please pay at the hotel.", 1)) {
				Reporter.log("Your stay is confirmed. Please pay at the hotel. : message is not displayed");
				Assert.assertTrue(false);
			}
			if (!getText(driver, By.cssSelector("h3.lightHeader")).contains("Pay@hotel")) {
				Reporter.log("Pay@hotel text is not displayed in rate breakup");
				Assert.assertTrue(false);
			}
			if (!getText(driver, By.cssSelector("span.status.positive")).contains("CONFIRMED")) {
				Reporter.log("Confirmed Status is not displayed , status is displayed as "
						+ getText(driver, By.cssSelector("span.status.positive")));
				Assert.assertTrue(false);
			}
			Reporter.log("PAHCC / OTP - second booking / confiramtion TripID: " + TripID);
		}
		/*
		 * else if(BookingType.contains("PAHCC2")){
		 * driver.get(baseUrl+"/account/trips/"+TripID+"/pahcc/review");
		 * mobileCom_Hotel_PaymentPage(driver, "PAHCC2" , "PAHCC2");
		 * elementPresent_log(driver, By.cssSelector("i.i16.pahBrand"), "P@H icon ",
		 * 60); elementPresent_log(driver, By.cssSelector("span.status.positive"),
		 * "Confirmed Status", 10);
		 * 
		 * if(!textPresent(driver, "Your stay is confirmed. Please pay at the hotel.",
		 * 1)){ Reporter.
		 * log("Your stay is confirmed. Please pay at the hotel. : message is not displayed"
		 * ); Assert.assertTrue(false); } if(!getText(driver,
		 * By.cssSelector("h3.lightHeader")).contains("Pay@hotel")){
		 * Reporter.log("Pay@hotel text is not displayed in rate breakup");
		 * Assert.assertTrue(false); } if(!getText(driver,
		 * By.cssSelector("span.status.positive")).contains("CONFIRMED")){
		 * Reporter.log("Confirmed Status is not displayed , status is displayed as "
		 * +getText(driver, By.cssSelector("span.status.positive"))); System.out.
		 * println("Confirmed Status is not displayed , status is displayed as "+getText
		 * (driver, By.cssSelector("span.status.positive"))); Assert.assertTrue(false);
		 * } }
		 */
	}

	return TripID;

}

public String mobileCom_Hotel_MakePaymentPage1(RemoteWebDriver driver, String Trip_Logger_Msg) throws Exception {
	String TripID = null;
	safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
	textPresent(driver, "Enter your credit card details", 60);
	safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
	safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"),
			dataFile.value("Mobile_MasterCard_Exp_Month"));
	safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"),
			dataFile.value("Master_CC_Expiry_Year"));
	safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
	safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
	if (MakePaymentOnlyInQA2) {
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		textPresent(driver, "Your booking is done", 20);
		elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 30);
		TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
		// System.out.println(TripID+" for "+Trip_Logger_Msg);
		logger.info(TripID + " for " + Trip_Logger_Msg);
		Reporter.log(TripID + " for " + Trip_Logger_Msg, true);
		logURL(driver);
	}
	return TripID;
}

public String mobileCom_Hotel_ConfirmationPage(RemoteWebDriver driver, String BookingType, String Trip_Logger_Msg,
		String Confirmation_Msg) throws Exception {
	String TripID = null;
	if (MakePaymentOnlyInQA2) {
		if (!BookingType.equals("PAHOTP")) {
			mobileCom_Hotel_PaymentPage_Authentication(driver, "");
		}
		if (!elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 30)) {
			Reporter.log("Confirmation page not displayed");
			Assert.assertTrue(false);
		}
		TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
		logger.info(Trip_Logger_Msg + " : " + TripID);
		Reporter.log(Trip_Logger_Msg + " : " + TripID);
		logURL(driver);
		if (BookingType.contains("PAHOTP")) {
			if (!textPresent(driver, Confirmation_Msg, 20)) {
				Reporter.log("Confirmation msg is not correct");
				// Assert.assertTrue(false);
			}
			elementPresent_log(driver, By.xpath("//section/div/div/div/div[2]"), "Confirmation Booking Block", 5);
			elementPresent_log(driver, By.xpath("//section/div/div/div/div[2]/p[2]/a"), "Confirmtion Booking Block",
					5);
		} else if (BookingType.contains("PAHCC")) {
			textPresent(driver, Confirmation_Msg, 20);

		} else if (BookingType.contains("PARTPAY")) {
			if (!textPresent(driver, Confirmation_Msg, 20)) {
				Reporter.log("Confirmation msg is not correct");
				Assert.assertTrue(false);
			}
			if (!textPresent(driver, "Please pay the remaining", 20)) {
				Reporter.log("Please pay the remaining : msg is not displayed");
				Assert.assertTrue(false);
			}
			if (!textPresent(driver, "Call us when you're ready to pay", 20)) {
				Reporter.log("Call us when you're ready to pay: msg is not displayed");
				Assert.assertTrue(false);
			}
			elementPresent_log(driver, By.xpath("//section/div/div/p"), "Confirmation Booking Block", 5);
			if (!getText(driver, By.xpath("//section/div/div/p")).contains("Your trip id is")) {
				Reporter.log("Your trip id is : msg is not displayed");
				Assert.assertTrue(false);
			}
			if (!textPresent(driver, "95 95 333 333", 5)) {
				Reporter.log("95 95 333 333 : phone no is not displayed");
				Assert.assertTrue(false);
			}

		}
	}
	return TripID;
}

public void mobileCom_Hotel_MakePaymentPage_NBRetry_SC(RemoteWebDriver driver, String Trip_Logger_Msg)
		throws Exception {
	Thread.sleep(2000);
	safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_Tab"));
	safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
	if (MakePaymentTrue && !ProductionUrl) {
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		/*
		 * String SbiText =getText(driver,
		 * getObject("MobileCom_Hotel_PaymentPage_NB_SBIText"));
		 * Assert.assertEquals("Password *",SbiText ); safeClick(driver,
		 * getObject("MobileCom_Hotel_PaymentPage_NB_SBI_ClickAbort"));
		 */
		elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
		elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"), 5);
		safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
		textPresent(driver, "Oops, your payment didn’t work", 20);
		String PaymentRetryText = getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
		Assert.assertEquals("Oops, your payment didn’t work", PaymentRetryText);
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_SC_Tab"));
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_SC_CVV"), dataFile.value("MasterCard_CVV"));
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		textPresent(driver, "Your booking is done", 50);
		elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 50);
		String TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
		logger.info(TripID + " for " + Trip_Logger_Msg);
		Reporter.log(TripID + " for " + Trip_Logger_Msg);
	} else {
		printInfo("Either the URL is pointing to production or make payment is false");
	}
}

public void mobileCom_Hotel_MakePaymentPage_NBRetry_CC(RemoteWebDriver driver, String Trip_Logger_Msg,
		boolean international) throws Exception {
	Thread.sleep(5000);
	String PaymentRetryText = null;
	safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_NB_Tab"));
	// safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"),
	// "State Bank of India");
	safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_NB_DropDown"), "Bank of India");
	if (MakePaymentTrue && !ProductionUrl) {
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		/*
		 * String SbiText =getText(driver,
		 * getObject("MobileCom_Hotel_PaymentPage_NB_SBIText"));
		 * Assert.assertEquals("Password *",SbiText ); safeClick(driver,
		 * getObject("MobileCom_Hotel_PaymentPage_NB_SBI_ClickAbort"));
		 */
		elementVisible(driver, By.xpath("//html/body/form/table[1]/tbody/tr/td[1]"), 30);
		elementVisible(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"), 5);
		safeClick(driver, By.xpath("//html/body/form/center[3]/table/tbody/tr/td/a[1]"));
		textPresent(driver, "Oops, your payment didn’t work", 40);
		///// System.out.println(getText(driver,
		///// getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text")));
		if (international) {
			PaymentRetryText = getText(driver, By.xpath("//div[@class='system_messages']/div/div/h1"));
			//// System.out.println(PaymentRetryText);
		} else {
			PaymentRetryText = getText(driver, getObject("MobileCom_Hotel_PaymentPage_NB_PaymentRetry_Text"));
		}

		Assert.assertEquals("Oops, your payment didn’t work", PaymentRetryText);
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"),
				dataFile.value("Master_CC_Number"));
		safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"),
				dataFile.value("Mobile_MasterCard_Exp_Month"));
		safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"),
				dataFile.value("Master_CC_Expiry_Year"));
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
		safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		textPresent(driver, "Your booking is done", 50);
		elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 50);
		String TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
		// System.out.println(TripID+" for "+Trip_Logger_Msg);
		logger.info(TripID + " for " + Trip_Logger_Msg);
		Reporter.log(TripID + " for " + Trip_Logger_Msg);
	} else {
		printInfo("Either the URL is pointing to production or make payment is false");
	}
}

public void mobileCom_Hotel_MakePaymentPage_CancellaionFlow(RemoteWebDriver driver, String Trip_Logger_Msg)
		throws Exception {
	Thread.sleep(2000);
	safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Tab"));
	textPresent(driver, "Enter your credit card details", 60);
	safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardNumber"), dataFile.value("Master_CC_Number"));
	safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Month"),
			dataFile.value("Mobile_MasterCard_Exp_Month"));
	safeSelect(driver, getObject("MobileCom_Hotel_PaymentPage_CC_Expiry_Year"),
			dataFile.value("Master_CC_Expiry_Year"));
	safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CardName"), dataFile.value("Master_CC_Name"));
	safeType(driver, getObject("MobileCom_Hotel_PaymentPage_CC_CVV"), dataFile.value("Master_CC_CVV"));
	if (MakePaymentTrue && !ProductionUrl) {
		safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));
		textPresent(driver, "Your booking is done", 50);
		elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 50);
		String TripID = getText(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"));
		logger.info(TripID + " for " + Trip_Logger_Msg);
		Reporter.log(TripID + " for " + Trip_Logger_Msg);
		mobileCom_Hotel_Cancellationflow(driver, TripID);
	}
}

public void mobileCom_Hotel_Cancellationflow(RemoteWebDriver driver, String TripID) throws Exception {
	/*
	 * Thread.sleep(2000); driver.get(common.value("murl"));
	 * if(elementVisible(driver,By.id("menuBtn"),1)){
	 * safeClick(driver,By.id("menuBtn")); } List<WebElement>
	 * we=driver.findElements(By.xpath("//b")); Test: for(int i=0;i<we.size();i++){
	 * // we.get(i).getText(); // //System.out.println( we.get(i).getText());
	 * if(we.get(i).getText().equalsIgnoreCase("trips")){ we.get(i).click(); break
	 * Test; } } // safeClick(driver, getObject("MobileCom_HomePage_Trips_Tab"));
	 * Thread.sleep(5000); boolean TripPage500 = isElementPresent(driver,
	 * getObject("MobileCom_TripsPage_500Error")); if (TripPage500){
	 * driver.navigate().refresh();
	 * mobileCom_Hotel_CancellationSteps(driver,TripID); }else{
	 * mobileCom_Hotel_CancellationSteps(driver,TripID); }
	 */
	mobileCom_Hotel_CancellationSteps(driver, TripID);
}

public void mobileCom_Hotel_CancellationSteps(RemoteWebDriver driver, String TripID) throws Exception {
	/*
	 * String TripsText =getText(driver,
	 * getObject("MobileCom_HomePage_Trips_PageText")); Assert.assertEquals("Trips",
	 * TripsText); printInfo(TripID);
	 */

	driver.get("https://qa2.cleartrip.com/account/trips/" + TripID + "/cancel");
	Thread.sleep(2000);
	elementVisible(driver, getObject("MobileCom_HomePage_Trips_CancellationPage1_Text"), 20);
	String CancellationText = getText(driver, getObject("MobileCom_HomePage_Trips_CancellationPage1_Text"));
	Assert.assertEquals("Cancellations", CancellationText);
	safeClick(driver, getObject("MobileCom_homePage_Trips_cancellationPage1_RefundToPaymentOption_RadioButton"));
	safeClick(driver, By.className("primary"));
	if (waitElement(driver, getObject("MobileCom_HomePage_Trips_CancellationHotel_TicketsCancelled_Message"), 500)
			&& isElementPresent(driver,
					getObject("MobileCom_HomePage_Trips_CancellationHotel_TicketsCancelled_Message"))) {
		// String CancellationConfirmationText =
		// getText(driver,getObject("MobileCom_HomePage_Trips_CancellationPage3_TicketsCancelled_Message"));
		// Assert.assertEquals("Your hotel booking was cancelled successfully",
		// CancellationConfirmationText );
		/*
		 * Thread.sleep(1000); safeClick(driver,
		 * getObject("MobileCom_HomePage_Trips_CancellationPage3_BackToTrips_Button"));
		 * Thread.sleep(2000); String TripsText2 =getText(driver,
		 * getObject("MobileCom_HomePage_Trips_PageText")); Assert.assertEquals("Trips",
		 * TripsText2);
		 */
	} else {
		Reporter.log("cancellation failed");
	}
}

public void mobileCom_Hotel_Confirmationpage_PriceValidation(RemoteWebDriver driver, int Price_Int)
		throws Exception {
	mobileCom_Hotel_PaymentPage_Authentication(driver, "");
	elementVisible(driver, getObject("MobileCom_Hotel_ConfirmationPage_TripID"), 30);
	String Price_Confirmation = null;
	for (int i = 0; i <= 6; i++) {
		String XPATH = "//div/div/section/div/div/aside/nav/ul/li[3]/dl/dt[" + i + "]";
		String XPATH_Price = "//div/div/section/div/div/aside/nav/ul/li[3]/dl/dd[" + i + "]";

		if (elementPresent_Time(driver, By.xpath(XPATH), 1)) {
			String Price_Type = getText(driver, By.xpath(XPATH));
			// System.out.println(Price_Type);
			if (Price_Type.contains("Total")) {
				Price_Confirmation = getText(driver, By.xpath(XPATH_Price)).replace("Rs. ", "");
				if (Price_Confirmation.contains(",")) {
					Price_Confirmation = Price_Confirmation.replace(",", "");
				}
				break;
			}
		}
	}
	int Price_Confirmation_Int = Integer.parseInt(Price_Confirmation);
	Reporter.log("Price in Trip Confirmation Page : " + Price_Confirmation);
	if (!(Price_Confirmation_Int == Price_Int)) {
		Reporter.log("Price in SRP ' " + Price_Int + " ' does not match in Confirmation  page ' "
				+ Price_Confirmation_Int + " '");
	}
}

public void mobileCom_Hotel_MakePaymentPage_PAHOTP(RemoteWebDriver driver, String Trip_Logger_Msg)
		throws Exception {
	String OTP = hotelComPAHSendOTP(driver);
	if (!textPresent(driver, "We've sent you a verification code to your mobile. Please enter the code to verify.",
			10)) {
		Reporter.log(
				"We've sent you a verification code to your mobile. Please enter the code to verify. : message is not displayed");
		Assert.assertTrue(false);
	}
	elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_Text"), "", 5);
	elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), "", 5);
	elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"), "", 5);
	safeType(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_TextBox"), OTP);
	safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SendButton"));
	elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_PAHOTP_SuccessMsg"), "", 5);
	if (!textPresent(driver, "Great, verified successfully!", 10)) {
		Reporter.log("Great, verified successfully! : message is not displayed");
		Assert.assertTrue(false);
	}
	safeClick(driver, getObject("MobileCom_Hotel_PaymentPage_Payment_Button"));

}

public void mobileCom_Hotel_ItineraryPage_PriceValidation(RemoteWebDriver driver, int Price_Int) throws Exception {
	String Price_Itn = getText(driver, getObject("MobileCom_Hotel_ItineraryPage_Price")).replace(",", "");
	int Price_Itn_int = Integer.parseInt(Price_Itn);
	printInfo("Price in Itinerary Page : " + Price_Itn_int);
	Reporter.log("Price in Itinerary Page : " + Price_Itn_int);
	if (!(Price_Int == Price_Itn_int)) {
		printInfo("Price in SRP ' " + Price_Int + " ' does not match in itenary page ' " + Price_Itn_int + " '");
		Reporter.log("Price in SRP ' " + Price_Int + " ' does not match in itenary page ' " + Price_Itn_int + " '");
	}
}

public int mobileCom_Hotel_Detailspage_PriceValidation(RemoteWebDriver driver) throws Exception {
	// String Price=getText(driver,
	// getObject("MobileCom_Hotel_RoomType_Price")).replace("Rs. ",
	// "").replace(".0", "").replace(",", "");

	// int Price_Int = Integer.parseInt(Price);
	int Price_Int = hotelCom_Price_To_Int(driver, getObject("MobileCom_Hotel_RoomType_Price"));
	printInfo("Price in Hotel Details Page : " + Price_Int);
	Reporter.log("Price in Hotel Details Page : " + Price_Int);
	return Price_Int;
}

// --------------------------------Weekend-Getaways-Scripts--------------------------------------//

public void mobileCom_Weekend_Getaway_HomePage(RemoteWebDriver driver, String From_Date, String To_Date, String Pax,
		String Trip_Logger_Msg) throws Exception {
	Thread.sleep(2000);
	safeClick(driver, getObject("MobileCom_Hotel_HomePage_Hotel_Tab"));
	safeClick(driver, getObject("MobileCom_HomePage_WeekendGetaways_Tab"));
	String WeekendGetwaysText = getText(driver, getObject("MobileCom_WeekendGetaway_SearchPage_Text"));
	Assert.assertEquals(WeekendGetwaysText, "Escape. Every Weekend.");
	safeClick(driver, getObject("MobileCom_WeekendGetaway_SearchPage_YourCity"));
	driver.findElement(getObject("MobileCom_WeekendGetaway_SearchPage_YourCityText")).sendKeys("bangalore");
	Thread.sleep(3000);
	safeClick(driver, getObject("MobileCom_WeekendGetaway_SearchPage_CitySelect"));
	selectCalendarDate(driver, getObject("MobileCom_WeekendGetaway_SearchPage_CheckIn_Date"),
			getObject("MobileCom_WeekendGetaway_SearchPage_CheckIn_Date_NextMonth"), 1, From_Date);
	// safeClick(driver,
	// getObject("MobileCom_WeekendGetaway_SearchPage_CheckIn_Date_CrossButton"));
	selectCalendarDate(driver, getObject("MobileCom_WeekendGetaway_SearchPage_CheckOut_Date"),
			getObject("MobileCom_WeekendGetaway_SearchPage_CheckIn_Date_NextMonth"), 1, To_Date);
	// safeClick(driver,
	// getObject("MobileCom_WeekendGetaway_SearchPage_CheckIn_Date_CrossButton"));
	safeClick(driver, getObject("MobileCom_WeekendGetaway_SearchPage_SearchGetaway_Button"));

}

public void mobileCom_Weekend_Getaway_SRP(RemoteWebDriver driver) throws Exception {
	/*
	 * String srpText = getText(driver,
	 * getObject("MobileCom_WeekendGetaway_SRP_Text"));
	 * Assert.assertEquals("Drive time: upto 10h", srpText);
	 */
	elementNotVisible(driver, By.xpath("//body/div[@class='glassShield']"), 60);
	// Thread.sleep(10000);
	safeClick(driver, getObject("MobileCom_WeekendGetaway_SRP_FilterLink"));
	String city = getText(driver, getObject("MobileCom_WeekendGetaway_SRP_Filter_SecondDestination"));
	if (city != null) {
		safeClick(driver, getObject("MobileCom_WeekendGetaway_SRP_Filter_SecondDestination"));
		safeClick(driver, getObject("MobileCom_WeekendGetaway_SRP_Filter_ApplyButton"));
	} else {
		safeClick(driver, getObject("MobileCom_WeekendGetaway_SRP_Filter_FirstDestination"));
		safeClick(driver, getObject("MobileCom_WeekendGetaway_SRP_Filter_ApplyButton"));
	}

	safeClick(driver, getObject("MobileCom_WeekendGetaway_SRP_Price_SortLink"));
	Thread.sleep(2000);
	safeClick(driver, getObject("MobileCom_WeekendGetaway_SRP_Price_SortLink"));
	Thread.sleep(5000);
	if (elementVisible(driver, getObject("MobileCom_WeekendGetaway_SRP_HotelSelection"), 50)) {
		safeClick(driver, getObject("MobileCom_WeekendGetaway_SRP_HotelSelection"));
	}
}

public void mobileCom_Weekend_Getaway_SelectRoom(RemoteWebDriver driver) throws Exception {
	Thread.sleep(2000);
	Robot robot = new Robot();
	// we are passing 13 as no of attempts to select "select room button" It just
	// selects the button with robot functionality.
	tabRobotNavigator(robot, 13);
	Thread.sleep(2000);
	tabRobotNavigator(robot, 6);
}

// -----------------------------------------Expressway------------------------------------------//

private void tabRobotNavigator(Robot robot, int NumberOfClicks) {
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
}

public void mobileCom_Settings_Tab(RemoteWebDriver driver) throws Exception {
	safeClick(driver, getObject("MobileCom_Settings_Tab"));
	safeClick(driver, getObject("MobileCom_Settings_Expressway_Tab"));
	Thread.sleep(5000);
}

public void mobileCom_Expressway_Delete_Card(RemoteWebDriver driver) throws Exception {
	boolean DeleteLogo = isElementPresent(driver, getObject("MobileCom_Settings_Expressway_Delete_logo"));
	if (DeleteLogo) {
		safeClick(driver, getObject("MobileCom_Settings_Expressway_Delete_logo"));
		driver.switchTo().alert().accept();
		String DeleteSuccessMessage = getText(driver,
				getObject("MobileCom_Settings_Expressway_DeleteCard_Successfull_Message"));
		Assert.assertEquals("Great! your card was deleted successfully", DeleteSuccessMessage);
		Thread.sleep(5000);
		mobileCom_Expressway_Add_Card(driver);
	} else {
		mobileCom_Expressway_Add_Card(driver);
		mobileCom_Expressway_Delete_Card(driver);
	}
}

public void mobileCom_Expressway_Add_Card(RemoteWebDriver driver) throws Exception {
	driver.navigate().refresh();
	safeClick(driver, getObject("MobileCom_Settings_Expressway_Tab"));
	if (isElementPresent(driver, getObject("MobileCom_Settings_Expressway_AddCard"))) {
		String ExpresswayText = getText(driver, getObject("MobileCom_Settings_Expressway_Settings_text"));
		Assert.assertEquals("Expressway settings", ExpresswayText);
		safeClick(driver, getObject("MobileCom_Settings_Expressway_AddCard"));
		addCard_OneMoreCard(driver);
	} else if (isElementPresent(driver, getObject("MobileCom_Settings_Expressway_Tab_StartAddingCard_Button"))) {
		Thread.sleep(3000);
		safeClick(driver, getObject("MobileCom_Settings_Expressway_Tab_StartAddingCard_Button"));
		addCard_FirstTime(driver);
	} else {
		printInfo("the page is not available");
	}
}

public void addCard_FirstTime(RemoteWebDriver driver) throws Exception {
	safeType(driver, getObject("MobileCom_Settings_Expressway_AddCard_CardNumber"),
			dataFile.value("CCNumber_Mobile"));
	safeType(driver, getObject("MobileCom_Settings_Expressway_AddCard_CardName"), dataFile.value("CCName_Mobile"));
	safeSelect(driver, getObject("MobileCom_Settings_CardExpirationMonth_DropDown"),
			dataFile.value("CCExpiryMonth_Mobile"));
	safeSelect(driver, getObject("MobileCom_Settings_CardExpirationYear_DropDown"),
			dataFile.value("CCExpiryYear_Mobile"));
	safeClick(driver, getObject("MobileCom_Settings_Expressway_AddCard_SaveCard_Button"));
	Thread.sleep(5000);
	String AddSuccessMessage = getText(driver,
			getObject("MobileCom_Settings_Expressway_AddCard_Successfull_Message"));
	Assert.assertEquals("Great! your card was saved successfully", AddSuccessMessage);
}

public void addCard_OneMoreCard(RemoteWebDriver driver) throws Exception {
	safeType(driver, getObject("MobileCom_Settings_Expressway_AddCard_CardNumber"),
			dataFile.value("CCNumber_Mobile_Extra"));
	safeType(driver, getObject("MobileCom_Settings_Expressway_AddCard_CardName"),
			dataFile.value("CCName_Mobile_Extra"));
	safeSelect(driver, getObject("MobileCom_Settings_CardExpirationMonth_DropDown"),
			dataFile.value("CCExpiryMonth_Mobile_Extra"));
	safeSelect(driver, getObject("MobileCom_Settings_CardExpirationYear_DropDown"),
			dataFile.value("CCExpiryYear_Mobile_Extra"));
	safeClick(driver, getObject("MobileCom_Settings_Expressway_AddCard_SaveCard_Button"));
	Thread.sleep(5000);
	String AddSuccessMessage = getText(driver,
			getObject("MobileCom_Settings_Expressway_AddCard_Successfull_Message"));
	Assert.assertEquals("Great! your card was saved successfully", AddSuccessMessage);
}

public void mobileCom_Trains_HomePage_SinglePax_Normal(RemoteWebDriver driver, String From_City, String To_City,
		String Class, String Depart_Date, String Adults, String Children, String SeniorMaleAdults,
		String SeniorFemaleAdults, String Trip_Logger_Msg) throws Exception {
	safeClick(driver, getObject("MobileCom_Train_Tab"));
	safeClick(driver, By.xpath("//div[2]/div/a[1]"));
	safeAutocomplete(driver, getObject("MobileCom_Train_TrainSearchPage_FromStation"),
			getObject("MobileCom_Train_TrainSearchPage_Ajax"), From_City);
	safeAutocomplete(driver, getObject("MobileCom_Train_TrainSearchPage_ToStation"),
			getObject("MobileCom_Train_TrainSearchPage_Ajax"), To_City);
	Select ClassOfTravel = new Select(
			driver.findElement(getObject("MobileCom_Train_TrainSearchPage_SelectClass_DropDown")));
	ClassOfTravel.selectByIndex(2);
	selectCalendarDate(driver, getObject("MobileCom_Train_TrainSearchPage_DepartDate"),
			getObject("MobileCom_Train_TrainSearchPage_Calendar_NextMonth"), 1, Depart_Date);
	Select NumberOfAdults = new Select(
			driver.findElement(getObject("MobileCom_Train_TrainSearchPage_Adults_DropDown")));
	NumberOfAdults.selectByVisibleText(Adults);
	Thread.sleep(5000);
	safeClick(driver, getObject("MobileCom_Train_TrainSearchPage_Submit_Button"));
}

public void mobileCom_Trains_SRP(RemoteWebDriver driver) throws Exception {
	safeClick(driver, getObject("MobileCom_Train_SRP_FirstTrain"));
}

public void mobileCom_Trains_Availability(RemoteWebDriver driver) throws Exception {
	String availabiltyPageText = getText(driver, getObject("MobileCom_Train_AvailabilityPage_AvailabiltyText"));
	Assert.assertEquals("Availability for next travel dates", availabiltyPageText);
	safeClick(driver, getObject("MobileCom_Train_AvailabilityPage_FirstDate"));
}

public void mobileCom_Trains_ItineraryPage(RemoteWebDriver driver) throws Exception {
	String ItineraryText = getText(driver, getObject("MobileCom_Train_ItineraryPage_Text"));
	Assert.assertEquals("Review your itinerary", ItineraryText);
	// Thread.sleep(5000);
	if (elementVisible(driver, getObject("MobileCom_Train_ItineraryPage_BookNow_Button"), 5)) {
		safeClick(driver, getObject("MobileCom_Train_ItineraryPage_BookNow_Button"));
	}
}

public void mobileCom_Trains_TravellersPage(RemoteWebDriver driver) throws Exception {
	String TravellersPageText = getText(driver, getObject("MobileCom_Train_TravellerPage_Text"));
	Assert.assertEquals("Traveler Details", TravellersPageText);
	Select AdultTitle = new Select(driver.findElement(getObject("MobileCom_Train_TravellerPage_Adult_DropDown")));
	AdultTitle.selectByVisibleText("Mr.");
	safeType(driver, getObject("MobileCom_Train_TravellerPage_FirstName"), dataFile.value("First_Name"));
	safeType(driver, getObject("MobileCom_Train_TravellerPage_LastName"), dataFile.value("Last_Name"));
	Select Age = new Select(driver.findElement(getObject("MobileCom_Train_TravellerPage_Age_DropDown")));
	Age.selectByValue("15");
	Select Berth = new Select(driver.findElement(getObject("MobileCom_Train_TravellerPage_Berth_DropDown")));
	Berth.selectByVisibleText("Lower");
	safeType(driver, getObject("MobileCom_Train_TravellerPage_CountryCode"), dataFile.value("Country_Code"));
	safeType(driver, getObject("MobileCom_Train_TravellerPage_PhoneNumber"), dataFile.value("Mobile_Number"));
	if (elementVisible(driver, getObject("MobileCom_Train_TravellerPage_ContinueBooking"), 5)) {
		safeClick(driver, getObject("MobileCom_Train_TravellerPage_ContinueBooking"));
	}
}

public void mobileCom_Trains_PaymentPage(RemoteWebDriver driver, String Trip_Logger_Msg) throws Exception {
	String PaymentPage = getText(driver, getObject("MobileCom_Train_PaymentPage_Text"));
	Assert.assertEquals("Make payment", PaymentPage);
	safeClick(driver, getObject("MobileCom_Train_PaymentPage_CreditAndDebitCard"));
	Select CardType = new Select(driver.findElement(getObject("MobileCom_Train_PaymentPage_CardType_DropDown")));
	CardType.selectByVisibleText("MasterCard");
	safeType(driver, getObject("MobileCom_Train_PaymentPage_CardNumber"), dataFile.value("Master_CC_Number"));
	safeType(driver, getObject("MobileCom_Train_PaymentPage_CardName"), dataFile.value("Master_CC_Name"));
	Select CardExpirationMonth = new Select(
			driver.findElement(getObject("MobileCom_Train_PaymentPage_CardExpirationMonth_DropDown")));
	CardExpirationMonth.selectByVisibleText("5");
	Select CardExpirationYear = new Select(
			driver.findElement(getObject("MobileCom_Train_PaymentPage_CardExpirationYear_DropDown")));
	CardExpirationYear.selectByVisibleText("2017");
	safeType(driver, getObject("MobileCom_Train_PaymentPage_CardCVV"), dataFile.value("Master_CC_CVV"));
	if (MakePaymentTrue && !ProductionUrl) {
		safeClick(driver, getObject("MobileCom_Train_PaymentPage_MakePayment_Button"));
		textPresent(driver, "Great, your booking went through", 500);
		elementVisible(driver, getObject("MobileCom_Air_ConfirmationPage_TripID"), 500);
		String TripID = getText(driver, getObject("MobileCom_Train_ConfirmationPage_TripID"));
		logger.info(TripID + " for " + Trip_Logger_Msg);
		Reporter.log(TripID + " for " + Trip_Logger_Msg);
	} else {
		printInfo("Either URL is pointing to Production or Payment flag is true");
	}
}

public HashMap<String, String> getGstDataFromTripXML(RemoteWebDriver driver, String tripID, boolean gstDefault)
		throws Exception {
	Reporter.log("Fetching GST Details from Trip XML for trip ID: " + tripID, true);
	HashMap<String, String> xmlDetails = new HashMap<String, String>();
	HashMap<String, String> gstDetails = new HashMap<String, String>();
	HashMap<String, String> amountDetails = new HashMap<String, String>();
	String tmp1;
	float z = 0;
	String tmp;
	String id;
	String tripId;
	String gstNumber;
	String gstHolderName;
	String gstHolderAddress;
	String gstHolderStateName;
	String gstHolderStateCode;
	String createdAt;
	String updatedAt;
	String tmp2;
	/*
	 * String tripID = getText(driver, getObject("AirCom_Confirmation_TripID"));
	 * Reporter.log("Getting GST details from trip XML for TripId: " + tripID,
	 * true);
	 */

	if (!gstDefault) {
		// GST details entered in UI
		xmlDetails.put("gst-detail", "");
		xmlDetails.put("pricing-elements", "");
		xmlDetails = getTripXmlPrams(tripID, xmlDetails);
		// System.out.println(xmlDetails);
		// System.out.println(xmlDetails.get("pricing-elements"));
		tmp1 = xmlDetails.get("pricing-elements");
		// System.out.println("========================="+tmp1);
		tmp = xmlDetails.get("gst-detail");
		// System.out.println("++++++++"+tmp1.split("<pricing-element>").length);
		int j = 0;
		for (int i = 1; i < tmp1.split("<pricing-element>").length; i++) {

			// System.out.println("amount="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
			// System.out.println("code="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0]);
			if (tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1]
					.split("</code>")[0].contains("true")) {
				amountDetails.put(
						tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1]
								.split("</code>")[0].replace("nil=\"true\">", "no code") + j,
								tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1]
										.split("</amount>")[0]);
				j++;
			} else {
				amountDetails.put(
						tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1]
								.split("</code>")[0].replace(">", ""),
								tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1]
										.split("</amount>")[0]);
			}

			// System.out.println(amountDetails);
			// xmlDetails.put(, value)
			// .split("<amount>")[1].split("</amount>")[0]
		}
		for (String key : amountDetails.keySet()) {
			// System.out.println("z value="+z);
			// System.out.println(amountDetails.get(key));
			z = z + (Float.parseFloat(amountDetails.get(key)));
		}
		System.out.println(amountDetails);
		Reporter.log("cgst=" + amountDetails.get("CGST"), true);
		Reporter.log("sgst=" + amountDetails.get("SGST"), true);

		System.out.println("-----" + z);

		// System.out.println(tmp);
		id = tmp.split("<id>")[1].split("</trip-id>")[0];
		tripId = tmp.split("<trip-id>")[1].split("</trip-id>")[0];
		gstNumber = tmp.split("<gst-number>")[1].split("</gst-number>")[0];
		gstHolderName = tmp.split("<gst-holder-name>")[1].split("</gst-holder-name>")[0];
		// gstHolderAddress =
		// tmp.split("<gst-holder-address>")[1].split("</gst-holder-address>")[0];
		gstHolderStateName = tmp.split("<gst-holder-statename>")[1].split("</gst-holder-statename>")[0];
		gstHolderStateCode = tmp.split("<gst-holder-statecode>")[1].split("</gst-holder-statecode>")[0];
		createdAt = tmp.split("<created-at>")[1].split("</created-at>")[0];
		updatedAt = tmp.split("<updated-at>")[1].split("</updated-at>")[0];

	} else {
		// GST details entered in UI
		xmlDetails.put("pricing-elements", "");
		xmlDetails.put("gst-detail", "");
		xmlDetails = getTripXmlPrams(tripID, xmlDetails);
		tmp1 = xmlDetails.get("pricing-elements");
		tmp = xmlDetails.get("gst-detail");
		int j = 0;
		System.out.println("length===" + tmp1.split("<pricing-element>").length);
		try {
			for (int i = 0; i < tmp1.split("<pricing-element>").length; i++) {
				// System.out.println("amount="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
				// System.out.println("code="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0]);
				if (tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1]
						.split("</code>")[0].contains("true")) {
					amountDetails.put(
							tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1]
									.split("</code>")[0].replace("nil=\"true\">", "no code") + j,
									tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1]
											.split("</amount>")[0]);
					j++;
				} else {
					amountDetails.put(
							tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1]
									.split("</code>")[0].replace(">", ""),
									tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1]
											.split("</amount>")[0]);
				}
			}
		} catch (Exception e) {

			for (int i = 1; i < tmp1.split("<pricing-element>").length; i++) {
				// System.out.println("amount="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1].split("</amount>")[0]);
				// System.out.println("code="+tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1].split("</code>")[0]);
				if (tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1]
						.split("</code>")[0].contains("true")) {
					amountDetails.put(
							tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1]
									.split("</code>")[0].replace("nil=\"true\">", "no code") + j,
									tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1]
											.split("</amount>")[0]);
					j++;
				} else {
					amountDetails.put(
							tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<code")[1]
									.split("</code>")[0].replace(">", ""),
									tmp1.split("<pricing-element>")[i].split("</pricing-element>")[0].split("<amount>")[1]
											.split("</amount>")[0]);
				}
			}

		}

		// System.out.println(amountDetails);

		for (String key : amountDetails.keySet()) {
			// System.out.println("z value="+z);
			// System.out.println(amountDetails.get(key));
			z = z + (Float.parseFloat(amountDetails.get(key)));
		}
		System.out.println(amountDetails);
		Reporter.log("cgst=" + amountDetails.get("CGST"), true);
		Reporter.log("sgst=" + amountDetails.get("SGST"), true);

		System.out.println("-----" + z);
		Assert.assertEquals(Float.parseFloat(amountDetails.get("CGST")) > 0, true);
		Assert.assertEquals(Float.parseFloat(amountDetails.get("SGST")) > 0, true);
		id = tmp.split("<id>")[1].split("</trip-id>")[0];
		tripId = tmp.split("</trip-id>")[1].split("</trip-id>")[0];
		try {
			gstNumber = tmp.split("<gst-number nil=")[1].split("></gst-number>")[0].replaceAll("\"", "");
			gstHolderName = tmp.split("<gst-holder-name nil=")[1].split("></gst-holder-name>")[0].replaceAll("\"",
					"");
			// gstHolderAddress = tmp.split("<gst-holder-address
			// nil=")[1].split("></gst-holder-address>")[0].replaceAll("\"", "");
		} catch (Exception e) {
			gstNumber = tmp.split("<gst-number>")[1].split("</gst-number>")[0].replaceAll("\"", "");
			gstHolderName = tmp.split("<gst-holder-name>")[1].split("</gst-holder-name>")[0].replaceAll("\"", "");
			// gstHolderAddress =
			// tmp.split("<gst-holder-address>")[1].split("</gst-holder-address>")[0].replaceAll("\"",
			// "");
		}
		gstHolderStateName = tmp.split("<gst-holder-statename>")[1].split("</gst-holder-statename>")[0];
		gstHolderStateCode = tmp.split("<gst-holder-statecode>")[1].split("</gst-holder-statecode>")[0];
		createdAt = tmp.split("<created-at>")[1].split("</created-at>")[0];
		updatedAt = tmp.split("<updated-at>")[1].split("</updated-at>")[0];
	}

	gstDetails.put("id", id);
	gstDetails.put("tripId", tripId);
	gstDetails.put("gstNumber", gstNumber);
	gstDetails.put("gstHolderName", gstHolderName);
	// gstDetails.put("gstHolderAddress", gstHolderAddress);
	gstDetails.put("gstHolderStateName", gstHolderStateName);
	gstDetails.put("gstHolderStateCode", gstHolderStateCode);
	gstDetails.put("createdAt", createdAt);
	gstDetails.put("updatedAt", updatedAt);

	return gstDetails;
}

public HashMap<String, String> getTripXmlPrams(String tripID, HashMap<String, String> xmlDetails) throws Exception {
	DefaultHttpClient client = new DefaultHttpClient();
	HttpGet get = new HttpGet(new URI("http://172.17.12.101:9080/trips/" + tripID));
	// HttpGet get = new HttpGet(new
	// URI("http://10.10.21.107:9080/trips/Q1707110060"));
	HttpResponse response = client.execute(get);
	// client.close();

	BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	StringBuffer sb = new StringBuffer();
	String str = "";
	while ((str = br.readLine()) != null) {
		sb.append(str);
	}
	// System.out.println("++++++++++++++++++++++"+sb.toString());

	Set<String> keys = xmlDetails.keySet();
	for (String key : keys) {
		String sb1 = sb.toString();
		sb1 = sb1.split("<" + key + ">")[1].split("</" + key + ">")[0];
		xmlDetails.put(key, sb1);
		Reporter.log(sb1, true);

	}

	return xmlDetails;
}

public void checkDetails(RemoteWebDriver driver, String tripid, String carrier, boolean gstpartialdetails)
		throws Exception {
	boolean gstnum = false;
	boolean gststate = false;
	boolean gststatecode = false;
	HashMap<String, String> hp = new HashMap<String, String>();
	hp = getGstDataFromTripXML(driver, tripid, gstpartialdetails);
	if (!gstpartialdetails) {
		String gstnumber = hp.get("gstNumber").trim();
		String gstholderstatecode = hp.get("gstHolderStateCode").trim();
		// String gstholderaddress=hp.get("gstHolderAddress").trim();
		String gstholdername = hp.get("gstHolderName").trim();
		String gstholderstatename = hp.get("gstHolderStateName").trim();
		// if(carrier.equalsIgnoreCase("spicejet")){
		if (gstnumber.matches("[A-Za-z0-9]+")) {
			gstnum = true;
		}
		if (gstholderstatename.equalsIgnoreCase("Chhattisgarh")
				|| (gstholderstatename.equalsIgnoreCase("Maharashtra"))) {
			gststate = true;
		}
		if (gstholderstatecode.equalsIgnoreCase("27") || (gstholderstatecode.equalsIgnoreCase("22"))) {
			gststatecode = true;
		}
		Assert.assertEquals(true, gstnum, "Mismatch in GST number");
		Assert.assertEquals(gstholdername, "cleartrip", "Mismatch in gst holder name");
		// Assert.assertEquals(gstholderaddress,"cleartrip","gst address mismatch");

		// Assert.assertEquals(true,gststate,"gstholderstatename mismatch");
		// Assert.assertEquals(true,gststatecode,"gstholderstatecode mismatched");

		// }
	} else {
		String gstholderstatename = hp.get("gstHolderStateName").trim();
		// Assert.assertEquals(gstholderstatename,"Chhattisgarh","gstholderstatename
		// mismatch");
	}
}

public void mobileCom_Hotel_PaymentPage_Authentication(RemoteWebDriver driver, String Values) throws Exception {

	for (int i = 0; i <= 25; i++) {
		textPresent(driver, "ACS Emulator", 1);
		if (elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Header"), 1)) {
			break;
		}
		if (elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"), 1)) {
			break;
		}
	}
	Thread.sleep(1000);
	elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"), 10, "Payment Authentication : ");
	smartClick(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"));

}
public String getModifiedDate(String date) {
	Calendar c = Calendar.getInstance();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	c.add(Calendar.DATE, Integer.parseInt(date));
	String convertedDate = dateFormat.format(c.getTime());
	return convertedDate;
}
}
