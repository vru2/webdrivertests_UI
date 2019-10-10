// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.CorporateHotels;

	public class HotelCorp_PTA_Rejected extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test ( dataProviderClass = CorporateDataProvider.class,dataProvider="HotelCorp")
	public void CorpHotel_PTA_Rejected(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
	  driver.manage().deleteAllCookies();
	  corpPTA_Hotel_SrpUrl(driver, "PTAAPPROVER", "Bangalore", "Karnataka", "IN", "1", 44, 45);
	  //corpPTA_Hotel_SrpUrl(driver, "PTABOOK", "Bangalore", "Karnataka", "IN", "1", 44, 45);
	  corpHotel_SRP(driver, Hotel_Name,"");
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  textPresent(driver, "Thanks! Your booking request is sent for approval.", 50);
	  elementVisible(driver, By.xpath("//div[@id='ptaThanks']/p/a"), 10);
	  String TripID = getText(driver, By.xpath("//div[@id='ptaThanks']/p/a"));
	  Reporter.log("PTA TripID : "+TripID);
	  logger.info("PTA TripID : " + TripID );
	  logURL(driver);
	  
		driver.manage().deleteAllCookies();
		driver.get(Corp_PTA_Url());
		//corp_SignIn_User(driver, "PTAAPPROVER");
		corp_SignIn_User(driver, "PTABOOK");
		/*driver.get(Corp_PTA_Url()+"/trips/"+TripID);
		elementVisible(driver, By.cssSelector("span.status.pending"), 50);
		safeType(driver, By.id("rejectionReason"), "Rejected - Select a 5 Star Hotel");
		safeClick(driver, By.xpath("(//form[@id='approveOrReject']/input[6])[2]"));
		if(!elementVisible(driver, By.cssSelector("span.status.rejected"), 50)){
			Reporter.log("Rejected Status is not displayed");
			Assert.assertTrue(false);
		}	  */
		
		driver.get(Corp_PTA_Url()+"/mobile/reject?tripId="+TripID+"&approverId=13957750");
		textPresent(driver, "Are you sure you want to reject this trip?", 5);
		elementPresent(driver, By.id("rejectionReason"));
		safeType(driver, By.id("rejectionReason"), "Rejected - Select a 5 Star Hotel");
		
		safeClick(driver, By.xpath("//form[@id='rejectTrip']/div/input"));
		if(!textPresent(driver,	"Rejected! Trip has been successfully dismissed.", 5)) {
			Reporter.log("Rejected message is not displayed");
			Assert.assertTrue(false);
		}
		
		  logger.info("PTA Rejected TripID : " + TripID );
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