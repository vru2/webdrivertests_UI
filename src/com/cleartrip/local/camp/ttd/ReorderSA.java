package com.cleartrip.local.camp.ttd;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ReorderSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void manageDataTTDReorderSA_37692(String user, String domain) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		if (domain.equals("com")) {
			inputTypes.put("city", "Bangalor");
		} else {
			inputTypes.put("city", "Duba");
		}
		inputTypes.put("productType", "Things to do");
		inputTypes.put("collection", "Bird Watching");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/reorder");
		manageReorder(inputTypes);

	}

}
