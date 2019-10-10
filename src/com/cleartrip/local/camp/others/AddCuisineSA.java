package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AddCuisineSA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void manageDataMasterAddCuisineSA_36438(String user ) throws Exception {
		campActivities_SignIN(driver,user);
		String inputCuisineUnit = "Auto_Qa2_Cuisine";
		driver.get(baseUrl + "/manage_data/master_list");
		addMasterData("Cuisines", inputCuisineUnit);
	}

}
