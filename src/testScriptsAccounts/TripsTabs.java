package testScriptsAccounts;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;

public class TripsTabs extends Accounts

{
	public RemoteWebDriver driver = null;
	String domain = "com";

	@Test 
	public void TripsTab() throws Exception {
		 driver.manage().deleteAllCookies(); 

		driver.get(baseUrl);
		if (!checkIfSignedIn(driver)) {
			Accounts_HomepageSignIn(driver);
		}

		Thread.sleep(1000);

		boolean b2cAccountPage = getAccountsPage(driver);
		if (b2cAccountPage == false) {
			Reporter.log("Error in the flow. Accounts page not loading!");
		}
		/*WebDriverWait wait = new WebDriverWait(driver, 40);*/
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("trips_tab")));
		safeClick(driver, getObject("Acc_Trips_Tab"));
		/*wait.until(ExpectedConditions.elementToBeClickable(By.id("upcomingTripsLink")));*/
		safeClick(driver, getObject("Acc_Trips_AllTrips_Tab"));
		safeClick(driver, getObject("Acc_Trips_Upcoming_Tab"));
		safeClick(driver, getObject("Acc_Trips_First_TripID"));
		textPresent_Log(driver, "Payment Details", 5);
		/*wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]")));
		driver.findElement(By.xpath("/html/body/div[1]")).click();
	*/
		elementPresent_log(driver, getObject("Acc_Trips_TripDetails"), "trip details ", 5);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		//safeClick(driver, By.id("travellers_tab"));
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("shortlists_tab"))).click();
		//textAssert(driver, "Your shortlisted hotels will be shown here", 2);

		safeClick(driver, By.id("travellers_tab"));
		elementPresent_log(driver, By.id("addTraveller"), "Add traveller is not present", 10);
		textAssert(driver, "People you book travel for", 1);
		//elementPresent(driver, By.id("addTraveller1"));
		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("express_tab"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("profile_tab"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("settings_tab"))).click();	
		/*safeClick(driver, By.id("wallet_tab"));
		elementPresent_log(driver, By.cssSelector("div.col.col8.blankIcon.wallet"), "Wallet", 10);
		textAssert(driver, "Faster refunds for faster booking", 1);
		
		safeClick(driver, By.linkText("Learn more"));
		if(Host.equalsIgnoreCase("www")) {
			elementPresent_log(driver, By.cssSelector("div.wallet"), "Wallet image", 10);
			textAssert(driver, "No more waiting for refunds after cancellations ", 1);
		
		}*/
		
		
// below script for new wallet account
	/*	safeClick(driver, By.id("wallet_tab"));
		elementPresent_log(driver, By.cssSelector("div.col.col8.blankIcon.wallet"), "Wallet", 10);
		textAssert(driver, "Faster refunds for faster booking", 1);
		
		safeClick(driver, By.linkText("Learn more"));
		if(Host.equalsIgnoreCase("www")) {
			elementPresent_log(driver, By.cssSelector("div.wallet"), "Wallet image", 10);
			textAssert(driver, "No more waiting for refunds after cancellations ", 1);
		
		}*/
		
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