package com.cleartrip.local.camp.fnb;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class SortListingsSA extends CampActivities {

	@Parameters({ "user","domain" })
	@Test
	public void manageDataTTDSortListingSA_37701(String user,String domain) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		if(domain.equals("com")) {
		inputTypes.put("city", "Bangalor");
		}else {
			inputTypes.put("city", "Duba");
		}
		inputTypes.put("productType", "Food and Beverages");
		inputTypes.put("collection", "All Day dining");
		inputTypes.put("list", "new variant");
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/activities/activities_sorting");
		manageSortListings(inputTypes);

	}

}
