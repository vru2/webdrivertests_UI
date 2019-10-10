// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_Content_HQPlaces_Features_SRP extends IndiaHotels{
	public RemoteWebDriver driver;
	public String HotelName = "hotel+tap+gold+crest";
	public String HotelID = "50759";
	
  @Test 
  public void HotelCom_HQPlaces_Content_SRP() throws Exception {
	  	   driver.manage().deleteAllCookies();   
	  	  //String[] AmenitiesNameSRP = { "Air Conditioner" ,  "WiFi / Internet", "Room service",  "Pool", "Airport Shuttle", "Parking" , "Restaurant", "Cafe", "Gym / Fitness Center", "Business Center", "Bar"};    
	  	  // String[] AmenitiesNameSRP = { "Bar",  "Restaurant", "Parking", "Business Center", "Airport", "Pool" , "Gym"};
		   driver.get("http://qa2.cleartrip.com");
	  	   hotelCom_AddCookie(driver);
	  	   driver.get("https://qa2.cleartrip.com/hq/hotels/edit/"+HotelID);
	  	   ArrayList<String> Amenities = getBasicAmenitiesSRP(3);
	  	   Reporter.log("Amenities Selected in HQ : "+Amenities.get(0)+", "+Amenities.get(1)+" & "+Amenities.get(2) );
	  	   elementVisible(driver, getObjectHotels("HotelCom_HQPlaces_FeaturesTab"), 10);
	  	   safeClick(driver, getObjectHotels("HotelCom_HQPlaces_FeaturesTab"));
	  	   elementVisible(driver, getObjectHotels("HotelCom_HQPlaces_Features_Description"), 20);
	  	   List<WebElement> weCheckBox =driver.findElements(By.xpath("//tr/td/input[2]"));
	  	   List<WebElement> WebEle = driver.findElements(By.xpath("//tr/td/label"));
	  	   for (int i =0; i<=WebEle.size()-1; i++) {	
	  		   String elementText = WebEle.get(i).getText();
	  		   boolean uncheck = weCheckBox.get(i).getAttribute("checked") != null ;
	  		   if(uncheck) {
	  			 weCheckBox.get(i).click();
	  		   }
	  		   if(elementText.contains(Amenities.get(0))||elementText.contains(Amenities.get(1))||elementText.contains(Amenities.get(2))){				  
	  			 weCheckBox.get(i).click();			    	
				 }			    	
			}
	  	   safeClick(driver, getObjectHotels("HotelCom_HQPlaces_Save_Button"));
	  	   elementPresent_log(driver, getObjectHotels("HotelCom_HQPlaces_Updated_Message"), "Hotel content updated", 60);
	  	   driver.get(SRP_URL_Link(driver, "com", "Bangalore", "Karnataka", HotelID, 10)+"&hotelName="+HotelName);
			elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 50);
	  	  List<WebElement> SRPAminities = driver.findElements(By.xpath("//ul[@id='amenitiesLabel']/li"));
	  	  for (int i =0; i<=SRPAminities.size()-1; i++) {	
	  		  int j= i+1;
	  		  //String elementText = SRPAminities.get(i).getText();
	  		String elementText = getAttribute(driver, By.xpath("//ul[@id='amenitiesLabel']/li["+j+"]"), "original-title");
	  		 if(elementText.contains(Amenities.get(0))) {
	  			 if(elementText.contains("unavailable")) {
	  				 Reporter.log(Amenities.get(0)+" : Amenity is showing as unavailable");
	  				 Assert.assertTrue(false);
	  			 }
	  		 }
	  		 if(elementText.contains(Amenities.get(1))) {
	  			 if(elementText.contains("unavailable")) {
	  				 Reporter.log(Amenities.get(1)+" : Amenity is showing as unavailable");
	  				 Assert.assertTrue(false);
	  			 }
	  			 }
	  			 if	(elementText.contains(Amenities.get(2))) {
	  				 if(!elementText.contains(Amenities.get(2))) {
		  				 Reporter.log(Amenities.get(2)+" : Amenity is showing as unavailable");
		  				 Assert.assertTrue(false);
	  			 }
	  		 }
	  	  }  	  	   
  }

  @BeforeClass
  public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
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