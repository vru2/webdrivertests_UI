package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalOfferAll extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void eventEditorialCarousalOfferAll_39735(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "CarousalOfferAll");
		EditorialType(driver, "Carousal", "Events");
		contentType(driver, "OfferAll", "Events");
		mapCarousalEvent(driver, true);
		orderCarousal(driver, "Events", campLocal.value("campCity"), "CarousalOfferAll");

	}
}
