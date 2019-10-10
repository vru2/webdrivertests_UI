package com.cleartrip.local.camp.fnb;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import domainServices.CampActivities;

public class EditorialHomePageLayoutActivityList extends CampActivities {

	@Parameters({"user"})
	@Test
	public void editorial_HomePage_ActivityList_39690(String user) throws Exception {
		campActivities_SignIN(driver,user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver, "FNBActivityList");
		homeEditorialactivityList(driver, "FNB");
		mapHomePage(driver, "");
		if (homePageConfig(driver, "FNBActivityList") == true) {
			Reporter.log("Fnb Activity found elastic search",true);
		} else {
			Reporter.log("Fnb Activity didn't find elastic search",true);
			Assert.fail();

		}

	}
}
