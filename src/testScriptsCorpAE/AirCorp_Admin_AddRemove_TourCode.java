package testScriptsCorpAE;

import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;


public class AirCorp_Admin_AddRemove_TourCode extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
	
	@DataProvider(name = "traveller_details")
    public Object [ ][ ] traveller_details() {
        return new Object [ ] [ ] { { "QR","Tour"+System.currentTimeMillis() } };
    }

    @Test(dataProvider = "traveller_details")
    public void Corp_Addremove_TourCodes_474(String tcname, String tourcode) throws Exception {
    	driver.get(Corp_AE_Url());
		corp_SignIn(driver);
		textPresent(driver, "Fly anywhere. Fly everywhere.", 10);
		safeClick(driver, getObject("AirCorpCom_HomePage_Travellers_Tab"));
		elementPresent_Time(driver, getObject("AirCorpCom_Traveller_Add_Traveller_Button"), 10);
		safeClick(driver, getObject("AirCorpCom_CompanySettings_Link"));
		textPresent(driver, "Account details & settings", 5);
		safeClick(driver, getObject("AirCorp_CompanySettings_Tourcodes"));
		elementPresent_Time(driver, getObject("AirCorp_CompanySettings_Tourcodes_addbtn"), 5);
		safeClick(driver, By.xpath("//p/a/img"));
		elementVisible(driver, By.id("tourCodeAirline_new"), 5);
		int i = 1;
		for( i=1; i<=10; i++) {
			String Xpath_New_Code = "//div["+i+"]/input";
			if(elementNotVisible(driver, By.xpath(Xpath_New_Code), 1)) {
				i=i-1;
				break;
			}
			
		}
		String Xpath_New_Codes = "//div["+i+"]/input";

		String Xpath_New_Codess = "//div["+i+"]/input[3]";
		safeAutocomplete(driver, By.xpath(Xpath_New_Codes), By.xpath("//body/ul/li"), tcname);
		safeType(driver, By.xpath(Xpath_New_Codess), tourcode);
/*		
		safeAutocomplete(driver, getObject("AirCorp_CompanySettings_Tourcodes_tcname"), getObject("AirCorp_InputTxt_Ajax"), tcname);
		safeType(driver, getObject("AirCorp_CompanySettings_Tourcodes_tccode"), tourcode);*/
		safeClick(driver, getObject("AirCorp_CompanySettings_Tourcodes_addbtn"));
		assertTrue("Tour Codes Not Updated", textPresent(driver, "Tour codes updated successfully", 10));
		//safeClick(driver, By.cssSelector("img.remove"));
		safeClick(driver, By.xpath("//div[2]/a/img"));
		
		//elementNotVisible(driver, By.cssSelector("img.remove"), 10);
		safeClick(driver, getObject("AirCorp_CompanySettings_Tourcodes_addbtn"));
		//assertTrue("Tour code not deleted", textPresent(driver, "Register Tour Codes here", 10));
		assertTrue("Tour Codes Not Updated", textPresent(driver, "Tour codes updated successfully", 10));
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
