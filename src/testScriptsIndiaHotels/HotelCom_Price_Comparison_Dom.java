// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - May, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsIndiaHotels;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Price_Comparison_Dom extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;	
	public HashMap < String, String > param = new HashMap < String, String >();
	
    @DataProvider(name = "Sector1")
    public Object [ ][ ] createData1() {
        return new Object [ ] [ ] { { "Ooty","Tamil+Nadu","IN" } };
    }
    
    @DataProvider(name = "Sector2")
    public Object [ ][ ] createData2() {
        return new Object [ ] [ ] {
        									   { "New+Delhi","Delhi","IN"  } };
    }
    
    @DataProvider(name = "Sector3")
    public Object [ ][ ] createData3() {
        return new Object [ ] [ ] { {"Bangalore","Karnataka","IN" } };
    }

  @Test(dataProvider = "Sector1")
  public void HotelComPrice_ComparisonDom1(String From, String State, String Country) throws Exception {
	  String Prod_Url = "http://www.cleartrip.com/hotels/results?from=" + From + "&city=" + From + "&state=" + State + "&country=" + Country + "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + putDate1(5) + "&chk_out=" + putDate1(6) + "&adults1=1&children1=0&num_rooms=1";
      String Beta_Url = "http://beta.cleartrip.com/hotels/results?from=" + From + "&city=" + From + "&state=" + State + "&country=" + Country + "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + putDate1(5) + "&chk_out=" + putDate1(6) + "&adults1=1&children1=0&num_rooms=1";

	  driver.manage().deleteAllCookies();
	  driver.get(Prod_Url);	
	  ArrayList<String> ProdHotel = hotelSRP_PriceCheck(driver);
	  
	  driver.manage().deleteAllCookies();
	  driver.get(Beta_Url);
	  ArrayList<String> BetaHotel = hotelSRP_PriceCheck(driver);	
      
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
  

  @Test(dataProvider = "Sector2")
  public void HotelComPrice_ComparisonDom2(String From, String State, String Country) throws Exception {
	  String Prod_Url = "http://www.cleartrip.com/hotels/results?from=" + From + "&city=" + From + "&state=" + State + "&country=" + Country + "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + putDate1(5) + "&chk_out=" + putDate1(6) + "&adults1=1&children1=0&num_rooms=1";
      String Beta_Url = "http://beta.cleartrip.com/hotels/results?from=" + From + "&city=" + From + "&state=" + State + "&country=" + Country + "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + putDate1(5) + "&chk_out=" + putDate1(6) + "&adults1=1&children1=0&num_rooms=1";

	  driver.manage().deleteAllCookies();
	  driver.get(Prod_Url);	
	  ArrayList<String> ProdHotel = hotelSRP_PriceCheck(driver);
	  
	  driver.manage().deleteAllCookies();
	  driver.get(Beta_Url);
	  ArrayList<String> BetaHotel = hotelSRP_PriceCheck(driver);	
      
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
  

  @Test(dataProvider = "Sector3")
  public void HotelComPrice_ComparisonDom3(String From, String State, String Country) throws Exception {
	  String Prod_Url = "http://www.cleartrip.com/hotels/results?from=" + From + "&city=" + From + "&state=" + State + "&country=" + Country + "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + putDate1(5) + "&chk_out=" + putDate1(6) + "&adults1=1&children1=0&num_rooms=1";
      String Beta_Url = "http://beta.cleartrip.com/hotels/results?from=" + From + "&city=" + From + "&state=" + State + "&country=" + Country + "&area=&poi=&hotelId=&hotelName=&dest_code=&chk_in=" + putDate1(5) + "&chk_out=" + putDate1(6) + "&adults1=1&children1=0&num_rooms=1";

	  driver.manage().deleteAllCookies();
	  driver.get(Prod_Url);	
	  ArrayList<String> ProdHotel = hotelSRP_PriceCheck(driver);
	  
	  driver.manage().deleteAllCookies();
	  driver.get(Beta_Url);
	  ArrayList<String> BetaHotel = hotelSRP_PriceCheck(driver);	
      
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
    
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }

}