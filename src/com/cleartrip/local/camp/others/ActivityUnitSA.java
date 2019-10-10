package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ActivityUnitSA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void manageDataMasterListActivityUnitSA_36437(String user) throws Exception {
		campActivities_SignIN(driver,user);
		String inputActivityUnit = "Auto_Qa2_Activity";
		driver.get(baseUrl + "/manage_data/master_list");
		addMasterData("Activity Units", inputActivityUnit);

	}
}
