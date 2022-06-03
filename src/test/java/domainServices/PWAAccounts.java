// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  domainServices;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PWAAccounts extends IndiaHotels {
	public String commonURL;
	public RemoteWebDriver driver;
	private String baseUrl = "https://" + common.value("host") + common.value("url") + "com";
	private String baseUrl_AE = "https://" + common.value("host") + common.value("url") + "ae";
	private String baseUrl_OM = "https://om" + common.value("url") + "com";
	private String baseUrl_KW = "https://kw" + common.value("url") + "com";
	private String baseUrl_BH = "https://bh" + common.value("url") + "com";
	private String baseUrl_QA = "https://qa" + common.value("url") + "com";
	private String baseUrl_SA = "https://www" + common.value("url") + "sa";
	private String baseUrl_ME = "https://me" + common.value("url") + "com";
	public String NetBankingQA = "Bank of India";
	public String NetBankingProd = "Citibank";

	public String getDate(RemoteWebDriver driver, String date) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		c.add(Calendar.DATE, Integer.parseInt(date));
		String convertedDate = dateFormat.format(c.getTime());
		return convertedDate;
	}

	public String URL(RemoteWebDriver driver, String Domain) throws Exception {
		String URL = null;
		if (Domain.equalsIgnoreCase("com")) {
			URL = baseUrl;
		} else if (Domain.equalsIgnoreCase("ae")) {
			URL = baseUrl_AE;
		} else if (Domain.equalsIgnoreCase("bh")) {
			URL = baseUrl_BH;
		} else if (Domain.equalsIgnoreCase("om")) {
			URL = baseUrl_OM;
		} else if (Domain.equalsIgnoreCase("kw")) {
			URL = baseUrl_KW;
		} else if (Domain.equalsIgnoreCase("sa")) {
			URL = baseUrl_SA;
		} else if (Domain.equalsIgnoreCase("me")) {
			URL = baseUrl_ME;
		} else if (Domain.equalsIgnoreCase("qa")) {
			URL = baseUrl_QA;
		}
		return URL;
	}

	public void homePagefromtrips_SignIn(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		Thread.sleep(2000);
		if (elementPresent(driver, By.id("menuBtn"), 1)) {
			safeClick(driver, By.id("menuBtn"));
		}
		safeClick(driver, getObjectPlatform("MobileCom_HomePage_Trip_Link"));
		safeClick(driver, getObjectPlatform("MobileCom_HomePage_signin"));
		elementPresent_log(driver, getObjectPlatform("MobileCom_HomePage_UserName"), "Login page", 10);
		safeType(driver, getObjectPlatform("MobileCom_HomePage_UserName"), dataFile.value("AccountsUserName"));
		safeType(driver, getObjectPlatform("MobileCom_HomePage_Pwd"), dataFile.value("AccountsPassword"));
		if (ProductionUrl) {
			safeType(driver, getObjectPlatform("MobileCom_HomePage_UserName"), dataFile.value("AccountsUserName"));
			safeType(driver, getObjectPlatform("MobileCom_HomePage_Pwd"), dataFile.value("AccountsPassword"));
		}
		Thread.sleep(2000);
		safeClick(driver, getObjectPlatform("MobileCom_HomePage_SignIN_Button"));
		elementPresent_log(driver, getObjectPlatform("MobileCom_HomePage_Trip_Link"), "Trips Link", 10);
		safeClick(driver, getObjectPlatform("MobileCom_HomePage_Trip_Link"));
		textPresent_Log(driver, "Recent Trips", 10);
		Thread.sleep(2000);
	}

	public void homePagefromSettings_SignIn(RemoteWebDriver driver) throws Exception {
		driver.navigate().refresh();
		Thread.sleep(2000);
		if (elementPresent(driver, By.id("menuBtn"), 1)) {
			safeClick(driver, By.id("menuBtn"));
		}
		safeClick(driver, getObjectPlatform("MobileCom_HomePage_Settings_Link"));
		safeClick(driver, getObjectPlatform("MobileCom_HomePage_signin"));
		elementPresent_log(driver, getObjectPlatform("MobileCom_HomePage_UserName"), "Login page", 10);
		safeType(driver, getObjectPlatform("MobileCom_HomePage_UserName"), dataFile.value("AccountsUserName"));
		safeType(driver, getObjectPlatform("MobileCom_HomePage_Pwd"), dataFile.value("AccountsPassword"));
		if (ProductionUrl) {
			safeType(driver, getObjectPlatform("MobileCom_HomePage_UserName"), dataFile.value("AccountsUserName_Trips"));
			safeType(driver, getObjectPlatform("MobileCom_HomePage_Pwd"), dataFile.value("AccountsPassword_Trips"));
		}
		Thread.sleep(2000);
		safeClick(driver, getObjectPlatform("MobileCom_HomePage_SignIN_Button"));
		elementPresent_log(driver, getObjectPlatform("MobileCom_HomePage_Settings_Link"), "Settings Link", 10);
		Reporter.log("Settings link displayed");
		safeClick(driver, getObjectPlatform("MobileCom_HomePage_Settings_Link"));	
		textPresent_Log(driver, "Expressway", 10);
		Reporter.log("Expressway text displayed");
		Thread.sleep(2000);
	}
	
	public void homePagefromSettings_SignOut(RemoteWebDriver driver) throws Exception {
		elementPresent_log(driver, getObjectPlatform("MobileCom_HomePage_SignOUT_Button"), "SignOut Link", 20);
		safeClick(driver, getObjectPlatform("MobileCom_HomePage_SignOUT_Button"));
		Reporter.log("SignOut text is displayed");
		elementPresent_log(driver, getObjectPlatform("MobileCom_HomePage_SignOUT_Text"), "Continue without signing", 20);
	}

	public void myTrips(RemoteWebDriver driver) throws Exception {
		if(ProductionUrl){
		 textPresent_Log(driver,"No trips",10);
		textPresent_Log(driver, "Sign out", 10);
		}else{
		safeClick(driver, getObjectPlatform("MobileCom_Triplist"));
		logURL(driver);
		// driver.findElement(By.xpath("//li[1][@class='item']")).click();
		tripdetailsValidation(driver);
		elementPresent_log(driver, getObjectPlatform("MobileCom_Tripdetails_Support"), "Trip Details Support Button",20);
		// elementPresent_log(driver,By.xpath(".//*[@id='wrap']/ul/li"),"Trip
		// details page",40);
		safeClick(driver, getObjectPlatform("MobileCom_TripDetails_Back_Button"));
		// driver.findElement(By.xpath(".//*[@id='trip1']/h1/a/span")).click();
		textPresent_Log(driver, "Recent Trips", 10);
		textPresent_Log(driver, "Sign out", 10);
	}

	}

	public void tripdetailsValidation(RemoteWebDriver driver) throws InterruptedException {
		if ((driver.findElement(By.xpath(".//*[@id='trip1']/h4[3]/strong"))) != null) {
			textPresent_Log(driver, "Trip ID:", 10);
			Reporter.log("Trip ID found on Trip details page");
			textPresent_Log(driver, "Pricing", 10);
			Reporter.log("Pricing and Payment details found on Trip details page");
		} else {
			Reporter.log("Trip deatils page not loaded");
			Assert.assertTrue(false);
		}
	}
	
	public void myTripsCancellation(RemoteWebDriver driver) throws Exception{
//		if(ProductionUrl){
//			 textPresent_Log(driver,"No trips",10);
//			textPresent_Log(driver, "Sign out", 10);
//			}else{
			safeClick(driver, getObjectPlatform("MobileCom_Triplist"));
			logURL(driver);
			// driver.findElement(By.xpath("//li[1][@class='item']")).click();
			tripdetailsValidation(driver);
			elementPresent_log(driver, getObjectPlatform("MobileCom_Tripdetails_Support"), "Trip Details Support Button",20);
		   safeClick(driver,getObjectPlatform("MobileCom_Canceltrip"));
		    textPresent_Log(driver,"Cancellations",100);
		    Thread.sleep(5000);
		    if(elementVisible(driver,By.xpath("//button[@id='trip_review_cancel']"),20))
		      {
		    	textPresent_Log(driver,"Select Passengers",20);
				elementPresent_log(driver,By.xpath("//button[@id='trip_review_cancel']"),"See refund link",10);
				safeClick(driver,getObjectPlatform("MobileCom_Air_Passenger"));
				safeClick(driver,getObjectPlatform("MobileCom_Air_Reason"));
				safeClick(driver,getObjectPlatform("MobileCom_Air_SeeRefund"));
				textPresent_Log(driver,"Review Refund",20);
				textPresent_Log(driver,"Want to proceed",20);
				safeClick(driver,getObjectPlatform("MobileCom_Refund_Mode"));
				safeClick(driver,getObjectPlatform("MobileCom_Cancel"));
				textPresent_Log(driver,"were cancelled successfully",20);
				elementPresent_log(driver,getObjectPlatform("MobileCom_BacktoTrips"),"Back to trips page link",20);
		      }else{
			
				  textPresent_Log(driver,"Review cancellations",10);
					elementPresent_log(driver,getObjectPlatform("MobileCom_Cancel"),"Cancel link",10);
					safeClick(driver,getObjectPlatform("MobileCom_Refund_Mode"));
					safeClick(driver,getObjectPlatform("MobileCom_Cancel"));
					textPresent_Log(driver,"booking was cancelled successfully",20);
					elementPresent_log(driver,getObjectPlatform("MobileCom_BacktoTrips"),"Back to trips page link",20);
		}
			
}

	public String getURL() {
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

}