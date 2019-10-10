package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialRemoveCarousal extends CampActivities {
	@Test
	@Parameters({ "user" })
	public void ttdEditorialCarousalActivities_39717() throws Exception {
		driver.get(baseUrl + "/accounts/sign_in");
		campActivities_SignIN(driver, "SA");
		selectManageEditorial(driver);
		removeCarousal(driver, "Events");

	}

}
