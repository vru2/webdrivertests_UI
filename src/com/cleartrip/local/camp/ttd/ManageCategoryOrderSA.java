package com.cleartrip.local.camp.ttd;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ManageCategoryOrderSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void manageDataTTDOrderCategorySA_37717(String user, String domain) throws Exception {
		HashMap<String, String> inputTypes = new HashMap<>();
		if (domain.equals("com")) {
			inputTypes.put("city", "Bangalor");
		} else {
			inputTypes.put("city", "Dubai");
		}
		inputTypes.put("productType", "Things to do");
		inputTypes.put("category", "Testing ttd");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/manage_data/manage_themes");
		manageOrderCollection(inputTypes);
	}

}
