// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

	public class HotelCom_SearchTags_SRP extends IndiaHotels{
	public RemoteWebDriver driver;
	
  @Test
  public void SRP_SearchTags() throws Exception {
	   driver.manage().deleteAllCookies();
	   if(ProductionUrl) {
	   driver.get(SRP_URL_Link(driver, "com", "Goa", "Goa", "", 5));
	   } else driver.get(SRP_URL_Link(driver, "com", "bangalore", "Karnataka", "", 5));
	  elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 30);
	  elementPresent_log(driver, getObjectHotels("HotelCom_SRP_SearchTag_Block"), "Search Tag Block in SRP ", 10);
	  elementPresent_log(driver, getObjectHotels("HotelCom_SRP_SearchTag1"), "Search Tag1 ", 10);
	  String Tag_Text = getText(driver, getObjectHotels("HotelCom_SRP_SearchTag_Text"));
	  if(!Tag_Text.contains("Select your stay preferences")) {
		  Reporter.log("Tag text contains : "+Tag_Text+"Instead of : Select your stay preferences");
		  Assert.assertTrue(false);
	  } 
	  /*elementPresent_log(driver, getObjectHotels("HotelCom_SRP_SearchTag_YourSort_Link"), "Your Sort Link is not displayed", 2);
	  safeClick(driver, getObjectHotels("HotelCom_SRP_SearchTag_YourSort_Link"));
	  elementPresent_log(driver, getObjectHotels("HotelCom_SRP_YourSort_Text"), "Your Sort text is not displayed", 2);
	  elementPresent_log(driver, getObjectHotels("HotelCom_SRP_YourSort_Image"), "Your Sort Image is not displayed", 2);*/
	  safeClick(driver, getObjectHotels("HotelCom_SRP_SearchTag1") );
	  safeClick(driver, getObjectHotels("HotelCom_SRP_SearchTag2") );
	  String Search_Tag1 = getText(driver, getObjectHotels("HotelCom_SRP_SearchTag1") );
	  String Search_Tag2 = getText(driver, getObjectHotels("HotelCom_SRP_SearchTag2") );
	/*  elementPresent_log(driver, getObjectHotels("HotelCom_SRP_SearchTag1_Hotel"), "Search Tag1 in SRP Hotel Details", 2);
	  elementPresent_log(driver, getObjectHotels("HotelCom_SRP_SearchTag2_Hotel"), "Search Tag2 in SRP Hotel Details", 2);*/
		elementPresent_log(driver, By.xpath(" (//section/div[3]/ul/li[1])[1]"), "Search Tag1 in SRP Hotel Details", 2);
		elementPresent_log(driver, By.xpath("(//section/div[3]/ul/li[2])[1]"), "Search Tag2 in SRP Hotel Details", 2);
	  String Search_Hotel_Tag1 = getText(driver, By.xpath(" (//section/div[3]/ul/li[1])[1]") );
	  String Search_Hotel_Tag2 = getText(driver, By.xpath("(//section/div[3]/ul/li[2])[1]") );
	  if(!Search_Tag1.equals(Search_Hotel_Tag1)) {
		  Reporter.log("Selected Tag1 : "+Search_Tag1+" displayed Tag1 : "+Search_Hotel_Tag1);
		  Assert.assertTrue(false);
	  }
	  if(!Search_Tag2.equals(Search_Hotel_Tag2)) {
		  Reporter.log("Selected Tag2 : "+Search_Tag1+" displayed Tag2 : "+Search_Hotel_Tag2);
		  Assert.assertTrue(false);
	  }
	//  safeClick(driver, getObjectHotels(""));
	//  safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));
	  elementPresent_log(driver, getObjectHotels("HotelCom_SRP_YourSort_ClearAll"), "Your Sort Clear Link", 2);
	  safeClick(driver, getObjectHotels("HotelCom_SRP_YourSort_ClearAll"));
	  Thread.sleep(2000);
	  if(elementVisible(driver, By.cssSelector(".metaTags__item.active"), 2)) {
		  Reporter.log("Hotel Search Tag is not Cleared after clicking on clearall");
		  Assert.assertTrue(false);
	  }
	 /* safeClick(driver, getObjectHotels("HotelCom_SRP_SearchTag1") );
	  safeClick(driver, getObjectHotels("HotelCom_SRP_SearchTag2") );
	  safeClick(driver, getObjectHotels("HotelCom_SRP_SelectRoom_Button"));
		hotelCom_Switchto_NextTab(driver);
	 		Thread.sleep(1000);
		loop: for(int i=0; i<=20;i++) {
			if(elementPresent_Time(driver, getObjectHotels("HotelCom_ModalWindow_Price"), 1)) {
				break loop;
			} 
			if(elementPresent_Time(driver, By.xpath("//div[@id='hotelDetailsHeader']/div/div/div[2]/div[2]/div[2]/strong"), 1)) {
				break loop;
			} 
			Thread.sleep(100);
		}  */
	  
  }

/*  @Test (dependsOnMethods= {"SRP_SearchTags"})
  public void Details_SearchTags() throws Exception {
	  elementVisible(driver, getObjectHotels("HotelCom_DetailsPage_Tag_Text"), 5);
	  elementPresent_log(driver, getObjectHotels("HotelCom_DetailsPage_Tag_Text"), "Tag text is not displayed :", 5);
	  if(!getText(driver, getObjectHotels("HotelCom_DetailsPage_Tag_Text")).contains("")) {
		  Reporter.log("Tag text is displayed as: "+getText(driver, getObjectHotels("HotelCom_DetailsPage_Tag_Text")));
		  Assert.assertTrue(false);
	  }
  }*/
  
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