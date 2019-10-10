// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Aug, 2016
// Author - sUDHIR
// Copyright © 2016 cleartrip Travel. All right reserved.
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

	public class HotelCom_Opaque extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class)
  public void HotelComCoupon() throws Exception {
	   	driver.manage().deleteAllCookies(); 
	//  	driver.get(baseUrl);   
	    driver.get(hotelSrpUrl(driver, "Shimla", "Himachal+Pradesh", "IN"));
	    logMessagePageNotLoaded(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 50, "Search Results Page has not loaded  :( :( :( :( :( :(");
	    if(elementVisible(driver, getObject("HotelCom_SRP_HotelName_TextBox"), 25)) {
	     Reporter.log("Hotel SRP is displayed");
	    } else {
	     Reporter.log("Hotel SRP is not displayed");
	     Thread.sleep(5000);
	    }
	    elementVisible(driver, getObject("HotelCom_SRP_Price_Filter"), 10);
	   
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
	    Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		loop: for(int i=0; i<=30;i++) {
			if(elementPresent_Time(driver, getObject("HotelCom_ModalWindow_Price"), 1)) {
				break loop;
			} 
			if(elementPresent_Time(driver, By.xpath("//div[@id='hotelDetailsHeader']/div/div/div[2]/div[2]/div[2]/strong"), 1)) {
				break loop;
			} 
			Thread.sleep(1000);
		}
		Boolean Price1 = elementPresent_Time(driver, getObject("HotelCom_ModalWindow_Price"), 1);
		Boolean Price2 = elementPresent_Time(driver, By.xpath("//div[@id='hotelDetailsHeader']/div/div/div[2]/div[2]/div[2]/strong"), 1);
		if((!Price1) && (!Price2)) {
			Reporter.log("Price is not displayed in Modal Window");
			Assert.assertTrue(false);
		} 
		String Modal_URL = driver.getCurrentUrl();
		driver.switchTo().window(tabs.get(0));
		driver.get(Modal_URL);
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(2000);
		if(!(elementVisible(driver, getObject("HotelCom_ModalWindow_Price"), 20) || elementPresent_Time(driver, By.xpath("//div[2]/strong"), 20))) {
			refreshPage(driver);
		}
		if(elementVisible(driver, getObject("HotelCom_ModalWindow_Price"), 25)) {
		} else {
			Reporter.log("Modal window is not displayed");
			
		}
		if(!textPresent(driver,"Opaque", 30)){
			Reporter.log("Text : 'Opaque  text is not displayed");
			Assert.assertTrue(false);
		}
		if(!textPresent(driver,"We get you the best price, guaranteed", 30)){
			Reporter.log("Text : 'We get you the best price, guaranteed...  text is not displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.linkText("Book this hotel"));
	   hotelCom_ItineraryPage(driver, "OPAQUE");
	   hotelCom_LoginPage(driver, "SignIN","");
	   hotelCom_TravelerPage(driver);
	   String TripID =hotelCom_PaymentPage(driver, "DEBITCARD", "Hotels Opaque TripID : ", "Your secret hotel is ...");
	   safeClick(driver, By.linkText("View hotel info"));

	    Thread.sleep(5000);
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(1));
		Thread.sleep(5000);
		Modal_URL = logURL(driver);
		Thread.sleep(5000);
		driver.switchTo().window(tabs1.get(0));
	
		driver.get(Modal_URL);
		driver.switchTo().window(tabs1.get(1)).close();
		driver.switchTo().window(tabs1.get(0));
		Thread.sleep(5000);
		if(textPresent(driver,"Opaque", 2)){
			Reporter.log("Text : 'Opaque  text is not displayed");
			Assert.assertTrue(false);
		}
		if(textPresent(driver,"We get you the best price, guaranteed", 5)){
			Reporter.log("Text : 'We get you the best price, guaranteed...  text is not displayed");
			Assert.assertTrue(false);
		}
		textPresent(driver, "Traveller reviews", 10);
		driver.get( baseUrl+"/account/trips/"+TripID);
		if(textPresent(driver,"Opaque", 2)){
			Reporter.log("Text : 'Opaque...  text is displayed");
			Assert.assertTrue(false);	
			
  }
		String Trip_Status=getText(driver, By.xpath("//td[4]/span"));
		if(!Trip_Status.equals("CONFIRMED")){

			Reporter.log("Trip status is not confirmed, It is dispalyed as "+Trip_Status);
			Assert.assertTrue(false);
		}
		driver.get( baseUrl+"/hq/trips/"+TripID);
		textPresent(driver,"This is an Opaque Booking", 5);
		if(textPresent(driver,"NOTOpaque", 2)){
			Reporter.log("Text : 'NOTOpaque  text is displayed");
			Assert.assertTrue(false);
		}
		Trip_Status=getText(driver, By.xpath("//td[5]"));
		if(!Trip_Status.equals("Confirmed")){
			Reporter.log("Trip status is not confirmed, It is dispalyed as "+Trip_Status);
			Assert.assertTrue(false);}
  }
  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "com");
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