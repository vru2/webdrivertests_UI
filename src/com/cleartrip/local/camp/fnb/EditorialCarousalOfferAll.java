package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalOfferAll extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void fnbEditorialCarousalOfferAll_39728(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "CarousalOfferAll");
		EditorialType(driver, "Carousal", "FNB");
		contentType(driver, "OfferAll", "FNB");
		mapCarousal(driver, "FNB", true);
		orderCarousal(driver, "FNB", campLocal.value("campCity"), "CarousalOfferAll");

	}
}