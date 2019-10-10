package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class RestaurantFeatureSA extends CampActivities {

	@Parameters({ "user" })
	@Test
	public void manageDataMasterRestaurantFeatureSA_36443(String user) throws Exception {
		String restaurantFeatureUnit = "Auto_qa2_Restaurent_feature";
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/master_list");
		addMastertagsData("Restaurant Feature", restaurantFeatureUnit);
	}

}
