package com.cleartrip.local.camp.fnb;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class FNBCustomCollectionSA extends CampActivities {

	@Parameters({ "user","domain" })
	@Test
	public void manageDataFNBCustomCollectionSA_37723(String user, String domain) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		if (domain.equals("com")) {
			inputTypes.put("city", "Bangalor");
			inputTypes.put("activity", "Lunch");
		} else {
			inputTypes.put("city", "Duba");
			inputTypes.put("activity", "butter");
		}
		inputTypes.put("productType", "Food and Beverages");
		inputTypes.put("collection", "Dinner Buffets");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/custom_collections_mapping");
		manageCustomCollection(inputTypes);
	}

}
