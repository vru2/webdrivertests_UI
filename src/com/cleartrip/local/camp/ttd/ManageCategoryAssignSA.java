package com.cleartrip.local.camp.ttd;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ManageCategoryAssignSA extends CampActivities {

	@Parameters({ "user" })
	@Test
	public void manageDataTTDAssignSA_37704(String user) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		inputTypes.put("productType", "Things to do");
		inputTypes.put("category", "Testing ttd");
		inputTypes.put("collection", "Biking Tour");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/manage_themes");
		manageAssignCollection(inputTypes);
	}
}
