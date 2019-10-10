// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - April, 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All right reserved.
package testScriptsMobileHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.MobileHotels;

	public class Dom_QA extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileQA_Hotel_MultiPax_MultiRoom")
  public void MobileQA_Hotel_MultiPaxRoom(String City, String From_Date, String To_Date, String Rooms, String AdultsInRoom1, String AdultsInRoom2, String ChildInRoom1, String ChildInRoom2, String ChildAgeInRoom1, String ChildAgeInRoom2, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  driver.manage().deleteAllCookies();
	  driver.get(baseUrl);
	  mobileCom_Hotel_HomepageMultiPaxRoom(driver, City, From_Date, To_Date, Rooms, AdultsInRoom1,AdultsInRoom2,ChildInRoom1,ChildInRoom2,ChildAgeInRoom1,ChildAgeInRoom2, Hotel_Name,Trip_Logger_Msg);
	  mobileCom_Hotel_SRP(driver, Hotel_Name);
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "");
	  mobileCom_Hotel_Login(driver, "SignIn");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "", "Mobile Web QA : ");
	  mobileCom_Hotel_ConfirmationPage(driver, "", "Mobile Web QA : ", "Your booking is done");
	  
	  
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver3(driver);
	baseUrl = common.value("urlqa");
  }

  @AfterMethod(alwaysRun = true)
	 public void afterMethod(ITestResult _result) throws Exception {
	 	afterMethod(driver, _result);
	 }
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}