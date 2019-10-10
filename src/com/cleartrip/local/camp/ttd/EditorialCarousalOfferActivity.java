package com.cleartrip.local.camp.ttd;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalOfferActivity extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void TTD_Editorial_Carousal_Offer_Activity_39719(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "CarousalOfferActivity");
		EditorialType(driver, "Carousal", "TTD");
		contentType(driver, "OfferActivity", "TTD");
		mapCarousal(driver, "TTD", true);
		orderCarousal(driver, "TTD", campLocal.value("campCity"), "CarousalOfferActivity");

	}
}
