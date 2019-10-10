package com.cleartrip.local.camp.ttd;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageHeroContent extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void editorialHomePageHeroContent_39722(String user) throws Exception {
		campActivities_SignIN(driver, user);

		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "heroTTD");
		homePageHeroContent(driver, "TTD");
		mapHomePage(driver, "");
		if (homePageConfig(driver, "heroTTD") == true) {
			Reporter.log("pass", true);
		} else {
			Reporter.log("Did not found in elastic search", true);
			Assert.fail();

		}

	}
}
