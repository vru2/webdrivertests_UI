package testScriptsAccounts;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class HeaderElements extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";
	
	@Test
	public void accountHeaderElements() throws Exception {
		 driver.manage().deleteAllCookies(); 
		   driver.get(baseUrl);
		   if (!checkIfSignedIn(driver)) {
		   Accounts_HomepageSignIn(driver); 		
		   }
          safeClick(driver, getObject("Acc_Menu_dropdown"));
 		
 		safeClick(driver,getObject("Acc_Trip_Link"));
 		textPresent_Log(driver, "Trips you've booked", 10);
 		safeClick(driver,getObject("Acc_Trip_Tab"));
		elementPresent_log(driver, getObject("Acc_Trips_UpcomingTrips"), "Upcoming", 5);	
		safeClick(driver,getObject("Acc_Shortlist_Tab"));
	/*	
		safeClick(driver,getObject("Acc_Shortlist_Tab"));
		elementPresent_log(driver, By.cssSelector("ul.product-tabs > li"),"flights tab", 5);	
		safeClick(driver, By.cssSelector("ul.product-tabs > li"));
 		textPresent(driver, "Shortlists", 2);*/
		safeClick(driver,getObject("Acc_Traveller_Tab"));
		elementPresent_log(driver, getObject("Acc_AddTraveller_button"), "+ Add a new traveller", 5);	
		
		safeClick(driver,getObject("Acc_ExpressWay_Tab"));
		if(elementPresent(driver, getObject("Acc_StartExpressway_button"), 5)){	
 		textPresent_Log(driver, "Start by adding a card", 2);
		}else if(elementPresent(driver,By.xpath(".//*[@id='startExpress']"),10)){
			textPresent_Log(driver,"Your saved cards",10);
		}
		safeClick(driver,getObject("Acc_Profile_Tab"));
		elementPresent_log(driver, getObject("Acc_Profile_EditProfile"), "Edit Profile", 5);	
		
		safeClick(driver,getObject("Acc_Settings_Tab"));
		elementPresent_log(driver, getObject("Acc_Settings_Username"), "Change username", 10);	
	
	}

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
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