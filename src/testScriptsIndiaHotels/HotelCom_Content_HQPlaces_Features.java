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

	public class HotelCom_Content_HQPlaces_Features extends IndiaHotels{
	public RemoteWebDriver driver;
	
  @Test 
  public void HotelCom_HQPlaces_Content_Overview() throws Exception {
	  	   driver.manage().deleteAllCookies();   
	  	   ArrayList<String> Amenities = getBasicAmenities(3);
	  	 Reporter.log("Amenities Selected in HQ : "+Amenities.get(0)+", "+Amenities.get(1)+" & "+Amenities.get(2) );
	  	   driver.get("http://qa2.cleartrip.com");
	  	   hotelCom_AddCookie(driver);
	  	   driver.get("https://qa2.cleartrip.com/hq/hotels/edit/166202#");
	  	   logURL(driver);
	  	   elementVisible(driver, getObjectHotels("HotelCom_HQPlaces_FeaturesTab"), 10);
	  	   safeClick(driver, getObjectHotels("HotelCom_HQPlaces_FeaturesTab"));
	  	   elementVisible(driver, getObjectHotels("HotelCom_HQPlaces_Features_Description"), 10);
	  	   List<WebElement> weCheckBox =driver.findElements(By.xpath("//tr/td/input[2]"));
	  	   List<WebElement> WebEle = driver.findElements(By.xpath("//tr/td/label"));
	  	   for (int i =1; i<=WebEle.size()-1; i++) {	
	  		   String elementText = WebEle.get(i).getText();
	  		   boolean uncheck = weCheckBox.get(i).getAttribute("checked") != null ;
	  		   if(uncheck) {
	  			 weCheckBox.get(i).click();
	  		   }
	  		   if(elementText.equalsIgnoreCase(Amenities.get(0))||elementText.equalsIgnoreCase(Amenities.get(1))||elementText.equalsIgnoreCase(Amenities.get(2))){				  
	  			 weCheckBox.get(i).click();			    	
				 }			    	
			}
	  	   safeClick(driver, getObjectHotels("HotelCom_HQPlaces_Save_Button"));
	  	   elementPresent_log(driver, getObjectHotels("HotelCom_HQPlaces_Updated_Message"), "Hotel content updated", 50);
	  	   driver.get(detailsPage_URL_Link(driver, "com", "166202", 20));
	  	   elementVisible(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 10);
	  	   List<WebElement> AmenitiesSRP =driver.findElements(By.xpath("//div[3]/ul/li"));
	  	   for (int i =0; i<=AmenitiesSRP.size()-1; i++) {	
	  		   String AmenitiesText = AmenitiesSRP.get(i).getText();
	  		   boolean Amn_0 = AmenitiesText.contains(Amenities.get(0));
	  		   boolean Amn_1 = AmenitiesText.contains(Amenities.get(1));
	  		   boolean Amn_2 = AmenitiesText.contains(Amenities.get(2));
	  		   if(Amn_0||Amn_1||Amn_2) {
		  	   } else {
		  			 Reporter.log(AmenitiesText+" Content is not displayed");
		  			   Assert.assertTrue(false);
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