package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import domainServices.CampActivities;

public class EditorialCarousalOfferCollection extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void fnbEditorialCarousalOfferCollection_39729(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "CarousalOfferCollection");
		EditorialType(driver, "Carousal", "FNB");
		contentType(driver, "OfferCollection", "FNB");
		mapCarousal(driver, "FNB", true);
		orderCarousal(driver, "FNB", campLocal.value("campCity"), "CarousalOfferCollection");

	}
}
