// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMiddleEastHotels;

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

	public class HotelAE_Shortlist_and_SRP_Page_Intl extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl;
	public String HotelName_SRP;
	public int HotelPrice_Shortlist, HotelPrice_SRP;
	
  @Test 
  public void HotelCom_Shortlist_SRP() throws Exception {
	   driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl);
	   hotelCom_HomepageSignIn(driver, "");
	   driver.get(hotelSrpUrl_AE(driver, "Dubai", "", "AE"));
	   for(int i=0; i<=10; i++){
			if(elementVisible(driver, getObjectHotels("HotelCom_SRP_HotelName_TextBox"), 1)) {
				break;
		} else {
				if(textPresent(driver, "Sorry, our system is acting up", 1)){
					Reporter.log("Sorry, our system is acting up message is displayed in SRP");
					Assert.assertTrue(false);
				}
		}
	   }
	  HotelName_SRP = getText(driver, getObjectHotels("HotelCom_SRP_HotelName"));

	   safeClick(driver, getObjectHotels("HotelCom_SRP_Total_Link"));
	   Thread.sleep(2000);
	   HotelPrice_SRP = hotelCom_Price_To_Int(driver, getObjectHotels("HotelCom_SRP_Total_Price"));

	   if(elementVisible(driver, getObjectHotels("HotelCom_SRP_Shortlisted_Link"), 2)) {
		   safeClick(driver, getObjectHotels("HotelCom_SRP_Shortlisted_Link"));
	   }
	   Thread.sleep(2000);
	   if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_ToBe_Shortlisted_Link"), 5)) {
		   Reporter.log("Shortlist delete is not working");
		   Assert.assertTrue(false);
	   }
	   //Verifing ADD
	   safeClick(driver, getObjectHotels("HotelCom_SRP_ToBe_Shortlisted_Link"));
	   Thread.sleep(2000);
	   //XPATH://button[@class='button shortlist shortlisted mr5']
	   if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_Shortlisted_Link"), 5)) {   
		   System.out.println("Shortlist ADD is not working");
		   Assert.assertTrue(false);
	   }
	   //Verifing Delete
	   safeClick(driver, getObjectHotels("HotelCom_SRP_Shortlisted_Link"));
	   Thread.sleep(2000);
	   if(!elementVisible(driver, getObjectHotels("HotelCom_SRP_ToBe_Shortlisted_Link"), 5)) {
		   Reporter.log("Shortlist delete is not working");
		   Assert.assertTrue(false);
	   }
	   safeClick(driver, getObjectHotels("HotelCom_SRP_ToBe_Shortlisted_Link"));
	   Thread.sleep(5000);
	   safeClick(driver, getObjectHotels("HotelCom_SRP_Your_Shortlist_Link"));
  }

  @Test (dependsOnMethods ="HotelCom_Shortlist_SRP")
  public void HotelCom_ShortlistPage() throws Exception {	   
	   //Verifing Shortlist link in SRP	   
		hotelCom_Switchto_NextTab(driver);
		elementVisible(driver, getObjectHotels("HotelCom_ShortlistPage_Modify_Button"), 20);
		elementPresent(driver, getObjectHotels("HotelCom_ShortlistPage_CheckIN_Details"), 5);
		String TextShortlist = getText(driver, By.cssSelector("h1.shortlist__heading"));
		if(!TextShortlist.contains("Shortlisted hotel")) {
			Reporter.log("Shortlisted hotels in : text is not displayed");
			Assert.assertTrue(false);
		}
		
		   //Verifing Shortlist page Share
			driver.navigate().refresh();
			safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_Share_Button"));
			Thread.sleep(5000);
			safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_Share_Copy_Button"));
		//	driver.navigate().refresh();
			String ShareUrl= getAttribute(driver, getObjectHotels("HotelCom_ShortlistPage_Share_Link"), "value");
			safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_Share_Close"));
		   //Verifing Shortlist page FB Share
			safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_FB_Button"));
			hotelCom_Switchto_NextTab(driver);
			String FBShare_Url = driver.getCurrentUrl();
			if(!(FBShare_Url.contains("cleartrip.com")&&FBShare_Url.contains("shortlist"))) {
				Reporter.log("FB share url doesnt contain cleartrip or shortlist text in url");
				Assert.assertTrue(false);
			}
		driver.navigate().to(ShareUrl);
	   //Verifing Shortlist page modify
		elementPresent(driver, getObjectHotels("HotelCom_ShortlistPage_HotelName"));
		safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_Modify_Button"));
		HotelPrice_Shortlist = hotelCom_Price_To_Int(driver, getObjectHotels("HotelCom_ShortlistPage_HotelPrice"));
		safeSelectByIndex(driver, getObjectHotels("HotelCom_ShortlistPage_PAX_Dropdown"), 2);
		safeClick(driver, getObjectHotels("HotelCom_ShortlistPage_Submit_Button"));
		Thread.sleep(2000);
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl = getBaseUrl( "ae");
  }
  
/*  @AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	  browserClose(driver);
  }*/

}