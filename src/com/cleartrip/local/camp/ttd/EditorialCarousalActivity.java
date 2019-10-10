package com.cleartrip.local.camp.ttd;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalActivity extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void ttdEditorialCarousalActivities_39717(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "CarousalActivity");
		EditorialType(driver, "Carousal", "TTD");
		contentType(driver, "Activities", "TTD");
		mapCarousal(driver, "TTD", false);
		orderCarousal(driver, "TTD", campLocal.value("campCity"), "CarousalActivity");

	}
}
