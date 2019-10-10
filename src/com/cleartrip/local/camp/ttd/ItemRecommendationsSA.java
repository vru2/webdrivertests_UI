package com.cleartrip.local.camp.ttd;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ItemRecommendationsSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void manageTTDItemRecommendation_37803(String user, String domain) throws Exception {
		HashMap<String, String> inputs = new HashMap<>();
		if (domain.equals("com")) {
			inputs.put("city", "Bagalko");
		} else {
			inputs.put("city", "Duba");
		}
		inputs.put("productType", "Things to do");
		inputs.put("activity", "test carousal activit");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/item_recommendations");
		manageItemRecommendation(inputs);
	}

}
