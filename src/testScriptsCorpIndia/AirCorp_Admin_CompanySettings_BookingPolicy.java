package testScriptsCorpIndia;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Admin_CompanySettings_BookingPolicy extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	
	@DataProvider(name = "traveller_details")
    public Object [ ][ ] traveller_details() {
        return new Object [ ] [ ] { { "100"} };
    }

    @Test(dataProvider = "traveller_details")
    public void Corp_VerifyBookingPolicy_260(String amount) throws Exception {
    	driver.get(Corp_Url());
		corp_SignIn(driver);
		textPresent(driver, "Fly anywhere. Fly everywhere.", 10);		
		safeClick(driver, getObject("AirCorpCom_HomePage_Travellers_Tab"));
		elementPresent_Time(driver, getObject("AirCorpCom_Traveller_Add_Traveller_Button"), 10);
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

	@BeforeClass
	public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
	 browserClose(driver);
	}
}
