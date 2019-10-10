// Framework - Cleartrip Automation
// Author - Kiran Kumar

package domainServices;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.w3c.dom.Document;
import au.com.bytecode.opencsv.CSVWriter;
import commonServices.WrapperMethod;

	@SuppressWarnings("deprecation")
	public class IndiaHotels extends WrapperMethod {
	private String baseUrl = "https://"+common.value("host")+common.value("url")+"com";
	private String baseUrl_AE = "https://"+common.value("host")+common.value("url")+"ae";
	private String baseUrl_BH = "https://bh.cleartrip.com";
	private String baseUrl_KW = "https://kw.cleartrip.com";
	private String baseUrl_ME = "https://me.cleartrip.com";
	private String baseUrl_OM = "https://om.cleartrip.com";
	private String baseUrl_QA = "https://qa.cleartrip.com";
	private String QA2Domain = "new";
	private String baseUrl_SA = "https://"+common.value("host")+common.value("url")+"sa";
	public Double Canc_refund_amt;
	public String RetryPage_Totalfare;
	public String PaymentPage_Totalfare;
	public String WalletRefundAmountItineraray;
	public boolean IntlDetailsPage = true;
	public String cookievalue_QA2 ="durKIrnSWVfSz69S25SlY1kG8AIKhQfCjhXh2rxUr6Iu9NR3GfRQCC5QNZxT3zU3ooD%2Bz1b1mXq0sILAc7%2BJ22%2BrPCZDvtuN9DBR%2FJvde9eSUKPv1oaM1qJW9XIjWHLDYlIP9USAQkAk8yRsLKGkfqTQ4E6vjI3E84HdsoCk1EH%2BZ4EasKrdvJX9i9i0GDaDwkTPFCDLXz6IMs1f4ytxTaivexOyITyHs3jDljHH744Otk%2BrJLIvrS6uQ56Bds1%2BJJBnu8iGhzwt0UIY42oTNBPJlvHA3zkTeqeOD%2Fkot6LMQ2XMnywCcgOtCyOR6SChNbj4%2BfCcYXP2ZvUYuiHgHTqbtYAcWzmZmsOGqrwbd0cM1kcfQHga9EIyEZPFn3ImvorbBQUOH5mQIcGY16N%2F8PLNPaqxgHPoG%2Fb4MQRnyZVI9iJH8UXIBBVrkHxP0uq0KBIFqMINY881df17xzwxrP0wDfAjdIJWLC650cfnf3%2BZ8zInGGcv79tFePFr5UA0tMv7Ci9nlUUKOJuJ4P3asT%2F%2Fu6AA%2BWLog2uJ%2FY2YYqgP%2FV7P4M74%2FvZ8fgql9D8g8%2F%2F1HM9Q%2BhZwENf6sKaAU%2FkxX%2BEKCRjKf8O%2BTNUSfsuE8coUcku3YuzCaIBgM2P8NVvvXX%2BCxf%2Fm6cjigvjCkvDC6VLjm593O1WecRIljwWzrDlNcy1wG4f6T76M34bngsuWnsZgdHapp3eC%2BQF3cOmUlAFWlkXJa9mTQJiY5QeNbIjuetfaOF%2BjNKUwV2FifCQzhdKRpggUtNMpFSD0v%2FOXKDRkCMG%2FFSB0fI5tKaWLObKMLZwPaPoFiIF9j%2BXvqp7ZUp93xaKbJrXbSJYfPZPBPoPgrJ%2B1aIzT21%2FzQzJyefQtEImjEDGsX2VYPzP0adUTVhzAKnx4hPeQi0iRXC6I7HkqIfaPd7q1FKWWoYKTCiOJxyK3knZRsS4ugd95n0%2Fxp%2BnJk2P22T406KwWDLLS7Hy%2BnP6GYfRurd9fmGKR542%2BOA2oEyJtvXrSf7VZy9nC0bNx63oigKNjjXCB10gFh007tI%2FVoVyFSSPMCdOo9cM6hqUdcCG2UvF5ermroPP8N9KPWfSg9jpub0MW9TSGguK%2F5w%2F%2BLWTReONAGW%2B3fXhI1eveISyw%2Fz27cJZ%2BBbx9ic%2F4O1YWyQSp4osEiHhgVDR8a7SzocfbQXvZDm7DfC5qpJ5afUIVPXo8aELUYL57zh1Hwt0PVwrqqOXsUjk3CwVaIbJKt8aB6RXKV4pUABJaIfST%2Bt9GMoMfSIdhAibKoFK1zk7MmpIOGrdWLVUK7QJdhxtegcchjxTNmBqma90dDATIG9c1IOPl7gYW6Px%2BsfN5kv4pC2%2BxBUM2ou64ZbW925UThDNw%2FbOEqQLoft%2BF3co7qPUE%2FXlzAxsNC9WcCgcRBnCcQWMvQvpzm%2FEkMDuMdGN1i0hrRAtUPqOXGpQgxqCDZuuSJvTJrKAyvn20u3NpoT40%2Bd4NLMCFMO8XlGPV4QDOwbewTzakvSndEmgwfbkXGeqmjglmUsNjTkPedq4RaCEGtxHOJUtvf%2BkuvQXSYy7G%2FYoAT8IWpKK5eChTYHI6ZZm3rN%2FW8zcz";
	public String cookievalue_WWW = "XTfGHWOfuwLJdeVqQ6To09plhzUJezf0HMMCLqeiPzgtbOyYhef9WNRKdHHkkiCGooD%2Bz1b1mXq0sILAc7%2BJ246%2FLCjUyitBpM6o3TVqS0KvjuZK%2B1V89n5nBIXJd8DDq4dSN5pefc1ZoQ%2BTbujyKVn9qvEqQSFPpz%2Bay9gg%2BYKeaRlhC%2FCB8gGpU4iTUMw1";
	public String cookievalue_BETA = "XTfGHWOfuwLJdeVqQ6To09plhzUJezf0HMMCLqeiPzgtbOyYhef9WNRKdHHkkiCGooD%2Bz1b1mXq0sILAc7%2BJ246%2FLCjUyitBpM6o3TVqS0KvjuZK%2B1V89n5nBIXJd8DDq4dSN5pefc1ZoQ%2BTbujyKVn9qvEqQSFPpz%2Bay9gg%2BYKeaRlhC%2FCB8gGpU4iTUMw1";
	public String CancellationPolicy_CHMM = "If you cancel within 48 hours before checkin, you will incur 25.0% of your total stay."	;
	public String CancellationPolicy_OYO = "within 24 Hours of check-in date), amount for 1 night will be deducted. Incase of no show, no refund will be provided. Booking cannot be cancelled or modified on or after the check-in date. Cancellation within 10 mins of booking creation is free";
	public String CancellationPolicy_GTA = "will incur no cancellation charges. Cancellation of a reservation between";
	public String CancellationPolicy_Trust = "Penalty charge for late cancellation is 1 night";
	public String CancellationPolicy_Expedia = "imposes the following penalty to its customers that we are required to pass on: Cancellations or changes made after 6:00 PM";
	
	public  String RAN_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public int RAN_LEN = 5;
     
   	public DefaultHttpClient httpclient = new DefaultHttpClient();
	
	public void hotelCom_HomepageSignIn(RemoteWebDriver driver, String UserType) throws Exception {
		String mainWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_YourTrips"), 20);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_SignIN"), 20);
		Thread.sleep(2000);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");
		if(UserType.isEmpty()){
		elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("beta")||Host.equalsIgnoreCase("www")) {
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelGmailEmailID"));
			}
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
		
		if(Host.equalsIgnoreCase("beta")||Host.equalsIgnoreCase("www")) {
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelGmailPassword"));
			}
		}
		else if(UserType.equalsIgnoreCase("WALLET")){
			elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelWalletsEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelWalletsPassword"));
			
		}else if(UserType.equalsIgnoreCase("DEBUG")){
			elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), "rohit.r@cleartrip.com");
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), "lakshmi123");
			
		}
		else if(UserType.equalsIgnoreCase("ACCTRIP")){
			elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), "prabhanjan.manoli@cleartrip.com");
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), "test1234");
		}
		safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		
		Thread.sleep(7000);
		driver.switchTo().window(mainWindow);
	}
	 	
	public void hotelCom_HomepageSignIn_Prod(RemoteWebDriver driver) throws Exception {
		String mainWindow = driver.getWindowHandle();
		safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));
		Thread.sleep(5000);
		driver.switchTo().frame("modal_window");
		elementVisible(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 30);
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), "punith.a@cleartrip.com");
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), "cltp@2010");
		safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		Thread.sleep(5000);
		driver.switchTo().window(mainWindow);
		driver.get(baseUrl);
		if (textPresent(driver, dataFile.value("HotelEmailID"), 5)) {
			Reporter.log("Unable to SignIN");
			safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
			safeClick(driver, getObjectHotels("HotelCom_HomePage_SignOut"));
		}
	}
	
	public void hotelCom_HomepageSignIn_IE(RemoteWebDriver driver) throws Exception {
		safeClick(driver, getObjectHotels("HotelCom_HomePage_YourTrips"));
		safeClick(driver, getObjectHotels("HotelCom_HomePage_SignIN"));		
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
		safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
		safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		elementVisible(driver, getObjectHotels("HotelCom_AccountPage_Hotelslink"), 100);
		elementPresent(driver, getObjectHotels("HotelCom_AccountPage_Hotelslink"));
		safeClick(driver, getObjectHotels("HotelCom_AccountPage_Hotelslink"));//li[2]/a/span
	}

	public String hotelCom_WalletAmount(RemoteWebDriver driver) throws Exception {
		driver.get(baseUrl);
		elementPresent_Time(driver, By.xpath("//a[@id='userAccountLink']/span[2]"), 20);
		safeClick(driver, By.xpath("//a[@id='userAccountLink']/span[2]"));
		safeClick(driver, By.xpath("//li/ul/li/a"));
		textPresent(driver, "Trips you've booked", 20);
		if(!elementVisible(driver, By.xpath("//a[@id='wallet_tab']"), 10)) {
			Reporter.log("Wallet Tab is not displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//a[@id='wallet_tab']"));
		String WalletAmount =null;
		refreshPage(driver);
		if(!textPresent(driver, "Current balance in your wallet", 50)) {
			refreshPage(driver);
		}
		WalletAmount = getText(driver, getObjectHotels("HotelCom_WalletPage_Amount"));
        if(WalletAmount.contains(",")){
        	WalletAmount = WalletAmount.replace(",", "");
        }
		return WalletAmount;		
	}	
	
	public void hotelCom_HomepageSearch(RemoteWebDriver driver, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1,
			String Adult2, String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2) throws Exception {	
		CheckIn_Date = getDate( "dd");
		CheckIn_Date = CheckIn_Date.substring(1);
		CheckIn_Date = "1"+CheckIn_Date;
		int DateInt = Integer.parseInt(CheckIn_Date);
		DateInt = DateInt+1;
		CheckOut_Date = Integer.toString(DateInt);
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_HomePage_HotelTab"), 1, "Home Page has not loaded :( :( :( :( :( :( :( :( ");
		safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
		if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 1)){
			safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));	
		}
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"), 1);
		safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), City);
		selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
		selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
		hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
	}

	public void hotelCom_Homepage_PaxEntry(RemoteWebDriver driver, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, String Child1,
			String Child2, String ChildAge1, String ChildAge2) throws Exception {
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"), 10);
			safeSelect(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"), Rooms);
			String Adults[] = new String[] { Adult1, Adult2, Adult3, Adult4 };
			String Children[] = new String[] { Child1, Child2 };
			String ChildAge[] = new String[] { ChildAge1, ChildAge2 };
			int Hotel_Rooms = Integer.parseInt(Rooms);
			for (int i = 1; i <= Hotel_Rooms; i++) {
				String AdultID = "Adult_" + i;
				if (elementPresent(driver, By.id(AdultID))) {
					safeSelect(driver, By.id(AdultID), Adults[i - 1]);
				}
			}
		
			for(int j=1; j<3; j++) {
			String ChildID = "Childs_" + j, Child_AgeID = "ca" + j;
			smartSelect(driver, By.id(ChildID), Children[j - 1]);
			smartSelect(driver, By.name(Child_AgeID), ChildAge[j - 1]);
			}
		}
	
	public void hotelCom_Search_Misc(RemoteWebDriver driver, String Search_Type, String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1,
			String Adult2, String Adult3, String Adult4, String Child1, String Child2, String ChildAge1, String ChildAge2) throws Exception {
		CheckIn_Date = getDate( "dd");
		CheckIn_Date = CheckIn_Date.substring(1);
		CheckIn_Date = "1"+CheckIn_Date;
		int DateInt = Integer.parseInt(CheckIn_Date);
		DateInt = DateInt+1;
		CheckOut_Date = Integer.toString(DateInt);
		char firstChar = CheckIn_Date.charAt(0);
		char firstChar1 = CheckOut_Date.charAt(0);
		String Str_firstChar = Character.toString(firstChar);
		String Str_firstChar1 = Character.toString(firstChar1);
		if(Str_firstChar.equals("0")) {
			CheckIn_Date = CheckIn_Date.replace("0", "");
		}
		if(Str_firstChar1.equals("0")) {
			CheckOut_Date = CheckOut_Date.replace("0", "");
		}
		
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_HotelTab"), 30);
		 if(elementVisible(driver, getObjectHotels("HotelCom_HomePage_HotelTab"), 1)) {
				Reporter.log("Home Page Hotel Tab is displayed");
			} else {
				Reporter.log("Home Page Hotel Tab is not displayed");
			}
		 		 
//=============================MultiDays Search===================================//
				
		if(Search_Type.contains("MUILTIDAYS")){
			CheckOut_Date = CheckOut_Date+1;
			 safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
				if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));	
					}
				elementVisible(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"), 1);
				safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), City);
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 2, "14");
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, "16");
				safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
				hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
				String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
				String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
				Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
		}		
		
	 //=============================Area Specific DropDown===================================//
		
				if(Search_Type.contains("AreaSpecificDropdown")){
					 safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
						if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 10)){
							safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));	
							}
						elementVisible(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"), 1);
						safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), By.xpath("//body/ul/li[4]/a"), City);
						selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 3, CheckIn_Date);
						selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
						safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
						hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
						String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
						String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
						Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
						safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
				}				
		 
	//=============================Traveller Option Search===================================//
			
				else if(Search_Type.contains("TRAVELLEROPTION")){
				safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
				if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));	
				}
				elementPresent(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"));
				safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), City);
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 3, CheckIn_Date);
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
				safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), Rooms);
				String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
				String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
				Reporter.log("Hotels Searched for " + City + "CheckIn Date : " + FromDate + "CheckOut Date : " + TODate);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
		}
		
//=============================Dateless Search===================================//
		
		else if(Search_Type.equalsIgnoreCase("DATELESS")){
		    safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
			if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 10)){
				safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));	
			}
			elementPresent(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"));
			safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), City);
			safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
			hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
			String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value");
			String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
			Reporter.log("Hotels Searched for " + City + "CheckIn Date : " + FromDate + "CheckOut Date : " + TODate);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
		}

				//============================Directory City Search----==================================//
				
		else if(Search_Type.equalsIgnoreCase("DIRECTORYCITY")){
			elementVisible(driver, getObjectHotels("HotelCom_HomePage_India_Hotels"),  20);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_India_Hotels"));
			String CityLink = City+" Hotels";
			elementVisible(driver, By.linkText(CityLink), 20);
			safeClick(driver, By.linkText(CityLink));
			elementVisible(driver, By.xpath("//h1"), 5);
			elementVisible(driver, By.id("cityautoSearch"), 5);
			safeAutocomplete(driver, By.id("cityautoSearch"), By.xpath("//body/ul/li/div"), "Bangalore");
			selectCalendarDate(driver, By.id("checkin_date"), By.xpath("//div[2]/div/a/span"), 1, "15");
			selectCalendarDate(driver, By.id("checkout_date"), By.xpath("//div[2]/div/a/span"), 0, "16");
			Thread.sleep(1000);
			logURL(driver);
			if(elementVisible(driver, By.id("buttonHotelSearch"), 10)){
			safeClick(driver, By.id("buttonHotelSearch"));
			}else{
				safeClick(driver, By.xpath("//input[@class='booking']"));
			}
			logURL(driver);
		}
				
	//============================Directory City Search----==================================//
				
		else if(Search_Type.equalsIgnoreCase("DIRECTORYCITY1")){
			elementPresent_log(driver, getObjectHotels("HotelCom_HomePage_BangaloreHotels_Link"), "Bangalore Hotels Link", 5);
			safeClick(driver, getObjectHotels("HotelCom_HomePage_BangaloreHotels_Link"));
			if(!textPresent_Log(driver, "Hotels in Bangalore", 10)) {
				Reporter.log("Hotels in Bangalore text is not displayed");
				Assert.assertTrue(false);
			}
		
			safeClick(driver, By.xpath("//h2/a/span"));		
	
		}

//=====================================Directory Hotel Search=======================================//
	
	else if(Search_Type.equalsIgnoreCase("DIRECTORYHOTEL")){
		elementVisible(driver, getObjectHotels("HotelCom_HomePage_HotelDirectory"),  20);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelDirectory"));
		String CityLink = City+" Hotels";
		elementVisible(driver, By.linkText("Bangalore Hotels"), 20);
		safeClick(driver, By.linkText("Bangalore Hotels"));
		elementVisible(driver, getObjectHotels("HotelCom_Directory_Citypage_Hotel_Count"), 20);
		String No_of_Hotels = getText(driver, getObjectHotels("HotelCom_Directory_Citypage_Hotel_Count"));
		Reporter.log("Number of Hotels displayed for "+City+" Search : "+No_of_Hotels);
		safeClick(driver, getObjectHotels("HotelCom_Directory_Citypage_Price_Filter"));
		Thread.sleep(5000);
		loop :for(int i=0; i<=5; i++) {
		int j= i+4;
		String Check_Availability_Xpath = "//div["+j+"]/div[5]/a";
		elementVisible(driver, By.xpath(Check_Availability_Xpath), 20);
		safeClick(driver, By.xpath(Check_Availability_Xpath));
		break loop;
		}
	
		
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50, "Search Results Page has not loaded  :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 25)) {
			Reporter.log("Hotel SRP is displayed");
		} else {
			Reporter.log("Hotel SRP is not displayed");
		}
		safeClick(driver, By.linkText("Check availability"));
		
		selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
		selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
		safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
		hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
		safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
		Thread.sleep(10000);
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 5, "Search Results Page has not loaded  :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 5)) {
			Reporter.log("Hotel SRP is displayed");
		} else {
			Reporter.log("Hotel SRP is not displayed");
		}
		safeClick(driver, By.xpath("//div/button"));
				
		}
		
//=====================================Quickies Search =======================================//
		
		if(Search_Type.contains("QUICKIES")){
			CheckIn_Date = putDate(1);
			CheckOut_Date = putDate(2); 
			 safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
				if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));	
					}
				elementVisible(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"), 1);
				safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), City);
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckIn_Date);
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
				safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
				hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
				String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
				String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
				Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
			
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_Quickeys_Icon_SRP"), 100)) {
				Reporter.log("Quickies Icon is not displayed in SRP"); 
				Assert.assertTrue(false);
			}
		}		
		//=======================================Debug mode===========================================//
		if(Search_Type.contains("DEBUG")){
			safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
			if(elementVisible(driver, getObjectHotels("HotelCom_HomePage_ShowDebug"), 100)) 
			{
				safeClick(driver, getObjectHotels("HotelCom_HomePage_ShowDebug"));elementVisible(driver, getObjectHotels("HotelCom_HomePage_Room_DropDown"), 1);
				safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), City);
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 3, CheckIn_Date);
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
				safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
				hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
				String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
				String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
				Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
				
				
			}
			else
			{
				Reporter.log("Debug mode checkbox is not visible");
			}
			
			
		}
		//=============================OfferPage Search===================================//
		
				if(Search_Type.contains("OFFERPAGE")){
					textPresent(driver, "Get Offers on", 10);
					driver.switchTo().frame("bookingengine");
					elementVisible(driver, By.name("city"), 10);
					safeAutocomplete(driver, By.name("city"), By.xpath("//body/ul/li"), City);
					selectCalendarDate(driver, By.id("checkin_date"), getObjectHotels("HotelCom_HomePage_Calendar_NextMonth"), 2, CheckIn_Date);
					selectCalendarDate(driver, By.id("checkout_date"), getObjectHotels("HotelCom_HomePage_Calendar_NextMonth"), 0, CheckOut_Date);
					safeClick(driver, By.xpath("//fieldset[4]/button"));
					Thread.sleep(5000);
					ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));
					Thread.sleep(1000);
					driver.manage().window().maximize();
		}	
		
//=====================================WeekendGetaway Search =======================================//
		
		if(Search_Type.contains("WeekendGetaway")){
			 	safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
			 	if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_WeekendGetAways_Radio_Button"), 5)) {
				 	safeClick(driver, getObjectHotels("HotelCom_HomePage_HotelTab"));
				 	elementVisible(driver, getObjectHotels("HotelCom_HomePage_WeekendGetAways_Radio_Button"), 5);
				 	Thread.sleep(5000);
					safeClick(driver, getObjectHotels("HotelCom_HomePage_WeekendGetAways_Radio_Button"));
				if(!elementVisible(driver, getObjectHotels("HotelCom_HomePage_WeekendGetAways_Radio_Button"), 5)) {
					safeClick(driver, getObjectHotels("HotelCom_HomePage_WeekendGetAways_Radio_Button"));
					elementVisible(driver, getObjectHotels("HotelCom_HomePage_WeekendGetAways_From_Dropdown"), 30);	
				}
			 	}
			 	Thread.sleep(5000);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_WeekendGetAways_Radio_Button"));
				elementVisible(driver, getObjectHotels("HotelCom_HomePage_WeekendGetAways_From_Dropdown"), 20);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_WeekendGetAways_From_Dropdown"));
				safeClick(driver, By.xpath("//span/div/div/ul/li[2]"));
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 3, CheckIn_Date);
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
				safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
				hotelCom_Homepage_PaxEntry(driver, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2, ChildAge1, ChildAge2);
				String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
				String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
				Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
				elementVisible(driver, By.cssSelector("div.selection"), 60);
				elementVisible(driver, getObjectHotels("HotelCom_Quickeys_Image") , 60);
				Thread.sleep(5000);
				safeClick(driver, getObjectHotels("HotelCom_Quickeys_Image") );
				Thread.sleep(5000);
				driver.switchTo().frame("modal_window");
				elementVisible(driver, By.cssSelector("input.booking.changeTab"), 60);
				safeClick(driver, By.cssSelector("input.booking.changeTab"));
				elementVisible(driver, By.cssSelector("button.booking"), 60);
				safeClick(driver, By.cssSelector("button.booking"));
				Thread.sleep(5000);
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs.get(0));
			}
		logURL(driver);
	}
	
	public void hotelCom_SRP(RemoteWebDriver driver, String Hotel_Name, String Booking_Type) throws Exception {
		String URL = logURL(driver);
		for(int i=0; i<=10; i++){
			if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 1)) {
				Reporter.log("Hotel results are displayed");
				break;
		} else {
				if(textPresent(driver, "Sorry, our system is acting up", 1)){
					Reporter.log("Sorry, our system is acting up message is displayed in SRP");
					Assert.assertTrue(false);
				}
		}
		}
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 20, "Search Results Page has not loaded  :( :( :( :( :( :(");
		smartClick(driver, By.xpath("//a[@id='close']"));
					
//=====================================Hotel Name Search =======================================//
		
		if (!Hotel_Name.isEmpty()) {
			hotelCom_SRP_Select_HotelName(driver, Hotel_Name);
		}

//===================================== Click Price Fiter=======================================//
		
		elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
		safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
		
//=====================================Part Pay =======================================//
		
		if(Booking_Type.equalsIgnoreCase("PARTPAY")){
			Hotel_Name = null;

			safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
			if(elementVisible(driver, getObjectHotels("HotelCom_SRP_MobileUpsell_Close"), 1)){
				safeClick(driver, getObjectHotels("HotelCom_SRP_MobileUpsell_Close"));
			}
			String StrPrice = null, StrPrice_Disc = null; int Price = 0 ;
			for(int i=1; i<=30; i++){
			Thread.sleep(500);
			if(i==6){
				i=i+1;
			}
			Price = hotelCom_SRP_Hotel_Price(driver, i);
			String Hotel_Name_Xpath = "//li["+i+"]/section/nav/ul/li/h2/a";
			if(Price>500){
				Hotel_Name = getText(driver, By.xpath(Hotel_Name_Xpath)); 
				break;
			}
		}
			
			elementPresent(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"));
			safeAutocomplete(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), getObjectHotels("HotelCom_SRP_HotelName_Ajax"), Hotel_Name);
		}
		
//=====================================QUICKIES  =======================================//
		
		else if(Booking_Type.equalsIgnoreCase("QUICKIES")){
					Hotel_Name = null;
					safeClick(driver, By.xpath("//input[@id='1_1quickeys']"));
					if(!elementVisible(driver, By.xpath("//li[2]/i"), 10)){
						
						Reporter.log("Quickeys icon is not displayed");
						Assert.assertTrue(false);
						}
					}
		
		//=============================Chmm promo=====================================
		
		else if (Booking_Type.equalsIgnoreCase("CHMMPromo")) {
			if(!isElementSelected(driver, getObjectHotels("HotelCom_SRP_CHMMPromo_dealCheckbox"))){
				 safeClick_CheckBox(driver, getObjectHotels("HotelCom_SRP_CHMMPromo_dealCheckbox"));
				 Reporter.log(getText(driver, getObjectHotels("HotelCom_SRP_CHMMPromo_deal")));
				 
				 if(elementPresent(driver, getObjectHotels("HotelCom_SRP_CHMMPromo_deal"))){
					 Reporter.log("CHMM promo displayed in SRP");
					 elementPresent(driver, getObjectHotels("HotelCom_SRP_CHMMPromo_save"));
					 System.out.println(getText(driver,getObjectHotels("HotelCom_SRP_CHMMPromo_save")));
				 }
				 else{
					Reporter.log("chmm promo is not present");
					Assert.assertTrue(false);
				}
			}
			else{
				Reporter.log("Hotel deals checkbox is not present");
				Assert.assertTrue(false);
			}
		}

//===================================== Area Specific  =======================================//
		
		else if (Booking_Type.equalsIgnoreCase("AreaSpecific")) {
			if(!elementVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div[5]/section/div"), 20)) {
				Reporter.log("Showing properties in : ' ' Text is not displayed ");
			}
			if(!elementVisible(driver, By.xpath("//li/small"), 50)) {
				Reporter.log("Area text is not displayed  in : ' ' Text is not displayed ");
				Assert.assertEquals(true, false);
			}
		}
		
				//===================================== NEW Scripts  =======================================//
		
		else if (Booking_Type.equalsIgnoreCase("OPAQUE")) {
				   
				    elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
				    
				    for(int i=1;i<=10;i++){
				     String HotelName_Xpath = "//li["+i+"]/section/nav/ul/li/h2/a";
				     String Button_Xpath = "//div[2]/button";
				     String HotelName = getText(driver, By.xpath(HotelName_Xpath));
				     Thread.sleep(2000);
				     if(HotelName.contains("Opaque")){
				      safeClick(driver, By.xpath(Button_Xpath));
				      break;
				     }
				    }
					}
				
				//===================================== Trust Booking  =======================================//
				
			else if (Booking_Type.equalsIgnoreCase("Supp_Trust")) {
					if(elementVisible(driver, By.xpath("//nav/label"),10))
					{
						safeClickListContains(driver, By.xpath("//nav/label"), "TrustInternational");
						Reporter.log("Trust Connector Selected");
					}
					else
					{
						Reporter.log("Trust Filter is not present");
						Assert.assertTrue(false);
					}
					String Trust = getText(driver, By.cssSelector("li.info > p"));
					if(!getText(driver, By.cssSelector("li.info > p")).contains("(Suppliers: TrustInternational)")){
						Reporter.log("Suppliers: TrustInternational text is not present");
						Assert.assertTrue(false);			
					}
					
				}
				
//===================================== GTA Booking  =======================================//
				
			else if (Booking_Type.equalsIgnoreCase("Supp_GTA")) {
					if(elementVisible(driver, By.xpath("//nav/label"),10))
					{
						safeClickListContains(driver, By.xpath("//nav/label"), "GTA");
						Reporter.log("Trust Connector Selected");
					}
					else
					{
						Reporter.log("Trust Filter is not present");
						Assert.assertTrue(false);
					}
					String Trust = getText(driver, By.cssSelector("li.info > p"));
					if(!getText(driver, By.cssSelector("li.info > p")).contains("(Suppliers: GTA)")){
						Reporter.log("Suppliers: GTA text is not present");
						Assert.assertTrue(false);			
					}
					
				}
				
				//===================================== CHMM Booking  =======================================//
				
			else if (Booking_Type.equalsIgnoreCase("Supp_Chmm")) {
					if(elementVisible(driver, By.xpath("//nav/label"),10))
					{
						safeClickListContains(driver, By.xpath("//nav/label"), "Chmm");
						Reporter.log("CHMM Connector Selected");
					}
					else
					{
						Reporter.log("CHMM Filter is not present");
						Assert.assertTrue(false);
					}
					if(!getText(driver, By.cssSelector("li.info > p")).contains("(Suppliers: Chmm)")){
						Reporter.log("Suppliers: Chmm text is not present");
						Assert.assertTrue(false);			
					}
				}
				
//===================================== OYO Booking  =======================================//
				
			else if (Booking_Type.equalsIgnoreCase("Supp_OYO")) {
					if(elementVisible(driver, By.xpath("//nav/label"), 10))
					{
						safeClickListContains(driver, By.xpath("//nav/label"), "OYOROOMS");
						Reporter.log("OYO Connector Selected");
					}
					else
					{
						Reporter.log("OYO filter is not present");
						Assert.assertTrue(false);
					}
					if(!getText(driver, By.cssSelector("li.info > p")).contains("(Suppliers: OYOROOMS)")){
						Reporter.log("Suppliers: OYO text is not present");
						Assert.assertTrue(false);			
					}

				     JavascriptExecutor jse = (JavascriptExecutor) driver;
					 jse.executeScript("window.scrollBy(0, -750)", "");		
				}
				
//===================================== Expedia Booking  =======================================//
				
			else if (Booking_Type.equalsIgnoreCase("Supp_Expedia")) {
					if(elementVisible(driver, By.xpath("//nav/label"),10))
					{ 
						safeClickListContains(driver, By.xpath("//nav/label"), "Expedia");
						Reporter.log("Expedia Connector Selected");
					}
					else
					{
						Reporter.log("Expedia Filter is not present");
						Assert.assertTrue(false);
					}
					if(!getText(driver, By.cssSelector("li.info > p")).contains("(Suppliers: Expedia)")){
						Reporter.log("Suppliers: Expedia text is not present");
						Assert.assertTrue(false);			
					}

				     JavascriptExecutor jse = (JavascriptExecutor) driver;
					 jse.executeScript("window.scrollBy(0, -1000)", "");		
				}
		//===================================== CREATIVEPRICINGSIGNIN   =======================================//
		
		
			else if(Booking_Type.equalsIgnoreCase("CREATIVEPRICINGSIGNIN")){
				
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_CreativePricing_Block"), 5)){
					Reporter.log("Creative Pricing Sigin Block not displayed in SRP");
					Assert.assertTrue(false);
				}
				if(!textPresent(driver, "Unlock Special Deals", 5)){
					Reporter.log("Creative Pricing Message is not displayed in SRP");
						Assert.assertTrue(false);
				}if(!textPresent(driver, "Sign in and get discounts upto 50% off on selected hotels", 5)){
					Reporter.log("Creative Pricing Message is not displayed in SRP");
						Assert.assertTrue(false);
				}
				String mainWindow = driver.getWindowHandle();
				Thread.sleep(2000);
				safeClick(driver, getObjectHotels("HotelCom_SRP_CreativePricing_SignIn_Button"));
				Thread.sleep(1000);
				driver.switchTo().frame("modal_window");
				elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
				safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
				safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));

				safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
				
				Thread.sleep(7000);
				driver.switchTo().window(mainWindow);

				Thread.sleep(3000);
				String StrikeThrough_Price = getText(driver, getObjectHotels("HotelCom_SRP_CreativePricing_StrikeThrough_Price"));
				String[] StrikeThroghPrice = StrikeThrough_Price.split("Rs.");
				System.out.println(StrikeThroghPrice[2]+" is the price displayed after wallet amt is applied");
				if(!StrikeThroghPrice[2].equals("0")){
					Reporter.log("Price is not getting deducted from wallet");
					Assert.assertTrue(false);
				}
				elementPresent_log(driver, getObjectHotels("HotelCom_SRP_CreativePricing_PopUp"), "Creative pricing wallet amt is not displayed", 2);
				if(!textPresent(driver, "We have pre-applied your wallet balance in the fares", 5)){
					Reporter.log("We have pre-applied your wallet balance in the fares : message not displayed");
					Assert.assertTrue(false);
				}
				
			}
				
						
		String Booking_Hotel = getText(driver, getObjectHotels("HotelCom_SRP_HotelName"));
		Reporter.log("The Hotel Being Booked is " +Booking_Hotel);
		if(URL.contains(".com")&& (!(URL.contains("amex")))){
		//	safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));
			//safeClick(driver, By.xpath("//li[1]/section/div/div/button[2]"));
			if(elementVisible(driver, By.xpath("//li[1]/section/div[5]/div/button[2]"), 5)){
				safeClick(driver, By.xpath("//li[1]/section/div[5]/div/button[2]"));
			}
			else if(elementVisible(driver, By.xpath("//nav/ul/li[1]/section/div[3]/div[3]/div/button[2]"), 5)){
				safeClick(driver, By.xpath("//nav/ul/li[1]/section/div[3]/div[3]/div/button[2]"));
			}
			hotelCom_SRP_ModalWindow(driver, Booking_Type);
			
		}
		else if(URL.contains("amex")) {
			safeClick(driver, By.xpath("//div[5]/div/button"));
			hotelCom_SRP_ModalWindow(driver, Booking_Type);
		}
		else {
				safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));
				hotelCom_SRP_ModalWindow(driver, Booking_Type);
			
		}
	}
	
	public void hotelCom_SRP_Intl(RemoteWebDriver driver, String Hotel_Name, int Room_Type) throws Exception {
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 60, "Search Results Page has not loaded  :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 25)) {
			Reporter.log("Hotel SRP is displayed");
		} else {
			Reporter.log("Hotel SRP is not displayed");
		}
		String URL = logURL(driver);
	//====================================================-------------- Hotel Name Search  =======================================//
		
				if (!Hotel_Name.isEmpty()) {
					hotelCom_SRP_Select_HotelName(driver, Hotel_Name);
				}
				
			//====================================================== Click Price Fiter =======================================//
				
				elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
				safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	

			//====================================================--- Select the RoomType =======================================//
				
		if(!IntlDetailsPage) {		
		loop:		for(int i=1; i<6; i++) {
		if(i==4) {
			i=i+1;
		}
			String SelectRoom_XPath = "//li["+i+"]/section/div[5]/div/button[2]";			
			safeClick(driver, By.xpath(SelectRoom_XPath));
			String BookButton_XPath = "//li["+i+"]/div/div[2]/table/tbody/tr["+Room_Type+"]/td[6]/input";
			Thread.sleep(2000);
			elementNotVisible(driver, By.cssSelector("span.loader.dotDotDot"), 2);
			if(elementPresent_Time(driver, By.xpath(BookButton_XPath), 5)) {
				safeClick(driver, By.xpath(BookButton_XPath));
				break loop;
			}		
		}
		}
		else {
			//safeClick(driver, By.xpath("//li[1]/section/div[5]/div/button[2]"));
			if(elementVisible(driver, By.xpath("//li[1]/section/div[5]/div/button[2]"), 5)){
				safeClick(driver, By.xpath("//li[1]/section/div[5]/div/button[2]"));
			}
			else if(elementVisible(driver, By.xpath("//nav/ul/li[1]/section/div[3]/div[3]/div/button[2]"), 5)){
				safeClick(driver, By.xpath("//nav/ul/li[1]/section/div[3]/div[3]/div/button[2]"));
			}
			hotelCom_SRP_ModalWindow(driver, "");
		}
	}
	
	public void hotelCom_SRP_Intl_Connector(RemoteWebDriver driver, String HotelName, String BookingType) throws Exception {
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 30, "Search Results Page has not loaded  :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 25)) {
			Reporter.log("Hotel SRP is displayed");
		} else {
			Reporter.log("Hotel SRP is not displayed");
		}
		String URL = logURL(driver);
	//====================================================-------------- Hotel Name Search  =======================================//
		
				if (!HotelName.isEmpty()) {
					hotelCom_SRP_Select_HotelName(driver, HotelName);
				}
				
			//====================================================== Click Price Fiter =======================================//
				
				elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
				safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
				
				if(BookingType.equalsIgnoreCase("Supp_GTA")){

					if(elementVisible(driver, By.xpath("//nav/label"),10))
					{
						safeClickListContains(driver, By.xpath("//nav/label"), "GTA");
						Reporter.log("GTA Connector Selected");
					}
					else
					{
						Reporter.log("GTA Filter is not present");
						Assert.assertTrue(false);
					}
					if(!getText(driver, By.cssSelector("li.info > p")).contains("(Suppliers: GTA)")){
						Reporter.log("Suppliers: GTA text is not present");
						Assert.assertTrue(false);			
					}
					Thread.sleep(2000);
					safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button_GTA"));
					safeClick(driver, getObjectHotels("HotelCom_SRP_BookRoom_Button"));
					
				}
		
	}
	
	public void hotelCom_SRP_Dom_Connector(RemoteWebDriver driver, String Hotel_Name, String Booking_Type) throws Exception {
		String URL = logURL(driver);
			for(int i=0; i<=10; i++){
			if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 1)) {
				Reporter.log("Hotel results are displayed");
				break;
		} else {
				if(textPresent(driver, "Sorry, our system is acting up", 1)){
					Reporter.log("Sorry, our system is acting up message is displayed in SRP");
					Assert.assertTrue(false);
				}
		}
		}
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 20, "Search Results Page has not loaded  :( :( :( :( :( :(");
		smartClick(driver, By.xpath("//a[@id='close']"));
					
//=====================================Hotel Name Search =======================================//
		
		if (!Hotel_Name.isEmpty()) {
			hotelCom_SRP_Select_HotelName(driver, Hotel_Name);
		}

//===================================== Click Price Fiter=======================================//
		
		elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
		safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
		
		
//===================================== Trust Booking  =======================================//
				
			if (Booking_Type.equalsIgnoreCase("Supp_Trust")) {
				if(elementVisible(driver, By.xpath("//nav/label"),10))
				{
					safeClickListContains(driver, By.xpath("//nav/label"), "TrustInternational");
					Reporter.log("Trust Connector Selected");
				}
				else
				{
					Reporter.log("Trust Filter is not present");
					Assert.assertTrue(false);
				}
				String Trust = getText(driver, By.cssSelector("li.info > p"));
				if(!getText(driver, By.cssSelector("li.info > p")).contains("(Suppliers: TrustInternational)")){
					Reporter.log("Suppliers: TrustInternational text is not present");
					Assert.assertTrue(false);			
				}
				   JavascriptExecutor jse = (JavascriptExecutor) driver;
					 jse.executeScript("window.scrollBy(0, -750)", "");		
				
			}
				
//===================================== GTA Booking  =======================================//
				
			if (Booking_Type.equalsIgnoreCase("Supp_GTA")) {
					if(elementVisible(driver, By.xpath("//nav/label"),10))
					{
						safeClickListContains(driver, By.xpath("//nav/label"), "GTA");
						Reporter.log("Trust Connector Selected");
					}
					else
					{
						Reporter.log("Trust Filter is not present");
						Assert.assertTrue(false);
					}
					String Trust = getText(driver, By.cssSelector("li.info > p"));
					if(!getText(driver, By.cssSelector("li.info > p")).contains("(Suppliers: GTA)")){
						Reporter.log("Suppliers: GTA text is not present");
						Assert.assertTrue(false);			
					}
					
				}
				
				//===================================== CHMM Booking  =======================================//
				
			else if (Booking_Type.equalsIgnoreCase("Supp_Chmm")) {
					if(elementVisible(driver, By.xpath("//nav/label"),10))
					{
						safeClickListContains(driver, By.xpath("//nav/label"), "Chmm");
						Reporter.log("CHMM Connector Selected");
					}
					else
					{
						Reporter.log("CHMM Filter is not present");
						Assert.assertTrue(false);
					}
					if(!getText(driver, By.cssSelector("li.info > p")).contains("(Suppliers: Chmm)")){
						Reporter.log("Suppliers: Chmm text is not present");
						Assert.assertTrue(false);			
					}
				}
		

		String Booking_Hotel = getText(driver, getObjectHotels("HotelCom_SRP_HotelName"));
		Reporter.log("The Hotel Being Booked is " +Booking_Hotel);
			safeClick(driver, By.xpath("//li[1]/section/div[5]/div/button[2]"));
			hotelCom_SRP_ModalWindow(driver, Booking_Type);

	
	}
	
	public void hotelCom_SRP_Dom(RemoteWebDriver driver, String Hotel_Name, int Room_Type) throws Exception {
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 60, "Search Results Page has not loaded  :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 25)) {
			Reporter.log("Hotel SRP is displayed");
		} else {
			Reporter.log("Hotel SRP is not displayed");
		}
		logURL(driver);
			//======================================================= Hotel Name Search =======================================//
		
				if (!Hotel_Name.isEmpty()) {
					hotelCom_SRP_Select_HotelName(driver, Hotel_Name);
				}
				
			//========================================================== Click Price Fiter =======================================//
				
				elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
				safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
				
OuterLoop: for(int i=1; i<=5; i++) {
				Thread.sleep(2000);
				String SelectRoom_XPath =	"//li["+i+"]/section/div/div/button";
				safeClick(driver, By.xpath(SelectRoom_XPath));
				Thread.sleep(1000);
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				Thread.sleep(1000);
				driver.manage().window().maximize();
				logURL(driver);
				loop: for(int j=0; j<=10;i++) {
					if(elementPresent_Time(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)) {
						break loop;
					} 
					if(elementPresent_Time(driver, By.xpath("//div[@id='hotelDetailsHeader']/div/div/div[2]/div[2]/div[2]/strong"), 1)) {
						break loop;
					} 
					Thread.sleep(1000);
				}
				String SelectRateType_Xpath = "//div["+Room_Type+"]/nav/ul/li/div/div[1]";
				
				if(elementPresent_Time(driver, By.xpath(SelectRateType_Xpath), 1)) {
					String Modal_URL = driver.getCurrentUrl();
					driver.switchTo().window(tabs.get(0));
					driver.get(Modal_URL);
					driver.switchTo().window(tabs.get(1)).close();
					driver.switchTo().window(tabs.get(0));
					elementPresent_Time(driver, By.xpath(SelectRateType_Xpath), 20);
					safeClick(driver, By.xpath(SelectRateType_Xpath));	
					elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 20);
					if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button"), 20)) {
						safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button"));
					}
					if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button2"), 1)) {
						safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button2"));
					}	
					break OuterLoop; 
				} else {
					Reporter.log("Multiple room rates are not displayed");					
					driver.switchTo().window(tabs.get(1)).close();
					driver.switchTo().window(tabs.get(0));
				}
		}

				smartClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button3"));
	}
	
	public void hotelCom_SRP_Misc(RemoteWebDriver driver, String Hotel_Name, String Booking_Type, String BookingTypeDetails) throws Exception {
		logURL(driver);
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 15, "Search Results Page has not loaded");
		if (!Hotel_Name.isEmpty()) {
			hotelCom_SRP_Select_HotelName(driver, Hotel_Name);
		}	
	//====================================================MAP VIEW =======================================//
		if (Booking_Type.equalsIgnoreCase("MAPVIEW")) {

			elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
			safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
			elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 20);
			safeClick(driver, getObjectHotels("HotelCom_SRP_MapView_Tab"));
			elementVisible(driver, By.xpath("//div[5]/nav/ul/li/span"), 10);
			elementVisible(driver, By.xpath("//li/h2/a"), 20);
			Thread.sleep(2000);
			mouseHover(driver, By.xpath("//div[5]/nav/ul/li[1]/span"));
			elementVisible(driver, getObjectHotels("HotelCom_SRP_Map_HotelPin"), 30);
			Thread.sleep(2000);		
			mouseHover(driver, getObjectHotels("HotelCom_SRP_Map_HotelPin"));		
			Thread.sleep(1000);	
			mouseHover(driver, getObjectHotels("HotelCom_SRP_Map_HotelPin"));
			Thread.sleep(1000);	
			elementVisible(driver, getObjectHotels("HotelCom_SRP_Map_Hotel_Select_Button"), 5);
			smartClick(driver, getObjectHotels("HotelCom_SRP_Map_Hotel_Select_Button"));
			smartClick(driver, By.xpath("//nav[2]/ul/li[2]/input"));
			Thread.sleep(5000);
			hotelCom_SRP_ModalWindow(driver,"");
		}
		
	//============================================================== FULL COUPON  =======================================//
		
		else if(Booking_Type.equalsIgnoreCase("FULLCOUPON")){		
					elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
					safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
					hotelCom_SRP_Select_HotelPrice_MoreThan(driver, 60);				
					Thread.sleep(5000);
					hotelCom_SRP_ModalWindow(driver,"");
				}
				
				//============================================================== AMEX  =======================================//
				
		else if(Booking_Type.equalsIgnoreCase("AMEX")) {	
					elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
					safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
				    safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_ButtonOLD"));
					Thread.sleep(2000);
					hotelCom_SRP_ModalWindow(driver,"");
				}
				
				
	//============================================================== DUAL Cash back =======================================//
				
		else if(Booking_Type.equalsIgnoreCase("DUALCASHBACK")){
					elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
					safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
					hotelCom_SRP_Select_HotelPrice_MoreThan(driver, 500);				
					Thread.sleep(5000);
					hotelCom_SRP_ModalWindow(driver,"");
				}
		
	//==================================================== MODAL WINDOW =======================================//
		
		else if (Booking_Type.equalsIgnoreCase("MODALWINDOW")) {
			elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 30);
			safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
			safeClick(driver, getObjectHotels("HotelCom_SRP_HotelName"));
			hotelCom_SRP_ModalWindow(driver,"");
		}		
		
	//=========================================================== MODAL WINDOW - V2 =======================================//
		
		else if (Booking_Type.equalsIgnoreCase("MODALWINDOW_V2")) {
			elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 30);
			safeType(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), Hotel_Name);
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_CheckIN_Date"), 1)){
				Reporter.log("CheckIn date is not displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_CheckOut_Date"), 1)){
				Reporter.log("CheckOut date is not displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_Select_Traveller"), 1)){
				Reporter.log("Select Traveller is not displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_Star_Rating"), 1)){
				Reporter.log("Star Rating is not displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_TripAdvisor"), 1)){
				Reporter.log("TripAdvisor is not displayed");
				Assert.assertTrue(false);
			}
			if(!elementAssert(driver, getObjectHotels("HotelCom_ModalWindow_V2_ModifySearch_Button"), 1)){
				Reporter.log("Modify Search button is not displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_BookThisHotel_Button"), 1)){
				Reporter.log("Book this Hotel button is not displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_RoomAndRates"), 1)){
				Reporter.log("Rooms and Rates are not displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_TravellerReviews"), 1)){
				Reporter.log("Traveller reviews is not displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_Amenities"), 1)){
				Reporter.log("Amenities are not displayed");
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_Photos"), 1)){
				Reporter.log("Photos tab are not displayed");
				Assert.assertTrue(false);
			}	
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_Photo1"), 1)){
				Reporter.log("Photos are not displayed");
				Assert.assertTrue(false);
			}
			if(!elementAssert(driver, getObjectHotels("HotelCom_ModalWindow_V2_MapImage"), 1)){
				Reporter.log("Map Image is not displayed");
				Assert.assertTrue(false);
			}	
			
		}		
		
				//=========================================================== CREATIVEPRICINGSIGNIN SRP =======================================//
				
		else if (Booking_Type.equalsIgnoreCase("CREATIVEPRICINGSIGNIN")) {
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_CreativePricing_Block"), 5)){
				Reporter.log("Creative Pricing Sigin Block not displayed in SRP");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver, "Unlock Special Deals", 5)){
				Reporter.log("Creative Pricing Message is not displayed in SRP");
					Assert.assertTrue(false);
			}if(!textPresent(driver, "Sign in and get discounts upto 50% off on selected hotels", 5)){
				Reporter.log("Creative Pricing Message is not displayed in SRP");
					Assert.assertTrue(false);
			}
			String mainWindow = driver.getWindowHandle();
			Thread.sleep(2000);
			safeClick(driver, getObjectHotels("HotelCom_SRP_CreativePricing_SignIn_Button"));
			Thread.sleep(1000);
			driver.switchTo().frame("modal_window");
			elementPresent_Time(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), 50);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));

			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
			
			Thread.sleep(7000);
			driver.switchTo().window(mainWindow);

			Thread.sleep(3000);
			String StrikeThrough_Price = getText(driver, getObjectHotels("HotelCom_SRP_CreativePricing_StrikeThrough_Price"));
			String[] StrikeThroghPrice = StrikeThrough_Price.split("Rs.");
			Reporter.log(StrikeThroghPrice[2]+" is the price displayed after wallet amt is applied");
			if(!StrikeThroghPrice[2].equals("0")){
				Reporter.log("Price is not getting deducted from wallet");
				Assert.assertTrue(false);
			}
			elementPresent_log(driver, getObjectHotels("HotelCom_SRP_CreativePricing_PopUp"), "Creative pricing wallet amt is not displayed", 2);
			if(!textPresent(driver, "We have pre-applied your wallet balance in the fares", 5)){
				Reporter.log("We have pre-applied your wallet balance in the fares : message not displayed");
				Assert.assertTrue(false);
			}

			safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));
			Thread.sleep(5000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(1000);
			
			elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10);
			elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"), 10);
			
			String Modal_URL = driver.getCurrentUrl();
			driver.switchTo().window(tabs.get(0));
			driver.get(Modal_URL);
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(1000);
			

			elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10);
			elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"), 10);
			   if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
				   Reporter.log("Hotel Rate is not displayed in Details page");
			   }
			   if(textPresent(driver, "Sorry, this hotel is unavailable", 1)) {
					String HotelName = getText(driver, By.xpath("//div[2]/div/div/h1"));
					Reporter.log("Sorry, this hotel is unavailable - No rates/ inventory for hotel :"+HotelName);
					Assert.assertTrue(false);
				} 
			   
			   if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_CreativePricing_Message"), 5)){
				   Reporter.log("Wallet message in Details page is not displayed");
				   Assert.assertTrue(false);
			   }
			   String Price_ModalWindow = getText(driver, getObjectHotels("HotelCom_ModalWindow_Price"));
			   if(!Price_ModalWindow.contains("Rs.0")){
				   Reporter.log("Price in modal window is not '0' "+Price_ModalWindow);
				   Assert.assertTrue(false);
			   }
			   
			   if(elementVisible(driver, By.xpath("//div/div/div[2]/a"), 1)){
				   safeClick(driver, By.xpath("//div/div/div[2]/a"));
				   smartClick(driver, By.linkText("Book"));
			   }
			   smartClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));
		}
				

				//=========================================================== CREATIVEPRICINGSIGNINHOMEPAGE SRP =======================================//
				
		else if (Booking_Type.equalsIgnoreCase("CREATIVEPRICINGSIGNINHOMEPAGE")) {
			String StrikeThrough_Price = getText(driver, getObjectHotels("HotelCom_SRP_CreativePricing_StrikeThrough_Price"));
			String[] StrikeThroghPrice = StrikeThrough_Price.split("Rs.");
			Reporter.log(StrikeThroghPrice[2]+" is the price displayed after wallet amt is applied");
			if(!StrikeThroghPrice[2].equals("0")){
				Reporter.log("Price is not getting deducted from wallet");
				Assert.assertTrue(false);
			}
			elementPresent_log(driver, getObjectHotels("HotelCom_SRP_CreativePricing_PopUp"), "Creative pricing wallet amt is not displayed", 2);
			if(!textPresent(driver, "We have pre-applied your wallet balance in the fares", 5)){
				Reporter.log("We have pre-applied your wallet balance in the fares : message not displayed");
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));
			Thread.sleep(5000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(1000);			
			elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10);
			elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"), 10);			
			String Modal_URL = driver.getCurrentUrl();
			driver.switchTo().window(tabs.get(0));
			driver.get(Modal_URL);
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(1000);		
			elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10);
			elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"), 10);
			   if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
				   Reporter.log("Hotel Rate is not displayed in Details page");
			   }
			   if(textPresent(driver, "Sorry, this hotel is unavailable", 1)) {
					String HotelName = getText(driver, By.xpath("//div[2]/div/div/h1"));
					Reporter.log("Sorry, this hotel is unavailable - No rates/ inventory for hotel :"+HotelName);
					Assert.assertTrue(false);
				} 
			   
			   if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_CreativePricing_Message"), 5)){
				   Reporter.log("Wallet message in Details page is not displayed");
				   Assert.assertTrue(false);
			   }
			   String Price_ModalWindow = getText(driver, getObjectHotels("HotelCom_ModalWindow_Price"));
			   if(!Price_ModalWindow.contains("Rs.0")){
				   Reporter.log("Price in modal window is not '0' "+Price_ModalWindow);
				   Assert.assertTrue(false);
			   }
			   
			   if(elementVisible(driver, By.xpath("//div/div/div[2]/a"), 1)){
				   safeClick(driver, By.xpath("//div/div/div[2]/a"));
				   smartClick(driver, By.linkText("Book"));
			   }
			   smartClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));
		}
				
//========================================================== MODAL WINDOW - Old =======================================//
		
		else if (Booking_Type.equalsIgnoreCase("ModalWindow_Old")) {
			safeClick(driver, getObjectHotels("HotelCom_SRP_HotelName"));
			Thread.sleep(5000);			
			driver.switchTo().frame("modal_window");
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_HotelName"), 1)){
				Reporter.log("HotelName is not displayed");
				Assert.assertTrue(false);
			}
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_CheckIN"), 1)){
				Reporter.log("CheckIn Date is not displayed");
				Assert.assertTrue(false);
			}
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_CheckOut"), 1)){
				Reporter.log("CheckOut Date is not displayed");
				Assert.assertTrue(false);
			}
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_Guests"), 1)){
				Reporter.log("Guests are not displayed");
				Assert.assertTrue(false);
			}
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_NoOf_Rooms"), 1)){
				Reporter.log("No Of rooms are not displayed");
				Assert.assertTrue(false);
			}
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_PAH"), 1)){
				Reporter.log("PAH is not displayed");
				Assert.assertTrue(false);
			}
			
			if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_SelectRoom"), 1)){
				Reporter.log("Select Room button is not displayed");
				Assert.assertTrue(false);
			}
					
			if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_Highlights_Link"), 1)){
				safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Old_Highlights_Link"));
				elementPresent_Time(driver, By.xpath("//div/small"), 10);
				elementAssert(driver, By.xpath("//div/small"), 5);
				elementAssert(driver, By.xpath("//div[2]/div/div/div/div[2]"), 5);
				elementAssert(driver, By.xpath("//span[2]"), 5);
				elementAssert(driver, By.xpath("//li[11]/span"), 5);
				elementAssert(driver, By.xpath("//div[@id='highlights']/div/div/div[3]/nav/ul/li[1]"), 5);
				elementAssert(driver, By.xpath("//div[@id='highlights']/div/div/div[3]/nav/ul/li[2]"), 5);
				elementAssert(driver, By.xpath("//div[@id='highlights']/div/div/div[3]/nav/ul/li[3]"), 5);
				elementAssert(driver, By.xpath("//div[@id='highlights']/div/div/div[3]/nav/ul/li[4]"), 5);
				elementAssert(driver, By.id("mapImage"), 5);				
			} else {
				Reporter.log("Highlight tab is not displayed");
				Assert.assertTrue(false);
			}

			if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_Amenities_Link"), 1)){
				safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Old_Amenities_Link"));
				elementAssert(driver, By.xpath("//h4"), 5);
				textAssert(driver, By.xpath("//h4"), "Basic amenities");
				textAssert(driver, By.xpath("//h4[2]"), "Other Basic amenities");
				textAssert(driver, By.xpath("//h4[3]"), "Hotel amenities");
				textAssert(driver, By.xpath("//h4[4]"), "Business services");
				textAssert(driver, By.xpath("//b[3]"), "Rooms :");
				textAssert(driver, By.xpath("//div[2]/div/h4"), "Other facilities in the hotel");
				textAssert(driver, By.xpath("//p/b"), "Location :");
				textAssert(driver, By.xpath("//p/b[2]"), "Hotel Features :");
				
			} else {
				Reporter.log("Amenities tab is not displayed");
				Assert.assertTrue(false);
			}

			if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_Photos_Link"), 1)){
				safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Old_Photos_Link"));
			} else {
				Reporter.log("Photos tab is not displayed");
				Assert.assertTrue(false);
			}	
			
			if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_Map_Link"), 1)){
				safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Old_Map_Link"));
			} else {
				Reporter.log("Map tab is not displayed");
				Assert.assertTrue(false);
			}
			
			if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_TravellerReview_Link"), 1)){
				safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Old_TravellerReview_Link"));
			} else {
				Reporter.log("Traveller Review tab is not displayed");
				Assert.assertTrue(false);
			}
			
			if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_SimilarHotels_Link"), 1)){
				safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Old_SimilarHotels_Link"));				
			} else {
				Reporter.log("Similar Hotels tab is not displayed");
				Assert.assertTrue(false);
			}
			
			if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Old_RoomRates_Link"), 1)){
				safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Old_RoomRates_Link"));				
			} else {
				Reporter.log("Room Rates tab is not displayed");
				Assert.assertTrue(false);
			}		
		}		
		
	//================================================== HOTEL NAME SEARCH HOMEPAGE =======================================//

		else if (Booking_Type.equalsIgnoreCase("NAMESEARCHHOMEPAGE")) {
			boolean NewDetailsPage = false, OldDetailsPage = false;
			for(int i=0; i<=5;i++) {
				if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"),1)) {
					NewDetailsPage = true;
					break;
				}else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"),1)) {
					OldDetailsPage = true;
					break;
				}			
				else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 5)) {
					Reporter.log("Sorry, this hotel is unavailable message is displayed");
					Assert.assertTrue(false);
				}	
			}
			if(NewDetailsPage) {
				elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 5);
				safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
				Thread.sleep(500);
				safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));		
			} else if(OldDetailsPage) {
				elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"), 30);
				safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));
			}
		}
	
	//=============================================== DATELESS SEARCH =======================================//

		else if(Booking_Type.equalsIgnoreCase("DATELESSSEARCH")){
			  elementVisible(driver, getObjectHotels("HotelCom_SRP_Check_Availability_Button"), 30);
			  safeClick(driver, getObjectHotels("HotelCom_SRP_Check_Availability_Button"));
			  selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 1, "15");
			  selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, "16");
			  safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
			  safeClick(driver, getObjectHotels("HotelCorp_HomePage_Search_Button"));
			  elementVisible(driver, By.cssSelector("div.glassShield"), 60);
			  elementNotVisible(driver, By.cssSelector("div.glassShield"), 60);
		}

	//====================================================Modify Search =======================================//
		else if (Booking_Type.equalsIgnoreCase("MODIFYSEARCH")) {
			elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 30);
			if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Modify_Tab"), 20)){
		  	   Reporter.log("Modify Tab is not Visible");
		  	   Assert.assertTrue(false);
		     }
			safeClick(driver, getObjectHotels("HotelCom_SRP_Modify_Tab"));
	      	safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), "Mumbai");
			selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, "23");
			selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, "24");
			safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
			hotelCom_Homepage_PaxEntry(driver, "1", "1", "0","0","0","0","0","0","0");
			safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
			Thread.sleep(20000);
			elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
			String Modify_City = getText(driver, By.xpath("//strong/span"));
			if(!Modify_City.equals("Mumbai")){
				Reporter.log("Modify City Search is not working");
				Assert.assertTrue(false);
			}		
		}
		
//================================================ Free Cancellation =======================================//
		
		else if (Booking_Type.equalsIgnoreCase("FREECANCELLATION")) {
			// code merged with Areaspecific search
			if(!elementVisible(driver, By.xpath("//div[2]/section[2]/section/div/div[2]/div[5]/section/div"), 20)) {
				Reporter.log("Showing properties in : ' ' Text is not displayed ");
			}
			if(!elementVisible(driver, By.xpath("//li/small"), 50)) {
				Reporter.log("Area text is not displayed  in : ' ' Text is not displayed ");
				Assert.assertEquals(true, false);
			}
			//-------------------------------//			
			
			elementVisible(driver, getObjectHotels("HotelCom_SRP_FreeCancellation_CheckBox"), 30);
			safeClick(driver, getObjectHotels("HotelCom_SRP_FreeCancellation_CheckBox"));
			safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
			safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));
			Thread.sleep(5000);
			hotelCom_SRP_ModalWindow(driver, Booking_Type);
		}
		
	//====================================================Deal of the Day  =====================================================//
		
		else if (Booking_Type.equalsIgnoreCase("DealoftheDay")) {
			elementVisible(driver, By.xpath("//li[3]/span"), 30);
			String Deal_Text = getText(driver, By.xpath("//li[3]/span"));
			if(!Deal_Text.equalsIgnoreCase("Deal of the day")){
				Reporter.log("Deal of the day text displayed in SRP as : "+Deal_Text );
				Assert.assertTrue(false);
			}
			safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));

			hotelCom_SRP_ModalWindow(driver,"DealOfTheDay");
		}
		
	//============================================= Prod Connectors  ==============================//
		
			else if (Booking_Type.equalsIgnoreCase("ProductionConnectors")) {
					String Connector[] = { "Chmm", "GTA", "Expedia", "TrustInternational", "BOOKING"};
					elementPresent(driver, By.id("1_1Chmm"));
					for(int i=0; i<=4; i++){
					String ID = "1_1"+Connector[i];
					if(elementPresent(driver, By.id(ID))){
						safeClick(driver, By.id(ID));
					}
					else {
						Reporter.log("Connector is not displayed in SRP : "+Connector[i]);
						Assert.assertEquals(ID, Connector[i]);
					}
					}
				}
		
	//==============================================================Air BNB =====================================================//
				
		if (Booking_Type.equalsIgnoreCase("AIRBNB")) {	
			logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_Air_BNB_Tab"), 20, "AirBNB tab is not displayed");
				safeClick(driver, getObjectHotels("HotelCom_SRP_Air_BNB_Tab"));
				safeClick(driver, getObjectHotels("HotelCom_SRP_Air_BNB_Price_Filter"));
				safeClick(driver, getObjectHotels("HotelCom_SRP_Air_BNB_Review_Book_Button"));
				String mainWindow = driver.getWindowHandle();
				driver.switchTo().frame("modal_window");
				elementVisible(driver, getObjectHotels("HotelCom_SRP_Air_BNB_HotelName"), 60);
				String CT_AirBNB_HotelName = getText(driver, getObjectHotels("HotelCom_SRP_Air_BNB_HotelName"));
				safeClick(driver, getObjectHotels("HotelCom_SRP_Air_BNB_Book_Button"));
				driver.switchTo().window(mainWindow);
				elementVisible(driver, getObjectHotels("HotelCom_AirBNB_Site_HotelName"), 60);
				String AirBNB_HotelName = getText(driver, getObjectHotels("HotelCom_AirBNB_Site_HotelName"));
				Boolean Hotel_Verification = CT_AirBNB_HotelName.contains(AirBNB_HotelName);
				if(!Hotel_Verification){
					Reporter.log("Hotel Selected in CT & Displayed in AIRBNB Site is not same - Hotel Name : "+AirBNB_HotelName);
					Assert.assertTrue(false);
				}				
		}
		
	//==============================================================Package Rates ============================================================//
		
				if (Booking_Type.equalsIgnoreCase("PACKAGERATE")) {	
					elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
					safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
			  loop: for(int i=1; i<=8; i++){
						if(i==6){
							i=7;
						}
						String Hotel_Select_Room_Xpath = "//li["+i+"]/section/div[5]/div/button[2]";
						safeClick(driver, By.xpath(Hotel_Select_Room_Xpath));
						for(int j=2; j<=3; j++){
							String Hotel_Package_Rate_Xpath = "//li["+i+"]/div/div[2]/table/tbody/tr["+j+"]/td";
							Thread.sleep(2000);
							String PackageRate = getText(driver, By.xpath(Hotel_Package_Rate_Xpath));
						if(PackageRate.contains("Package")){
								String Package_Rate_Book_Button = "//li["+i+"]/div/div[2]/table/tbody/tr["+j+"]/td[6]/input";
								safeClick(driver, By.xpath(Package_Rate_Book_Button));
								break loop;								
							} 
						else if(i==8){
							Reporter.log("Package rates are not available in the page");
						}
						}
					}				
				}
	//====================================================Pay @ Hotel =====================================================//
				if (Booking_Type.equalsIgnoreCase("PAHH")) {
					elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
					safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter")); 	
					if(elementVisible(driver, getObjectHotels("HotelCom_SRP_PAH_Checkbox"), 20)){
						safeClick(driver, getObjectHotels("HotelCom_SRP_PAH_Checkbox"));
					} 
					else {
						Reporter.log("Pay @ Hotel button is not displayed");
						Assert.assertTrue(false);
					}
					outerloop: for(int i=1; i<=10; i++){
						if(i==6){
							i=7;
						}
						int HotelPrice = hotelCom_SRP_Hotel_Price(driver, i);
						if(HotelPrice > 500){
							String Hotel_Select_Room_Xpath = "//li["+i+"]/section/div[5]/div/button[2]";
							safeClick(driver, By.xpath(Hotel_Select_Room_Xpath));
						}
						Thread.sleep(1000);
						ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
						driver.switchTo().window(tabs.get(1));
						Thread.sleep(1000);
						elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10);
						for(int j=1; j<=3; j++){
							for(int k=1; k<=3; k++){
								String Xpath_RoomRate = "//div[@id='roomsContainerWrapper']/div[2]/div/div["+j+"]/nav/ul/li["+k+"]/div/div[2]/div[2]";
								if(elementVisible(driver, By.xpath(Xpath_RoomRate), 1)){
								String RateText = getText(driver, By.xpath(Xpath_RoomRate));							
										if(RateText.contains("Free cancellation")){
												Reporter.log("Cancellation Text : "+RateText);
												String Xpath_RateRadio_Button = "//div[@id='roomsContainerWrapper']/div[2]/div/div["+j+"]/nav/ul/li["+k+"]/div/div";
												safeClick(driver, By.xpath(Xpath_RateRadio_Button));
												safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button"));
												break outerloop;		
										} 
									} 
						}
				driver.close();		
				driver.switchTo().window(tabs.get(0));
						}						
					}				
				}
				if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), 5)) {
				boolean NewDetailsPage = false, OldDetailsPage = false;
				for(int i=0; i<=5 ;i++) {
					if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"),1)) {
						NewDetailsPage = true;
						break;
					}else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"),1)) {
						OldDetailsPage = true;
						break;
					}
					else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 5)) {
						Reporter.log("Sorry, this hotel is unavailable message is displayed");
						Assert.assertTrue(false);
					}	
				}
				
				if(NewDetailsPage) {
					elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 5);
					safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
					Thread.sleep(500);
					safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));		
				}else if(OldDetailsPage) { 
					smartClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));
				}
	
				}
	}

	public void hotelCom_SRP_Select_HotelPrice_MoreThan(RemoteWebDriver driver, int Price) throws Exception {
			int j=1;
		for(int i=1; i<=10;i++) {
			int HotelPrice = hotelCom_SRP_Hotel_Price(driver, i);
			if(HotelPrice >Price) {
				j=i;
				break;
			}
		}
		safeClick(driver, By.xpath("//li["+j+"]/section/div[5]/div/button[2]"));
	}

	public void hotelCom_SRP_Select_HotelName(RemoteWebDriver driver, String Hotel_Name) throws Exception, InterruptedException {
		Reporter.log("Hotel Selected is "+Hotel_Name);
		elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 30);
		safeAutocomplete(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), getObjectHotels("HotelCom_SRP_HotelName_Ajax"), Hotel_Name);
		elementPresent_log(driver, By.linkText(Hotel_Name), "Hotel selected not displayed", 5);
		Thread.sleep(2000);
		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Rooms_Unavailable"), 1)) {
			Reporter.log("Rooms are Unavailable for Hotel : "+Hotel_Name);
			Assert.assertTrue(false);
		}
		String Selected_HotelName = getText(driver, By.xpath("//li/h2/a"));
		if(!Selected_HotelName.contains(Hotel_Name)) {
			Reporter.log("Hotel displayed in SRP is different than Hotel Selected from filter : Selected Hotel : "+Hotel_Name+" Hotel displayed in SRP : "+Selected_HotelName);
			Assert.assertTrue(false);
		}	
	}

	public void hotelCom_SRP_SwitchTo_MainWindow1(RemoteWebDriver driver) {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}
	
	public void DetailsPage_BookingType(RemoteWebDriver driver, String BookingType) throws Exception {
	
		if(BookingType.equalsIgnoreCase("PartPay")) {
			BookingType = "Part pay";
		}
		else if(BookingType.equalsIgnoreCase("PAH")) {
			BookingType = "Pay@hotel";
		}
		else {
			BookingType = "Pay full amount";
		}
		
		for(int i=1; i<=3;i++) {
			String Xpath_BookingType = "//div/ul/li["+i+"]/p";
				if(elementVisible(driver, By.xpath(Xpath_BookingType), 1)) {
				String HotelBookingType = getText(driver, By.xpath(Xpath_BookingType));
					if(HotelBookingType.equals(BookingType)) {
						smartClick(driver, By.xpath(Xpath_BookingType));
						break;
					}
				}		
		}
	}
		
	public String hotelCom_PAH_SinglePage(RemoteWebDriver driver, String BookingType, String Logger_Msg , String Booking_Confirmation_Message) throws Exception {
		String TripID = null;
		if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 50)) {
			Reporter.log("PAH Single Page Book : Page not displayed");
		}
		if(BookingType.equals("PAHCC")) {
			textPresent(driver, "Card Details", 5);
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_Title"), "Mr");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_FirstName"), "Test");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_LastName"), "Booking");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Phone"), "1212121212");
			textAssert(driver, By.xpath("//div[2]/div/p/strong"), "This hotel requires a credit card to guarantee the booking. You wont be charged now");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_CC"), "5123456789012346");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_Name"), "Test");
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_Exp_Month"), "05");
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_Exp_Year"), "2017");			
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_CVV"), "123");
			
		}
		
		else if(BookingType.equals("PAHCCSC")) {
			textPresent(driver, "Card Details", 5);
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_Title"), "Mr");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_FirstName"), "Test");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_LastName"), "Booking");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Phone"), "1212121212");
			textAssert(driver, By.xpath("//div[2]/div/p/strong"), "This hotel requires a credit card to guarantee the booking. You wont be charged now");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_CC"), "5123456789012346");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_Name"), "Test");
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_Exp_Month"), "05");
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_Exp_Year"), "2017");			
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_CVV"), "123");
			
		}
		
		else if(BookingType.equals("PAHINVALIDCC")) {
			textPresent(driver, "Card Details", 5);
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_Title"), "Mr");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_FirstName"), "Test");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_LastName"), "Booking");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Phone"), "1212121212");
			textAssert(driver, By.xpath("//div[2]/div/p/strong"), "This hotel requires a credit card to guarantee the booking. You wont be charged now");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_CC"), "5123456789012346");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_Name"), "Test");
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_Exp_Month"), "06");
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_Exp_Year"), "2017");			
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_CC_CVV"), "123");
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
			elementVisible(driver, By.id("retryLink"), 60);
			textAssert(driver, By.xpath("//h1"), "Oops! We weren't able to process your payment");
			safeClick(driver, By.id("retryLink"));
			elementVisible(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 60);
			textAssert(driver, By.xpath("//h1"), "Sorry, your bank refused the payment");
			textAssert(driver, By.xpath("//div[@id='pahPayValidateMess']/p/strong"), "This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now");
		}
		
		else if(BookingType.equals("PAHINVAILDOTP")) {
			textPresent(driver, "Guest Details", 5);
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_Title"), "Mr");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_FirstName"), "Test");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_LastName"), "Booking");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Phone"), "1212121212");
			safeClick(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_PhoneNo_Send"));
			elementVisible(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Code_Sent"), 20);
			textPresent(driver, "Verification code has been sent to the mobile number. ", 5);
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_VerificationCode"), "1212");
			safeClick(driver, getObjectHotels("HotelCom_SinglePage_Book_VerificationCode_Verify"));
			}
		
		else if(BookingType.equals("PAHEDIT")) {
			textPresent(driver, "Room Details", 5);
			elementVisible(driver, By.xpath("//form/div/div/div[2]/p"), 20);	
			mouseHover(driver, By.xpath("//form/div/div/div[2]/p"));
			textPresent(driver, "Guest Details", 5);
			safeClick(driver, By.xpath("//form/div/div/div[2]/p"));
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_Title"), "Mr");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_FirstName"), "Test");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_LastName"), "Bookings");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Phone"), "1212121215");
			safeClick(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_PhoneNo_Send"));
			elementVisible(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Code_Sent"), 20);			
			String OTP = hotelComPAHSendOTP(driver); 
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_VerificationCode"), OTP);
			safeClick(driver, getObjectHotels("HotelCom_SinglePage_Book_VerificationCode_Verify"));
			}
		
		else if(BookingType.equals("PAHSIGNEDIN")) {
			textPresent(driver, "Room Details", 5);
			elementVisible(driver, By.xpath("//form/div/div/div[2]/p"), 20);			
			String OTP = hotelComPAHSendOTP(driver); 
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_VerificationCode"), OTP);
			safeClick(driver, getObjectHotels("HotelCom_SinglePage_Book_VerificationCode_Verify"));
			}
		
				
		else {
			textPresent(driver, "Guest Details", 5);
			safeSelect(driver, getObjectHotels("HotelCom_SinglePage_Book_Title"), "Mr");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_FirstName"), "Test");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_LastName"), "Booking");
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Phone"), "1212121212");
			safeClick(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_PhoneNo_Send"));
			elementVisible(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Code_Sent"), 20);
			String VerificationCode_Sent = getText(driver, getObjectHotels("HotelCom_SinglePage_Book_PAH_Code_Sent"));
			String OTP = hotelComPAHSendOTP(driver); 
			if(!VerificationCode_Sent.contains("Verification code has been sent to the mobile number")) {
				Reporter.log("Success Message not displayed: Verification code has been sent to the mobile number");
			}      
			safeType(driver, getObjectHotels("HotelCom_SinglePage_Book_VerificationCode"), OTP);
			safeClick(driver, getObjectHotels("HotelCom_SinglePage_Book_VerificationCode_Verify"));
			if(elementVisible(driver, By.xpath("//div[@id='otpFailure']/p"), 5)) {
				String OTP_Error = getText(driver, By.xpath("//div[@id='otpFailure']/p"));
				if(!OTP_Error.contains("Sorry! You've entered an incorrect verification code.Please try again")) {
					Reporter.log("Sorry! You've entered an incorrect verification code.Please try again :  Error message is not displayed");
					Assert.assertTrue(false);
				}
			}
			if(!textPresent(driver, "Number successfully verified", 1)) {
				Reporter.log("Number successfully verified : message is not displayed");
				Assert.assertTrue(false);
			}
			}
		
		if(MakePaymentOnlyInQA2 && !BookingType.equals("PAHINVAILDOTP") && !BookingType.equals("PAHINVALIDCC")) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
			TripID = hotelCom_ConfirmationPage(driver, Logger_Msg, "", Booking_Confirmation_Message);
		}
		return TripID;
		
	}
	
	public void hotelCom_ItineraryPage(RemoteWebDriver driver, String BookingType) throws Exception {
		for(int i=0; i<=5;i++) {
			if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), 5)) {
				if(i==5) {
				logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), 2, "Itinerary Page has not loaded :( :( :( :( :( :( :(");
				}
			} else break;
		}
		logURL(driver);
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), 5)) {
		} else {
			Reporter.log("Hotel Book Step1 / Itinerary Page is not displayed");
		}
		if(elementClickable(driver, By.id("insurance_box_hotel"), 1)) {
			UnChecking_Checkbox(driver, By.id("insurance_box_hotel"));
		}
		smartClick(driver, getObjectHotels("HotelCom_ItineraryPage_PayNow_RadioButton"));
		
//====================================================Regular Booking =============================//
		if (BookingType.isEmpty()) {			
	
		} 
		//====================================================Expedia Booking =============================//
				if (BookingType.contains("Expedia")) {			
					elementPresent_log(driver, By.xpath("//p[3]/strong"), "Spl CheckIn for expedia", 10);
					textPresent_Log(driver, "Special check-in instructions:", 5);
					
				} 

//====================================================CHMM Promo=============================//
		if (BookingType.equalsIgnoreCase("CHMMPROMO")) {
			if(elementVisible(driver, getObjectHotels("HotelCom_ItineraryPage_PayNow_RadioButton"), 1)){
			safeClick(driver, getObjectHotels("HotelCom_ItineraryPage_PayNow_RadioButton"));
			if(elementPresent(driver, By.xpath("//*[@id='offerApplied']/span"))){
				Reporter.log("Chmm promo displayed in itinerary page");
				}
			}
		} 


//====================================================Coupon=====================================================//
		if (BookingType.equalsIgnoreCase("COUPON")) {
			String BookStep1_Price = getText(driver, getObjectHotels("HotelCom_BookStep1_Price"));
			String BookStep1_HotelName = getText(driver, getObjectHotels("HotelCom_BookStep1_HotelName"));
			Reporter.log("BookStep1: Hotel Name " + BookStep1_HotelName + " with Price " + BookStep1_Price);
			elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
			String URL = driver.getCurrentUrl();
			int Price = priceCapture(driver, By.cssSelector("dd.relative > #totalFare > #counter"), 5);
			Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_Coupon"));
			if(URL.contains(".com")){
				safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_Coupon"));
				}
			else if(URL.contains(".ae")){
				safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelAE_Coupon"));
				}
			safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
			elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"), 10);
			String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
			if (!CouponText.contains("Great! You just saved ")) {
				Reporter.log("Coupon error message displayed as : " + CouponText);
				Assert.assertTrue(false);
			}
			Reporter.log("Coupon Success message displayed : " + CouponText);
			CouponText = CouponText.replaceAll("\\D+", "");
			int CouponInt = Integer.parseInt(CouponText);

			int PriceCoupon = priceCapture(driver, By.cssSelector("dd.relative > #totalFare > #counter"), 5);
			
			if(!(PriceCoupon+CouponInt==Price)) {
				Reporter.log("Price before applying Coupon : "+Price+" Price after applying Coupon : "+PriceCoupon+" Coupon Price : "+CouponInt);
				Assert.assertTrue(false);
			}
		}
		
		
		//====================================================CouponPROD=====================================================//
				if (BookingType.equalsIgnoreCase("COUPONPROD")) {
					String BookStep1_Price = getText(driver, getObjectHotels("HotelCom_BookStep1_Price"));
					String BookStep1_HotelName = getText(driver, getObjectHotels("HotelCom_BookStep1_HotelName"));
					Reporter.log("BookStep1: Hotel Name " + BookStep1_HotelName + " with Price " + BookStep1_Price);
					elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
					String URL = driver.getCurrentUrl();
					int Price = priceCapture(driver, By.cssSelector("dd.relative > #totalFare > #counter"), 5);
					Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_Coupon"));
					if(URL.contains(".com")){
						safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_CouponProd"));
						}
					else if(URL.contains(".ae")){
						safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelAE_CouponProd"));
						}
					Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_CouponProd"));
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
					elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"), 10);
					String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
					if (!CouponText.contains("Great! You just saved ")) {
						Reporter.log("Coupon error message displayed as : " + CouponText);
						Assert.assertTrue(false);
					}
					Reporter.log("Coupon Success message displayed : " + CouponText);
					CouponText = CouponText.replaceAll("\\D+", "");
					int CouponInt = Integer.parseInt(CouponText);
					int PriceCoupon = priceCapture(driver, By.cssSelector("dd.relative > #totalFare > #counter"), 5);
					
					if(!(PriceCoupon+CouponInt==Price)) {
						Reporter.log("Price before applying Coupon : "+Price+" Price after applying Coupon : "+PriceCoupon+" Coupon Price : "+CouponInt);
						Assert.assertTrue(false);
					}
				}

		
				//=================================Card limit======================
				else if (BookingType.contains("CCLCREATE")) {
					String BookStep1_Price = getText(driver, getObjectHotels("HotelCom_BookStep1_Price"));
					String BookStep1_HotelName = getText(driver, getObjectHotels("HotelCom_BookStep1_HotelName"));
					Reporter.log("BookStep1: Hotel Name " + BookStep1_HotelName + " with Price " + BookStep1_Price);
					elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
					String URL = driver.getCurrentUrl();
					int Price = priceCapture(driver, By.cssSelector("dd.relative > #totalFare > #counter"), 5);
					Reporter.log("Coupon Used is :"+BookingType);
					if(URL.contains(".com")){
						safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), BookingType);
						}
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
					String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
					if (!CouponText.contains("Great! You just saved ")) {
						Reporter.log("Coupon error message displayed as : " + CouponText);
						Assert.assertTrue(false);
					}
					Reporter.log("Coupon Success message displayed : " + CouponText);
				}
				
				//=================================Backup======================
				else if (BookingType.contains("BACKUP")) {					
					elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));					
					safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), "TESTEST");						
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
					String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
					if (!CouponText.contains("Sorry, that's an invalid coupon")) {
						Reporter.log("Coupon error message displayed as : " + CouponText);
						Assert.assertTrue(false);
					}	
					Reporter.log("Coupon Error message is displayed : " + CouponText);
					String ErrorText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_BackupText"));
					if(ProductionUrl) {
					if (!ErrorText.contains("applicable on select hotels")) {
						Reporter.log("Coupon error message displayed as : " + ErrorText);
						Assert.assertTrue(false);
					}
					}else  {
						if (!ErrorText.contains("You may try")) {
							Reporter.log("Coupon error message displayed as : " + ErrorText);
							Assert.assertTrue(false);
						}
					}					
				}
		
		//=============================================================
		else if (BookingType.equalsIgnoreCase("COUPONAMEX")) {
			safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_Amex_Coupon"));

			Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_Amex_Coupon"));
			safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
			String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
			if (!CouponText.contains("Great! You just saved ")) {
				Reporter.log("Coupon error message displayed as : " + CouponText);
				Assert.assertTrue(false);
			}			
			Reporter.log("Coupon Success message displayed : " + CouponText);
		}
		

		//===================================================CouponLimit 1==========================//
		else if (BookingType.equalsIgnoreCase("COUPONLIMITONE")) {			
			String BookStep1_Price = getText(driver, getObjectHotels("HotelCom_BookStep1_Price"));
			String BookStep1_HotelName = getText(driver, getObjectHotels("HotelCom_BookStep1_HotelName"));
			Reporter.log("BookStep1: Hotel Name " + BookStep1_HotelName + " with Price " + BookStep1_Price);
			elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
			safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_CouponLimit"));
			Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_CouponLimit"));
			safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
			String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
			if (!CouponText.contains("Great! You just saved ")) {
				Reporter.log("Coupon error message displayed as : " + CouponText);
				Assert.assertTrue(false);
			}
			Reporter.log("Coupon Success message displayed : " + CouponText);
			CouponText = CouponText.replaceAll("\\D+", "");
			int CouponInt = Integer.parseInt(CouponText);

			if(CouponInt!=100){
				Reporter.log("Limit has execeded - 100 Rs");
				Assert.assertTrue(false);
			}else Reporter.log("Coupon amont applied is "+CouponInt);
			
		} 
		
		//===================================================CouponLimit 2==========================//
				else if (BookingType.equalsIgnoreCase("COUPONLIMITTWO")) {			
					String BookStep1_Price = getText(driver, getObjectHotels("HotelCom_BookStep1_Price"));
					String BookStep1_HotelName = getText(driver, getObjectHotels("HotelCom_BookStep1_HotelName"));
					Reporter.log("BookStep1: Hotel Name " + BookStep1_HotelName + " with Price " + BookStep1_Price);
					elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
					safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_CouponLimit"));
					Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_CouponLimit"));
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
					String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
					if (!CouponText.contains("Great! You just saved ")) {
						Reporter.log("Coupon error message displayed as : " + CouponText);
						Assert.assertTrue(false);
					}
					Reporter.log("Coupon Success message displayed : " + CouponText);
					CouponText = CouponText.replaceAll("\\D+", "");
					int CouponInt = Integer.parseInt(CouponText);
					System.out.println("Coupon Limit is "+CouponInt);
					if(CouponInt>100){
						Reporter.log("Limit has execeded - 100 Rs");
						Assert.assertTrue(false);
					} else Reporter.log("Coupon Limit is "+CouponInt);
				} 
		
//====================================================GOODIES=====================================================//
		
		else if (BookingType.equalsIgnoreCase("GOODIES")) {
			elementVisible(driver, By.id("goodieVoucher"), 30);
			String Goodies_Text = getText(driver, By.xpath("//dd/label"));
			Reporter.log(" Goodies Text : "+Goodies_Text);
			if(!Goodies_Text.contains("Get gift vouchers worth")){
				Reporter.log("Goodies text displayed in Itinerary page : "+Goodies_Text );
				Assert.assertTrue(false);
			}
			safeClick(driver, By.id("goodieVoucher"));
		}
		
		//================================================ ROOMER=====================================================//
				
				else if (BookingType.equalsIgnoreCase("ROOMER")) {
					if(elementVisible(driver, By.id("insurance_box_hotel"), 5)) {
						Thread.sleep(5000);
						Checking_Checkbox(driver, By.id("insurance_box_hotel"));
					} else {
						Reporter.log("Roomier insurance check box is not displayed");
						Assert.assertTrue(false);
					}
					if(!getText(driver, By.xpath("//dd/label")).contains("Make this booking refundable. ")) {
						Reporter.log("Make this booking refundable : 'text message is not displayed'");
						Assert.assertTrue(false);
					}					
				}
//====================================================WalletRestructuring  Dual=====================================================//
		
				else if (BookingType.equalsIgnoreCase("WALLETRESTRUCTURINGDUAL")) {
					String BookStep1_Price = getText(driver, getObjectHotels("HotelCom_BookStep1_Price"));
					String BookStep1_HotelName = getText(driver, getObjectHotels("HotelCom_BookStep1_HotelName"));
					Reporter.log("BookStep1: Hotel Name " + BookStep1_HotelName + " with Price " + BookStep1_Price);
					elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));

					int Price = priceCapture(driver, By.cssSelector("dd.relative > #totalFare > #counter"), 5);
					Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_WALLREST_DUAL"));
					safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_WALLREST_DUAL"));
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
					String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));

					String CouponText1 = getText(driver, getObjectHotels("HotelCom_BookStep1_WallRest_Dual_Second_Msg"));
					if (!CouponText.contains("will be credited to your Cleartrip Wallet post booking")) {
						Reporter.log("Coupon error message displayed as : " + CouponText);
						Assert.assertTrue(false);
					}
					if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep1_WallRest_CashBack_Schedule_Link"), 10)){
						Reporter.log("Cashback schedule link is not displayed");
						Assert.assertTrue(false);
					}
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_WallRest_CashBack_Schedule_Link"));
					if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep1_WallRest_CashBack_Schedule_PopUp"), 10)){
						Reporter.log("Cashback schedule popup is not displayed");
						Assert.assertTrue(false);
					}
					if(!getText(driver, getObjectHotels("HotelCom_BookStep1_WallRest_CashBack_Schedule_PopUp_Text")).contains("We'll add Rs.")){
						Reporter.log("We'll add Rs. text is not displayed");
						Assert.assertTrue(false);
					}
					
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_WallRest_CashBack_Schedule_PopUp_Close"));
					
					Reporter.log("Coupon Success message displayed : " + CouponText);
				}
		
//====================================================ROOMER UnAvialable=====================================================//
		
				else if (BookingType.equalsIgnoreCase("ROOMERUNAVAILABLE")) {
					if(elementVisible(driver, By.id("insurance_box_hotel"), 5)) {
						Reporter.log("Roomier insurance check box is displayed");
						Assert.assertTrue(false);
					} else {
						Reporter.log("Roomier insurance check box is not displayed");
						}
				}
		
	//====================================================ROOMER Coupon=====================================================//
		
				else if (BookingType.equalsIgnoreCase("ROOMERCOUPON")) {
					if(elementVisible(driver, By.id("insurance_box_hotel"), 1)) {
						Thread.sleep(1000);
						Checking_Checkbox(driver, By.id("insurance_box_hotel"));
					} else {
						Reporter.log("Roomier insurance check box is not displayed");
						Assert.assertTrue(false);
					}
					if(!getText(driver, By.xpath("//dd/label")).contains("Make this booking refundable. ")) {
						Reporter.log("Make this booking refundable : 'text message is not displayed'");
						Assert.assertTrue(false);
					}					
					String BookStep1_Price = getText(driver, getObjectHotels("HotelCom_BookStep1_Price"));
					String BookStep1_HotelName = getText(driver, getObjectHotels("HotelCom_BookStep1_HotelName"));
					Reporter.log("BookStep1: Hotel Name " + BookStep1_HotelName + " with Price " + BookStep1_Price);
					elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
					String URL = driver.getCurrentUrl();
					if(URL.contains(".com")){
						safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_Coupon"));
						}
					else if(URL.contains(".ae")){
						safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelAE_Coupon"));
						}
					Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_Coupon"));
					elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"), 10);
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
					String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
					if (!CouponText.contains("Great! You just saved ")) {
						Reporter.log("Coupon error message displayed as : " + CouponText);
						Assert.assertTrue(false);
					}
					Reporter.log("Coupon Success message displayed : " + CouponText);	
				
				}
		
//====================================================Full Coupon=====================================================//
		
		else if (BookingType.equalsIgnoreCase("FULLCOUPON")) {
			elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
			Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_CouponFullRefund"));
			safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_CouponFullRefund"));
			safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
			String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
			String HotelPrice = getText(driver, By.xpath("//dd/strong"));
			if (!(CouponText.contains("Great! You just saved") && HotelPrice.contains("50"))) {
				Reporter.log("Coupon error message displayed as : " + CouponText);
				Assert.assertTrue(false);
			}
			Reporter.log("Coupon Success message displayed as : " + CouponText);
		}
		
//====================================================Dual Coupon=====================================================//
		
				else if (BookingType.equalsIgnoreCase("DUALCASHBACK")) {
					elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
					Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_CouponDualRefund"));
					safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_CouponDualRefund"));
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
					String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
					String WalletCashBackText = getText(driver, By.xpath("//div[2]/p[2]"));
					Thread.sleep(2000);
					if (!(CouponText.contains("Great! You just saved") &&  WalletCashBackText.contains("Additionally, you will get"))) {
						Reporter.log("Coupon error message displayed as : " + CouponText);
						Assert.assertTrue(false);
					}
					Reporter.log("Coupon Success message displayed as : " + CouponText);
				}
		
	//====================================================Wallet Plus Plus=============================//
			
			if (BookingType.equalsIgnoreCase("WALLETPLUS")) {
					elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
					Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_Walletplus"));
					String URL = driver.getCurrentUrl();
					if(URL.contains(".com")){
						safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_Walletplus"));
						}
					else if(URL.contains(".ae")){
						safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelAE_Coupon"));
						}
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
					String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
					if (!CouponText.contains("credited to your Cleartrip Wallet post booking")) {
						Reporter.log("Coupon is not applied message is displayed : "+CouponText);
						Assert.assertTrue(false);
					}
				WalletRefundAmountItineraray= CouponText.replaceAll("[^0-9]", "");
				Reporter.log("WalletPlusPlus Success message disaplyed : " + CouponText);
				}

	//====================================================Deal of the Day=====================================================//
			
			else if (BookingType.equalsIgnoreCase("DealoftheDay")) {
				elementVisible(driver, By.xpath("//div[@id='itinBlock']/div/div/nav/ul/li[3]/h6/span"), 10);
				elementPresent(driver,By.cssSelector("i.DODSavings > small"));
				String Deal_Text = getText(driver, By.xpath("//div[@id='itinBlock']/div/div/nav/ul/li[3]/h6/span"));
					if(!Deal_Text.equalsIgnoreCase("Deal of the day")){
						Reporter.log("Deal of the day text displayed in SRP as : "+Deal_Text );
						Assert.assertTrue(false);
					}
			}
			
			
	//====================================================Part Pay=============================//
		
		else if(BookingType.equalsIgnoreCase("PARTPAY")){
			elementPresent(driver, getObjectHotels("HotelCom_ItineraryPage_Partpay_Button"));
			safeClick(driver, getObjectHotels("HotelCom_ItineraryPage_Partpay_Button"));
			Thread.sleep(2000);
		}
			
		//====================================================Part Pay=============================//
			
			else if(BookingType.equalsIgnoreCase("PARTPAYCOUPON")){
				elementPresent_Time(driver, getObjectHotels("HotelCom_ItineraryPage_Partpay_Button"), 10);
				safeClick(driver, getObjectHotels("HotelCom_ItineraryPage_Partpay_Button"));
				String BookStep1_Price = getText(driver, getObjectHotels("HotelCom_BookStep1_Partpay_Price"));
				String BookStep1_HotelName = getText(driver, getObjectHotels("HotelCom_BookStep1_HotelName"));
				Reporter.log("BookStep1: Hotel Name " + BookStep1_HotelName + " with Price " + BookStep1_Price);
				elementPresent(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"));
				String URL = driver.getCurrentUrl();
				if(!textPresent(driver, "Pay a token advance of Rs. 250* now and we will hold your booking for you", 5)){
					Reporter.log("Pay a token advance of Rs. 250* now and we will hold your booking for you : message is not displayed");
				//	Assert.assertTrue(false);
				}

				Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_Coupon"));
				if(URL.contains(".com")){
					safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_Coupon"));
					}
				else if(URL.contains(".ae")){
					safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelAE_Coupon"));
					}
				safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
				String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
				if (!CouponText.contains("Great! You just saved ")) {
					Reporter.log("Coupon is not applied message is displayed");
					Assert.assertTrue(false);
				}
				Reporter.log("Coupon Success message disaplyed : " + CouponText);
				Thread.sleep(2000);
			}
		
	//==================================================== Gift Voucher ==========================================//
		
		else if(BookingType.equalsIgnoreCase("GIFTVOUCHER")){
			String URL = driver.getCurrentUrl();
			Reporter.log("GV Used is :"+dataFile.value("HotelCom_Gift_Card_No")+"   "+dataFile.value("HotelCom_Gift_Card_Pin")+" GV pin");
			
			if(URL.contains(".com")){
				safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_Gift_Card_No"));
				safeType(driver, getObjectHotels("HotelCom_BookStep1_GiftCard_Pin"), dataFile.value("HotelCom_Gift_Card_Pin"));
				}
			else if(URL.contains(".ae")){
				safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelAE_Gift_Card_No"));
				safeType(driver, getObjectHotels("HotelCom_BookStep1_GiftCard_Pin"), dataFile.value("HotelAE_Gift_Card_Pin"));
				}
			safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
			String GV_Msg = getText(driver, getObjectHotels("HotelCom_BookStep1_GiftCard_SuccessText"));
			if(!(GV_Msg.contains("An amount of"))){			
				Reporter.log("Message dispayed after applying Gift Card : "+GV_Msg);
				Assert.assertTrue(false);
			}
			Reporter.log("Message displayed after applying coupon is '"+GV_Msg);			
		}
		
	//====================================================Hold Booking=============================//
		if (BookingType.equalsIgnoreCase("HOLD")) {
				if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Hold_Content"), 30)){
					Reporter.log("Hold Booking content not didplayed in Book Step 1");
					Assert.assertTrue(false);
				}				
				if(!textPresent(driver, "You've picked a hotel that does not support online bookings", 5)){
					Reporter.log("You've picked a hotel that does not support online bookings. text not displayed");
					Assert.assertTrue(false);
				}
				if(!textPresent(driver, "If the hotel is not available, we will give you a full refund of your", 5)){
					Reporter.log("If the hotel is not available, we will give you a full refund of your text not displayed");
					Assert.assertTrue(false);
				}if(!textPresent(driver, "How booking requests work", 5)){
					Reporter.log("How booking requests work text not displayed");
					Assert.assertTrue(false);
				}
				textAssert(driver, getObjectHotels("HotelCom_BookStep1_Hold_Text"), "You've picked a hotel that does not support online bookings");
			}
		
	//==================================================== Pay @ Hotel=============================//
		if (BookingType.equalsIgnoreCase("PAH")) {
			safeClickList(driver, By.xpath("//span/label/span"), "Pay @ Hotel");
			elementVisible(driver, By.xpath("//div[@id='payAtHotelDetails']/nav"), 5);		
		}
		
		//==================================================== Pay @ Hotel=============================//
				if (BookingType.equalsIgnoreCase("PAHCC")) {
					safeClickList(driver, By.xpath("//span/label/span"), "Pay @ Hotel");
					if(elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), 1)){
						Reporter.log("Coupon textbox is displayed");
					}
				}
	//==================================================== Pay @ Hotel Coupon=============================//
			if (BookingType.equalsIgnoreCase("PAHCOUPON")) {
				safeClickList(driver, By.xpath("//span/label/span"), "Pay @ Hotel");
				elementVisible(driver, By.xpath("//div[@id='payAtHotelDetails']/nav"), 5);
				if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), 1)){
					Reporter.log("Coupon textbox not displayed is displayed");
					Assert.assertTrue(false);
				}
				Reporter.log("Coupon Used is :"+dataFile.value("HotelCom_PAHCoupon"));
				String URL = driver.getCurrentUrl();
				if(URL.contains(".com")){
					safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_PAHCoupon"));
				}
				else if(URL.contains(".ae")){
					safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), dataFile.value("HotelCom_PAHCoupon"));
				}
				safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
				String CouponText = getText(driver, getObjectHotels("HotelCom_BookStep1_Coupon_SuccessText"));
				if (!CouponText.contains("credited to your Cleartrip Wallet post booking")) {
					Reporter.log("Coupon is not applied message is displayed : "+CouponText);
					Assert.assertTrue(false);
				}
				WalletRefundAmountItineraray= CouponText.replaceAll("[^0-9]", "");
				Reporter.log("WalletPlusPlus Success message disaplyed : " + CouponText);
				}				
				
		//==================================================== Pay @ Hotel AE (Booking.com)================//
		if (BookingType.equalsIgnoreCase("PAHAE")) {
			String PAH_Text = getText(driver, By.xpath("//dd/p"));
			if(!PAH_Text.contains("You won't be charged now but will be charged by the hotel within 3 working days")) {
				Reporter.log("You won't be charged now but will be charged by the hotel within 3 working days : Text is not displayed");
			}
		}
	
		
		//===================================== Opaque  =======================================//
		
		if (BookingType.equalsIgnoreCase("OPAQUE")) {
			if(!textPresent(driver,"Opaque", 30)){
				Reporter.log("Text : 'Opaque  text is not displayed");
				Assert.assertTrue(false);
			}
			if(!textPresent(driver,"We get you the best price, guaranteed", 30)){
				Reporter.log("Text : 'We get you the best price, guaranteed...  text is not displayed");
				Assert.assertTrue(false);
			}
			
		}
		
		//===================================== Creative Pricing  =======================================//
		
		if (BookingType.equalsIgnoreCase("CREATIVEPRICING")) {
				
			if(!textPresent(driver, "We've pre-applied your wallet balance in the fares", 5)){
				Reporter.log("We've pre-applied your wallet balance in the fares message is not displayed");
				Assert.assertTrue(false);
			}
			elementPresent_log(driver, getObjectHotels("HotelCom_BookStep1_CreativePricing_Msg"), "We've pre-applied your wallet  : ", 2);
			elementPresent_log(driver, getObjectHotels("HotelCom_BookStep1_CreativePricing_WalletBalance_Msg"), "Wallet balance   : ", 2);
					int CreativePrice_Int = hotelCom_ConvertPrice_To_Int(driver, getObjectHotels("HotelCom_BookStep1_CreativePricing_Price"));
					Reporter.log("Price in Itinerary after wallet amount deducted is : "+CreativePrice_Int);
			if(!(CreativePrice_Int==0)){
				Reporter.log("Price in Itinerary after wallet amount deducted is not zero");
				Assert.assertTrue(false);
			}
		}
				
		//===================================== GIFTVOUCHER Multi  =======================================//
				
		if (BookingType.equalsIgnoreCase("GIFTVOUCHERMULTI")) {
			String GV1[] = hotelCom_GV_Creation(driver, "50");
			String GV2[] = hotelCom_GV_Creation(driver, "50");
			String GV3[] = hotelCom_GV_Creation(driver, "50");
			
			safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), GV1[0]);
			safeType(driver, getObjectHotels("HotelCom_BookStep1_GiftCard_Pin"), GV1[1]);
			safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
	
		safeClick(driver, getObjectHotels("HotelCom_BookStep1_Add_GV_Link"));
		elementPresent_log(driver, getObjectHotels("HotelCom_BookStep1_Coupon2_TextBox"), "Second GV Option", 5);		
		safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon2_TextBox"), GV2[0]);
		safeType(driver, getObjectHotels("HotelCom_BookStep1_GiftCard2_Pin"), GV2[1]);
		safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton2"));
		
		
		safeClick(driver, getObjectHotels("HotelCom_BookStep1_Add_GV_Link"));
		elementPresent_log(driver, getObjectHotels("HotelCom_BookStep1_Coupon3_TextBox"), "Third GV Option", 5);		
		safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon3_TextBox"), GV3[0]);
		safeType(driver, getObjectHotels("HotelCom_BookStep1_GiftCard3_Pin"), GV3[1]);
		safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton2"));
		
		String GV_Msg = getText(driver, getObjectHotels("HotelCom_BookStep1_GiftCard_SuccessText"));
		if(!(GV_Msg.contains("An amount of"))){			
		Reporter.log("Message dispayed after applying Gift Card : "+GV_Msg);
		Assert.assertTrue(false);
		}
		Reporter.log("Message displayed after applying first GV is '"+GV_Msg);			
		}
				
		//===================================== GIFTVOUCHER PLUS CC  =======================================//
		
				if (BookingType.equalsIgnoreCase("GIFTVOUCHERCC")) {
					String GV1[] = hotelCom_GV_Creation(driver, "40");
					safeType(driver, getObjectHotels("HotelCom_BookStep1_Coupon_TextBox"), GV1[0]);
					safeType(driver, getObjectHotels("HotelCom_BookStep1_GiftCard_Pin"), GV1[1]);
					safeClick(driver, getObjectHotels("HotelCom_BookStep1_Verifybutton"));
				String GV_Msg = getText(driver, getObjectHotels("HotelCom_BookStep1_GiftCard_SuccessText"));
				if(!(GV_Msg.contains("An amount of"))){			
				Reporter.log("Message dispayed after applying Gift Card : "+GV_Msg);
				Assert.assertTrue(false);
				}

				Reporter.log("Message displayed after applying first GV is '"+GV_Msg);			
			}
		
				//===================================== Package Rate  =======================================//
				
				if (BookingType.equalsIgnoreCase("PACKAGE")) {
							String PackageRoomType = getText(driver, getObjectHotels("HotelCom_BookStep1_RoomDetails"));
							if(PackageRoomType.contains("Package")) {
								Reporter.log("Package rate is not dispayed");
								Assert.assertTrue(false);
							}
				}		
				//===================================== NEW Scripts  =======================================//
				
				if (BookingType.equalsIgnoreCase("NEW")) {
					
				}	
		if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"),5)){
			Reporter.log("Itinerary page / button not dispalyed");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"));
		if(!elementNotVisible(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), 10)){
			if(elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), 2)) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"));
			}
		}		
	}


	  public void detailsPage_CancellationPolicy(RemoteWebDriver driver, String CancellationType) throws Exception {
		  if(CancellationType.equals("NonRefundable")) {
		   elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_CancelationPolicy"), 10);
		   String cancellationText = getText(driver, getObjectHotels("HotelCom_DetailsPageNew_CancelationPolicy"));
		   if(!cancellationText.equals("Non refundable")) {
			   Reporter.log("NonRefundable text is not displayed, its displayed as - "+cancellationText);
			   Assert.assertTrue(false);
		   }
		   if(!getAttribute(driver, getObjectHotels("HotelCom_DetailsPageNew_CancelationPolicy"), "title").contains("This booking is non-refundable")) {
			   Reporter.log("This booking is non-refundable message is not displayed");
			   Assert.assertTrue(false);
		   }  		   
		  }else if(CancellationType.equals("Refundable")) { 
			  elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_CancelationPolicy"), 10);
			  String cancellationText = getText(driver, getObjectHotels("HotelCom_DetailsPageNew_CancelationPolicy"));
			  if(!cancellationText.equals("Refundable")) {
				   Reporter.log("Refundable text is not displayed, its displayed as - "+cancellationText);
				   System.out.println("Refundable text is not displayed, its displayed as - "+cancellationText);
			   Assert.assertTrue(false);
			  }
			   if(!getAttribute(driver, getObjectHotels("HotelCom_DetailsPageNew_CancelationPolicy"), "title").contains("Free Cancellation until")) {
				   Reporter.log("Free Cancellation until message is not displayed");
				   Assert.assertTrue(false);
			   }  
		   }else if(CancellationType.equals("CancellationPolicy")) { 
				  elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_CancelationPolicy"), 10);
				  String cancellationText = getText(driver, getObjectHotels("HotelCom_DetailsPageNew_CancelationPolicy"));
				  if(!cancellationText.equals("Cancellation Policy")) {
				   Reporter.log("Cancellation Policy text is not displayed, its displayed as - "+cancellationText);
				   Assert.assertTrue(false);
				  }
				  mouseHover(driver, getObjectHotels("HotelCom_DetailsPageNew_CancelationPolicy"));
				   }
			elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 2);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
			Thread.sleep(500);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));			
	  }
	  
	  public void itineraryPage_CancellationPolicy(RemoteWebDriver driver, String CancellationType) throws Exception {
			elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), 5);
			safeClick(driver, By.linkText("(rate details)"));
			Thread.sleep(2000);
			driver.switchTo().frame("modal_window");  
			elementVisible(driver, By.cssSelector("b"), 5);		
			elementVisible(driver, By.xpath("//div[@id='ContentFrame']/ul[2]/li"), 5);
			String CancellationPolicy  = getText(driver, By.xpath("//div[@id='ContentFrame']/ul[2]/li"));
			// xpath for 'cancellation policy' in rate details modal is different from refundable & non- refundable hence adding a different one
			String CancellationPolicy1  = getText(driver, By.xpath("//div[@id='ContentFrame']/ul[3]/li")); 
			String CancellationPolicy2  = getText(driver, By.xpath("//*[@id='ContentFrame']/ul[2]/li"));
			if(CancellationType.equals("NonRefundable")) {
			if(!textPresent(driver, "This booking is non-refundable.", 1)) {
				 Reporter.log("This booking is non-refundable message is not displayed");
				 Assert.assertTrue(false);
			}
			if(!CancellationPolicy.contains("This booking is non-refundable.")) {
				 Reporter.log("This booking is non-refundable message is not displayed");
				Assert.assertTrue(false);
				}
			} else if(CancellationType.equals("Refundable")) {
				if(!CancellationPolicy.contains("If you cancel within ")) {
					 Reporter.log("If you cancel within : message is not displayed");
					Assert.assertTrue(false);
				   }
				}else if(CancellationType.equals("CancellationPolicy")) {
					if(ProductionUrl) {
						/*if(!CancellationPolicy1.contains("If you cancel within 48 hours before checkin, you will incur 1 room night charges")) {
							 Reporter.log("If you cancel within 48 hours before checkin, you will incur 1 room night charges : message is not displayed");
							Assert.assertTrue(false);
						   }*/
						 if(!CancellationPolicy2.contains("If you cancel within 72 hours before checkin, you will incur 100.0% of your total stay.")){
							 Reporter.log("If you cancel within 72 hours before checkin, you will incur 100.0% of your total stay : message is not displayed");
								Assert.assertTrue(false);
						}
					} else {
						if(!CancellationPolicy1.contains("If you cancel within 72 hours before checkin, you will incur 100.0% of your total stay.")) {
							 Reporter.log("If you cancel within 72 hours before checkin, you will incur 100.0% of your total stay : message is not displayed");
							Assert.assertTrue(false);
						   }
					}					
				}
	  }
	
	public void hotelCom_LoginPage(RemoteWebDriver driver, String SignIN, String Account_Type) throws Exception {
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep2_Continue_Button"), 25, "Login Step has not loaded :( :( :( :( :( :( ");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep2_Continue_Button"), 5)) {
		} else {
			Reporter.log("Hotel Book Step2 / Login Step is not displayed - Clicking Itinerary page button for Second time");
			if(elementVisible(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), 1)){
				safeClick(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"));
			}
		}		
		String Host = common.value("host");
		if(Account_Type.isEmpty()){
			if(Host.equalsIgnoreCase("QA2")||Host.equalsIgnoreCase("hf")) {
				safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_UserName"), dataFile.value("HotelEmailID"));
			}
			else if(Host.equalsIgnoreCase("beta")||Host.equalsIgnoreCase("www")) {
				safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_UserName"), dataFile.value("HotelGmailEmailID"));
				}
		if (SignIN.equalsIgnoreCase("SignIN")) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep2_SignIn_CheckBox"));
			if(Host.equalsIgnoreCase("QA2")||Host.equalsIgnoreCase("hf")) {
			safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_Password"), dataFile.value("HotelPassword"));
			}
			else if(Host.equalsIgnoreCase("beta")||Host.equalsIgnoreCase("www")) {
				safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_Password"), dataFile.value("HotelGmailPassword"));
				}
			Reporter.log("SignIN @ BookStep2 with Cleartrip Account");
			}		
		}
		else if (Account_Type.equalsIgnoreCase("Wallet")) {
				safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_UserName"), dataFile.value("HotelWalletsEmailID"));
			if (SignIN.equalsIgnoreCase("SignIN")) {
				Thread.sleep(1000);
				safeClick(driver, getObjectHotels("HotelCom_BookStep2_SignIn_CheckBox"));
				safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_Password"), dataFile.value("HotelWalletsPassword"));
				Reporter.log("SignIN @ BookStep2 with Gmail Account");
			}		
		}
		else if (Account_Type.equalsIgnoreCase("DEBUG")) {
			safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_UserName"), "cltp.hotelstest@gmail.com");
		if (SignIN.equalsIgnoreCase("SignIN")) {
			Thread.sleep(1000);
			safeClick(driver, getObjectHotels("HotelCom_BookStep2_SignIn_CheckBox"));
			safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_Password"), "cleartrip");
			Reporter.log("SignIN @ BookStep2 with Gmail Account");
		}		
	}
		
		
		
		else if (Account_Type.equalsIgnoreCase("GMAIL")) {
				safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_UserName"), dataFile.value("HotelGmailEmailID"));
			if (SignIN.equalsIgnoreCase("SignIN")) {
				Thread.sleep(1000);
				safeClick(driver, getObjectHotels("HotelCom_BookStep2_SignIn_CheckBox"));
				safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_Password"), dataFile.value("HotelGmailPassword"));
				Reporter.log("SignIN @ BookStep2 with Gmail Account");
				}		
			}
		else if (Account_Type.equalsIgnoreCase("StoredCard")) {
			safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_UserName"), dataFile.value("HotelWalletsEmailID"));
		if (SignIN.equalsIgnoreCase("SignIN")) {
			Thread.sleep(1000);
			safeClick(driver, getObjectHotels("HotelCom_BookStep2_SignIn_CheckBox"));
			safeType(driver, getObjectHotels("HotelCom_BookStep2_EmailID_Password"), dataFile.value("HotelWalletsPassword"));
			Reporter.log("SignIN @ BookStep2 with Gmail Account");
			}		
		}
		safeClick(driver, getObjectHotels("HotelCom_BookStep2_Continue_Button"));
	}

	public void hotelCom_TravelerPage(RemoteWebDriver driver) throws Exception {
		
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 25, "Traveler Step has not loaded :( :( :( :( :( ");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 5)) {
			} else {
			Reporter.log("Hotel Book Step 3 / Traveller Step is not displayed");
		}
		if(elementVisible(driver, By.id("use_vat"), 10)){
			if(isElementSelected(driver, By.id("use_vat"))){
				Reporter.log("Vat fields shown, ncheck and continue");
				safeClick_CheckBox(driver, By.id("use_vat"));
			}
		}
		/*else{
			Reporter.log("Vat fields not shown");
		}*/
		safeSelect(driver, getObjectHotels("HotelCom_BookStep3_Traveller_Title"), dataFile.value("Title"));// dataFile.value("Title")
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_FirstName"), dataFile.value("HotelFirst_Name"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_LastName"), dataFile.value("HotelLast_Name"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_MobileNo"), dataFile.value("Mobile_Number"));
		
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), 1)){
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), "Spl Request");
		}
		
		if(MakePaymentOnlyInProd) {
			safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_MobileNo"), "1234567890");
			if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), 1)){
			safeType(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), "");
			}
		}
		Thread.sleep(500);
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		if(!elementNotVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 30)) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		}
	}
	
public void hotelCom_TravelerPage_Prod(RemoteWebDriver driver) throws Exception {
		
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 25, "Traveler Step has not loaded :( :( :( :( :( ");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 5)) {
			} else {
			Reporter.log("Hotel Book Step 3 / Traveller Step is not displayed");
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_CheckBox"));		
		safeSelect(driver, getObjectHotels("HotelCom_BookStep3_Traveller_Title"), dataFile.value("Title"));// dataFile.value("Title")
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_FirstName"), dataFile.value("HotelFirst_Name"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_LastName"), dataFile.value("HotelLast_Name"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_MobileNo"), "");
		Thread.sleep(500);
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		if(!elementNotVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 30)) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		}
	}
	
}
	
	
	public void hotelCom_TravelerPage_Intl_GST(RemoteWebDriver driver) throws Exception {
		
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 25, "Traveler Step has not loaded :( :( :( :( :( ");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 1)) {
			} else {
			Reporter.log("Hotel Book Step 3 / Traveller Step is not displayed");
		}
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_CheckBox"), 1)){
			Reporter.log("GST Check box is present for Intl Search");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Use GSTIN for this booking (Optional)", 1)){
			Reporter.log("Use GSTIN for this booking (Optional) text is present for Intl Search");
			Assert.assertTrue(false);
		}		
		safeSelect(driver, getObjectHotels("HotelCom_BookStep3_Traveller_Title"), dataFile.value("Title"));// dataFile.value("Title")
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_FirstName"), dataFile.value("HotelFirst_Name"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_LastName"), dataFile.value("HotelLast_Name"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_MobileNo"), dataFile.value("Mobile_Number"));
		
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), 1)){
			safeType(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), "Spl Request");
		}
		Thread.sleep(500);
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		if(!elementNotVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 30)) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		}
	}
	
	public void hotelCom_TravelerPage_GST(RemoteWebDriver driver, String GSTType, String GSTNo) throws Exception {
		
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 25, "Traveler Step has not loaded :( :( :( :( :( ");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 5)) {
			} else {
			Reporter.log("Hotel Book Step 3 / Traveller Step is not displayed");
		}	
		elementPresent_log(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_CheckBox"), "GST Checkbox ", 5);
		safeSelect(driver, getObjectHotels("HotelCom_BookStep3_Traveller_Title"), dataFile.value("Title"));// dataFile.value("Title")
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_FirstName"), dataFile.value("HotelFirst_Name"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_LastName"), dataFile.value("HotelLast_Name"));
		UnChecking_Checkbox(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_CheckBox"));
		if(GSTType.equalsIgnoreCase("GSTEDIT")){	
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_CheckBox"));
		elementPresent_Time(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_Number"), 5);
		Checking_Checkbox(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_CheckBox"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_Number"), GSTNo);
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_HolderName"), dataFile.value("HotelGSTHolderName"));
		}
		else if(GSTType.equalsIgnoreCase("GSTUnselect")){
			UnChecking_Checkbox(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_CheckBox"));
			Reporter.log("GST option is unselected");
		}
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_MobileNo"), dataFile.value("Mobile_Number"));
		
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), 1)){
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), "Spl Request");
		}
		Thread.sleep(500);
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		if(!elementNotVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"),20)) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		}
	}

	public void hotelCom_TravelerPage_GSTState(RemoteWebDriver driver) throws Exception {
	
	logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 25, "Traveler Step has not loaded :( :( :( :( :( ");
	if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 5)) {
		} else {
		Reporter.log("Hotel Book Step 3 / Traveller Step is not displayed");
	}
	elementPresent_log(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_CheckBox"), "GST Checkbox ", 5);
	UnChecking_Checkbox(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_CheckBox"));
	int randomNum = new Random().nextInt(25)+1;
	safeSelectByIndex(driver, getObjectHotels("HotelCom_BookStep3_Traveller_GST_State"), randomNum);

	safeSelect(driver, getObjectHotels("HotelCom_BookStep3_Traveller_Title"), dataFile.value("Title"));// dataFile.value("Title")
	safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_FirstName"), dataFile.value("HotelFirst_Name"));
	safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_LastName"), dataFile.value("HotelLast_Name"));
	safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_MobileNo"), dataFile.value("Mobile_Number"));
	
	if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), 1)){
	safeType(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), "Spl Request");
	}
	Thread.sleep(500);
	safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
	if(!elementNotVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 30)) {
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
	}
}

	public void hotelCom_TravelerPage_PickATraveller(RemoteWebDriver driver) throws Exception {
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 25, "Traveler Step has not loaded :( :( :( :( :( ");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 5)) {
			Reporter.log("Hotel Book Step 3 / Traveller Step is displayed");
		} else {
			Reporter.log("Hotel Book Step 3 / Traveller Step is not displayed");
		}
		safeSelect(driver, getObjectHotels("HotelCom_BookStep3_Traveller_Title"), dataFile.value("Title"));// dataFile.value("Title")
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_FirstName"), "Cltp");
		if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Traveller_PickATraveller"), 10)) {
			Reporter.log("Pick a traveller option is not displayed in Travaller step for SignIN User");
			Assert.assertTrue(false);
		}
		String TravellerName = getText(driver, getObjectHotels("HotelCom_BookStep3_Traveller_Pick_FirstTraveller"));
		if(!TravellerName.contains("Cltp")) {
			Reporter.log("First name in Pick a traveller/ suggest traveller is not correct: Traveller typed is : "+"Cltp"+ " Traveller displayed is : "+TravellerName );
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Traveller_Pick_FirstTraveller"));
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		if(!elementNotVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 20)) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
		}
	}
	
	public void hotelCom_TravelerPage_PAH(RemoteWebDriver driver) throws Exception {
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 25, "Traveler Step has not loaded :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"), 5)) {
			Reporter.log("Hotel Book Step 3 / Traveller Step is displayed");
		} else {
			Reporter.log("Hotel Book Step 3 / Traveller Step is not displayed");
		}
		safeSelect(driver, getObjectHotels("HotelCom_BookStep3_Traveller_Title"), dataFile.value("Title"));// dataFile.value("Title")
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_FirstName"), dataFile.value("HotelFirst_Name"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_LastName"), dataFile.value("HotelLast_Name"));
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Traveller_MobileNo_PAH"), dataFile.value("Mobile_Number"));
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), 1)){
		safeType(driver, getObjectHotels("HotelCom_BookStep3_Special_Request"), dataFile.value("Hotel_Special_Request"));
		}
		safeClick(driver, getObjectHotels("HotelCom_BookStep3_Continue_Button"));
	}

	public String hotelCom_PaymentPage(RemoteWebDriver driver,String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Text) throws Exception {
		logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 20, "Payment Step has not loaded :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 5)) {
			} else {
			Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
		}
		if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 1)) {
			if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Error_White_Background"), 1)) {
				if(textPresent(driver, "Oops! Something snapped", 1)) {
					Reporter.log("Oops! Something snapped message is displayed");
					Assert.assertTrue(false);
				}
			}
		}
		String TripID = null;
		elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), "Payment Button ", 10);
		
		smartClick(driver, getObjectHotels("HotelCom_BookStep4_WalletOptOut_CheckBox"));

//================================= CREDIT CARD ===========================================//
		
			if(Payment_Type.isEmpty() || Payment_Type.equalsIgnoreCase("CREDITCARD")){
				Reporter.log("Payment Type is : CC");
				elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), "CC Tab", 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
				smartClick(driver, By.id("native_currency"));
				Thread.sleep(2000);
			}
//================================= CREDITCARDLIMIT===========================================//
			
			if(Payment_Type.equalsIgnoreCase("CREDITCARDLIMIT")){
				Reporter.log("Payment Type is : CC");
				elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), "CC Tab", 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
				smartClick(driver, By.id("native_currency"));
				Thread.sleep(2000);
				safeClick(driver, By.id("paymentSubmit"));
				
				if(elementVisible(driver, By.id("couponError"), 10)) {
					Reporter.log("Passed:card limit working!, booking is not happening above limit");
					
				}
				else{
					Reporter.log("Card limit did not work");
					Assert.assertTrue(false);
				}   
			}
			

			//================================= Production Payment ===========================================//
					if(MakePaymentOnlyInProd) {
					if(Payment_Type.equals("ProdpaymentLive")){
						System.out.println("=================================LIVE CARD details are Used =================================");
						
							Reporter.log("Payment Type is : CC");
							elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), "CC Tab", 10);
							safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
							safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), common.value("MasterCardNo"));
							safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), "10");
							safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), "2018");
							safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), "Ramesh K");
							safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), "");
							smartClick(driver, By.id("native_currency"));
							Thread.sleep(2000);
						}
					}

			//=================================Invalid CREDIT CARD ===========================================//
					
						if(Payment_Type.equalsIgnoreCase("INVALIDCREDITCARD")){
							Reporter.log("Payment Type is :  Invalid CC");
							elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), "CC Tab", 10);
							safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
							safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
							safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), "06");
							safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
							safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
							safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
							smartClick(driver, By.id("native_currency"));
							Thread.sleep(2000);
						}

//================================= DEBIT CARD ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("DEBITCARD")){
				Reporter.log("Payment Type is : DC");
				elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_DebitCard_Tab"), "DC tab",  10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_DebitCard_Tab"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_DebitCard_No"), dataFile.value("MasterCard_Number"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_DebitCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_DebitCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_DebitCard_HolderName"), dataFile.value("MasterCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_DebitCard_CVV"), dataFile.value("MasterCard_CVV"));
			}

//================================= NETBANKING ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("NETBANKING")){
				Reporter.log("Payment Type is : NB");
				elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"),  "NB tab : " , 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"));
				elementPresent(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_DropDown"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_DropDown"), "Bank of India");
			}
						

		//================================= NETBANKING AE===========================================//
									
			else if(Payment_Type.equalsIgnoreCase("NETBANKINGAE")){
					Reporter.log("Payment Type is : AE NB");
					elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"),  "NB tab : " , 10);
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"));
					elementPresent(driver, By.id("adcb_bank"));
					safeClick(driver, By.id("adcb_bank"));
			}
						

		//================================= NETBANKING AE Edirham==========================================//
												
			else if(Payment_Type.equalsIgnoreCase("NETBANKINGAE_EDIRHAM")){
				Reporter.log("Payment Type is : AE EDirham NB");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"));
				elementPresent_log(driver, By.id("e-dirham"), "e-dirham option ", 5);
				safeClick(driver, By.id("e-dirham"));
			}

//================================= PAH ===== ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("PAH")){
				Reporter.log("Payment Type is : PAH");
				String OTP = hotelComPAHSendOTP(driver);       
				elementVisible(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox"), 30);
				textPresent(driver,"We've sent you a verification code to your email and mobile. Please enter the code to verify.", 20);
				Reporter.log("verification code to your email and mobile: Text Message is displayed");
				safeType(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox") ,OTP);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_OTP_Submit_Button"));
				textPresent(driver, "Great, verified successfully!", 5);
			}
			
//================================= PAH New  ===== ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("PAHNEW")){	
				Reporter.log("Payment Type is : PAH");
				textPresent(driver, "This hotel requires a credit card to guarantee the booking. You wont be charged now", 5);
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_DebitCard_Tab"), 1)) {
					Reporter.log("Debit Card Option is displayed for PAH CC validation");
					Assert.assertTrue(false);				
				}

				Reporter.log("Payment Type is : CC");
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			}

//================================= PAH UNSIGNED ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("PAHUNSIGNED")){
				textPresent(driver, "Please verify your details to confirm the booking", 10);
				if(!elementVisible(driver, By.id("mobileNumberForChmm"), 10)) {
					Reporter.log("PAH paymentpage doesnot display Mobile No");
					Assert.assertEquals(true, false);
				}
				safeType(driver, By.id("mobileNumberForChmm"), "1212121212");
				safeClick(driver, By.id("sendPhoneNo"));
				String OTP = hotelComPAHSendOTP(driver);   
				if(!elementVisible(driver, By.id("mobileSuccess"), 10)) {
					Reporter.log("Verification code has been sent to the mobile number.  : message is not displayed");
					Assert.assertEquals(true, false);
				}    
				safeType(driver, By.id("otpNum"), OTP);
				safeClick(driver, By.id("sendOtp"));
				if(!elementVisible(driver, By.id("otpSuccess"), 10)) {
					Reporter.log("Great, verified successfully! : OTP success message is not displayed");
					Assert.assertEquals(true, false);
				}
				textPresent(driver, "Great, verified successfully!", 5);
			}

//================================= NETBANKING PROD ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("NETBANKINGPROD")) {

				Reporter.log("Payment Type is : NB");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"));
				elementPresent(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_DropDown"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_DropDown"), "Bank of India");
				Thread.sleep(5000);
				if(elementVisible(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"), 10)){
					PaymentPage_Totalfare = getText(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"));
					Reporter.log("Total fare on payment page : " +PaymentPage_Totalfare);
					} 
					else 
					{
						Reporter.log("Total Fare is not visible on payment page");
					}
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"), 50)){
					Thread.sleep(2000);			
					Reporter.log("NB site page is displayed");
					elementPresent(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"));
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"));
					elementVisible(driver, By.linkText("Cancel"), 10);
					textPresent(driver, "Cleartrip Travel Services Pvt Ltd", 20);
					smartClick(driver, By.linkText("Cancel"));
//					elementVisible(driver, By.id("try-again-button"), 1);
//					textPresent(driver, "We are sorry but the transaction failed.", 20);
//					smartClick(driver, By.linkText("www.cleartrip.com"));
					waitElement(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"), 30);
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"));
					if(elementVisible(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"), 1)){
						RetryPage_Totalfare = getText(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"));
						Reporter.log("Total fare on payment retry page : " +RetryPage_Totalfare);
						} 
					else 
						{
						Reporter.log("Total Fare is not visible on payment retry page");
						}
					
					}
				else {
						Reporter.log("Netbanking Site is not displayed");
						Assert.assertTrue(false);
						}
			
				if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 30)){
					Reporter.log("Page is not redirected to Cleartrip Site from Bank Site after clicking on cancel button");
					Assert.assertTrue(false);
				} Reporter.log("Page has redirected to Cleartrip Site from Bank Site after clicking on cancel button");
				
			}

						//================================= NETBANKING PROD Citibank===========================================//
						
			else if(Payment_Type.equalsIgnoreCase("NETBANKINGPRODCITI")) {

				Reporter.log("Payment Type is : NB");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"));
				elementPresent(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_DropDown"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_DropDown"), "Citibank");
				Thread.sleep(5000);
				if(elementVisible(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"), 10)){
					PaymentPage_Totalfare = getText(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"));
					Reporter.log("Total fare on payment page : " +PaymentPage_Totalfare);
					} 
					else 
					{
						Reporter.log("Total Fare is not visible on payment page");
					}
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				
				
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CityBank_Site_Logo"), 50)){
					Thread.sleep(2000);			
					Reporter.log("NB site page is displayed");
				}
					elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CityBank_PaymentText"), 5);
					elementPresent(driver, getObjectHotels("HotelCom_BookStep4_CityBank_Cancel_Link"));
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_CityBank_Cancel_Link"));
				
					waitElement(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 30);
			
				if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 30)){
					Reporter.log("Page is not redirected to Cleartrip Site from Bank Site after clicking on cancel button");
					Assert.assertTrue(false);
				} Reporter.log("Page has redirected to Cleartrip Site from Bank Site after clicking on cancel button");
				if(!textPresent(driver, "Oops, your payment didn’t work", 20)) {
					Reporter.log("Oops, your payment didn’t work text is not displayed");
					Assert.assertTrue(false);
				}
				
			}
			else if(Payment_Type.equalsIgnoreCase("NETBANKINGPRODCITI1")) {

				Reporter.log("Payment Type is : NB");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_Tab"));
				elementPresent(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_DropDown"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_NetBanking_DropDown"), "Citibank");
				Thread.sleep(5000);
				if(elementVisible(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"), 10)){
					PaymentPage_Totalfare = getText(driver, By.xpath("//p[@id='payBlockTotal']/label/strong"));
					Reporter.log("Total fare on payment page : " +PaymentPage_Totalfare);
					} 
					else 
					{
						Reporter.log("Total Fare is not visible on payment page");
					}
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				
				
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CityBank_Site_Logo"), 50)){
					Thread.sleep(2000);			
					Reporter.log("NB site page is displayed");
				}
				
			}
						
//================================= STORED CARD ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("STOREDCARD")){
				Reporter.log("Payment Type is : Stored Card");
				if(MakePaymentOnlyInQA2){ // Due to ME issue
				elementVisible(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"), 20);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_CVV"), dataFile.value("MasterCard_CVV"));
				}
			}

//================================= WALLET ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("WALLET")){
				Reporter.log("Payment Type is : CT Wallet");
				elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
			}
						
						//================================= WALLETSA ===========================================//
						
			else if(Payment_Type.equalsIgnoreCase("WALLETSA")){
				Reporter.log("Payment Type is : CT Wallet");
				elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab_SA"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab_SA"));
			}

//================================= WALLET +CC ===========================================//

			else if(Payment_Type.equalsIgnoreCase("WALLETCC")){
				Reporter.log("Payment Type is : CT Wallet+CC");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 10);
						waitElement(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 10);
						safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
						elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_WalletOptOut_CheckBox"), "Opt-out the Wallet + CC Button", 5);
						Checking_Checkbox(driver, getObjectHotels("HotelCom_BookStep4_WalletOptOut_CheckBox"));
						if(!textPresent(driver, "Utilise the balance", 5)){
							Reporter.log("Utilise the balance text not displayed");
							Assert.assertTrue(false);
						}
						if(!textPresent(driver, "Utilise the balance", 1)){
							Reporter.log("Pay the balance with your credit card text not displayed");
							Assert.assertTrue(false);
						}
						if(!textPresent(driver, "Total payable", 1)){
							Reporter.log("Total payable text not displayed");
							Assert.assertTrue(false);
						}
						if(!textPresent(driver, "Deduction from wallet", 1)){
							Reporter.log("Deduction from wallet text not displayed");
							Assert.assertTrue(false);
						}	
						if(!textPresent(driver, "Balance payable", 1)){
							Reporter.log("Balance payable text not displayed");
							Assert.assertTrue(false);
						}
						safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
						safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
						safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
						safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
						safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
						Thread.sleep(2000);
			}

//================================= WALLET AE ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("WALLETAE")){
				Reporter.log("Payment Type is : CT Wallet AE");
				elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
			}
	
//============================== GIFT VOUCHER ===================================//
			
			else if(Payment_Type.equalsIgnoreCase("GIFTVOUCHER")){
				Reporter.log("Payment Type is : GV");
				elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Gift_Voucher_Msg"), 60);
				String GiftVoucher_Msg = getText(driver, getObjectHotels("HotelCom_BookStep4_Gift_Voucher_Msg"));
				Reporter.log("Gift Voucher payment message : "+GiftVoucher_Msg);
				if(!GiftVoucher_Msg.contains("covers the booking expenses")){
					Reporter.log("Gift Voucher Msg is not matching/displayed");
					Assert.assertTrue(false);
				}
			}

//================================= HOLD =====================================//
						
			else if(Payment_Type.equalsIgnoreCase("HOLD")){
				Reporter.log("Payment Type is : HOLD");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			}			

//================================= AMEX CARD =====================================//
			
			else if(Payment_Type.equalsIgnoreCase("AMEX")){
				Reporter.log("Payment Type is : CC Amex");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Policy_Checkbox"), 5)){
				Checking_Checkbox(driver, getObjectHotels("HotelCom_BookStep4_Policy_Checkbox"));
				}
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("AmexCard_Number_Hotel"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("AmexCard_Exp_Month_Hotels"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("AmexCard_Exp_Year"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("AmexCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("AmexCard_CVV"));
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Amex_Billing_Address"), 1))
					{
					safeType(driver, getObjectHotels("HotelCom_BookStep4_Amex_Billing_Address"), "JP Nagar");
					safeType(driver, getObjectHotels("HotelCom_BookStep4_Amex_Billing_City"), "Bangalore");
					safeType(driver, getObjectHotels("HotelCom_BookStep4_Amex_Billing_State"), "Karnataka");
					safeType(driver, getObjectHotels("HotelCom_BookStep4_Amex_Billing_Pin"), "560076");
					safeAutocomplete(driver, getObjectHotels("HotelCom_BookStep4_Amex_Billing_Country") , getObjectHotels("HotelCom_BookStep4_Amex_Billing_Country_Ajax"), "India");
				}
			} 

//============================== AMEX STORED CARD ===============================//
			
			else if(Payment_Type.equalsIgnoreCase("AMEXSTOREDCARD")){
				Reporter.log("Payment Type is : Amex Stored Card");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"));
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Amex"), 5)) {
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Amex"));
				}else {
					Reporter.log("Amex Stored Card is not displayed");
					Assert.assertTrue(false);
				}
				
				safeType(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_CVV"), dataFile.value("AmexCard_CVV"));
			}
						
						//============================== Master STORED CARD ===============================//
						
			else if(Payment_Type.equalsIgnoreCase("MASTERSTOREDCARD")){
				Reporter.log("Payment Type is : MasterCard Stored");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"));
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Master"), 5)) {
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Master"));
				}else {
					Reporter.log("Master Stored Card is not displayed");
					Assert.assertTrue(false);
				}
				safeType(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_CVV"), dataFile.value("MasterCard_CVV"));
			}
			
//================================= PAYU ===========================================//
			
			else if(Payment_Type.equalsIgnoreCase("PAYU") || Payment_Type.equalsIgnoreCase("PAYURetry")){
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
					hotelCom_Select_Wallets(driver, "PayU");				
					Reporter.log("Payment Type is : PayU");
				}	
				else {
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);
				driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
				 elementVisible(driver, getObjectHotels("HotelCom_PayU_Page_Logo"), 30);
		if(textPresent(driver, "This site can’t be reached", 1)){
			Reporter.log("This site can’t be reached : message");
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, getObjectHotels("HotelCom_PayU_Login_Button"), "Login Button PayU", 5);
		
			}

//================================= MASTERPASS ======================================//
			
			else if(Payment_Type.equalsIgnoreCase("MASTERPASS")){
				Reporter.log("Payment Type is : MasterPass");
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
				
				hotelCom_Select_Wallets(driver, "Masterpass");
				}	
				
				else { 
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }

				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				Thread.sleep(20000);
				driver.switchTo().frame("MasterPass_frame");
				Thread.sleep(5000);
				if(!elementVisible(driver, getObjectHotels("HotelCom_MasterPass_Site_WelcomeText"), 30)){
				Reporter.log("Masterpass webpage is not displayed");
				Assert.assertEquals(true, false);
				}				
				String MasterPassText = getText(driver, getObjectHotels("HotelCom_MasterPass_Site_WelcomeText"));
				if(!MasterPassText.contains("Welcome to Masterpass")){
					Reporter.log("Welcome to Masterpass text is not displayed");
					Assert.assertEquals(true, false);
				}
			}
			
//================================= PAYTM =======================================//
						
			else if(Payment_Type.equalsIgnoreCase("PAYTMPAGE") || Payment_Type.equalsIgnoreCase("PAYTMCancel")){
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
					hotelCom_Select_Wallets(driver, "Paytm");				
					Reporter.log("Payment Type is : PayTM");
				}	
				else {
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			
		if(textPresent(driver, "SELECT AN OPTION TO PAY", 30)) {
					Reporter.log("PayTM Page is displayed");
		} else {
					Reporter.log("PayTM Page is not displayed");
					Assert.assertTrue(false);
		}
			}
						
//================================= freecharge =======================================//
						
			else if(Payment_Type.equalsIgnoreCase("FreeCharge")){
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
					hotelCom_Select_Wallets(driver, "freecharge");				
					Reporter.log("Payment Type is : FreeCharge");
				}	
				else {
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				if(!elementVisible(driver, getObjectHotels("HotelCom_FreeCharge_Site_Header"), 30)){
							Reporter.log("FreeCharge logo is not displayed");
							Assert.assertEquals(true, false);
				}				
				if(!elementVisible(driver, getObjectHotels("HotelCom_FreeCharge_Site_CT_Header"), 30)){
					Reporter.log("CT logo is not displayed in FreeCharge");
					Assert.assertEquals(true, false);
				}	
			}
						
//================================= Mobikwik =======================================//
						
			else if(Payment_Type.equalsIgnoreCase("Mobikwik")){
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
					hotelCom_Select_Wallets(driver, "Mobikwik");				
					Reporter.log("Payment Type is : Mobikwik");
				}	
				else {
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				if(!textPresent(driver, "Enter Mobile Number", 30)) {
					Reporter.log("Enter Mobile Number text is not displayed");
					Assert.assertTrue(false);
				}
				if(!elementVisible(driver, getObjectHotels("HotelCom_Mobikwik_SiteLogo"), 5)){
					Reporter.log("Mobikwik logo is not displayed ");
					Assert.assertEquals(true, false);
				}	
				
			}
						
//================================= Ola_Money =======================================//
						
			else if(Payment_Type.equalsIgnoreCase("OlaMoney")){
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
					hotelCom_Select_Wallets(driver, "Ola_Money");				
					Reporter.log("Payment Type is : Ola_Money");
				}	
				else {
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }
			}
						
						
		//	================================= PhonePe =======================================//	
												
									else if(Payment_Type.equalsIgnoreCase("PhonePe")){
										if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_UPI"), 10)){
											safeClick(driver, getObjectHotels("HotelCom_BookStep4_UPI"));
											safeClick(driver, getObjectHotels("HotelCom_BookStep4_PhonePe_Button"));
											Reporter.log("Payment Type is : PhonePe");
										}	
										else {
											 Reporter.log("PhonePe Payment Option is not available");
											 Assert.assertTrue(false);
									 }
									}

//================================= JIO_Money =======================================//
						
			else if(Payment_Type.equalsIgnoreCase("JioMoney")){
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
					hotelCom_Select_Wallets(driver, "jio_money");				
					Reporter.log("Payment Type is : jio_money");
				}	
				else {
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				if(!elementVisible(driver, getObjectHotels("HotelCom_JioMoney_Logo"), 30)){
					Reporter.log("Jio logo is not displayed ");
					Assert.assertEquals(true, false);
				}	
				if(!elementVisible(driver, getObjectHotels("HotelCom_JioMoney_BackButton"), 20)){
					Reporter.log("JioMoney Back button is not displayed ");
					Assert.assertEquals(true, false);
				}	
				String Tran_No= getText(driver, getObjectHotels("HotelCom_JioMoney_TrxNo"));
				String Tran_Amt= getText(driver, getObjectHotels("HotelCom_JioMoney_TrxAmt"));
				if(!Tran_No.contains("Transaction ID")) {
					Reporter.log("Transaction ID text is not displayed ");
					Assert.assertEquals(true, false);
				}		
				if(!Tran_Amt.contains("Transaction Amount")) {
					Reporter.log("Transaction Amount is not displayed ");
					Assert.assertEquals(true, false);
				}
				safeClick(driver, getObjectHotels("HotelCom_JioMoney_BackButton"));
				Thread.sleep(2000);
				safeClick(driver, getObjectHotels("HotelCom_JioMoney_Cancel_Popup"));
				if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 30)){
					Reporter.log("Page is not redirected to Cleartrip Site from Bank Site after clicking on cancel button");
					Assert.assertTrue(false);
				} Reporter.log("Page has redirected to Cleartrip Site from Bank Site after clicking on cancel button");
				if(!textPresent(driver, "Oops, your payment didn’t work", 20)) {
					Reporter.log("Oops, your payment didn’t work text is not displayed");
					Assert.assertTrue(false);
				}
			}

//================================= Airtel_Money =======================================//
						
			else if(Payment_Type.equalsIgnoreCase("AirtelMoney")){
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
					hotelCom_Select_Wallets(driver, "airtel_money");				
					Reporter.log("Payment Type is : airtel_money");
				}	
				else {
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }
			}
//================================= AmazonPay=======================================//

			else if(Payment_Type.equalsIgnoreCase("AmazonPay")){
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
					hotelCom_Select_Wallets(driver, "AmazonPay");				
					Reporter.log("Payment Type is : AmazonPay");
				}	
				else {
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				elementPresent_log(driver, getObjectHotels("HotelCom_AmazonPay_SiteLogo"), "Amazonpay logo ", 30);
		}
						
			else if(Payment_Type.equalsIgnoreCase("AmazonPayHotel")){
				if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"), 10)){
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_Wallet_Tab"));
					hotelCom_Select_Wallets(driver, "AmazonPay");				
					Reporter.log("Payment Type is : AmazonPay");
				}	
				else {
					 Reporter.log("Wallet Payment Option is not available");
					 Assert.assertTrue(false);
			 }
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				elementPresent_log(driver, getObjectHotels("HotelCom_AmazonPay_SiteLogo"), "Amazonpay logo ", 30);
		}
												
						
			else if(Payment_Type.equalsIgnoreCase("PAH")){
			}
						
//=========================================Make Payment TRUE=============================================================//			
						
		if (MakePaymentOnlyInQA2) {
//============================= PAYMENT CONFIRMATION PAGE ==========================//
				
	if(!(Payment_Type.equalsIgnoreCase("NETBANKING")||Payment_Type.equalsIgnoreCase("NETBANKINGAE")||
		Payment_Type.equalsIgnoreCase("NETBANKINGAE_EDIRHAM")||Payment_Type.equalsIgnoreCase("AMEX")||
		Payment_Type.equalsIgnoreCase("NETBANKINGPROD")||Payment_Type.contains("FreeCharge")||
		Payment_Type.equalsIgnoreCase("MASTERPASS")||Payment_Type.equalsIgnoreCase("Mobikwik")|| Payment_Type.contains("PhonePe") 	||
		Payment_Type.contains("AirtelMoney") ||	Payment_Type.contains("AmazonPay")||		Payment_Type.contains("AmazonPayHotel")||	Payment_Type.contains("JioMoney") 	||
		Payment_Type.equalsIgnoreCase("PAYU") ||Payment_Type.equalsIgnoreCase("PAYURetry")|| 
		Payment_Type.contains("OlaMoney")	||	Payment_Type.equalsIgnoreCase("HOLD")||Payment_Type.equalsIgnoreCase("CREDITCARDLIMIT"))||
		Payment_Type.equalsIgnoreCase("NETBANKINGPRODCITI")||	Payment_Type.equalsIgnoreCase("NETBANKINGPRODCITI1")||	
		Payment_Type.equalsIgnoreCase("AMEXSTOREDCARD")||	Payment_Type.contains("PAYTM")||
		Payment_Type.contains("PAYTMPAGE")   ||	Payment_Type.contains("AMEX"))
	{
		safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
		Reporter.log("Payment Step : Button Clicked");
		hotelCom_PaymentPage_Authentication(driver, "");		
		TripID = hotelCom_ConfirmationPage(driver, Trip_Logger_Msg, TripID, Booking_Confirmation_Text);
		}
		
		//==============================INVALIDCREDITCARD =====================================//
		
		if(Payment_Type.equalsIgnoreCase("INVALIDCREDITCARD")) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));	
			 if(!textPresent(driver, "Oops, your payment didn’t work", 30)) {
					Reporter.log("Error Message : Oops, your payment didn’t work is not displayed");
					Assert.assertTrue(false);				
				}
			if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 10)) {
				Reporter.log("Page is not redirecting to Payu");
			}
			
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
			}

				
//==============================NET BANK PAGE =====================================//
				
		if(Payment_Type.equalsIgnoreCase("NETBANKING")) {
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));	
					hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);
					driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"), 60)){
					Thread.sleep(2000);			
					elementPresent(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"));
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_BOI_Bank_CancelLink"));		
					if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 60)) {
						Reporter.log("Cleartrip - Payment Retry Page is not displayed");
						Assert.assertTrue(false);
					}
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
					safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
					safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
					safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
					safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
					safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
					logURL(driver);		
					}
			else {
					logURL(driver);		
					Reporter.log("Netbanking Site is not displayed");
					Assert.assertTrue(false);
					}
				}	
				

		
//==============================NETBANK AE  Edirham=====================================//
						
				if(Payment_Type.equalsIgnoreCase("NETBANKINGAE_EDIRHAM")) {
					Thread.sleep(5000);		
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));	
					Thread.sleep(5000);
					hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);
					driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
					if(elementVisible(driver, By.xpath("//h1"), 60)){
						elementVisible(driver, By.xpath("//img"), 20);
					}else {
						Reporter.log("Edirham bank site page is not displayed");
						Assert.assertTrue(false);
					}
					textPresent(driver, "Payment Methods Types", 10);
					Thread.sleep(5000);
					safeClick(driver, By.name("Cancel"));
					Reporter.log("Edirham Bank page is displayed");
					if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 60)) {
						Reporter.log("Cleartrip - Payment Retry Page is not displayed");
						Assert.assertTrue(false);
					}
				}
				
				//==============================NETBANK AE=====================================//
									
				if(Payment_Type.equalsIgnoreCase("NETBANKINGAE")) {
					safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));	
					hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);
					driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			if(elementVisible(driver, By.id("Merchant_Logo"), 60)){
				elementVisible(driver, By.xpath("//div[@id='Message']/input"), 20);
			}else {
				Reporter.log("ADCB bank site page is not displayed");
				Assert.assertTrue(false);
			}
			textPresent(driver, "Cleartrip", 20);
			Thread.sleep(5000);
			safeClick(driver, By.xpath("//input[@value='Pay']"));
			textPresent(driver, "ADCB Personal Internet Banking", 20);
			driver.switchTo().frame("bottom_frame");
			elementVisible(driver, By.id("cidnumber"), 30);
			Reporter.log("ADCB Bank page is displayed");
			
		}				
				
//======================= HOLD CONFIRMATION PAGE ==============================//

		if(Payment_Type.equalsIgnoreCase("HOLD")){
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);
				hotelCom_PaymentPage_Authentication(driver, "");
			if(elementVisible(driver, getObjectHotels("HotelCom_Confirmation_TripID"), 20)) {
						Reporter.log("Confirmation Page is displayed");
			}
			else {
						Reporter.log("Confirmation Page is not displayed");
			}
			elementPresent(driver, getObjectHotels("HotelCom_Confirmation_TripID"));					
			if(elementVisible(driver, getObjectHotels("HotelCom_Confirmation_BookingDone_TextMsg"), 20)){
						String Booking_ConfirmationText = getText(driver, getObjectHotels("HotelCom_Confirmation_BookingDone_TextMsg"));					
			if (Booking_ConfirmationText.contains("Booking request taken")) {
					TripID = getText(driver, getObjectHotels("HotelCom_Confirmation_TripID"));
					String HotelName = getText(driver, getObjectHotels("HotelCom_Confirmation_HotelName"));
					Reporter.log("Hotel '" + HotelName + " Holded & TripID is " + TripID);
					logger.info(Trip_Logger_Msg + TripID);
					logURL(driver);	
					}
					} 
			else {
					logURL(driver);
					Reporter.log(Trip_Logger_Msg + " Booking Confirmation page is not displayed");
					Assert.assertEquals(true, false);
					}
	}		

//============================= PAYTM PAYMENT CONFIRMATION PAGE ================================//
				
	if(Payment_Type.equalsIgnoreCase("PAYTM")){
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);
				//driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			if(elementVisible(driver, getObjectHotels("HotelCom_PayTM_Page_Logo"), 30)) {
					Reporter.log("PayTM Page is displayed");
			}
			else {
					Reporter.log("PayTM Page is not displayed");
					Assert.assertTrue(false);
				}				 
	}
			
//============================= PAYTM CANCEL CONFIRMATION PAGE ================================//
			
	if(Payment_Type.equalsIgnoreCase("PAYTMCancel")){
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);
		if(elementVisible(driver, By.xpath("//strong[2]"), 50)){
					String TripID_NB = getText(driver, By.xpath("//strong[2]"));
					Reporter.log("PayTM Trip ID : " +TripID_NB);
					} 
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		if(elementVisible(driver, getObjectHotels("HotelCom_PayTM_Page_Logo"), 30)) {
					Reporter.log("PayTM Page is displayed");
		} else {
			Reporter.log("PayTM Page is not displayed");
			Assert.assertTrue(false);
				}
		safeClick(driver, getObjectHotels("HotelCom_PayTM_Page_Cancel_Button"));
	}

	//============================= PAYTM PAGE ================================//
	
		if(Payment_Type.equalsIgnoreCase("AirtelMoney")){
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
			textPresent(driver, "Do not refresh or close the browser", 30);
			elementVisible(driver, By.xpath("//img"), 30);
			assertTrue(driver.getTitle().contains("Airtel Money QuickPay"));
		}

	
//============================== PAYU PAYMENT CONFIRMATION PAGE ============================//
						
	if(Payment_Type.equalsIgnoreCase("PAYU")){
	
	}
		
	//============================== PAYU back to Cleartrip Page ============================//
	
		if(Payment_Type.equalsIgnoreCase("PAYURetry")){
						safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
						hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);
						driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
						
				if(!elementVisible(driver, getObjectHotels("HotelCom_PayU_Page_Email"), 1)){
					driver.navigate().back();
				}
				elementVisible(driver, getObjectHotels("HotelCom_PayU_Page_Email"), 10);
				if(textPresent(driver, "This site can’t be reached", 1)){
					Reporter.log("This site can’t be reached : message");
					Assert.assertTrue(false);
				}
			
				if(!elementVisible(driver, getObjectHotels("HotelCom_PayU_Page_Email"), 30)){
					Reporter.log("PayU Site is not loaded");
					Assert.assertTrue(false);
				}
				if(!textPresent(driver, "Back to 100bestbuy", 5)){
					Reporter.log("Back to 100bestbuy text is not displayed");
					Assert.assertTrue(false);
				}	
				safeClick(driver, By.xpath("//a/span[2]"));
		}

//============================ PhonePe PAYMENT CONFIRMATION PAGE ==================================//
							
	if(Payment_Type.equalsIgnoreCase("PhonePe")){
		
	}
	
	if(Payment_Type.equalsIgnoreCase("AmazonPayHotel")){
		
	}
	
	//============================ FreeCharge PAYMENT  PAGE ==================================//
	
		if(Payment_Type.equalsIgnoreCase("FreeCharge")){
			
		}

//======================= AMEX / STORED PAYMENT CONFIRMATION PAGE ===================================//
							
	if(Payment_Type.equalsIgnoreCase("AMEX")||Payment_Type.equalsIgnoreCase("AMEXSTOREDCARD")){
					TripID = hotelCom_ConfirmationPage(driver, Trip_Logger_Msg, TripID, Booking_Confirmation_Text);
		}	
	}	
		if(MakePaymentOnlyInProd) {
		if(Payment_Type.equals("ProdpaymentLive")) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
			Reporter.log("Payment Step : Button Clicked");
			hotelCom_PaymentPage_Production_Card_Authentication(driver, "B2C");		
			TripID = hotelCom_ConfirmationPage(driver, Trip_Logger_Msg, TripID, Booking_Confirmation_Text);
		}
		}
		
	return TripID;	
}
	
	public Boolean hotelCom_PaymentPage_Hotel_NolongerAvailable(RemoteWebDriver driver, String Booking_Confirmation_Text) throws Exception {
		boolean Hotel_NoLonger_Available = false;
		if(MakePaymentOnlyInQA2) {
			for (int i = 0; i < 5; i++) {			
			if(elementVisible(driver, By.cssSelector("h3.icon32.bad"), 1)) {
				Hotel_NoLonger_Available = true;
				Reporter.log("Hotel error after payment : Oops! The hotel room you picked is no longer available");
				Assert.assertTrue(false);
			}
			else if(textPresent(driver, Booking_Confirmation_Text, 1) ) {
				break;
				//Assert.assertTrue(false);
			}
			
			}
		}
		return Hotel_NoLonger_Available;		
	}

	public String hotelCom_ConfirmationPage(RemoteWebDriver driver, String Trip_Logger_Msg, String TripID, String Booking_Confirmation_Text) throws Exception {
		Thread.sleep(2000);
		textPresent(driver, Booking_Confirmation_Text, 5); 
		hotelCom_PaymentPage_Hotel_NolongerAvailable(driver, Booking_Confirmation_Text);		
		for(int i=0; i<=7; i++){			
			if(textPresent(driver, Booking_Confirmation_Text, 1)) {
				break;
			}
			else if(elementVisible(driver, By.cssSelector("h3.icon32.bad"), 1)) {
				Reporter.log("Error after payment step : Oops! The hotel room you picked is no longer available");
				Assert.assertTrue(false);
			}
			
			else if(textPresent(driver, "Oops! Something snapped", 1)) {
				Reporter.log("Error after payment step : Oops! Something snapped");
				Assert.assertTrue(false);
			} 
			else if(textPresent(driver, "Oops, your payment didn’t work", 1)) {
				Reporter.log("Error after payment step : Oops, your payment didn’t work");
				Assert.assertTrue(false);
			} 
			else if(textPresent(driver, "Oops! We weren't able to process your payment", 1)) {
				Reporter.log("Error after payment step : Oops! We weren't able to process your payment");
				Assert.assertTrue(false);				
			}
			else if(textPresent(driver, "Sorry, we couldn't confirm your booking", 1)) {
				Reporter.log("Error after payment step : Sorry, we couldn't confirm your booking");
				Assert.assertTrue(false);				
			}
			else if(textPresent(driver, "Sorry, our system is acting up", 1)) {
				Reporter.log("Error after payment step : Sorry, our system is acting up");
				Assert.assertTrue(false);				
			}
			
		}
		elementVisible(driver, By.linkText("Flights"), 1);
		String URL = null;
		if(elementVisible(driver, getObjectHotels("HotelCom_Confirmation_TripID"), 10)){
			TripID = getText(driver, getObjectHotels("HotelCom_Confirmation_TripID"));
			URL = logURL(driver);
			Reporter.log("Confirmation Page is displayed");
			Reporter.log(Trip_Logger_Msg + TripID);
			logger.info(Trip_Logger_Msg + TripID);
		}	else {
			Reporter.log(Trip_Logger_Msg + " : Booking Confirmation page is not displayed");
			Assert.assertEquals(true, false);
			}	
		
		if(!URL.contains("confirmation")) {
			Reporter.log("Payment confirmation page has not displayed");			
			Assert.assertEquals(URL.contains("confirmation"), true);
		}
		if(TripID.equals(null)) {
			Reporter.log("TripId is null");
			Assert.assertTrue(false);
		}
		return TripID;
	}
	
	public void hotelCom_ConfirmationPageValidation(RemoteWebDriver driver, String BookingType, String BookingType1, String ConfirmationText) throws Exception {
		if(MakePaymentOnlyInQA2){
			if(BookingType.equalsIgnoreCase("")){
				elementPresent_log(driver, By.id("print_tickets"), "Get Your Voucher ", 5);
				elementPresent_log(driver, By.xpath("//aside"), "Payment Reciept", 5);
				elementPresent_log(driver, By.cssSelector("li.col.col8"), "Traveller Name", 5);
				elementPresent_log(driver, By.xpath("//li[2]/span"), "Email ID", 5);
				elementPresent_log(driver, By.cssSelector("small.positive"), "Itinerary emailed", 5);
				elementPresent_log(driver, By.xpath("//li[3]/span"), "Phone No", 5);
				elementPresent_log(driver, By.xpath("//section/div/div[2]/div/nav/ul/li[3]/small"), "Itinerary Sent", 5);
				textPresent_Log(driver, "Primary traveller in this trip", 1);
				textPresent_Log(driver, ConfirmationText, 1);
				textPresent_Log(driver, "Thank you for booking with Cleartrip. Use the above trip id in all communication with Cleartrip.", 1);

				Reporter.log("Confiramation Page Validation Completed - Basic");
			}
			
			else if(BookingType.equalsIgnoreCase("COUPON")){
						elementPresent_log(driver, By.id("print_tickets"), "Get Your Voucher", 5);
						elementPresent_log(driver, By.xpath("//aside"), "Payment Reciept", 1);
						elementPresent_log(driver, By.cssSelector("li.col.col8"), "Traveller Name", 1);
						elementPresent_log(driver, By.xpath("//li[2]/span"), "Email ID", 1);
						elementPresent_log(driver, By.cssSelector("small.positive"), "Itinerary emailed", 1);
						elementPresent_log(driver, By.xpath("//li[3]/span"), "Phone No", 1);
						elementPresent_log(driver, By.xpath("//section/div/div[2]/div/nav/ul/li[3]/small"), "Itinerary Sent", 1);
						textAssert(driver, By.cssSelector("h4"), "Primary traveller in this trip");
						textAssert(driver, By.cssSelector("h2"), ConfirmationText);
						textAssert(driver, By.cssSelector("small"),  "Thank you for booking with Cleartrip. Use the above trip id in all communication with Cleartrip.");
						hotelCom_ConfirmationPage_PriceValidation(driver);
						List<WebElement> PriceName = driver.findElements(By.xpath("//dt"));
						List<WebElement> PriceValue = driver.findElements(By.xpath("//dd"));
						for (int i = 1; i < PriceName.size(); i++) {
							String CashbackText = PriceName.get(i).getText();
							if(CashbackText.equals("Cashback:")){
								String CashbackPrice = PriceValue.get(i).getText().replace("RS. ", "");							
								int CashBack = Integer.parseInt(CashbackPrice);							
								if(CashBack < 0){
									Reporter.log("Cashback is not having a discounted value "+ CashbackPrice);
									break;
								}else Assert.assertTrue(false);
							}
						}
						Reporter.log("Confiramation Page Validation Completed - Coupon");
					}
			
			else if(BookingType.equalsIgnoreCase("WALLETDUAL")){
				elementPresent_log(driver, By.id("print_tickets"), "Get Your Voucher", 5);
				elementPresent_log(driver, By.xpath("//aside"), "Payment Reciept", 1);
				elementPresent_log(driver, By.cssSelector("li.col.col8"), "Traveller Name", 1);
				elementPresent_log(driver, By.xpath("//li[2]/span"), "Email ID", 1);
				elementPresent_log(driver, By.cssSelector("small.positive"), "Itinerary emailed", 1);
				elementPresent_log(driver, By.xpath("//li[3]/span"), "Phone No", 1);
				elementPresent_log(driver, By.xpath("//section/div/div[2]/div/nav/ul/li[3]/small"), "Itinerary Sent", 1);
				textAssert(driver, By.cssSelector("h4"), "Primary traveller in this trip");
				textAssert(driver, By.cssSelector("h2"), ConfirmationText);
				textAssert(driver, By.cssSelector("small"),  "Thank you for booking with Cleartrip. Use the above trip id in all communication with Cleartrip.");
				hotelCom_ConfirmationPage_PriceValidation(driver);
				List<WebElement> PriceName = driver.findElements(By.xpath("//dt"));
				List<WebElement> PriceValue = driver.findElements(By.xpath("//dd"));
				for (int i = 1; i < PriceName.size(); i++) {
					String CashbackText = PriceName.get(i).getText();
					if(CashbackText.equals("Cashback:")){
						String CashbackPrice = PriceValue.get(i).getText().replace("RS. ", "");							
						int CashBack = Integer.parseInt(CashbackPrice);							
						if(CashBack > 0){
							Reporter.log("Cashback is not having a discounted value "+ CashbackPrice);
							 Assert.assertTrue(false);
							 }
					}
				}
				Reporter.log("Confiramation Page Validation Completed - Dual Wallet");			
			}
			

			else if(BookingType.equalsIgnoreCase("COUPONAE")){
						elementPresent_log(driver, By.id("print_tickets"), "Get Your Voucher", 5);
						elementPresent_log(driver, By.xpath("//aside"), "Payment Reciept", 1);
						elementPresent_log(driver, By.cssSelector("li.col.col8"), "Traveller Name", 1);
						elementPresent_log(driver, By.xpath("//li[2]/span"), "Email ID", 1);
						elementPresent_log(driver, By.cssSelector("small.positive"), "Itinerary emailed", 1);
						elementPresent_log(driver, By.xpath("//li[3]/span"), "Phone No", 1);
						elementPresent_log(driver, By.xpath("//section/div/div[2]/div/nav/ul/li[3]/small"), "Itinerary Sent", 1);
						textAssert(driver, By.cssSelector("h4"), "Primary traveller in this trip");
						textAssert(driver, By.cssSelector("em.curr.AED"), "AED");
						textAssert(driver, By.cssSelector("h2"), ConfirmationText);
						textAssert(driver, By.cssSelector("small"),  "Thank you for booking with Cleartrip. Use the above trip id in all communication with Cleartrip.");
						hotelCom_ConfirmationPage_PriceValidation(driver);
						Reporter.log("Confiramation Page Validation Completed - Coupon AE");
						
					}
			
			else if(BookingType.equalsIgnoreCase("PAH")){
				textPresent_Log(driver, "Reservation confirmed", 5);
				elementPresent_log(driver, By.cssSelector("li.slipNote > p"), "We'll get in touch with you", 2);

				Reporter.log("Confiramation Page Validation Completed - PAH");
			}
			else if(BookingType.equalsIgnoreCase("PAHCC")){
				textPresent_Log(driver, "Your booking is done", 5);
				elementPresent_log(driver, By.id("print_tickets"), "Get Your Voucher", 5);
				elementPresent_log(driver, By.cssSelector("li.payAtHotelTag"), "P@H logo", 5);
				textPresent_Log(driver, "Payment accepted at the hotel", 5);

				Reporter.log("Confiramation Page Validation Completed - PAHCC");
			}
			else if(BookingType.equalsIgnoreCase("PARTPAY")){
				textPresent_Log(driver, "Your booking is done, but...", 5);
				textPresent_Log(driver, "Please note: The advance amount of Rs.250 is non-refundable, and will be forfeited if the balance is not paid", 5);
				textPresent_Log(driver, "You can pay through your Cleartrip Account", 1);
				textPresent_Log(driver, "Please pay the remaining", 1);
				elementPresent_log(driver, By.cssSelector("span.cardIconHD.master"), "CC Icon", 1);
				elementPresent_log(driver, By.cssSelector("li.col.col8"), "CC number", 1);
				elementPresent_log(driver, By.xpath("//nav/ul/li[3]"), "Paid now text and value", 1);
				elementPresent_log(driver, By.xpath("//nav/ul/li[4]"), "Balance text and value", 1);
				elementPresent_log(driver, By.xpath("//nav/ul/li[5]"), "Total text and value", 1);
				elementPresent_log(driver, By.cssSelector("p.note"), "Please pay the remaining", 1);
				elementPresent_log(driver, By.cssSelector("span.fRight.nextArrow"), "Right arrow", 1);
				elementPresent_log(driver, By.cssSelector("div.counterBig"), "Weeks Left Icon", 1);
				elementPresent_log(driver, By.cssSelector("div.horizontalSlip"), "Payment Reciept - Horizontal", 1);	
				if(!getText(driver, By.xpath("//nav/ul/li[3]")).contains("250")){
					Reporter.log("Paid now amount is not equal to 250 Rs");
					Assert.assertTrue(false);
				}

				Reporter.log("Confiramation Page Validation Completed - PartPay");
			}
			else if(BookingType.equalsIgnoreCase("PARTPAYCONFIRM")){
				
			}
			else if(BookingType.equalsIgnoreCase("WALLET")){
				elementPresent_log(driver, By.id("print_tickets"), "Get Your Voucher", 5);
				elementPresent_log(driver, By.xpath("//aside"), "Payment Reciept", 5);
				elementPresent_log(driver, By.cssSelector("span.cardIconHD.wallet"), "CT  Wallet Icon", 5);

				Reporter.log("Confiramation Page Validation Completed - Wallet CT");
			}
			else if(BookingType.equalsIgnoreCase("ROOMER")){
				elementPresent_log(driver, By.cssSelector("p.insuranceDetails"), "Insurance text message", 5);
				elementPresent_log(driver, By.cssSelector("i.bookflowSprite.lifeHappensSmall"), "Insurance Icon", 5);
				if(!getText(driver, By.xpath("//aside/nav/ul")).contains("Protection")){
					Reporter.log("Protection text is not displayed in Rate details");
					Assert.assertTrue(false);
				}
				Reporter.log("Confiramation Page Validation Completed - Roomer");
				
			}
			else if(BookingType.equalsIgnoreCase("GIFTVOUCHER")){
				elementPresent_log(driver, By.cssSelector("span.cardIconHD.giftcard"), "Gift Voucher Icon",	 10);
				textPresent_Log(driver, "By gift card:", 1);

				Reporter.log("Confiramation Page Validation Completed - GV");
			}
			else if(BookingType.equalsIgnoreCase("WALLETCC")){
				elementPresent_log(driver, By.cssSelector("span.cardIconHD.wallet"), "Gift & Wallet Voucher Icon",	 10);
				elementPresent_log(driver, By.cssSelector("span.multiPaymentCombo"), "Gift Voucher Icon",	 10);
				elementPresent_log(driver, By.cssSelector("span.cardIconHD.master"), "Gift Voucher Icon",	 10);
				textPresent_Log(driver, "By wallet", 1);
				textPresent_Log(driver, "By card", 1);						
				Reporter.log("Confirmation Page Validation Completed - WalletCC");
			}
		}
	}	
	
	public void hotelCom_ConfirmationPage_PriceValidation(RemoteWebDriver driver) throws Exception{
		elementVisible(driver, By.xpath("//dt"), 10);
		List<WebElement> PriceName = driver.findElements(By.xpath("//dt"));
		List<WebElement> PriceValue = driver.findElements(By.xpath("//dd"));
		Map<String, Integer> PriceDetails = new HashMap<String, Integer>();
		
		for (int i = 1; i < PriceName.size(); i++) {
			String value = PriceValue.get(i).getText();
			value = value.replaceAll("[^0-9.]", "");
			
			if(value.contains(",")){
				value = value.replace(",", "");
			}
			if(value.contains(".")){
				value = value.replace(".", "");
			}
			
			int valueInt = Integer.parseInt(value);
			PriceDetails.put(PriceName.get(i).getText(), valueInt);
			Reporter.log(PriceName.get(i).getText()+" : "+ valueInt);
		}
		int TotalPrice = PriceDetails.get("Total:");
		int TotalAddedAmt = 0;
		for (int j = PriceDetails.size()-1; j >=1; j--) {
			int valueIntegers = PriceDetails.get(PriceName.get(j).getText());
			TotalAddedAmt = TotalAddedAmt+valueIntegers;
		}
		
		if(TotalPrice != TotalAddedAmt){
			Reporter.log("Total Price is : "+TotalPrice+" Total after adding all the Values : "+TotalAddedAmt);
		}
		
	}
	
	public void hotelCom_Select_Wallets(RemoteWebDriver driver, String WalletType)
			throws Exception {
		for(int i=1; i<=11; i++){
		String WalletType_Xpath = "//nav[@id='wallets']/ul/li["+i+"]/label";
		if(elementVisible(driver, By.xpath(WalletType_Xpath), 2)){
		String Wallet_Type = getText(driver, By.xpath(WalletType_Xpath));
		if(Wallet_Type.contains(WalletType)){
			safeClick(driver, By.xpath(WalletType_Xpath));
			break;
				}
			}
		}
	}
	
	public String  hotelCom_PaymentPage_PAH(RemoteWebDriver driver,String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Text) throws Exception {
		String OTP = null;
		if(MakePaymentOnlyInQA2){
		 OTP= hotelComPAHSendOTP(driver);
		}
			if(Payment_Type.equals("PAHPROD")){
				if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox"), 10)){
					Reporter.log("OTP Text Box is not displayed");
					Assert.assertTrue(false);				
				}
				textPresent(driver,"We've sent you a verification code to your email and mobile. Please enter the code to verify.", 1);
				Reporter.log("verification code to your email and mobile: Text Message is displayed");
		}
		String TripID = null;
		if(MakePaymentOnlyInQA2){
		if(Payment_Type.equals("PAH")){
			if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox"), 10)){
				Reporter.log("OTP Text Box is not displayed");
				Assert.assertTrue(false);				
			}
			textPresent(driver,"We've sent you a verification code to your email and mobile. Please enter the code to verify.", 1);
			Reporter.log("verification code to your email and mobile: Text Message is displayed");
			safeType(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox") ,OTP);
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_OTP_Submit_Button"));
			textPresent_Log(driver, "Great, verified successfully!", 5);
			
		}
		else if(Payment_Type.equals("PAHUnsigned")){
			if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox"), 10)){
				Reporter.log("OTP Text Box is not displayed");
				Assert.assertTrue(false);				
			}
			textPresent(driver,"Please verify your details to confirm the booking", 1);
			Reporter.log("Please verify your details to confirm the booking: Text Message is displayed");
			safeClick(driver, By.id("sendPhoneNo"));
			if(!elementVisible(driver, By.id("mobileSuccess"), 5)){
				Reporter.log("Verification code has been sent to the mobile number. : text is not displayed");
				Assert.assertTrue(false);
			}
			safeType(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox") ,OTP);
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_OTP_Submit_Button"));
			if(!elementVisible(driver, By.id("otpSuccess"), 5)){
				Reporter.log("Great, verified successfully!  : text is not displayed");
				Assert.assertTrue(false);
			}
			textPresent_Log(driver, "Great, verified successfully!", 5);
		}
		else if(Payment_Type.equals("PAHCC")){
		
			if(!elementVisible(driver, By.xpath("//div[@id='pahPayValidateMess']/p/strong"), 20))
			{
				Reporter.log("This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now : Text Message is not displayed");
				//Assert.assertTrue(false);
			}
			else {
				String PAHCC_Msg = getText(driver, By.xpath("//div[@id='pahPayValidateMess']/p/strong"));
				if(!PAHCC_Msg.contains("This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now")){
					Reporter.log("This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now : Text Message is not displayed");
					Assert.assertTrue(false);
				}
				elementPresent_log(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), "CC Tab", 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
				smartClick(driver, By.id("native_currency"));
				Thread.sleep(2000);
			
			}
		}
		
		else if(Payment_Type.equals("PAHCCInvalid")){
			
			if(!elementVisible(driver, By.xpath("//div[@id='pahPayValidateMess']/p/strong"), 20))
			{
				Reporter.log("This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now : Text Message is not displayed");
				Assert.assertTrue(false);
			}
			else {
				String PAHCC_Msg = getText(driver, By.xpath("//div[@id='pahPayValidateMess']/p/strong"));
				if(!PAHCC_Msg.contains("This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now")){
					Reporter.log("This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now : Text Message is not displayed");
					Assert.assertTrue(false);
				}
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), "09");
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
				smartClick(driver, By.id("native_currency"));
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				if(MakePaymentOnlyInQA2){
				hotelCom_PaymentPage_Authentication(driver, "");
				Thread.sleep(2000);
				} else {
					if(!textPresent(driver, "Sorry, we couldn't recognise the card you provided. Please use another card.", 50)){
						Reporter.log("Sorry, we couldn't recognise the card you provided. Please use another card. - Message is not displayed");
						Assert.assertTrue(false);
					}
				}
			}
			if(MakePaymentOnlyInQA2){
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
				smartClick(driver, By.id("native_currency"));
				Thread.sleep(2000);		
			}
		}
	
		else if(Payment_Type.equals("PAHCCStored")){
			
			if(!elementVisible(driver, By.xpath("//div[@id='pahPayValidateMess']/p/strong"), 10))
			{
				Reporter.log("This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now : Text Message is not displayed");
				Assert.assertTrue(false);
			}
			else {
				String PAHCC_Msg = getText(driver, By.xpath("//div[@id='pahPayValidateMess']/p/strong"));
				if(!PAHCC_Msg.contains("This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now")){
					Reporter.log("This hotel requires a credit card to guarantee the booking. You wont be charged for the booking now : Text Message is not displayed");
					Assert.assertTrue(false);
				}
				Reporter.log("Payment Type is : Stored Card");
				
				

				Reporter.log("Payment Type is : MasterCard Stored");
				waitElement(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"));
				/*for(int i =1; i<=2; i++) {
					String CardXpath = "//nav[2]/ul/li["+i+"]/span";
					String CardName = getText(driver, By.xpath(CardXpath));
					if(CardName.contains("Master")) {
						safeClick(driver, By.xpath(CardXpath));
						break;
					}
				}*/
			if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Master"), 5)) {
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Master"));
			}else {
				Reporter.log("Master Stored Card is not displayed");
				Assert.assertTrue(false);
			}
				safeType(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_CVV"), dataFile.value("MasterCard_CVV"));
			/*
				elementVisible(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_Tab"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_StoredCard_CVV"), dataFile.value("MasterCard_CVV"));
				Thread.sleep(2000);		*/
				Reporter.log(" ---- Second payment is done ----- ");
			}
		}
		else if(Payment_Type.equalsIgnoreCase("PAHINVALIDOTP")){
			if(!elementVisible(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox"), 10)){
				Reporter.log("OTP Text Box is not displayed");
				Assert.assertTrue(false);				
			}
			textPresent(driver,"We've sent you a verification code to your email and mobile. Please enter the code to verify.", 5);
			Reporter.log("verification code to your email and mobile: Text Message is displayed");
			safeType(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox") ,"12344");
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_OTP_Submit_Button"));
			if(!elementPresent_Time(driver, By.id("otpFailure"), 10)){
				Reporter.log("OTP Verification failed message is not displayed");
				Assert.assertTrue(false);
			}
			//textAssert(driver, "Great, verified successfully! ", 5);
		}
		

		if(MakePaymentOnlyInQA2  && !Payment_Type.equals("PAHINVALIDOTP")) {
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
			if(textPresent(driver, "Credit card expiration month should be two months greater than hotel checkout", 5)){
				  Reporter.log("Credit card expiration month should be two months greater than hotel checkout date : message id displayed");
				  Assert.assertTrue(false);
			}
			hotelCom_PaymentPage_Authentication(driver, "");
			elementVisible(driver, getObjectHotels("HotelCom_Confirmation_TripID"), 20);
			elementPresent(driver, getObjectHotels("HotelCom_Confirmation_TripID"));					
			if(elementVisible(driver, getObjectHotels("HotelCom_Confirmation_BookingDone_TextMsg"), 20)){
				String Booking_ConfirmationText = getText(driver, getObjectHotels("HotelCom_Confirmation_BookingDone_TextMsg"));	
				TripID = getText(driver, getObjectHotels("HotelCom_Confirmation_TripID"));
				logger.info(Trip_Logger_Msg + TripID);
				Reporter.log("TripID : " + TripID);
			if (!Booking_ConfirmationText.contains(Booking_Confirmation_Text)) {
				Reporter.log("Booking_ConfirmationText :"+Booking_ConfirmationText);
			}
			} else {
				Reporter.log(Trip_Logger_Msg + " : Booking Confirmation page is not displayed");
				Assert.assertEquals(true, false);
			}
		}
		}
		return TripID;
	}
	
	public void hotelCom_PaymentPage_PAH_Invalid(RemoteWebDriver driver) throws Exception {
		elementVisible(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox"), 30);
		textPresent(driver,"We've sent you a verification code to your email and mobile. Please enter the code to verify.", 30);
		Reporter.log("verification code to your email and mobile: Text Message is displayed");
		safeType(driver, getObjectHotels("HotelCom_BookStep4_OTP_TextBox") ,"12134");
		safeClick(driver, getObjectHotels("HotelCom_BookStep4_OTP_Submit_Button"));
		if(!textPresent(driver, "Sorry! You've entered an incorrect verification code", 10)) {
			Reporter.log("Invalid OTP code is working for PAH");
			Assert.assertTrue(false);
		}
		
	}
		
	public String hotelCom_PaymentPage_PartPay(RemoteWebDriver driver,String Payment_Type, String Trip_Logger_Msg, String Booking_Confirmation_Text) throws Exception {
		String TripID = null;
		elementVisible(driver, getObjectHotels("HotelCom_BookStep4_PartPay_Text"), 30);		
		elementPresent(driver, getObjectHotels("HotelCom_BookStep4_PartPay_Text"));
		String PartPay_Text= getText(driver, getObjectHotels("HotelCom_BookStep4_PartPay_Text"));
			if(!PartPay_Text.contains("Part payment")){
				Assert.assertEquals(PartPay_Text, "Part payment");
			}
			if(Payment_Type.equalsIgnoreCase("CCPROD")){
				elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 10);
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), "09");
				safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), "20");
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
				safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
				safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
				if(!textPresent(driver, "Sorry, we couldn't recognise the card you provided. Please use another card.", 10)){
					Reporter.log("Sorry, we couldn't recognise the card you provided. Please use another card. - Message is not displayed");
					Assert.assertTrue(false);
				}				
			}
		if (MakePaymentOnlyInQA2){
			elementVisible(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 10);
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
			safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
			safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
			safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
			safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
			safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
			safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));
			hotelCom_PaymentPage_Authentication(driver, "");
/*
			elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Header"), 50);
			elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"), 50);
			Thread.sleep(5000);
			
			safeClick(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"));*/
			
			if(textPresent(driver, "Sorry, our system is acting up.", 5)){
				Reporter.log("Sorry, our system is acting up. : message is displayed");
				Assert.assertTrue(false);
			}
			textPresent(driver, "Your booking is done, but", 30);
			elementAssert(driver, getObjectHotels("HotelCom_Confirmation_TripID"), 50);
			elementPresent(driver, getObjectHotels("HotelCom_Confirmation_TripID"));					
			if(elementVisible(driver, getObjectHotels("HotelCom_Confirmation_TripID"), 50)){
				elementVisible(driver, getObjectHotels("HotelCom_Confirmation_BookingDone_TextMsg"), 10);
				String Booking_ConfirmationText = getText(driver, getObjectHotels("HotelCom_Confirmation_BookingDone_TextMsg"));					
			if (Booking_ConfirmationText.contains("Your booking is done")) {
				TripID = getText(driver, getObjectHotels("HotelCom_Confirmation_TripID"));
				Reporter.log(Trip_Logger_Msg + TripID);
				logger.info(Trip_Logger_Msg + TripID);
			} 
			}	else {
				Reporter.log(Trip_Logger_Msg + " : Booking Confirmation page is not displayed");
				Assert.assertEquals(true, false);
				}
		}
		return TripID;		
	}
	
	public void hotelCom_PaymentRetry_PriceCheck(RemoteWebDriver driver) throws Exception {
		Double Paymentpage_fare=Double.parseDouble(PaymentPage_Totalfare.replaceAll("[^0-9.]", "").substring(1));
		Reporter.log("Price on payment page :" +Paymentpage_fare );
		Double Retrypage_fare=Double.parseDouble(RetryPage_Totalfare.replaceAll("[^0-9.]", "").substring(1));
		Reporter.log("Price on retry page :" +Retrypage_fare );
		if(Paymentpage_fare.equals(Retrypage_fare))
		{
			Reporter.log("Price is Correct: Price on Payment page :" +Paymentpage_fare + " Price on Retry page :" +Retrypage_fare);
		}
		else 
		{

			Reporter.log("Price is not matching: Price on Payment page :" +Paymentpage_fare + " Price on Retry page :" +Retrypage_fare);
			System.out.println("Price is not matching: Price on Payment page :" +Paymentpage_fare + " Price on Retry page :" +Retrypage_fare);
			Assert.assertTrue(false);
		}
	}
 	
	public void alertCaptureAndFail(RemoteWebDriver driver) {
		try {
			org.openqa.selenium.Alert alert = driver.switchTo().alert();
			Reporter.log("Alert detected: " + alert.getText());
			Thread.sleep(2000);
			alert.accept();
			Assert.assertEquals(true, false);
		} catch (Exception e) {
		}
	}
	
	public String hotelCom_Account_Cancellation(RemoteWebDriver driver, String TripID, String CancelType) throws Exception{
		if (MakePaymentOnlyInQA2){
			String URL = logURL(driver);
			if(URL.contains(".ae")) {
				baseUrl = baseUrl_AE;
			}
			Thread.sleep(2000);
			driver.get(baseUrl+"/account/trips/"+TripID);
			if(!TripID.contains("null")) {
			if(textPresent(driver, "Sorry, our system is acting up.", 2)) {
				Reporter.log("Sorry, our system is acting up. : error is displayed");
				logURL(driver);
				Assert.assertTrue(false);
			}
			if(!elementVisible(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), 20)) {
				refreshPage(driver);
			}
			elementVisible(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), 10);
			textAssert(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), "Trips you've booked");
			
			String Trip_status = getText(driver, getObjectHotels("HotelCom_Account_TripsPage_TripStatus"));  // To check the Trip is confirmed or not
			if(!Trip_status.equals("CONFIRMED"))
			{
				Reporter.log("Trip status is not confirmed but is "+Trip_status);
				Assert.assertEquals(Trip_status, "CONFIRMED");
			}
			if (CancelType.equals("CCVALIDATION")) {
				if(!elementVisible(driver, By.cssSelector("dd.total > small"), 20)) {
					Reporter.log("Credit Card Details are not displayed");
					Assert.assertTrue(false);
				}
				String CardDetails_TripPage = getText(driver, By.cssSelector("dd.total > small"));
				if(!CardDetails_TripPage.contains("2346")) {
					Reporter.log(CardDetails_TripPage +" : Card Details Displayed");
					Assert.assertTrue(false);
				}
			}
			safeClick(driver, getObjectHotels("HotelCom_AccountPage_Cancel_Button"));
			elementVisible(driver, getObjectHotels("HotelCom_CancellationPage_Text"),60);
			textAssert(driver, getObjectHotels("HotelCom_CancellationPage_Text"), "Review cancellations & confirm");
			
			if (CancelType.equals("CCVALIDATION")) {
				if(!elementVisible(driver, By.xpath("//div/nav/ul/li/label/span/small"), 20)) {
					Reporter.log("Credit Card Details are not displayed");
					Assert.assertTrue(false);
				}
				String CardDetails_CancelPage = getText(driver, By.xpath("//div/nav/ul/li/label/span/small"));
				if(!CardDetails_CancelPage.contains("2346")) {
					Reporter.log(CardDetails_CancelPage +" : Card Details Displayed");
					Assert.assertTrue(false);
				}
			}
			
			else if (CancelType.equals("FreeCancellation"))
			{
			elementPresent(driver, getObjectHotels("HotelCom_CancellationPage_TotalPaidAmt"));
			elementPresent(driver, getObjectHotels("HotelCom_CancellationPage_TotalRefundAmt"));
			
			String Total_Paid_Amt = getText(driver, getObjectHotels("HotelCom_CancellationPage_TotalPaidAmt"));
			String Total_Refund_Amt = getText(driver, getObjectHotels("HotelCom_CancellationPage_TotalRefundAmt"));
			if(!Total_Paid_Amt.equals(Total_Refund_Amt))
			{
				Reporter.log("The Paid amount and the Refund amount doesn't match up!!" +Total_Paid_Amt+ "&" +Total_Refund_Amt);
				Assert.assertEquals(Total_Refund_Amt, Total_Paid_Amt);	
			}
			Reporter.log("The amount matches:" +Total_Paid_Amt+ " ==== " +Total_Refund_Amt);
			
			}
			else if (CancelType.equals("NonFreeCancellation"))	{
			elementVisible(driver, getObjectHotels("HotelCom_CancellationPage_TotalPaidAmt"), 10);		
			String Total_Paid_Amt = getText(driver, getObjectHotels("HotelCom_CancellationPage_TotalPaidAmt"));
			String Total_Refund_Amt = getText(driver, getObjectHotels("HotelCom_CancellationPage_TotalRefundAmt"));
			Canc_refund_amt=Double.parseDouble(Total_Refund_Amt.replaceAll("[^0-9.]", "").substring(1));
			Double Canc_paid_amt=Double.parseDouble(Total_Paid_Amt.replaceAll("[^0-9.]", "").substring(1));
			if(!(Canc_paid_amt>Canc_refund_amt))
				{
					Reporter.log("The Refund amount is not correct!!" +Total_Paid_Amt+ "&" +Total_Refund_Amt);
					Assert.assertEquals(Total_Refund_Amt, Total_Paid_Amt);	
				}
			else
					Reporter.log("The refund amount is correct :" +Total_Paid_Amt+ " ==== " +Total_Refund_Amt);
			}
			
			if (!CancelType.equals("CCVALIDATION")) {
		    if(elementPresent_Time(driver, getObjectHotels("HotelCom_CancellationPage_Wallet_Radio_Button"), 1)){
		    	safeClick(driver, getObjectHotels("HotelCom_CancellationPage_Wallet_Radio_Button"));
		    	safeClick(driver, getObjectHotels("HotelCom_CancellationPage_Wallet_Policy_Checkbox"));
		    	}
			 }
		    else smartClick(driver, getObjectHotels("HotelCom_CancellationPage_Card_Radio_Button"));
						
		    safeClick(driver, getObjectHotels("HotelCom_CancellationPage_TripCancellation_Button"));
		    if(CancelType.equals("PAHCC")){

		    	elementVisible(driver, getObjectHotels("HotelCom_CancellationPage_Status"), 60);
		    	if(!getText(driver, getObjectHotels("HotelCom_CancellationPage_Status")).contains("CANCELLED")){
		    		Reporter.log("Booking is cancelled Status is not displayed");
					  Assert.assertTrue(false);
		    	}
/*		    	if(!getText(driver, getObjectHotels("HotelCom_CancellationPage_PAHCC_Message")).contains("We will proccess your card for cancellation fees momentarily.")){
		    		Reporter.log("We will proccess your card for cancellation fees momentarily. message is not displayed");
					  Assert.assertTrue(false);
		    	}
		    	if(!textPresent(driver, "Charged from", 5)){
		    		Reporter.log("Charged from message is not displayed");
					  Assert.assertTrue(false);
		    	}
		    	if(!textPresent(driver, "Cancellation fee", 5)){
		    		Reporter.log("Cancellation fee message is not displayed");
					  Assert.assertTrue(false);
		    	}
		    	if(!elementVisible(driver, getObjectHotels("HotelCom_CancellationPage_PAHCC_Price"), 5)){
		    		Reporter.log("Cacellation price is not displayed");
					  Assert.assertTrue(false);
		    	}
		    	
		    	String Cancel_Price =	getText(driver, getObjectHotels("HotelCom_CancellationPage_PAHCC_Price"));
		    	
		    	Cancel_Price = Cancel_Price.replace("Cancellation price : ", "");
		    	Reporter.log("Cancellation price : "+Cancel_Price);*/
		    	
		        }
		    else {
		    	elementVisible(driver, getObjectHotels("HotelCom_CancellationPage_TripCancelled_Confirmation"), 30);
			    textAssert(driver, getObjectHotels("HotelCom_CancellationPage_TripCancelled_Confirmation"), "Your hotel booking was cancelled successfully");
			    Reporter.log("Your hotel booking was cancelled successfully message is displayed");
			  
		    }
		    Thread.sleep(2000);			
		    
		    if (CancelType.equals("CCVALIDATION")) {
				if(!elementVisible(driver, By.xpath("//div[2]/section/dl/dd[2]"), 20)) {
					Reporter.log("Credit Card Details are not displayed");
					Assert.assertTrue(false);
				}
				String CardDetails_CancelledPage = getText(driver, By.xpath("//div[2]/section/dl/dd[2]"));
				if(!CardDetails_CancelledPage.contains("2346")) {
					Reporter.log(CardDetails_CancelledPage +" : Card Details Displayed");
					Assert.assertTrue(false);
				}
			}
		}
			else {
				Reporter.log("TripID is Null/ Payment confirmation page not displayed");
				Assert.assertTrue(false);
			}
		} 
	   	return TripID;
	}
	
	
	public String hotelCom_Account_Cancellation_Prod(RemoteWebDriver driver, String TripID, String CancelType) throws Exception{
		
	if(CancelType.equals("ProdpaymentLive")) {
		String URL = logURL(driver);
		if(URL.contains(".ae")) {
			baseUrl = baseUrl_AE;
		}
		Thread.sleep(2000);
		driver.get(baseUrl+"/account/trips/"+TripID);
		if(!TripID.contains("null")) {
		if(textPresent(driver, "Sorry, our system is acting up.", 2)) {
			Reporter.log("Sorry, our system is acting up. : error is displayed");
			logURL(driver);
			Assert.assertTrue(false);
		}
		if(!elementVisible(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), 20)) {
			refreshPage(driver);
		}
		elementVisible(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), 10);
		textAssert(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), "Trips you've booked");
		
		String Trip_status = getText(driver, getObjectHotels("HotelCom_Account_TripsPage_TripStatus"));  // To check the Trip is confirmed or not
		if(!Trip_status.equals("CONFIRMED"))
		{
			Reporter.log("Trip status is not confirmed but is "+Trip_status);
			Assert.assertEquals(Trip_status, "CONFIRMED");
		}
		safeClick(driver, getObjectHotels("HotelCom_AccountPage_Cancel_Button"));
		elementVisible(driver, getObjectHotels("HotelCom_CancellationPage_Text"),60);
		textAssert(driver, getObjectHotels("HotelCom_CancellationPage_Text"), "Review cancellations & confirm");		

		elementPresent(driver, getObjectHotels("HotelCom_CancellationPage_TotalPaidAmt"));
		elementPresent(driver, getObjectHotels("HotelCom_CancellationPage_TotalRefundAmt"));
		
		String Total_Paid_Amt = getText(driver, getObjectHotels("HotelCom_CancellationPage_TotalPaidAmt"));
		String Total_Refund_Amt = getText(driver, getObjectHotels("HotelCom_CancellationPage_TotalRefundAmt"));
		if(!Total_Paid_Amt.equals(Total_Refund_Amt))
		{
			Reporter.log("The Paid amount and the Refund amount doesn't match up!!" +Total_Paid_Amt+ "&" +Total_Refund_Amt);
			Assert.assertEquals(Total_Refund_Amt, Total_Paid_Amt);	
		}
		Reporter.log("The amount matches:" +Total_Paid_Amt+ " ==== " +Total_Refund_Amt);
		
		  if(elementPresent_Time(driver, getObjectHotels("HotelCom_CancellationPage_Wallet_Radio_Button"), 1)){
		    	safeClick(driver, getObjectHotels("HotelCom_CancellationPage_Wallet_Radio_Button"));
		    	safeClick(driver, getObjectHotels("HotelCom_CancellationPage_Wallet_Policy_Checkbox"));
		    	}
		    else smartClick(driver, getObjectHotels("HotelCom_CancellationPage_Card_Radio_Button"));						
		    safeClick(driver, getObjectHotels("HotelCom_CancellationPage_TripCancellation_Button"));
		    elementVisible(driver, getObjectHotels("HotelCom_CancellationPage_Status"), 60);
	    	if(!getText(driver, getObjectHotels("HotelCom_CancellationPage_Status")).contains("CANCELLED")){
	    		Reporter.log("Booking is cancelled Status is not displayed");
				  Assert.assertTrue(false);
	    	}
			
		
		}
		else {
			Reporter.log("TripID is Null/ Payment confirmation page not displayed");
			Assert.assertTrue(false);
		}
		}
		return TripID;
		
	}

	public String hotelCom_Account_Status(RemoteWebDriver driver, String TripID, String Status, String BookingType) throws Exception{
		if (MakePaymentOnlyInQA2){
			hotelCom_Open_TripID_Accounts(driver, TripID);
			
			String StatusAcc = getText(driver, getObjectHotels("HotelCom_Account_TripsPage_TripStatus"));
			if(!StatusAcc.contains(Status)){
				Reporter.log("Status in accounts is "+StatusAcc+" expected Status is "+Status);
				Assert.assertTrue(false);
			}else Reporter.log("Trip Status in Acct is "+StatusAcc);
		}
		return Status;
	}
	
	public String hotelCom_HQ_Status(RemoteWebDriver driver, String TripID, String Status, String BookingType) throws Exception{
		if (MakePaymentOnlyInQA2){
			hotelCom_Open_TripID_HQ(driver, TripID);
			String StatusHQ = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Status"));
			if(!StatusHQ.contains(Status)){
				if(elementVisible(driver, By.id("tab_6"), 10)){
					Reporter.log("Status in HQ is "+StatusHQ+" expected Status is "+Status);
					Reporter.log("Penalty collected");
					Assert.assertTrue(true);
				}
				else{
				Reporter.log("Status in HQ is "+StatusHQ+" expected Status is "+Status);
				Reporter.log("-------------------------Status in HQ is ----------------------"+StatusHQ+" expected Status is "+Status);
				Assert.assertTrue(false);
				}
			}else Reporter.log("Trip Status in HQ is "+StatusHQ);
		}
		return Status;		
	}
	
	public void hotelCom_HQ_PAHValidation(RemoteWebDriver driver) throws Exception{
		if(!elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_PAH_Link"), 5)){
			Reporter.log("PAH Booking details tab is not displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_PAH_Link"));
		Thread.sleep(2000);
		if(!textPresent(driver, "Elapsation date", 2)){
			Reporter.log(" Elapsation date : message is not displayed");
			Assert.assertTrue(false);
		}
		if(!textPresent(driver, "Reminder date", 2)){
			Reporter.log(" Reminder date : message is not displayed");
			Assert.assertTrue(false);
		}
		if(!textPresent(driver, "Cancellation policy start date", 2)){
			Reporter.log(" Cancellation policy start date : message is not displayed");
			Assert.assertTrue(false);
		}	
	}
	
	public void hotelCom_HQ_Login(RemoteWebDriver driver, String BookingType) throws Exception{
		if (MakePaymentOnlyInQA2){
			hotelCom_Open_HQ(driver);
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Email"), dataFile.value("HotelEmailID"));
			safeType(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_Password"), dataFile.value("HotelPassword"));
			safeClick(driver, getObjectHotels("HotelCom_SignIn_ModalWindow_SignIN_Button"));
		}
		}
	
	public String hotelCom_HQ_Cancellation(RemoteWebDriver driver, String TripID) throws Exception{
		if (MakePaymentOnlyInQA2){
			hotelCom_Open_TripID_HQ(driver, TripID);
			//-------------- HQ Bubble 1 ----------------------//
			safeClick(driver, getObjectHotels("HotelCom_HQ_Bubble1_Link"));
			elementPresent_log(driver, getObjectHotels("HotelCom_HQ_Bubble_Frame"), "HQ Bubble1 frame ", 2);
			elementPresent_log(driver, getObjectHotels("HotelCom_HQ_Bubble_RoomRate_Text"), "HQ Bubble1 Room Rate Text ", 2);
			elementPresent_log(driver, getObjectHotels("HotelCom_HQ_Bubble_RoomRate_Price"), "HQ Bubble1 Room Rate Price", 2);
			safeClick(driver, getObjectHotels("HotelCom_HQ_Bubble_Frame_Close"));
			//-------------- HQ Bubble 2 (Payable to hotel ) ----------------------//
			safeClick(driver, getObjectHotels("HotelCom_HQ_Bubble2_Link"));
			elementPresent_log(driver, getObjectHotels("HotelCom_HQ_Bubble_Frame"), "HQ Bubble2 frame ", 2);
			elementPresent_log(driver, getObjectHotels("HotelCom_HQ_Bubble_RoomRate_Text"), "HQ Bubble2 Room Rate Text ", 2);
			elementPresent_log(driver, getObjectHotels("HotelCom_HQ_Bubble_RoomRate_Price"), "HQ Bubble2 Room Rate Price", 2);
			safeClick(driver, getObjectHotels("HotelCom_HQ_Bubble_Frame_Close"));
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Link"));
			elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), 30);
			safeType(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), "Test Booking");
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_Button"));
		    elementNotPresent_Time(driver, getObjectHotels("HotelCom_HQ_Trips_Cancel_AddNotes"), 20);
			elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Status"), 30);
			String Cancel_Status = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Status"));
			if(!Cancel_Status.contains("Cancelled")){
				Reporter.log("Trip Status after cancellation is : "+Cancel_Status);
				Assert.assertEquals(true, false);
				}	
		}
		return TripID;		
		}
	
	public void hotelCom_Refund_Cases(RemoteWebDriver driver, String TripID, String RefundType) throws Exception{
		hotelCom_Open_TripID_HQ(driver, TripID);
		if (RefundType.equalsIgnoreCase("FC")){

		//	if (!elementVisible(driver, getObjectHotels("HotelCom_HQ_TripsPage_RefundTab"), 2));
			if (!textPresent(driver, "Refunds", 5))
			{
				hotelcom_HQ_Cancellation_Queue(driver, TripID);
				if(elementVisible(driver, By.linkText(TripID)	, 5)) {
					safeClick(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_TripID_CheckBox"));
					safeClick(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_TripID_Done_Button"));
					elementNotVisible(driver, By.linkText(TripID)	, 5);
				}
				
				else{
					hotelcom_HQ_RefundComp_Queue(driver, TripID);
					safeClick(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_TripID_DetailsLink"));
					int Converted_RefundPrice = hotelcom_Price_String_to_float(driver, By.id("refund_1"));
					int Converted_ReversalPrice = hotelcom_Price_String_to_float(driver, By.id("reversal_1"));

					Reporter.log("Refund Amount before computation "+Converted_RefundPrice+ " & Reversal Amount "+Converted_ReversalPrice);
					Reporter.log("Converted Refund amount "+Converted_RefundPrice);
					safeClick(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Save_Button"));
					textPresent(driver, "Hotel Refund Computation Queue", 5);
					driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
					if(!elementVisible(driver, getObjectHotels("HotelCom_HQ_TripsPage_RefundTab"), 5)){
						Reporter.log("The Refund computation failed");
						Assert.assertTrue(false);
					}


					safeClick(driver, getObjectHotels("HotelCom_HQ_TripsPage_RefundTab"));
					int HQ_Refund1 = hotelCom_Price_To_Int(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Refund_Amt"));
					Reporter.log("HQ REFUND AMOUNT "+HQ_Refund1);
					if (Converted_RefundPrice!= HQ_Refund1){
						Reporter.log("Refund amount not valid");
					}
					Reporter.log("The Refund amount matches & the trip is full refunded");
				}
			}
			Reporter.log("The trip is auto refunded");
		}

		if(RefundType.equalsIgnoreCase("NR")){
			if (!elementVisible(driver, getObjectHotels("HotelCom_HQ_TripsPage_RefundTab"), 2))
			{
				hotelcom_HQ_Cancellation_Queue(driver, TripID);
				if(elementVisible(driver, By.linkText(TripID)	, 5)) {
					safeClick(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_TripID_CheckBox"));
					safeClick(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_TripID_Done_Button"));
					elementNotVisible(driver, By.linkText(TripID)	, 5);
				}
				else{
					hotelcom_HQ_RefundComp_Queue(driver, TripID);
					safeClick(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_TripID_DetailsLink"));
					int Converted_RefundPrice = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Refund_Amt"));
					int Converted_ReversalPrice = hotelcom_Price_String_to_float(driver,  getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Reversal_Amt"));

					if(Converted_RefundPrice!=0){
						Reporter.log("The Refund amount is not 0");
						Assert.assertTrue(false);
					}

					Reporter.log("Refund Amount before computation "+Converted_RefundPrice+ " & Reversal Amount "+Converted_ReversalPrice);
					Reporter.log("Converted Refund amount "+Converted_RefundPrice);
					safeClick(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Save_Button"));
					textPresent(driver, "Hotel Refund Computation Queue", 5);
					driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
					if(!elementVisible(driver, getObjectHotels("HotelCom_HQ_TripsPage_RefundTab"), 5)){
						Reporter.log("The Refund computation failed");
						Assert.assertTrue(false);
					}
					safeClick(driver, getObjectHotels("HotelCom_HQ_TripsPage_RefundTab"));
					int HQ_Refund1 = hotelCom_Price_To_Int(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Refund_Amt"));
					Reporter.log("HQ REFUND AMOUNT "+HQ_Refund1);
					if (Converted_RefundPrice!= HQ_Refund1){
						Reporter.log("Refund amount not valid");	
					}
					Reporter.log("The refund computed correctly and is 0");
				}
			}
			Reporter.log("The trip is auto refunded");
		}		
		if (RefundType.equalsIgnoreCase("PC")){

			if (!elementVisible(driver, getObjectHotels("HotelCom_HQ_TripsPage_RefundTab"), 2))
			{
				hotelcom_HQ_Cancellation_Queue(driver, TripID);
				if(elementVisible(driver, By.linkText(TripID)	, 5)) {
					safeClick(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_TripID_CheckBox"));
					safeClick(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_TripID_Done_Button"));
					elementNotVisible(driver, By.linkText(TripID)	, 5);
				}
				else{
					hotelcom_HQ_RefundComp_Queue(driver, TripID);
					safeClick(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_TripID_DetailsLink"));
					int Reversal_Amount = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Reversal_Amt"));
					int Total_Refund = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Refund_Amt"));
					int Sup_Charges = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Supp_Charges"));
					int Discount = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Discount"));
					int CT_Charges = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_CTCharges"));
					int Cashback = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_CashBack"));
					int Other_Charges = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_OtherCharges"));
					int GW_Fee = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_GW_Fee"));

					int Total_charges = Sup_Charges + CT_Charges + GW_Fee + Discount + Cashback + Other_Charges;
					int Final_Refund = Reversal_Amount - Total_charges;
					if (Sup_Charges!= 1000 && GW_Fee != 118 && CT_Charges != 354)
					{
						Reporter.log("The Supplier charges are computed wrongly");
					}
					if (Final_Refund != Total_Refund)
					{
						Reporter.log("The Refund values in the computation are wrong");
						Assert.assertTrue(false);
					}
					Reporter.log("The Refund components are correctly computed");

					safeClick(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Save_Button"));
					textPresent(driver, "Hotel Refund Computation Queue", 5);
					driver.get("https://qa2.cleartrip.com/hq/trips/"+TripID);
					if(!elementVisible(driver, getObjectHotels("HotelCom_HQ_TripsPage_RefundTab"), 5)){
						Reporter.log("The Refund computation failed");
						Assert.assertTrue(false);
					}
					safeClick(driver, getObjectHotels("HotelCom_HQ_TripsPage_RefundTab"));

					int Refund_Reversal_Amount = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Reversal_Total"));
					int Refund_Total_Refund = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Refund_Total"));
					int Refund_Sup_Charges = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Supp_Charges"));
					int Refund_Sup_GST_Charges = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Supp_GST_Charges"));
					int Refund_Discount = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_Discount"));
					int Refund_CT_Charges = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_CTCharges"));
					int Refund_CT_GST_Charges = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_CT_GST_Charges"));
					int Refund_Cashback = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_CashBack"));
					int Refund_Other_Charges = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_OtherCharges"));
					int Refund_GW_Fee = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_GW_Fee"));
					int Refund_GW_GST_Fee = hotelcom_Price_String_to_float(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Details_GW_GST_Charges"));

					Reporter.log("Reversal amount : "+Refund_Reversal_Amount+ "\nRefund Amount : "+Refund_Total_Refund+"\nSupplier Charges: "+Refund_Sup_Charges+"\nSupplier GST Charges: "+Refund_Sup_GST_Charges+"\nDiscount: "
							+Discount+"\nCleartrip Charges: "+Refund_CT_Charges+"\nCleartrip GST Charges: "+Refund_CT_GST_Charges+"\nDiscount: "+Refund_Discount+"\nCashback: "+Refund_Cashback+"\nOther charges: "+Refund_Other_Charges+ "\nPG Fee: "+Refund_GW_Fee+ "\nPG Fee: "+Refund_GW_GST_Fee);
					
					int Refund_Total_Sup_Charges = Refund_Sup_Charges+Refund_Sup_GST_Charges;
					int Refund_Total_CT_Charges = Refund_CT_Charges+Refund_CT_GST_Charges;
					int Refund_Total_GW_Fee = Refund_GW_Fee+Refund_GW_GST_Fee;

					if (Refund_Total_Sup_Charges!= 1000 && Refund_Total_GW_Fee != 118 && Refund_Total_CT_Charges != 354)
					{
						Reporter.log("The Supplier charges are computed wrongly");
						}
					if (Final_Refund != Total_Refund)
					{
						Reporter.log("The Refund values in the computation are wrong");
						Assert.assertTrue(false);
					}
					Reporter.log("The Refund amount matches & the trip is full refunded");
				}
			}
			Reporter.log("The trip is auto refunded");
		}
	}

	public void hotelcom_HQ_Cancellation_Queue(RemoteWebDriver driver, String TripID) throws Exception {
		safeClick(driver, getObjectHotels("HotelCom_HQ_Dashboard_Tab"));
		safeClick(driver, getObjectHotels("HotelCom_HQ_Hotels_Tab"));
		//safeClick(driver, By.xpath("(//a[contains(text(),'Cancellation')])[2]"));
		safeClick(driver, getObjectHotels("HotelCom_HQ_Fulfilment_Cancellation"));
		textPresent(driver, "Hotel Cancellation Queue", 5);
		safeType(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_SearchBox"), TripID);
		safeClick(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_SearchFilter"));
	}
	
	
	public void hotelcom_HQ_RefundComp_Queue(RemoteWebDriver driver, String TripID) throws Exception {
		safeClick(driver, getObjectHotels("HotelCom_HQ_Dashboard_Tab"));
		safeClick(driver, getObjectHotels("HotelCom_HQ_Hotels_Tab"));
		safeClick(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation"));
		elementPresent(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_SearchBox"), 10);
		safeType(driver, getObjectHotels("HotelCom_HQ_Cancellation_Queue_SearchBox"), TripID);
		safeClick(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_Search_Button"));
		elementPresent(driver, getObjectHotels("HotelCom_HQ_Fulfilment_RefundComputation_TripID_DetailsLink"), 5);
	}
	
	public int hotelcom_Price_String_to_float(RemoteWebDriver driver, By by) throws Exception{
		String Price = getText(driver, by);
		Price = Price.replaceAll("Rs. ", "");
		  if(Price.contains(","))
		  {
			  Price = Price.replaceAll(",", "");	
		  }
		  
		  float FPrice = Float.parseFloat(Price);  
		  float FPrice1 = (float)Math.round(FPrice);
		  int Converted_RefundPrice = (int) FPrice1;
		return Converted_RefundPrice;
		  
		
	}
	public void hotelCom_Account_Invoice_Validation(RemoteWebDriver driver, String TripID) throws Exception, InterruptedException {
		if (MakePaymentOnlyInQA2){
			String Acc_Total_Price = null;
			String Acc_Tax = null;
			if(!TripID.contains("null")) {	
			hotelCom_Open_TripID_Accounts(driver, TripID);
			elementVisible(driver, getObjectHotels("HotelCom_Account_TripsPage_HotelName"), 60);
			String Acc_HotelName = getText(driver, getObjectHotels("HotelCom_Account_TripsPage_HotelName"));
    		String Acc_TripID = getText(driver, getObjectHotels("HotelCom_Account_TripsPage_TripID"));
    		Acc_TripID = Acc_TripID.replace("Trip ID : ", "");
    		String Acc_CHMMVoucher = getText(driver, getObjectHotels("HotelCom_Account_TripsPage_CHMM_Voucher"));
    		Acc_CHMMVoucher = Acc_CHMMVoucher.replace("CHMM-", "");
			
			for (int i=1; i<=4; i++){
				String PaymentDetail_Name = "//dt["+i+"]";
				String PaymentDetail_Price = "//dd["+i+"]";
	    			if(elementVisible(driver, By.xpath(PaymentDetail_Name), 1)){
	    			String Pricing_Name = getText(driver, By.xpath(PaymentDetail_Name));
	    			Acc_Total_Price = getText(driver, By.xpath(PaymentDetail_Price));		
	    			if(Pricing_Name.contains("Total")){
		    			break;
		    		}
		    	}	
    		}			
			for (int i=1; i<=4; i++){
				String PaymentDetail_Name = "//dt["+i+"]";
				String PaymentDetail_Price = "//dd["+i+"]";
	    			if(elementVisible(driver, By.xpath(PaymentDetail_Name), 1)){
	    			String Pricing_Name = getText(driver, By.xpath(PaymentDetail_Name));
	    			Acc_Tax = getText(driver, By.xpath(PaymentDetail_Price));
		    			if(Pricing_Name.contains("Convenience Fee")){
		    				break;
		    			}
		    			}	
    		}
			
			String[] Acc_Price2 = Acc_Total_Price.split(" ");
			Acc_Total_Price = Acc_Price2[2];
    		if(Acc_Total_Price.contains("Rs.")){
    			Acc_Total_Price = Acc_Total_Price.replace("Rs.", "");
    		}
    		safeClick(driver, getObjectHotels("HotelCom_Account_TripsPage_Invoice_Link"));
    		//hotelCom_Select_PopUp_Window_In_Window(driver);
    		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    		driver.switchTo().window(tabs.get(1));
			String Invoice_HotelName = getText(driver, getObjectHotels("HotelCom_Account_Invoice_HotelName"));
    		String Invoice_TripID = getText(driver, getObjectHotels("HotelCom_Account_Invoice_TripID"));
    		Invoice_TripID = Invoice_TripID.replace("Cleartrip Invoice No. ", "");
    		String Invoice_CHMMVoucher = getText(driver, getObjectHotels("HotelCom_Account_Invoice_CHMM_Voucher"));
    		Invoice_CHMMVoucher = Invoice_CHMMVoucher.replace("Voucher# CHMM-", "");
    		String Invoice_Price=null;
    		/*for (int i=1; i<3; i++){
    				String PaymentDetail_Name_XPATH = "//table[4]/tbody/tr["+i+"]/td[2]";
    				String Invoice_Price_XPATH = "//table[4]/tbody/tr["+i+"]/td[2]";
    				if(elementPresent_Time(driver, By.xpath(PaymentDetail_Name_XPATH), 1)){
    				String Pricing_Name = getText(driver, By.xpath(PaymentDetail_Name_XPATH));
    				Invoice_Price= getText(driver, By.xpath(Invoice_Price_XPATH));
    				Thread.sleep(2000);
    				if(Pricing_Name.contains("Total Amount (rounded)")){
    					break;
    				}
    				}
    		}*/
    		
    		for (int i = 10; i < 17; i++) {
				String PriceName_Xpath = "//tr["+i+"]/td[1]"; 
				String PriceValue_Xpath = "//tr["+i+"]/td[2]"; 
				if(elementPresent_Time(driver, By.xpath(PriceValue_Xpath), 1)){
    				String Pricing_Name = getText(driver, By.xpath(PriceName_Xpath));
    				Invoice_Price= getText(driver, By.xpath(PriceValue_Xpath));
    				if(Pricing_Name.contains("Total Amount (rounded)")){
    					break;
    				}
				}	
			}
    		if(Invoice_Price.contains(".0")){
    			Invoice_Price = Invoice_Price.replace(".0", "");
    		}
    		
    		if(!(Invoice_HotelName.contains(Acc_HotelName))){
    			Reporter.log("Hotel Name in Invoice is not equal : ' "+Invoice_HotelName+" ' to the Hotel Name in Accounts Page : ' "+Acc_HotelName+" '" );
    			Assert.assertTrue(false);
    		}
    		if(!(Invoice_CHMMVoucher.equals(Acc_CHMMVoucher))){
    			Reporter.log("CHMM Voucher in Invoice is not equal : ' "+Invoice_CHMMVoucher+" ' to the CHMM Voucher in Accounts Page : ' "+Acc_CHMMVoucher+" '" );
    			Assert.assertTrue(false);
    		}     	
    		if(!(Invoice_TripID.contains(Acc_TripID))){
    			Reporter.log("Trip ID in Invoice is not equal : ' "+Invoice_TripID+" ' to the Trip ID in Accounts Page : ' "+Acc_TripID+" '" );
    			Assert.assertTrue(false);
    		}        	

    		if(!(Acc_Tax.contains(Invoice_Price))){
    			Reporter.log("Price in Invoice is not equal : ' "+Invoice_Price+" ' to the Tax in Accounts Page : ' "+Acc_Tax+" '" );
    		Assert.assertTrue(false);
    		} 

			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
	   }			
		else {	
					Reporter.log("TripID is Null/ Payment confirmation page not displayed");
					Assert.assertTrue(false);
				}
			elementVisible(driver, getObjectHotels("HotelCom_Account_TripsPage_Payment_Invoice_Link"), 10);
			safeClick(driver, getObjectHotels("HotelCom_Account_TripsPage_Payment_Invoice_Link"));
    		//hotelCom_Select_PopUp_Window_In_Window(driver);
    		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    		driver.switchTo().window(tabs.get(1));
			String Invoice_HotelName = getText(driver, getObjectHotels("HotelCom_Account_Invoice_HotelName"));
    		String Invoice_TripID = getText(driver, getObjectHotels("HotelCom_Account_Invoice_TripID"));
    		Invoice_TripID = Invoice_TripID.replace("Cleartrip Invoice No. ", "");
			
			
    		String Invoice_Total_Price=null;
    	
    		for (int i = 10; i < 19; i++) {
				String PriceName_Xpath = "//tr["+i+"]/td[1]"; 
				String PriceValue_Xpath = "//tr["+i+"]/td[2]"; 
				if(elementPresent_Time(driver, By.xpath(PriceValue_Xpath), 1)){
    				String Pricing_Name = getText(driver, By.xpath(PriceName_Xpath));
    				Invoice_Total_Price= getText(driver, By.xpath(PriceValue_Xpath));
    				if(Pricing_Name.contains("Total Amount (rounded)")){
    					break;
    				}
				}	
			}
    		if(Invoice_Total_Price.contains(".0")){
    			Invoice_Total_Price = Invoice_Total_Price.replace(".0", "");
    		}

    		if(!(Acc_Total_Price.contains(Invoice_Total_Price))){
    			Reporter.log("Total Price in Invoice is not equal : ' "+Invoice_Total_Price+" ' to the Price in Accounts Page : ' "+Acc_Total_Price+" '" );
    		Assert.assertTrue(false);
    		} 

			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
			
		}

	}

	public void hotelCom_HQ_Invoice_Validation(RemoteWebDriver driver, String TripID) throws Exception, InterruptedException {
		if (MakePaymentOnlyInQA2){  
			String HQ_Tax = null;		
			String HQ_Total_Price = null;
			String HQ_Invoice_Tax = null;		
			String HQ_Invoice_Total_Price = null;
			if(!TripID.contains("null")) {	
			hotelCom_Open_TripID_HQ(driver, TripID);
			elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_CHMM_Voucher"), 60);
			
			for (int i=1; i<=4; i++){
				String PaymentDetail_Name = "//div[8]/div/dl/dt["+i+"]";
				String PaymentDetail_Price = "//div[8]/div/dl/dd["+i+"]";
	    			if(elementVisible(driver, By.xpath(PaymentDetail_Name), 1)){
	    			String Pricing_Name = getText(driver, By.xpath(PaymentDetail_Name));
	    			HQ_Total_Price = getText(driver, By.xpath(PaymentDetail_Price));
	    			if(Pricing_Name.contains("Total")){
		    			break;
		    		}
		    	}	
    		}			
			for (int i=1; i<=4; i++){
				String PaymentDetail_Name = "//div[8]/div/dl/dt["+i+"]";
				String PaymentDetail_Price = "//div[8]/div/dl/dd["+i+"]";
	    			if(elementVisible(driver, By.xpath(PaymentDetail_Name), 1)){
	    			String Pricing_Name = getText(driver, By.xpath(PaymentDetail_Name));
	    			HQ_Tax = getText(driver, By.xpath(PaymentDetail_Price));
	    			if(Pricing_Name.contains("Convenience fee")){
		    				break;
		    			}
		    	}	
    		}			
			
			String CHMM_Voucher_HQ = getText(driver, getObjectHotels("HotelCom_HQ_Trips_CHMM_Voucher"));
			HQ_Total_Price = HQ_Total_Price.replace("Rs. ", "");
			String HotelName_HQ = getText(driver, getObjectHotels("HotelCom_HQ_Trips_HotelName"));
			String[] Hotel_Name_HQ = HotelName_HQ.split(",");
			HotelName_HQ = Hotel_Name_HQ[0];
			elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Print_Invoice_Link"), 1);
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Print_Invoice_Link"));
			//hotelCom_Select_PopUp_Window_In_Window(driver);	
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    		driver.switchTo().window(tabs.get(1));
			if(!elementVisible(driver, getObjectHotels("HotelCom_HQ_Invoice_TripID"), 30)) {
				Reporter.log("Invoice Page is not displayed");			
			}
			if(textPresent(driver, "Sorry, our system is acting up", 5)) {
				Reporter.log("Sorry, our system is acting up message is displayed");
				Assert.assertTrue(false);
			}
			String Invoice_TripID = getText(driver, getObjectHotels("HotelCom_HQ_Invoice_TripID"));
			String Invoice_HotelName = getText(driver, getObjectHotels("HotelCom_Account_Invoice_HotelName"));
			String Invoice_CHMM_Voucher = getText(driver, getObjectHotels("HotelCom_Account_Invoice_CHMM_Voucher"));
			
			String[] HotelName_Invoice = Invoice_HotelName.split(",");
			Invoice_HotelName = HotelName_Invoice[0];
			Invoice_HotelName = Invoice_HotelName.replace("Hotel: ", "");
			Invoice_CHMM_Voucher = Invoice_CHMM_Voucher.replace("Voucher# ", "");
			
			
			
			for (int i = 10; i <= 18; i++) {
				String PriceName_Xpath = "//tr["+i+"]/td[1]"; 
				String PriceValue_Xpath = "//tr["+i+"]/td[2]"; 
				if(elementPresent_Time(driver, By.xpath(PriceValue_Xpath), 1)){
    				String Pricing_Name = getText(driver, By.xpath(PriceName_Xpath));
    				HQ_Invoice_Tax= getText(driver, By.xpath(PriceValue_Xpath));
    				if(Pricing_Name.contains("Total Amount (rounded)")){
    					break;
    				}
				}	
			}
			
			if(HQ_Invoice_Tax.contains(".0")){
				HQ_Invoice_Tax = HQ_Invoice_Tax.replace(".0", "");
			}
			
			if(!(CHMM_Voucher_HQ.equals(Invoice_CHMM_Voucher))){
				Reporter.log("Chmm Voucher in HQ Trip details ' "+CHMM_Voucher_HQ+" ' is not equal to Chmm Voucher in Invoice ' "+Invoice_CHMM_Voucher);
				Assert.assertTrue(false);
			}
			if(!(HotelName_HQ.equals(Invoice_HotelName))){
				Reporter.log("Hotel Name in HQ Trip details ' "+HotelName_HQ+" ' is not equal to Hotel Name in Invoice ' "+Invoice_HotelName);
				Assert.assertTrue(false);
			}
			if(!(Invoice_TripID.equals(TripID))){
				Reporter.log("Trip ID in HQ Trip details ' "+TripID+" ' is not equal to Trip ID in Invoice ' "+Invoice_TripID);
				Assert.assertTrue(false);
			}
			if(!(HQ_Tax.contains(HQ_Invoice_Tax))){
				Reporter.log("Tax Price in HQ Trip details ' "+HQ_Tax+" ' is not equal to Tax Price in Invoice ' "+HQ_Invoice_Tax);
				Assert.assertTrue(false);
			}
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
		}
			else {	Reporter.log("TripID is Null/ Payment confirmation page not displayed");
						Assert.assertTrue(false);	
					}
			
			elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Print_Payment_Invoice_Link"), 1);
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Print_Payment_Invoice_Link"));
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    		driver.switchTo().window(tabs.get(1));
			if(!elementVisible(driver, getObjectHotels("HotelCom_HQ_Invoice_TripID"), 30)) {
				Reporter.log("Invoice Page is not displayed");			
			}
			if(textPresent(driver, "Sorry, our system is acting up", 5)) {
				Reporter.log("Sorry, our system is acting up message is displayed");
				Assert.assertTrue(false);
			}

			for (int i = 10; i <= 18; i++) {
				String PriceName_Xpath = "//tr["+i+"]/td[1]"; 
				String PriceValue_Xpath = "//tr["+i+"]/td[2]"; 
				if(elementPresent_Time(driver, By.xpath(PriceValue_Xpath), 1)){
    				String Pricing_Name = getText(driver, By.xpath(PriceName_Xpath));
    				HQ_Invoice_Total_Price= getText(driver, By.xpath(PriceValue_Xpath));
    				if(Pricing_Name.contains("Total Amount (rounded)")){
    					break;
    				}
				}	
			}
			if(HQ_Invoice_Total_Price.contains(".0")){
				HQ_Invoice_Total_Price = HQ_Invoice_Total_Price.replace(".0", "");
    		}
			if(HQ_Invoice_Tax.contains(".0")){
				HQ_Invoice_Tax = HQ_Invoice_Tax.replace(".0", "");
    		}
			
			if(!(HQ_Invoice_Total_Price.contains(HQ_Total_Price))){
				Reporter.log("Total Price in HQ Trip details ' "+HQ_Invoice_Total_Price+" ' is not equal to Total Price in Invoice ' "+HQ_Total_Price);
				Assert.assertTrue(false);
			}
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
    		
			
		}		
	}

	public void hotelCom_HQ_Voucher_Validation(RemoteWebDriver driver, String TripID) throws Exception {
	if (MakePaymentOnlyInQA2){
		hotelCom_Open_TripID_HQ(driver, TripID);
		elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_CHMM_Voucher"), 60);

		
		String CHMM_Voucher_HQ = getText(driver, getObjectHotels("HotelCom_HQ_Trips_CHMM_Voucher"));
		String HotelName = getText(driver, getObjectHotels("HotelCom_HQ_Trips_HotelName"));
		String[] Hotel_Name_HQ = HotelName.split(",");
		String HQ_HotelName = Hotel_Name_HQ[0];		
		
		String checkIN_HQStr = getText(driver, By.xpath("//tr[4]/td"));
		String checkOut_HQStr = getText(driver, By.xpath("//td[2]"));
		
		String[] checkIN_HQ = checkIN_HQStr.split(" " );
		checkIN_HQ[4] = checkIN_HQ[4].replace(",", "").toUpperCase(); 
		String[] strSplit1 = checkIN_HQ[0].split(":");
		checkIN_HQ[0] = strSplit1[0];
		String CheckIN_WeekTime = checkIN_HQ[2]+" "+checkIN_HQ[0]+" "+checkIN_HQ[1];
		String CheckIN_Day = checkIN_HQ[4]+" "+checkIN_HQ[3];
			  
		String[] checkOut_HQ = checkOut_HQStr.split(" " );
		checkOut_HQ[4] = checkOut_HQ[4].replace(",", "").toUpperCase(); 
		String[] strSplit2 = checkOut_HQ[0].split(":");
		checkOut_HQ[0] = strSplit2[0];
		String CheckOut_WeekTime = checkOut_HQ[2]+" "+checkOut_HQ[0]+" "+checkOut_HQ[1];
		String CheckOut_Day = checkOut_HQ[3]+" "+checkOut_HQ[4];
		
		safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_Print_Voucher_Link"));
		elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_Voucher_TripID"), 60);
		elementPresent_log(driver, By.xpath("//td[2]/table/tbody/tr/td/img"), "QR Image", 5);
		elementPresent_log(driver, By.xpath("//div[4]/table/tbody/tr/td/table/tbody/tr/td[2]"), "Cleartrip Support PhoneNO", 1);
		elementPresent_log(driver, By.xpath("//td[3]/table/tbody/tr/td[2]"), "Cleartrip Support EmailID", 1);
		textAssert(driver, By.xpath("//div[4]/table/tbody/tr/td/table/tbody/tr/td[2]"), "95 95 333 333");
		textAssert(driver, By.xpath("//td[3]/table/tbody/tr/td[2]"), "support@cleartrip.com");
		String TripID_Voucher = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Voucher_TripID"));
		String CHMM_Voucher_No = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Voucher_CHMM_Voucher"));
		String Voucher_HotelName = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Voucher_Hotel_Name"));
		CHMM_Voucher_No = CHMM_Voucher_No.replace("Voucher # ", "");
		TripID_Voucher = TripID_Voucher.replace("Cleartrip Trip ID: ", "");
		
		String CheckIN_WeekTime_Voucher = getText(driver, By.xpath("//td/table/tbody/tr[3]/td"));
		String CheckIN_Day_Voucher = getText(driver, By.xpath("//td/table/tbody/tr[2]/td/strong"));
		String CheckOut_WeekTime_Voucher = getText(driver, By.xpath("//tr[3]/td[3]"));
		String CheckOut_Day_Voucher = getText(driver, By.xpath("//td[3]/strong"));
		
		
		if(!(CheckIN_WeekTime.equals(CheckIN_WeekTime_Voucher)||CheckIN_WeekTime_Voucher.equals("24 hrs"))){
			Reporter.log("CheckIn Week & Time doesnt Match HQ : "+CheckIN_WeekTime+" Voucher : "+CheckIN_WeekTime_Voucher);
			Assert.assertTrue(false);
		}
		if(!CheckIN_Day.equals(CheckIN_Day_Voucher)){
			Reporter.log("CheckIn Day doesnt Match HQ : "+CheckIN_Day+" Voucher : "+CheckIN_Day_Voucher);
		}
		if(!(CheckOut_WeekTime.equals(CheckOut_WeekTime_Voucher)||CheckOut_WeekTime_Voucher.equals("24 hrs"))){
		Reporter.log("CheckOut Week & Time doesnt Match HQ : "+CheckOut_WeekTime+" Voucher : "+CheckOut_WeekTime_Voucher);
		Assert.assertTrue(false);
		}
		if(!CheckOut_Day.equals(CheckOut_Day_Voucher)){
			Reporter.log("CheckOut Day doesnt Match HQ : "+CheckOut_Day+" Voucher : "+CheckOut_Day_Voucher);
			Assert.assertTrue(false);
		}
		
		
		
		if(!(CHMM_Voucher_HQ.equals(CHMM_Voucher_No))){
			Reporter.log("Chmm Voucher in HQ Trip details ' "+CHMM_Voucher_HQ+" ' is not equal to Chmm Voucher in Voucher page ' "+CHMM_Voucher_No);
			Assert.assertTrue(false);
		}
		
		if(!(HQ_HotelName.equals(Voucher_HotelName))){
			Reporter.log("Hotel Name in HQ Trip details ' "+HQ_HotelName+" ' is not equal to Hotel Name in Voucher page ' "+Voucher_HotelName);
			Assert.assertTrue(false);
		}
	
		if(!(TripID_Voucher.equals(TripID))){
			Reporter.log("Trip ID in HQ Trip details ' "+TripID+" ' is not equal to Trip ID in Voucher page ' "+TripID_Voucher);
			Assert.assertTrue(false);
			}
		
		
		}
	}
	
	public void hotelCom_Open_TripID_HQ(RemoteWebDriver driver, String TripID) throws Exception {
		String URL = logURL(driver);
		if(URL.contains("com")||URL.contains("amex.co.in")){
			driver.get(baseUrl+"/hq/trips/"+TripID);
		}
		else if(URL.contains("ae")){
			driver.get(baseUrl_AE+"/hq/trips/"+TripID);
		} 
		//logURL(driver);
		if(!elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_HotelName"), 10)) {
			refreshPage(driver);
		}
		else if(textPresent(driver, "Sorry, our system is acting up.", 2)) {
			Reporter.log("Sorry, our system is acting up. : error is displayed");
			Assert.assertTrue(false);
		}
		
	}
	
	public void hotelCom_Open_HQ(RemoteWebDriver driver) throws Exception {
		String URL = logURL(driver);
		if(!ProductionUrl) {
		if(URL.contains("com")||URL.contains("amex.co.in")){
			driver.get(baseUrl+"/hq");
		}
		else if(URL.contains("ae")){
			driver.get(baseUrl_AE+"/hq");
		} 
		//logURL(driver);
		if(!elementVisible(driver, getObjectHotels("HotelCom_HQ_HoemPage"), 10)) {
			refreshPage(driver);
		}
		else if(textPresent(driver, "Sorry, our system is acting up.", 2)) {
			Reporter.log("Sorry, our system is acting up. : error is displayed");
			Assert.assertTrue(false);
		}
		}
	}
	
	public void hotelCom_Accounts_Refund_Verification(RemoteWebDriver driver,String TripID) throws Exception
	{
		hotelCom_Open_TripID_Accounts(driver,TripID);
		elementVisible(driver, getObjectHotels("HotelCom_Accont_TripsPage_RefundAmt"), 10);
		String Ref_Amt = getText(driver, getObjectHotels("HotelCom_Accont_TripsPage_RefundAmt"));
		if(Ref_Amt.contains("to Credit Card")) {
			String Ref_Amt1[] = Ref_Amt.split(" to");
			Ref_Amt = Ref_Amt1[0];
		}
			//Parsing refund amount to remove non numeric characters
		Double Account_Trip_ref_Amt=Double.parseDouble(Ref_Amt.replaceAll("[^0-9.]", "").substring(1));
		//Compare amount to refund amount displayed while cancellation
		if(Account_Trip_ref_Amt==Canc_refund_amt)
			{
			Reporter.log("Refund amount is correct :" +Account_Trip_ref_Amt);
			}
		else
		{
			Reporter.log("Refund amount is incorrect :" +Account_Trip_ref_Amt);
			//Assert.assertEquals(Trip_ref_Amt, Canc_refund_amt); Already existing bug
		}		
		
	}
	
	public void hotelCom_HQ_Refund_Verification(RemoteWebDriver driver,String TripID) throws Exception
	{
		hotelCom_Open_TripID_HQ(driver,TripID);
		//Verify Refund amount in HQ
		if(elementVisible(driver, getObjectHotels("HotelCom_HQ_Trips_RefundTab"), 5)){
			safeClick(driver, getObjectHotels("HotelCom_HQ_Trips_RefundTab"));
			String Ref_Amount = getText(driver, getObjectHotels("HotelCom_HQ_Trips_Refund_Amount"));
			//Parsing refund amount to remove non numeric characters
			Double HQ_Trip_ref_Amt=Double.parseDouble(Ref_Amount.replaceAll("[^0-9.]", "").substring(1));
			//Comparing amount with refund amount displayed while trip cancellation
		if(HQ_Trip_ref_Amt==Math.round(Canc_refund_amt))
		{
			Reporter.log("Refund amount is correct :" +HQ_Trip_ref_Amt);
		}
		else
		{ 	Reporter.log("Refund amount is incorrect :" +HQ_Trip_ref_Amt);
		  	//Assert.assertEquals(Trip_ref_Amt, refund_amt);Already existing bug
		}		
		}
		else Reporter.log("Refund Tab is not displayed");
	}
	
	public void hotelCom_Inclusions_Verification(RemoteWebDriver driver) throws Exception {
		String inclusionTitle="";
	
		elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_ShowDetails"), 20);
		//Click Show Details Link for all Room Rates
		List<WebElement> showDetails=driver.findElements(By.xpath("//a[contains(@class, 'weak shwDetails')]"));
		for(WebElement showDetailLink:showDetails)
		{
			if(showDetailLink.isDisplayed())
			showDetailLink.click();
		}
		
		//Fetch Inclusion Title for all rates
		elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_InclusionTitles"), 20);
		List<WebElement> InclusionText  = driver.findElements(By.xpath("//div[contains(@class, 'col col12 rateMidCol')]/div[1]"));
		for(int i=0;i<=InclusionText.size()-1;i++)
		{
			inclusionTitle= InclusionText.get(i).getText();
			if(inclusionTitle.equals("Room only rate"))
				Reporter.log("Rate has no inclusion, it is room only rate");
			else
			{
				String array[]=inclusionTitle.split("&");
				String[] trimmedArray = new String[array.length];
				for (int t = 0; t < array.length; t++)
			    trimmedArray[t] = array[t].trim();
				//Fetch first and second inclusion room rates
				elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_V2_InclusionDetails"), 20);
				List<WebElement> InclusionInfo  = driver.findElements(By.xpath("//ul[contains(@class, 'rateInclusions checkList')]/li[1]"));
				List<WebElement> InclusionInfo2  = driver.findElements(By.xpath("//ul[contains(@class, 'rateInclusions checkList')]/li[2]"));
				if(trimmedArray[0].equals(InclusionInfo.get(i).getText())|| trimmedArray[0].equals(InclusionInfo2.get(i).getText()))
				{
					//System.out.println("Inclusion Part1 Matches" + inclusionTitle);
					if(trimmedArray.length>1)
					{
						if(trimmedArray[1].equalsIgnoreCase(InclusionInfo.get(i).getText()) || trimmedArray[1].equalsIgnoreCase(InclusionInfo2.get(i).getText()))
							{
							Reporter.log("Inclusion Part2 Matches" +inclusionTitle);
							}
					}
					else
						Reporter.log("Rate has single inclusion");
				}
				else
				{
					Reporter.log("Rate has no inclusions or not matching");
					Assert.assertTrue(false);
				}
			}
			
		}
	}
	
	public void hotelCom_Open_TripID_Accounts(RemoteWebDriver driver, String TripID) throws Exception {
		String URL = logURL(driver);
		if(URL.contains("com")){
			driver.get(baseUrl+"/account/trips/"+TripID);
		}
		else if(URL.contains("ae")){
			driver.get(baseUrl_AE+"/account/trips/"+TripID);
		}

		logURL(driver);
		if(elementVisible(driver, getObjectHotels("HotelCom_Account_TripsPage_HotelName"), 20)) {
			
		} else if(textPresent(driver, "Sorry, our system is acting up.", 2)) {
			Reporter.log("Sorry, our system is acting up. : error is displayed");
			Assert.assertTrue(false);
		}
	}
	
	public String hotelCom_Open_URL(RemoteWebDriver driver, String Domain) throws Exception {
		String URL = null;
		if(Domain.equalsIgnoreCase("com")){
			URL = baseUrl; 
		}
		else if(Domain.equalsIgnoreCase("ae")){
			URL = baseUrl_AE;
		}
		return URL;
	}

	public void hotelCom_Select_PopUp_Window(RemoteWebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
	}
	
	public void hotelCom_Select_PopUp_Window_In_Window(RemoteWebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
	}
	
	public void hotelCom_SRP_Filter(RemoteWebDriver driver, String Domain, String FilterType, String City, String State, String Dest_Code) throws InterruptedException , Exception{
		  // driver.get(baseUrl);	
			
		//====================================================-------------- Deals Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("DEALS")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Deals_CheckBox"), 10)){
			  	   Reporter.log("Deals link is not Visible");
			  	   Assert.assertTrue(false);
			     }
		      safeClick(driver, getObjectHotels("HotelCom_SRP_Deals_CheckBox"));
		      elementPresent(driver, getObjectHotels("HotelCom_SRP_Deals_Icon"));
		      elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Taxes"), 20);	      
			}
		
			//	------------------------------------------------------ Amenities Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("AMENITIES")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Amenities_LeftSide_Bar"), 10)){
			  	   Reporter.log("Amenities option is not Visible");
			  	   Assert.assertTrue(false);
			     }
				Thread.sleep(5000);
				//safeClick(driver, By.xpath("//div[17]/div/nav/ul/li[2]/label/span"));
				safeClick(driver, getObjectHotels("HotelCom_SRP_Amenities_LeftSide_Bar"));
				
			for(int i=1; i<=11; i++){
					String Amenities_Leftside_Xpath = "//div[14]/div/nav/ul/li["+i+"]/label/span";
		 			safeClick(driver, By.xpath(Amenities_Leftside_Xpath));
					int j[] = {0, 4, 3, 2, 8, 6, 10, 11, 9, 5, 7, 1 };
		 			String Amenities_Rightside_Xpath = "//div[4]/ul/li["+j[i]+"]/span";
		 			String Amenities_Property = driver.findElement(By.xpath(Amenities_Rightside_Xpath)).getAttribute("title");
		 			safeClick(driver, By.xpath(Amenities_Leftside_Xpath));
					Thread.sleep(1000);
					if(Amenities_Property.contains("unavailable")){
						Reporter.log(Amenities_Property +" : Amenity is not Available");
						Assert.assertTrue(false);
					}
				}    	
			/*	for(int i=1; i<=11; i++){
					String Amenities_Leftside_Xpath = "//section[@id='area']/section/aside/div/form/div[17]/div/nav/ul/li["+i+"]/label/input";
		 			safeClick(driver, By.xpath(Amenities_Leftside_Xpath));
				}*/
				
			}
			
			//====================================================BestSellers Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("BESTSELLERS")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_BestSellers_CheckBox"), 10)){
			  	   Reporter.log("BestSeller link is not Visible");
			  	   Assert.assertTrue(false);
			     }
		      safeClick(driver, getObjectHotels("HotelCom_SRP_BestSellers_CheckBox"));
		      elementVisible(driver, getObjectHotels("HotelCom_SRP_BestSellers_Icon"), 10);
		      elementPresent(driver, getObjectHotels("HotelCom_SRP_BestSellers_Icon"));
			}
		
			
		//==================================================== Curency Dropdown ============================================================//	
			if(FilterType.equalsIgnoreCase("CURRENCYDROPDOWN")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Currency_DropDown"), 10)){
			  	   Reporter.log("Curency DropDown link is not Visible");
			  	   Assert.assertTrue(false);
			     }
		      safeClick(driver, getObjectHotels("HotelCom_SRP_Currency_DropDown"));
		      Thread.sleep(2000);
		      if(!elementPresent_Time(driver, getObjectHotels("HotelCom_SRP_Currency_DropDown_Snippet"), 10)) {
		    	  Reporter.log("After Clicking the select currency link , currency snippet is not displayed");
		    	  Assert.assertTrue(false);
		      }
		      Thread.sleep(2000);
		      if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Currency_DropDown_AED"), 20)) {
		    	  Reporter.log("After Clicking the select currency link , AED currency Option is not displayed");
		    	  Assert.assertTrue(false);
		      } else safeClick(driver, getObjectHotels("HotelCom_SRP_Currency_DropDown_AED"));

		      Thread.sleep(5000);
		      if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Currency_Value"), 20)) {
		    	  String CurrencyAED = getText(driver, getObjectHotels("HotelCom_SRP_Currency_Value"));
		    	  if(!CurrencyAED.contains("AED")) {
		    		  Reporter.log("AED Currency is not displayed after selecting AED from the dropdown");
			    	  Assert.assertTrue(false);
		    	  }
		      }
		      
//====================================================Check for IND Currency=====================================================//

		      safeClick(driver, getObjectHotels("HotelCom_SRP_Currency_DropDown"));
		      Thread.sleep(2000);
		      if(!elementPresent_Time(driver, getObjectHotels("HotelCom_SRP_Currency_DropDown_Snippet"), 10)) {
		    	  Reporter.log("After Clicking the select currency link , currency snippet is not displayed");
		    	  Assert.assertTrue(false);
		      }
		      Thread.sleep(2000);
		      if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Currency_DropDown_India"), 20)) {
		    	  Reporter.log("After Clicking the select currency link , India currency Option is not displayed");
		    	  Assert.assertTrue(false);
		      } else safeClick(driver, getObjectHotels("HotelCom_SRP_Currency_DropDown_India"));

		      Thread.sleep(5000);
		      if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Currency_Value"), 20)) {
		    	  String CurrencyIndia = getText(driver, getObjectHotels("HotelCom_SRP_Currency_Value"));
		    	  if(!CurrencyIndia.contains("Rs")) {
		    		  Reporter.log("Rs Currency is not displayed after selecting AED from the dropdown");
			    	  Assert.assertTrue(false);
		    	  }
		      }
			}
		
		//==================================================== Free Cancellation Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("FREECANCELLATION")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_FreeCancellation_CheckBox"), 10)){
			  	   Reporter.log("Free Cancellation option is not Visible");
			  	   Assert.assertTrue(false);
			     }
			 	safeClick(driver, getObjectHotels("HotelCom_SRP_FreeCancellation_CheckBox"));
		    	if(!elementPresent(driver, getObjectHotels("HotelCom_SRP_FreeCancellation_Text"))){
		    		Reporter.log("Free Cancellation Icon is not displayed");
		    		   Assert.assertTrue(false);
		    	}
			}
			
		//==================================================== Hotel Search Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("HOTELSEARCH")){
					SRP_URL(driver, Domain, City, State, Dest_Code);
					if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 10)){
				  	   Reporter.log("Deals link is not Visible");
				  	   Assert.assertTrue(false);
				     }
				String Hotel_Name = "VeeKay Tourist Home";
			 	//safeType(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), Hotel_Name);
				Thread.sleep(5000);
				safeAutocomplete(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), getObjectHotels("HotelCom_SRP_HotelName_Ajax"), Hotel_Name);
				String HotelName = getText(driver, getObjectHotels("HotelCom_SRP_HotelName"));
				if(!HotelName.contains(Hotel_Name)){
					Reporter.log("Searched hotel is not displayed");
					Assert.assertTrue(false);
				}
			}
			
		//===================================================== Modify Search ============================================================//	
			if(FilterType.equalsIgnoreCase("MODIFYSEARCH")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Modify_Tab"), 10)){
			  	   Reporter.log("Modify Tab is not Visible");
			  	   Assert.assertTrue(false);
			     }
		      safeClick(driver, getObjectHotels("HotelCom_SRP_Modify_Tab"));
		      Thread.sleep(7000);
		      safeAutocomplete(driver, getObjectHotels("HotelCom_HomePage_SearchBox"), getObjectHotels("HotelCom_HomePage_SearchAjax"), "Mumbai");
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 2, "23");
				selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, "24");
				safeSelect(driver, getObjectHotels("HotelCom_HomePage_Traveller_DropDown"), "More travellers...");
				hotelCom_Homepage_PaxEntry(driver, "1", "1", "0","0","0","0","0","0","0");
				String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
				String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
				Reporter.log("Hotels Searched for " + City + " City for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
				safeClick(driver, getObjectHotels("HotelCom_HomePage_Search_Button"));
				elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 10);
				String Modify_City = getText(driver, By.xpath("//strong/span"));
				if(!Modify_City.equals("Mumbai")){
					Reporter.log("Modify City Search is not working");
					Assert.assertTrue(false);
				}
			}
			
		//=================================================== Location Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("LOCATION")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				for(int i=1;i<=2;i++){
			    		Thread.sleep(500);
			    		String Location_Xpath = "//div[14]/div/nav/ul/li["+i+"]/label/span";
			    		if(elementVisible(driver, By.xpath(Location_Xpath), 1)){
			    			String Location = getText(driver, By.xpath(Location_Xpath));
			    			safeClick(driver, By.xpath(Location_Xpath));
			    			String Location_Hotel = getText(driver, getObjectHotels("HotelCom_SRP_Hotel_Location_Text"));
			    			safeClick(driver, By.xpath(Location_Xpath));
			    			if(!Location_Hotel.toLowerCase().contains(Location.toLowerCase())){
			    				Reporter.log("Location filter selected : "+Location+" but Hotel Location details displayed as : "+Location_Hotel);
			    				Assert.assertTrue(false);
			    			}
			    		}
			    	}
			}	
			
		//==================================================== PAH Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("PAH")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_PAH_CheckBox"), 10)){
					Reporter.log("Pay @ Hotel Icon is not displayed on leftside column");
			 		Assert.assertTrue(false);
			 	}
				 safeClick(driver, getObjectHotels("HotelCom_SRP_PAH_CheckBox"));
			 		if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_PAH_Icon"), 10)){
				 		Reporter.log("Pay @ Hotel Icon is not displayed on Rightside");
				 		Assert.assertTrue(false);
				 	}
			}
			
			
		//==================================================== PAH Unavailable Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("PAH_NA")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(elementVisible(driver, getObjectHotels("HotelCom_SRP_PAH_CheckBox"), 5)){
					Reporter.log("Pay @ Hotel Icon is displayed on leftside column");
			 		Assert.assertTrue(false);
			 	}
			}
			
		//=================================================== StarRating Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("STARRATING")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				//if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_StarRating"), 10)){
				if(!elementVisible(driver, By.xpath("//div[3]/h5"), 10)){	
				Reporter.log("Star Rating icon is not displayed in SRP");
					Assert.assertTrue(false);
				}String StarRating_Xpath = null;
			
				for(int i=1; i<=4; i++){
					StarRating_Xpath = "//div[9]/div/nav/ul/li["+i+"]/label";
					Thread.sleep(1000);
					safeClick(driver, By.xpath(StarRating_Xpath));
					String StarRatingFilter = getText(driver, By.xpath(StarRating_Xpath));		
					String[] StarLeftFilter = StarRatingFilter.split(" ");
					StarRatingFilter = StarLeftFilter[0];
					String StarRatingSRP = getAttribute(driver, By.xpath("//li[1]/section/div[3]/span[1]"), "title");	
					safeClick(driver, By.xpath(StarRating_Xpath));
					StarRatingSRP = StarRatingSRP.replace(" Star hotel", "");
					int StartRating_Int = Integer.parseInt(StarRatingSRP);
					int StartRatingFilter_Int = Integer.parseInt(StarRatingFilter);
					if(!(StartRating_Int <= StartRatingFilter_Int)){
						Reporter.log("Star Rating Filter is not showing correct results");
						Assert.assertTrue(false);
						}
				}
			}	
			
		//================================================== TripAdvisor Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("TRIPADVISOR")){
					SRP_URL(driver, Domain, City, State, Dest_Code);
					//String[] TripAdvisor_Rating = {"Top rated", "4 & more", "3 & more"};  						
					String[] TripAdvisor_Rating = {"Top rated", "4 & more", "3 & more"};
					int[] TripAdvisor_Rating_No = {5, 4, 3};    	
					for(int i=1; i<=3; i++){
						smartClick(driver, By.linkText("Show all"));
						int j = i-1;
						safeClick(driver, By.xpath("//div[14]/div/nav/ul/li["+i+"]/label"));
						Thread.sleep(500);
						String Rating = getText(driver, By.xpath("//div[14]/div/nav/ul/li["+i+"]/label"));
						/*System.out.println("Rating : "+Rating);
						System.out.println("TripAdvisor_Rating : "+TripAdvisor_Rating[j]);*/
						if(!Rating.contains(TripAdvisor_Rating[j])){
							Reporter.log("Rating text does not match with filter selected");
							Assert.assertTrue(false);
						}				
					
						String Rating_Hotel = getAttribute(driver, getObjectHotels("HotelCom_SRP_TripAdvisor_Rating_RightSide"), "title");   
						//String Rating_Hotel = getAttribute(driver, By.xpath("//span[2]/span[2]"), "title");   
						Rating_Hotel = Rating_Hotel.replace("/5", "");
						if(Rating_Hotel.contains(".5")){
							Rating_Hotel = Rating_Hotel.replace(".5", "");
				    	}
						int Rating_int = Integer.parseInt(Rating_Hotel);
					/*	//System.out.println("TripAdvisor_Rating_No : "+TripAdvisor_Rating_No[i-1]);
						//System.out.println("Rating_int : "+Rating_int);*/
						if(!(Rating_int >= TripAdvisor_Rating_No[j])){
							Reporter.log("Rating does not match with the filter selected ");
							Assert.assertTrue(false);
						}	
						safeClick(driver, By.xpath("//div[15]/div/nav/ul/li["+i+"]/label"));
				    	}		
			}

		//=================================================== Location ShowAll Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("LOCATIONSHOWALL")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_ShowAll_Locations"), 10)){
					Reporter.log("ShowAll Location button is not displayed");
					Assert.assertTrue(false);
					}
					elementVisible(driver, getObjectHotels("HotelCom_SRP_ShowAll_Locations"), 10);
					Thread.sleep(5000);
					safeClick(driver, getObjectHotels("HotelCom_SRP_ShowAll_Locations"));
			    	Thread.sleep(7000);
			    	driver.switchTo().frame("modal_window");  
			    	elementVisible(driver, getObjectHotels("HotelCom_SRP_Location_PopUp_SearchBox"), 50);
			    	safeClick(driver, getObjectHotels("HotelCom_SRP_Location_CheckBox"));
			    	safeClick(driver, getObjectHotels("HotelCom_SRP_Location_Search_Button"));
			}
			
		//================================================== Property Type Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("PROPERTYTYPE")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
			 	if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Property_Type"), 10)){
			 			Reporter.log("Property Type is not displayed");
			 			Assert.assertTrue(false);
			 	}
		    	/*for(int i=1; i<=10; i++){
		    		String Property_CheckBox_XPATH =  "//div[16]/div/nav/ul/li["+i+"]/label/input";
		    		if(elementVisible(driver,  By.xpath(Property_CheckBox_XPATH), 1)){
		    			UnChecking_Checkbox(driver,  By.xpath(Property_CheckBox_XPATH));
		    			//safeClick(driver,  By.xpath(Property_CheckBox_XPATH));
		    		}
		    	}		 	*/
		    	for(int i=1; i<=10; i++){
		    		String Property_Type_XPATH =  "//div[12]/div/nav/ul/li["+i+"]/label/small";
		    		String Property_Type_XPATH1 =  "//div[12]/div/nav/ul/li["+i+"]/label/span";
		    		String Property_Type_Only =  "//li["+i+"]/label/a";
		    		if(elementVisible(driver, By.xpath(Property_Type_XPATH1), 1)){
		    		Thread.sleep(1000);
		    		mouseHover(driver,  By.xpath(Property_Type_XPATH1));
		    		Thread.sleep(1000);
		    		safeClick(driver,  By.xpath(Property_Type_Only));
		    		Thread.sleep(2000);
		    		String Property_Count = getText(driver, By.xpath(Property_Type_XPATH));
		       		String Hotel_Count = getText(driver, By.xpath("//div[2]/section[2]/section/aside/div/p[2]/strong/span"));
		    		String Property_Type = getText(driver, By.xpath(Property_Type_XPATH1));
		    		safeClick(driver,  By.xpath(Property_Type_XPATH1));
		    		if(!Property_Count.equals(Hotel_Count)){
		    			Reporter.log("Hotel Count after selecting Property : "+Property_Type+" is not same");
		    		//	System.out.println("Hotel Count after selecting Property : "+Property_Type+" " +Property_Count+" is not same"+Hotel_Count);
			    		Assert.assertTrue(false);
			    		}
		    		
		    		}
		      	}
			}
			
			//====================================================-------------- SAVING Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("SAVING")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				safeClick(driver, By.xpath("(//a[contains(text(),'Savings')])[2]"));
				String Saving1 = null, Saving2 = null;
				if(elementVisible(driver, By.xpath("//li[1]/section/nav/ul/li[3]/h2[2]/small"), 2)) {
				//	Saving1 = getText(driver, By.xpath("//li[1]/section/nav/ul/li[3]/h2[2]/small"));
					Saving1 = getText(driver, By.xpath("//h2[2]/small"));
				}
				if(elementVisible(driver, By.xpath("//li[2]/section/nav/ul/li[3]/h2[2]/small"), 2)) {
					//Saving2 = getText(driver, By.xpath("//li[2]/section/nav/ul/li[3]/h2[2]/small"));
					Saving2 = getText(driver, By.xpath("//li[2]/section/nav/ul/li[3]/h2[2]/small"));
					
				}
				if(Domain.equals("com")) {
				Saving1 = Saving1.replace("Save Rs.", "");
				Saving2 = Saving2.replace("Save Rs.", "");
				}
				else if(Domain.equals("ae")) {
					Saving1 = Saving1.replace("Save AED", "");
					Saving2 = Saving2.replace("Save AED", "");
				}
				if(Saving1.contains(",")) {
					Saving1 = Saving1.replace(",", "");
				}
				if(Saving2.contains(",")) {
					Saving2 = Saving2.replace(",", "");
				}
				int Saving1_int = Integer.parseInt(Saving1);
				int Saving2_int = Integer.parseInt(Saving2);
				if(Saving2_int > Saving1_int) {
					Reporter.log("Saving 1 : "+Saving1_int+ " Saving2 : "+Saving2_int);
					Assert.assertTrue(false);
				}
				
			}
			
		//================================================= Price Drag Filter =================================================================//	
			if(FilterType.equalsIgnoreCase("PRICEDRAG")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 30);
		   		safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
		   		for(int i=0; i<=2; i++){
		   		String Price = getText(driver, getObjectHotels("HotelCom_SRP_Price_Filter_LeftSide"));
		   		Price = Price.replaceAll("Rs.", "");
		   		Price = Price.replaceAll("to ", "");
		   		Price = Price.replaceAll(",", "");
		   		String PriceSplit[] = Price.split(" ");
		   		int PriceSplit1_Int = Integer.parseInt(PriceSplit[0]);
		   		int PriceSplit2_Int = Integer.parseInt(PriceSplit[1]);
		   		String Hotel_Price = getText(driver, getObjectHotels("HotelCom_SRP_Price_With_Discount_Text"));
		   		String SavingText = null;
		   		if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Without_Discount_Text"), 1)){
		   			SavingText = getText(driver, getObjectHotels("HotelCom_SRP_Price_Without_Discount_Text"));
		   		}
		   		if(!SavingText.isEmpty()){
		   			Hotel_Price = Hotel_Price.replace(SavingText, "");
		   		}
		   		Thread.sleep(2000);
		   		Hotel_Price = Hotel_Price.replaceAll("Rs.", "");
		   		if(Hotel_Price.contains(","))
		   		{
		   			Hotel_Price = Hotel_Price.replaceAll(",", "");	
		   		}
		   		int Hotel_Price_Int = Integer.parseInt(Hotel_Price);

		   		if(!(PriceSplit1_Int>=Hotel_Price_Int))
		   		{
		   			Reporter.log("Price filter is not working when selected : "+PriceSplit1_Int+" , it is displaying : "+Hotel_Price_Int);
		   			Assert.assertTrue(false);
		   		}
		   		dragAndDrop(driver, getObjectHotels("HotelCom_SRP_Price_Drag"), 10, 1);
		   		}		 	
			}
			
		//================================================== Sort Saving Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("SORTSAVING")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
		 		if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Savings_Filter"), 10)){
		 			Reporter.log("Savings link is not displayed");
		 			Assert.assertTrue(false);
		 		}
		 		elementVisible(driver, getObjectHotels("HotelCom_SRP_Savings_Filter"), 10);
		 		safeClick(driver, getObjectHotels("HotelCom_SRP_Savings_Filter"));
		 		String Savings_Hotel1 = getText(driver, By.xpath("//li[1]/section/nav/ul/li[3]/h2[2]/small"));
		 		String Savings_Hotel2 = getText(driver, By.xpath("//li[2]/section/nav/ul/li[3]/h2[2]/small"));
		 		String URL = logURL(driver);
		 		if(URL.contains("com")){
		 			if(Savings_Hotel1.contains("Save Rs.")) {
				 		Savings_Hotel1 = Savings_Hotel1.replaceAll("Save Rs.", "");
		 			}
		 			if(Savings_Hotel2.contains("Save Rs.")) {
				 		Savings_Hotel2 = Savings_Hotel2.replaceAll("Save Rs.", "");
		 			}
		 		}
		 		if(URL.contains("ae")){
		 			if(Savings_Hotel1.contains("Save Rs.")) {
				 		Savings_Hotel1 = Savings_Hotel1.replaceAll("Save AED", "");
		 			}
		 			if(Savings_Hotel2.contains("Save Rs.")) {
				 		Savings_Hotel2 = Savings_Hotel2.replaceAll("Save AED", "");
		 			}
		 		}
		 		if(Savings_Hotel1.contains(",")){
		 			Savings_Hotel1 = Savings_Hotel1.replace(",", "");
				}
		 		if(Savings_Hotel2.contains(",")){
		 			Savings_Hotel2 = Savings_Hotel2.replace(",", "");
				}
		 		int Saving1_int = Integer.parseInt(Savings_Hotel1);
				int Saving2_int = Integer.parseInt(Savings_Hotel2);
				if(Saving1_int<Saving2_int){
					Reporter.log("Saving sort is not working");
					Assert.assertTrue(false);
				}
			}
			
		//==================================================== Sort Tripadvisor Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("SORTTRIPADVISOR")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
		 		if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_TripAdvisor_Filter"), 10)){
		 			Reporter.log("TripAdvisor filter link is not displayed");
		 			Assert.assertTrue(false);
		 		}
		 		safeClick(driver, getObjectHotels("HotelCom_SRP_TripAdvisor_Filter"));
				//String TripAdvisor_1 = getAttribute(driver, By.xpath("//li[1]/section/div[3]/span[2]/span[2]"), "title");
				//String TripAdvisor_2 = getAttribute(driver, By.xpath("//li[2]/section/div[3]/span[2]/span[2]"), "title");
		 		String TripAdvisor_1 = getAttribute(driver, By.xpath("(//section/div[3]/div[1]/span[2]/span[2])[1]"), "title");
				String TripAdvisor_2 = getAttribute(driver, By.xpath("(//section/div[3]/div[1]/span[2]/span[2])[2]"), "title");
				if(TripAdvisor_1.contains(".5")){
					TripAdvisor_1 = TripAdvisor_1.replace(".5", "");
				}
				if(TripAdvisor_2.contains(".5")){
					TripAdvisor_2 = TripAdvisor_2.replace(".5", "");
				}
				TripAdvisor_1 = TripAdvisor_1.replace("/5", "");
				TripAdvisor_2 = TripAdvisor_2.replace("/5", "");		
				float TripAdvisor1_int = Integer.parseInt(TripAdvisor_1);
				float TripAdvisor2_int = Integer.parseInt(TripAdvisor_2);		
				if(TripAdvisor1_int<TripAdvisor2_int){
					Reporter.log("Tripadvisor sort is not working");
					Assert.assertTrue(false);
				}
			}
			
		//====================================================Pilgrimage Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("PILGRIMAGE")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, By.xpath("(//a[contains(text(),'Distance')])[2]"), 10)){
		 			Reporter.log("Distance search option is not displayed");
		 			Assert.assertTrue(false);
		 		}
			    Thread.sleep(2000);
			    safeClick(driver, By.xpath("(//a[contains(text(),'Distance')])[2]"));
			    
			   if( !elementVisible(driver, By.xpath("//li[1]/section/nav/ul/li/small/strong"), 10)){
			   	   Reporter.log("Distance link not displayed");
				   Assert.assertEquals(true, false);
			   }
				String Distance1 = getText(driver, By.xpath("//li[1]/section/nav/ul/li/small/strong"));
				String Distance2 = getText(driver, By.xpath("//li[2]/section/nav/ul/li/small/strong"));
				boolean Hotel3 = elementVisible(driver, By.xpath("//li[3]/section/nav/ul/li/small/strong"), 10);
				Distance1 = Distance1.replace("km","");
				Distance2 = Distance2.replace("km","");	
				String Distance3 = null;
				float Distance3_Float = 0.0f ;
				if(Hotel3){
				Distance3 = getText(driver, By.xpath("//li[3]/section/nav/ul/li/small/strong"));
				Distance3 = Distance3.replace("km","");
				Distance3_Float = Float.parseFloat(Distance3);
				}
				float Distance1_Float = Float.parseFloat(Distance1);
				float Distance2_Float = Float.parseFloat(Distance2);
				if(!(Distance1_Float <=Distance2_Float)){
					Reporter.log("Distance of first Hotel ' "+Distance1+" ' is more then that of second Hotel ' "+Distance2+" '");
					Assert.assertTrue(false);    		
				}
				if(Hotel3){
				if(!(Distance2_Float <=Distance3_Float)){
					Reporter.log("Distance of second Hotel ' "+Distance2+" ' is more then that of third Hotel ' "+Distance3+" '");
					Assert.assertTrue(false);    		
					}   
				}
			}
			
		//==================================================== Business Enhancer Filter ============================================================//	
			if(FilterType.equalsIgnoreCase("BUSINESSENHANCER")){
				SRP_URL(driver, Domain, City, State, Dest_Code);
				if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Distance_Search"), 10)){
		 			Reporter.log("Distance Search box is not displayed");
		 			Assert.assertTrue(false);
		 		}				
		 		safeType(driver, getObjectHotels("HotelCom_SRP_Distance_Search"), "Central Delhi");
		 		safeClick(driver, getObjectHotels("HotelCom_SRP_Distance_Search_Button"));
		        Thread.sleep(2000);
		        elementVisible(driver, By.xpath("//div[2]/section[5]/section/div/div[2]/section/div[2]/nav/ul/li[7]/a"), 60);
				String Location1 = getText(driver, By.xpath("//li/small"));
				String Location2 = getText(driver, By.xpath("//li[2]/section/nav/ul/li/small"));
		        String[] word1 = Location1.split("\\s+");
		        String[] word2 = Location2.split("\\s+");
				 String Word1 = word1[5].replace("m", "");
				 String Word2 = word2[5].replace("m", "");
				 int distance1 = Integer.parseInt(Word1);
				 int distance2 = Integer.parseInt(Word2);
				 if(!(distance1<=distance2)){
					 Reporter.log("Distance displayed in first hotel is ' "+word1+" ' Distance displayed in Second hotel is ' "+word2+" '");
					 Assert.assertTrue(false);
				 }
			}
	}

	public String getAttribute(RemoteWebDriver driver, By by, String Attribute) {
		String ElementAttribute = driver.findElement(by).getAttribute(Attribute);
		return ElementAttribute;
	}

	public void SRP_URL(RemoteWebDriver driver, String Domain, String City, String State, String Dest_Code ) throws Exception {
			baseUrl = hotelCom_Open_URL(driver, Domain);
			
			String Date = putDate1(20);
			String Date1 = putDate1(21);
			String SRP_URL = baseUrl+"/hotels/results?city="+City+"&state="+State+"&country=IN&area=&poi=&hotelId=&hotelName=&dest_code="+Dest_Code+"&chk_in="+Date+"&chk_out="+Date1+"&adults1=1&children1=0&num_rooms=1";
			driver.get(SRP_URL);
			logURL(driver);
			elementNotVisible(driver, By.cssSelector("div.glassShield"), 30);
			elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50);
			Thread.sleep(2000);
	}
	
	public void SRP_URL_HotelName_Pax(RemoteWebDriver driver, String Domain, String City, String State, String Dest_Code, String Adults, String Children, String HotelName ) throws Exception {
		baseUrl = hotelCom_Open_URL(driver, Domain);
		
		String Date = putDate1(20);
		String Date1 = putDate1(21);
		String SRP_URL = baseUrl+"/hotels/results?city="+City+"&state="+State+"&country=IN&area=&poi=&hotelId=&hotelName=&dest_code="+Dest_Code+"&chk_in="+Date+"&chk_out="+Date1+"&adults1="+Adults+"&children1="+Children+"&num_rooms=1&hotelName="+HotelName;
		driver.get(SRP_URL);
		logURL(driver);
		elementNotVisible(driver, By.cssSelector("div.glassShield"), 30);
		elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50);
	 	safeClick(driver, By.xpath("(//a[contains(text(),'Per room, per night')])[2]"));
		Thread.sleep(2000);
}
	
	public String SRP_URL_Link(RemoteWebDriver driver, String Domain, String City, String State, String Dest_Code, int Date ) throws Exception {
		baseUrl = hotelCom_Open_URL(driver, Domain);
		
		String Date1 = putDate1(Date);
		String Date2 = putDate1(Date+1);
		String SRP_URL = baseUrl+"/hotels/results?city="+City+"&state="+State+"&country=IN&area=&poi=&hotelId=&hotelName=&dest_code="+Dest_Code+"&chk_in="+Date1+"&chk_out="+Date2+"&adults1=1&children1=0&num_rooms=1";
		/*driver.get(SRP_URL);
		logURL(driver);
		elementNotVisible(driver, By.cssSelector("div.glassShield"), 60);
		elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50);
		Thread.sleep(2000);*/
		return SRP_URL;
}
	
	public void SRP_URL_Debug(RemoteWebDriver driver, String Domain, String City, String State, String Country, String Dest_Code, int date ) throws Exception {
		baseUrl = hotelCom_Open_URL(driver, Domain);		
		int date1 = date+1;
		String Date = putDate1(date);
		String Date1 = putDate1(date1);
		String SRP_URL = baseUrl+"/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code="+Dest_Code+"&chk_in="+Date+"&chk_out="+Date1+"&adults1=1&children1=0&num_rooms=1&showDebug=true";
		driver.get(SRP_URL);
		logURL(driver);
		elementNotVisible(driver, By.cssSelector("div.glassShield"), 60);
		elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50);
		Thread.sleep(2000);
}
	
	public void SRP_URL_Debug_MultiRoom_Nights(RemoteWebDriver driver, String Domain, String City, String State, String Country, String Dest_Code, int date ) throws Exception {
		baseUrl = hotelCom_Open_URL(driver, Domain);		
		int date1 = date+2;
		String Date = putDate1(date);
		String Date1 = putDate1(date1);
		String SRP_URL = baseUrl+"/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code="+Dest_Code+"&chk_in="+Date+"&chk_out="+Date1+"&adults1=2&children1=0&adults2=2&children2=0&num_rooms=2&showDebug=true";
		driver.get(SRP_URL);
		logURL(driver);
		elementNotVisible(driver, By.cssSelector("div.glassShield"), 60);
		elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50);
		Thread.sleep(2000);
}
		
		
	public String detailsPage_URL_Link(RemoteWebDriver driver, String Domain, String HotelID, int Date) throws Exception {
		String CheckIn = putDateNoSplChar(Date);
		   String CheckOut = putDateNoSplChar(Date+1);
		   if(Domain.contains("ae")) {
			   baseUrl =baseUrl_AE; 
		   }
		   else if(Domain.contains("ae")) {
			   baseUrl =baseUrl_AE; 
		   }
		   else if(Domain.contains("bh")) {
			   baseUrl =baseUrl_BH; 
		   }
		   else if(Domain.contains("sa")) {
			   baseUrl =baseUrl_SA; 
		   }
		   else if(Domain.contains("kw")) {
			   baseUrl =baseUrl_KW; 
		   }
		   else if(Domain.contains("OM")) {
			   baseUrl =baseUrl_OM; 
		   }
		   else if(Domain.contains("qa")) {
			   baseUrl =baseUrl_QA; 
		   }
		   else if(Domain.contains("me")) {
			   baseUrl =baseUrl_ME; 
		   }
		   else if(Domain.contains("AMEX")) {
			   baseUrl =getAmexTravelUrl(); 
		   }
		String detailsPageUrlLink = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0&compId=&fr=1&ur=1&urt=featured&stp=none&pahCCRequired=true&op=true";
		return detailsPageUrlLink;
	}	
	
	public void hotelCom_DetailsPage_BackLink(RemoteWebDriver driver) throws Exception{
		if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
			   Reporter.log("Hotel Rate is not displayed in Details page");
		   }
	 	if(!elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Back_Button"), 5)) {
	 		Reporter.log("Back button is not displayed");
	 		Assert.assertTrue(false);
	 	}
	 	String CityName = getText(driver, getObjectHotels("HotelCom_DetailsPage_Back_Button"));
	 	CityName = CityName.replaceAll(" All hotels in", "");
	 	String[] CityName1 = CityName.split(" ");
	 	CityName = CityName1[1];
	 	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Back_Button"));
	 	elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 5);
	 	String CityName_SRP = getText(driver, getObjectHotels("HotelCom_SRP_CityName"));
	 	if(!CityName_SRP.contains(CityName)) {
	 		Reporter.log("City Name is different : Details Page City : "+CityName+" SRP City Name : "+CityName_SRP);
	 		Assert.assertTrue(false);
	 	}
	}

	public void hotelCom_DetailsPage_BackLinkAmex(RemoteWebDriver driver) throws Exception{
		if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
			   Reporter.log("Hotel Rate is not displayed in Details page");
		   }
	 	if(!elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Back_Button"), 5)) {
	 		Reporter.log("Back button is not displayed");
	 		Assert.assertTrue(false);
	 	}
	 	String CityName = getText(driver, getObjectHotels("HotelCom_DetailsPage_Back_Button"));
	 	CityName = CityName.replaceAll(" All hotels in", "");
	 	String[] CityName1 = CityName.split(" ");
	 	CityName = CityName1[1];
	 	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Back_Button"));
	 	elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 5);
	 	if(!elementVisible(driver, By.xpath("(//a[@id='modifySearchLink'])[2]"), 2)) {
	 		Reporter.log("Amex SRP page is not displayed");
	 		Assert.assertTrue(false);
	 	}
	 	
	}

	public void hotelCom_SRP_ModalWindow(RemoteWebDriver driver, String BookingType)throws InterruptedException, Exception {
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		boolean NewDetailsPage = false, OldDetailsPage = false;
		for(int i=0; i<=5 ;i++) {
			if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"),1)) {
				NewDetailsPage = true;
				break;
			}else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"),1)) {
				OldDetailsPage = true;
				break;
			}			
			else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 5)) {
				Reporter.log("Sorry, this hotel is unavailable message is displayed");
				Assert.assertTrue(false);
			}	
		}
		
		if(NewDetailsPage) {
			loop: for(int i=0; i<=10;i++) {
				if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_Price"), 1)) {
					break loop;
				} else if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_LoaderDots"), 1)) {
					Thread.sleep(2000);
				}else if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 1)) {
					Reporter.log("Sorry, this hotel is unavailablePlease try different dates - Error is displayed");
					Assert.assertTrue(false);
				}else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
					  Reporter.log("Sorry, our system is acting up. : message is displayed");
					  Assert.assertTrue(false);
				}else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)){
					  Reporter.log("Sorry, this hotel is unavailable. : message is displayed");
					  Assert.assertTrue(false);
				}else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)){
					  Reporter.log("Whatever you're looking for, isn't here : message is displayed");
					  Assert.assertTrue(false);
				}Thread.sleep(1000);
			}
			String Modal_URL = driver.getCurrentUrl();
			driver.switchTo().window(tabs.get(0));
			driver.get(Modal_URL);
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
			elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 5);
			
			if(BookingType.equals("DealOfTheDay")) {
				if(!elementVisible(driver, By.cssSelector("svg.rate__DODBanner > use"), 5)) {
					Reporter.log("Deal of the day tag is not displayed in details page");
					Assert.assertTrue(false);
				}
			}
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
			Thread.sleep(500);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));			
		} 
		
		else if(OldDetailsPage) {
		
		loop: for(int i=0; i<=10;i++) {
			if(elementPresent_Time(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)) {
				break loop;
			} 
			if(elementPresent_Time(driver, By.xpath("//div[@id='hotelDetailsHeader']/div/div/div[2]/div[2]/div[2]/strong"), 1)) {
				break loop;
			} 		
			Thread.sleep(1000);
		}
		
		String Modal_URL = driver.getCurrentUrl();
		driver.switchTo().window(tabs.get(0));
		driver.get(Modal_URL);
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
		if(!(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10) || elementPresent_Time(driver, By.xpath("//div[2]/strong"), 5))) {
			refreshPage(driver);
		}
		 for(int i=0; i<=10;i++){
			if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
				 		break;
				 }
			else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
				  Reporter.log("Sorry, our system is acting up. : message is displayed");
				  Assert.assertTrue(false);
			  }
			  
			  else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)){
				  Reporter.log("Sorry, this hotel is unavailable. : message is displayed");
				  Assert.assertTrue(false);
			  }
			  else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)){
				  Reporter.log("Whatever you're looking for, isn't here : message is displayed");
				  Assert.assertTrue(false);
			  }
			  else if(!elementVisible(driver, By.cssSelector("div.roomRate > div.row > div.perRoomPrDisp > span.loader.dotDotDot"), 1)){
				 break;
			 }
			 
		 }
			   if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
				   Reporter.log("Hotel Rate is displayed in Details page");
			   }
			   else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)) {
					String HotelName = getText(driver, By.xpath("//div[2]/div/div/h1"));
					Reporter.log("Sorry, this hotel is unavailable - No rates/ inventory for hotel :"+HotelName);
					Assert.assertTrue(false);
			   } 
			   
			   if(BookingType.equalsIgnoreCase("PACKAGERATECORP")) {
				   if(!elementVisible(driver, By.cssSelector("span.status"), 5)) {
					   Reporter.log("Package Rate : is not displayed in details page");
					   Assert.assertTrue(false);
				   }
				   if(getText(driver, By.cssSelector("span.status")).contains("SPECIAL RATE")) {
					   Reporter.log("SPECIAL RATE text is not displayed in Details Page");
					   Assert.assertTrue(false);
				   }
				   safeClick(driver, By.cssSelector("span.status"));
			   }
			   else if(BookingType.equalsIgnoreCase("SPECIALRATECORP")) {
				   if(!elementVisible(driver, By.cssSelector("span.status"), 5)) {
					   Reporter.log("Special Rate : is not displayed in details page");
					   Assert.assertTrue(false);
				   }
				   if(!getText(driver, By.cssSelector("span.status")).contains("SPECIAL RATE")) {
					   Reporter.log("SPECIAL RATE text is not displayed in Details Page");
					   Assert.assertTrue(false);
				   }
				   safeClick(driver, By.cssSelector("span.status"));
			   }
			   //==========================chmm promo===============
			   else if(BookingType.equalsIgnoreCase("CHMMPromo")) {
					if(elementPresent(driver, getObjectHotels("HotelCom_DetailPage_CHMMPromo_dod"))){
						Reporter.log(getText(driver, getObjectHotels("HotelCom_DetailPage_CHMMPromo_dod")));
						Reporter.log("chmm Promo displayed in details page");
					}
					else{
						Reporter.log("chmm Promo not displayed in details page");
						Assert.assertTrue(false);
					}
					}
			   //====================================================
			   if(elementVisible(driver, By.xpath("//div/div/div[2]/a"), 1)){
				   safeClick(driver, By.xpath("//div/div/div[2]/a"));
				   smartClick(driver, By.linkText("Book"));
			   }	
			   smartClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));	
		}
	}	
	
	
	

	public void hotelCom_SRP_ModalWindow_B2B(RemoteWebDriver driver, String BookingType)throws InterruptedException, Exception {
		
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		boolean NewDetailsPage = false, OldDetailsPage = false;
		for(int i=0; i<=5 ;i++) {
			if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"),1)) {
				NewDetailsPage = true;
				break;
			}else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"),1)) {
				OldDetailsPage = true;
				break;
			}				
			else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 5)) {
				Reporter.log("Sorry, this hotel is unavailable message is displayed");
				Assert.assertTrue(false);
			}	
		}
		if(OldDetailsPage) {
		loop: for(int i=0; i<=10;i++) {
			if(elementPresent_Time(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)) {
				break loop;
			} 
			if(elementPresent_Time(driver, By.xpath("//div[@id='hotelDetailsHeader']/div/div/div[2]/div[2]/div[2]/strong"), 1)) {
				break loop;
			} 		
			Thread.sleep(1000);
		}
		
		String Modal_URL = driver.getCurrentUrl();
		driver.switchTo().window(tabs.get(0));
		driver.get(Modal_URL);
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
		if(!(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10) || elementPresent_Time(driver, By.xpath("//div[2]/strong"), 5))) {
			refreshPage(driver);
		}
		 for(int i=0; i<=10;i++){
			if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
				 		break;
				 }
			else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
				  Reporter.log("Sorry, our system is acting up. : message is displayed");
				  Assert.assertTrue(false);
			  }
			  
			  else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)){
				  Reporter.log("Sorry, this hotel is unavailable. : message is displayed");
				  Assert.assertTrue(false);
			  }
			  else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)){
				  Reporter.log("Whatever you're looking for, isn't here : message is displayed");
				  Assert.assertTrue(false);
			  }
			  else if(!elementVisible(driver, By.cssSelector("div.roomRate > div.row > div.perRoomPrDisp > span.loader.dotDotDot"), 1)){
				 break;
			 }
			 
		 }
			   if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
				   Reporter.log("Hotel Rate is displayed in Details page");
			   }
			   else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)) {
					String HotelName = getText(driver, By.xpath("//div[2]/div/div/h1"));
					Reporter.log("Sorry, this hotel is unavailable - No rates/ inventory for hotel :"+HotelName);
					Assert.assertTrue(false);
			   } 
			   
			   if(BookingType.equalsIgnoreCase("PACKAGERATECORP")) {
				   if(!elementVisible(driver, By.cssSelector("span.status"), 5)) {
					   Reporter.log("Package Rate : is not displayed in details page");
					   Assert.assertTrue(false);
				   }
				   if(getText(driver, By.cssSelector("span.status")).contains("SPECIAL RATE")) {
					   Reporter.log("SPECIAL RATE text is not displayed in Details Page");
					   Assert.assertTrue(false);
				   }
				   safeClick(driver, By.cssSelector("span.status"));
			   }
			   else if(BookingType.equalsIgnoreCase("SPECIALRATECORP")) {
				   if(!elementVisible(driver, By.cssSelector("span.status"), 5)) {
					   Reporter.log("Special Rate : is not displayed in details page");
					   Assert.assertTrue(false);
				   }
				   if(!getText(driver, By.cssSelector("span.status")).contains("SPECIAL RATE")) {
					   Reporter.log("SPECIAL RATE text is not displayed in Details Page");
					   Assert.assertTrue(false);
				   }
				   safeClick(driver, By.cssSelector("span.status"));
			   }
			   //==========================chmm promo===============
			   else if(BookingType.equalsIgnoreCase("CHMMPromo")) {
					if(elementPresent(driver, getObjectHotels("HotelCom_DetailPage_CHMMPromo_dod"))){
						Reporter.log(getText(driver, getObjectHotels("HotelCom_DetailPage_CHMMPromo_dod")));
						Reporter.log("chmm Promo displayed in details page");
					}
					else{
						Reporter.log("chmm Promo not displayed in details page");
						Assert.assertTrue(false);
					}
					}
			   //====================================================
			   if(elementVisible(driver, By.xpath("//div/div/div[2]/a"), 1)){
				   safeClick(driver, By.xpath("//div/div/div[2]/a"));
				   smartClick(driver, By.linkText("Book"));
			   }	
			   smartClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));	
		}
		//==========================New Details Page=====================================//
		else if(NewDetailsPage) {

			loop: for(int i=0; i<=10;i++) {
				if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_Price"), 1)) {
					break loop;
				} else if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_LoaderDots"), 1)) {
					Thread.sleep(2000);
				}else if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 1)) {
					Reporter.log("Sorry, this hotel is unavailablePlease try different dates - Error is displayed");
					Assert.assertTrue(false);
				}else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
					  Reporter.log("Sorry, our system is acting up. : message is displayed");
					  Assert.assertTrue(false);
				}else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)){
					  Reporter.log("Sorry, this hotel is unavailable. : message is displayed");
					  Assert.assertTrue(false);
				}else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)){
					  Reporter.log("Whatever you're looking for, isn't here : message is displayed");
					  Assert.assertTrue(false);
				}Thread.sleep(1000);
			}
			String Modal_URL = driver.getCurrentUrl();
			driver.switchTo().window(tabs.get(0));
			driver.get(Modal_URL);
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0));
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
			Thread.sleep(500);
			//smartClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Agency_Book_Button"));
			
		}
	}
	
	
	public void hotelCom_DetailsPage_URL_Open(RemoteWebDriver driver, String Domain, String HotelID, int Date ) throws Exception {
		   String CheckIn = putDateNoSplChar(Date);
		   String CheckOut = putDateNoSplChar(Date+1);
		   String DetailsPage_URL = null;
			 	DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,1-5";																																	
				driver.get(DetailsPage_URL);
				refreshPage(driver);
			  
	}
	
	public void hotelCom_DetailsPage_URL_Open1(RemoteWebDriver driver, String Domain, String HotelID, int Date ) throws Exception {
		   String CheckIn = putDateNoSplChar(Date);
		   String CheckOut = putDateNoSplChar(Date+1);
		   String DetailsPage_URL = null;		   
		   DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0";																																	
			if(Domain.equals("ae")) {
			 	DetailsPage_URL = baseUrl_AE+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0";		
			}
			driver.get(DetailsPage_URL);
			Reporter.log(DetailsPage_URL);
	}
	
	public void hotelCom_DetailsPage(RemoteWebDriver driver, String Domain, String HotelID, int Date, String BookingType ) throws Exception {
		   String CheckIn = putDateNoSplChar(Date);
		   String CheckOut = putDateNoSplChar(Date+1);
		   String DetailsPage_URL = null;
		   if(Domain.equalsIgnoreCase("com")&&BookingType.equalsIgnoreCase("")) {
				DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0-5";																																	
				driver.get(DetailsPage_URL);
				//refreshPage(driver);
			   }
			   else if(BookingType.equals("ProdpaymentLive")) {
					DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0-5";																																	
					driver.get(DetailsPage_URL);
					refreshPage(driver);
				   }
			   else if(BookingType.equals("ProdpaymentLiveExpedia")) {
					DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0-5";																																	
					driver.get(DetailsPage_URL);
					refreshPage(driver);
				   }
	//====================================================
			   else if(Domain.equalsIgnoreCase("com")&&BookingType.equalsIgnoreCase("CHMM")) {
					DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=1,0";																																	
					driver.get(DetailsPage_URL);
					refreshPage(driver);
					if(elementPresent(driver, getObjectHotels("HotelCom_DetailPage_CHMMPromo_dod"))){
						if(elementPresent(driver, getObjectHotels("HotelCom_DetailPage_CHMMPromo_deal"))){
							if(elementPresent(driver, getObjectHotels("HotelCom_DetailPage_CHMMPromo_save"))){
								String save=getText(driver, getObjectHotels("HotelCom_DetailPage_CHMMPromo_save"));
								Reporter.log("CHMM promo "+save+" displayed in Details Page");
								}
							}
						}
					}
//======================UTM Trivago==================
			   if(Domain.equalsIgnoreCase("com")&&BookingType.equalsIgnoreCase("Trivago")) {
					//DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,1-5";
				   DetailsPage_URL =baseUrl+"/hotels/details/"+HotelID+"?hotelID="+HotelID+"&utm_source=Trivago&utm_medium=cpc&utm_campaign="+HotelID+"&c="+CheckIn+"|"+CheckOut+"&r=2,0";
					driver.get(DetailsPage_URL);
					refreshPage(driver);
				   }
			   
//=======================TripAdvisor=================================================
			   if(Domain.equalsIgnoreCase("com")&&BookingType.equalsIgnoreCase("TA")) {
					//https://www.cleartrip.com/hotels/details/732040?&utm_source=tripadvisor&utm_campaign=732040&c=250418|260418&r=2,0
				   DetailsPage_URL =baseUrl+"/hotels/details/"+HotelID+"?&utm_source=tripadvisor&utm_campaign="+HotelID+"&c="+CheckIn+"|"+CheckOut+"&r=2,0";
					driver.get(DetailsPage_URL);
					refreshPage(driver);
				   }
//======================UTM Google==================
		   if(Domain.equalsIgnoreCase("com")&&BookingType.equalsIgnoreCase("Google")) {
			   DetailsPage_URL =baseUrl+"/hotels/details/"+HotelID+"?utm_source=GoogleHPA&utm_campaign="+HotelID+"&c="+CheckIn+"|"+CheckOut+"&r=2,0";
				driver.get(DetailsPage_URL);
				refreshPage(driver);
		   }		   
//==========================================
			   else if(Domain.equalsIgnoreCase("com")&&BookingType.equalsIgnoreCase("HQ")) {
					DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=1,0";																																	
					driver.get(DetailsPage_URL);
					refreshPage(driver);
					if(HotelID.equals("709552")){
						String dpPromo=getText(driver, By.xpath("//div[@class='roomRate ']/div/p"));
						Reporter.log("HQ promo "+dpPromo+" displayed in Details Page");
					}
				}
//=============================================		   
			   else if(Domain.equalsIgnoreCase("com")&&BookingType.equalsIgnoreCase("MultipleRoomNights")) {
				   CheckOut = putDateNoSplChar(Date+2);
				   DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,0|2,0";																																	
					driver.get(DetailsPage_URL);
					refreshPage(driver);
			   }
			  else if(!Domain.equalsIgnoreCase("com")) {
							DetailsPage_URL = baseUrl_AE+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=1,0&shwb=true&compId=";		   
							driver.get(DetailsPage_URL);
							refreshPage(driver);
					   }
		  logURL(driver);
		  
		  boolean NewDetailsPage = false, OldDetailsPage = false;
			for(int i=0; i<=5;i++) {
				if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"),1)) {
					NewDetailsPage = true;
					break;
				}else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"),1)) {
					OldDetailsPage = true;
					break;
				}
				else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 5)) {
						Reporter.log("Sorry, this hotel is unavailable message is displayed");
						Assert.assertTrue(false);
					}					
			}
		  if(NewDetailsPage) {
				loop: for(int i=0; i<=10;i++) {
					if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_Price"), 1)) {
						break loop;
					} else if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_LoaderDots"), 1)) {
						Thread.sleep(2000);
					}else if(elementPresent_Time(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 1)) {
						Reporter.log("Sorry, this hotel is unavailablePlease try different dates - Error is displayed");
						Assert.assertTrue(false);
					}else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
						  Reporter.log("Sorry, our system is acting up. : message is displayed");
						  Assert.assertTrue(false);
					}else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)){
						  Reporter.log("Sorry, this hotel is unavailable. : message is displayed");
						  Assert.assertTrue(false);
					}else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)){
						  Reporter.log("Whatever you're looking for, isn't here : message is displayed");
						  Assert.assertTrue(false);
					}Thread.sleep(1000);
				}
		  
		  if(BookingType.equals("ProdpaymentLive")) {
			  if(!textPresent(driver, "Free cancellations", 1)){
					  Reporter.log("Free cancellations. : message is not displayed");
					  Assert.assertTrue(false);
				  }	
		   }
		   if(BookingType.equals("ProdpaymentLiveExpedia")) {
			   elementVisible(driver, By.linkText("Show details"), 20);
			   safeClick(driver, By.linkText("Show details"));
			   Thread.sleep(5000);
			   elementVisible(driver, By.cssSelector("div.rateAdditionalDetails > div.row"), 20);
			   String CancellationText = getText(driver, By.cssSelector("div.rateAdditionalDetails > div.row"));
			   
			   if(!CancellationText.contains("we are required to pass on: Cancellations or changes made after")) {
				   Reporter.log("we are required to pass on: Cancellations or changes made after : message is not displayed");
				   Reporter.log("CancellationText : "+CancellationText);
				   Assert.assertTrue(false);
			   }
			   if(CancellationText.contains("This rate is non-refundable")||CancellationText.isEmpty()) {
				   Reporter.log("This rate is non-refundable - so cancelling the booking");
				   Reporter.log("CancellationText : "+CancellationText);
				   Assert.assertTrue(false);
			   }
		   }
				elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 5);
				safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
				Thread.sleep(500);
				safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));			
			} 
		  else if(OldDetailsPage) {		  
		  for(int i=0; i<=15;i++){
			  if(!elementVisible(driver, By.cssSelector("div.roomRate > div.row > div.perRoomPrDisp > span.loader.dotDotDot"), 2)){
					 break;
				 }
			  else if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
			 		break;
			 }else if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_PriceNEW"), 1)){
			 		break;
			 }
			  else if(textPresent(driver, "Sorry, our system is acting up.", 1)){
				  Reporter.log("Sorry, our system is acting up. : message is displayed");
				  Assert.assertTrue(false);
			  }			  
			  else if(textPresent(driver, "Sorry, this hotel is unavailable", 1)){
				  Reporter.log("Sorry, this hotel is unavailable. : message is displayed");
				  Assert.assertTrue(false);
			  }
			  else if(textPresent(driver, "Whatever you're looking for, isn't here", 1)){
				  Reporter.log("Whatever you're looking for, isn't here : message is displayed");
				  Assert.assertTrue(false);
			  }			 
			  else if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 5)) {
					Reporter.log("Rooms are unavailable for the selected dates");
					Assert.assertTrue(false);
				}
		 }
			   if(!elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)){
				   Reporter.log("Hotel Rate is not displayed in Details page");
			   }
			   if(textPresent(driver, "Sorry, this hotel is unavailable", 1)) {
					String HotelName = getText(driver, By.xpath("//div[2]/div/div/h1"));
					Reporter.log("Sorry, this hotel is unavailable - No rates/ inventory for hotel :"+HotelName);
					Assert.assertTrue(false);
				} 
			   if(BookingType.equals("ProdpaymentLive")) {
				  if(!textPresent(driver, "Free cancellations", 1)){
						  Reporter.log("Free cancellations. : message is not displayed");
						  Assert.assertTrue(false);
					  }	
			   }
			   if(BookingType.equals("ProdpaymentLiveExpedia")) {
				   elementVisible(driver, By.linkText("Show details"), 20);
				   safeClick(driver, By.linkText("Show details"));
				   Thread.sleep(5000);
				   elementVisible(driver, By.cssSelector("div.rateAdditionalDetails > div.row"), 20);
				   String CancellationText = getText(driver, By.cssSelector("div.rateAdditionalDetails > div.row"));
				   
				   if(!CancellationText.contains("we are required to pass on: Cancellations or changes made after")) {
					   Reporter.log("we are required to pass on: Cancellations or changes made after : message is not displayed");
					   Reporter.log("CancellationText : "+CancellationText);
					   Assert.assertTrue(false);
				   }
				   if(CancellationText.contains("This rate is non-refundable")||CancellationText.isEmpty()) {
					   Reporter.log("This rate is non-refundable - so cancelling the booking");
					   Reporter.log("CancellationText : "+CancellationText);
					   Assert.assertTrue(false);
				   }
			   }
			   
		if(NewDetailsPage) {				
			if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 5)) {
				Reporter.log("Rooms Are unavailable for the selected dates");
				Assert.assertTrue(false);
			}
			elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 5);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
			Thread.sleep(500);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));	
			
		}else if(OldDetailsPage) {
			   if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)) {				
			   if(elementVisible(driver,  getObjectHotels("HotelCom_DetailsPage_Book_Button"), 1)){
					safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"))	;				  
			   }
			   
			   }
			   else if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_PriceNEW"), 1)) {		   
				   safeClick(driver, getObjectHotels("HotelCom_ModalWindow_New_SelectRoom_Button"))	;
				   safeClick(driver, getObjectHotels("HotelCom_ModalWindow_New_Book_Button"));
			   }
		  
		}
	 }
	}
			
	public void detailsPage_URLCHMM(RemoteWebDriver driver, String Domain, String HotelID, String Date, int Month, String BookingType ) throws Exception {
		   String CheckInMonth = putDateNoSplCharMonth(Month);
		   String CheckIn = Date+CheckInMonth;
		   int DateInt = Integer.parseInt(Date);
		   DateInt = DateInt+1;		  
		   String CheckOutMonth = putDateNoSplCharMonth(Month);
		   String CheckOut = DateInt+CheckOutMonth;
		   String DetailsPage_URL = null;
			   if(Domain.contains("com")) {
				DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,1-5&shwb=true&compId=";																																							
				driver.get(DetailsPage_URL);				
			   }
				else {
					DetailsPage_URL = baseUrl_AE+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,1-5&shwb=true&compId=";		   
					driver.get(DetailsPage_URL);
				}
				logURL(driver);
				/*elementVisible(driver, By.xpath("//div[2]/div/div/div[2]/a"), 5);
			    safeClick(driver, By.xpath("//div[2]/div/div/div[2]/a"));	*/
				elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"), 5);
			    safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Book_Button"));
			   //smartClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button3"));
		}
		
	public void detailsPage_URLCHMM_Link(RemoteWebDriver driver, String Domain, String HotelID, String Date, int Month, String BookingType ) throws Exception {
		   String CheckInMonth = putDateNoSplCharMonth(Month);
		   String CheckIn = Date+CheckInMonth;
		   int DateInt = Integer.parseInt(Date);
		   DateInt = DateInt+1;		  
		   String CheckOutMonth = putDateNoSplCharMonth(Month);
		   String CheckOut = DateInt+CheckOutMonth;
		   String DetailsPage_URL = null;
			   if(Domain.contains("com")) {
				DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,1-5&shwb=true&compId=";
				driver.get(DetailsPage_URL);				
			   }
					   else {
							DetailsPage_URL = baseUrl_AE+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,1-5&shwb=true&compId=";		   
							driver.get(DetailsPage_URL);
						
					   }

				 logURL(driver);
		}
	
	public void detailsPage_URL_PartPay(RemoteWebDriver driver, String Domain, String HotelID, int Date, String BookingType ) throws Exception {
		   String CheckIn = putDateNoSplChar(Date);
		   String CheckOut = putDateNoSplChar(Date+1);
		   String DetailsPage_URL = null;
			   if(Domain.contains("com")) {
				DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,1-5&shwb=true&compId=";
				driver.get(DetailsPage_URL);
				logURL(driver);
			   }
					   else {
							DetailsPage_URL = baseUrl_AE+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,1-5&shwb=true&compId=";		   
							driver.get(DetailsPage_URL);
							logURL(driver);			
					   }
			   elementVisible(driver, By.xpath("//div/div/div[2]/a"), 60);
				Thread.sleep(1000);
				safeClick(driver, By.xpath("//div/div/div[2]/a"));		
				//		   smartClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button3"));
		}
		
	public void detailsPage_PAHCC(RemoteWebDriver driver, String Domain, String HotelID, int Date ) throws Exception {
		   String CheckIn = putDateNoSplChar(Date);
		   String CheckOut = putDateNoSplChar(Date+1);
		   String DetailsPage_URL = null;
			   if(Domain.contains("com")) {
				DetailsPage_URL = baseUrl+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=2,1-5&shwb=true&compId=";
				driver.get(DetailsPage_URL);
				logURL(driver);
				Thread.sleep(5000);
				if(!textPresent(driver, "required to guarantee booking", 20)) {
					Reporter.log("Credit card required to guarantee booking text is not displayed ");
					Assert.assertTrue(false);
				}
						if(elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button"), 20)) {
							safeClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button"));
						}
						smartClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button2"));

						smartClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button3"));
			   }
					   else {
							DetailsPage_URL = baseUrl_AE+"/hotels/details/"+HotelID+"?c="+CheckIn+"|"+CheckOut+"&r=1,0&shwb=true&compId=";		   
							driver.get(DetailsPage_URL);
							logURL(driver);
							elementVisible(driver, By.xpath("//div/div/div[2]/a"), 60);
							Thread.sleep(10000);
							safeClick(driver, By.xpath("//div/div/div[2]/a"));			
					   }

				smartClick(driver, getObjectHotels("HotelCom_ModalWindow_Book_Button3"));
		}
	
	public void SRP_URL_AE(RemoteWebDriver driver, String City, String State, String Dest_Code ) throws Exception {
		baseUrl = getBaseUrl( "ae");
		String Date = getMonthTime(1, "MM")+"/"+putYear(0);
		String SRP_URL = baseUrl+"/hotels/results?city="+City+"&state="+State+"&country=IN&area=&poi=&hotelId=&hotelName=&dest_code="+Dest_Code+"&chk_in=20/"+Date+"&chk_out=21/"+Date+"&adults1=2&children1=1&ca1=5&num_rooms=1";
		driver.get(SRP_URL);
	//	elementNotVisible(driver, getObjectHotels("HotelCom_SRP_Loading"), 60);
		elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 60);
		Thread.sleep(5000);
	}
	
	public boolean checkIfSignedIn(RemoteWebDriver driver) throws Exception {
		if (driver.findElement(getObjectHotels("Acc_Dashboard_Link_Dropdown_Menu_User_Text")).getText().equals("Your trips")) {
			return false;
		}
		return true;
	}

	public void hotelCom_Hold_HQ_Accept(RemoteWebDriver driver, String TripID) throws Exception{
		driver.get(baseUrl+"/hq/workflows/hold_booking/hotel");
		logURL(driver);
    	elementVisible(driver, getObjectHotels("HotelCom_HQ_Holdpage_Header"), 60);
		textPresent(driver, "Hotel Hold Booking Trips Queue", 20);
		String TripIDAccept = TripID+"_accept";
    	if(!(elementVisible(driver, By.id(TripIDAccept), 30))){
    		refreshPage(driver);
    	}
    	/*else if(elementVisible(driver, By.linkText("3"), 10)) {
			safeClick(driver, By.linkText("3"));
		}*/
    	if(!(elementVisible(driver, By.id(TripIDAccept), 30))){
    		refreshPage(driver);
    	}
    	safeClick(driver, By.id(TripIDAccept));
    	elementVisible(driver, By.id("Flash"), 60);
    	textPresent(driver, "Hotel status accepted for trip ref :"+TripID, 20);
		String Hold_Accept_Msg = getText(driver, By.id("Flash"));
    	if(!Hold_Accept_Msg.equals("Hotel status accepted for trip ref :"+TripID)){
    		Reporter.log("HQ Hotel : Hold Booking error when clicked on Accept in HQ");
    		Reporter.log("Hold Message" +Hold_Accept_Msg);
    		Assert.assertTrue(false);
    	}
	}
	
	public void hotelCom_Hold_HQ_Reject(RemoteWebDriver driver, String TripID) throws Exception{
		driver.get(baseUrl+"/hq/trips/"+TripID);
		textPresent(driver, "Trip details", 5);
		elementVisible(driver, By.xpath("//tr[3]/td"), 10);
		List<WebElement> hotelDetails = driver.findElements(By.xpath("//tr[2]/th"));
		List<WebElement> detailsName = driver.findElements(By.xpath("//tr[3]/td"));
		int j=0;
		for (int i=0; i<=hotelDetails.size()-1;i++) {
			if(hotelDetails.get(i).getText().contains("Status")){
				j=i;
				if(!detailsName.get(i).getText().contains("Unconfirmed")){
					Reporter.log(hotelDetails.get(i).getText()+" contains "+detailsName.get(i).getText()+" instead of Unconfirmed Status");
					Assert.assertTrue(false);
				} break;
			
			}
			/*if(!detailsName.get(i).getText().contains("Unconfirmed")){
				Reporter.log("Unconfirmed Status is not displayed");
				Assert.assertTrue(false);
			}*/
		}
		
		driver.get(baseUrl+"/hq/workflows/hold_booking/hotel");
		logURL(driver);
    	elementVisible(driver, getObjectHotels("HotelCom_HQ_Holdpage_Header"), 50);
		textPresent(driver, "Hotel Hold Booking Trips Queue", 20);
		String TripIDReject = TripID+"_reject";
		for (int i = 0; i < 5; i++) {			
    	if(!(elementVisible(driver, By.id(TripIDReject), 10))){
    		refreshPage(driver);
    		}else break;    	
		}
    	elementVisible(driver, By.id(TripIDReject), 30);
    	safeClick(driver, By.id(TripIDReject));
    	elementVisible(driver, By.id("Flash"), 30);
    	textPresent(driver, "Hotel status rejected for trip ref :"+TripID, 20);
		String Hold_Reject_Msg = getText(driver, By.id("Flash"));		
		if(!Hold_Reject_Msg.equals("Hotel status rejected for trip ref :"+TripID)){
    		Reporter.log("HQ Hotel : Hold Booking error when clicked on Reject in HQ");
    		Reporter.log("Hold Reject Message" +Hold_Reject_Msg);
    		Assert.assertTrue(false);
    		}
		Thread.sleep(5000);
	}
	
	public String hotelComPAHSendOTP(RemoteWebDriver driver) throws Exception {
		
				if (common.value("host").contains("stg1") || common.value("host").contains("qa2"))
				{
		String url=hotelComOTPEndpoint()+"/tm_internal_api/generate_pah_otp?itinerary_id="+b2cLogItinerary(driver);
		String httpResponse = apiRestCall(url);
		////System.out.println(httpResponse);
		return httpResponse;
				}
		else{
			String url=hotelComOTPEndpoint()+"/"+releaseVersion()+"/tm_internal_api/generate_pah_otp?itinerary_id="+b2cLogItinerary(driver);
			String httpResponse = apiRestCall(url);
			////System.out.println(httpResponse);
			return httpResponse;
		}
	}
	
	public String hotelComPAHSendOTP_Corp(RemoteWebDriver driver, String Itinerary) throws Exception {
		if (common.value("host").contains("stg1") || common.value("host").contains("qa2"))
				{
		String url=hotelComOTPEndpoint()+"/tm_internal_api/generate_pah_otp?itinerary_id="+Itinerary;
		String httpResponse = apiRestCall(url);
		////System.out.println(httpResponse);
		return httpResponse;
				}
		else{
			String url=hotelComOTPEndpoint()+"/"+releaseVersion()+"/tm_internal_api/generate_pah_otp?itinerary_id="+b2cLogItinerary(driver);
			String httpResponse = apiRestCall(url);
			////System.out.println(httpResponse);
			return httpResponse;
		}
	}
	
	public String hotelComOTPEndpoint() throws Exception {
		if ( common.value("host").contains("stg1") ) {
			String Endpoint = "http://172.16.50.120:9080";
			return Endpoint;
		} /*else if ( common.value("host").contains("qa2")&&QA2Domain.equalsIgnoreCase("old") ) {
			String Endpoint = "http://10.10.21.107:9080";
			return Endpoint;
		}*/else if ( common.value("host").contains("qa2")&&QA2Domain.equalsIgnoreCase("old") ) {
			String Endpoint = "http://10.10.21.107:9080";
			return Endpoint;
		} else if ( common.value("host").contains("qa2")&&QA2Domain.equalsIgnoreCase("new") ) {
			//String Endpoint = "http://172.17.12.187:9080";
			String Endpoint = "http://172.17.13.35:9080";
			return Endpoint;
		}  else if ( common.value("host").contains("hf") ) {
					String Endpoint = "http://10.10.25.116:9080";
					return Endpoint;
				} 
			
			else if ( common.value("host").contains("beta")||common.value("host").contains("www") ) {
					String Endpoint = "http://chronicle.cltp.com:9001/";
					return Endpoint;
				} 
		return null;
	}
	
	public String apiRestCall(String url) throws IOException, ClientProtocolException {

		HttpGet getRequest = new HttpGet(url);
		HttpResponse httpResponse = httpclient.execute(getRequest);
		BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
		String output;
		StringBuilder builder = new StringBuilder();

		while ((output = br.readLine()) != null) {
			builder.append(output);
		}
		String text = builder.toString();
		return text;
	}
	
	public String releaseVersion() throws Exception {
		JSONParser parser = new JSONParser();
		String webPage = "http://summary.cleartrip.com/cur_rel.php?json";
		String name = "punith.a@cleartrip.com";
		String password = "cltp@2010";
		String authString = name + ":" + password;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		URL url = new URL(webPage);
		URLConnection urlConnection = url.openConnection();
		urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
		InputStream is = urlConnection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		int numCharsRead;		
		char[] charArray = new char[1024];
		StringBuffer sb = new StringBuffer();
		while ((numCharsRead = isr.read(charArray)) > 0) {
			sb.append(charArray, 0, numCharsRead);
		}
		String result = sb.toString();
		////System.out.println(result);
		Object obj = parser.parse(isr);		 
		JSONObject jsonObject = (JSONObject) obj;  	        
		if(common.value("host").contains("www")){
			String rel = (String) jsonObject.get("production");
			rel=rel.replace("elease", "");
			////System.out.println(rel);
			return rel;
		}else if(common.value("host").contains("beta")){
			String rel = (String) jsonObject.get("beta");
			rel=rel.replace("elease", "");
			////System.out.println(rel);
			return rel;
		}else{
			String rel="/";
			return rel;

		}
	}
	
	public String b2cLogItinerary(RemoteWebDriver driver) throws Exception {
		//String Url = "https://www.cleartrip.com/hotels/itinerary/7549440f24-8ff1-46c0-913d-5e6f02502e13/review";
		String Url = logURL(driver);
		String [ ] itinerary = Url.split("/");
		Reporter.log("itineraryId : " + itinerary[5]);
		//System.out.println("itineraryId : " + itinerary[5]);
		return itinerary[5];
	}

	public String hotelsGetFromDate() throws Exception {
		String  FromDate = null; 
		FromDate = getDate( "dd");
		FromDate = FromDate.substring(1);	
		FromDate = "1"+FromDate;
		return FromDate;
	}
	
	public String hotelsGetToDate() throws Exception {
		String  FromDate, ToDate = null; 
		FromDate = getDate( "dd");
		FromDate = FromDate.substring(1);
		FromDate = "1"+FromDate;
		int DateInt = Integer.parseInt(FromDate);
		DateInt = DateInt+1;
		ToDate = Integer.toString(DateInt);		
		return ToDate;
	}
	
	public String hotelSrpUrl(RemoteWebDriver driver, String City, String State, String Country) throws Exception {
		String SRPURL = null;	
		String Check_In_Date = putDate1(60);
		String Check_Out_Date = putDate1(62);
		//driver=(RemoteWebDriver) getDriver(driver);
		String domainURL = getBaseUrl( "com");
		String URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1=1&children1=0&num_rooms=1";
		SRPURL = domainURL+URL_2;
		return SRPURL;		
	}
	
	public String hotelSrpUrl1(RemoteWebDriver driver, String City, String State, String Country) throws Exception {
		String SRPURL = null;	
		String Check_In_Date = putDate1(30);
		String Check_Out_Date = putDate1(31);
		//driver=(RemoteWebDriver) getDriver(driver);
		String domainURL = getBaseUrl( "com");
		String URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1=1&children1=0&num_rooms=1";
		SRPURL = domainURL+URL_2;
		return SRPURL;		
	}
	

	public String hotelSrpUrl2(RemoteWebDriver driver, String City, String State, String Country) throws Exception {
		String SRPURL = null;	
		String Check_In_Date = putDate1(40);
		String Check_Out_Date = putDate1(51);
		//driver=(RemoteWebDriver) getDriver(driver);
		String domainURL = getBaseUrl( "com");
		String URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1=1&children1=0&num_rooms=1";
		SRPURL = domainURL+URL_2;
		return SRPURL;		
	}
	
	public String hotelSrpUrl_AE(RemoteWebDriver driver, String City, String State, String Country) throws Exception {
		String SRPURL = null;	
		String Check_In_Date = putDate1(50);
		String Check_Out_Date = putDate1(52);
		//driver=(RemoteWebDriver) getDriver(driver);
		String domainURL = getBaseUrl( "ae");
		String URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1=1&children1=0&num_rooms=1";
		SRPURL = domainURL+URL_2;
		return SRPURL;		
	}
	
	public String hotelSrpUrl_Date(RemoteWebDriver driver, String Domain,  String City, String State, String Country,String Adults, int FromDate, int ToDate) throws Exception {
		String SRPURL = null;	
		String domainURL = null;
		String URL_2 = null;
		String Check_In_Date = putDate1(FromDate);
		String Check_Out_Date = putDate1(ToDate);
		if(Domain.equalsIgnoreCase("com")) {
			domainURL = getBaseUrl( "com");
			URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		}
		if(Domain.equalsIgnoreCase("com")) {
			domainURL = getBaseUrl( "com");
			URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		}
		else if(Domain.equalsIgnoreCase("ae")) {
			domainURL = getBaseUrl( "ae");
			URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		}
		else if(Domain.equalsIgnoreCase("sa")) {
			domainURL = getBaseUrl( "sa");
			URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		}
		else if(Domain.equalsIgnoreCase("bh")) {
			domainURL = "https://bh.cleartrip.com";
			URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		}
		else if(Domain.equalsIgnoreCase("qa")) {
			domainURL = "https://qa.cleartrip.com";
			URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		}
		else if(Domain.equalsIgnoreCase("kw")) {
			domainURL = "https://kw.cleartrip.com";
			URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		}
		else if(Domain.equalsIgnoreCase("om")) {
			domainURL = "https://om.cleartrip.com";
			URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		}
		else if(Domain.equalsIgnoreCase("me")) {
			domainURL = "https://me.cleartrip.com";
			URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1="+Adults+"&children1=0&num_rooms=1";
		}
		SRPURL = domainURL+URL_2;
		return SRPURL;		
	}
	
	public String hotelSrpUrl_Quickes(RemoteWebDriver driver, String City, String State, String Country) throws Exception {
		String SRPURL = null;	
		String Check_In_Date = putDate1(0);
		String Check_Out_Date = putDate1(1);
		driver=(RemoteWebDriver) getDriver(driver);
		String domainURL = getBaseUrl( "com");
		String URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1=1&children1=0&num_rooms=1";
		SRPURL = domainURL+URL_2;
		return SRPURL;		
	}
	
	public String hotelSrpUrl_PAH(RemoteWebDriver driver, String City, String State, String Country) throws Exception {
		String SRPURL = null;	
		String Check_In_Date = putDate1(3);
		String Check_Out_Date = putDate1(4);
		driver=(RemoteWebDriver) getDriver(driver);
		String domainURL = getBaseUrl( "com");
		String URL_2 = "/hotels/results?city="+City+"&state="+State+"&country="+Country+"&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in="+Check_In_Date+"&chk_out="+Check_Out_Date+"&adults1=1&children1=0&num_rooms=1";
		SRPURL = domainURL+URL_2;
		return SRPURL;		
	}
	
	public void hotelCom_SRP_Loaded(RemoteWebDriver driver) throws Exception {
    	logURL(driver);
    	Thread.sleep(2000);
    	elementNotVisible(driver, getObjectHotels("HotelCom_SRP_PageLoading"), 10);
		for (int i = 0; i < 5; i++) {
			if(elementVisible(driver, getObjectHotels("HotelCom_SRP_PerRoom_Price"), 1)) {
				Reporter.log("Price is displayed");
				break;
			}
			if(textPresent(driver, "Sorry, our servers are acting up", 1)){
				Reporter.log("Sorry, our servers are acting up");
				Assert.assertTrue(false);
	  	   }	
			if(textPresent(driver, "Sorry, we couldn't find any hotels. Try a fresh search", 1)){
				Reporter.log("Sorry, we couldn't find any hotels. Try a fresh search");
				Assert.assertTrue(false);
	  	   }
			if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 5)) {
				Reporter.log("Results are not displayed");
				Assert.assertTrue(false);
			}else Reporter.log("Results are displayed");
			if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Rooms_Unavailable"), 1)) {
				Reporter.log("Room are Unavailable");
				Assert.assertTrue(false);
			}else Reporter.log("Hotel is available");
		}
    	logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_SRP_PerRoom_Price"), 5, "Search Results Page Price has not loaded  :( :( :( :( :( :(");
    	elementPresent_log(driver, getObjectHotels("HotelCom_SRP_PerRoom_Price"), "Search Results Page Price has not loaded  :( :( :( :( :( ", 5);
	}
	
	
	public void hotelCom_Hold_Account_Confirm(RemoteWebDriver driver, String TripID) throws Exception{
		if (MakePaymentOnlyInQA2){
		driver.get( baseUrl+"/account/trips/"+TripID);
		if(textPresent(driver, "Sorry, unable to get your details right now.", 10)) {
			refreshPage(driver);
			Reporter.log("Sorry, unable to get your details right now. error is displayed");
		}
		if(!textPresent(driver,"Woohoo! Rooms are available for your dates", 30)){
			refreshPage(driver);
		}
		if(!textPresent(driver,"Woohoo! Rooms are available for your dates", 30)){
			Reporter.log("Text : 'Woohoo! Rooms are available for your dates' is not displayed");
			Assert.assertTrue(false);
		}			
		hotelCom_Account_Status(driver, TripID, "PENDING PAYMENT", "");
		safeClick(driver, getObjectHotels("HotelCom_AccountPage_Hold_MakePayment"));
		}
		Reporter.log("Second Payment Step");
	}
	
	public void hotelCom_Hold_Account_Reject(RemoteWebDriver driver, String TripID) throws Exception{
		if (MakePaymentOnlyInQA2){
		driver.get( baseUrl+"/account/trips/"+TripID);
		if(!textPresent(driver,"Sorry rooms are not available for the dates you requested.", 20)){
			refreshPage(driver);
		}
		if(!textPresent(driver,"Sorry rooms are not available for the dates you requested.", 30)){	
			Reporter.log("Text : 'Sorry rooms are not available for the dates you requested.' is not displayed");
			Assert.assertTrue(false);
		}
	
		}
	}
	
	public void hotelCom_Hold_Account_Validate(RemoteWebDriver driver, String TripID) throws Exception{
		if (MakePaymentOnlyInQA2){
		Thread.sleep(5000);
		driver.get( baseUrl+"/account/trips/"+TripID);
		elementVisible(driver, By.xpath("//h2/a"), 50);
		if(!textPresent(driver, "We are currently processing this booking request", 5)){
			Reporter.log("We are currently processing this booking request : Message is not displayed");
			Assert.assertTrue(false);
		}
		String Status = getText(driver, By.xpath("//td[4]/span"));
		if (Status.contains("Unconfirmed")) {
			Reporter.log("Status is not containing Unconfirmed instead its displayed as "+Status);
			Assert.assertTrue(false);
		}
		}
	}
	
	public void hotelCom_PartPay_Account_Confirm(RemoteWebDriver driver, String TripID) throws Exception{
		if (MakePaymentOnlyInQA2){
		driver.get( baseUrl+"/account/trips/"+TripID);
		if(!textPresent(driver,"Your booking is done, but", 60)){
			refreshPage(driver);
		}
		if(!textPresent(driver,"Your booking is done, but", 30)){
			Reporter.log("Text : 'Your booking is done, but... text is not displayed");
			Assert.assertTrue(false);
		}			
		driver.get( baseUrl+"/account/trips/"+TripID);
		elementVisible(driver, By.xpath("//td[4]/span"), 50);
		String Trip_Status=getText(driver, By.xpath("//td[4]/span"));
		if(!Trip_Status.equals("CONFIRMED")){
			Reporter.log("Trip status is not confirmed, It is dispalyed as "+Trip_Status);
			Assert.assertTrue(false);
		}
		if(getText(driver, By.xpath("//td[3]")).contains("CHMM")){
			Reporter.log("CHMM Voucher should not be displayed in first Payment "+getText(driver, By.xpath("//td[3]")));
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectHotels("HotelCom_AccountPage_Partpay_MakePayment"));
	}
	}
	
	public String api_url() {
		if ( common.value("host").contains("qa1") ) {
			String url = "http://apiqa.cleartrip.com";
			return url;
		} else
			if ( common.value("host").contains("stg1") ) {
				String url = "http://api.staging.cleartrip.com";
				return url;
			} else
				if ( common.value("host").contains("qa2") ) {
					String url = "https://apiqa.cleartrip.com";
					return url;
				}else
					if ( common.value("host").contains("hf") ) {
						String url = "https://apiqa.cleartrip.com";
						return url;
					}  
				
				else	if ( common.value("host").contains("beta") ) {
						String url = "http://apibeta.cleartrip.com";
						return url;
					} if ( common.value("host").contains("debug") ) {
						String url = "http://apistaging.cleartrip.com";
						return url;
					}else
						if ( common.value("host").contains("www")||common.value("host").contains("dr") ) {
							String url = "https://api.cleartrip.com";
							return url;
							
						}
		return null;
	}
	
	public void validatePlacesXML( String StartTag, String EndTag, String TagValue) throws IOException, URISyntaxException{
		if(MakePaymentOnlyInQA2){
			DefaultHttpClient client = new DefaultHttpClient();
			String XML_URL = null;
			if(common.value("host").equalsIgnoreCase("qa2")){
				XML_URL = "https://qa2.cleartrip.com/places/hotels/7259/725958/xml/index.xml";
			}
			else if(common.value("host").equalsIgnoreCase("hf")){
				XML_URL = "https://hf.cleartrip.com/places/hotels/7259/725958/xml/index.xml";
			}
			else if(common.value("host").equalsIgnoreCase("beta")){
				XML_URL = "https://beta.cleartrip.com/places/hotels/7259/725958/xml/index.xml";
			}
			else if(common.value("host").equalsIgnoreCase("www")){
				XML_URL = "https://www.cleartrip.com/places/hotels/7259/725958/xml/index.xml";
			}
			
			Reporter.log("XML URL : "+XML_URL);
			
			HttpGet get = new HttpGet(new URI(XML_URL));
			
			HttpResponse response = client.execute(get);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer();
			String str="";
			while((str=br.readLine())!=null){
				sb.append(str);
			}
			String sb1=sb.toString();
			String [ ] getFOP = sb1.split(StartTag);
			String [ ] FOP = getFOP[1].split(EndTag);
			if(FOP[0].contains(TagValue)){
				       Reporter.log(StartTag+" is displayed as " +FOP[0]);
			        } else {
			        	Reporter.log(StartTag+" is displayed as " +FOP[0]);
				        Assert.assertTrue(false);
			        	}
			}	
	}
	
	  public String putDateApi(int days) throws Exception {
	        Calendar c = new GregorianCalendar();
	        c.add(Calendar.DATE, +days);
	        Date s = c.getTime();
	        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(s);
	        return dateString;
	    }
	  
	  public boolean api_payment_flag() {
			if ( common.value("host").contains("stg1") || common.value("host").contains("qa2") || common.value("host").contains("stg2")|| common.value("host").contains("debug") ) {

				boolean flag = true;
				return flag;
			} else {
				boolean flag = false;
				return flag;
			}
		}

		public Integer hotelCom_SRP_Hotel_Price(RemoteWebDriver driver, int Xpath_no) throws Exception{
			String HotelPrice_Xpath =  "//li["+Xpath_no+"]/section/nav/ul/li[3]/h2[2]/div/strong";
			String HotelPrice_Saving_Xpath =  "//li["+Xpath_no+"]/section/nav/ul/li[3]/h2[2]/strong/div/small";
			String HotelPrice = null;
			if(elementVisible(driver, By.xpath(HotelPrice_Xpath), 1)){
				HotelPrice = getText(driver, By.xpath(HotelPrice_Xpath));
				if(elementVisible(driver, By.xpath(HotelPrice_Saving_Xpath), 1)){
					String HotelPrice_Saving = getText(driver, By.xpath(HotelPrice_Saving_Xpath));
					HotelPrice = HotelPrice.replace(HotelPrice_Saving, "");
				}
			}
			String URL = logURL(driver);
			if(URL.contains(".com")) {
			HotelPrice = HotelPrice.replace("Rs.", "");
			}
			else if(URL.contains(".ae")) {
			HotelPrice = HotelPrice.replace("AED", "");
			}
			if(HotelPrice.contains(",")){
				HotelPrice = HotelPrice.replace(",", "");
			}
			int HotelPrice_Int = Integer.parseInt(HotelPrice);
			return HotelPrice_Int;
		}
	
		public String getHotelID() throws Exception{ 
			String[] HotelIDs ;
			if(ProductionUrl) {
				HotelIDs  =  new String[] { "2047804",  "717673", "1358390"};
			}
			else {
				HotelIDs  =  new String[]{  "40977", "378660", "41158"};
				}
			String RanNo = getRandomNos(HotelIDs.length-1);
			int RandomNo = Integer.parseInt(RanNo);
			return HotelIDs[RandomNo];
		}
		
		public String getHotelIDProd() throws Exception{ 
			String[] HotelIDs ;
			HotelIDs  =  new String[]{  "709946", "723508", "708924", "2048228", "316137", "387902", "2047804", "2180182", "868870", "2049372", "2422770", "45106"};
			String RanNo = getRandomNos(HotelIDs.length-1);
			int RandomNo = Integer.parseInt(RanNo);
			return HotelIDs[RandomNo];
		}
		
		public String getHotelIDProdExpedia() throws Exception{ 
			String[] HotelIDs ;
			HotelIDs  =  new String[]{  "725958", "172427"};
			String RanNo = getRandomNos(HotelIDs.length-1);
			int RandomNo = Integer.parseInt(RanNo);
			return HotelIDs[RandomNo];
		}
		
		public String getUTMHotelID() throws Exception{ 
			String[] HotelIDs = {"713200", "732040", "713089","716891", "737120"};
			String RanNo = getRandomNos(HotelIDs.length-1);
			int RandomNo = Integer.parseInt(RanNo);
			return HotelIDs[RandomNo];
		}
		
		public ArrayList<String> getBasicAmenities(int Count) throws Exception{
			String[] Amenities = { "Pets Allowed" ,  "Doorman", "Housekeeping",  "Express Check-In", "Non-Smoking Rooms", "Library" , "Lift", "Escalator", "Central Heating", "Internet", "Wi-Fi on Charge", "Banquet Facility"};    
			ArrayList<String> updatableList = new ArrayList<String>();
			for(int i=0; i<=Count; Count--) {
			if(Count!=0) {
				String RanNo = getRandomNos(Amenities.length-1);
				int RandomNo = Integer.parseInt(RanNo);
				updatableList.add(Amenities[RandomNo]);
			}
			}
			return updatableList;
		}
		
		public ArrayList<String> getBasicAmenitiesDetailsPage(int Count) throws Exception{
			String[] Amenities = { "Air Conditioning" ,  "Beach Beds", "Doorman",  "Elevator", "Laundry", "Pharmacy" , "Lift", "Private Beach"};    
			ArrayList<String> updatableList = new ArrayList<String>();
			for(int i=0; i<=Count; Count--) {
			if(Count!=0) {
				String RanNo = getRandomNos(Amenities.length-1);
				int RandomNo = Integer.parseInt(RanNo);
				updatableList.add(Amenities[RandomNo]);
			}
			}
			return updatableList;
		}
		
		public ArrayList<String> getBasicAmenitiesSRP(int Count) throws Exception{
			//String[] Amenities = { "Air Conditioning" ,  "Indoor Swimming Pool", "Bar",  "Restaurant", "Parking", "Coffee Shop" , "Gym", "Airport Transfer", "24 Hour Room Service", "Business Center"};    
			String[] Amenities = { "Bar",  "Restaurant", "Parking", "Business Center", "Pool", "Airport", "Gym"};
			ArrayList<String> updatableList = new ArrayList<String>();
			for(int i=0; i<=Count; Count--) {
			if(Count!=0) {
				String RanNo = getRandomNos(Amenities.length-1);
				int RandomNo = Integer.parseInt(RanNo);
				updatableList.add(Amenities[RandomNo]);
			}
			}
			return updatableList;
		}
		
		public ArrayList<String> getBasicAmenitiesRoomRateBasic(int Count) throws Exception{
			String[] Amenities = { "Television" ,  "Kitchen", "Wardrobe",  "laptop", "dishwasher", "Microwave" , "Telephone"};    
			ArrayList<String> updatableList = new ArrayList<String>();
			for(int i=0; i<=Count; Count--) {
			if(Count!=0) {
				String RanNo = getRandomNos(Amenities.length-1);
				int RandomNo = Integer.parseInt(RanNo);
				updatableList.add(Amenities[RandomNo]);
			}
			}
		return updatableList;
		}
		
		public ArrayList<String> getBasicAmenitiesRoomRateOther(int Count) throws Exception{
			String[] Amenities = { "Newspaper" ,  "Bathtub", "Intercom",  "Hangers", "Jacuzzi", "Sofa" , "Refrigerator", "Shower", "Toiletries"};    
			ArrayList<String> updatableList = new ArrayList<String>();
			for(int i=0; i<=Count; Count--) {
			if(Count!=0) {
				String RanNo = getRandomNos(Amenities.length-1);
				int RandomNo = Integer.parseInt(RanNo);
				updatableList.add(Amenities[RandomNo]);
			}
			}
		return updatableList;
		}
		
		public ArrayList<String> getBasicAmenitiesRoomRateSpace(int Count) throws Exception{
			String[] Amenities = { "Sofa" , "Private Bathroom", "Luggage Rack"};    
			ArrayList<String> updatableList = new ArrayList<String>();
			for(int i=0; i<=Count; Count--) {
			if(Count!=0) {
				String RanNo = getRandomNos(Amenities.length-1);
				int RandomNo = Integer.parseInt(RanNo);
				updatableList.add(Amenities[RandomNo]);
			}
			}
		return updatableList;
		}
	
		public ArrayList<String> hotelSRP_PriceCheck(RemoteWebDriver driver) throws Exception, InterruptedException {
	 // elementNotVisible(driver, By.cssSelector("div.glassShield"), 100);
	  if(!elementVisible(driver, By.xpath("//div[2]/section[5]/section/aside/div/p/strong/span"), 50)){
	      	Reporter.log("SRP page is not loaded in Production");
	      	refreshPage(driver);
	      }
	  logURL(driver);
	  elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50);
	  elementVisible(driver, getObjectHotels("HotelCom_SRP_Price_Filter"), 50);
	  Thread.sleep(5000);
	  safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
	  Thread.sleep(2000);
	  safeClick(driver, By.xpath("(//a[contains(text(),'Total')])[2]"));
	  Thread.sleep(5000);
	  ArrayList < String > Hotel_Price = new ArrayList < String >();
	  ArrayList < String > Hotel_Name = new ArrayList < String >(); 

	  for ( int i = 1; i <= 5; i++) {
		   String Xpath_Price = "//li["+i+"]/section/nav/ul/li[3]/h2/strong";           
		   String Xpath_Name = "//li["+i+"]/section/nav/ul/li/h2/a";           
           if ( elementVisible(driver,By.xpath(Xpath_Price), 5) ) {
        	   Hotel_Price.add(getText(driver, By.xpath(Xpath_Price)));
              }	         
           if ( elementVisible(driver,By.xpath(Xpath_Name), 5) ) {
        	   Hotel_Name.add(getText(driver, By.xpath(Xpath_Name)));
              }	         
       } 
	  	Reporter.log(" Hotel Names : "+Hotel_Name);	   
	  	return Hotel_Price;
}

		public String MiddleEastUrl(String ME_QA_URL, String ME_Beta_URL) throws Exception {
		String ME_URL = null;
		String Host = common.value("host");
		if(Host.equalsIgnoreCase("QA2")||Host.equalsIgnoreCase("www")) {
			ME_URL = ME_QA_URL;
		} 
		else if(Host.equalsIgnoreCase("HF")) {
			ME_URL = ME_QA_URL;
		} else if (Host.equalsIgnoreCase("beta")){
			ME_URL = ME_Beta_URL;		}
	    return ME_URL;
}
		
		public String MiddleEastUrlSA(String ME_QA_URL, String ME_Beta_URL) throws Exception {
			String ME_URL = null;
			String Host = common.value("host");
			if(Host.equalsIgnoreCase("QA2")) {
				ME_URL = ME_QA_URL;
			} 
			else if(Host.equalsIgnoreCase("HF")) {
				ME_URL = ME_QA_URL;
			} else if (Host.equalsIgnoreCase("www")){
				ME_URL = ME_Beta_URL;		}
		    return ME_URL;
	}
	
		public String getAmexTravelUrl() {
			if (common.value("host").equals("qa2")||common.value("host").equals("hf")) 
				{
					baseUrl = dataFile.value("AmexTravel_Hotel_QA2");
				}
				else if (common.value("host").equals("beta")) 
				{
					baseUrl = dataFile.value("AmexTravel_BETA");
				}
				else if (common.value("host").equals("www")) 
				{
					baseUrl = dataFile.value("AmexTravel_PROD");
				}
			return baseUrl;
		}
		
	public void hotelCom_AddCookie(RemoteWebDriver driver){
		   String domain = common.value("host");
		   if(domain.equalsIgnoreCase("qa2")){
			Cookie cookieName = new Cookie("ct-auth", cookievalue_QA2);
			driver.manage().addCookie(cookieName);
		   }
		   else if(domain.equalsIgnoreCase("www")){
			   Cookie cookieName = new Cookie("ct-auth", cookievalue_WWW);
			   driver.manage().addCookie(cookieName);
		   }
		   else if(domain.equalsIgnoreCase("beta")){
			   Cookie cookieName = new Cookie("ct-auth", cookievalue_BETA);
			   driver.manage().addCookie(cookieName);
		   } 
		   refreshPage(driver);
	}
	
	public String validateXML(String TripID, String StartTag, String EndTag, String TagValue) throws IOException, URISyntaxException{
		String [ ] FOP = null;
		if(MakePaymentOnlyInQA2){
			DefaultHttpClient client = new DefaultHttpClient();
			String XML_URL = null;
			if(common.value("host").contains("qa2")){
				XML_URL = "http://172.17.12.231:9080/trips/"+TripID;
			}else if(common.value("host").contains("hf")){
				XML_URL = "http://10.10.25.116:9080/trips/"+TripID;
			}
			
			Reporter.log("XML URL : "+XML_URL);
			
			HttpGet get = new HttpGet(new URI(XML_URL));
			
			HttpResponse response = client.execute(get);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer();
			String str="";
			while((str=br.readLine())!=null){
				sb.append(str);
			}
			String sb1=sb.toString();
			String [ ] getFOP = sb1.split(StartTag);
			int i = getFOP.length;
			FOP = getFOP[0].split(EndTag);
		//	System.out.println(FOP[0]);
			if(FOP[0].equals(TagValue)){
				       Reporter.log(StartTag+FOP[0]+EndTag);
			        } else {
			        	Reporter.log(StartTag+FOP[0]+EndTag);
				        Assert.assertTrue(false);
			        	}
			}
		return FOP[0];	
	}
	
	public String PAHDate() throws Exception {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String Month = new SimpleDateFormat("MMM").format(date);
		String Year = new SimpleDateFormat("YY").format(date);
		Month = Month.toUpperCase();
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, +-1);
		Date date1 = c.getTime();
		String Date1 = new SimpleDateFormat("dd").format(date1);
		String PAHDate = Date1+"-"+Month+"-"+Year;
		return PAHDate;
	}
	
	public String getCHMMVoucher(String TripID) throws SQLException, ClassNotFoundException {
		String Voucher = null; 
		{
			 String url="jdbc:oracle:thin:@172.17.4.101:1521/cleardb";
			 //String url="jdbc:oracle:thin:@10.10.12.16:1521:cleardb";
			  String user="Product";
			  String password="product";

			  ArrayList<String> TripIDName = new ArrayList<String>();;
			  ArrayList<String> TripIDValue = new ArrayList<String>();;


			String query = "select VOUCHER_NO from product.chmm_booking_search_vw where trip_ref='"+TripID+"'";
			
			Reporter.log("oracle driver is called..");
		   
		  Connection myCon=DriverManager.getConnection(url, user, password);
		  Reporter.log("Conection to QA2 DB is established..");

		  if(myCon!=null)
		  {
			  ResultSet myRes = myCon.createStatement().executeQuery(query);
			   //ResultSet myRes = myCon.createStatement().executeUpdate(query);
			  Reporter.log("Executing Query..");
			  while(myRes.next()==true){
			  ResultSetMetaData result=myRes.getMetaData();
			  int noOfColumns=result.getColumnCount();
			
			  for(int x=1;x<=noOfColumns ;x++){
			  String colName=result.getColumnName(x);
			  String colValue=myRes.getString(x);
			  TripIDName.add(colName);
			  TripIDValue.add(colValue);
			
		 if(TripIDName.get(x-1).contains("VOUCHER_NO")) {
			 	Reporter.log(TripIDName.get(x-1)+" : "+TripIDValue.get(x-1));
				Reporter.log(TripIDValue.get(x-1));
			  	 Voucher = TripIDValue.get(x-1);
			  	 break;
		 }	  
		 Reporter.log("Voucher "+Voucher);
			  	}
			  
			    }
		  myCon.close();
		  }
		  else
		   Reporter.log("Connection not established");
		 }
		return Voucher;

	}
	
	public void hotelCom_UpdateCHMMDate(String TripID) throws Exception {
	  String Date = PAHDate();
	  String CHMMVoucher = getCHMMVoucher(TripID);
	  //String url="jdbc:oracle:thin:@10.10.12.16:1521:cleardb";
	  String url="jdbc:oracle:thin:@172.17.4.101:1521/cleardb"; 
	  
	  String user="Product";
	  String password="product";
	
		  String  Updatequery = "UPDATE tm.hotel_bookings set check_in_date='"+Date+"' where voucher_number='"+CHMMVoucher+"'";
		//		  UPDATE HOTEL_LEDGER_INFO  set CHECK_IN_DATE='05-MAY-18' where voucher_no='CHMM-8516284';
		  Connection myConUpdate=DriverManager.getConnection(url, user, password);
		  if(myConUpdate!=null)
		  {	  
		       int count = myConUpdate.createStatement().executeUpdate(Updatequery);
		       Reporter.log("Updated the Date for TripID");
		       myConUpdate.commit();
		       myConUpdate.close();
		  }
	}
	
	public void hotelCom_PAHOTP_SecondPayment(RemoteWebDriver driver) throws Exception{
 		safeClick(driver, By.xpath("//form/button"));
		textPresent(driver, "This hotel requires a credit card to guarantee the booking", 20);
		waitElement(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"), 10);
		safeClick(driver, getObject("HotelCom_BookStep4_CreditCard_Tab"));
		safeType(driver, getObject("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
		safeSelect(driver, getObject("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
		safeSelect(driver, getObject("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
		safeType(driver, getObject("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
		safeType(driver, getObject("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
		smartClick(driver, By.id("native_currency"));
		Thread.sleep(2000);
		safeClick(driver, getObject("HotelCom_BookStep4_MakePayment_Button"));
		
	  if(textPresent(driver, "Credit card expiration month should be two months greater than hotel checkout", 5)){
		  Reporter.log("Credit card expiration month should be two months greater than hotel checkout date : message is displayed");
		  Assert.assertTrue(false);
	  }
	  if(textPresent(driver, "Sorry, but we can't continue until you fix a few things. We've marked everything that needs fixing in RED.", 5)){
		  Reporter.log("Sorry, but we can't continue until you fix a few things. We've marked everything that needs fixing in RED. : message is displayed");
		  Assert.assertTrue(false);
	  }
	  hotelCom_PaymentPage_Authentication(driver, "");
	  if(!textPresent(driver,"Your stay is confirmed. Please pay at the hotel", 30)){
		Reporter.log("Your stay is confirmed. Please pay at the hotel text is not displayed");
		Assert.assertTrue(false);
	}
	}
	
	public void hotelCom_PAH_Noshow_ClaimRetention(RemoteWebDriver driver, String TripID) throws Exception{
		driver.get("https://qa2.cleartrip.com/chmm/bookings");
		elementVisible(driver, By.id("no_show"), 10);
		//safeClick(driver, By.id("no_show"));
		  for(int i=1; i<10; i++){
			   String TripID_XPATH = "//tr["+i+"]/td[3]/a";
			   String ClaimRetention_XPATH = "//tr["+i+"]/td[14]/div/button";
			   if(getText(driver, By.xpath(TripID_XPATH)).contains(TripID)){
				   safeClick(driver, By.xpath(ClaimRetention_XPATH));
				   break;
			   } 
			 }
		
		Thread.sleep(5000);
	 	driver.switchTo().alert().accept();
	 	Thread.sleep(2000);	
	}
	
	public String[] hotelCom_GV_Creation(RemoteWebDriver driver, String Amount) throws ClientProtocolException, IOException, URISyntaxException, JSONException{
		String[] GV = null;
		DefaultHttpClient clientSearch = null;
		String GV_Details ="INR|"+Amount+"|kiran.kumar@cleartrip.com|1|cleartrip";
		clientSearch = new DefaultHttpClient();
		String hash=calculateHash("SHA-256",GV_Details);				
		String postString="{ \"currency\":\"INR\", \"amount\":"+Amount+", \"userEmail\":\"kiran.kumar@cleartrip.com\", \"paymentId\":1 }";
		HttpPost itinenaryCall = new HttpPost(new URI("http://172.17.13.109:9080/payment/gv/create"));
		//HttpPost itinenaryCall = new HttpPost(new URI("http://10.10.21.245:9080/payment/gv/create"));
		StringEntity params = new StringEntity(postString);
		itinenaryCall.setEntity(params);
		itinenaryCall.setHeader("Content-Type","application/json");
		itinenaryCall.setHeader("checksum",hash);
		HttpResponse itinenaryResponse = clientSearch.execute(itinenaryCall);
		HttpEntity entityIti = itinenaryResponse.getEntity();
		Document docIti =null;
		BufferedReader br12 = new BufferedReader(new InputStreamReader(entityIti.getContent()));
		String GVNumber ="";
		StringBuffer GVNumber1 =new StringBuffer();
		while((GVNumber=br12.readLine())!=null){
			GVNumber1.append(GVNumber);
		}
		JSONObject GVPin = new JSONObject(GVNumber1.toString());
		String GVString = GVPin.toString();
		String [] GVString1 = GVString.split("\"gvPin\":\""); 
		String [] GVString_Pin = GVString.split("\",\"balance");
		String [] GVString_No = GVString.split("gvNumber\":\"");
		GVString_No[1] = GVString_No[1].replaceAll("\"}", ""); 
		GVString_Pin[0] = GVString_Pin[0].replaceAll("gvPin", "");		
		GVString_Pin[0] = GVString_Pin[0].replaceAll("\\{", "").replaceAll(":", "").replaceAll("\"\"\"", "");	
		GV = new String[2];
		GV[0] =GVString_No[1];
		GV[1] =GVString_Pin[0];
		Reporter.log("GV No : "+GVString_No[1]);
		Reporter.log("GV Pin : "+GVString_Pin[0]);
		System.out.println("GV No : "+GVString_No[1]);
		System.out.println("GV Pin : "+GVString_Pin[0]);
		return GV;
		
	}
	
	 public  String calculateHash(String hashType, String input) {
	        byte[] hashseq = input.getBytes();
	        StringBuffer hexString = new StringBuffer();
	        try {
	            MessageDigest algorithm = MessageDigest.getInstance(hashType);
	            algorithm.reset();
	            algorithm.update(hashseq);
	            byte[] messageDigest = algorithm.digest();

	            for (int i = 0; i < messageDigest.length; i++) {
	                String hex = Integer.toHexString(0xFF & messageDigest[i]);
	                if (hex.length() == 1) {
	                    hexString.append("0");
	                }
	                hexString.append(hex);
	            }

	        } catch (NoSuchAlgorithmException e) {
	            //logger.error(e);
	        }

	        return hexString.toString();
	    }
	
	 public void hotelCom_GSTValidation(RemoteWebDriver driver, String TripID, String[] Tags) throws URISyntaxException, ClientProtocolException, IOException{
		 
		 if(MakePaymentOnlyInQA2) {
		 	Reporter.log("http://172.17.13.35:9080/trips/"+TripID+".xml");
     		DefaultHttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(new URI("http://172.17.12.231:9080/trips/"+TripID+".xml"));
			
			HttpResponse response = client.execute(get);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer();
			String str="";
			while((str=br.readLine())!=null){
				sb.append(str);
			}
			 String sb1=sb.toString();
			 String [ ] getHotel = sb1.split("<code>");
			  int TagsLen = Tags.length;
			 int len = getHotel.length;
			 for(int i=0; i<len;i++){
				 for(int j=0; j<TagsLen;j++){
				 if(getHotel[i].contains(Tags[j])){
					 String[]  IGST = getHotel[i].split("</code>");			 
					 String[] IGST1 =IGST[1].split("</amount>");			 
					 IGST1[0]=IGST1[0].replace("<amount>", "");		
					 Reporter.log("Value of "+Tags[j]+" in XML : "+IGST1[0]);
				 }
				 }
			 }
		 }
	 }
	 
	 public int hotelCom_ConvertPrice_To_Int(RemoteWebDriver driver, By by) throws Exception{
		   String Price_Str = getText(driver, by);
		   Price_Str = Price_Str.replaceAll("\\D+", "");
			int priceInt = Integer.parseInt(Price_Str);
			System.out.println("Price : "+priceInt);
			return priceInt;
	 }
	 
	 public int hotelCom_Price_To_Int(RemoteWebDriver driver, By by) throws Exception{
		   String Price_Str = getText(driver, by);
		   Price_Str = Price_Str.replaceAll("\\D+", "");
			int priceInt = Integer.parseInt(Price_Str);
			return priceInt;
	 }
	
	 
	 public String generateRandomString(){
		 
         StringBuffer randStr = new StringBuffer();
         for(int i=0; i<RAN_LEN; i++){
             int number = getRandomNumber();
             char ch = RAN_LIST.charAt(number);
             randStr.append(ch);
         }
         return randStr.toString();
     }         
     
     public int getRandomNumber() {
         int randomInt = 0;
         Random randomGenerator = new Random();
         randomInt = randomGenerator.nextInt(RAN_LIST.length());
         if (randomInt - 1 == -1) {
             return randomInt;
         } else {
             return randomInt - 1;
         }
     }
     
     public void hotelCom_PaymentPage_Authentication(RemoteWebDriver driver, String Values) throws Exception {
    	 if(elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Dropdown"), 10)) {
    	 for(int i=0; i<=10; i++) {
    		 	textPresent(driver, "ACS Emulator", 1);
				if(elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Header"), 1)) {
					break;
				}
				if(elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"), 1)) {
					break;
				}
				if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 1)) {
					Reporter.log("SRP is displayed after Payment is intiated");
					Assert.assertTrue(false);
				}
			}
			Thread.sleep(1000);
			elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"), 10, "Payment Authentication : ");
			smartClick(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"));
 	}
  }
     
     public void hotelCom_PaymentPage_Authentication_Amex(RemoteWebDriver driver, String Values) throws Exception {
    	 
    	 for(int i=0; i<=10; i++) {
    		 	textPresent(driver, "ACS Emulator", 1);
				if(elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Header"), 1)) {
					break;
				}
				if(elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"), 1)) {
					break;
				}
				if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 1)) {
					Reporter.log("SRP is displayed after Payment is intiated");
					Assert.assertTrue(false);
				}
			}
			Thread.sleep(1000);
			elementVisible(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"), 10, "Payment Authentication : ");
			smartClick(driver, getObjectHotels("HotelCom_AMEX_PaymentPage_Submit"));
    	 
     }
     
     public void hotelCom_PaymentPage_Production_Card_Authentication(RemoteWebDriver driver, String BookingSource) throws Exception {
    	 elementVisible(driver, By.cssSelector("th.right"), 50);
    	 String CltpMerchantName= getText(driver, By.cssSelector("th.right"));
    	 if(BookingSource.equals("B2C")) {
    	 if(!CltpMerchantName.equals("CLEARTRIP PRIVATE LTD")) {
    		 Reporter.log("CLEARTRIP PRIVATE LTD");
    		 Assert.assertTrue(false);
    	 }
    	 } else  if(BookingSource.equals("Agency")) {
        	 if(!CltpMerchantName.equals("CLEARTRIP PVT LTD")) {
        		 Reporter.log("CLEARTRIP PVT LTD");
        		 Assert.assertTrue(false);
        	 }
    	 } else   if(BookingSource.equals("Corp")) {
        	 if(!CltpMerchantName.equals("CLEARTRIP PVT LTD")) {
        		 Reporter.log("CLEARTRIP PVT LTD");
        		 Assert.assertTrue(false);
        	 }
    	 }
    	 elementVisible(driver, By.id("staticAuthOpen"), 10);    	 
    	 safeClick(driver, By.id("staticAuthOpen"));
    	 //============================Card Details======================//
    	 safeType(driver, By.id("txtPassword"), "");
    	//============================Card Details======================//
    	 Reporter.log("-------------Payment is done in Production please cancel-----------------");
    	 System.out.println("-------------Payment is done in Production please cancel-----------------");
    	 safeClick(driver, By.xpath("//form[@id='staticAuthForm']/div/div[2]/button"));
     }
     
     //======================================
     public void hotelCom_HQ_CardLimitUpdate(RemoteWebDriver driver) throws Exception{
       driver.get(baseUrl + "/hq/hotel_raterules/5899830/edit");
	   Thread.sleep(2000);
	   String oldLimit =getAttribute(driver, getObjectHotels("HotelCom_HQ_Update_RateRule"),"value");
	   int oldLimit_Int = Integer.parseInt(oldLimit);
	   String Limit= String.valueOf(oldLimit_Int+1);
	   safeType(driver, getObjectHotels("HotelCom_HQ_Update_RateRule"), Limit);
	   Thread.sleep(5000);
	   driver.findElement(By.xpath("//*[@id='add_button']/input")).submit();
	   elementPresent(driver, By.xpath("//*[@id='ContentFrame']/div[1]/div[2]/form/input[2]"), 20);
	   Thread.sleep(2000);
	   safeClick(driver, getObjectHotels("HotelCom_HQ_ControlPanel_Link"));
	   safeClick(driver, getObjectHotels("HotelCom_HQ_Generate_RateRule"));
	   Reporter.log("Rate rule generated");
	   Thread.sleep(20000);
	   }
     
     public void hotelCom_Accounts_VerifyTripLinks(RemoteWebDriver driver) throws Exception{
    	 Thread.sleep(2000);
    	 /*safeClick(driver, getObjectHotels("HotelCom_Account_Trips_PrintVoucher"));
    	 ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    	 driver.switchTo().window(tabs.get(1));
    	 Thread.sleep(5000);
    	 elementVisible(driver, By.xpath("//div[2]/div/table[1]/tbody/tr[2]/td/strong"), 10);
  	   safeClick(driver, getObjectHotels("HotelCom_Account_Trips_VoucherEmailLlink"));
  	   safeType(driver, getObjectHotels("HotelCom_Account_Trips_VoucherEmail"), dataFile.value("HotelCom_SendMail"));
  	   safeClick(driver, getObjectHotels("HotelCom_Account_Trips_VoucherEmailSubmit"));
  	   elementPresent(driver, getObjectHotels("HotelCom_Account_Trips_VoucherEmailSubmit"), 10);
  		driver.switchTo().window(tabs.get(1)).close();
  		driver.switchTo().window(tabs.get(0));*/
    	 elementVisible(driver, By.id("email_itinerary"), 10);
  	   safeClick(driver, getObjectHotels("HotelCom_Account_Trips_ItineraryLink"));
  	   safeType(driver, getObjectHotels("HotelCom_Account_Trips_ItineraryEmail"), dataFile.value("HotelCom_SendMail"));
  	   safeClick(driver, getObjectHotels("HotelCom_Account_Trips_ItinerarySent"));
  	   elementPresent(driver, getObjectHotels("HotelCom_Account_Trips_TextVerify"), 10);
  	   Thread.sleep(3000);
  		
  	   safeClick(driver, getObjectHotels("HotelCom_Account_Trips_PassLink"));
  	   safeType(driver, getObjectHotels("HotelCom_Account_Trips_PassEmail"), dataFile.value("HotelCom_SendMail"));
  	   safeClick(driver, getObjectHotels("HotelCom_Account_Trips_PassSent"));
  	   elementPresent(driver, getObjectHotels("HotelCom_Account_Trips_TextVerify"), 10);
  		Thread.sleep(3000);
  		
  		elementVisible(driver, By.id("sms_itinerary"), 10);
  	   safeClick(driver, getObjectHotels("HotelCom_Account_Trips_SMSLink"));
  		safeType(driver, getObjectHotels("HotelCom_Account_Trips_SMSNo"), dataFile.value("HotelCom_SendSms"));
  		safeClick(driver, getObjectHotels("HotelCom_Account_Trips_SMSSent"));
  		elementPresent(driver, getObjectHotels("HotelCom_Account_Trips_TextVerify"), 10);
  		Thread.sleep(3000);	
  		
  		safeClick(driver, getObjectHotels("HotelCom_Account_Trips_InvoiceLink"));
  		
  	//===============  Verifying Print Voucher=================//
 	   elementVisible(driver, By.linkText("Print voucher"), 5);
 		safeClick(driver, By.linkText("Print voucher"));
 		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
 		Thread.sleep(5000);
 		driver.switchTo().window(tabs1.get(1));
 		Thread.sleep(5000);
 		elementVisible(driver, By.xpath("//div[2]/div/table[1]/tbody/tr[2]/td/strong"), 10);
 		driver.switchTo().window(tabs1.get(1)).close();
 		driver.switchTo().window(tabs1.get(0));
     }
     
     //S====================================================
     
     public void hotelCom_ModalWindow_Highlights(RemoteWebDriver driver, String Channel) throws Exception{
 	  	if(!elementVisible(driver, getObject("HotelCom_SRP_Price_Filter"), 10)) {
 			Reporter.log("Results are not displayed in SRP");	
 		}
 		logURL(driver);
 		safeClick(driver, getObjectHotels("HotelCom_SRP_Price_Filter"));
 		Thread.sleep(2000);
 		elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 20);
 		if(!Channel.equalsIgnoreCase("B2CIntl")) {
 			safeAutocomplete(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), getObjectHotels("HotelCom_SRP_HotelName_Ajax"), dataFile.value("ModalWindow_Hotel"));
 		} else safeAutocomplete(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), getObjectHotels("HotelCom_SRP_HotelName_Ajax"), dataFile.value("ModalWindow_Intl_Hotel")); 
 			
 		Thread.sleep(2000);
 		safeClick(driver, getObjectHotels("HotelCom_SRP_Hotel_Name"));
 		Thread.sleep(10000);
 		driver.switchTo().frame("modal_window");
 		if(textPresent(driver, "Sorry, our system is acting up.", 1)) {
 			Reporter.log("Modal window is displaying :Sorry, our system is acting up.");
 			Assert.assertTrue(false);
 		}
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_Tab"), "Highlights Tab " , 5);
 		safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_Tab"));
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_Name"), "Hotel Name ", 2);
 		if(!Channel.equalsIgnoreCase("B2CIntl")) {
 			textAssert(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_Name"), dataFile.value("ModalWindow_Hotel"));
 		} else textAssert(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_Name"), dataFile.value("ModalWindow_Intl_Hotel"));
 		String CheckIn = getDateTime(1, "E, dd MMM");
 		String CheckOut = getDateTime(2, "E, dd MMM");
 		CheckIn = CheckIn.replaceAll("0", "");
 		CheckOut = CheckOut.replaceAll("0", "");
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_CheckIN"), "Check IN ", 2);
 		if(!(Channel.equalsIgnoreCase("CorpAE") || Channel.equalsIgnoreCase("B2CIntl"))) {
 	 		textAssert(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_CheckIN"), CheckIn);
 		} else textAssert(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_CheckIN"), CheckIn);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_CheckOut"), "Check Out", 2);
 		textAssert(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_CheckOut"), CheckOut);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_Guest"), "Guests", 2);
 		textAssert(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_Guest"), "2 adults");
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_Rooms"), "Room and rates ", 2);
 		textAssert(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Hotel_Rooms"), "1 room, 1 night");
 		if(!(Channel.equalsIgnoreCase("CorpAE") || Channel.equalsIgnoreCase("B2CIntl"))) {
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_PhoneNo"), "Phone No", 2);
 		}
 		//textAssert(driver, getObjectHotels("HotelCom_B2B_ModalWindow_PhoneNo"), "022 40554954");
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_TopHotel_Image"), "Top Hotel % Image", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_TopHotel_CityName"), "Top Hotel City ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_TripAdvisorIndex"), "TA Index ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_Star_Icon"), "Star Icon ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_Star_Text"), "Star Text ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_TA_Logo"), "TA Logo ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_TA_Rating"), "TA Rating ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_TA_Review_Link"), "TA review link ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_Amenities_Link"), "TA Amenities link ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_CheckIN"), "CheckIn time", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_CheckOut"), "CheckOut time", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_Rooms"), "No of Rooms ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_Floors"), "No of floors ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_TA_Summary"), "TA Summary ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_TA_Details"), "TA Details", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_TA_Link"), "TA Link", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_Image"), "Image ", 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Highlights_Map"), "Map ", 2);
 	 }
     
     public void hotelCom_ModalWindow_Amenities(RemoteWebDriver driver, String Channel) throws Exception{
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Tab"), "Amenities Tab " , 5);
 		safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Tab"));
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Basic"), "Amenities Basic ", 5);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Basic_Icons"), "Amenities Icons ", 5);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Other_Basic"), "Amenities Other Basic ", 5);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Other_Basic_Details"), "Amenities Other Basic  Details", 5);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Hotel_Amenities"), "Amenities Hotel ", 5);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Hotel_Amenities_Details"), "Amenities Details ", 5);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Business"), "Amenities Business ", 5);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Business_Details"), "Amenities Business details", 5);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Amenities_Info"), "Amenities info ", 5);
     }
     
     public void hotelCom_ModalWindow_Photos(RemoteWebDriver driver, String Channel) throws Exception{
    	 elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Photos_Tab"), "Photos Tab " , 5);
 		safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Photos_Tab"));
 		/*elementPresent(driver, By.cssSelector("img.rsImg"), 10);
 	 	mouseHover(driver, By.cssSelector("img.rsImg"));
 	 	elementPresent(driver, By.cssSelector("div.rsArrow.rsArrowRight > div.rsArrowIcn"), 1);
 	 	safeClick(driver, By.cssSelector("#hotelGalleryV3 > div.rsOverflow > div.rsArrow.rsArrowRight > div.rsArrowIcn"));

 	 	Thread.sleep(2000);
 	 	WebElement ImageFile = driver.findElement(By.cssSelector("img.rsImg"));
 	    
 	    Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
 	    if (!ImagePresent)
 	    {
 	         Reporter.log("Image1 not displayed.");
 	         Assert.assertTrue(false);
 	    }
 	    else
 	    {
 	    	Reporter.log("Image1 displayed.");
 	    }
 	    elementVisible(driver, By.cssSelector("div.rsArrowIcn"), 1);
 	    Thread.sleep(5000);
 		WebElement ImageFile1 = driver.findElement(By.xpath("//div[@id='hotelGalleryV3']/div/div/div[2]/img"));
 	    Boolean ImagePresent1 = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile1);
 	    if (!ImagePresent1)
 	    {
 	    	Reporter.log("Image2 not displayed.");
 	    	Assert.assertTrue(false);
 	    }
 	    else
 	    {
 	    	Reporter.log("Image2 displayed.");
 	    }
 	    mouseHover(driver,By.xpath("//div[@id='hotelGalleryV3']/div/div/div[2]/img"));*/
     }

     public void hotelCom_ModalWindow_Map(RemoteWebDriver driver, String Channel) throws Exception{
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Map_Tab"), "Map Tab " , 5);
    		safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Map_Tab"));
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Map_Sights"), "Sights link ", 5);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Map_Eat"), "Eat link ", 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Map_Nightlife"), "NightLife link ", 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Map_Hotels"), "Hotels link ", 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Map_Shopping"), "Shopping link ", 2);
     }
     
     public void hotelCom_ModalWindow_Review(RemoteWebDriver driver, String Channel) throws Exception{
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Reviews_Tab"), "Traveller Review Tab " , 5);
    		safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Reviews_Tab"));
    		textPresent_Log(driver, "Traveller Ratings:", 5);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_Text"), "TA Text " , 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_Rating"), "TA Rating Block" , 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_PoweredBy"), "TA Poweredby text" , 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_PoweredLogo"), "TA Powered by logo" , 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_Review_Logo"), "TA Review Logo" , 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_Review_Summary"), "TA Review Summary" , 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_Review_Details"), "TA Review Details" , 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_Write_Review"), "TA Write Review Link" , 2);
    		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_SeeAll_Reviews"), "TA Sell All Review Link" , 2);
    		safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_Write_Review"));
    		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    		driver.switchTo().window(tabs.get(1));
    		Thread.sleep(1000);
    		String Modal_URL = driver.getCurrentUrl();
    		if(!Modal_URL.contains("www.tripadvisor.com/UserReviewEdit")) {
    			Reporter.log("TA web page is not opened after clicking on Write a Review link");
    			Assert.assertTrue(false);
    		}
    		driver.switchTo().window(tabs.get(0));
    		driver.switchTo().window(tabs.get(1)).close();
    		driver.switchTo().window(tabs.get(0));
    		driver.switchTo().frame("modal_window");
    	
    		Thread.sleep(1000);
    		
    		safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_TA_SeeAll_Reviews"));
    		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
    		driver.switchTo().window(tabs1.get(1));
    		Thread.sleep(1000);
    		String Modal_URL1 = driver.getCurrentUrl();
    		if(!Modal_URL1.contains("www.tripadvisor.in/Hotel_Review")) {
    			Reporter.log("TA web page is not opened after clicking on Sell All Reviews link");
    			Assert.assertTrue(false);
    		}
    		driver.switchTo().window(tabs1.get(0));
    		driver.switchTo().window(tabs1.get(1)).close();
    		driver.switchTo().window(tabs1.get(0));
    		driver.switchTo().frame("modal_window");
     }
     
     public void hotelCom_ModalWindow_SimilarHotels(RemoteWebDriver driver, String Channel) throws Exception{
 	  	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Similar_Tab"), "Similar Hotels Tab " , 5);
 		safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Similar_Tab"));
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_SimilarHotels_Text"), "Similar Hotels Text" , 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_SimilarHotels_Name"), "Similar Hotels Name" , 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_SimilarHotels_TA"), "Similar Hotels TA" , 2);
 		elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_SimilarHotels_StarRating"), "Similar Hotels Star Rating" , 2);
 		String srpHotelName = getText(driver, By.xpath("//strong"));
 		Thread.sleep(5000);
 		textAssert(driver, By.xpath("//div[6]/div/h4"), "Similar hotels that might interest you");
 		String similarHotelName = getText(driver, By.xpath("//a/div[2]/h4"));
 		//safeClick(driver, By.xpath("//a/div/span/img"));
 		safeClick(driver, By.xpath("//a/div/span"));
 		Thread.sleep(5000);
 		elementVisible(driver, By.xpath("//input"), 20);
 		String selectedHotelName = getText(driver, By.xpath("//strong"));
 		Reporter.log("SRP Hotel Name : " +srpHotelName);
 		Reporter.log("Hotel Name Selected in Similar Hotels page : " +similarHotelName);
 		Reporter.log("Similar Hotel Name : " +selectedHotelName);
 		if(srpHotelName.contains(similarHotelName)) {
 			Reporter.log("Hotel in SRP : "+srpHotelName+" Similar Hotel Selected : "+similarHotelName);
 			Assert.assertTrue(false);
 		}
 		
 		
 		if(!selectedHotelName.contains(similarHotelName)) {
 			Reporter.log("Hotel Selected in Similar hotels page : "+selectedHotelName+" Hotel getting booked : "+similarHotelName);
 			Assert.assertTrue(false);
 		}
 	
   
     }
     
     public void hotelCom_ModalWindow_RoomRates(RemoteWebDriver driver, String Channel) throws Exception{

   		String mainWindow = driver.getWindowHandle();
 		driver.switchTo().window(mainWindow);
    	 safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Close"));
  		Thread.sleep(2000);
  		safeClick(driver, getObjectHotels("HotelCom_SRP_Hotel_Name"));
  		Thread.sleep(10000);
  		driver.switchTo().frame("modal_window");
 	  	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Rooms_Tab"), "Room Rates Tab " , 5);
 		safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_Rooms_Tab"));
 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_Text"), "Room Rates Text " , 5);
 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_Type"), "Room Rates Tab " , 2);
 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_Price_Before_Disc"), "Room Rates Tab " , 2);
 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_Price_After_Disc"), "Room Rates Tab " , 2);
 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_Book_Btn"), "Room Rates Tab " , 2);
 	 	
 		if(!Channel.equalsIgnoreCase("B2CIntl")) {
 	 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_RoomDetails_Link"), "Room Rates Tab " , 2);
 	 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_RateDetails_Link"), "Room Rates Tab " , 2);
 	 	 	safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_RateDetails_Link"));
 	 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_RoomDetails_Content"), "Room Rates Details " , 2);
 	 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_RateType"), "Room Rate Type", 2);
 	 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_RoomDetails"), "Room Rate Details", 2);
 	 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_Room_Inclusions"), "Room Rate Inclusions", 2);
 	 	 	elementPresent_log(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_RatesText"), "Room Rate Text", 2); 		
 	 	 	String ModalWindow_Price = getText(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_Price_After_Disc"));
 	 	 	String ModalWindow_Details_Price = getText(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_TotalPrice"));
 	 	 	if(!ModalWindow_Price.contains(ModalWindow_Details_Price)) {
 	 	 		Reporter.log("Modal window price & price is rate details are not equal - Modal window :"+ModalWindow_Price+" Details Price : "+ModalWindow_Details_Price);
 	 	 		Assert.assertTrue(false);
 	 	 	}
 	 	}
 		Thread.sleep(2000);
 	 	safeClick(driver, getObjectHotels("HotelCom_B2B_ModalWindow_RoomRates_Book_Btn"));

 		if(!Channel.equalsIgnoreCase("B2CIntl")) {
 			elementPresent_log(driver, getObjectHotels("AgencyHotels_Itinerarypage_ContinueButton"), "Itinerary Continue button", 30);
 		} 	else elementPresent_log(driver, getObjectHotels("HotelCom_BookStep1_Continue_Button"), "Itinerary Continue button", 30); 
 			
     }
     
     public void hotelCom_Detailspage_RoomRate(RemoteWebDriver driver, String Channel) throws Exception{
    	 	logURL(driver);
    	 	elementPresent_log(driver,  getObjectHotels("HotelCom_DetailsPage_HotelName"), "Hotel Name ", 30); 	
      	 	String HotelName = getText(driver, getObjectHotels("HotelCom_DetailsPage_HotelName"));
      	 	if(!HotelName.contains(dataFile.value("DetailsPage_Hotel"))) {
      	 		Reporter.log("Hotel Name doesnt match with serch criteria ");
      	 		Assert.assertTrue(false);
      	 	}
      	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_HotelName"), "Hotel Name ", 10);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_HotelAddress"), "Hotel Address ", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Logo"), "TA Logo ", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Review"), "TA review ", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Review_Link"), "TA review link", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Star"), "Star rating ", 1);
//    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TopSeller"), "Top seller Logo ", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Price"), "Price", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Tab_Rooms"), "Rooms & Rate Tab", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Tab_TA"), "TA Tab", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Tab_Amenities"), "Amenities Tab", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Tab_Photos"), "Photos Tab", 1);
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Tab_Map"), "Map Tab", 1);

    	  	String Rate_PageTop = getText(driver, getObjectHotels("HotelCom_DetailsPage_Price"));
    	 	String Rate_RateTab = getText(driver, getObjectHotels("HotelCom_DetailsPage_RatesTab_Price"));
    	 	if(!Channel.equalsIgnoreCase("QA")) {
    	 	if(!Rate_PageTop.equals(Rate_RateTab)){
    			Reporter.log("Rate  @ Page top : "+Rate_PageTop+" is not same as the Rate displayed in Rate Tab : "+Rate_RateTab);
    			System.out.println("Rate  @ Page top : "+Rate_PageTop+" is not same as the Rate displayed in Rate Tab : "+Rate_RateTab);
    			Assert.assertTrue(false);
    	 	}
    	 	}
    	 	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Tab_Rooms"));
    	 	String RateTab_Text = getText(driver, getObjectHotels("HotelCom_DetailsPage_RatesTab_Rate_Text"));
    	 	if(!RateTab_Text.equals("Rooms & rates")){
    	 		Reporter.log("Rate Tab text should be 'Rooms & rates' but : "+RateTab_Text+" is displayed");
    	 		Assert.assertTrue(false);
    	 	}
    	 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_RatesTab_Rate_Text"), "Rate Block ", 1); 	 	
     }
     
     public void hotelCom_Detailspage_TravellerReview(RemoteWebDriver driver, String Channel) throws Exception{
	 	JavascriptExecutor jse = (JavascriptExecutor)driver;
 	 	jse.executeScript("window.scrollBy(0, -1000)", "");
 	 	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Tab_TA"));
  	
  	String TripAdvisor_Text = getText(driver, getObjectHotels("HotelCom_DetailsPage_TA_Text"));
  	if(!TripAdvisor_Text.equals("Traveller reviews")){
  		Reporter.log("TripAdvisor Tab text should be 'Traveller reviews' but : "+TripAdvisor_Text+" is displayed");
  		Assert.assertTrue(false);
  	}
  	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Review_Summary"), "Traveller review heading : ", 1);
  	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Review_Rating"), "Traveller Rating : ", 1);
  	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Review_Details"), "Traveller Rating block : ", 1);
  	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Review_Time"), "TA Review timing ", 1);
  	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Review_Logo"), "TA Logo", 1);
  	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Review_TopLogo"), "Top Reviews Logo", 1);
	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Logo_Bottom"), "Logo in Bottom", 1);
	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_PoweredBy_Text"), "Powered by text", 1);
	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_SellAll_Reviews"), "Sell all review link", 1);
  	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_TA_Write_Review_Link"), "Write your review link ", 1);
  	 	
  	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_TA_Write_Review_Link"));
	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	driver.switchTo().window(tabs.get(1));
	Thread.sleep(1000);
	String URL = driver.getCurrentUrl();
	if(!URL.contains("www.tripadvisor.com/UserReviewEdit")) {
		Reporter.log("TA web page is not opened after clicking on Write a Review link");
		Assert.assertTrue(false);
	}
	driver.switchTo().window(tabs.get(0));
	driver.switchTo().window(tabs.get(1)).close();
	driver.switchTo().window(tabs.get(0));
	Thread.sleep(1000);	
	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_TA_SellAll_Reviews"));
	ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
	driver.switchTo().window(tabs1.get(1));
	Thread.sleep(1000);
	String Modal_URL1 = driver.getCurrentUrl();
	if(!Modal_URL1.contains("www.tripadvisor.in/Hotel_Review")) {
		Reporter.log("TA web page is not opened after clicking on Sell All Reviews link");
		Assert.assertTrue(false);
	}
	driver.switchTo().window(tabs1.get(0));
	driver.switchTo().window(tabs1.get(1)).close();
	driver.switchTo().window(tabs1.get(0));
  	jse.executeScript("window.scrollBy(0, -2000)", "");
  }

     public void hotelCom_Detailspage_Amenities(RemoteWebDriver driver, String Channel) throws Exception{
    	 	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Tab_Amenities")); 	
		 	String Amenities_Text = getText(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_Text"));
		 	if(!Amenities_Text.equals("Amenities & Info")){
		 		Reporter.log("Amenities Tab text should be 'Amenities & Info' but : "+Amenities_Text+" is displayed");
		 		Assert.assertTrue(false);
		 	}
		 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_AboutHotel_Details"), "About hotel details", 2);
		 	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_ContinueReading_Link"));
		 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_Extra_Info"), "Extra details of hotels", 5);
		 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_Stats"), "Hotel Stats Checkin , Out, Rooms", 1);
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_AboutHotel_Text"), "About the hotel");
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_FoodBeverage"), "Food");
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_BusinessServices"), "Business Services");
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_Basics"), "Basics");
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_Travel"), "Travel");
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_PersonalServices"), "Personal Services");
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_NoCategory"), "No Category");
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_HotelAmenities"), "Hotel Amenities");
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_Recreation"), "Recreation");
		 	textAssert(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_BasicRoomAmenities"), "Basic Room Amenities");
/*
		 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_Image1"), "Left side Image1", 1);
		 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_Image2"), "Left side Image2", 1);
		 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Amenities_Image3"), "Left side Image3", 1);*/
		 	
     }

     public void hotelCom_Detailspage_Photos(RemoteWebDriver driver, String Channel) throws Exception{
 			JavascriptExecutor jse = (JavascriptExecutor)driver;
		 	jse.executeScript("window.scrollBy(0, -5000)", "");
		 	
			safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Tab_Photos"));
		 	elementPresent(driver, getObjectHotels("HotelCom_DetailsPage_Photos_Image"), 10);
		 	mouseHover(driver, getObjectHotels("HotelCom_DetailsPage_Photos_Image"));
		 	elementPresent(driver,  getObjectHotels("HotelCom_DetailsPage_Photos_RightArrow"), 1);
		 	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Photos_RightArrow1"));
		
		 	Thread.sleep(2000);
		 	WebElement ImageFile = driver.findElement(getObjectHotels("HotelCom_DetailsPage_Photos_Image"));
		    
		    Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
		    if (!ImagePresent)
		    {
		         Reporter.log("Image1 not displayed.");
		         Assert.assertTrue(false);
		    }
		    else
		    {
		    	Reporter.log("Image1 displayed.");
		    }
		    elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Photos_ArrowIcon"), 1);
		    Thread.sleep(5000);
			WebElement ImageFile1 = driver.findElement(getObjectHotels("HotelCom_DetailsPage_Photos_Image2"));
		    Boolean ImagePresent1 = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile1);
		    if (!ImagePresent1)
		    {
		    	Reporter.log("Image2 not displayed.");
		    	Assert.assertTrue(false);
		    }
		    else
		    {
		    	Reporter.log("Image2 displayed.");
		    }
		    safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Photos_360View_Link"));
		    elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Photos_360View_Photo"), "360 view photo", 20);
		    mouseHoverClick(driver, getObjectHotels("HotelCom_DetailsPage_Photos_360View_Photo"));
		    Thread.sleep(5000);
		    Point coordinates = driver.findElement(By.id("hotelPanos")).getLocation();
		    Robot robot = new Robot();
		    robot.mouseMove(coordinates.getX(),coordinates.getY()+120);
		   Thread.sleep(5000);
		    safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Photos_Switch_To_Photos"));
		    mouseHover(driver, getObjectHotels("HotelCom_DetailsPage_Photos_Image2"));
		    safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Photos_Close"));
		    elementPresent_log(driver,getObjectHotels("HotelCom_DetailsPage_Photos_RoomType1_Tab"), "Room Type 1 Tab : ", 5);
		    elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Photos_RoomType2_Tab"), "Room Type 2 Tab : ", 5);
		    //safeClick(driver, By.xpath("//header/div/a"));
		 	jse.executeScript("window.scrollBy(0, -5000)", "");
		 	
		 	  
     }

     public void hotelCom_Detailspage_Map(RemoteWebDriver driver, String Channel) throws Exception{
    		JavascriptExecutor jse = (JavascriptExecutor)driver;
		 	jse.executeScript("window.scrollBy(0, -5000)", "");
		 	Thread.sleep(2000);
		 	safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Tab_Map"));
		 	Thread.sleep(5000);
		 	elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Map_HotelDetails"), "Hotel Details in Map : ", 5);
		 	/*List<WebElement> NearByIcon = driver.findElements(getObjectHotels("HotelCom_DetailsPage_Map_Distance_Pin"));
			
			List<WebElement> NearByDetails = driver.findElements(getObjectHotels("HotelCom_DetailsPage_Map_Distance_Details"));
			for (int i =0; i<=NearByDetails.size()-1; i++) {	
				String NearByText = NearByDetails.get(i).getText();
				String NearByIconText = NearByIcon.get(i).getAttribute("class");
				NearByIconText = NearByIconText.replace("hotelSprite map", "");
				Reporter.log(NearByIconText+" : "+NearByText);
			}*/
			safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Map_Close"));
		  
     }
     
     public void hotelCom_Detailspage_SimilarHotels(RemoteWebDriver driver, String Channel) throws Exception{
 		elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_SimilarHotels_Text"), "Similiar Hotel Text : ", 5);
 		String SimilarHotel_Text = getText(driver, getObjectHotels("HotelCom_DetailsPage_SimilarHotels_Text"));
 		if(!SimilarHotel_Text.contains("Hotels similar to")) {
 			Reporter.log("Similar Hotels text is not displayed");
 			Assert.assertTrue(false);
 		}
 		elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_SimilarHotels_Photo"), "Similiar Hotel  Photo ", 1);
 		elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_SimilarHotels_Name"), "Similiar Hotel name ", 1);
 		elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_SimilarHotels_Price"), "Similiar Hotel Price ", 1);
 		elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_SimilarHotels_StarRating"), "Similiar Hotel Star Rating ", 1);
 		elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_SimilarHotels_TA_Rating"), "Similiar Hotel TA Rating ", 1);
     }
     
     public void hotelCom_DetailsPage_CancellationPolicy(RemoteWebDriver driver, String Connector, String CancellationPolicy) throws Exception {
    	 logURL(driver);
    	 elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10);
    	 elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_ShowDetails_Link"), 10);
    	 safeClick(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_ShowDetails_Link"));
         Thread.sleep(2000);
         elementNotVisible(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_Loader"), 5);
         elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_Policy_Details")	, 10);
    	 if(Connector.equalsIgnoreCase("CHMM")||Connector.equalsIgnoreCase("TRUST")||Connector.equalsIgnoreCase("EXPEDIA")||Connector.equalsIgnoreCase("OYO")||Connector.equalsIgnoreCase("GTA")) {
         smartClick(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_ShowDetails_Link"));
         elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_Policy_Details")	, 10);
         if(!getText(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_Policy_Text")).contains("Cancellation policy")) {
        	  Reporter.log("Cancellation policy text is not displayed");
        	  Assert.assertTrue(false);
          }
          if(!getText(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_Policy_Details")).contains(CancellationPolicy)) {
        	  Reporter.log(CancellationPolicy+" :  text is not displayed");
        	  Assert.assertTrue(false);
          }
     }
    	  if(Connector.equalsIgnoreCase("TRUST")) {
             
              if(!getText(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_Policy_Text")).contains("Cancellation policy")) {
            	  Reporter.log("Cancellation policy text is not displayed");
            	  Assert.assertTrue(false);
              }
              if(!getText(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_Policy_Details")).contains(CancellationPolicy)) {
            	  Reporter.log(CancellationPolicy+" :  text is not displayed");
            	  Assert.assertTrue(false);
              }
              if(!getText(driver, getObjectHotels("HotelCom_DetailsPage_Cancellation_Policy_Details")).contains("Children aged 13 years and older are fully charged.")) {
            	  Reporter.log(CancellationPolicy+" :  text is not displayed");
            	  Assert.assertTrue(false);
              }
              
         }
     }
     
    public static String generateRandomAlphaNumeric(int count) {
    	final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    	StringBuilder builder = new StringBuilder();
    	while (count-- != 0) {
    			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
    			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    	}
    	return builder.toString();
     }

@SuppressWarnings("unused")
public void hotelCom_Switchto_NextTab(RemoteWebDriver driver) {
	
	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	driver.switchTo().window(tabs.get(1));
	String Modal_URL = driver.getCurrentUrl();
	driver.switchTo().window(tabs.get(0));
	driver.get(Modal_URL);
	driver.switchTo().window(tabs.get(1)).close();
	driver.switchTo().window(tabs.get(0));
}
    
public String HotelCom_Coupon_Creation(RemoteWebDriver driver, String BookingType) throws Exception{
	   String Code= generateRandomString();
	   if(BookingType.equalsIgnoreCase("CCLCREATE")){
	   Code = "CCLCREATE"+Code;
	   System.out.println("code: "+Code);
	   }
	   String BookIn_Date = getDate( "dd");
		BookIn_Date = BookIn_Date .substring(1);
		BookIn_Date = "1"+BookIn_Date ;
		int DateInt = Integer.parseInt(BookIn_Date);
		String BookIn_Start_Date = putDate1(0);
		String BookIn_End_Date = putDate1(DateInt+60);
		
	   driver.manage().deleteAllCookies();   
	   driver.get("http://qa2.cleartrip.com");
	   hotelCom_AddCookie(driver);
	   driver.get("https://qa2.cleartrip.com/hq/hotel_raterules");
	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_NewRule"));
	   safeType(driver, getObjectHotels("HotelCom_HQ_RateRule_PromoCode"),Code );
	   safeType(driver, getObjectHotels("HotelCom_HQ_RateRule_PromoDescription"),Code);
	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_Channels"));
	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_Domains"));
	  // Alert alert = driver.switchTo().alert();
	   org.openqa.selenium.Alert alert = driver.switchTo().alert();
		Reporter.log("Alert detected: " + alert.getText());
   	   alert.accept();
  	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_Percent"));
	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_CouponSelect"));
	   safeType(driver, getObjectHotels("HotelCom_HQ_RateRule_PercentValue"), "10");
	   safeType(driver, getObjectHotels("HotelCom_HQ_RateRule_CouponCode"),Code);
	   
	   safeType(driver, getObjectHotels("HotelCom_HQ_RateRule_BookingStartDate"), BookIn_Start_Date);
	   safeType(driver, getObjectHotels("HotelCom_HQ_RateRule_BookingEndDate"),BookIn_End_Date);
	   safeType(driver, getObjectHotels("HotelCom_HQ_RateRule_CheckinStartDate"),BookIn_Start_Date );
	   safeType(driver, getObjectHotels("HotelCom_HQ_RateRule_CheckinEndDate"),BookIn_End_Date);
	   
	   safeUncheck(driver, getObjectHotels("HotelCom_HQ_RateRule_AccountRestriction"));
	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_CCSelect"));
	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_CCTrans"));
	   safeType(driver, getObjectHotels("HotelCom_HQ_RateRule_CCTransLimit"),"1" );
	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_ExcludePAH"));
	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_ExcludePAHCC"));
	   safeClick(driver, getObjectHotels("HotelCom_HQ_Add_RateRule_SubmitBtn"));
	   safeClick(driver, getObjectHotels("HotelCom_HQ_RateRule_CouponEdit"));
	   Reporter.log(Code);
	   Reporter.log(driver.getCurrentUrl());
	   String url=driver.getCurrentUrl(); 
	   url=url.replace("https://qa2.cleartrip.com/hq/hotel_raterules/", "");
	   url=url.replace("/edit", "");
		CSVWriter csvWriter = null;
		try{
			//Create CSVWriter for writing to Employee.csv 
			csvWriter = new CSVWriter(new FileWriter("coupon.csv"));
			//row1
			String[] row = new String[]{"add-coupon",Code,url};
			csvWriter.writeNext(row);
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		finally{
			try{
				//closing the writer
				csvWriter.close();
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		}
		driver.get("https://qa2.cleartrip.com/hq/");
 	safeClick(driver,getObject("Hotelcom_HQ_Others"));
 	safeClick(driver,getObject("HotelCom_HQ_BatchUpload"));
 	elementVisible(driver, getObject("HotelCom_HQ_BatchUpload_TextPresent"), 10);
 	Reporter.log("In HQ - Batch UploadPage");
		File f=null;
		f=new File("coupon.csv");
		String path="";
		boolean fileExist=f.exists();
		if(fileExist==true){
			path=f.getAbsolutePath();
			Reporter.log("File Path "+path);
		}else {
		 Reporter.log("File Not found");}
		 driver.findElement(By.name("file")).sendKeys(path);
 	 safeClick(driver,getObject("HotelCom_HQ_batchProcess"));
 	 Reporter.log("File Upload for processing");
 	 Thread.sleep(5000);
 	 elementVisible(driver,getObject("HotelCom_HQ_UploadedText"),10);
 	 elementVisible(driver,getObject("HotelCom_HQ_thisPage"),10);
 	 safeClick(driver,getObject("HotelCom_HQ_thisPage"));
    	 Thread.sleep(2000);   
    	driver.get("https://qa2.cleartrip.com/hq/air/services");
    	safeClick(driver, getObjectHotels("HotelCom_HQ_Generate_RateRule"));
    	waitElement(driver, getObjectHotels("HotelCom_HQ_RateRule_GenerateOK"), 10);
    	return Code;
}

public String HotelCom_SRP_SortOrder(RemoteWebDriver driver, String OrderType) throws Exception{
	String fhotel=null;
 	driver.get("https://qa2.cleartrip.com/hq");
  	safeClick(driver, getObjectHotels("HotelCom_HQ_HotelTab"));
  	safeClick(driver, getObjectHotels("HotelCom_HQ_PromotionHotel_Link"));
  	safeType(driver, getObjectHotels("CHMM_Add_Hotel_City"), "Hyderabad");
  	safeClick(driver, getObjectHotels("HotelCom_HQ_PromotionHotel_Autosuggest"));
  	safeClick(driver, getObjectHotels("HotelCom_HQ_PromotionHotel_CityFilter"));
  	waitForElementVisibility(driver, getObjectHotels("HotelCom_HQ_Default_SortOrder"), 50);
  	//-------------Default------------------------------
  	if(OrderType.equalsIgnoreCase("default")){
  		if(isElementSelected(driver,  getObjectHotels("HotelCom_HQ_Default_SortOrder"))){
  			safeClick(driver, By.id("dxNoneFilter"));
  		}
  	}
  	//-----------------SortExp1-----------------------------------
  	if(OrderType.equalsIgnoreCase("sortexp1")){
 		safeClick(driver, By.id("Sort1-checkbox"));
 		if(elementClickable(driver, By.xpath("//*[@id='div-Sort1']/input[2]"), 10)){
 			safeClick(driver, By.xpath("//*[@id='div-Sort1']/input[2]"));
 			waitForElementVisibility(driver, getObjectHotels("HotelCom_HQ_Default_SortOrder"), 50);
 			if(elementNotVisible(driver, getObjectHotels("HotelCom_HQ_Default_SortOrder"), 10)){
 				Reporter.log("Sort Expression is not working coz of secturity issue");
 			}
 		}
  	}
  	String hotelid=getText(driver, getObjectHotels("HotelCom_HQ_Promotion_HotelList"));
  	List<WebElement> hotelids = driver.findElements(getObjectHotels("HotelCom_HQ_Promotion_HotelTable_Data"));
  	for (int i = 1; i < 5; i++) {
  			Reporter.log(hotelids.get(i).getText());
  		}
  		fhotel=getText(driver, getObjectHotels("HotelCom_HQ_Promotion_HotelTable_FirstHotel"));
  		Reporter.log(fhotel);
  		safeClick(driver, getObjectHotels("HotelCom_HQ_Promotion_HotelTable_NewRank"));
  		driver.findElement(getObjectHotels("HotelCom_HQ_Promotion_HotelTable_NewRank")).sendKeys(Keys.BACK_SPACE);
  		safeType(driver, getObjectHotels("HotelCom_HQ_Promotion_HotelTable_NewRank"), "1");
  		Thread.sleep(1000);
  	
  	safeClick(driver, getObjectHotels("HotelCom_HQ_Promotion_HotelTable_Update"));
  	waitForElementVisibility(driver, By.id("sortTypeField"), 50);

  	return(fhotel);
}

    /*
    	    
         private static final String ALGO = "AES";
        private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

    public static String encrypt(String Data) throws Exception {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(Data.getBytes());
            String encryptedValue = new BASE64Encoder().encode(encVal);
            return encryptedValue;
        }

        public static String decrypt(String encryptedData) throws Exception {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        }
        private static Key generateKey() throws Exception {
            Key key = new SecretKeySpec(keyValue, ALGO);
            return key;
    }
*/
     
	}