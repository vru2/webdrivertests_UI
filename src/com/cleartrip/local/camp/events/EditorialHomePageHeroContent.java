package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageHeroContent extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void editorialHomePageHeroContent_39737(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "heroEvent");
		homePageHeroContent(driver, "Events");
		mapHomePage(driver, "");
		homePageConfig(driver, "heroEvent");
	}
}
