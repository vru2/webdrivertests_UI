// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;
import junit.framework.Assert;

	public class HotelCom_Content_HQPlaces_Overview extends IndiaHotels{
	public RemoteWebDriver driver;
	String Hotel_Description=null;
	
  @Test 
  public void HotelCom_HQPlaces_Content_AboutHotel() throws Exception {
	  	   driver.manage().deleteAllCookies();   
	  	   String Hotel_Description_Ran = generateRandomAlphaNumeric(10); 
	  	   Hotel_Description = "Hotel T.A.P. Paradise Description"+Hotel_Description_Ran; 
	  	   driver.get("http://qa2.cleartrip.com");
	  	   hotelCom_AddCookie(driver);
	  	   driver.get("https://qa2.cleartrip.com/hq/hotels/edit/166202#");
	  	   elementVisible(driver, getObjectHotels("HotelCom_HQPlaces_ContentTab"), 10);
	  	   safeClick(driver, getObjectHotels("HotelCom_HQPlaces_ContentTab"));
	  	 /*  safeClick_CheckBox(driver, getObjectHotels("HotelCom_HQPlaces_Hold_Btn"));

	  		 Thread.sleep(2000);
	  	   if(isAlertPresent(driver)) {
			 	driver.switchTo().alert().accept();
	  	   }
	  	   elementVisible(driver, getObjectHotels("HotelCom_HQPlaces_Hold_Text"), 10);*/
	  	   //safeClick(driver, By.xpath("//fieldset[3]/input[2]"));
	  	   safeType(driver, getObjectHotels("HotelCom_HQPlaces_Hotel_Basic_Description"), Hotel_Description);
	  	   safeClick(driver, getObjectHotels("HotelCom_HQPlaces_Save_Button"));
	  	   
	  	   elementPresent_log(driver, getObjectHotels("HotelCom_HQPlaces_Updated_Message"), "Hotel content updated", 50);
	  	   driver.get(detailsPage_URL_Link(driver, "com", "166202", 2));
	  	   String Updated_HotelContent= getText(driver, getObjectHotels("HotelCom_DetailsPage_Content_HotelDescription"));
	  	   if(!Updated_HotelContent.contains(Hotel_Description)) {
	  		   Reporter.log("Hotel Content is not updated in details page is displayed as "+Updated_HotelContent);
	  		   Assert.assertTrue(false);
	  	   }
  	}
  
	  	  @Test (dependsOnMethods = "HotelCom_HQPlaces_Content_AboutHotel")
	  	  public void HotelCom_HQPlaces_Content_Hold() throws Exception {	  	   
			  	   	 hotelCom_DetailsPage_URL_Open(driver, "com", "166202", 10);
			  	   //	 if(!textPresent(driver, Hotel_Description, 20)) {
			  	   	if(!textPresent(driver, "qwqedqweqweqweqw", 20)) {
			  	   		 Reporter.log("Edited test in description is not displayed");
			  	   		 Assert.assertTrue(false);
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