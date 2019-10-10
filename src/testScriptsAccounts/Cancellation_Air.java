// Framework - cleartrip Automation
// Author - Kiran Kumar

package testScriptsAccounts;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.Accounts;

public class Cancellation_Air extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";


	  @BeforeClass
	  public void setUp() throws Exception {
		  driver=(RemoteWebDriver) getDriver(driver);
		  baseUrl = getBaseUrl( "com");			
		}

	@Test
	public void CancelAir() throws Exception {
			driver.manage().deleteAllCookies(); 
			driver.get(baseUrl);
			ArrayList<String> tripID  = db_GetTripUserID("Air");
			String TripID= tripID.get(0);
			Reporter.log("Air trip being cancelled "+TripID);
			driver.get(baseUrl);
			Accounts_HomepageSignIn_User(driver, "QA");
			Thread.sleep(5000);
			driver.get(baseUrl+"/account/trips/"+TripID);
			Thread.sleep(5000);		  
			elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader"), 2);		  
			elementVisible(driver, By.cssSelector("a.toolBarButton"), 10);
			textAssert(driver, "Some transactions in this booking are open.", 5);
			elementVisible(driver, By.cssSelector("a.cancleButton"), 10);
			safeClick(driver, By.cssSelector("a.cancleButton"));
			textAssert(driver, "This trip cannot be cancelled online", 5);
		 	Thread.sleep(5000);		  
		 	elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader"), 2);		  
		 	textAssert(driver, "Select passengers to see refund amount", 10);
		 	smartClick(driver, By.xpath("//label/span/input"));

		 	textAssert(driver, "Choose reason for cancellation", 1);
		 	smartClick(driver, By.name("cancellationReason"));
		 	
		 	smartClick(driver, By.id("trip_review_cancel"));
		 	textAssert(driver, "Review Refund", 10);
		 	elementPresent_log(driver, By.id("noCancel"), "noCancel", 5);
		 	smartClick(driver, By.id("card"));
		 	safeClick(driver, By.cssSelector("li > #trip_cancel"));
		 	elementNotVisible(driver, By.cssSelector("section.cancellations.colZero.col16.content > #pageLoader > h3"), 5);
		 	elementPresent_log(driver, By.cssSelector("div.blockMessage.good.icon32.abs"), "Cancel Sucessfull", 5);
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
