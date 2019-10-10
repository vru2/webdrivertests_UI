package testScriptsAgency;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.AgencyDataProvider;
import domainServices.AgencyHotels;

public class Agency_TripLocal_SSO extends AgencyHotels{
	public RemoteWebDriver driver;

	@Test
	public void  Agency_TripLocal_SingleSignIn() throws Exception{
		driver.manage().deleteAllCookies();
		//driver.get(Agency_Url());
		if (common.value("host").contains("qa2") ) {
			driver.get("https://automationqa2.agentbox.com/");
		}
		else if(common.value("host").contains("www")) {
			driver.get("https://agencydemo3.agentbox.com/");
		} 
		agency_Ctauth_SignIn(driver);
		safeClick(driver, getObjectLocals("LocalCom_HomePage_Locals_Tab"));
		if(elementNotVisible(driver, getObjectLocals("LocalCom_HomePage_Activity_Tab"), 20)){
			Reporter.log("Triplocal home page not loaded");
			Assert.assertTrue(false);
		}
		/*else if(elementNotVisible(driver, getObjectLocals("LocalCom_HomePage_Activity_Tab"), 20)){
			Reporter.log("Triplocal home page loaded");
		} */
		else{
			Reporter.log("Triplocal home page loaded");
		}
	}

	@BeforeClass
	public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
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
