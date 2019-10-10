package com.cleartrip.local.camp.ttd;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class SortListingsSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void manageDataTTDSortListingSA_37700(String user, String domain) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		if (domain.equals("com")) {
			inputTypes.put("city", "Bangalor");
			inputTypes.put("list", "Have An Eagle Eye");
		} else {
			inputTypes.put("city", "Duba");
			inputTypes.put("list", "Peacock Sanctuary");
		}
		inputTypes.put("productType", "Things to do");
		inputTypes.put("collection", "Bird Watching");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/activities/activities_sorting");
		manageSortListings(inputTypes);
	}

}
