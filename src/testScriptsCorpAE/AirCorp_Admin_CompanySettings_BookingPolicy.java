package testScriptsCorpAE;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.*;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.thoughtworks.selenium.Wait.WaitTimedOutException;

import dataServices.CorporateDataProvider;
import domainServices.Corporate;
import domainServices.Corporate;


public class AirCorp_Admin_CompanySettings_BookingPolicy extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@BeforeClass
	public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	}
	
	@DataProvider(name = "traveller_details")
    public Object [ ][ ] traveller_details() {
        return new Object [ ] [ ] { { "100"} };
    }

    @Test(dataProvider = "traveller_details")
    public void Corp_VerifyBookingPolicy_473(String amount) throws Exception {
    	
    	driver.get(Corp_AE_Url());
		corp_SignIn(driver);
		textPresent(driver, "Fly anywhere. Fly everywhere.", 10);
		
		safeClick(driver, getObject("AirCorpCom_HomePage_Travellers_Tab"));
		elementPresent_Time(driver, getObject("AirCorpCom_Traveller_Add_Traveller_Button"), 20);
		safeClick(driver, getObject("AirCorpCom_CompanySettings_Link"));
		textPresent(driver, "Account details & settings", 5);
		safeClick(driver, getObject("AirCorpCom_BookingPolicy_link"));
		textPresent(driver, "Travel booking policy", 5);
		safeClick(driver, getObject("AirCorpCom_CompanySettings_BookingPolicy_AmountRadiobtn"));
		safeType(driver, getObject("AirCorpCom_CompanySettings_BookingPolicy_AmountInputfld"), amount);
		safeClick(driver, getObject("AirCorpCom_CompanySettings_BookingPolicy_SaveChangesBtn"));
		assertTrue("Booking Policy Not Updated", textPresent(driver, "Booking policy saved", 10));
		safeType(driver, getObject("AirCorpCom_CompanySettings_BookingPolicy_AmountInputfld"), "");
		safeClick(driver, getObject("AirCorpCom_CompanySettings_BookingPolicy_SaveChangesBtn"));
		Thread.sleep(3000);
	
    }

	@AfterMethod(alwaysRun = true)	
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}

