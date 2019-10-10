package com.cleartrip.local.camp.others;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class CampWLWorkflow extends CampActivities {
	//public RemoteWebDriver driver;

	@Parameters({"user"})
	@Test
	public void campWlFlow_39749(String user) throws Exception {
		campActivities_SignIN(driver,user);
	/*	campAccountApprove(driver);
		campActivities_SignIN(driver, "SA");
	*/	wlAccountPage(driver);
		wlAccountConfig(driver);
		wlSiteConfig(driver);
		communicationConfig(driver);
		contentConfig(driver);

	}

}
