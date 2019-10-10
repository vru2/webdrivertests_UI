package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageLayoutCarousal extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void editorialHomePageLayoutCarousal_39739(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "EventCarousal");
		homeEditorialCarousal(driver, "Events");
		mapHomePage(driver, "");
		homePageConfig(driver, "EventCarousal");

	}
}
