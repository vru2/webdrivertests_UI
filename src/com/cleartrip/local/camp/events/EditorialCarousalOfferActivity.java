package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalOfferActivity extends CampActivities {
	@Parameters({"user"})
	@Test
	public void eventEditorialCarousalOfferActivity_39734(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "CarousalOfferActivity");
		EditorialType(driver, "Carousal", "Events");
		contentType(driver, "OfferActivity", "Events");
		mapCarousalEvent(driver, true);
		orderCarousal(driver, "Events", campLocal.value("campCity"), "CarousalOfferActivity");

	}
}