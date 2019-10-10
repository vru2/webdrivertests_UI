package com.cleartrip.local.camp.events;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ManageCategoryAssignSA extends CampActivities {

	@Parameters({ "user" })
	@Test
	public void manageDataEventsAssignSA_37710(String user) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		inputTypes.put("productType", "Events");
		inputTypes.put("category", "Music");
		inputTypes.put("collection", "Night life");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/manage_themes");
		manageAssignCollection(inputTypes);
	}

}
