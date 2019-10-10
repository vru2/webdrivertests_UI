// Framework - cleartrip Automation
// Author - Kiran Kumar

package testScriptsAccounts;

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

import domainServices.Accounts;

public class Cancellation_Hotels extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";


	  @BeforeClass
	  public void setUp() throws Exception {
		  driver=(RemoteWebDriver) getDriver(driver);
		  baseUrl = getBaseUrl( "com");	
		}

	@Test
	public void CancelHotels() throws Exception {
		 driver.manage().deleteAllCookies(); 
		  ArrayList<String> tripID  = db_GetTripUserID("Hotel");
		  String TripID= tripID.get(0);
		  Reporter.log("Hotel trip being cancelled "+TripID);
		  driver.get(baseUrl);
		  Accounts_HomepageSignIn_User(driver, "QA");
		  Thread.sleep(5000);
		  driver.get(baseUrl+"/account/trips/"+TripID);
		  Thread.sleep(5000);		  
		  elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader"), 10);
		  if(textPresent(driver, "Sorry, our system is acting up.", 2)) {
					Reporter.log("Sorry, our system is acting up. : error is displayed");
					logURL(driver);
					Assert.assertTrue(false);
				}
				if(textPresent(driver, "Sorry, unable to get your details right now.", 2)) {
					Reporter.log("Sorry, unable to get your details right now.. : error is displayed");
					refreshPage(driver);
				}
				if(!elementVisible(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), 20)) {
					refreshPage(driver);
				}
				elementVisible(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), 10);
				textAssert(driver, getObjectHotels("HotelCom_TripPage_TextMessage"), "Trips you've booked");
				
				String Trip_status = getText(driver, getObjectHotels("HotelCom_Account_TripsPage_TripStatus"));  // To check the Trip is confirmed or not
				if(!Trip_status.equals("CONFIRMED"))
				{
					Reporter.log("Trip status is not confirmed but is "+Trip_status);
					Assert.assertEquals(Trip_status, "CONFIRMED");
				}
				safeClick(driver, getObjectHotels("HotelCom_AccountPage_Cancel_Button"));
				elementVisible(driver, getObjectHotels("HotelCom_CancellationPage_Text"),60);
				textAssert(driver, getObjectHotels("HotelCom_CancellationPage_Text"), "Review cancellations & confirm");
				smartClick(driver, getObjectHotels("HotelCom_CancellationPage_Card_Radio_Button"));
			    safeClick(driver, getObjectHotels("HotelCom_CancellationPage_TripCancellation_Button"));	
			    elementVisible(driver, getObjectHotels("HotelCom_CancellationPage_TripCancelled_Confirmation"), 30);
			    textAssert(driver, getObjectHotels("HotelCom_CancellationPage_TripCancelled_Confirmation"), "Your hotel booking was cancelled successfully");
			    Reporter.log("Your hotel booking was cancelled successfully message is displayed");
			    db_removeTrip(TripID);	
	}

	@AfterClass
	public void closeSelenium() throws Exception {
		browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
}
