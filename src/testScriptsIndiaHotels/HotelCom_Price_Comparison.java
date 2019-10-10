// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsIndiaHotels;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Price_Comparison extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;	
	public HashMap < String, String > param = new HashMap < String, String >();
	
    @DataProvider(name = "Sector")
    public Object [ ][ ] createData1() {
        return new Object [ ] [ ] { { "Ooty","Tamil+Nadu","IN" },
        									   { "New+Delhi","Delhi","IN"  },
        									   {"Bangalore","Karnataka","IN" },
        									   {"Bangkok","","TH" } };
    }

  @Test(dataProvider = "Sector")
  public void HotelComPrice_Comparison(String From, String State, String Country) throws Exception {
	  String Prod_Url = "http://www.cleartrip.com/hotels/results?from=" + From + "&city=" + From + "&state=" + State + "&country=" + Country + "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + putDate1(5) + "&chk_out=" + putDate1(6) + "&adults1=1&children1=0&num_rooms=1";
      String Beta_Url = "http://beta.cleartrip.com/hotels/results?from=" + From + "&city=" + From + "&state=" + State + "&country=" + Country + "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + putDate1(5) + "&chk_out=" + putDate1(6) + "&adults1=1&children1=0&num_rooms=1";
   
	  driver.get(Prod_Url);
	  if(!elementVisible(driver, By.xpath("//div[2]/section[5]/section/aside/div/p/strong/span"), 30)){
      	Reporter.log("SRP page is not loaded in Production");
      	refreshPage(driver);
      }
	  logURL(driver);
	  elementVisible(driver, getObject("HotelCom_SRP_Price_Filter"), 50);
	  Thread.sleep(5000);
	  safeClick(driver, getObject("HotelCom_SRP_Price_Filter"));
	  Thread.sleep(5000);
	  ArrayList < String > ProdHotel = new ArrayList < String >();
	   for ( int i = 1; i <= 5; i++) {
		   String Xpath = "//li["+i+"]/section/nav/ul/li[3]/h2[2]/strong";           
           if ( elementVisible(driver,By.xpath(Xpath), 5) ) {
        	   ProdHotel.add(getText(driver, By.xpath(Xpath)));
              }	           
       }
	      
	  driver.manage().deleteAllCookies();
	  driver.get(Beta_Url);
	  if(!elementVisible(driver, By.xpath("//div[2]/section[5]/section/aside/div/p/strong/span"), 30)){
      	Reporter.log("SRP page is not loaded in Beta");
      	refreshPage(driver);
      }
	  logURL(driver);
	  elementVisible(driver, getObject("HotelCom_SRP_Price_Filter"), 50);
	  Thread.sleep(5000);
	  safeClick(driver, getObject("HotelCom_SRP_Price_Filter"));
	  Thread.sleep(5000);
	  ArrayList < String > BetaHotel = new ArrayList < String >();
	  for ( int i = 1; i <= 5; i++) {
		   String Xpath = "//li["+i+"]/section/nav/ul/li[3]/h2[2]/strong";
            if ( elementVisible(driver,By.xpath(Xpath), 5) ) {
               BetaHotel.add(getText(driver, By.xpath(Xpath)));
           }	
       }
	
      for(int i=0; i<=3; i++) {
    	  String ProdHotels = ProdHotel.get(i);
    	  String BetaHotels = BetaHotel.get(i);
    	  if(!ProdHotels.equals(BetaHotels)) {
    		  Reporter.log("Prod Hotel_"+i+" : "+ProdHotels+" - "+BetaHotels+ " & Beta Hotel_"+i);
    	  }
      }
      
      Reporter.log("Production Hotel List : "+ProdHotel);
      Reporter.log("Beta Hotel List : "+BetaHotel);
      if(!(BetaHotel.equals(ProdHotel))) {
		  Assert.assertTrue(false);
		  }  	
	  
	 
	  
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