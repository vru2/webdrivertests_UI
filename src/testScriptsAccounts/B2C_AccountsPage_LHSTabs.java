package testScriptsAccounts;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class B2C_AccountsPage_LHSTabs extends Accounts{
	public RemoteWebDriver driver = null;
	String domain = "com";

	@Test 
	public void Accounts_LHStabs() throws Exception {
		driver.manage().deleteAllCookies(); 
		driver.get(baseUrl);
		if(ProductionUrl){
			Accounts_HomepageSignInProd(driver);
			  elementVisible(driver,getObjectPlatform("Account_Your_Trips"), 10);
		  	   safeClick(driver,getObjectPlatform("Account_Your_Trips"));
		  	   safeClick(driver,getObjectPlatform("B2C_ACCOUNT_TRIPSLINK"));
		  	   Thread.sleep(2000);
		  	   textPresent_Log(driver,"Trip ID",5);
		  	   Thread.sleep(2000);
		  	   elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader > h3"), 10);
		  	   elementPresent_log(driver,getObject("Acc_ExpressWay_Tab"),"expressway tab",5);
		}else{
			Accounts_HomepageSignIn(driver);
	   elementVisible(driver,getObjectPlatform("Account_Your_Trips"), 10);
  	   safeClick(driver,getObjectPlatform("Account_Your_Trips"));
  	   safeClick(driver,getObjectPlatform("B2C_ACCOUNT_TRIPSLINK"));
  	   elementPresent_log(driver,getObject("Acc_Trips_Upcoming_Tab"),"Upcoming trips tab",20);
  	   elementPresent_log(driver,getObject("Acc_Trips_AllTrips_Tab"),"All trips tab",5);
  	   textPresent_Log(driver,"Trip ID",5);
  	   Thread.sleep(2000);
  	   elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader > h3"), 10);
  	   elementPresent_log(driver,getObject("Acc_ExpressWay_Tab"),"expressway tab",5);
  	  
  	   // Trips tab
  	    safeClick(driver,getObject("Acc_Trips_Upcoming_Tab"));
		}
  	    String TripID = null;
  	  if(ProductionUrl) {
  		TripID="190401816972";
  	  }
  	  else TripID = "Q190810461608"; 
  		driver.get(baseUrl+"/account/trips/"+TripID);
  		 elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader > h3"), 20);
  		elementPresent_log(driver, By.cssSelector("a.truncate.span.span24"), "Trip Name / details", 30);
  		textPresent_Log(driver,"Trip ID",1);
  		textPresent_Log(driver,"Contact Details",1);
  		textPresent_Log(driver,"Payment Details",1);
  		
  		safeClick(driver,getObjectPlatform("B2C_TripList_Page"));
  		 elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader > h3"), 10);
  	  	elementPresent_log(driver,getObject("Acc_ExpressWay_Tab"),"expressway tab",5);
  		
  		// Shortlist tab
  		safeClick(driver,getObjectPlatform("B2C_Accounts_Shortlist_Link"));
 		 elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader > h3"), 10);
  		elementPresent_log(driver,By.xpath("//section[@id='shortlists']/div/section/nav/ul/li"),"Flights tab",2);
  		
  		// Travellers
  		safeClick(driver,getObjectPlatform("B2C_Account_Travellers_Link"));
 		elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader > h3"), 10);
  		elementPresent_log(driver,By.id("addTraveller"),"Add Travellers button",20);
	  	elementVisible(driver,By.id("addTraveller"), 1);
  		textPresent_Log(driver,"People you book travel for",20);
  		textPresent_Log(driver,"Contact details",5);
  		
  		// Wallet
  		safeClick(driver,getObjectPlatform("B2C_Account_Wallet_Link"));
  		if(ProductionUrl) {
  			elementPresent_log(driver,By.xpath("//div[@class='row pad featured']/h2"),"Current balance in your wallet", 30);
  	  		elementVisible(driver,By.xpath("//div[@class='row pad featured']/h2"), 30);
  			textPresent_Log(driver,"Current balance in your wallet", 1);
  			
  		}else{
  	  	elementPresent_log(driver,By.xpath("//a[@id='walletFilter']/span"),"Wallet Filter", 20);
  		elementVisible(driver, By.xpath("//a[@id='walletFilter']/span"), 1);
  		textPresent_Log(driver,"Current balance in your wallet", 1);
  		textPresent_Log(driver,"Transaction history", 1);
  		}
  		
  		// ExpressWay
  		safeClick(driver,getObjectPlatform("B2C_Account_ExpressWay_Link"));
 		elementNotVisible(driver, By.cssSelector("section.content.colZero.col16.CentreCol.eightcol > #pageLoader > h3"), 10);
  		elementPresent_log(driver,By.id("startExpress"),"Add Express card", 20);
  		elementVisible(driver,By.id("startExpress"), 1);
  		textPresent_Log(driver,"Expressway",5);
  		
  		
  		// Profile
  		safeClick(driver,getObjectPlatform("B2C_Account_Profile_Link"));	
  		elementPresent_log(driver,By.xpath("//a[contains(text(),'Edit Profile')]"),"edit profile button", 30);
  		
  		elementVisible(driver,By.xpath("//a[contains(text(),'Edit Profile')]"), 1);
  		textPresent_Log(driver,"Contact details", 1);
  		textPresent_Log(driver,"General information",1);
  	
        // Settings
  		safeClick(driver,getObjectPlatform("B2C_Account_Settings_Link"));
  		elementPresent_log(driver,By.id("change_username"),"Change username button", 30);
  		elementVisible(driver,By.id("change_username"), 1);
  		textPresent_Log(driver,"Your login details", 1);
  		textPresent_Log(driver,"Email newsletter", 1);
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
