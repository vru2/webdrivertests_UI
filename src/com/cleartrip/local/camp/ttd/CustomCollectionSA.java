package com.cleartrip.local.camp.ttd;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class CustomCollectionSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void manageDataTTDReorderSA_37722(String user, String domain) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		if (domain.equals("com")) {
			inputTypes.put("city", "Bangalor");
			inputTypes.put("activity", "Backwater tour");
		} else {
			inputTypes.put("city", "Duba");
			inputTypes.put("activity", "dubai fountains");
		}
		inputTypes.put("productType", "Things to do");
		inputTypes.put("collection", "BangaloreCleartrip");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/custom_collections_mapping");
		manageCustomCollection(inputTypes);
	}
}
