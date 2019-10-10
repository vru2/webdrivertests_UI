package com.cleartrip.local.camp.events;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ManageCategoryOrderSA extends CampActivities {

	@Parameters({ "user","domain" })
	@Test
	public void manageDataEventsOrderCategorySA_37721(String user, String domain) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		if (domain.equals("com")) {
			inputTypes.put("city", "Bangalor");
		} else {
			inputTypes.put("city", "Duba");
		}
		inputTypes.put("productType", "Events");
		inputTypes.put("category", "Music");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/manage_themes");
		manageOrderCollection(inputTypes);
	}

}
