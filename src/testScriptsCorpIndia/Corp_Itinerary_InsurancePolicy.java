// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Jan 2017
// Author - Kiran Kumar
// Copyright © 2017 cleartrip Travel. All rights reserved.
package testScriptsCorpIndia;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import domainServices.Corporate;

public class Corp_Itinerary_InsurancePolicy extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";


	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "MAA", "BLR", "1", "0", "0","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void Corp_BookingPolicyItinerary_253(String FromCity, String ToCity, String Adults, String Childrens, String Infants, String Payment_Type) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", "" , 40, 41);
		driver.get(SRP_URL);
		corpAir_SRP(driver, "DOMOW", "");
		 if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 60)){
				Reporter.log("Itinerary Page has not loaded :( :( :( :( :( :( :( :(");
				Assert.assertTrue(false);
			}
		if(!elementVisible(driver, By.linkText("ICICI Lombard Insurance Policy"), 10)){
			Reporter.log("ICICI Insurance policy is not displayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.linkText("ICICI Lombard Insurance Policy"));
		Thread.sleep(5000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
				
		  if(!elementVisible(driver, By.xpath("//h1"), 20)) {
			  Reporter.log("Booking Policy Pop Up is not opened");
			  Assert.assertTrue(false);
		  }
		  textAssert(driver, "Product Details and Coverage Snapshot", 5);
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