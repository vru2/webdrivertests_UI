package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageDealV2 extends CampActivities {
	@Parameters({"user"})
	@Test
	public void editorialHomePageDealV2_39745(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "DealV2");
		homePageDeal(driver, "DealV2");
		mapHomePage(driver, "");
		homePageConfig(driver, "DealV2");
	}
}
