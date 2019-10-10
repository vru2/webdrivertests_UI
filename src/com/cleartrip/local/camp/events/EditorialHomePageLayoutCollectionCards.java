package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageLayoutCollectionCards extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void editorialHomePageLayoutCollectionCards_39740(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "EventCollectionCard");
		homeEditorialCollection(driver, "Events");
		mapHomePage(driver, "Events");
		homePageConfig(driver, "EventCollectionCard");

	}
}
