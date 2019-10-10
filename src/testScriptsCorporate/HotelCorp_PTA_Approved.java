// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.CorporateHotels;

	public class HotelCorp_PTA_Approved extends CorporateHotels{
	public RemoteWebDriver driver;

	@Test
	public void CorpHotel_PTA_Approved() throws Exception {
	  driver.manage().deleteAllCookies();
	  corpPTA_Hotel_SrpUrl(driver, "PTAAPPROVER", "Mumbai", "Maharashtra", "IN", "1", 44, 45);
	  corpHotel_SRP(driver, "","");
	  corpHotel_ItineraryPage(driver);
	  corpHotel_TravelerPage(driver);
	  textPresent(driver, "Thanks! Your booking request is sent for approval.", 10);
	  elementVisible(driver, By.xpath("//div[@id='ptaThanks']/p/a"), 10);
	  String TripID = getText(driver, By.xpath("//div[@id='ptaThanks']/p/a"));
	  Reporter.log("PTA TripID : "+TripID);
	  logger.info("PTA TripID : " + TripID );
	  logURL(driver);	  
		driver.manage().deleteAllCookies();
		driver.get(Corp_PTA_Url());
		//corp_SignIn_User(driver, "PTAAPPROVER");
		corp_SignIn_User(driver, "PTABOOK");
		driver.get(Corp_PTA_Url()+"/mobile/approve?tripId="+TripID+"&approverId=13957750");
		textPresent(driver, "Are you sure you want to approve this trip?", 10);
		elementPresent_log(driver, By.linkText(TripID), "TripId is not displayed", 5);
		elementPresent_log(driver, By.xpath("//form[@id='approveTrip']/input[5]"), "Approve button is not displayed", 5);
		safeClick(driver, By.xpath("//form[@id='approveTrip']/input[5]"));
		if(!textPresent(driver,	"Great! Trip has been approved.", 10)) {
			Reporter.log("Great! Trip has been approved. message is not displayed");
			Assert.assertTrue(false);
		}
		driver.manage().deleteAllCookies();
		driver.get(Corp_PTA_Url());
		//corp_SignIn_User(driver, "PTABOOK");	
		corp_SignIn_User(driver, "PTAAPPROVER");	
		driver.get(Corp_PTA_Url()+"/trips/"+TripID);
		elementVisible(driver, By.cssSelector("span.status.approved"), 50);
		safeClick(driver, By.xpath("//button"));
		if(!textPresent(driver, "Make a payment to confirm this booking", 5)){
			Reporter.log("Make Payment page is not displayed");
		}
		safeClick(driver, getObject("AgencyHotels_PaymentPage_DepositAccount_Tab"));
		safeClick(driver, By.xpath("//form[2]/div/p/button"));

		for(int i=0; i<=30; i++) {
			if(elementVisible(driver, getObject("HotelCorp_ConfirmationPage_TripID"), 5)){
				break;
			}
			if(elementVisible(driver, By.cssSelector("h1.Failure"), 1)){
				Reporter.log("Oops! Cleartrip’s system is behaving badly");
				Assert.assertTrue(false);
			}
			
		}
		TripID = getText(driver, getObject("HotelCorp_ConfirmationPage_TripID"));
		Reporter.log("Corp PTA Approved trip : " + TripID);
		logger.info( "Corp PTA Approved trip : " + TripID);
	  
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