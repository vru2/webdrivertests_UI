package com.cleartrip.local.camp.ttd;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalOfferAll extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void TTD_Editorial_Carousal_Offer_All_39720(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "CarousalOfferAll");
		EditorialType(driver, "Carousal", "TTD");
		contentType(driver, "OfferAll", "TTD");
		mapCarousal(driver, "TTD", true);
		orderCarousal(driver, "TTD", campLocal.value("campCity"), "CarousalOfferAll");

	}
}
