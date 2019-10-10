package com.cleartrip.local.camp.events;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ItemRecommendationsSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void manageEventsItemRecommendation_37802(String user, String domain) throws Exception {
		HashMap<String, String> inputs = new HashMap<>();
		if (domain.equals("com")) {
			inputs.put("city", "Bangalor");
		} else {
			inputs.put("city", "Duba");
		}
		inputs.put("productType", "Events");
		inputs.put("activity", "Testing of event");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/item_recommendations");
		manageItemRecommendation(inputs);
	}

}
