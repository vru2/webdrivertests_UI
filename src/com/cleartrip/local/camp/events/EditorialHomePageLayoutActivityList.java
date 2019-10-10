package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageLayoutActivityList extends CampActivities

{
	@Parameters({ "user" })
	@Test
	public void editorialHomePageActivityList_39738(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "EventActivityList");
		homeEditorialactivityList(driver, "Events");
		mapHomePage(driver, "");
		homePageConfig(driver, "EventActivityList");

	}
}
