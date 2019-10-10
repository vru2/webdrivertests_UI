package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageExplore extends CampActivities {

	@Parameters({ "user" })
	@Test
	public void editorialHomePageExplore_39746(String user) throws Exception {
		campActivities_SignIN(driver, user);
		waitForPageLoading(3);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "homePageExplore");
		homePageExplore(driver);
		mapHomePage(driver, "");
		homePageConfig(driver, "homePageExplore");
	}
}
