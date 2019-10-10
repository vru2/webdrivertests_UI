package com.cleartrip.local.camp.fnb;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ItemRecommendationsSA extends CampActivities {

	@Parameters({ "user","domain" })
	@Test
	public void manageFNBItemRecommendation_37801(String user, String domain) throws Exception {
		HashMap<String, String> inputs = new HashMap<>();
		if (domain.equals("com")) {
			inputs.put("city", "Bagalko");
			inputs.put("activity", "Restaurant142014202");
		} else {
			inputs.put("city", "Duba");
			inputs.put("activity", "chain point");
		}
		inputs.put("productType", "Food and Beverages");
		

		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/item_recommendations");
		manageItemRecommendation(inputs);
	}

}
