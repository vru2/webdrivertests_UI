package com.cleartrip.local.camp.fnb;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ReorderSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void manageDataFNBReorderSA_37693(String user, String domain) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		if (domain.equals("com")) {
			inputTypes.put("city", "Bangalor");
		} else
			inputTypes.put("city", "Duba");
		inputTypes.put("productType", "Food and Beverages");
		inputTypes.put("collection", "All Day dining");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/reorder");
		manageReorder(inputTypes);
	}

}
