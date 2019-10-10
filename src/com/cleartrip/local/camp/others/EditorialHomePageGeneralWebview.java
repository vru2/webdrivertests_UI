package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageGeneralWebview extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void editorialHomePageGeneralWebview_39747(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "GeneralWebview");
		homePagegeneralWebview(driver);
		mapHomePage(driver, "");
		homePageConfig(driver, "GeneralWebview");
	}

}
