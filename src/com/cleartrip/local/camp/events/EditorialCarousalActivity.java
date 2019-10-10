package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalActivity extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void eventEditorialCarousalActivities_39732(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "CarousalActivity");
		EditorialType(driver, "Carousal", "Events");
		contentType(driver, "Activities", "Events");
		mapCarousalEvent(driver, false);
		orderCarousal(driver, "Events", campLocal.value("campCityEvent"), "CarousalActivity");

	}
}
