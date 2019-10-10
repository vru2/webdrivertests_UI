// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - 30 Aug 2015
// Author - Kiran Kumar
// Copyright © 2015 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.Agency;

		public class AgencyAir_Dom_OneWay_Cancellation extends Agency{
		public RemoteWebDriver driver;
		String TripID = null;
		
		@Test ( dataProviderClass = AgencyDataProvider.class,dataProvider="AirAgencyDomOWCancel")	  
		public void AgencyComDom_OW_Cancel(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Pref_Airline, String Payment_Type,  String Trip_Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		  driver.manage().deleteAllCookies();
		  driver.get(Agency_Url());
			agency_SignIn(driver);	
			safeClick(driver,By.xpath("//*[text()='Trips']"));
			String trip;
			String tex = null;
			int j=0;
			boolean found=false;
			do {
			List<WebElement> we=driver.findElements(By.xpath("//*[contains(text(),'one-way')]"));
			for(int  i=j;i<we.size();i++) {
				we.get(i).click();
				if(elementPresent(driver,By.xpath("//*[text()='Cancellations']"),2)) {
					safeClick(driver,By.xpath("//*[text()='Cancellations']"));
					//safeClick(driver, getObject("Air_Agency_Trips_Cancel_One"));
					safeClick(driver, By.xpath("//td/input"));
					safeClick(driver, getObject("Air_Agency_Trips_Cancel_Two"));
					elementPresent_Time(driver, By.xpath("//div[2]/div/h1"), 30);
					textAssert(driver, By.xpath("//div[2]/div/h1"), "Review cancellation & refund amount");
					safeClick(driver, By.id("cancel_button"));
					elementPresent_Time(driver, By.xpath("//div[3]/div/h1"), 20);
					Thread.sleep(2000);
					textAssert(driver, By.xpath("//div[3]/div/h1"), "Cancellation confirmation");
					found=true;
					break;
					
				}
				//safeClick(driver,By.xpath("//*[text()='Flights']"));
				safeClick(driver,By.xpath("//*[text()='Trips']"));
				j++;
				break;
			}
			}
			while(!found);
			
		/*String SRP_URL = agencyAir_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline, 38, 39);	
		  driver.get(SRP_URL);
		  agencyAir_SRP_Domestic_Oneway(driver);
		  agencyAir_ItineraryPage(driver);
		  agencyAir_TravellerPage(driver, Adults, Childrens, Infants, "false", "false");
		  TripID = agencyAir_Paymentpage(driver, "CREDITCARD", "", Trip_Logger_Msg, Booking_Confirmation_Message);
		  if(MakePaymentOnlyInQA2){
		  agencyAir_Account_Cancellation(driver, TripID);
		  }*/
		}

		

		@BeforeClass
		public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		}
  
		@AfterMethod (alwaysRun = true)
		public void takeScreenshot(ITestResult _result) throws Exception{
		screenshot(_result, driver);
		}
  
		@AfterClass
		public void tearDown() throws Exception {
		browserClose(driver);
		}
  
}