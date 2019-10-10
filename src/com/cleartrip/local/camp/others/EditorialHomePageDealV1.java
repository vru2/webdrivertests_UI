package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageDealV1 extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void editorialHomePageDealV1_39744(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "DealV1");
		homePageDeal(driver, "DealV1");
		mapHomePage(driver, "");
		homePageConfig(driver, "DealV1");
	}

}
