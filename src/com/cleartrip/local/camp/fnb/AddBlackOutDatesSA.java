package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AddBlackOutDatesSA extends CampActivities {
	
	@Parameters({"user"})
	@Test
	public void addBackOutDates_35742(String user) throws Exception {
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/activities");
		fndAddBackDate();

	}
}
