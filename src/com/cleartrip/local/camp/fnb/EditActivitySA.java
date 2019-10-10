package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import domainServices.CampActivities;

public class EditActivitySA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void FNBEditActivity_35739(String user) throws Exception {
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/activities");
		fnbEditActivity();
	}
}
