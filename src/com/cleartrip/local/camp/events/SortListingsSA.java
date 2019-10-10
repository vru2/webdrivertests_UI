package com.cleartrip.local.camp.events;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class SortListingsSA extends CampActivities {

	@Parameters({ "user" ,"domain"})
	@Test
	public void manageDataTTDSortListingSA_37702(String user, String domain) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		if (domain.equals("com")) {
			inputTypes.put("city", "Bangalor");
		} else {
			inputTypes.put("city", "Duba");
		}
		inputTypes.put("productType", "Events");
		inputTypes.put("collection", "Kids Event");

		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/activities/activities_sorting");
		manageSortListings(inputTypes);

	}

}
