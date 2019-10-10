package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalOfferCollection extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void eventEditorialCarousalOfferCollection_39736(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "CarousalOfferCollection");
		EditorialType(driver, "Carousal", "Events");
		contentType(driver, "OfferCollection", "Events");
		mapCarousalEvent(driver, true);
		orderCarousal(driver, "Events", campLocal.value("campCity"), "CarousalOfferCollection");

	}
}
