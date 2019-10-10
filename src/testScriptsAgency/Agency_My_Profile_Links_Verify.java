// Framework - cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - 30 June 2015
// Author - Prashanth S
// Copyright © 2015 cleartrip Travel. All rights reserved.
package testScriptsAgency;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.Agency;

public class Agency_My_Profile_Links_Verify extends Agency {
	
	public RemoteWebDriver driver;
	
	@Test(groups="Regression")
	public void Agency_MyProfilePage_310() throws Exception {
		driver.get(Agency_Url());
		agency_SignIn(driver);
		Agency_MyProfile(driver);
}
	@BeforeClass
	public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	}

	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
	driver.quit();    
	}
}
