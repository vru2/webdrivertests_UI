package com.cleartrip.local.camp.ttd;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageLayoutCarousal extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void editorial_HomePage_Layout_Carousal_39687(String user) throws Exception {
		campActivities_SignIN(driver, user);
		waitForPageLoading(4);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "TTDCarousal");
		homeEditorialCarousal(driver, "TTD");
		mapHomePage(driver, "");
		if (homePageConfig(driver, "TTDCarousal") == true) {
			Reporter.log("TTDCarousal "+common.value("releasenumber")+" is present is elastic Search",true);
		} else {
			Reporter.log("TTDCarousal "+common.value("releasenumber")+" isnot present is elastic Search",true);
			Assert.fail();

		}

	}
}
