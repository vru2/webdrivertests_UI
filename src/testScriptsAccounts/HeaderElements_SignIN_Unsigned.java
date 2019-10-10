// Framework - cleartrip Automation
// Author - Kiran Kumar

package testScriptsAccounts;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Accounts;
import junit.framework.Assert;

public class HeaderElements_SignIN_Unsigned extends Accounts {

	public RemoteWebDriver driver = null;
	String domain = "com";
	

	@Test
	public void accountHeaderSignin() throws Exception {
		 driver.manage().deleteAllCookies(); 
		   driver.get(baseUrl);
		   if (!checkIfSignedIn(driver)) {
			   Accounts_HomepageSignIn(driver);
		   }
 		elementPresent_log(driver, By.linkText("Manage trips"), " Manage trips ", 5);
		elementPresent_log(driver, getObject("Acc_Menu_dropdown"), "Mytrips Button Homepage ", 20);
		safeClick(driver, getObject("Acc_Menu_dropdown"));
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoTrips"), " Trips ", 5);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoShortlists"),  "Shortlists ", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoTravellers"),  "Travellers ", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoWallet"),  "Wallet ", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoExpressway"),  "Expressway ", 1);
 		elementPresent_log(driver, By.linkText("Profile"),  "Profile ", 1);
 		elementPresent_log(driver, By.linkText("Settings"),  "Settings ", 1);
 		elementPresent_log(driver, By.id("global_signout"), "global_signout ", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoCancel"), "Cancellations", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoChange"), "Change flight", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoPNR"), "Check PNR Status", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoTicket"), "Print hotel voucher", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoHotelVoucher"), "Print ticket", 1);
 		safeClick(driver, By.id("global_signout"));
 		String YourTrip = getText(driver, By.cssSelector("span.span.span19.truncate"));
 		if(!YourTrip.contains("Your trips")) {
 			Reporter.log("Signout is not working");
 		Assert.assertTrue(false);
 		}
	}
	
	@Test(dependsOnMethods={"accountHeaderSignin"}, alwaysRun=true)
	public void accountHeaderUnsigned1() throws Exception {
		 driver.manage().deleteAllCookies(); 
		driver.get(baseUrl);
		if(!elementVisible(driver, getObject("Acc_Menu_dropdown"), 20)) {
			refreshPage(driver);
		}
 		safeClick(driver, getObject("Acc_Menu_dropdown"));
 		elementPresent_log(driver, By.id("SignIn"), " SignIn ", 5);
 		elementPresent_log(driver, By.linkText("Register"), "Register link ", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoCancel"), "Cancellations", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoChange"), "Change flight", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoPNR"), "Check PNR Status", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoTicket"), "Print hotel voucher", 1);
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoHotelVoucher"), "Print ticket", 1);
 	
 		String emailID = 	getDate("yyMMddmmss")+"@1234.com";
 		safeClick(driver, By.linkText("Register"));
 		Thread.sleep(5000);
 		String mainWindow = driver.getWindowHandle();

		driver.switchTo().frame("modal_window");	
		elementPresent(driver, By.id("username1"));
		textPresent_Log(driver, "Sign up with your current email address", 5);
		safeType(driver, By.id("username1"), emailID);
		safeClick(driver, By.id("registerButton"));
		Thread.sleep(5000);
		textPresent_Log(driver, "Get a Cleartrip Account", 5);
		driver.switchTo().window(mainWindow);
		safeClick(driver, By.id("close"));
		elementPresent_log(driver, getObject("Acc_Menu_dropdown"),"", 5);
	 	safeClick(driver, getObject("Acc_Menu_dropdown"));
	 	elementPresent_log(driver, By.id("SignIn"), " SignIn ", 5);
	 	safeClick(driver, By.cssSelector("i.iconSprite.icoCancel"));
		//elementPresent_log(driver, By.id("registerButton4"), "email"	, 5);
		driver.navigate().back();
	 	elementPresent_log(driver, getObject("Acc_Menu_dropdown"), " ", 5);

	 	safeClick(driver, getObject("Acc_Menu_dropdown"));
	 	elementPresent_log(driver, By.cssSelector("i.iconSprite.icoChange"), "Change Flight ", 5);
	 	safeClick(driver, By.cssSelector("i.iconSprite.icoChange"));
		//elementPresent_log(driver, By.id("registerButton4"), "email"	, 5);

		driver.navigate().back();
	 	elementPresent_log(driver, getObject("Acc_Menu_dropdown"), " ", 5);
		safeClick(driver, getObject("Acc_Menu_dropdown"));
 		elementPresent_log(driver, By.cssSelector("i.iconSprite.icoPNR"), "Check PNR Status", 1);
	 	safeClick(driver, By.cssSelector("i.iconSprite.icoPNR"));
		textPresent_Log(driver, "Indian Railways PNR Status Enquiry", 5);
		driver.navigate().back();
	 	elementPresent_log(driver, getObject("Acc_Menu_dropdown"), " ", 5);
		safeClick(driver, getObject("Acc_Menu_dropdown"));
	 	elementPresent_log(driver, By.cssSelector("i.iconSprite.icoTicket"), "Print hotel voucher ", 5);
	 	safeClick(driver, By.cssSelector("i.iconSprite.icoTicket"));
		textPresent_Log(driver, "See and print your eticket", 5);	
		driver.navigate().back();
	 	elementPresent_log(driver, getObject("Acc_Menu_dropdown"), " ", 5);
		safeClick(driver, By.linkText("Manage trips"));
		//textPresent_Log(driver,"Help us find your trip", 5);
		
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