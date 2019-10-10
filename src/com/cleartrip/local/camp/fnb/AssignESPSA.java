package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AssignESPSA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void AssignESP_35746(String user) throws Exception {
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/accounts");
		espAssign();
	}
}
