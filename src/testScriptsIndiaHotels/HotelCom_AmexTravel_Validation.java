// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - June, 2017
// Author - Kiran Kumar
// Copyright © 2017 Cleartrip Travel. All right reserved.
package testScriptsIndiaHotels;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

	public class HotelCom_AmexTravel_Validation extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComAmexIntlSearch", groups = "Smoke Tests")
  public void HotelCom_AmexTravel_HomepageValidation(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception { 
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);	  
	   elementPresent_log(driver, By.cssSelector("a.amexLogo.fLeft"), "Amex Logo", 5);
	   elementPresent_log(driver, By.cssSelector("span.amexIndiaFlag"), "India Flag", 5);
	   elementPresent_log(driver, By.xpath("//img[@alt='American Express Travel']"), "American Express Travel text", 5);
	   elementPresent_log(driver, By.linkText("Copyright © 2017 American Express Company American Express Banking Corp"), "Copyright", 5);
	   elementPresent_log(driver, By.linkText("Privacy"), "Privacy link ", 5);
	   elementPresent_log(driver, By.cssSelector("p.amexFooterByline"), "support Text", 5);
	   if(!getText(driver, By.cssSelector("p.amexFooterByline")).contains("1860 266 9902")){
		   Reporter.log("Support Text" +getText(driver, By.cssSelector("p.amexFooterByline")));
		   Reporter.log("1860 266 9902 phone no is not displayed in Support text");
		   Assert.assertTrue(false);
	   }
	   safeClick(driver, By.linkText("Privacy"));
	  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	  driver.switchTo().window(tabs.get(1));
	  elementPresent_log(driver, By.cssSelector("span.iCHd1"), "Privacy page", 10);
	  String policyPageText = getText(driver, By.cssSelector("span.iCHd1"));
	  if(!policyPageText.equals("American Express® India Online Privacy Statement")){
		  Reporter.log("American Express® India Online Privacy Statement text is not displayed : "+policyPageText);
		  Assert.assertTrue(false);
	  }
	  driver.switchTo().window(tabs.get(1)).close();
	  driver.switchTo().window(tabs.get(0));
  }
  
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComAmexIntlSearch", dependsOnMethods="HotelCom_AmexTravel_HomepageValidation")
  public void HotelCom_AmexTravel_No_Intl(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception { 
     // hotelCom_HomepageSearch(driver, "Dubai", CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
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
		if(driver.findElement(By.id("SearchHotelsButton")).isEnabled()){
			System.out.println("Able to search International Ciy in AmexWL");
			Assert.assertTrue(false);
		}
		else{
			System.out.println("Verified:AmexWL doesn't support International Search");
			
		}
      /* elementPresent_log(driver, By.linkText("Search again"), "Search again link ", 20);
	  elementPresent_log(driver, By.xpath("//div[@id='ResultContainer_1_1']/div[3]/div/p/span"), "Warning Image", 2);
	  elementPresent_log(driver, By.cssSelector("div.warningMessage > h2"), "This search is only for Domestic Travel Text", 2);*/
	}


  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getAmexTravelUrl();
  }
  
  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }


}