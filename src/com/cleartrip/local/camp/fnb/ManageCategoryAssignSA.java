package com.cleartrip.local.camp.fnb;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ManageCategoryAssignSA extends CampActivities {
	@Parameters({ "user" })
	@Test
	public void manageDataFNBAssignSA_37706(String user) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		inputTypes.put("productType", "Food and Beverages");
		inputTypes.put("category", "Food Festival");
		inputTypes.put("collection", "Chennai Spl");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/manage_themes");
		manageAssignCollection(inputTypes);
	}

}
